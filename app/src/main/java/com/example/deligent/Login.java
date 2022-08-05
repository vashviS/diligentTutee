package com.example.deligent;

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
import @org.jetbrains.annotations.NotNull;


public class Login extends AppCompatActivity {
    EditText e1,e2;
    TextView t1, t2, t3;
    Button b1,b2;
    ProgressBar p1;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        t1=(TextView)findViewById(R.id.textView3);
        t2=(TextView)findViewById(R.id.textView4);
        t3=(TextView)findViewById(R.id.textView5);
        b1=(Button)findViewById(R.id.button3);
        b2=(Button)findViewById(R.id.button4);
        p1=(ProgressBar)findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Login.this,Signup.class);
                startActivity(k);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                if(s1.isEmpty())
                {
                    e1.setError("Plzz enter your gmail");
                    return;
                }
                else
                {
                    if(s2.isEmpty())
                    {
                        e2.setError("Enter password");
                        return;
                    }
                }
                p1.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            p1.setVisibility(View.INVISIBLE);
                            Toast.makeText(Login.this, "Login done", Toast.LENGTH_SHORT).show();
                            Intent l=new Intent(Login.this,Student.class);
                            startActivity(l);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                        }

                    }
                });


            }
        });


    }
}