package com.example.cafeoda.OrderAlertList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeoda.BuyHistoryList.BuyHistroyDTO;
import com.example.cafeoda.R;

import java.util.List;

public class AlertHistroyAdapter extends RecyclerView.Adapter<AlertHistroyAdapter.ViewHolder> {
    Context context;
    int row_res_id;
    List<AlertHistoryDTO> data;

    public AlertHistroyAdapter(Context context, int row_res_id, List<AlertHistoryDTO> data) {
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
        TextView orderdate = holder.orderdate;
        TextView cafename = holder.cafename;
        TextView prdname = holder.prdname;
        TextView statusmsg = holder.statusmsg;

        /*cafeid.setText(data.get(position).getCafeid()+"");*/

        prdname.setText(data.get(position).getPrdname());
        orderdate.setText(data.get(position).getOrderdate());
        cafename.setText(data.get(position).getCafename());
        statusmsg.setText(data.get(position).getStatusmsg());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView orderdate;
        TextView cafename;
        TextView prdname;
        TextView statusmsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderdate = itemView.findViewById(R.id.Txt_alert_ordertime);
            cafename = itemView.findViewById(R.id.Txt_alert_cafename);
            prdname = itemView.findViewById(R.id.Txt_alert_prodname);
            statusmsg = itemView.findViewById(R.id.Txt_alert_statusmsg);

        }
    }
}
