package com.e.learn.multiviewrecycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.learn.R;
import com.e.learn.multiviewrecycler.model.Movie;
import com.e.learn.multiviewrecycler.viewholder.CoverViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HorizontalCoverList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public HorizontalCoverList(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cover_item, parent, false);
        return new CoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CoverViewHolder) {

            Glide.with(context).load(movies.get(position).getMoviePictureURL()).into(((CoverViewHolder) holder).item_cover_picture);



            ((CoverViewHolder) holder).cover_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, ""+movies.get(position).getMovieSubtitle(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


}
