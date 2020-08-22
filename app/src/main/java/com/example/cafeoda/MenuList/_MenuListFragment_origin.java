package com.example.cafeoda.MenuList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeoda.Mainpage.MainListDTO;
import com.example.cafeoda.Mainpage.MainPageActivity;
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
public class _MenuListFragment_origin extends Fragment {
    ArrayList<Jangbaguny> shoppingbag =new ArrayList<Jangbaguny>();
    private MainListDTO entercafedto;
    int intentcafeid;
    String intentcafename,intentguestphone;
    OrderDTO order = new OrderDTO();
    ArrayList<OrderDTO> orderList = new ArrayList<>();
    ArrayList<RecommandMenuItem> reclist;
    ArrayList<MenuItem> list=new ArrayList<MenuItem>();
    RecyclerView menu;
    MenulistAdapter adapter2;
    LinearLayout linearLayout;

    int cafeId;

    public _MenuListFragment_origin() {

    }

    public _MenuListFragment_origin(int cafeId){
        this.cafeId = cafeId;
    }

    /*메인페이지에서 카페 아이디 값 받음 */
    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;

    }
    public int getCafeId(){
        return cafeId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("menulist", "onCreate: ");
        HttpTest task = new HttpTest();
        task.execute();
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

        entercafedto = ((MainPageActivity)getActivity()).tempentercafedto;
        intentcafeid = entercafedto.getCafeid();
        intentcafename = entercafedto.getCafename();
        intentguestphone = ((MainPageActivity)getActivity()).tempguestphone;

        // - 추천 메뉴
        ArrayList<RecommandMenuItem> simple_data = new ArrayList<RecommandMenuItem>();
        final RecommandMenuItem[] item = new RecommandMenuItem[4];
        item[0] = new RecommandMenuItem(01,01,01,"아메리카노",3500,"케냐",R.drawable.coffee1);
        item[1] = new RecommandMenuItem(01,01,03,"바닐라라떼",4500,"브라질",R.drawable.coffee1);
        item[2] = new RecommandMenuItem(01,01,02,"카페라떼",4000,"케냐",R.drawable.coffee1);
        item[3] = new RecommandMenuItem(01,01,05,"블루베리스무디",4500,"없음",R.drawable.coffee1);
        // -메뉴 리스트
        final ArrayList<MenuItem> menu_data = new ArrayList<MenuItem>();
        final MenuItem[] menuitem = new MenuItem[7];
        menuitem[0] = new MenuItem(01,01,01,"아메리카노",3500,"케냐",R.drawable.coffee1+"");
        menuitem[1] = new MenuItem(01,01,02,"카페라떼",4000,"케냐","R.drawable.android");
        menuitem[2] = new MenuItem(01,01,03,"바닐라라떼",4500,"브라질","R.drawable.android");
        menuitem[3] = new MenuItem(01,01,05,"블루베리스무디",4500,"없음","R.drawable.android");
        menuitem[4] = new MenuItem(01,01,04,"카라멜마끼아또",4500,"브라질","R.drawable.android");
        menuitem[5] = new MenuItem(01,01,06,"딸기라떼",5500,"시즌메뉴","R.drawable.android");
        menuitem[6] = new MenuItem(01,01,07,"달고나라떼",5000,"브라질","R.drawable.android");


        for (int i=0;i<4;i++){
            simple_data.add(item[i]);
        }
        for (int i=0;i<7;i++){
            menu_data.add(menuitem[i]);
        }

        RecommandMenuorderAdapter adapter = new RecommandMenuorderAdapter(getActivity().getApplicationContext(), R.layout.recommandmenuorder,simple_data);
        adapter.setOnItemClickListener(new RecommandMenuorderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getActivity().getApplicationContext(),"데이터"+menuitem[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), MenuDetailActivity.class);
                intent.putExtra("img",item[position].getImgsource());
                intent.putExtra("menuname",item[position].getProname());
                intent.putExtra("country",item[position].getCountry());
                intent.putExtra("price",item[position].getPrice());
                intent.putExtra("menunum",item[position].getMenunum());
                intent.putExtra("cafeid",intentcafeid);
                intent.putExtra("cafename",intentcafename);
                intent.putExtra("guestphone",intentguestphone);

                startActivityForResult(intent,2);
            }
        });

        adapter2 = new MenulistAdapter(getActivity().getApplicationContext(), R.layout.menulist,menu_data);
        adapter2.setOnItemClickListener(new MenulistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext().getApplicationContext(),"데이터"+menuitem[position],Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getActivity().getApplicationContext(), MenuDetailActivity.class);
                intent2.putExtra("menuname",menuitem[position].getProname());
                intent2.putExtra("country",menuitem[position].getCountry());
                intent2.putExtra("price",menuitem[position].getPrice());
                intent2.putExtra("img",menuitem[position].getImgsource());
                intent2.putExtra("menunum",menuitem[position].getMenunum());
                intent2.putExtra("cafeid",intentcafeid);
                intent2.putExtra("cafename",intentcafename);
                intent2.putExtra("guestphone",intentguestphone);
                startActivityForResult(intent2,1);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity().getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        recommand.setLayoutManager(manager);
        menu.setLayoutManager(manager2);
        recommand.setAdapter(adapter);
        menu.setAdapter(adapter2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shoppingbag!=null){
                    Intent intent2 = new Intent(getActivity().getApplicationContext(), ShoppingActivity.class);
                    intent2.putParcelableArrayListExtra("test",shoppingbag);
                    intent2.putExtra("order",order);
                    intent2.putExtra("orderList",orderList);
                    startActivity(intent2);
                    Log.d("=== ","데이터"+shoppingbag.size());
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("menulist", "onViewCreated: ");
    }

    class HttpTest extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            Log.d("menulist", "doInBackground: ");
            URL url = null;
            BufferedReader in= null;
            String data="";
            try {
                String path = "http://"+getString(R.string.ipaddress)+":8088/cafeoda/menulist.do?cafeid=2";
                url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json");
                if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    Log.d("test1","메시지");
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
            Log.d("menulist", "onPostExecute: ");
            JSONArray ja = null;
            Log.d("myhttp100000",s);
            try{
                ja = new JSONArray(s);
                Log.d("myhttp100000",ja.length()+"");
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
            MenulistAdapter adapter2 = new MenulistAdapter(getActivity().getApplicationContext(), R.layout.menulist,list);
            adapter2.setOnItemClickListener(new MenulistAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(getActivity().getApplicationContext(),"데이터"+ list.get(position),Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getActivity().getApplicationContext(), MenuDetailActivity.class);
                    intent2.putExtra("menuname", list.get(position).getProname());
                    intent2.putExtra("country",list.get(position).getCountry());
                    intent2.putExtra("price",list.get(position).getPrice());
                    intent2.putExtra("img",list.get(position).getImgsource());
                    startActivityForResult(intent2,1);
                }
            });
            menu.setAdapter(adapter2);
            //refreshData();
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
                if(order.getIcehot().trim().equals("HOT")){
                    validate = "HOT";
                }else {
                    validate = "ICE";
                }
                Jangbaguny jangbaguny = new Jangbaguny(order.getPrdname(),validate,order.getCupsize(),order.getQuantity(),order.getOneprice());
                Log.d("===","이름 : "+jangbaguny.getMenuname());
                shoppingbag.add(jangbaguny);
                orderList.add(order);
            }
        }
    }
}
