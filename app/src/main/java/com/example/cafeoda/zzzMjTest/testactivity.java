package com.example.cafeoda.zzzMjTest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafeoda.R;
import com.example.cafeoda.zzzNetwork.TestHttpHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class testactivity extends AppCompatActivity {

    EditText numtext;
    EditText context;
    TextView teststr;
    String tempResult = "";
    List<TestVO> items;
    ListView listview;

    TestHttpHandler getDailySalesHttpHandler; //액티비티 찾아야 하는 거 보통 맨 위에 써놓는다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        numtext = findViewById(R.id.num);
        context = findViewById(R.id.content);
        teststr = findViewById(R.id.strinfo);
        listview = (ListView)findViewById(R.id.rlist);
        items = new ArrayList<>();

    }

    public void submit(View view) {

        //쓰레드를 불러온 담에 execute를 해야 실행이 된다.
        getTestDataFromHttp task = new getTestDataFromHttp();
        task.execute();
        //Toast.makeText(this, "getFromDB"+ tempResult, Toast.LENGTH_SHORT).show();


    }

    public void inserting(View view){

    }


    class getTestDataFromHttp extends AsyncTask<Void, Void, String> {
        /*@Override
        protected void onPreExecute() { // 얘는 백그라운드 넘기기 전에 전처리 하는 메소드
            super.onPreExecute();
        }*/
        URL url;
        JSONObject jo;
        JSONArray ja;

        public getTestDataFromHttp() {
            try {//url을 커넥션으로 넘겨

                url = new URL("http://172.30.1.4:8088/cafeoda/home.do");
            } catch (MalformedURLException e) {
                Log.d("---","error Connection");
                e.printStackTrace();
            }

        }

        // background 에서 http 요청
        @Override
        protected String doInBackground(Void... voids) {

            return TestHttpHandler.requestData(url);
        }


        // 받아온 값 정제 //
        @Override
        protected void onPostExecute(String result) {
                /* JSONArray 를 서버에서 String으로 보낸것을, 다시 JSON으로 만드는 과정 /
                ja = new JSONArray(result);
                sdbList2 = new ArrayList<SalesVO>();
                if(ja.length() != 0 ){
                    for(int i = 0; i<ja.length();i++){
                        jo = ja.getJSONObject(i);
                        sdb = new SalesVO();
                        sdb.setDailySales(jo.getString("dailySales"));
                        sdb.setRevenue(jo.getString("revenue"));

                        sdbList2.add(sdb);
                    }
                    lastDay = sdbList2.get(0).getDailySales();
                    startDay = sdbList2.get(sdbList2.size()-1).getDailySales();
                    txt_lastday.setText(lastDay);
                    txt_startday.setText(startDay);
                    Log.d("---", "lastDay : " + lastDay + " startDay" + startDay);
                    Log.d("---","salesDBList" + sdbList2.toString());}
            } catch (JSONException e) {
                e.printStackTrace();
            }
            */
            //


            try {
                Log.d("---", "Get Result: " + result);
                tempResult = result;
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("---", "Error Occured during Converting result data");
            }

        }

    }


    /*class insertTestDataToHttp extends AsyncTask<Void, Void, String>{

    }*/
}
