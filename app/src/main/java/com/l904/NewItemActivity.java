package com.l904;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.l904.database.DbController;
import com.l904.database.ParamsUtil;

public class NewItemActivity extends AppCompatActivity implements NewItemActivityFragment.OnInteractionListener {
    int type;
    public static final String TAG="NewItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type=getIntent().getIntExtra(ParamsUtil.TYPE,0);
        if(type==0) {
            Log.e(TAG,"this activity needs int extra with name ParamsUtil.TYPE, with value >0");
            finish();
        }
        Log.e(TAG, getIntent().getIntExtra("type", 0) + "");
        setContentView(R.layout.activity_new_item);
        //TODO fix this. currently its throwing error
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_item, menu);
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

        return super.onOptionsItemSelected(item);
    }
    @Override
    public int getType() {
        return type;
    }

}
