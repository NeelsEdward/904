package com.l904;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewItemActivityFragment extends Fragment implements View.OnClickListener {

    private EditText nameEditText, numEditText;

    public NewItemActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_item, container, false);
        nameEditText = (EditText) v.findViewById(R.id.editText);
        numEditText = (EditText) v.findViewById(R.id.editText2);
        v.findViewById(R.id.button).setOnClickListener(this);
        setRetainInstance(true);
        return v;
    }

    @Override
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        nameEditText.setText("");

        String numS = numEditText.getText().toString();
        Integer num = numS.equals("")?0:Integer.parseInt(numS);
        numEditText.setText("");

    }
}
