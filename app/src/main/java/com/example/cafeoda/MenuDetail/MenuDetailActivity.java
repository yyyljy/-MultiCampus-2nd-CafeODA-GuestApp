package com.example.cafeoda.MenuDetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafeoda.Mainpage.MainPageActivity;
import com.example.cafeoda.R;
import com.example.cafeoda.WishList.OrderDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuDetailActivity extends AppCompatActivity {
    RadioGroup tempgroup;
    RadioGroup sizegroup;
    RadioButton hot;
    RadioButton ice;
    RadioButton tall;
    RadioButton grande;
    RadioButton venti;
    FloatingActionButton add;
    FloatingActionButton minus;
    TextView count;
    Button btn;
    CircleImageView detailimg;
    TextView detailname;
    TextView detailinfo;
    TextView detailmenunum;

    Date time = new Date();
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

    String ordnum = yearFormat.format(time)+monthFormat.format(time)+dayFormat.format(time);


    String[] menu = {"","tall",1+"",0+""};

    OrderDTO order = new OrderDTO();
    ArrayList<OrderDTO> orderList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_menu);
        tempgroup = (RadioGroup) findViewById(R.id.tempgroup);
        sizegroup = (RadioGroup) findViewById(R.id.sizegroup);
        hot = (RadioButton) findViewById(R.id.radio_HOT);
        ice = (RadioButton) findViewById(R.id.radio_ICE);
        tall = (RadioButton) findViewById(R.id.tall);
        grande = (RadioButton) findViewById(R.id.grande);
        venti = (RadioButton) findViewById(R.id.venti);
        add = (FloatingActionButton) findViewById(R.id.count_add);
        minus = (FloatingActionButton) findViewById(R.id.conut_minus);
        count = findViewById(R.id.order_count);
        btn = findViewById(R.id.containbtn);
        detailimg = findViewById(R.id.menudetailimg);
        detailname = findViewById(R.id.menudetailname);
        detailinfo = findViewById(R.id.menudetailinfo);
        detailmenunum = findViewById(R.id.menudetailmenunum);


        final Intent intent = getIntent();
        detailimg.setImageResource(intent.getExtras().getInt("img"));
        detailname.setText(intent.getExtras().getString("menuname"));
        detailinfo.setText(intent.getExtras().getString("country"));
        detailmenunum.setText(intent.getExtras().getInt("menunum")+"");
        final int price = intent.getExtras().getInt("price");

        order.setCafeid(intent.getExtras().getInt("cafeid"));
        order.setCafename(intent.getExtras().getString("cafename"));
        order.setCountry(intent.getExtras().getString("country"));
        order.setGuestphone(intent.getExtras().getString("guestphone"));
        order.setMenunum(intent.getExtras().getInt("menunum"));
        order.setOneprice(price);
        order.setOrdnum(ordnum+intent.getExtras().getString("guestphone"));
        order.setPrdname(intent.getExtras().getString("menuname"));
        //order.setQuantity();
        //order.setIcehot();
       // order.setCupsize(intent.getExtras().getString(""));



        menu[3] = price+"";
        count.setText("1");
        tempgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_HOT :
                        Toast.makeText(getApplicationContext(),"HOT",Toast.LENGTH_SHORT).show();
                        menu[0] ="HOT";
                        break;
                    case R.id.radio_ICE :

                        Toast.makeText(getApplicationContext(),"ICE",Toast.LENGTH_SHORT).show();
                        menu[0] ="ICE";
                        break;
                }
            }
        });
        sizegroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tall :
                        Toast.makeText(getApplicationContext(),"tall",Toast.LENGTH_SHORT).show();
                        menu[1] ="tall";
                        menu[3] = (intent.getExtras().getInt("price")+0)+"";
                        break;
                    case R.id.grande:
                        Toast.makeText(getApplicationContext(),"grande",Toast.LENGTH_SHORT).show();
                        menu[1] ="grande";
                        menu[3] = (price+500)+"";
                        break;
                    case R.id.venti:
                        Toast.makeText(getApplicationContext(),"venti",Toast.LENGTH_SHORT).show();
                        menu[1] ="venti";
                        menu[3] = (price+1000)+"";
                        break;
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count_num =Integer.parseInt(count.getText()+"");
                count.setText((count_num+1)+"");
                menu[2] = count.getText()+"";

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count_num =Integer.parseInt(count.getText()+"");
                if(count_num>1){
                    count.setText((count_num-1)+"");
                    menu[2] = count.getText()+"";
                }
                menu[2] = count.getText()+"";
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"선택 결과 :"+menu[0]+","+menu[1]+", 갯수 :"+menu[2],Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), MainPageActivity.class);
                int price = Integer.parseInt(menu[3]);
                int quantity = Integer.parseInt(menu[2]);
                order.setCupsize(menu[1]);
                order.setMenunum(Integer.parseInt(detailmenunum.getText().toString()));
                order.setPrdname(detailname.getText().toString());
                order.setIcehot(menu[0]);
                order.setQuantity(quantity);
                order.setOneprice(price);
                orderList.add(order);
                intent1.putExtra("order",order);
                intent1.putExtra("orderList",orderList);
                Log.d("===","hot?ice?:"+order.getIcehot());
                Log.d("===","Put Order in Intent" + order.toString());
                Log.d("===","Put Order in Intent" + orderList.toString());
                setResult(RESULT_OK,intent1);
                finish();
            }
        });
    }

}
