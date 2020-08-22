package com.example.cafeoda.ShopMain;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cafeoda.LoginJoin.LoginAcitvity;
import com.example.cafeoda.Mainpage.MainPageActivity;
import com.example.cafeoda.Mainpage.MainListDTO;
import com.example.cafeoda.MenuList.MenuListFragment;
import com.example.cafeoda.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopMainFragment extends Fragment {
    int mycafeid;
    String cafename;
    String address;
    double latitude;
    double longitude;
    String weekend_opentime;
    String weekend_closetime;
    String weekday_opentime;
    String weekday_closetime;
    String regday;
    int mostpick1;
    int mostpick2;
    int mostpick3;
    String nearstation1;
    String nearstation2;
    String cafetel;

    int cafeId;

    TextView largecafenameTxt;

    boolean aflag = true;
    MainListDTO entercafedto = new MainListDTO();


    public ShopMainFragment() {
    }

    /*메인페이지에서 카페 아이디 값 받음 */
    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
        Log.d("테스트번들받기성공??","setCafeId=>"+cafeId);
    }

    public int getCafeId() {
        return cafeId;
    }


    //여기랑 fragment_guest_main.xml 얘가 카페이름이랑 사진
    //shopmainInfoFragment, shop_main_info.xml 가 매장 주소, 매장 시간 정보
    //menulistfragment가 메뉴 목록
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_guest_main, container, false);
        TabLayout tabLayout;
        tabLayout = view.findViewById(R.id.myshop_tab);
        largecafenameTxt = view.findViewById(R.id.cafename);

        getCafeDataFromHttp task = new getCafeDataFromHttp();
        task.execute();

        //if (task.getStatus() == AsyncTask.Status.FINISHED) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            ShopMainInfoFragment shopMainInfoFragment = new ShopMainInfoFragment();
            transaction.replace(R.id.main_shop_container, shopMainInfoFragment);
            transaction.detach(shopMainInfoFragment).attach(shopMainInfoFragment);

       // }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                MenuListFragment menuListFragment = new MenuListFragment();
                ShopMainInfoFragment shopMainInfoFragment = new ShopMainInfoFragment();
                int position = tab.getPosition();
                Log.d("===","position"+position);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (tab.getPosition()==0) {
                    Log.d("===", "탭 :" + tab.getPosition());
                    transaction.replace(R.id.main_shop_container, shopMainInfoFragment);
                  //  transaction.commit();
                    transaction.detach(menuListFragment).attach(shopMainInfoFragment).commit();
                } else {
                    Log.d("===", "탭 :" + tab.getPosition());
                    transaction.replace(R.id.main_shop_container, menuListFragment);
                    //transaction.commit();
                    transaction.detach(shopMainInfoFragment).attach(menuListFragment).commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    //서버에서 매장 정보를 받아올 클래스
    class getCafeDataFromHttp extends AsyncTask<Void, Void, String> {
        int cafeidd = getCafeId();

        public getCafeDataFromHttp() {

        }

        @Override
        protected String doInBackground(Void... voids) {
            String urlstr = "";
            URL url;
            BufferedReader in = null;
            String data = "";
            OutputStream os = null;
            //String cafeid = ((MainPageActivity) getActivity()).tempcafeid;
            JSONArray ja;

            try {
                urlstr = "http://" + getString(R.string.ipaddress) + ":8088/cafeoda/entercafe.do?";
                urlstr += "cafeid=" + cafeidd;
                url = new URL(urlstr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");

                //정상응답을 받았을 때 실행한다.

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(), "UTF-8")
                    );
                    data = in.readLine();
                    Log.d("===", "data from server" + data);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("===", "OnPostExecute");
            super.onPostExecute(s);

            //웹서버에서 가져온 데이터가 json형식이므로
            //파싱해서 JSONObject를 MenuVO로 변환
            //변환한 MenuVO를 ArrayList에 저장


            try {
                Log.d("===", "PostExecute on ShopMain Start");
                JSONObject jo = new JSONObject(s);
                mycafeid = jo.getInt("cafeid");
                cafename = jo.getString("cafename");
                address = jo.getString("address");
                //  latitude = jo.getDouble("latitude");
                //  longitude = jo.getDouble("longitude");
                weekday_opentime = jo.getString("weekday_opentime");
                weekday_closetime = jo.getString("weekday_closetime");
                weekend_opentime = jo.getString("weekend_opentime");
                weekend_closetime = jo.getString("weekend_closetime");
                regday = jo.getString("regday");
                //  nearstation1 = jo.getString("nearstation1");
                //  nearstation2 = jo.getString("nearstation2");
                cafetel = jo.getString("tel");


                entercafedto.setCafeid(mycafeid);
                entercafedto.setCafename(cafename);
                entercafedto.setAddress(address);
                //entercafedto.setLatitude(latitude);
                //entercafedto.setLongitude(longitude);
                entercafedto.setWeekday_opentime(weekday_opentime);
                entercafedto.setWeekday_closetime(weekday_closetime);
                entercafedto.setWeekend_opentime(weekend_opentime);
                entercafedto.setWeekend_closetime(weekend_closetime);
                entercafedto.setRegday(regday);
                // entercafedto.setNearstation1(nearstation1);
                // entercafedto.setNearstation2(nearstation2);
                entercafedto.setTel(cafetel);

                ((MainPageActivity) getActivity()).tempentercafedto = entercafedto;
                Log.d("===", "Save to Main : " + entercafedto.toString());
                Log.d("===", "Saved at Main : " + ((MainPageActivity) getActivity()).tempentercafedto.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("===", "Asynctask Ended");
            largecafenameTxt.setText(cafename);


        }
    }


}
