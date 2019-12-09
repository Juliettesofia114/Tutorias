package com.example.tut.ui.Cuenta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tut.R;
import com.example.tut.ui.login.Login;
import com.google.firebase.auth.FirebaseAuth;

public class Cuenta extends AppCompatActivity {
    private Button password;
    private Button cerrar;
    private Button ver;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.password);
        cerrar = findViewById(R.id.cerrar);
        ver = findViewById(R.id.ver);

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cuenta.this,password.class));
            }
        });
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), com.example.tut.logIn.class));
                finish();
            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cuenta.this,ver_cuenta.class));
            }
        });
    }
}
