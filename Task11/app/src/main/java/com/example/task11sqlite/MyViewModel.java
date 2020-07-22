package com.example.task11sqlite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    MyRepository repository;

    LiveData<List<Person>> getallData;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository=new MyRepository(application);
        getallData=repository.readALLData();

    }

    public void insert(Person person){
        repository.insert(person);
    }

    public void update(Person person){

        repository.update(person);
    }

    public void delete(Person person){

        repository.delete(person);
    }

    public LiveData<List<Person>> readData(){

        return getallData;
    }
}
