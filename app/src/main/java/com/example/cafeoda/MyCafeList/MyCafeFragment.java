package com.example.cafeoda.MyCafeList;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cafeoda.LoginJoin.LoginAcitvity;
import com.example.cafeoda.Mainpage.ItemClick;
import com.example.cafeoda.Mainpage.MainPageActivity;
import com.example.cafeoda.R;
import com.example.cafeoda.ShopMain.ShopMainFragment;

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
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCafeFragment extends Fragment {

    private int mycafe1,mycafe2,mycafe3,mycafe4,mycafe5;
    ShopMainFragment shopMainFragment = new ShopMainFragment();

    public MyCafeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mycafe_main, container, false);
        RecyclerView list;
        list = view.findViewById(R.id.rlist);
        MyCafeVO item;
        List<MyCafeVO> recycler_simple_data = new ArrayList<MyCafeVO>();

        mycafe1 = ((MainPageActivity)getActivity()).tempmycafe1;
        mycafe2 = ((MainPageActivity)getActivity()).tempmycafe2;
        mycafe3 = ((MainPageActivity)getActivity()).tempmycafe3;
        mycafe4 = ((MainPageActivity)getActivity()).tempmycafe4;
        mycafe5 = ((MainPageActivity)getActivity()).tempmycafe5;

        int arr[] = new int[5];
        arr[0] = mycafe1;
        arr[1] = mycafe2;
        arr[2] = mycafe3;
        arr[3] = mycafe4;
        arr[4] = mycafe5;

        for(int i=0; i<5;i++){

      /*      getCafeDataFromHttp task = new getCafeDataFromHttp();
            task.execute(arr[0]);
*/
        }



        MyCafeAdapter myadapter = new MyCafeAdapter(getActivity().getApplicationContext(),
                R.layout.mycafe_item,
                recycler_simple_data, new ItemClick() {
            @Override
            public void onClick(String val, int cafeid) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                if(val.equals("shop")){
                    shopMainFragment.setCafeId(cafeid);
                    transaction.replace(R.id.main_container,shopMainFragment).addToBackStack("shop");


                }
                transaction.commit();
            }
        });

        //리니어레이아웃을 세팅할 수 있는 LinearlayoutManager객체
        //그리드를 만드는 레이아웃 매니저 객체도 따로 있다. 종류에 따라 매니저 객체가 존재한다.


        GridLayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        list.setHasFixedSize(true);

        list.setLayoutManager(manager); //리사이클러뷰에 VERTICAL LinearLayout이 세팅

        list.setAdapter(myadapter);
        return view;
    }
/*
    // 서버에서 메뉴 정보 가져오기 //

    class getCafeDataFromHttp extends AsyncTask<Void, Void, String> {
        String url;
        public getCafeDataFromHttp(int cafeid){

        }

        @Override
        protected String doInBackground(Void... voids) {
            String urlstr = "";
            URL url;
            BufferedReader in = null;
            String data = "";
            OutputStream os = null;
            //String cafeid = ((MainPageActivity) getActivity()).tempcafeid;





            //JSONArray ja;

            try {
                urlstr = "http://" + LoginAcitvity.ip + ":8088/cafeoda/mycafe.do?";
                urlstr += "cafeid=" + cafeid;
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
                    Log.d("---", data);
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
            super.onPostExecute(s);

            //웹서버에서 가져온 데이터가 json형식이므로
            //파싱해서 JSONObject를 MenuVO로 변환
            //변환한 MenuVO를 ArrayList에 저장

            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    String proname = jo.getString("proname");
                    String country = jo.getString("country");
                    int price = jo.getInt("price");
                    int menunum = jo.getInt("menunum");

                    MyCafeVO menuitem = new MyCafeVO(cafename);
                    menulist.add(menuitem);

                }

                myadapter = new MenuListAdapter(getActivity().getApplicationContext(),
                        R.layout.owner_menu_item, menulist);

                manager = new LinearLayoutManager(getActivity().getApplicationContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                list.setLayoutManager(manager);
                list.setAdapter(myadapter);
                myadapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }*/


}
