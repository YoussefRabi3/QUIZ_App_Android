package com.youssef.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //Step 1: Declaration
    EditText etLogin, etPassword;
    Button bLogin;
    TextView tvRegister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Déclarez une instance de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //Step 2: Recuperation des ids
        etLogin = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.login);
        tvRegister = (TextView) findViewById(R.id.tvRegister);


        //Step 3: Association de listeners
        //en utilise la methode setonclicklistener en lui indiquant une instance de la classe dans la quelle se trouvera une methode qui doit
        // etre declancher en cas de pression sur le button
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
        private void login() {
            String user = etLogin.getText().toString().trim();//trim pour eviter l'espace premier ou dernier
            String pass = etPassword.getText().toString().trim();
            if (user.isEmpty())
            {
                Toast.makeText(MainActivity.this, "login obligatoire ", Toast.LENGTH_SHORT).show();
                etLogin.setError("email can not be empty");
            }
            else if (pass.isEmpty())
            {
                etPassword.setError("password can not be empty");
                Toast.makeText(MainActivity.this, "password obligatoire ", Toast.LENGTH_SHORT).show();
            }
            else
            {

                mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,Quiz1.class));
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Login Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
            //Step 4: Traitement
            tvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, Register.class));
                }
            });

    }

}
