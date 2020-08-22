package com.example.cafeoda.WishList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cafeoda.FCM.FCMMain;
import com.example.cafeoda.Mainpage.MainListDTO;
import com.example.cafeoda.R;
import com.example.cafeoda.zzzNetwork.InsertOrderHttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {
    RecyclerView shoppingmenu;
    TextView tot;
    MainListDTO newshopdto;
    TextView cafenameshow;
    Button buybtn;
    ArrayList<OrderDTO> orderList;
    OrderDTO order;
    private String guestphone;
    private int cafeid;

    //ArrayList<ShoppingItem> test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        cafenameshow = findViewById(R.id.cafename);
        //newshopdto = ((MainPageActivity)getActivity())
        shoppingmenu = findViewById(R.id.shoppinglist);
        buybtn = findViewById(R.id.buybtn);
        tot = findViewById(R.id.totprice);
        ArrayList<Jangbaguny> simple_data = new ArrayList<Jangbaguny>();
        order = new OrderDTO();
        orderList = new ArrayList<OrderDTO>();
        Intent intent = getIntent();
        order = (OrderDTO) intent.getExtras().get("order");
        orderList = (ArrayList<OrderDTO>) intent.getSerializableExtra("orderList");
        Log.d("===", "get From MenuDetailActivity : " + order.toString());
        Log.d("===", "get From MenuDetailActivity :" + orderList.toString());

        //FCM을 위한 것
        guestphone = intent.getStringExtra("guestphone");
        cafeid = intent.getIntExtra("cafeid",0); //defaulValue지우지 마세요!


        ShoppingItem[] item;
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    insertOrderData(orderList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        if (intent.getExtras() != null) {
            simple_data = intent.getParcelableArrayListExtra("test");
        } else {
            Toast.makeText(getApplicationContext(), "담은 물건이 없습니다.", Toast.LENGTH_SHORT).show();
        }
        int total = 0;
        for (int i = 0; i < simple_data.size(); i++) {
            total += simple_data.get(i).getQuantity() * simple_data.get(i).getPrice();
        }
        tot.setText(total + "원");
        ShoppingAdapter adapter = new ShoppingAdapter(this, R.layout.shoppingbasket, simple_data);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        shoppingmenu.setLayoutManager(manager);
        shoppingmenu.setAdapter(adapter);


    }

    public void insertOrderData(ArrayList<OrderDTO> orderList) throws JSONException {
        JSONObject obj = new JSONObject();
        JSONArray jArray = new JSONArray();//배열이 필요할때
        try {

            for (int i = 0; i < orderList.size(); i++)//배열
            {
                JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
                sObject.put("ordid", orderList.get(i).getOrdid());
                sObject.put("guestphone", orderList.get(i).getGuestphone());
                sObject.put("ordnum", orderList.get(i).getOrdnum());
                sObject.put("cafeid",orderList.get(i).getCafeid());
                sObject.put("cafename",orderList.get(i).getCafename());
                sObject.put("menunum",orderList.get(i).getMenunum());
                sObject.put("prdname",orderList.get(i).getPrdname());
                sObject.put("country",orderList.get(i).getCountry());
                sObject.put("quantity",orderList.get(i).getQuantity());
                if(orderList.get(i).getIcehot().equals("HOT")){
                    orderList.get(i).setIcehot("HOT");
                    //sObject.put("icehot","HOT");
                }else if(orderList.get(i).getIcehot().equals("ICE")){
                    orderList.get(i).setIcehot("ICE");
                    //sObject.put("icehot","ICE");
                }else{
                    orderList.get(i).setIcehot("ICEHOT");
                    //sObject.put("icehot","ICE");
                }
                sObject.put("icehot",orderList.get(i).getIcehot());
                Log.d("===","쇼핑액티비티에서 insert하는 ice?hot?:"+orderList.get(i).getIcehot());
                sObject.put("cupsize",orderList.get(i).getCupsize());
                sObject.put("oneprice",orderList.get(i).getOneprice());
                jArray.put(sObject);
            }

           obj.put("orderdata",jArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        InsertOrderList task = new InsertOrderList(obj);
        task.execute();
    }


    class InsertOrderList extends AsyncTask<Void, Void, String> {
        String url;
        JSONObject jo;

        public InsertOrderList(JSONObject jo) {
            this.jo = jo;
            url = "http://" + getString(R.string.ipaddress) + ":8088/cafeoda/orderinsert.do";
        }

        @Override
        protected String doInBackground(Void... voids) {
            //커넥션 시작
            return InsertOrderHttpHandler.requestData(url,jo);
        }

        @Override
        protected void onPostExecute(String s) {
            FCMMain fcmMain = new FCMMain();
            Log.d("===","sendid설정하기:"+guestphone);
            Log.d("===","cafeid설정하기:"+cafeid);
            //sendId=고객ID, recvId=카페ID
            fcmMain.request("01074965575","01041133164");
            Toast.makeText(ShoppingActivity.this, "구매정보 전송완료", Toast.LENGTH_SHORT).show();
        }
    }
    public void buyBtnClick(View view){
        FCMMain fcmMain = new FCMMain();
        //sendId=고객ID, recvId=카페ID
        fcmMain.request("01074965575","01041133164");
        Toast.makeText(ShoppingActivity.this,"주문이 완료되었습니다.",Toast.LENGTH_SHORT).show();
    }
}
