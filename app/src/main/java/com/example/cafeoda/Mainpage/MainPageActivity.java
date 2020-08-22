package com.example.cafeoda.Mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.cafeoda.BuyHistoryList.BuyHistoryFragment;
import com.example.cafeoda.MyCafeList.MyCafeFragment;
import com.example.cafeoda.OrderAlertList.AlertHistoryFragment;
import com.example.cafeoda.R;
import com.example.cafeoda.ShopMain.ShopMainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainPageActivity extends AppCompatActivity {

    //TAG//
    public static String TAG = "===";

    //로그인 액티비티에서 받아온 JsonObject 변환 변수들
    public String tempguestphone="";
    public String tempguestpass="";
    public String tempguestnickname="";
    public int tempguestpoint;
    public int tempmycafe1;
    public int tempmycafe2;
    public int tempmycafe3;
    public int tempmycafe4;
    public int tempmycafe5;
    //JsonObject 변환 변수들 끝

    public int tempmycafeid;
    public String tempcafename;
    public String tempaddress;
    public double templatitude;
    public double templongitude;
    public String tempweekend_opentime;
    public String tempweekend_closetime;
    public String tempweekday_opentime;
    public String tempweekday_closetime;
    public String tempregday;
    public int tempmostpick1;
    public int tempmostpick2;
    public int tempmostpick3;
    public String tempnearstation1;
    public String tempnearstation2;
    public String tempcafetel;
    public MainListDTO tempentercafedto;

    //cafeid 저장 변수 //
    //public int tempCafeId = 2;

    //프래그먼트들
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    MainFragment mainFragment = new MainFragment();
    AlertHistoryFragment alertHistoryFragment = new AlertHistoryFragment();
    MyCafeFragment myCafeFragment = new MyCafeFragment();
    BuyHistoryFragment buyHistoryFragment = new BuyHistoryFragment();
    ShopMainFragment shopMainFragment = new ShopMainFragment();
    Button button1;
    Button button2;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_main_page);

        //로그인액티비티에서 로그인 성공 시 intent로 값 넘겨와서 main액티비티로 온다.
        Intent intent = getIntent();
        String guestinfo = intent.getExtras().getString("guestinfo");
        Log.d("===","getFromLoginPage:"+guestinfo);
        try {
            JSONObject jo = new JSONObject(guestinfo);
            String guestphone = jo.getString("guestphone");
            tempguestphone = guestphone;
            String guestpass = jo.getString("guestpass");
            tempguestpass = guestpass;
            String guestnickname = jo.getString("guestnickname");
            tempguestnickname = guestnickname;
            int guestpoint = Integer.parseInt(jo.getString("guestpoint"));
            tempguestpoint = guestpoint;
            int guestmycafe1 = Integer.parseInt(jo.getString("mycafe1"));
            tempmycafe1 = guestmycafe1;
            int guestmycafe2 = Integer.parseInt(jo.getString("mycafe2"));
            tempmycafe1 = guestmycafe2;
            int guestmycafe3 = Integer.parseInt(jo.getString("mycafe3"));
            tempmycafe1 = guestmycafe3;
            int guestmycafe4 = Integer.parseInt(jo.getString("mycafe4"));
            tempmycafe1 = guestmycafe4;
            int guestmycafe5 = Integer.parseInt(jo.getString("mycafe5"));
            tempmycafe1 = guestmycafe5;
            Log.d("===","메인액티비티guestnickname:"+guestnickname);
            Log.d("===","메인액티비티tempguestnickname:"+tempguestnickname);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ////로그인액티비티에서 온 데이터 정제 끝 ///


        //바텀 내비게이션
        drawerLayout = findViewById(R.id.main_drwer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_str, R.string.close_str);
        toggle.syncState();
        bottomNavigationView = findViewById(R.id.bottomNavi_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(new MyListener());
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container,mainFragment); //xml에서 fragment를 넣어서 바꾸는게 아니라
        //layout에 id를 주고 거기에 fragment를 넣어서 바꾸는 걸로 수정함
        transaction.commit();

        /*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.main_naviView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                return false;
            }
        });
        */
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.msg,menu);
        return true;
        /*타이틀바에 종모양*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        int id = item.getItemId();
        transaction = fragmentManager.beginTransaction();
        switch (id){
            case R.id.msgitem :
                transaction.replace(R.id.main_container,alertHistoryFragment).addToBackStack("bottom");;
                drawerLayout.closeDrawers();
                break;
        }
        transaction.commit();
        return super.onOptionsItemSelected(item);
    }

    class MyListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int item = menuItem.getItemId();
            transaction = fragmentManager.beginTransaction();
            switch (item){
                //bottom navi 메인페이지
                case R.id.myhome :
                    menuItem.setChecked(true);
                    transaction.replace(R.id.main_container,mainFragment).addToBackStack(null);

                    drawerLayout.closeDrawers();
                    break;
                //bottom navi 주문현황
                case R.id.myalarm :
                    menuItem.setChecked(true);
                        transaction.replace(R.id.main_container,alertHistoryFragment).addToBackStack("bottom");
                    drawerLayout.closeDrawers();
                    break;
               /* //bottom navi 매장의 메인화면
                case R.id.myinfo :
                    *//*
                    menuItem.setChecked(true);
                        transaction.replace(R.id.main_container,shopMainFragment).addToBackStack("bottom");
                    drawerLayout.closeDrawers();
                    *//*
                    break;*/
                //bottom navi 내 주문내역
                case R.id.myorder :
                    menuItem.setChecked(true);
                    transaction.replace(R.id.main_container,buyHistoryFragment).addToBackStack("bottom");
                    drawerLayout.closeDrawers();
                    break;
            }
            transaction.commit();
            return false;
        }
    }
}
