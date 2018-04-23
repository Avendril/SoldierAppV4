package com.example.nexer.soldierappv4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
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

public class MainActivity extends Activity {

    private Button registerButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;

    private FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
           finish();
           startActivity(new Intent(getApplicationContext(), NavActivity.class));
        }
        registerButton = (Button)findViewById(R.id.registerButton);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        textViewSignIn = (TextView)findViewById(R.id.textViewSignIn);
//
//        registerButton.setOnClickListener((View.OnClickListener) this);
//        textViewSignIn.setOnClickListener((View.OnClickListener) this);
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter your email!",Toast.LENGTH_SHORT).show();
            //stopping the function
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter your password!",Toast.LENGTH_SHORT).show();
            //stopping the function
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else{

                }
            }
        });
    }

    public void onClick(View view){
        if(view == registerButton){
            registerUser();
        }else{
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
