package com.example.tut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Recordatorios extends AppCompatActivity {
    private CheckBox che1, che11, che2, che21, che22, che23, che3, che31, che32, che4, che5, che6, che7, che8, che81, che82, che83, che84;
    private TextView tut1, hor1, tut2, hor2,tut3, hor3,tut4, hor4,tut5, hor5,tut6, hor6,tut7, hor7,tut8, hor8,tut9, hor9,tut10, hor10,tut11, hor11,tut12, hor12,tut13, hor13,tut14, hor14,tut15, hor15,tut16, hor16,tut17, hor17,tut18, hor18;
    private NotificationCompat.Builder mBuilder;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        setContentView(R.layout.activity_recordatorios);
        tut1 = findViewById(R.id.tut1);
        hor1 = findViewById(R.id.hor1);
        tut2 = findViewById(R.id.tut2);
        hor2 = findViewById(R.id.hor2);
        tut3 = findViewById(R.id.tut3);
        hor3 = findViewById(R.id.hor3);
        tut4 = findViewById(R.id.tut4);
        hor4 = findViewById(R.id.hor4);
        tut5 = findViewById(R.id.tut5);
        hor5 = findViewById(R.id.hor5);
        tut6 = findViewById(R.id.tut6);
        hor6 = findViewById(R.id.hor6);
        tut7 = findViewById(R.id.tut7);
        hor7 = findViewById(R.id.hor7);
        tut8 = findViewById(R.id.tut8);
        hor8 = findViewById(R.id.hor8);
        tut9 = findViewById(R.id.tut9);
        hor9 = findViewById(R.id.hor9);
        tut10 = findViewById(R.id.tut10);
        hor10 = findViewById(R.id.hor10);
        tut11 = findViewById(R.id.tut11);
        hor11 = findViewById(R.id.hor11);
        tut12 = findViewById(R.id.tut12);
        hor12 = findViewById(R.id.hor12);
        tut13 = findViewById(R.id.tut13);
        hor13 = findViewById(R.id.hor13);
        tut14 = findViewById(R.id.tut14);
        hor14 = findViewById(R.id.hor14);
        tut15 = findViewById(R.id.tut15);
        hor15 = findViewById(R.id.hor15);
        tut16 = findViewById(R.id.tut16);
        hor16 = findViewById(R.id.hor16);
        tut17 = findViewById(R.id.tut17);
        hor17 = findViewById(R.id.hor17);
        tut18 = findViewById(R.id.tut18);
        hor18 = findViewById(R.id.hor18);


        che1 = findViewById(R.id.c10);
        che11 = findViewById(R.id.d11);
        che2 = findViewById(R.id.c20);
        che21 = findViewById(R.id.c21);
        che22 = findViewById(R.id.c22);
        che23 = findViewById(R.id.c23);
        che3 = findViewById(R.id.c30);
        che31 = findViewById(R.id.c31);
        che32 = findViewById(R.id.c32);
        che4 = findViewById(R.id.c40);
        che5 = findViewById(R.id.c50);
        che6 = findViewById(R.id.c60);
        che7 = findViewById(R.id.c70);
        che8 = findViewById(R.id.c80);
        che81 = findViewById(R.id.c81);
        che82 = findViewById(R.id.c82);
        che83 = findViewById(R.id.c83);
        che84 = findViewById(R.id.c84);
        che1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che1.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut1.getText().toString());
                    mapa.put("Horario",hor1.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che11.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut2.getText().toString());
                    mapa.put("Horario",hor2.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che2.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut3.getText().toString());
                    mapa.put("Horario",hor3.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che21.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut4.getText().toString());
                    mapa.put("Horario",hor4.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che22.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut5.getText().toString());
                    mapa.put("Horario",hor5.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che23.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut6.getText().toString());
                    mapa.put("Horario",hor6.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che3.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut7.getText().toString());
                    mapa.put("Horario",hor7.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che31.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che31.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut8.getText().toString());
                    mapa.put("Horario",hor8.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che32.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che32.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut9.getText().toString());
                    mapa.put("Horario",hor9.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che4.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut10.getText().toString());
                    mapa.put("Horario",hor10.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che5.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut11.getText().toString());
                    mapa.put("Horario",hor11.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che6.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut12.getText().toString());
                    mapa.put("Horario",hor12.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che7.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut13.getText().toString());
                    mapa.put("Horario",hor13.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che8.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut14.getText().toString());
                    mapa.put("Horario",hor14.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che81.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che81.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut15.getText().toString());
                    mapa.put("Horario",hor15.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che82.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che82.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut16.getText().toString());
                    mapa.put("Horario",hor16.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che83.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che83.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut17.getText().toString());
                    mapa.put("Horario",hor17.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });
        che84.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (che84.isChecked()) {
                    Toast.makeText(Recordatorios.this, "A partir de ahora recibirás notificaciones que te recordarán los horarios de esta tutoría", Toast.LENGTH_LONG).show();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String userUid = firebaseUser.getUid();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference idref = ref.child("Recomendaciones");
                    Map<String, String> mapa = new HashMap<>();
                    mapa.put("Tutor", tut18.getText().toString());
                    mapa.put("Horario",hor18.getText().toString());
                    idref.child(userUid).setValue(mapa);
                }
            }
        });

    }
}
