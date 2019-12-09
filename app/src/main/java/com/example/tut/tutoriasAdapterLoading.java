package com.example.tut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class tutoriasAdapterLoading extends BaseAdapter {

    private static LayoutInflater inflater = null;

    private Context contexto;
    private String[] datos = {"Cargando tutorías...", "Ha ocurrido un error al cargar las tutorías"};
    private int fail;

    public tutoriasAdapterLoading(Context conexto, int fail) {
        this.contexto = conexto;
        this.fail = fail;

        inflater = (LayoutInflater) conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elem_lista2, null);

        TextView carga = (TextView) vista.findViewById(R.id.textoCarga);
        ProgressBar round = (ProgressBar) vista.findViewById(R.id.progressBarTutoria);

        carga.setText(datos[fail]);

        if(fail == 1){
            round.setVisibility(View.GONE);
        }

        return vista;
    }

    @Override
    public int getCount() {
        return 1;
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