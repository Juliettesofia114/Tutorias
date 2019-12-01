package com.example.tut.ui.login;



import androidx.annotation.NonNull;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.tut.MainActivity;
import com.example.tut.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    String email;
    String password;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button login = findViewById(R.id.login);
        final Button registro = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = usernameEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {

                    if (password.length() >= 6) {
                        register_user();
                    } else {
                        Toast.makeText(Login.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Debe llenar todos los campos para continuar", Toast.LENGTH_LONG).show();

                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = usernameEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    login_user();
                }else {
                    Toast.makeText(Login.this, "Debe llenar todos los campos para continuar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    private void register_user() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(Login.this, "Se ha creado exitosamente un nuevo usuario", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(Login.this, "Este correo ya está registrado", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Login.this, "Hubo un error de autenticación",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    private void login_user() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    Toast.makeText(Login.this, "Correo o contraseña incorrecta", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

