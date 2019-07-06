package com.example.webviewexample;

import android.os.Parcel;
import android.os.Parcelable;

public class Info implements Parcelable {

    private boolean IsValid;
    private String Message;

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.IsValid ? (byte) 1 : (byte) 0);
        dest.writeString(this.Message);
    }

    public Info() {
    }

    protected Info(Parcel in) {
        this.IsValid = in.readByte() != 0;
        this.Message = in.readString();
    }

    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
}
