package com.example.cafeoda.OrderAlertList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cafeoda.R;

public class AlertHistoryMain extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_history_main);
        listview = findViewById(R.id.rlist);

        ArrayAdapter adpater = ArrayAdapter.createFromResource(this,
                R.array.alarm_history,
                android.R.layout.simple_list_item_1);

        listview.setAdapter(adpater);
    }
}
