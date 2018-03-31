package com.sergey.michael.sergey.Engine.TCP;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;

public abstract class MyTCP extends AsyncTask<Object,Integer,String>{
    AsyncResponse response = null;

    public static String host = "52.89.132.145";
    public static int port = 6969;

    MyTCP(AsyncResponse delegate){
        this.response = delegate;
    }

    @Override
    protected abstract String doInBackground(Object[] params);

    @Override
    protected abstract void onPostExecute(String result);

    String listen(InputStream nis) throws IOException {
        StringBuilder data = new StringBuilder();
        int current;
        while(true){
            current = nis.read();
            if(current =='\r'){
                current = nis.read();
                if(current == '\n'){
                    break;
                }
            }
            data.append((char) current);
        }
        return data.toString();
    }
}
