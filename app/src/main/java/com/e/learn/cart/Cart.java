package com.e.learn.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.e.learn.R;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    TextView mealTotalText;
    ArrayList<Food> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView storedOrders = (ListView)findViewById(R.id.selected_food_list);

        orders = getListItemData();
        mealTotalText = (TextView)findViewById(R.id.meal_total);
        OrderAdapter adapter = new OrderAdapter(this, orders);

        storedOrders.setAdapter(adapter);
        adapter.registerDataSetObserver(observer);
    }

    public int calculateMealTotal(){
        int mealTotal = 0;
        for(Food order : orders){
            mealTotal += order.getmAmount() * order.getmQuantity();
        }
        return mealTotal;
    }

    DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            setMealTotal();
        }
    };

    private ArrayList<Food> getListItemData(){
        ArrayList<Food> listViewItems = new ArrayList<Food>();
        listViewItems.add(new Food("Rice",30));
        listViewItems.add(new Food("Beans",40));
        listViewItems.add(new Food("Yam",60));
        listViewItems.add(new Food("Pizza",80));
        listViewItems.add(new Food("Fries",100));

        return listViewItems;
    }

    public void setMealTotal(){
        mealTotalText.setText("GH"+"\u20B5"+" "+ calculateMealTotal());
    }
}