package com.example.tut.ui.horario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HorarioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HorarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Horario");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
