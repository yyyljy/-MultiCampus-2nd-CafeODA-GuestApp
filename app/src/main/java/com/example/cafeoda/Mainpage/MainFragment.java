package com.example.cafeoda.Mainpage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cafeoda.MenuList.MenuListFragment;
import com.example.cafeoda.R;
import com.example.cafeoda.ShopMain.ShopMainFragment;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private String guestphone;
    private String guestpass;
    private String guestnickname;
    private int guestpoint;
    private int mycafe1,mycafe2,mycafe3,mycafe4,mycafe5;

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    ShopMainFragment shopMainFragment;
    MenuListFragment menuListFragment = new MenuListFragment();
    RecycleCardAdapter1 adapter1;
    RecycleCardAdapter2 adapter2;
    RecycleCardAdapter3 adapter3;
    RecycleCardAdapter4 adapter4;

    LinearLayoutManager manager;

    TextView nicknameTxt1, nicknameTxt2, nicknameTxt3;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = view.findViewById(R.id.main_recyclerview);
        recyclerView2 = view.findViewById(R.id.main_recyclerview2);
        recyclerView3 = view.findViewById(R.id.main_recyclerview3);
        recyclerView4 = view.findViewById(R.id.main_recyclerview4);

        mycafe1 = ((MainPageActivity)getActivity()).tempmycafe1;
        mycafe2 = ((MainPageActivity)getActivity()).tempmycafe2;
        mycafe3 = ((MainPageActivity)getActivity()).tempmycafe3;
        mycafe4 = ((MainPageActivity)getActivity()).tempmycafe4;
        mycafe5 = ((MainPageActivity)getActivity()).tempmycafe5;

        makeUI(view);

        // 최근추가 매장 task
        HttpMain1 task1 = new HttpMain1();
        task1.execute();

        // 랜덤 매장 task
        HttpMain3 task3 = new HttpMain3();
        task3.execute();

        //새로나온매장추가
        HttpMain4 task4 = new HttpMain4();
        task4.execute();
        shopMainFragment = new ShopMainFragment();

        return view;
    }

    //닉네임 노출용, 로그인된 정보를 저장한다.
    public void makeUI(View view){

        guestphone = ((MainPageActivity)getActivity()).tempguestphone;
        guestpass = ((MainPageActivity)getActivity()).tempguestpass;
        guestnickname = ((MainPageActivity)getActivity()).tempguestnickname;
        guestpoint = ((MainPageActivity)getActivity()).tempguestpoint;

        nicknameTxt1 = view.findViewById(R.id.Txt_nickname1);
        nicknameTxt3 = view.findViewById(R.id.Txt_nickname3);

        nicknameTxt1.setText(guestnickname);
        nicknameTxt3.setText(guestnickname);


        Log.d("---","makeui : "+guestnickname);
    }

    /*==========================================
          최근 구매 매장 리스트
   =========================================== */
    public class HttpMain1 extends AsyncTask<Void,Void,String> {
        ArrayList<MainListDTO> list = new ArrayList<MainListDTO>();
        @Override
        protected String doInBackground(Void... voids) {
            URL url = null;
            BufferedReader in = null;
            String data ="";
            try {
                /*최근구매 매장*/
                String path = "http://"+getString(R.string.ipaddress)+":8088/cafeoda/recentcafe.do";
                /*String path = "http://192.168.123.103:8088/cafeoda/recentcafe.do";*/
                url = new URL(path);
                Log.d("testurl",path);
                //웹서버에 연결
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                //연결된 HttpURLConnection에 정보 설정
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json");
                //응답을 정상으로 받았을 때 실행하겠다는 의미
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    Log.d("test","jjj");
                    in = new BufferedReader(new InputStreamReader(
                            connection.getInputStream(),"UTF-8"));
                }
                data = in.readLine();
                Log.d("test",data);
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
            Log.d("프래그먼트",s);
            /*cafelist 정보*/
            JSONArray ja = null;
            MainListDTO recentdto ;
            MainListDTO imgdto;
            try {
                ja = new JSONArray(s);
                for (int i=0;i<ja.length();i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    int cafeid = jo.getInt("cafeid");
                    String cafename = jo.getString("cafename");
                    String address = jo.getString("address");
                    long latitude = jo.getLong("latitude");
                    long longitude = jo.getLong("longitude");
                    String weekend_opentime = jo.getString("weekend_opentime");
                    String weekend_closetime = jo.getString("weekend_closetime");
                    String weekday_opentime = jo.getString("weekday_opentime");
                    String weekday_closetime = jo.getString("weekday_closetime");
                    String regday = jo.getString("regday");
                    int mostpick1 = jo.getInt("mostpick1");
                    int mostpick2 = jo.getInt("mostpick2");
                    int mostpick3 = jo.getInt("mostpick3");
                    String nearstation1 = jo.getString("nearstation1");
                    String nearstation2 = jo.getString("nearstation2");
                    String tel = jo.getString("tel");
                    String pass = jo.getString("pass");
                    /*최근구매매장 json받음*/
                    recentdto= new MainListDTO(cafeid,cafename,address,latitude,longitude,
                            weekend_opentime,weekend_closetime,weekday_opentime,weekday_closetime,
                            regday,mostpick1,mostpick2,mostpick3,nearstation1,nearstation2,tel,pass,R.drawable.gong);
                    list.add(recentdto);
                }

                adapter2 = new RecycleCardAdapter2(getActivity().getApplicationContext(),
                        R.layout.cardview,
                        list,
                        new ItemClick() {
                            @Override
                            public void onClick(String val,int cafeId) {
                                /*이부분은 클릭했을때 매장메인으로 프레그먼트 교체 ItemClick과 Adapter연결*/
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                if (val.equals("shop")) {
                                    shopMainFragment.setCafeId(cafeId);
                                    menuListFragment.setCafeId(cafeId);
                                    //((MainPageActivity)getActivity()).tempCafeId = cafeId;
                                    fragmentTransaction.replace(R.id.main_container, shopMainFragment).addToBackStack("main");
                                }
                                fragmentTransaction.commit();
                            }
                        });
                manager = new LinearLayoutManager(getActivity().getApplicationContext());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    /*==========================================
            랜덤 매장 리스트
     =========================================== */
    public class HttpMain3 extends AsyncTask<Void,Void,String> {
        ArrayList<MainListDTO> list3 = new ArrayList<MainListDTO>();
        @Override
        protected String doInBackground(Void... voids) {
            URL url = null;
            BufferedReader in = null;
            String data ="";
            try {
                /*최근구매 매장*/
                String path = "http://"+getString(R.string.ipaddress)+":8088/cafeoda/randomcafe.do";
                url = new URL(path);
                Log.d("testurl",path);
                //웹서버에 연결
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                //연결된 HttpURLConnection에 정보 설정
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json");
                //응답을 정상으로 받았을 때 실행하겠다는 의미
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    Log.d("test","jjj");
                    in = new BufferedReader(new InputStreamReader(
                            connection.getInputStream(),"UTF-8"));
                }
                data = in.readLine();
                Log.d("test",data);
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
            /*cafelist 정보*/
            JSONArray ja = null;
            MainListDTO randomdto ;
            try {
                ja = new JSONArray(s);
                for (int i=0;i<ja.length();i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    int cafeid = jo.getInt("cafeid");
                    String cafename = jo.getString("cafename");
                    String address = jo.getString("address");
                    long latitude = jo.getLong("latitude");
                    long longitude = jo.getLong("longitude");
                    String weekend_opentime = jo.getString("weekend_opentime");
                    String weekend_closetime = jo.getString("weekend_closetime");
                    String weekday_opentime = jo.getString("weekday_opentime");
                    String weekday_closetime = jo.getString("weekday_closetime");
                    String regday = jo.getString("regday");
                    int mostpick1 = jo.getInt("mostpick1");
                    int mostpick2 = jo.getInt("mostpick2");
                    int mostpick3 = jo.getInt("mostpick3");
                    String nearstation1 = jo.getString("nearstation1");
                    String nearstation2 = jo.getString("nearstation2");
                    String tel = jo.getString("tel");
                    String pass = jo.getString("pass");
                    /*랜덤매장 json받음*/
                    randomdto = new MainListDTO(cafeid,cafename,address,latitude,longitude,
                            weekend_opentime,weekend_closetime,weekday_opentime,weekday_closetime,
                            regday,mostpick1,mostpick2,mostpick3,nearstation1,nearstation2,tel,pass,R.drawable.jang);
                    list3.add(randomdto);


                }
                /*bundle.putParcelableArrayList("list",list);*/
                adapter3 = new RecycleCardAdapter3(getActivity().getApplicationContext(),
                        R.layout.cardview,
                        list3,
                        new ItemClick() {
                            @Override
                            public void onClick(String val,int cafeId) {
                                /*이부분은 클릭했을때 매장메인으로 프레그먼트 교체 ItemClick과 Adapter연결*/
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                if (val.equals("shop")) {
                                    shopMainFragment.setCafeId(cafeId);
                                    menuListFragment.setCafeId(cafeId);
                                    fragmentTransaction.replace(R.id.main_container, shopMainFragment).addToBackStack("main");
                                }
                                fragmentTransaction.commit();
                                /*shopMainFragment.setArguments(bundle);*/
                            }
                        });
                manager = new LinearLayoutManager(getActivity().getApplicationContext());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView3.setLayoutManager(manager);
                recyclerView3.setAdapter(adapter3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /*==========================================
            새로운 매장 리스트
     =========================================== */
    public class HttpMain4 extends AsyncTask<Void,Void,String> {
        ArrayList<MainListDTO> list = new ArrayList<MainListDTO>();
        @Override
        protected String doInBackground(Void... voids) {

            URL url = null;
            BufferedReader in = null;
            String data ="";
            try {
                String path = "http://"+ getString(R.string.ipaddress) +":8088/cafeoda/newcafe.do";
                url = new URL(path);
                Log.d("testurl",path);
                //웹서버에 연결
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //연결된 HttpURLConnection에 정보 설정
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json");

                //응답을 정상으로 받았을 때 실행하겠다는 의미
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    Log.d("test","jjj");
                    in = new BufferedReader(new InputStreamReader(
                            connection.getInputStream(),"UTF-8"));
                }
                data = in.readLine();
                Log.d("test",data);

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
            //웹서버에서 가져온 데이터가 json형식이므로
            //파싱해서 JSONObject를 ProductDTO로 변환
            //변환한 ProductDTO를 ArrayList에 저장장
            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                for (int i=0;i<ja.length();i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    int cafeid = jo.getInt("cafeid");
                    String cafename = jo.getString("cafename");
                    MainListDTO dto = new MainListDTO(cafeid,cafename,R.drawable.gong);
                    list.add(dto);
                }
                Log.d("testlist",list.toString());
                adapter4 = new RecycleCardAdapter4(getActivity().getApplicationContext(),
                        R.layout.cardview,
                        list,
                        new ItemClick() {
                            @Override
                            public void onClick(String val,int cafeid) {
                                /*이부분은 클릭했을때 매장메인으로 프레그먼트 교체 ItemClick과 Adapter연결*/
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                if (val.equals("shop")) {
                                    shopMainFragment.setCafeId(cafeid);
                                    menuListFragment.setCafeId(cafeid);
                                    fragmentTransaction.replace(R.id.main_container, shopMainFragment).addToBackStack("shop");
                                }
                                fragmentTransaction.commit();
                            }
                        });
                //매니저 정의해주시고
                //list
                manager = new LinearLayoutManager(getActivity().getApplicationContext());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView4.setLayoutManager(manager);
                recyclerView4.setAdapter(adapter4);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
