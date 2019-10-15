package com.e.learn.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class WordRepository {

    private WordDAO wordDAO;
    public LiveData<List<Word>> getAllword;
    private static WordRepository instance;

    public WordRepository(Application application){
        WordRoomDatabase db=WordRoomDatabase.getDatabase(application);
        wordDAO=db.worddao();
        getAllword=wordDAO.getAlldata();
    }

    public LiveData<List<Word>> getAllword(){
        return getAllword;
    }

    public void insert(Word word){
        new InsetAsync(wordDAO).execute(word);
    }

    public static class InsetAsync extends AsyncTask<Word,Void,Void>{

        private WordDAO asynctaskDAO;

        InsetAsync(WordDAO wordDAO){
            asynctaskDAO=wordDAO;
        }

        @Override
        protected Void doInBackground(Word... words) {
            asynctaskDAO.insert(words[0]);
            return null;
        }
    }
}
