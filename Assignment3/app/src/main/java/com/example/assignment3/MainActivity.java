package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.username);
        et2=findViewById(R.id.password);
    }
    public void Login(View view) {
        String data=et1.getText().toString();
            Intent I = new Intent(this, MainActivity2.class);
            I.putExtra("mydata", data);
            startActivity(I);
        }
    public void Register(View view) {
        String data = et1.getText().toString();
        Intent I = new Intent(this, MainActivity3.class);
        I.putExtra("mydata", data);
        startActivity(I);


    }
}