package com.example.cafeoda.OrderAlertList;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class AlertHistoryFragment extends Fragment {
    AlertHistroyAdapter myadapter;
    LinearLayoutManager manager;
    RecyclerView list;
    List<AlertHistoryDTO> guestorderlist = new ArrayList<AlertHistoryDTO>();

    public AlertHistoryFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.alert_history_main, container, false);
        list = view.findViewById(R.id.alert_list);
        getOrderlistDataFromHttp task = new getOrderlistDataFromHttp();
        task.execute();
        return view;
    }

    class getOrderlistDataFromHttp extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            String urlstr = "";
            URL url;
            BufferedReader in = null;
            String data = "";
            OutputStream os = null;
            Log.d("===","알림내역:"+((MainPageActivity) getActivity()).tempguestphone);
            String guestphone = ((MainPageActivity) getActivity()).tempguestphone;
            JSONArray ja;

            try {
                urlstr = "http://" + getString(R.string.ipaddress) + ":8088/cafeoda/guestorderlist.do?";
                urlstr += "guestphone=" + guestphone;
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
                    Log.d("===", "알림내역 가져오기 정상"+data);
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
            //변환한 AlertHistoryDTO를 ArrayList에 저장

            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    String orderdate = jo.getString("orderdate");
                    String cafename = jo.getString("cafename");
                    String prodname = jo.getString("prdname");
                    String statusmsg = jo.getString("statusmsg");


                    AlertHistoryDTO alertitem = new AlertHistoryDTO(orderdate, cafename, prodname, statusmsg);
                    guestorderlist.add(alertitem);

                }

                myadapter = new AlertHistroyAdapter(getActivity().getApplicationContext(),
                        R.layout.alert_histroy_list, guestorderlist);



                manager = new LinearLayoutManager(getActivity().getApplicationContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);

                list.setLayoutManager(manager);
                list.setAdapter(myadapter);
                myadapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
