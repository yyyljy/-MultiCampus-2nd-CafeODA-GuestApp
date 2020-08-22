package com.example.cafeoda.MenuList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeoda.R;

import java.util.ArrayList;

public class MenulistAdapter extends RecyclerView.Adapter<MenulistAdapter.ViewHoler> {
    Context context;
    int row_res_id;
    ArrayList<MenuItem> data;

    public MenulistAdapter(Context context, int row_res_id, ArrayList<MenuItem> data) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(row_res_id,null);
        return new ViewHoler(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        MenuItem item = data.get(position);
        TextView sel_menu_name = holder.sel_menu_name;
        TextView coffee = holder.coffee;
        TextView price = holder.price;

        sel_menu_name.setText(data.get(position).getProname());
        coffee.setText(data.get(position).getCountry());
        price.setText(data.get(position).getPrice()+"");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHoler extends RecyclerView.ViewHolder{
        TextView sel_menu_name;
        TextView coffee;
        TextView price;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            sel_menu_name = itemView.findViewById(R.id.sel_menu_name);
            coffee =  itemView.findViewById(R.id.coffee);
            price =  itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        MenuItem item = data.get(pos);
                        if(mListener!= null){
                            mListener.onItemClick(v,pos);
                        }
                    }}
            });

        }
    }
    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }
    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
}
