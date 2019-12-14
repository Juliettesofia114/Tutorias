package com.example.tut;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class insertData extends AppCompatActivity {

    private EditText name, id, email, pass;
    private ProgressDialog progressDialog;

    //Objeto de Firebase
    private FirebaseAuth mAuth;
    private FirebaseFirestore upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        //Instancia del Objeto Firebase
        mAuth = FirebaseAuth.getInstance();
        upload = FirebaseFirestore.getInstance();

        //Views Referenciados
        name = (EditText) findViewById(R.id.nombreCampoReg);
        id = (EditText) findViewById(R.id.idCampoReg);
        email = (EditText) findViewById(R.id.correoCampoReg);
        pass = (EditText) findViewById(R.id.passCampoReg);

        progressDialog = new ProgressDialog(this);
        progressDialog.dismiss();
    }

    public void goRegister(View view) {
        registarUsuario();
    }

    private void registarUsuario() {
        //Obtenemos el email y la contraseña
        final String Nm = name.getText().toString().trim();
        final String ID = id.getText().toString().trim();
        long cred = Long.parseLong(ID);
        String Em = email.getText().toString().trim();
        String Ps = pass.getText().toString().trim();

        if (Nm.isEmpty()) {
            name.setError("Campo obligatorio");
            name.requestFocus();
            return;
        }
        if (ID.isEmpty()) {
            id.setError("Campo obligatorio");
            id.requestFocus();
            return;
        }

        if (Em.isEmpty()) {
            email.setError("Campo obligatorio");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Em).matches()) {
            email.setError("Ingrese un correo válido");
            email.requestFocus();
            return;
        }

        if (Ps.isEmpty()) {
            pass.setError("Campo obligatorio");
            pass.requestFocus();
            return;
        }

        if (Ps.length() <= 5) {
            pass.setError("La contraseña debe ser mínimo 6 carácteres");
            pass.requestFocus();
            return;
        }
        progressDialog.setMessage("Realizando registro");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(Em, Ps).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder().setDisplayName(Nm).build();
                    user.updateProfile(changeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                Map<String, Object> map = new HashMap<>();
                                map.put("Nombre", Nm);
                                map.put("ID", Long.parseLong(ID));
                                map.put("Usuario", 0);

                                upload.collection("Usuarios").document(user.getUid()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(insertData.this, "El usuario ha sido registrado", Toast.LENGTH_LONG).show();
                                            progressDialog.dismiss();
                                            Intent i = new Intent(insertData.this, MainActivity.class);
                                            startActivity(i);
                                        }
                                        else{
                                            Toast.makeText(insertData.this, "No se pudo registrar su usuario (DB)", Toast.LENGTH_LONG).show();
                                            progressDialog.dismiss();
                                        }
                                    }
                                });

                            }
                            else{
                                Toast.makeText(insertData.this, "No se pudo registrar su usuario (US)", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                    });

                } else {

                    if(internetConnected()){
                        try {
                            throw task.getException();
                        } catch(FirebaseAuthUserCollisionException e) {
                            Toast.makeText(insertData.this,"Este usuario ya está registrado", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(insertData.this,"Ha ocurrido un error al iniciar sesión", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(insertData.this,"No tienes conexión a internet. Conéctate y vuelve a intentarlo", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private boolean internetConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        else
            return false;
    }

}
