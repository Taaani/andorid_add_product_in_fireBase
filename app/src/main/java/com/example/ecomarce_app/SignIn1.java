package com.example.ecomarce_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn1 extends AppCompatActivity {
    TextView tText;
    EditText LEmail, Lpassword;
    Button loginButton;
    ProgressBar progress2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in1);

        tText.findViewById(R.id.signupText);
        LEmail = findViewById(R.id.LEmail);
        Lpassword = findViewById(R.id.LPassword);
        progress2=findViewById(R.id.prograss2);
        loginButton = findViewById(R.id.loginBtn);
//
        tText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignUp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goToSignUp);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_login();
            }
        });
    }


//     login actual function

    public void get_login(){
        String email = LEmail.getText().toString().trim();
        String password = Lpassword.getText().toString().trim();
        if(email.isEmpty()){
            LEmail.setError("Enter email");
            LEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            Lpassword.setError("Enter password");
            Lpassword.requestFocus();
        }
        progress2.setVisibility(View.VISIBLE);
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        myAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  Toast.makeText(SignIn1.this, "you are successfully login", Toast.LENGTH_SHORT).show();
//                   Intent goToHome = new Intent(getApplicationContext(),myHomePage.class);
//                   startActivity(goToHome);
              }else{
                  Toast.makeText(SignIn1.this, "please fill correct email and password", Toast.LENGTH_SHORT).show();
              }progress2.setVisibility(View.GONE);
            }
        });
    }
}