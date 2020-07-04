package com.example.codelab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView T1,T2;
    public static final int TEXT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.EnterMessage);
        T1=findViewById(R.id.text_header);
        T2=findViewById(R.id.message_reply);
    }
        public void onActivityResult(int requestCode,
        int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == TEXT_REQUEST) {
                if (resultCode == RESULT_OK) {
                    String reply =
                            data.getStringExtra(MainActivity2.EXTRA_REPLY);
                    T1.setVisibility(View.VISIBLE);
                    T2.setText(reply);
                    T2.setVisibility(View.VISIBLE);
                }
            }
        }


    public void EnterMessage(View view) {
        String data=et.getText().toString();
        if (data.isEmpty()){
            Toast.makeText(this, "Please Enter Message", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent I = new Intent(this, MainActivity2.class);
            I.putExtra("mydata", data);
            startActivityForResult(I, TEXT_REQUEST);
        }
    }
}