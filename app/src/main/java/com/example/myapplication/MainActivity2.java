package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity2 extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Map<String, String> datos = new HashMap<String, String>();

        WebService ws = new
                WebService("https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGrid",
                datos, MainActivity2.this, MainActivity2.this);

        ws.execute("GET", "Public-Merchant-Id", "84e1d0de1fbf437e9779fd6a52a9ca18");

    }


    @Override
    public void processFinish(String result) throws JSONException {
        Log.d("API_RESPONSE", result);  // Imprime la respuesta en la consola
        TextView txtBancos = findViewById(R.id.txtLugaresturisticos);
        StringBuilder lugaresStringBuilder = new StringBuilder();
        JSONObject jsonResult = new JSONObject(result);
        JSONArray lugaresArray = jsonResult.getJSONArray("data");
        for (int i = 0; i < lugaresArray.length(); i++) {
            JSONObject lugar = lugaresArray.getJSONObject(i);
            String categoria = lugar.optString("categoria", "");
            String nombreLugar = lugar.optString("nombre_lugar", "");
            String puntuacion = lugar.optString("puntuacion", "");
            String telefono = lugar.optString("telefono", "");
            lugaresStringBuilder.append("Categoría Perteneciente: ").append(categoria).append("\n");
            lugaresStringBuilder.append("Nombre: ").append(nombreLugar).append("\n");
            lugaresStringBuilder.append("Puntos de Servicio: ").append(puntuacion).append("\n");
            lugaresStringBuilder.append("Tlf: ").append(telefono).append("\n\n");
        }
        // Muestra el resultado filtrado en el TextView
        txtBancos.setText("Información de lugares:\n" + lugaresStringBuilder.toString());
}
}