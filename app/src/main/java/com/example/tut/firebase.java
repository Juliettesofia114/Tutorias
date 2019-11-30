package com.example.tut;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class firebase implements ValueEventListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    public DatabaseReference getMyRef() {
        myRef.setValue("Hello world");
        return myRef;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String value = String.valueOf(dataSnapshot.getValue(firebase.class));
        Log.d(TAG,"Value is: "+value);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.w(TAG, "Failed to read value.", databaseError.toException());

    }
}
