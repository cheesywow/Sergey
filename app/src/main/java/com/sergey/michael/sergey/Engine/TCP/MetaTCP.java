package com.sergey.michael.sergey.Engine.TCP;

import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

public class MetaTCP extends MyTCP{

    MetaTCP(AsyncResponse delegate) {
        super(delegate);
    }

    @Override
    protected String doInBackground(Object[] params){
        return send((JSONObject) params[0]);
    }

    private String send(JSONObject message) {
        String data = "";
        try {
            // Creating new socket connection to the IP (first parameter) and its opened port (second parameter)
            Socket s = new Socket(host, port);
            InputStream nis = s.getInputStream();
            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
            out.write(message.toString());
            out.flush();
            Log.d("Network", "Got to send message");
            data = listen(nis);
            // Close the socket connection
            s.close();
            //Log.d("data",""+data);
        }

        catch (Exception ex) {
            Log.d("Network Exception", "" + ex.toString());
            Log.d("Network Exception", "" + ex.getMessage());
            Log.d("Network Exception", "" + Arrays.toString(ex.getStackTrace()));
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        response.processFinish(result);
    }
}

