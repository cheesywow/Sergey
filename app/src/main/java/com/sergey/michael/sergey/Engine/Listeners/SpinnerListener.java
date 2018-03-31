package com.sergey.michael.sergey.Engine.Listeners;

import android.view.View;
import android.widget.AdapterView;

import com.sergey.michael.sergey.Engine.Util.Toolbox;


public class SpinnerListener implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toolbox.approach = (String) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}