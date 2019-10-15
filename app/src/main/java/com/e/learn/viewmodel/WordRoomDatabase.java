package com.e.learn.viewmodel;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class},version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    public static volatile WordRoomDatabase INSTANCE;

    public abstract WordDAO worddao();

    static WordRoomDatabase getDatabase(final Context context){

        if(INSTANCE == null){
          synchronized (WordRoomDatabase.class){
              if(INSTANCE == null){
                  INSTANCE= Room.databaseBuilder(context.getApplicationContext(),WordRoomDatabase.class,
                          "word_database")
                          .build();
              }
          }
        }

    return INSTANCE;
    }


    public static RoomDatabase.Callback callback=new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateAsync(INSTANCE).execute();
        }
    };

    public static class PopulateAsync extends AsyncTask<Void,Void,Void>{

        WordDAO wordDAO;
        PopulateAsync(WordRoomDatabase wordDAO1){
          wordDAO=wordDAO1.worddao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            wordDAO.deleteAll();

            Word word=new Word("hello");
            wordDAO.insert(word);

            word=new Word("hello");
            wordDAO.insert(word);
            return null;

        }
    }

}

