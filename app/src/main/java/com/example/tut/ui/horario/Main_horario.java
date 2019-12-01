package com.example.tut.ui.horario;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tut.R;
import com.example.tut.Recordatorios;

public class Main_horario extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);
        Button recordatorio = findViewById(R.id.Recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horariofragment nuevo = new horariofragment();
                nuevo.boton();
            }
        });
    }
}
