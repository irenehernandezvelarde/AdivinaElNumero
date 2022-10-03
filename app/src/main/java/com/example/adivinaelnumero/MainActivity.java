package com.example.adivinaelnumero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    private int contador = 0;
    private ArrayList<String> rankingValues = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            rankingValues = parametros.getStringArrayList("valores");
        }
        Random rand = new Random();
        AtomicInteger randNumber = new AtomicInteger(rand.nextInt(10) + 1);
        final Button button = findViewById(R.id.checkButton);
        final EditText number = findViewById(R.id.number);
        TextView counter;
        TextView registro;
        counter = (TextView) findViewById(R.id.contador);
        registro = (TextView) findViewById(R.id.registro);
        registro.setMovementMethod(new ScrollingMovementMethod());
        final Button ranking = findViewById(R.id.rankingButton);
        Intent intent = new Intent(MainActivity.this, Ranking.class);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (number.getText().length() == 0) {
                    counter.setText("INTRODUCE UN NÚMERO");
                }else {
                    contador ++;
                    registro.append("\n");
                    counter.setText("Intentos: " + Integer.toString(contador));
                    if (Integer.parseInt(number.getText().toString()) > randNumber.get()) {
                        Context context = getApplicationContext();
                        CharSequence text = "El número que buscas es MENOR.";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        registro.append("El número que buscas es MENOR.\n");
                        toast.show();

                    } else if (Integer.parseInt(number.getText().toString()) < randNumber.get()) {
                        Context context = getApplicationContext();
                        CharSequence text = "El número que buscas es MAYOR.";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        registro.append("El número que buscas es MAYOR.\n");
                        toast.show();

                    } else if (Integer.parseInt(number.getText().toString()) == randNumber.get()) {
                        Context context = getApplicationContext();
                        CharSequence text = "HAS ACERTADO!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        registro.append("HAS ACERTADO!\n");
                        toast.show();

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage(counter.getText().toString()+"\n"+ "Escribe tu nombre y dale al botón 'REGISTRAR' para entrar en el raking, sino dale a seguir jugando.");
                        builder.setTitle("ENORABUENA, HAS ACERTADO!");
                        final EditText editTextName1 = new EditText(MainActivity.this);
                        builder.setView(editTextName1);
                        LinearLayout layoutName = new LinearLayout(MainActivity.this);
                        layoutName.setOrientation(LinearLayout.VERTICAL);
                        layoutName.addView(editTextName1); // displays the user input bar
                        builder.setView(layoutName);
                        builder.setCancelable(false);
                        builder.setNegativeButton("SEGUIR JUGANDO", (DialogInterface.OnClickListener) (dialog, which) -> {
                            dialog.cancel();
                            randNumber.set(rand.nextInt(10) + 1);
                            registro.setText("REGISTRO:\n");
                            contador = 0;
                            counter.setText("Intentos: " + Integer.toString(contador));
                        });
                        builder.setPositiveButton("REGISTRAR", (DialogInterface.OnClickListener) (dialog, which) -> {
                            dialog.cancel();
                            String getInput = editTextName1.getText().toString();

                            rankingValues.add(getInput);
                            rankingValues.add(Integer.toString(contador));
                            System.out.println(rankingValues.toString());
                            intent.putExtra("valores",rankingValues);

                            startActivity(intent);

                            counter.setText("Intentos: " + Integer.toString(contador));
                            randNumber.set(rand.nextInt(10) + 1);
                            registro.setText("REGISTRO:\n");
                            contador = 0;

                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                number.setText("");
            }
        });
        ranking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            startActivity(intent);
            }
        });
    }
}