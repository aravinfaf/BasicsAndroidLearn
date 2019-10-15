package com.e.learn.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private LiveData<List<Word>> getAll;


    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository=new WordRepository(application);
        getAll=wordRepository.getAllword();
    }

    LiveData<List<Word>> getAll(){
        return getAll;
    }

    public void insert(Word word){
        wordRepository.insert(word);
    }

}
