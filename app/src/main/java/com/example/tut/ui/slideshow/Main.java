package com.example.tut.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tut.R;
import com.example.tut.ui.login.Login;
import com.google.firebase.auth.FirebaseAuth;


public class Main extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_slideshow);
        Button cerrar = findViewById(R.id.Cerrar);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideshowFragment fragment = new SlideshowFragment();
                fragment.boton();
            }
        });
    }
}

