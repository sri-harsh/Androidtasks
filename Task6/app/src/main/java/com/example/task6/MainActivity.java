package com.example.task6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_name,et_mobile,et_mail,et_pass;
    RadioButton r_male,r_female,r_cse,r_ece,r_eee,r_mech;
    String gender,branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=findViewById(R.id.name);
        et_mobile=findViewById(R.id.mobile);
        et_mail=findViewById(R.id.email);
        et_pass=findViewById(R.id.password);
        r_male=findViewById(R.id.male);
        r_female=findViewById(R.id.female);
        r_cse=findViewById(R.id.CSE);
        r_ece=findViewById(R.id.ECE);
        r_eee=findViewById(R.id.EEE);
        r_mech=findViewById(R.id.MECH);

    }
    public void submit(View view){
        String name=et_name.getText().toString();
        String mobile=et_mobile.getText().toString();
        String email=et_mail.getText().toString();
        String pass=et_pass.getText().toString();
        if(r_male.isChecked()){
            gender=r_male.getText().toString();
        }
        if(r_female.isChecked()){
            gender=r_female.getText().toString();
        }
        if(r_cse.isChecked()){
            branch=r_cse.getText().toString();
        }
        if(r_ece.isChecked()){
            branch=r_ece.getText().toString();
        }
        if(r_eee.isChecked()){
            branch=r_eee.getText().toString();
        }
        if(r_mech.isChecked()){
            branch=r_mech.getText().toString();
        }
        setContentView(R.layout.details_activity);
        TextView tv;
        tv=findViewById(R.id.result);
        tv.setText(name+"\n"+mobile+"\n"+email+"\n"+pass+"\n"
                +gender+"\n"+branch);
    }
}