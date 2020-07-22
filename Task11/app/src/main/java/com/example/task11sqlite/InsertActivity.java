package com.example.task11sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    EditText et_name,et_email,et_phone,et_address;
    RadioButton r_male,r_female;
    Spinner sp_department;
    String gender;
    String department;
    CheckBox english,hindi,nepali,telugu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        sp_department=(Spinner) findViewById(R.id.department);
        et_address=(EditText) findViewById(R.id.address);
        et_name=(EditText) findViewById(R.id.name);
        et_email=(EditText) findViewById(R.id.mailid);
        et_phone=(EditText) findViewById(R.id.phonenumber);
        r_male=(RadioButton) findViewById(R.id.male);
        r_female=(RadioButton) findViewById(R.id.female);
        english=(CheckBox) findViewById(R.id.english);
        hindi=(CheckBox) findViewById(R.id.hindi);
        nepali=(CheckBox) findViewById(R.id.nepali);
        telugu=(CheckBox) findViewById(R.id.telugu);

        ArrayAdapter<CharSequence> branchAdapter= ArrayAdapter.createFromResource(this,R.array.department,android.R.layout.simple_spinner_item);
        sp_department.setAdapter(branchAdapter);

        sp_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    department=sp_department.getItemAtPosition(position).toString();
                }
                else{
                    department="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void save(View view) {

        String name=et_name.getText().toString();
        String mailid=et_email.getText().toString();
        String mobile=et_phone.getText().toString();
        String address=et_address.getText().toString();

        if(r_male.isChecked()){
            gender=r_male.getText().toString();
        }
        else {
            gender=r_female.getText().toString();
        }

        StringBuilder builder=new StringBuilder();

        if(english.isChecked()){

            builder.append(english.getText().toString());
        }

        if(hindi.isChecked()){

            builder.append(hindi.getText().toString());
        }

        if(nepali.isChecked()){

            builder.append(nepali.getText().toString());
        }

        if(telugu.isChecked()){

            builder.append(telugu.getText().toString());
        }

        Person person=new Person();

        person.setName(name);
        person.setMailID(mailid);
        person.setPhoneNumber(mobile);
        person.setAddress(address);
        person.setGender(gender);
        person.setDepartment(department);
        person.setLanguages(builder.toString());

        //MainActivity.database.myDao().insert(person);
        MainActivity.viewModel.insert(person);

        Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
        finish();

    }
}