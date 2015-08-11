package com.l904;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.l904.database.DbController;
import com.l904.database.ParamsUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DbController dbController;
    private DrawerLayout Drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private TextView expense,todo,counters;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ParamsUtil> myDataTodoLists;
    private ArrayList<ParamsUtil> myDataTodoExpenses;
    private ArrayList<ParamsUtil> myDataTodoCounters;



    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);

        dbController = DbController.getInstace();
        dbController.openDb(this);
        dbController.insertTodo("Test", "COLOR");
        dbController.insertTodo("Test1", "COLOR1");
        dbController.insertTodo("Test2", "COLOR2");
        myDataTodoLists = dbController.getAllTodoExpense(ParamsUtil.TYPE_TODO);//todo load from worker thread.startup is slow.
        myDataTodoExpenses=dbController.getAllTodoExpense(ParamsUtil.TYPE_EXPENSE);
        myDataTodoCounters=dbController.getAllTodoExpense(ParamsUtil.TYPE_COUNTER);

        Log.d("neels", "mydataset " + myDataTodoLists.size());
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ExpenseAdapter(myDataTodoLists,this);
        mRecyclerView.setAdapter(mAdapter);


        expense = (TextView)Drawer.findViewById(R.id.expense);
        expense.setOnClickListener(mDrawerItemsClickListener);
        todo = (TextView)Drawer.findViewById(R.id.todo);
        todo.setOnClickListener(mDrawerItemsClickListener);
        counters=(TextView) Drawer.findViewById(R.id.counter);
        counters.setOnClickListener(mDrawerItemsClickListener);



        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(final View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }

        }; // Drawer Toggle Object Made

        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
       /* dbController = DbController.getInstace();
        dbController.openDb(this);
        dbController.insertExpense("Neels Exp", "100Rs",String.valueOf(ParamsUtil.TYPE_EXPENSE));
        dbController.insertExpense("Neels Exp", "100Rs",String.valueOf(ParamsUtil.TYPE_EXPENSE));
        dbController.insertExpense("Me failed", "1",String.valueOf(ParamsUtil.TYPE_COUNTER));
        dbController.insertExpense("Me failed", "1",String.valueOf(ParamsUtil.TYPE_COUNTER));
        dbController.insertTodo("Shopping", "Market");
        dbController.insertTodo("Shopping","Market");
        dbController.insertItem("sugar","milk",1);
        dbController.deleteExpense(2);
        dbController.editExpense("me pass","yeah","GG",4);
        Utils.copyDBToExternal(this);*/

    }

    View.OnClickListener mDrawerItemsClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==todo.getId()){
                mAdapter = new ExpenseAdapter(myDataTodoLists,v.getContext());
                mRecyclerView.setAdapter(mAdapter);
            }else if(v.getId()==expense.getId()){
                mAdapter = new MyTodoAdapter(myDataTodoLists,v.getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            Drawer.closeDrawer(Gravity.LEFT);
            Toast.makeText(v.getContext(), ((TextView)v).getText().toString(),Toast.LENGTH_SHORT).show();

        }
    };
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id==R.id.action_new){
            //starting NewItemActivty
            Intent i = new Intent(this, NewItemActivity.class);
            i.putExtra(ParamsUtil.TYPE, ParamsUtil.TYPE_EXPENSE);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
