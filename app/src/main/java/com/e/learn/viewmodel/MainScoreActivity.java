package com.e.learn.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.learn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainScoreActivity extends AppCompatActivity {

    WordViewModel viewModel;
    RecyclerView recyclerview;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview=findViewById(R.id.recyclerview);
        final WordListAdapter adapter=new WordListAdapter(this);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        viewModel=ViewModelProviders.of(this).get(WordViewModel.class);

        viewModel.getAll().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWords(words);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScoreActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==RESULT_OK && requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE){

            Word word=new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            viewModel.insert(word);
        }else{
            Toast.makeText(
                    getApplicationContext(),
                    "empty_not_saved",
                    Toast.LENGTH_LONG).show();
        }
    }
}
