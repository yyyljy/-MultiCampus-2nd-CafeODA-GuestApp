
package com.example.cafeoda.ShoppingList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeoda.R;

import java.util.List;



public class ShoppinglistAdapter extends RecyclerView.Adapter<ShoppinglistAdapter.ViewHolder> {
    Context context;
    int row_res_id;

    List<MyShoppingItem> data;

    public ShoppinglistAdapter(Context context, int row_res_id, List<MyShoppingItem> data) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(row_res_id,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView row_image_view = holder.imgview;
        TextView row_text_view_name = holder.txtview_coffeename;
        TextView row_text_view_icehot = holder.txtview_icehot;
        TextView row_text_view_amount = holder.txtview_amount;

        row_image_view.setImageResource(data.get(position).getcafeimg());
        row_text_view_name.setText(data.get(position).getCoffeename());
        row_text_view_icehot.setText(data.get(position).getIcehot());
        row_text_view_amount.setText(data.get(position).getAmount()+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgview; //layoutinflater에 있는 것
        TextView txtview_coffeename;
        TextView txtview_icehot;
        TextView txtview_amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview = itemView.findViewById(R.id.cafeImg);
            txtview_coffeename = itemView.findViewById(R.id.coffeename);
            txtview_icehot =  itemView.findViewById(R.id.icehot);
            txtview_amount =  itemView.findViewById(R.id.amount);

        }
    }




}

