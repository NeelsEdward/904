package com.l904;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.l904.database.DbController;
import com.l904.database.ParamsUtil;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewItemActivityFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "NewItemActivityFragment";
    private EditText nameEditText, numEditText;
    private TextView largeText;
    private int type;
    private RadioGroup radioGroup;
    private OnInteractionListener listener;

    public NewItemActivityFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = ((OnInteractionListener) getActivity());
        type = listener.getType();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.fragment_new_item, container, false);
        nameEditText = (EditText) v.findViewById(R.id.editText);
        numEditText = (EditText) v.findViewById(R.id.editText2);

        largeText = (TextView) v.findViewById(R.id.textView);
        largeText.setText("Add " + ParamsUtil.TYPES[type]);

        v.findViewById(R.id.button).setOnClickListener(this);
        radioGroup= (RadioGroup) v.findViewById(R.id.radioGrp);
        //todo handle for all of them
        if(type!=ParamsUtil.TYPE_TODO) radioGroup.setVisibility(View.INVISIBLE);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //todo handle for all of them
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(ParamsUtil.TYPES[type]);
        } catch (NullPointerException e) {
            Log.e(TAG, " " + e.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        nameEditText.setText("");
        if(name.equals("")) return;

        String numS = numEditText.getText().toString();
        Integer num = 0;
        try {
            num = Integer.parseInt(numS);
        } catch (Exception ex) {
        }
        numEditText.setText("");

        DbController dbController= DbController.getInstace();

        dbController.openDb(getActivity());
        switch (type){
            case ParamsUtil.TYPE_EXPENSE://todo
                dbController.insertExpense(name, String.valueOf(num), String.valueOf(ParamsUtil.TYPE_EXPENSE));
                //Toast.makeText(getActivity(), "Inserted. Total rows=" + dbController.getAllTodoExpense(ParamsUtil.TYPE_EXPENSE).size(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Inserted. Total rows=" + dbController.getAllTodoExpense(ParamsUtil.TYPE_EXPENSE).size());
                listener.success();
                break;
            case ParamsUtil.TYPE_COUNTER://todo
                dbController.insertExpense(name, String.valueOf(num), String.valueOf(ParamsUtil.TYPE_COUNTER));
                //Toast.makeText(getActivity(), "Inserted. Total rows=" + dbController.getAllTodoExpense(ParamsUtil.TYPE_EXPENSE).size(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Inserted. Total rows=" + dbController.getAllTodoExpense(ParamsUtil.TYPE_COUNTER).size());
                listener.success();
                break;
        }
    }

    interface OnInteractionListener{
        int getType();

        void success();
    }
}
