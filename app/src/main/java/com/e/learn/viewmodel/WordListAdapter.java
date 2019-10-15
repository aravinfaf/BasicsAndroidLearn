package com.e.learn.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e.learn.R;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    LayoutInflater layoutInflater;
    List<Word> data;

    WordListAdapter(Context context){
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent);

        return new ViewHolder(view);
    }

    void setWords(List<Word> words){
        this.data=words;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(data!=null){
            Word word=data.get(position);
            holder.wordItemView.setText(word.getmWord());
        }else{
            holder.wordItemView.setText("Nothing..");
        }

    }

    @Override
    public int getItemCount() {
        if (data!=null)
            return data.size();
        else
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView wordItemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
