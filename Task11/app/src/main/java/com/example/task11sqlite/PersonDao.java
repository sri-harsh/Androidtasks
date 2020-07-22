package com.example.task11sqlite;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    public void insert(Person person);

    @Query("SELECT * FROM PersonData")
    public LiveData<List<Person>> readData();

    @Delete
    public void delete(Person person);

    @Update
    public void update(Person person);
}
