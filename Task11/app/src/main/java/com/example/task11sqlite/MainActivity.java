package com.example.task11sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static PersonDatabase database;

    RecyclerView rv;

    FloatingActionButton fab;

    static MyViewModel viewModel;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView) findViewById(R.id.textView);

        rv=(RecyclerView) findViewById(R.id.recycler);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InsertActivity.class));
            }
        });

//        database = Room.databaseBuilder(this,PersonDatabase.class,"MYDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();

//        List<Person> personList = database.myDao().readData();

        viewModel=new ViewModelProvider(this).get(MyViewModel.class);

        viewModel.readData().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {

                if(people.size()==0){
                    tv.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                }
                else {
                    tv.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);
                    rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rv.setAdapter(new MyDataAdapter(MainActivity.this, people));

                }
            }
        });


    }
}