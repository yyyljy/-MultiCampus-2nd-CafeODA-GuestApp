package com.example.cafeoda.BuyHistoryList;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafeoda.R;

public class BuyHistoryMain extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyhistorylist_main);
        listview = findViewById(R.id.rlist);

        ArrayAdapter adpater = ArrayAdapter.createFromResource(this,
                R.array.buyhistory_cafe_data,
                android.R.layout.simple_list_item_1);

        listview.setAdapter(adpater);

    }
}
