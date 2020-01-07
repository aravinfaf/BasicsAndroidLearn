package com.e.learn.viewtype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.learn.R;

import java.util.ArrayList;
import java.util.List;

public class ViewTypeActivity extends AppCompatActivity {

    RecyclerView my_recycler_view;
    List<List<ListItem>> arraylist;
    MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_type);

        my_recycler_view=findViewById(R.id.my_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MyRecyclerAdapter(getList());
        my_recycler_view.setLayoutManager(linearLayoutManager);
        my_recycler_view.setAdapter(adapter);
    }

    private ArrayList<ListItem> getList() {
        ArrayList<ListItem> arrayList = new ArrayList<>();
        for(int j = 0; j <= 4; j++) {
            Header header = new Header();
            header.setHeader("header"+j);
            arrayList.add(header);
            for (int i = 0; i <= 3; i++) {
                ContentItem item = new ContentItem();
                item.setRollnumber(i + "");
                item.setName("A" + i);
                arrayList.add(item);
            }
        }
        return arrayList;
    }
}
