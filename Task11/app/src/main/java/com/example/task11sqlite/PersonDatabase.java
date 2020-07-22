package com.example.task11sqlite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class},version = 1,exportSchema = false)
public abstract class PersonDatabase extends RoomDatabase {

        public abstract PersonDao myDao();

        public static PersonDatabase database;

        public static synchronized PersonDatabase getDatabase(Context context){

                if(database==null){

                        database = Room.databaseBuilder(context,PersonDatabase.class,"MYDB")
                                .allowMainThreadQueries().fallbackToDestructiveMigration().build();

                }

                return database;
        }

}
