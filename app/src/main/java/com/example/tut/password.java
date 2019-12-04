package com.example.tut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class password extends AppCompatActivity {
    private Button restablecer;
    private String correo;
    private ProgressDialog dialog;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        final EditText email = findViewById(R.id.email);
        restablecer = findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();
        restablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = email.getText().toString().trim();
                if (correo.isEmpty()){
                    Toast.makeText(password.this, "Debe ingresar un correo electrónico", Toast.LENGTH_SHORT).show();
                } else {
                    resetPassword();
                }
            }
        });
    }

    private void resetPassword(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(password.this, "Se ha enviado un correo a la cuenta para reestablecer tu contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(password.this, "Hubo un error, inténtalo de nuevo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
