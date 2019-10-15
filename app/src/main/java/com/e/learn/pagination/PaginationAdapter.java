package com.e.learn.pagination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.learn.R;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.ViewHolder> {

    public static final int ITEM=0;
    public static final int LOADING=1;
    private boolean isLoadingAdded=false;

    List<Movie> movies;
    Context context;

    public PaginationAdapter( Context context) {
        this.context=context;
       movies=new ArrayList<>();
    }

    public List<Movie> getMovies(){
        return movies;
    }

    public void setMovies(List<Movie> movies){
        this.movies=movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder=null;
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());

        switch (viewType){

            case  ITEM:
                viewHolder=getViewHolder(parent,layoutInflater);
                break;

            case LOADING:
                View view=layoutInflater.inflate(R.layout.item_progress,parent,false);
                viewHolder=new LoadingVH(view);
                break;
        }

        return (ViewHolder) viewHolder;
    }

    private RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup,LayoutInflater layoutInflater){
        RecyclerView.ViewHolder viewHolder;
        View view=layoutInflater.inflate(R.layout.item_list,viewGroup,false);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie=movies.get(position);

        switch (getItemViewType(position)){
            case ITEM:
            ViewHolder movieVH =  holder;

            movieVH.textView.setText(movie.getTitle());
            break;

            case LOADING:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return movies==null?0:movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);

        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder{

        public LoadingVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void addMovie(Movie movie){
        movies.add(movie);
        notifyItemInserted(movies.size()-1);
    }

    public void addAll(List<Movie> mc){
        for(Movie movie : mc){
            addMovie(movie);
        }
    }

    public void remove(Movie movie){
        int position=movies.indexOf(movie);
        if(position>-1) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public  Movie getItem(int position){
        return movies.get(position);
    }

    public void clear(){
        isLoadingAdded=false;

        while (getItemCount()>0){
            remove(getItem(0));
        }
    }

    public boolean isEmpty(){
        return getItemCount()==0;
    }

    public void addloadingFooter(){
        isLoadingAdded=true;
        addMovie(new Movie());
    }
    public void removingFooter(){
        isLoadingAdded=false;
        int position=movies.size()-1;
        Movie item=getItem(position);

        if(item!=null){
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }
}
