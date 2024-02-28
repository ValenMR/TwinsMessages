package com.example.valensmessages;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkManager extends AsyncTask<Void, Void, Void> {

    Socket socket; // Reemplaza "192.168.0.1" con la dirección IP del servidor y "12345" con el puerto del servidor
    DataOutputStream outputStream;
    DataInputStream inputStream;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // Realiza la conexión y obtiene los streams de entrada y salida
            socket = new Socket("192.168.0.1", 12345);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            // Maneja cualquier excepción que se produzca durante la conexión
            e.printStackTrace();
        }
        return null;
    }
}
