package com.example.tut;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class tutorusTutoria extends AppCompatActivity {

    private TextView name, id;
    private ListView listaTutorias;

    private ArrayList<String[]>  dataConverted, dataOrdered;

    //Objeto de Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorus_tutoria);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();

        listaTutorias = (ListView) findViewById(R.id.listaTutorias);
        listaTutorias.setAdapter( new tutoriasAdapterLoading(this));

        try {
            lookTutorias();
        }
        catch (Exception e){
            listaTutorias.setAdapter(new tutoriasAdapterError(tutorusTutoria.this));
        }


    }

    private void lookTutorias() {
        final ArrayList<String[]> mapDocuments = new ArrayList<>();

        db.collection("Tutorias").orderBy("Hora").startAfter(Calendar.getInstance().getTime()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String[] objectOrdered;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                objectOrdered = new String[6];
                                objectOrdered[0] = document.get("Nombre").toString();
                                objectOrdered[1] = new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(document.get("Hora"));
                                objectOrdered[2] = document.get("Perfil").toString();

                                if ((boolean) document.get("Asistencia")) {
                                    objectOrdered[3] = "true";
                                } else {
                                    objectOrdered[3] = "false";
                                }

                                objectOrdered[4] = document.getId();
                                objectOrdered[5] = document.get("Tutor").toString();

                                mapDocuments.add(objectOrdered);
                            }
                            listaTutorias.setAdapter(new tutoriasAdapter(tutorusTutoria.this, mapDocuments));
                        } else {
                            listaTutorias.setAdapter(new tutoriasAdapterError(tutorusTutoria.this));
                        }
                    }
                });
    }


}

