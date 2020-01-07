package com.e.learn.viewtype;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.learn.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    //Header header;
    List<ListItem> list;

    public MyRecyclerAdapter(List<ListItem> headerItems) {
        this.list = headerItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEADER) {
            View v = inflater.inflate(R.layout.header, parent, false);
            return new VHHeader(v);
        } else {
            View v = inflater.inflate(R.layout.list, parent, false);
            return new VHItem(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHHeader) {
            // VHHeader VHheader = (VHHeader)holder;
            Header currentItem = (Header) list.get(position);
            VHHeader VHheader = (VHHeader) holder;
            VHheader.txtTitle.setText(currentItem.getHeader());
        } else if (holder instanceof VHItem){
            ContentItem currentItem = (ContentItem) list.get(position);
        VHItem VHitem = (VHItem) holder;
        VHitem.txtName.setText(currentItem.getName());
    }
}

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return list.get(position) instanceof Header;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

class VHHeader extends RecyclerView.ViewHolder{
    TextView txtTitle;
    public VHHeader(View itemView) {
        super(itemView);
        this.txtTitle = (TextView) itemView.findViewById(R.id.txtHeader);
    }
}
class VHItem extends RecyclerView.ViewHolder{
    TextView txtName;
    public VHItem(View itemView) {
        super(itemView);
        this.txtName = (TextView) itemView.findViewById(R.id.txtName);
    }
}
}

class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<List<ListItem>> arraylist;
    MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MyRecyclerAdapter(getList());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
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