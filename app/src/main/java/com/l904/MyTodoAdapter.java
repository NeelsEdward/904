package com.l904;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.l904.database.ParamsUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Dell Laptop on 8/10/2015.
 */
public class MyTodoAdapter extends RecyclerView.Adapter<MyTodoAdapter.ViewHolder> {
    private ArrayList<ParamsUtil> list;
    private Context context;
    public MyTodoAdapter(ArrayList<ParamsUtil> list,Context c){
        this.list = list;
        this.context = c;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textview;
        public ViewHolder(View itemView) {
            super(itemView);
            textview = (TextView)itemView.findViewById(R.id.todo_text);
        }
    }

    public void add(int position, ParamsUtil item) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(ParamsUtil item) {
        int position = list.indexOf(item);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todolayout,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
            final String todo = list.get(i).getName_date_des();
            viewHolder.textview.setText(todo);
        viewHolder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Clicked "+todo,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
