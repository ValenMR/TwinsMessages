package com.example.valensmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mensajeTextView; /*variable de instancia del tipo TextView*/
    EditText mensajeEditText;  /*variable de instancia*/

    public void buttonPress(View view){

        Log.i("Info", "Botón presionado");

        mensajeEditText = findViewById(R.id.mensajeEditText);  /*ya se estaría poniendo en EditText, cuando presione el botón es que se edita*/

        String mensajeString = mensajeEditText.getText().toString();  /*convertir lo de editText en string*/
        mensajeTextView.setText(mensajeString);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   /*método de OnCreate*/
        setContentView(R.layout.activity_main);  /*asigna el contenido de la vista*/
        mensajeTextView = findViewById(R.id.mensajeTextView); /* creando la conexión al botón -  a esta var de instancia va a hacer una conexión con el elemento textView */
        mensajeTextView.setText("Hola Mundo"); /*utilizando la variable - se le está pasando como parámetro el mensaje hola mundo*/
        new NetworkManager().execute();
    }
}