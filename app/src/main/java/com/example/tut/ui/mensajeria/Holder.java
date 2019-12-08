package com.example.tut.ui.mensajeria;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tut.R;

public class Holder extends RecyclerView.ViewHolder {
    TextView user;
    TextView msn;
    TextView hora;
    public Holder(View itemView) {
        super(itemView);
        user = (TextView) itemView.findViewById(R.id.title);
        msn = (TextView) itemView.findViewById(R.id.msn);
        hora = (TextView) itemView.findViewById(R.id.Hora);
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }
    public TextView getUser() {
        return user;
    }

    public void setUser(TextView hora) {
        this.user = user;
    }
    public TextView getMes() {
        return msn;
    }

    public void setMes(TextView hora) {
        this.msn = msn;
    }
}
