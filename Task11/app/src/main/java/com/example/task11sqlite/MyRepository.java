package com.example.task11sqlite;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRepository {

    PersonDatabase personDatabase;
    LiveData<List<Person>> readData;

    public MyRepository(Application application) {

        personDatabase=PersonDatabase.getDatabase(application);
        readData=personDatabase.myDao().readData();


    }

    public void insert(Person person){

        new InsertTask().execute(person);
    }

    public LiveData<List<Person>> readALLData(){
        return readData;
    }

    public void update(Person person){

        new UpdateTask().execute(person);
    }

    public void delete(Person person){

        new DeleteTask().execute(person);
    }

    class InsertTask extends AsyncTask<Person,Void,Void>{

        @Override
        protected Void doInBackground(Person... people) {
            personDatabase.myDao().insert(people[0]);
            return null;
        }
    }

    class UpdateTask extends AsyncTask<Person,Void,Void>{

        @Override
        protected Void doInBackground(Person... people) {
            personDatabase.myDao().update(people[0]);
            return null;
        }
    }

    class DeleteTask extends AsyncTask<Person,Void,Void>{


        @Override
        protected Void doInBackground(Person... people) {
            personDatabase.myDao().delete(people[0]);
            return null;
        }
    }
}
