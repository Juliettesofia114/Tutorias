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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        listaTutorias.setAdapter( new tutoriasAdapterLoading(this,0));

        lookTutorias();

    }

    private void lookTutorias() {
        final ArrayList<String[]> mapDocuments = new ArrayList<>();

        db.collection("Tutorias").get()
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

                            try {
                                dataOrdered = orderedByDate(mapDocuments);
                                listaTutorias.setAdapter(new tutoriasAdapter(tutorusTutoria.this, dataOrdered));
                            } catch (ParseException e) {
                                Toast.makeText(tutorusTutoria.this, e.toString(), Toast.LENGTH_LONG);
                                listaTutorias.setAdapter(new tutoriasAdapterLoading(tutorusTutoria.this, 1));
                            }
                        } else {
                            listaTutorias.setAdapter(new tutoriasAdapterLoading(tutorusTutoria.this, 1));
                        }
                    }
                });
    }

    private ArrayList<String[]>  orderedByDate(ArrayList<String[]> converted) throws ParseException {

        ArrayList<Date> dates = new ArrayList<>();

        for(String [] a: converted){
            dates.add(new SimpleDateFormat("dd/MM/yyyy hh:mm a").parse(a[1]));
        }

        Collections.sort(dates);

        ArrayList<String[]> ordered = new ArrayList<>();
        String [] doc;
        Boolean [] checked = new Boolean[dates.size()];
        for(int i = 0; i < dates.size(); i++){
            checked[i] = false;
        }

        for(int i = 0; i < dates.size(); i++){
            doc = new String[6];
            for(int j = 0; j < dates.size(); j++){

                if( (new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(dates.get(i))).equals(converted.get(j)[1]) && !checked[j] ){
                    doc[0] = converted.get(j)[0];
                    doc[1] = converted.get(j)[1];
                    doc[2] = converted.get(j)[2];
                    doc[3] = converted.get(j)[3];
                    doc[4] = converted.get(j)[4];
                    doc[5] = converted.get(j)[5];
                    ordered.add(doc);
                    checked[j] = true;
                    break;
                }
            }
        }

        return ordered;
    }

}

