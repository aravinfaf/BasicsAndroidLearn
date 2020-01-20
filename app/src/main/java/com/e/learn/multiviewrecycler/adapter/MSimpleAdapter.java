package com.e.learn.multiviewrecycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.learn.R;
import com.e.learn.multiviewrecycler.model.Movie;
import com.e.learn.multiviewrecycler.viewholder.SimpleViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MSimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MSimpleAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setImage(((SimpleViewHolder) holder).item_simple_picture, movies.get(position).getMoviePictureURL());
        setOnClick(((SimpleViewHolder) holder).simple_parent, position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private void setImage(final ImageView imageView, String imageURL) {
        Picasso.get().load(imageURL).fit().centerCrop().into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                imageView.setBackgroundResource(R.drawable.ic_launcher_background);
            }
        });
    }

    private void setOnClick(RelativeLayout button, final int position) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Click: " + position, Toast.LENGTH_LONG).show();
            }
        });
    }
}
