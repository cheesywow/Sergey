package com.sergey.michael.sergey.Engine.TCP;


import android.util.Log;

import org.json.JSONObject;

import java.util.Arrays;

public class Sender {

    public void sendTCP(AsyncResponse response, JSONObject message) {
        TCPFactory tcpFactory = new TCPFactory();
        MyTCP tcp = tcpFactory.getTCP(response);
        try {
            tcp.execute(message);

        } catch (Exception ex) {
            Log.d("Network Exception", "" + ex.toString());
            Log.d("Network Exception", "" + ex.getMessage());
            Log.d("Network Exception", "" + Arrays.toString(ex.getStackTrace()));
        }
    }
}
