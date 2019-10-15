package com.e.learn.viewmodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_word")
public class Word {

    @PrimaryKey(autoGenerate = true)
    int id;

    @NonNull
    @ColumnInfo(name = "word")
    String mWord;

    public Word(String mWord) {
        this.mWord = mWord;
    }

    public String getmWord() {
        return mWord;
    }
}

