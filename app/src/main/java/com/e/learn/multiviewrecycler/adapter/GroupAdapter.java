package com.e.learn.multiviewrecycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.learn.R;
import com.e.learn.multiviewrecycler.model.Group;
import com.e.learn.multiviewrecycler.model.Movie;
import com.e.learn.multiviewrecycler.viewholder.GroupViewHolder;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Group> groups;
    private ArrayList<Movie> cover;
    private ArrayList<Movie> simple;
    private ArrayList<Movie> vertical;
    Context context;

    public GroupAdapter( Context context,ArrayList<Group> groups, ArrayList<Movie> cover, ArrayList<Movie> simple, ArrayList<Movie> vertical) {
        this.groups = groups;
        this.cover = cover;
        this.simple = simple;
        this.vertical = vertical;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof GroupViewHolder) {

            setGroupTitle(((GroupViewHolder) holder).group_title, groups.get(position).getGroupTitle());
            setGroupButtonTitle(((GroupViewHolder) holder).view_all, groups.get(position).getGroupButtonTitle());
            setOnClickViewAll(((GroupViewHolder) holder).head_parent, groups.get(position).getGroupTitle());
            setLists(((GroupViewHolder) holder).group_recycler_view, position);

            ((GroupViewHolder) holder).view_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, "View All "+groups.get(position).getGroupTitle(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void setLists(RecyclerView group_recycler_view, int position) {

        switch (position){

            case 0:
                setHorizontalcoverList(group_recycler_view);
                break;
            case 1:
                setHorizontalSimpleList(group_recycler_view);
                break;
            case 2:
                setVerticalList(group_recycler_view);
                break;

        }

    }

    private void setVerticalList(RecyclerView recyclerView) {

        MSimpleAdapter simpleAdapter = new MSimpleAdapter(context, simple);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(simpleAdapter);
        recyclerView.setNestedScrollingEnabled(true);

    }

    private void setHorizontalSimpleList(RecyclerView recyclerView) {

        MSimpleAdapter simpleAdapter = new MSimpleAdapter(context, simple);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(simpleAdapter);
        recyclerView.setNestedScrollingEnabled(true);

    }

    private void setHorizontalcoverList(RecyclerView recyclerView) {

        HorizontalCoverList horizontalCoverList=new HorizontalCoverList(context,cover);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(horizontalCoverList);
        recyclerView.setNestedScrollingEnabled(true);
        
    }

    private void setOnClickViewAll(RelativeLayout head_parent, String groupTitle) {

        head_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+groupTitle, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setGroupButtonTitle(Button view_all, String groupButtonTitle) {
        view_all.setText(groupButtonTitle);}
    private void setGroupTitle(TextView group_title, String groupTitle) {

        group_title.setText(groupTitle);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }
}
