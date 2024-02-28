package com.example.valensmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;

    TextView mensajeTextView; /*variable de instancia del tipo TextView*/
    EditText mensajeEditText;  /*variable de instancia*/

    NetworkManager nm;

    public void buttonPress(View view){

        Log.i("Info", "Botón presionado");
        mensajeEditText = findViewById(R.id.mensajeEditText);  /*ya se estaría poniendo en EditText, cuando presione el botón es que se edita*/
        String mensajeString = mensajeEditText.getText().toString();  /*convertir lo de editText en string*/
        nm.enviarmensaje(mensajeString);

    }

    public void agregarTexto (String textoAAgregar){
        mensajeEditText = findViewById(R.id.mensajeEditText);  /*ya se estaría poniendo en EditText, cuando presione el botón es que se edita*/
        String actual = this.mensajeEditText.getText().toString();
        actual.concat(textoAAgregar);
        mensajeTextView.setText(actual);
    }

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);   /*método de OnCreate*/
        setContentView(R.layout.activity_main);  /*asigna el contenido de la vista*/
        mensajeTextView = findViewById(R.id.mensajeTextView); /* creando la conexión al botón -  a esta var de instancia va a hacer una conexión con el elemento textView */
        mensajeTextView.setText("Hola Mundo"); /*utilizando la variable - se le está pasando como parámetro el mensaje hola mundo*/
        nm = new NetworkManager(this);
        nm.execute();
    }
}