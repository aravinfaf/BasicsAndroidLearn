package com.e.learn.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDAO {

    @Insert
    void insert(Word word);

    @Query("Select * from table_word ORDER BY word ASC")
    LiveData<List<Word>> getAlldata();

    @Delete
    void delete(Word word);

    @Query("Delete from table_word")
    void deleteAll();
}
