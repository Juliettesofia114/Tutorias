package com.example.tut.ui.horario;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tut.R;
import com.example.tut.Recordatorios;


public class horariofragment extends Fragment {
    private com.example.tut.ui.horario.HorarioViewModel HorarioViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HorarioViewModel =
                ViewModelProviders.of(this).get(HorarioViewModel.class);
        View root = inflater.inflate(R.layout.horario, container, false);
        final TextView textView = root.findViewById(R.id.horario);
        HorarioViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }
    public void boton(){
        startActivity(new Intent(getActivity(), Recordatorios.class));
    }

}