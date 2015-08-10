package com.l904;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.l904.database.ParamsUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swaroop on 8/10/2015.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder>{
    private final Context mContext;
    List<ParamsUtil> expences = null;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mExpenseName;
        public TextView mExpenseValue;
        public View mContainer;
        public ViewHolder(View view)
        {
            super(view);
            mContainer = view;
            mExpenseName = (TextView) view.findViewById(R.id.expense_name);
            mExpenseValue = (TextView) view.findViewById(R.id.expense_value);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mExpenseName.setText(expences.get(position).getName_date_des());
        holder.mExpenseValue.setText(expences.get(position).getColor_amount_target());
        if(position%2 == 0)
        {
            holder.mContainer.setBackgroundColor(mContext.getResources().getColor(R.color.orangedeep));
            holder.mExpenseName.setTextColor(mContext.getResources().getColor(R.color.bright_foreground_material_dark));
            holder.mExpenseValue.setTextColor(mContext.getResources().getColor(R.color.bright_foreground_material_dark));
        }
        else
        {
            holder.mContainer.setBackgroundColor(mContext.getResources().getColor(R.color.button_material_light));
            holder.mExpenseName.setTextColor(mContext.getResources().getColor(R.color.bright_foreground_material_light));
            holder.mExpenseValue.setTextColor(mContext.getResources().getColor(R.color.bright_foreground_material_light));
        }

    }

    public ExpenseAdapter(List<ParamsUtil> expences,Context context)
    {
        this.expences = expences;
        this.mContext = context;
    }


    @Override
    public int getItemCount() {
        return expences.size();
    }
}
