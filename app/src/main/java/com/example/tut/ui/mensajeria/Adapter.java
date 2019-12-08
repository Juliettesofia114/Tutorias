package com.example.tut.ui.mensajeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tut.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Holder> {
    List<Mensaje> mensajeList = new ArrayList<>();
    Context context;

    public Adapter(Context context) {
        this.context = context;
    }
    public void addMensaje(Mensaje mensaje){
        mensajeList.add(mensaje);
        notifyItemInserted(mensajeList.size());
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_mensajes, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.getUser().setText(mensajeList.get(position).getUser());
        holder.getMes().setText(mensajeList.get(position).getMsn());
        holder.getHora().setText(mensajeList.get(position).getHora());

    }

    @Override
    public int getItemCount() {
        return mensajeList.size();
    }
}
