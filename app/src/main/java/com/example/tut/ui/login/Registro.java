package com.example.tut.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    EditText correo;
    EditText contra;
    private EditText usuario;
    private EditText ident;
    private FirebaseAuth mAuth;
    private Button regis;
    private boolean crear = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.email);
        contra = findViewById(R.id.contra);
        usuario = findViewById(R.id.nombre);
        ident = findViewById(R.id.id);
        regis = findViewById(R.id.registrarse);


        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = correo.getText().toString().trim();
                String password = contra.getText().toString().trim();
                String user = usuario.getText().toString().trim();
                String id = ident.getText().toString().trim();
                if (!email.isEmpty() && !password.isEmpty() && !user.isEmpty() && !id.isEmpty()) {

                    if (password.length() >= 6) {
                        register_user();
                    } else {
                        Toast.makeText(Registro.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Registro.this, "Debe llenar todos los campos para continuar", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    private void register_user() {
        String email = correo.getText().toString().trim();
        String password = contra.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    save_info();
                    startActivity(new Intent(Registro.this, MainActivity.class));
                    finish();
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(Registro.this, "Este correo ya está registrado", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Registro.this, "Hubo un error de autenticación",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    private void save_info(){
        //FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String us = usuario.getText().toString().trim();
        String id = ident.getText().toString().trim();
        FirebaseUser user = mAuth.getCurrentUser();
        String userUid = user.getUid();
        String type_user = "0";
        if (user!=null){
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference();
            DatabaseReference idref = ref.child("Usersid");
            idref.child(userUid).setValue(id);
            Map<String,Object> datos = new HashMap<>();
            datos.put("ID", userUid);
            datos.put("Nombre", us);
            datos.put("Usuario", type_user);
           // firestore.collection("Usuarios").document(userUid).set(datos).addOnCompleteListener(new OnCompleteListener<Void>() {
                //@Override
                //public void onComplete(@NonNull Task<Void> task) {
                    //if (task.isSuccessful()){
                        //crear = true;
                    //}
                }
            //});
                UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(us).build();
                user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registro.this, "Se ha creado exitosamente un nuevo usuario", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Registro.this, "Hubo un problema, inténtalo de nuevo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            //}
        }
    }
