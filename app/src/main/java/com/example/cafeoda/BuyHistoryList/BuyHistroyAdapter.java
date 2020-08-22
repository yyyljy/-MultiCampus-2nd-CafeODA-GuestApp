package com.example.cafeoda.BuyHistoryList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeoda.R;

import java.util.List;

public class BuyHistroyAdapter extends RecyclerView.Adapter<BuyHistroyAdapter.ViewHolder> {
    Context context;
    int row_res_id;
    List<BuyHistroyDTO> data;

    public BuyHistroyAdapter(Context context, int row_res_id, List<BuyHistroyDTO> data) {
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
        TextView ordernum = holder.ordernum;
        TextView orderdate = holder.orderdate;
        TextView cafename = holder.cafename;
        TextView prdname = holder.prdname;
        TextView quantity = holder.quantity;
        TextView icehot = holder.icehot;
        TextView ordersize = holder.ordersize;
        TextView oneprice = holder.oneprice;
        //TextView totalprice = holder.totalprice;
        TextView statusmsg = holder.statusmsg;

        /*cafeid.setText(data.get(position).getCafeid()+"");*/
        ordernum.setText(data.get(position).getOrdnum()+"");
        prdname.setText(data.get(position).getPrdname());
        orderdate.setText(data.get(position).getOrderdate());
        cafename.setText(data.get(position).getCafename());
        quantity.setText(data.get(position).getQuantity()+"");
        icehot.setText(data.get(position).getIcehot());
        ordersize.setText(data.get(position).getCupsize());
        oneprice.setText((data.get(position).getOneprice()*data.get(position).getQuantity())+"");
        statusmsg.setText(data.get(position).getStatusmsg());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ordernum;
        TextView orderdate;
        TextView cafename;
        TextView prdname;
        TextView quantity;
        TextView icehot;
        TextView ordersize;
        TextView oneprice;
        //TextView totalprice;
        TextView statusmsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ordernum = itemView.findViewById(R.id.order_num);
            orderdate = itemView.findViewById(R.id.order_date);
            cafename = itemView.findViewById(R.id.order_cafename);
            prdname = itemView.findViewById(R.id.order_prdname);
            quantity = itemView.findViewById(R.id.order_quantity);
            icehot = itemView.findViewById(R.id.order_icehot);
            ordersize = itemView.findViewById(R.id.order_size);
            oneprice = itemView.findViewById(R.id.order_totalprice);
            statusmsg = itemView.findViewById(R.id.order_statusmsg);

        }
    }
}
