package com.example.adivinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {
    ArrayList<String> rankingValues = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        final Button juego = findViewById(R.id.juegoButton);
        rankingValues = getIntent().getStringArrayListExtra("valores");
        String filasNombres = "";
        String filasPuntuaciones = "";

        for (int i = 0; i < rankingValues.size(); i+= 2){
            filasNombres = filasNombres + rankingValues.get(i) + "\n";
            filasPuntuaciones = filasPuntuaciones + rankingValues.get(i+1) + "\n";

        }
        TextView nombres = findViewById(R.id.nombres);
        TextView puntuacion = findViewById(R.id.puntuacion);

        nombres.setText(filasNombres);
        puntuacion.setText(filasPuntuaciones);

        juego.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent volverAlJuego = new Intent(Ranking.this, MainActivity.class);
                volverAlJuego.putExtra("valores", rankingValues);
                startActivity(volverAlJuego);
            }
        });
    }

}