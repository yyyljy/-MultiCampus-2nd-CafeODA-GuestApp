package com.example.cafeoda.MenuList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeoda.MenuDetail.MenuDetailActivity;
import com.example.cafeoda.R;
import com.example.cafeoda.WishList.Jangbaguny;
import com.example.cafeoda.WishList.ShoppingActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class _MainActivity extends AppCompatActivity {
    RecyclerView recommand;
    RecyclerView menu;
    Button btn;
    ArrayList<Jangbaguny> shoppingbag =new ArrayList<Jangbaguny>();
    int cafeId;

    public _MainActivity(){

    }
    public _MainActivity(int cafeId){
        this.cafeId = cafeId;
        Log.d("테스트번들받기성공??","wqeqwe=>"+cafeId);
    }

    public int getCafeId() {
        return cafeId;
    }

    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
    }

    //JSonObject 변환 변수들
    public String tempguestphone="";
    public String tempguestpass="";
    public String tempguestnickname="";
    public String tempguestpoint="";
    //JSONObject 변수 끝

    ArrayList<RecommandMenuItem> reclist;
    ArrayList<MenuItem> list=new ArrayList<MenuItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_select);
        recommand = findViewById(R.id.recommandlist);
        menu = findViewById(R.id.menulist);
        btn = findViewById(R.id.menubtn);
        String url = "http://"+getString(R.string.ipaddress)+":8088/cafeoda/menulist.do";
        HttpTest task = new HttpTest();
        task.execute();
        ArrayList<RecommandMenuItem> simple_data = new ArrayList<RecommandMenuItem>();
        final RecommandMenuItem[] recitem = new RecommandMenuItem[4];
        recitem[0] = new RecommandMenuItem(1,2,1,"아메리카노",4100,"콜롬비아",R.drawable.coffee1);
        recitem[1] = new RecommandMenuItem(2,2,2,"카페라떼",4600,"인도네시아",R.drawable.coffee2);
        recitem[2] = new RecommandMenuItem(3,2,5,"카페모카",4500,"케냐",R.drawable.coffee3);
        recitem[3] = new RecommandMenuItem(4,2,6,"그린티",5000,"도네시아",R.drawable.coffee4);
        for (int i=0;i<4;i++){
            simple_data.add(recitem[i]);
        }
        //연습용 - 추천 메뉴


        RecommandMenuorderAdapter adapter = new RecommandMenuorderAdapter(this, R.layout.recommandmenuorder,simple_data);
        adapter.setOnItemClickListener(new RecommandMenuorderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getApplicationContext(),"데이터"+ reclist.get(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MenuDetailActivity.class);
                intent.putExtra("img", reclist.get(position).getImgsource());
                intent.putExtra("menuname",reclist.get(position).getProname());
                intent.putExtra("country",reclist.get(position).getCountry());
                intent.putExtra("price",reclist.get(position).getPrice());
                startActivityForResult(intent,2);
            }
        });
       /* MenulistAdapter adapter2 = new MenulistAdapter(this, R.layout.menulist,list);
        adapter2.setOnItemClickListener(new MenulistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getApplicationContext(),"데이터"+menuitem[position],Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), MenuDetailActivity.class);
                intent2.putExtra("menuname",menuitem[position].getMenuname());
                intent2.putExtra("country",menuitem[position].getCountry());
                intent2.putExtra("price",menuitem[position].getPrice());
                intent2.putExtra("img",menuitem[position].getImg());
                startActivityForResult(intent2,1);
            }
        });*/
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager manager2 = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        recommand.setLayoutManager(manager);
        menu.setLayoutManager(manager2);
        recommand.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shoppingbag!=null){
                    Intent intent2 = new Intent(getApplicationContext(), ShoppingActivity.class);
                    intent2.putParcelableArrayListExtra("test",shoppingbag);
                    startActivity(intent2);
                    Log.d("caartsize ","데이터"+shoppingbag.size());
                }
            }
        });
    }
    class HttpTest extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            URL url = null;
            BufferedReader in= null;
            String data="";
            try {
                String path = "http://"+getString(R.string.ipaddress)+":8088/cafeoda/menulist.do?cafeid="+getCafeId();
                url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json");
                if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    Log.d("teest","메시지");
                    in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                    data = in.readLine();
                }
                Log.d("myhttp00000",data);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONArray ja = null;
            Log.d("myhttp00000",s);
            try{
                ja = new JSONArray(s);
                Log.d("myhttp00000",ja.length()+"");
                for(int i=0;i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    int menunum = jo.getInt("menunum");
                    int cafeid = jo.getInt("cafeid");
                    int proid = jo.getInt("proid");
                    String proname = jo.getString("proname");
                    int price = jo.getInt("price");
                    String country = jo.getString("country");
                    String imgsource = jo.getString("imgsource");
                    MenuItem item = new MenuItem(menunum,cafeid,proid,proname,price,country,imgsource);
                    Log.d("myhttp00000",item+"");
                    list.add(item);
                }
                Log.d("myhttp00000",list.size()+"");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MenulistAdapter adapter2 = new MenulistAdapter(getApplicationContext(), R.layout.menulist,list);
            adapter2.setOnItemClickListener(new MenulistAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(getApplicationContext(),"데이터"+ list.get(position),Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getApplicationContext(), MenuDetailActivity.class);
                    intent2.putExtra("menuname", list.get(position).getProname());
                    intent2.putExtra("country",list.get(position).getCountry());
                    intent2.putExtra("price",list.get(position).getPrice());
                    intent2.putExtra("img",list.get(position).getImgsource());
                    startActivityForResult(intent2,1);
                }
            });
            menu.setAdapter(adapter2);
        }
    }

@Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("shopping",shoppingbag);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1|requestCode==2){
            if (resultCode==RESULT_OK){
                //String opt = data.getExtras().getString("optionice");

                Jangbaguny jangbaguny = new Jangbaguny(data.getExtras().getString("ordname"),
                        data.getExtras().getString("optionice"), data.getExtras().getString( "size"), data.getExtras().getInt("quantity"),data.getExtras().getInt("price"));
                Log.d("jang","이름 : "+jangbaguny.getMenuname());
                shoppingbag.add(jangbaguny);
            }
        }
    }
}