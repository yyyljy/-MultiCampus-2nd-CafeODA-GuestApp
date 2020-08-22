package com.example.cafeoda.ShopMain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cafeoda.Mainpage.MainPageActivity;
import com.example.cafeoda.Mainpage.MainListDTO;
import com.example.cafeoda.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopMainInfoFragment extends Fragment {

    private MainListDTO entercafedto;

    TextView addressTxt;
    TextView weekday_opentime;
    TextView weekday_closetime;
    TextView weekend_opentime;
    TextView weekend_closetime;
    TextView cafetel;


    public ShopMainInfoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_main_info, container, false);
        entercafedto =((MainPageActivity)getActivity()).tempentercafedto;

        Log.d("===","온크리에이트뷰" + entercafedto);
        addressTxt = view.findViewById(R.id.Txt_cafeAddress);
        weekday_opentime= view.findViewById(R.id.Txt_weekday_open);
        weekday_closetime= view.findViewById(R.id.Txt_weekday_close);
        weekend_opentime = view.findViewById(R.id.Txt_weekend_open);
        weekend_closetime = view.findViewById(R.id.Txt_weekend_close);
        cafetel = view.findViewById(R.id.Txt_cafetel);

        addressTxt.setText(entercafedto.getAddress());
        Log.d("===","getaddress:"+entercafedto.getAddress());
        weekday_opentime.setText(entercafedto.getWeekday_opentime());
        weekday_closetime.setText(entercafedto.getWeekday_closetime());
        weekend_opentime.setText(entercafedto.getWeekend_opentime());
        weekend_closetime.setText(entercafedto.getWeekend_closetime());
        cafetel.setText(entercafedto.getTel());


        return view;
    }
}
