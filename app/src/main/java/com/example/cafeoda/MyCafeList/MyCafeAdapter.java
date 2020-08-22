package com.example.cafeoda.MyCafeList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeoda.Mainpage.ItemClick;
import com.example.cafeoda.R;

import java.util.List;


public class MyCafeAdapter extends RecyclerView.Adapter<MyCafeAdapter.ViewHolder> {
    Context context;
    int row_res_id;
    List<MyCafeVO> data;
    ItemClick mainView;

    public MyCafeAdapter(Context context, int row_res_id, List<MyCafeVO> data, ItemClick mainView) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(row_res_id,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ImageView row_image_view = holder.imgview;
        TextView row_text_view = holder.txtview;
        //row_image_view.setImageResource(data.get(position).getcafeimg());
        row_text_view.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test",v.toString());
                mainView.onClick("shop",data.get(position).getcafeid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgview; //layoutinflater에 있는 것
        TextView txtview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview = itemView.findViewById(R.id.imageView);
            txtview = itemView.findViewById(R.id.textView);

            //itemView로부터 찾아온다.
        }
    }




}
