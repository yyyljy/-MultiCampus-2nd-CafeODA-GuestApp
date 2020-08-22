package com.example.cafeoda.BuyHistoryList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeoda.Mainpage.MainPageActivity;
import com.example.cafeoda.R;

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
public class BuyHistoryFragment extends Fragment {
    RecyclerView recyclerView;
    BuyHistroyAdapter adapter;
    LinearLayoutManager manager;
    List<BuyHistroyDTO> guestorderlist = new ArrayList<BuyHistroyDTO>();


    public BuyHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buyhistorylist_main, container, false);
        recyclerView = view.findViewById(R.id.buylist);
        HttpMainbuy task = new HttpMainbuy();
        task.execute();

        return view;
    }
    public class HttpMainbuy extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {

            String urlstr = "";
            URL url;
            BufferedReader in = null;
            String data = "";
            OutputStream os = null;
            Log.d("===","영수증내역:"+((MainPageActivity) getActivity()).tempguestphone);
            String guestphone = ((MainPageActivity) getActivity()).tempguestphone;
            JSONArray ja;
            try {
                urlstr = "http://" + getString(R.string.ipaddress) + ":8088/cafeoda/guestorderlist.do?";
                urlstr += "guestphone=" + guestphone;
                url = new URL(urlstr);
                //웹서버에 연결
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //연결된 HttpURLConnection에 정보 설정
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json");

                //응답을 정상으로 받았을 때 실행하겠다는 의미
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    Log.d("===","내영수증 응답정상");
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
                    int ordnum = jo.getInt("ordnum");
                    String orderdate = jo.getString("orderdate");
                    String cafename = jo.getString("cafename");
                    String prdname = jo.getString("prdname");
                    int quantity = jo.getInt("quantity");
                    String icehot = jo.getString("icehot");
                    String cupsize = jo.getString("cupsize");
                    int oneprice = jo.getInt("oneprice");
                    String statusmsg = jo.getString("statusmsg");


                    BuyHistroyDTO guesthistoryitem = new BuyHistroyDTO(ordnum,orderdate,cafename,prdname,quantity,icehot,cupsize,oneprice,statusmsg);
                    guestorderlist.add(guesthistoryitem);
                }
                adapter = new BuyHistroyAdapter(getActivity().getApplicationContext(),
                        R.layout.buy_histroy_list,
                        guestorderlist);
                Log.d("===","guest영수증리스트:"+guestorderlist.toString());

                //매니저 정의해주시고
                //list
                manager = new LinearLayoutManager(getActivity().getApplicationContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
