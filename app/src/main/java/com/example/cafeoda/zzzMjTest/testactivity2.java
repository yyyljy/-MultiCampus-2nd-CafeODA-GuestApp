package com.example.cafeoda.zzzMjTest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafeoda.R;
import com.example.cafeoda.zzzNetwork.TestHttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class testactivity2 extends AppCompatActivity {

    EditText numtext;
    EditText context;
    TextView teststr;
    String tempResult = "";
    ArrayList<TestVO> itemlist;
    ListView listview;

    TextView numnumtext;
    TextView strstrtext;
    LinearLayout container;
    ProductAdapter itemAdaptor;


    TestHttpHandler getDailySalesHttpHandler; //액티비티 찾아야 하는 거 보통 맨 위에 써놓는다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        numtext = findViewById(R.id.num);
        context = findViewById(R.id.content);
        teststr = findViewById(R.id.strinfo);
        listview = findViewById(R.id.listview);
        itemlist = new ArrayList<>();
        numnumtext = findViewById(R.id.textView1);
        strstrtext = findViewById(R.id.textView2);
        container = findViewById(R.id.container);


    }

    public void submit(View view) {

        //쓰레드를 불러온 담에 execute를 해야 실행이 된다.
        getTestDataFromHttp task = new getTestDataFromHttp();
        task.execute();
        //Toast.makeText(this, "getFromDB"+ tempResult, Toast.LENGTH_SHORT).show();


    }

      class getTestDataFromHttp extends AsyncTask<Void, Void, String> {



        // background 에서 http 요청
        @Override
        protected String doInBackground(Void... voids) {
            URL url;
            BufferedReader in =null;
            String data ="";

            JSONArray ja;
            try {
                url = new URL("http://70.12.231.173:8088/cafeoda/hometest.do");

                HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type","application/json");

                //정상응답을 받았을 때 실행한다.
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(),"UTF-8")
                    );


                    data = in.readLine();
                    Log.d("---",data);
                }
            } catch (IOException e) {
                e.printStackTrace();

        }
            return data;


        }


        // 받아온 값 정제 //
        @Override
        protected void onPostExecute(String s) {

            JSONArray ja = null;

            try {
                ja = new JSONArray(s);

                Log.d("---", "Get Result: " + s);

                for(int i=0; i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    int testnum = jo.getInt("testnum");
                    String teststr = jo.getString("teststr");

                    TestVO item = new TestVO(testnum,teststr);
                    itemlist.add(item);
                }
                    tempResult = s;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("---", "Error Occured during Converting result data");
            }
            itemAdaptor = new ProductAdapter(itemlist);
            listview.setAdapter(itemAdaptor);
        }

    }

    class ProductAdapter extends BaseAdapter {
        ArrayList<TestVO> listlist;
        public ProductAdapter(ArrayList<TestVO> listlist) {
            this.listlist = listlist;
        }
        @Override
        public int getCount() {
            return listlist.size();
        }
        @Override
        public Object getItem(int position) {
            return listlist.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = null;
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.testitem, container, true);
            TextView test_num = itemView.findViewById(R.id.textView1);
            TextView test_str = itemView.findViewById(R.id.textView2);
            test_num.setText(listlist.get(position).getTestnum()+"");
            test_str.setText(listlist.get(position).getTeststr());

            return itemView;
        }
    }

}
