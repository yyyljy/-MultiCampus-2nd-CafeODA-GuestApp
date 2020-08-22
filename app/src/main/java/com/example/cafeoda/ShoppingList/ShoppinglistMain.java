package com.example.cafeoda.ShoppingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cafeoda.R;

import java.util.ArrayList;
import java.util.List;


public class ShoppinglistMain extends AppCompatActivity {

    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myshopping_main);
        list = findViewById(R.id.rlist);
        MyShoppingItem item;
        List<MyShoppingItem> recycler_shopping_data = new ArrayList<MyShoppingItem>();

        item = new MyShoppingItem(R.drawable.gong, "아메리카노","ice",1);
        recycler_shopping_data.add(item);
        item = new MyShoppingItem(R.drawable.jang,"카페라떼","ice",2);
        recycler_shopping_data.add(item);
        item= new MyShoppingItem(R.drawable.jung,"헤이즐넛라떼","ice",3);
        recycler_shopping_data.add(item);
        item = new MyShoppingItem(R.drawable.lee,"아메리카노","hot",1);
        recycler_shopping_data.add(item);
        item = new MyShoppingItem(R.drawable.so,"아메리카노","ice",1);
        recycler_shopping_data.add(item);
        item = new MyShoppingItem(R.drawable.gong, "헤이즐넛라떼","hot",1);
        recycler_shopping_data.add(item);
        item = new MyShoppingItem(R.drawable.jang,"카페라떼","ice",2);
        recycler_shopping_data.add(item);
        item= new MyShoppingItem(R.drawable.jung,"헤이즐넛라떼","ice",3);
        recycler_shopping_data.add(item);
        item = new MyShoppingItem(R.drawable.lee,"카페라떼","hot",1);
        recycler_shopping_data.add(item);
        item = new MyShoppingItem(R.drawable.so,"헤이즐넛라떼","ice",1);
        recycler_shopping_data.add(item);

        ShoppinglistAdapter myadapter = new ShoppinglistAdapter(this,
                R.layout.myshopping_item,
                recycler_shopping_data);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        list.setLayoutManager(manager); //리사이클러뷰에 VERTICAL LinearLayout이 세팅
        list.setAdapter(myadapter);

    }
}
