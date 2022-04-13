package com.youssef.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    FirebaseAuth fAuth;
    EditText etName,etMail,etPassword,etPassword1;
    Button bRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth=FirebaseAuth.getInstance();//
        etName=findViewById(R.id.etName);
        etMail=findViewById(R.id.etMail);
        etPassword=findViewById(R.id.etPassword);
        etPassword1=findViewById(R.id.etPassword1);
        bRegister=findViewById(R.id.bRegister);


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register();
            }
        });

    }

    private void register()
    {
        String user=etMail.getText().toString().trim();//trim pour eviter l'espace du deput et de fin
        String pass=etPassword.getText().toString().trim();
        String conPass=etPassword1.getText().toString().trim();
        if(user.isEmpty()) {
            etMail.setError("email can not be empty");
            return;
        }
        if(pass.isEmpty() || conPass.isEmpty()) {
            etPassword.setError("password can not be empty");
            etPassword1.setError("Confirm password can not be empty");
        return;
        }
        else if(pass.equals(conPass)==false)
        {
            Toast.makeText(Register.this,"pass not compatible with conf pass",Toast.LENGTH_SHORT).show();
            return;
        }

        fAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Toast.makeText(Register.this, " login successful", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(Register.this, "erreur register ", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }
}