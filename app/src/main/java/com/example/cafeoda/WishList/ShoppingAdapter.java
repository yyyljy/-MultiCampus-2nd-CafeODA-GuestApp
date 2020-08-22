package com.example.cafeoda.WishList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cafeoda.R;

import java.util.ArrayList;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHoler>
implements ItemTouchHelperListener{
    Context context;
    int row_res_id;
    ArrayList<Jangbaguny> data;

    public ShoppingAdapter(Context context, int row_res_id, ArrayList<Jangbaguny> data) {
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
        Jangbaguny item =  data.get(position);
        TextView sel_menu_name = holder.shoppingmenu;
        TextView temp = holder.temp;
        TextView size = holder.size;
        TextView count = holder.count;

        Log.d("===","data.get(position).getIceable():"+data.get(position).getIceable());
        sel_menu_name.setText(data.get(position).getMenuname());
        if (data.get(position).getIceable()=="0"){
            temp.setText("HOT");
        }else{
            temp.setText("ICE");
        }
        temp.setText(data.get(position).getIceable());
        size.setText(data.get(position).getSize());
        count.setText(data.get(position).getQuantity()+"");
        Log.d("===","temp.getTxt?"+temp.getText());
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        return false;
    }

    @Override
    public void onItemSwipe(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHoler extends RecyclerView.ViewHolder{
        TextView shoppingmenu;
        TextView temp;
        TextView size;
        TextView count;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            shoppingmenu = itemView.findViewById(R.id.shoppingmenu);
            temp =  itemView.findViewById(R.id.temp);
            size =  itemView.findViewById(R.id.size);
            count =  itemView.findViewById(R.id.count);

        }
    }
}
