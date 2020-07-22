package com.example.task11sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder> {

    Context ct;
    List<Person> list;

    String department;
    String finalGender;

    public MyDataAdapter(MainActivity mainActivity, List<Person> people) {

        ct=mainActivity;
        list=people;
    }

    @NonNull
    @Override
    public MyDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ct).inflate(R.layout.row_design,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDataAdapter.MyViewHolder holder, int position) {

        final Person person=list.get(position);
        holder.rname.setText(person.getName());
        holder.rmail.setText(person.getMailID());
        holder.rphone.setText(person.getPhoneNumber());
        holder.raddress.setText(person.getAddress());
        holder.rdepart.setText(person.getDepartment());
        holder.rgender.setText(person.getGender());
        holder.rlanguages.setText(person.getLanguages());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.database.myDao().delete(person);
                MainActivity.viewModel.delete(person);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rname,rmail,rphone,raddress,rdepart,rgender,rlanguages;
        ImageView delete,edit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rname=(TextView) itemView.findViewById(R.id.readName);
            rmail=(TextView) itemView.findViewById(R.id.readmailid);
            rphone=(TextView) itemView.findViewById(R.id.readmobile);
            raddress=(TextView) itemView.findViewById(R.id.readaddress);
            rdepart=(TextView) itemView.findViewById(R.id.readdepartment);
            rgender=(TextView) itemView.findViewById(R.id.readgender);
            rlanguages=(TextView) itemView.findViewById(R.id.readlanguages);


            delete=(ImageView) itemView.findViewById(R.id.delete);
            edit =(ImageView) itemView.findViewById(R.id.edit);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name=rname.getText().toString();
                    String mail=rmail.getText().toString();
                    String phone=rphone.getText().toString();
                    String address=raddress.getText().toString();
                    String depart=rdepart.getText().toString();
                    String gender=rgender.getText().toString();
                    String languages=rlanguages.getText().toString();

                    ViewGroup viewGroup=view.findViewById(android.R.id.content);

                    View v=LayoutInflater.from(ct).inflate(R.layout.updatedata,viewGroup,false);

                    final EditText uname=v.findViewById(R.id.updatename);
                    final EditText uemail=v.findViewById(R.id.updatemailid);
                    final EditText uphone=v.findViewById(R.id.updatemobilenumber);
                    final EditText uaddress=v.findViewById(R.id.updateaddress);
                    final Spinner sp_department=v.findViewById(R.id.updatedepartment);

                    String departments[] ={"Choose your department","CSE","ECE","Mechanical","EEE","Civil"};

                    ArrayAdapter<String> branchAdapter= new ArrayAdapter<String>(ct,android.R.layout.simple_spinner_item,departments);
                    branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_department.setAdapter(branchAdapter);

                    if(depart!=null){
                        int spinnerPosition=branchAdapter.getPosition(depart);
                        sp_department.setSelection(spinnerPosition);
                    }

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

                    RadioButton r_male=v.findViewById(R.id.updatemale);
                    RadioButton r_female=v.findViewById(R.id.updatefemale);

                    RadioGroup radio=v.findViewById(R.id.RadioGroup);

                    String str_male=r_male.getText().toString();

                    if(gender.equals(str_male)){
                        radio.check(R.id.updatemale);
                    }

                    else{
                        radio.check(R.id.updatefemale);
                    }

                    if(r_male.isChecked()){
                        finalGender="Male";
                    }

                    else{
                        finalGender="Female";
                    }


                    Button update=v.findViewById(R.id.updatedata);
                    Button cancel=v.findViewById(R.id.canceldata);

                    final BottomSheetDialog dialog=new BottomSheetDialog(ct);
                    dialog.setContentView(v);
                    dialog.setCancelable(false);

                    uname.setText(name);
                    uemail.setText(mail);
                    uphone.setText(phone);
                    uaddress.setText(address);

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Person person=new Person();
                            person.setName(uname.getText().toString());
                            person.setMailID(uemail.getText().toString());
                            person.setPhoneNumber(uphone.getText().toString());
                            person.setAddress(uaddress.getText().toString());
                            person.setDepartment(department);
                            person.setGender(finalGender);

                            MainActivity.viewModel.update(person);
                            Toast.makeText(ct, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });
        }
    }
}
