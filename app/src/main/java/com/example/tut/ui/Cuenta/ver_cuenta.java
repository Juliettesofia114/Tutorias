package com.example.tut.ui.Cuenta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tut.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ver_cuenta extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private EditText id;
    private EditText user;
    private EditText email;
    private Button guardar;
    private String email1;
    private String userUid;
    private String user1;
    private ProgressDialog progressDialog;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cuenta);

        id = findViewById(R.id.id);
        user = findViewById(R.id.user);
        email = findViewById(R.id.email);
        guardar = findViewById(R.id.guardar);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        userUid = firebaseUser.getUid();
        user1 = firebaseUser.getDisplayName();
        email1 = firebaseUser.getEmail();

        progressDialog = new ProgressDialog(this);
        progressDialog.dismiss();

        email.setText(email1);

        if (user1.isEmpty()) {
            user.setText("Sin especificar");
        } else {
            user.setText(user1);
        }

        db.collection("Usuarios").document(userUid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    id.setText("" + task.getResult().get("ID"));
                }
                else{
                    id.setText("Error al conectar");
                }
            }
        });


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nuevo_user = user.getText().toString();
                final String nuevo_email = email.getText().toString();
                final String nuevo_id = id.getText().toString();

                if(nuevo_email.isEmpty()){
                    email.setError("Campo obligatorio");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(nuevo_email).matches()) {
                    email.setError("Ingrese un correo válido");
                    email.requestFocus();
                    return;
                }

                if(nuevo_user.isEmpty()){
                    user.setError("Campo obligatorio");
                    user.requestFocus();
                    return;
                }

                if(nuevo_id.isEmpty()){
                    id.setError("Campo obligatorio");
                    id.requestFocus();
                    return;
                }



                    progressDialog.setMessage("Actualizando datos");
                    progressDialog.show();

                    UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(nuevo_user).build();
                    firebaseUser.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                firebaseUser.updateEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Map<String, Object> map = new HashMap<>();
                                            map.put("Nombre", nuevo_user);
                                            map.put("ID", Long.parseLong(nuevo_id));

                                            db.collection("Usuarios").document(userUid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(ver_cuenta.this, "Se han actualizado los datos", Toast.LENGTH_SHORT).show();
                                                        progressDialog.dismiss();
                                                    }
                                                    else{
                                                        Toast.makeText(ver_cuenta.this, "Hubo un error, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                                                        progressDialog.dismiss();
                                                    }
                                                }
                                            });
                                        } else {
                                            Toast.makeText(ver_cuenta.this, "Hubo un error, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(ver_cuenta.this, "Hubo un error, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });

            }
        });

    }

}
