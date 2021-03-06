package com.l904;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.l904.database.DbController;
import com.l904.database.ParamsUtil;

import java.util.List;

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.VH> {
    private static final String TAG = "CounterAdapter";
    List<ParamsUtil> items;

    public CounterAdapter(List<ParamsUtil> items) {
        this.items = items;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_counter, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        ParamsUtil p = items.get(position);
        holder.textView.setText(p.getName_date_des() + "(" + p.getColor_amount_target() + ")");
        holder.p = p;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView textView;
        ParamsUtil p;

        public VH(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.info_text);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int c = Integer.parseInt(p.getColor_amount_target()) + 1;
                        DbController.getInstace().editExpense(p.getName_date_des(), String.valueOf(c), String.valueOf(ParamsUtil.TYPE_COUNTER), p.getId());
                    p.setColor_amount_target(String.valueOf(c));
                        notifyItemChanged(getAdapterPosition());
                    } catch (Exception ex) {
                        Log.e(TAG, ex.getMessage() + "");
                    }
                }
            });

            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    try {
                        int c = Integer.parseInt(p.getColor_amount_target()) - 1;
                        DbController.getInstace().editExpense(p.getName_date_des(), String.valueOf(c), String.valueOf(ParamsUtil.TYPE_COUNTER), p.getId());
                        p.setColor_amount_target(String.valueOf(c));
                        notifyItemChanged(getAdapterPosition());
                    } catch (Exception ex) {
                        Log.e(TAG, ex.getMessage() + "");
                    }
                    return true;
                }
            });
        }
    }
}
