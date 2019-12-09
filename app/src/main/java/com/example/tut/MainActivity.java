package com.example.tut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tut.ui.Cuenta.Cuenta;
import com.example.tut.ui.mensajeria.mensajeria;

public class MainActivity extends AppCompatActivity {
    private ImageView enviar;
    private ImageView config;
    private ImageView horario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        enviar = findViewById(R.id.enviar);
        config = findViewById(R.id.config);
        horario = findViewById(R.id.visual);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, mensajeria.class));
            }
        });
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cuenta.class));
            }
        });
        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, tutorusTutoria.class));
            }
        });
    }
}
