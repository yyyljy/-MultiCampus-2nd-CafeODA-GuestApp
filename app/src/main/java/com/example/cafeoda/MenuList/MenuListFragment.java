package com.example.cafeoda.MenuList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cafeoda.Mainpage.MainPageActivity;
import com.example.cafeoda.Mainpage.MainListDTO;
import com.example.cafeoda.MenuDetail.MenuDetailActivity;
import com.example.cafeoda.R;
import com.example.cafeoda.WishList.Jangbaguny;
import com.example.cafeoda.WishList.OrderDTO;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuListFragment extends Fragment {
    private MainListDTO entercafedto;
    int intentcafeid;
    boolean flag = false;
    String intentcafename,intentguestphone;
    LinearLayout linearLayout;
    RecyclerView menu;
    MenulistAdapter adapter2;
    ArrayList<Jangbaguny> shoppingbag =new ArrayList<Jangbaguny>();


    // 상품 클릭 시 옵션 선택 화면으로 값 넘겨주기
    ArrayList<OrderDTO> orderList = new ArrayList<>();
    OrderDTO order = new OrderDTO();
    //ArrayList<RecommandMenuItem> reclist;

    int cafeId;

    int cafeidFromMain;

    public MenuListFragment() {

    }

    /*메인페이지에서 카페 아이디 값 받음 */
    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
        Log.d("===","cafeid?????"+cafeId+"");

    }
    public int getCafeId(){
        return cafeId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("menulist", "onCreate: ");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("menulist", "onCreateView: ");
        View view = inflater.inflate(R.layout.menu_select, container, false);
        RecyclerView recommand;
        Button btn;
        recommand = view.findViewById(R.id.recommandlist);
        menu = view.findViewById(R.id.menulist);
        btn = view.findViewById(R.id.menubtn);

        entercafedto = ((MainPageActivity) getActivity()).tempentercafedto;
        intentcafeid = entercafedto.getCafeid();
        intentcafename = entercafedto.getCafename();
        intentguestphone = ((MainPageActivity) getActivity()).tempguestphone;
        Log.d("===","entercafedto???????"+entercafedto);
        Log.d("===","intentcafeid?????"+intentcafeid);
        HttpTest task = new HttpTest(intentcafeid);
        task.execute();


        // - 추천 메뉴
        ArrayList<RecommandMenuItem> simple_data = new ArrayList<RecommandMenuItem>();
        final RecommandMenuItem[] item = new RecommandMenuItem[4];
        item[0] = new RecommandMenuItem(01, 2000, 01, "아메리카노", 3500, "케냐",R.drawable.coffee1 );
        item[1] = new RecommandMenuItem(01, 2000, 03, "바닐라라떼", 4500, "브라질", R.drawable.coffee2);
        item[2] = new RecommandMenuItem(01, 2000, 02, "카페라떼", 4000, "케냐", R.drawable.coffee3);
        item[3] = new RecommandMenuItem(01, 2000, 05, "카페모카", 4500, "없음", R.drawable.coffee4);
        for (int i = 0; i < 4; i++) {
            simple_data.add(item[i]);
        }
        RecommandMenuorderAdapter adapter = new RecommandMenuorderAdapter(getActivity().getApplicationContext(), R.layout.recommandmenuorder, simple_data);
        adapter.setOnItemClickListener(new RecommandMenuorderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(getActivity().getApplicationContext(),"데이터"+menuitem[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), MenuDetailActivity.class);
                intent.putExtra("img", item[position].getImgsource());
                intent.putExtra("menuname", item[position].getProname());
                intent.putExtra("country", item[position].getCountry());
                intent.putExtra("price", item[position].getPrice());
                intent.putExtra("menunum", item[position].getMenunum());
                intent.putExtra("cafeid", intentcafeid);
                intent.putExtra("cafename", intentcafename);
                intent.putExtra("guestphone", intentguestphone);
                startActivityForResult(intent, 2);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommand.setLayoutManager(manager);
        recommand.setAdapter(adapter);


        // 장바구니 버튼 클릭
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoppingbag != null) {
                    Intent intent2 = new Intent(getActivity().getApplicationContext(), ShoppingActivity.class);
                    intent2.putParcelableArrayListExtra("test", shoppingbag);
                    intent2.putExtra("order", order);
                    intent2.putExtra("orderList", orderList);
                    //FCM을 위해 보낸다.//
                    intent2.putExtra("cafeid",intentcafeid);
                    intent2.putExtra("guestphone",intentguestphone);

                    startActivity(intent2);
                    Log.d("=== ", "데이터" + shoppingbag.size());
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("menulist", "onViewCreated: ");
        /**/
        //getView().findViewById(R.id.shop_layout).layou1;
    }

    class HttpTest extends AsyncTask<Void,Void,ArrayList<MenuItem>> {

        int intentcafeid;

        public HttpTest(int intentcafeid) {
            this.intentcafeid = intentcafeid;
        }

        @Override
        protected ArrayList<MenuItem> doInBackground(Void... voids) {
            Log.d("menulist", "doInBackground: ");
            ArrayList<MenuItem> menu_data= new ArrayList<MenuItem>();
            URL url = null;
            BufferedReader in= null;
            String data="";
            try {
                String path = "http://"+getString(R.string.ipaddress)+":8088/cafeoda/menulist.do?cafeid="+intentcafeid+"";
                url = new URL(path);
                Log.d("===","cafeid????????????????????????"+intentcafeid);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json");
                if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    Log.d("test1","메시지");
                    in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                    data = in.readLine();
                }
                JSONArray ja = null;
                try{
                    ja = new JSONArray(data);
                    Log.d("menulist",ja.length()+"");
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
                        Log.d("menulist",item+"");
                        menu_data.add(item);
                    }
                    Log.d("menulist",menu_data.size()+"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return menu_data;
        }

        @Override
        protected void onPostExecute(final ArrayList<MenuItem> menuArrayList) {
            super.onPostExecute(menuArrayList);
            // - 카페 메뉴
            adapter2 = new MenulistAdapter(getActivity().getApplicationContext(), R.layout.menulist, menuArrayList);
            adapter2.setOnItemClickListener(new MenulistAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    //Toast.makeText(getContext().getApplicationContext(),"데이터"+menuitem[position],Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getActivity().getApplicationContext(), MenuDetailActivity.class);
                    intent2.putExtra("menuname", menuArrayList.get(position).getProname());
                    intent2.putExtra("country", menuArrayList.get(position).getCountry());
                    intent2.putExtra("price", menuArrayList.get(position).getPrice());
                    intent2.putExtra("img", menuArrayList.get(position).getImgsource());
                    intent2.putExtra("menunum", menuArrayList.get(position).getMenunum());
                    intent2.putExtra("cafeid", intentcafeid);
                    intent2.putExtra("cafename", intentcafename);
                    intent2.putExtra("guestphone", intentguestphone);
                    startActivityForResult(intent2, 1);
                }
            });
            LinearLayoutManager manager2 = new LinearLayoutManager(getActivity().getApplicationContext());
            menu.setLayoutManager(manager2);
            menu.setAdapter(adapter2);
            menu.requestLayout();
        }
    }

    // insert 후 데이터 새로고침 //
    public void refreshData() {
        Log.d("menulist", "refreshData: ");
        getView().requestLayout();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("shopping",shoppingbag);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1|requestCode==2){
            if (resultCode== Activity.RESULT_OK){
                order = (OrderDTO) data.getExtras().get("order");
                Toast.makeText(getActivity().getApplicationContext(),"getorder :"+order,Toast.LENGTH_SHORT).show();
                String validate;
                /*if(order.getIcehot().trim().equals("0")){
                    validate = "HOT";
                }else {
                    validate = "ICE";
                }*/
                Jangbaguny jangbaguny = new Jangbaguny(order.getPrdname(),order.getIcehot(),order.getCupsize(),order.getQuantity(),order.getOneprice());
                Log.d("===","이름 : "+jangbaguny.getMenuname());
                shoppingbag.add(jangbaguny);
                orderList.add(order);
            }
        }
    }
}
