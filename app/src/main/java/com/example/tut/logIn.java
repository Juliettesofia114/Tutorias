package com.example.tut;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class logIn extends AppCompatActivity {

    private TextView forgot;
    private EditText email, pass;
    private ProgressDialog progressDialog;
    //Objeto de Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //Instancia del Objeto Firebase
        mAuth = FirebaseAuth.getInstance();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){
            startActivity(new Intent(logIn.this, MainActivity.class));
            finish();
        }

        //Views Referenciados
        email = (EditText) findViewById(R.id.emailCampo);
        pass = (EditText) findViewById(R.id.passCampo);
        forgot = (TextView) findViewById(R.id.forgotLink);

        progressDialog = new ProgressDialog(this);
        progressDialog.dismiss();

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(logIn.this, com.example.tut.ui.Cuenta.password.class));
            }
        });

    }

    //Función que toma el botón de Registro
    public void buttonRegistrar(View view){
        registrarUsuario();
    }

    public void buttonLogIn(View view){
        iniciarUsuario();
    }

    private void registrarUsuario(){
        Intent i = new Intent(logIn.this, insertData.class);
        startActivity(i);
    }

    private void iniciarUsuario(){
        //Obtenemos el email y la contraseña
        final String Em = email.getText().toString().trim();
        String Ps = pass.getText().toString().trim();

        if (Em.isEmpty()) {
            email.setError("Campo obligatorio");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Em).matches()){
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

        progressDialog.setMessage("Iniciando sesión");
        progressDialog.show();

        //Función de Firebase para Autenticar
        mAuth.signInWithEmailAndPassword(Em, Ps)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(logIn.this,"¡Bienvenido a TutoriaUN!", Toast.LENGTH_LONG).show();
                            //Pasar a la siguiente Actividad
                            Intent i = new Intent(logIn.this, tutorusTutoria.class);
                            progressDialog.dismiss();
                            startActivity(i);
                            finish();
                        } else {
                            if(internetConnected()){
                                try {
                                    throw task.getException();
                                } catch(FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(logIn.this,"Credenciales incorrectas", Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Toast.makeText(logIn.this,"Ha ocurrido un error al iniciar sesión", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(logIn.this,"No tienes conexión a internet. Conéctate y vuelve a intentarlo", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }

                    }
                });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            this.finishAffinity();
            return true;
        }

        return super.onKeyDown(keyCode, event);
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
