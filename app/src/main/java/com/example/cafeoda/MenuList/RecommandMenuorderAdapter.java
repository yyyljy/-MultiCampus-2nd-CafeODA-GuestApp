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

import de.hdodenhof.circleimageview.CircleImageView;

public class RecommandMenuorderAdapter extends RecyclerView.Adapter<RecommandMenuorderAdapter.ViewHoler> {
    Context context;
    int row_res_id;
    ArrayList<RecommandMenuItem> data;

    public RecommandMenuorderAdapter(Context context, int row_res_id, ArrayList<RecommandMenuItem> data) {
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
        RecommandMenuItem item = data.get(position);
        CircleImageView img = holder.img;
        TextView txt = holder.txt;
        img.setImageResource((data.get(position).getImgsource()));
        txt.setText(data.get(position).getProname());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHoler extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView txt;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.circlemenuimg3);
            txt =  itemView.findViewById(R.id.menuname3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        RecommandMenuItem item = data.get(pos);
                        if(mListener!= null){
                            mListener.onItemClick(v,pos);
                        }
                    }
                }
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