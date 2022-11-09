package com.example.adivinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ranking extends AppCompatActivity {
    ArrayList<String> rankingValues = new ArrayList<>();

    TableLayout tablita;
    List<String> usernames = new LinkedList<String>();
    List<String>scores = new LinkedList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        final Button juego = findViewById(R.id.juegoButton);
        rankingValues = getIntent().getStringArrayListExtra("valores");

        tablita = new TableLayout(getApplicationContext());
        this.cambio(rankingValues);

        TableLayout tbl = findViewById(R.id.tabla);

        TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1.0f);
        TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT);

        for(int i = 0 ;i < usernames.size(); i++)
        {
            TableRow row=new TableRow(this);
            TextView username = new TextView(this);
            TextView score = new TextView(this);

            username.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            score.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            username.setText(usernames.get(i));
            score.setText(scores.get(i));

            username.setLayoutParams(params1);
            score.setLayoutParams(params1);

            row.addView(username);
            row.addView(score);
            row.setLayoutParams(params2);
            tbl.addView(row);
        }
        juego.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent volverAlJuego = new Intent(Ranking.this, MainActivity.class);
                volverAlJuego.putExtra("valores", rankingValues);
                startActivity(volverAlJuego);
            }
        });
    }
    private void cambio(ArrayList<String> rankingValues){
        for(int i = 0; i < rankingValues.size(); i = i+2){
            usernames.add(rankingValues.get(i));
            scores.add(rankingValues.get(i+1));
        }
    }
}