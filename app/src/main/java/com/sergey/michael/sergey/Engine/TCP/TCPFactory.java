package com.sergey.michael.sergey.Engine.TCP;

class TCPFactory {
    MyTCP getTCP(AsyncResponse response){
        switch ("Meta"){
            case "Meta": return new MetaTCP(response);
        }
        return null;
    }
}
