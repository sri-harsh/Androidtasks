package com.example.codelab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView tv;
    TextView T1,T2;
    EditText et;
    public static final int TEXT_REQUEST = 1;
    public static final String EXTRA_REPLY =
            "com.example.android.codelab2.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv=findViewById(R.id.textview);
        T1=findViewById(R.id.text_header);
        T2=findViewById(R.id.message_reply);
        String S=getIntent().getStringExtra("mydata");
        tv.setText(S);
    }

    public void Reply(View view) {
        et=findViewById(R.id.Urreply);
        String reply = et.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        finish();



    }


}