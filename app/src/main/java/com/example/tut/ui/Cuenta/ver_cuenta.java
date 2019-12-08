package com.example.tut.ui.Cuenta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tut.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ver_cuenta extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private EditText id;
    private EditText user;
    private EditText email;
    private Button guardar;
    private String ident;
    private String email1;
    private String userUid;
    private String user1;
    Boolean confuser = false;
    Boolean confemail = false;
    Boolean confid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cuenta);
        id = findViewById(R.id.id);
        user = findViewById(R.id.user);
        email = findViewById(R.id.email);
        guardar = findViewById(R.id.guardar);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Usersid");
        firebaseUser = mAuth.getCurrentUser();
        userUid = firebaseUser.getUid();
        user1 = firebaseUser.getDisplayName();
        email1 = firebaseUser.getEmail();
        email.setText(email1);
        if (user1.isEmpty()) {
            user.setText("Sin especificar");
        } else {
            user.setText(user1);
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevo_user = user.getText().toString();
                String nuevo_email = email.getText().toString();
                String nuevo_id = id.getText().toString();

                if ((nuevo_user!=user1 && !nuevo_user.isEmpty()) || (nuevo_email!=email1 && !nuevo_email.isEmpty()) || (nuevo_id!=ident && !nuevo_id.isEmpty())){

                    UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(nuevo_user).build();
                    firebaseUser.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                confuser = true;
                            } else {
                                Toast.makeText(ver_cuenta.this, "Hubo un error, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    firebaseUser.updateEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                confemail = true;
                            } else {
                                Toast.makeText(ver_cuenta.this, "Hubo un error, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Usersid");
                    idref.child(userUid).setValue(nuevo_id);
                    if (confemail && confuser){
                        Toast.makeText(ver_cuenta.this, "Se han actualizado los datos del perfil con éxito", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    ident = dataSnapshot.child(userUid).getValue().toString();
                    id.setText(ident);
                } catch (Exception e){
                    id.setText("Sin especificar");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
