package com.example.ecomarce_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

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
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText tEmail,tPassword, tname ,tcity , tage ;
    TextView tText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button regBtn = findViewById(R.id.regBtn);
        tText = findViewById(R.id.text);
        tEmail = findViewById(R.id.tEmail);
        tPassword = findViewById(R.id.tPassword);
        tname = findViewById(R.id.tname);
        tcity = findViewById(R.id.tcity);
        tage = findViewById(R.id.tage);

//        if you have already register
        tText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignIn = new Intent(getApplicationContext(),SignIn1.class);
                startActivity(goToSignIn);
                Toast.makeText(MainActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
            }
        });
//  Athenticaition process

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valid_register();
            }
        });
    }
//  validation function

    public void valid_register(){

        String email = tEmail.getText().toString().trim();
        String password = tPassword.getText().toString().trim();
        String name = tname.getText().toString().trim();
        String city = tcity.getText().toString().trim();
        String Uage = tage.getText().toString().trim();
        int age = Integer.parseInt(Uage);

        if(email.isEmpty()){
            tEmail.setError("please enter email");
            tEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            tPassword.setError("please enter password");
            tPassword.requestFocus();
            return;
        }

        ProgressBar progress = findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
//        fireBase instance creation
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
               myUser addUser = new myUser(name,city,age);
                  FirebaseDatabase.getInstance().getReference("myUser").child(mAuth.getCurrentUser().getUid()).setValue(addUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(MainActivity.this, "Data is successfully add in dataBase", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(MainActivity.this, "something want wrong in database", Toast.LENGTH_SHORT).show();
                            }
                      }
                  });

              }else{
                  Toast.makeText(MainActivity.this, "registration failed !!", Toast.LENGTH_SHORT).show();
              }progress.setVisibility(View.GONE);
            }
        });
    }



}