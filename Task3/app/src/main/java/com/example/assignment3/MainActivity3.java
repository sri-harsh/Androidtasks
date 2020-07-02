package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    EditText et1;
    EditText et2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        et1=findViewById(R.id.username);
        et2=findViewById(R.id.password);
        b1=findViewById(R.id.Register);

    }
    public void Register(View view) {
        Toast.makeText(this, "Registration Sucessfull", Toast.LENGTH_LONG).show();


    }


}