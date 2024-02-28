package com.example.valensmessages;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkManager extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private Context context; // Agregar esta línea

    Socket socket; // Reemplaza "192.168.0.1" con la dirección IP del servidor y "12345" con el puerto del servidor
    DataOutputStream outputStream;
    DataInputStream inputStream;

    public NetworkManager(Activity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext(); // Agregar esta línea
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            String ip = Formatter.formatIpAddress(ipAddress);
            Log.d("ip", "ip: " + ip);

            // Realiza la conexión y obtiene los streams de entrada y salida
            socket = new Socket("192.168.20.120", 1234); //puerto no bloqueado, ip del celular
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            final String message = inputStream.readUTF();
                            // Muestra el mensaje en la interfaz de usuario
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    MainActivity.getInstance().agregarTexto("asd");
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            Log.i("ERR", "error de io");
            e.printStackTrace();
        }
        return null;
    }

    public Void enviarmensaje(String valor){

        try {
            Log.i("info", "valor de outputstream" + outputStream );

            outputStream.writeUTF(valor);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    Log.e("NetworkManager", "Error closing output stream", e);
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}