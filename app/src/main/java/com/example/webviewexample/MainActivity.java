package com.example.webviewexample;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;

public class MainActivity extends AppCompatActivity {

//https://www.baeldung.com/guide-to-okhttp
    //https://square.github.io/okhttp/
    //https://stackoverflow.com/questions/34597220/how-to-wait-for-the-result-on-a-okhttp-call-to-use-it-on-a-test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Info info = AccessValidationWithPort();



        if(info.isValid()){
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.setWebViewClient(new WebViewClient());
            //myWebView.loadUrl("https://pljenkinsapgr01.hlbank.my/jenkins");
            myWebView.loadUrl("https://www.google.com/");

        }else{


            Intent intent = new Intent(this, ExceptionActivity.class);
            intent.putExtra("Info",info);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }





    }


    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }


    public Info AccessValidationWithoutPort() {



        Info info = new Info();

        info.setMessage("");
        info.setValid(false);


        try{
            InetAddress ip=InetAddress.getByName("google.com");

            if(ip.isReachable(8000)){

                info.setValid(true);
            }

        }catch (Exception e){
            // Handle exception
            info.setMessage(e.getMessage());
        }


        return info;
    }
    public Info AccessValidationWithPort() {


        boolean isValid = false;

        Info info = new Info();

        info.setMessage("");
        info.setValid(isValid);

        try {
            //NOTE: GET BY ADDRESS
            //byte[] ipAddr = new byte[]{(byte) 172,(byte)217, 24, (byte) 174};
            //InetAddress ip=InetAddress.getByAddress(ipAddr);

            InetAddress ip=InetAddress.getByName("google.com");

            SocketAddress sockaddr = new InetSocketAddress(ip,443);
            // Create an unbound socket
            Socket sock = new Socket();

            // This method will block no more than timeoutMs.
            // If the timeout occurs, SocketTimeoutException is thrown.
            int timeoutMs = 2000;   // 2 seconds
            sock.connect(sockaddr, timeoutMs);
            isValid = true;
            info.setValid(isValid);
        } catch(IOException e) {
            // Handle exception
            info.setMessage(e.getMessage());
        }

        return info;
    }



}
