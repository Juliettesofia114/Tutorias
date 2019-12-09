package com.example.tut;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class tutoriasAdapter extends BaseAdapter {


    private static LayoutInflater inflater = null;
    private ProgressDialog progressDialog;

    private Context contexto;
    private ArrayList<String[]> datos;
    private FirebaseFirestore db;
    private FirebaseUser user;

    private int[] tutorColors = {Color.rgb(255,119,79),
            Color.rgb(76, 175, 80),
            Color.rgb(166, 28, 28),
            Color.rgb(103, 58, 183),
            Color.rgb(57, 57, 60),
            Color.rgb(139, 195, 74),
            Color.rgb(255, 193, 7),
            Color.rgb(76, 160, 225)};

    private int[] tutorIcons = {R.mipmap.ic_fisica_foreground, R.mipmap.ic_prog_foreground, R.mipmap.ic_circuitos_foreground, R.mipmap.ic_quimica_foreground, R.mipmap.ic_termo_foreground, R.mipmap.ic_bio_foreground, R.mipmap.ic_dibujo_foreground, R.mipmap.ic_mate_foreground};

    private final String[][] asigns = {
            {"Fundamentos de Física", "Programación", "Circuitos, Señales y Control", "Química", "Termodinámica y Transferencia de Calor y Masa", "Hidráulica y Manejo de Aguas", "Dinámica, Estática y Dibujo Básico", "Matemáticas"},
            {"Fundamentos de Electricidad y Magnetismo", "Fundamentos de Física Moderna", "Fundamentos de Mecánica", "Fundamentos de Oscilaciones, Ondas y Óptica", "Campos electromagnéticos"},
            {"Programación de Computadores", "Programación Orientada a Objetos", "Estructuras de Datos", "Métodos Numéricos"},
            {"Circuitos Eléctricos I", "Circuitos Eléctricos II", "Electrónica Análoga I", "Electrónica Análoga II", "Electrónica Básica", "Electrónica Digital I", "Señales y Sistemas I", "Señales y Sistemas II", "Control", "Fundamentos de Control"},
            {"Laboratorio técnicas básicas en química", "Laboratorio principios de análisis químico", "Principios de química inorgánica", "Principios de química orgánica", "Principios de química", "Fluidos", "Balance de energía y equilibrio químico", "Operaciones de separación"},
            {"Transferencia de Masa", "Transferencia de Calor", "Fundamentos de Transferencia de Calor", "Transferencia de Calor y Masa", "Termodinámica", "Termodinámica Química", "Termodinámica Técnica"},
            {"Mecánica de Fluidos", "Hidráulica Básica", "Hidrología", "Diseño Estructural", "Análisis Estructural Básico", "Mecánica de Sólidos", "Mecánica de Suelos", "Estática", "Dinámica"},
            {"Principios de Estática", "Principios de Dinámica", "Resistencia de Materiales", "Dibujo Básico", "Dibujo de Máquinas"},
            {"Matemáticas Básicas", "Cálculo Diferencial", "Cálculo Integral", "Cálculo en Varias Variables", "Álgebra Lineal", "Ecuaciones Diferenciales", "Probabilidad y Estadística Fundamental"}
    };

    public tutoriasAdapter(Context conexto, ArrayList<String[]> datos) {
        this.contexto = conexto;
        this.datos = datos;

        inflater = (LayoutInflater) conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View vista;
        if(datos.size() == 0){
            vista = inflater.inflate(R.layout.elem_lista0, null);
        }
        else{
            vista = inflater.inflate(R.layout.elem_lista, null);

            TextView Perfil = (TextView) vista.findViewById(R.id.textoPerfil);
            TextView Nombre = (TextView) vista.findViewById(R.id.textoAsign);
            TextView Tiempo = (TextView) vista.findViewById(R.id.textoFecha);
            TextView tutor = (TextView) vista.findViewById(R.id.textoTutor);
            TextView recor = (TextView) vista.findViewById(R.id.textoAsis);
            final CheckBox Asistencia = (CheckBox) vista.findViewById(R.id.checkAsistencia);
            ImageView icono = (ImageView) vista.findViewById(R.id.imagePerfil);
            CardView tarjeta = (CardView) vista.findViewById(R.id.card);

            Perfil.setText(asigns[0][Integer.parseInt(datos.get(i)[2])]);
            Nombre.setText(asigns[Integer.parseInt(datos.get(i)[2]) + 1][Integer.parseInt(datos.get(i)[0])]);
            Tiempo.setText(datos.get(i)[1]);
            tutor.setText(datos.get(i)[5]);

            if(Boolean.parseBoolean(datos.get(i)[3])){
                recor.setText("Asisitirá");
                tarjeta.setCardBackgroundColor(tutorColors[Integer.parseInt(datos.get(i)[2])]);
                switch (Integer.parseInt(datos.get(i)[2])){
                    case(2):
                    case(3):
                    case(4):
                        icono.setColorFilter(Color.WHITE);
                        Perfil.setTextColor(Color.WHITE);
                        Nombre.setTextColor(Color.WHITE);
                        Tiempo.setTextColor(Color.WHITE);
                        tutor.setTextColor(Color.WHITE);
                        Asistencia.setTextColor(Color.WHITE);
                        recor.setTextColor(Color.WHITE);
                        break;
                    default:
                        icono.setColorFilter(Color.BLACK);
                        Perfil.setTextColor(Color.BLACK);
                        Nombre.setTextColor(Color.BLACK);
                        Tiempo.setTextColor(Color.BLACK);
                        tutor.setTextColor(Color.BLACK);
                        Asistencia.setTextColor(Color.BLACK);
                        recor.setTextColor(Color.BLACK);
                        break;
                }
            }
            else{
                recor.setText("No asisitirá");
                tarjeta.setCardBackgroundColor(Color.GRAY);
            }

            Asistencia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(Asistencia.isChecked()){
                        Toast.makeText(contexto, "Ahora te recordaremos de esta tutoría", Toast.LENGTH_SHORT).show();
                    }

                }
            });


            icono.setImageResource(tutorIcons[Integer.parseInt(datos.get(i)[2])]);

            progressDialog = new ProgressDialog(contexto);
            final int p = i;
            db = FirebaseFirestore.getInstance();

        }

        return vista;
    }

    @Override
    public int getCount() {
        return (datos.size() == 0) ? 1 : datos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}