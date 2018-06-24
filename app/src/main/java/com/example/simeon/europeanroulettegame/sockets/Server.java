package com.example.simeon.europeanroulettegame.sockets;

import com.example.simeon.europeanroulettegame.entities.models.BaseWheel;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            ServerSocket server = new ServerSocket(3523);
            BaseWheel baseWheel = new BaseWheel();
            Socket socket = server.accept();
            socket.setSoTimeout(1000);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                outputStream.writeUTF(baseWheel.getNumber().toString());
                outputStream.flush();
                Thread.sleep(10000);
                baseWheel.generateNumber();
            }

        } catch (Exception e) {

        }


    }


}
