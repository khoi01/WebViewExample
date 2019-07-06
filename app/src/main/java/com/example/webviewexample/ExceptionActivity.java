package com.example.webviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ExceptionActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);

        textView = (TextView) findViewById(R.id.exception_text_id);
        Intent i = getIntent();
        Info info = (Info) i.getParcelableExtra("Info");

        textView.setText(info.getMessage());


    }

}
