package com.example.tut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class tutoriasAdapterLoading extends BaseAdapter {

    private static LayoutInflater inflater = null;

    private Context contexto;

    public tutoriasAdapterLoading(Context conexto) {
        this.contexto = conexto;

        inflater = (LayoutInflater) conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View vista = inflater.inflate(R.layout.elem_lista2, null);
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