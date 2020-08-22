package com.example.cafeoda.Mainpage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeoda.R;

import java.util.List;


public class RecycleCardAdapter2 extends RecyclerView.Adapter<RecycleCardAdapter2.ViewHolder> {
    Context context;
    int row_res_id;
    List<MainListDTO> data;
    ItemClick mainView;

    public RecycleCardAdapter2(Context context, int row_res_id, List<MainListDTO> data, ItemClick mainView) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
        this.mainView = mainView;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(row_res_id, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleCardAdapter2.ViewHolder holder, final int position) {
        ImageView imageView = holder.imageView;
        TextView textView = holder.textView;

        imageView.setImageResource(data.get(position).getImg());

        textView.setText(data.get(position).getCafename());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.onClick("shop",data.get(position).getCafeid());
                Log.d("테스트어덥터3","onclick3");
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

        }


    }
}
