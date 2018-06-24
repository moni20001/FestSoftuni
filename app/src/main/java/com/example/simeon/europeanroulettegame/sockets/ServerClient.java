package com.example.simeon.europeanroulettegame.sockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class ServerClient {

    public static void main(String[] args) throws IOException {

        ServerClient client = new ServerClient("192.168.166.18",3523);
        String debug = "";
        while(true){
            debug = client.numberInputStream();
            String arr[] = debug.split("\n");
            for (int i = 0; i < arr.length; i++) {
                if(!(arr[i]==null)){
                        Integer numb = Integer.valueOf(arr[i]);
                        System.out.println(numb);
                    }

                }
            }
        }

    private Socket socket;
    private DataInputStream inputStream;

    public ServerClient(String ip,int port) throws IOException {
        this.socket = new Socket(ip,port);
        this.inputStream = new DataInputStream(socket.getInputStream());
    }

    public String numberInputStream() throws IOException {
        return String.valueOf(this.inputStream.readUTF());
    }
    public void closeSocket() throws IOException {
        this.socket.close();
    }
}
