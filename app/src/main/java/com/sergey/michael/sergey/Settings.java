package com.sergey.michael.sergey;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sergey.michael.sergey.Engine.Util.Toolbox;

public class Settings extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbox.setupNavigation(this,this,"Settings");
        Toolbox.activiateFullscreen(this);

        LinearLayout layout = findViewById(R.id.settings_layout);

        View reset_inventory = LayoutInflater.from(this)
                .inflate(R.layout.card_item, layout, false);
        TextView name = reset_inventory.findViewById(R.id.name);
        TextView description = reset_inventory.findViewById(R.id.description);
        TextView inventory = reset_inventory.findViewById(R.id.inventory);
        TextView cost = reset_inventory.findViewById(R.id.cost);
        name.setText(R.string.reset_inventory);
        description.setText("");
        inventory.setText("");
        cost.setText("");
        layout.addView(reset_inventory);


        View reset_score = LayoutInflater.from(this)
                .inflate(R.layout.card_item, layout, false);
        name = reset_score.findViewById(R.id.name);
        description = reset_score.findViewById(R.id.description);
        inventory =  reset_score.findViewById(R.id.inventory);
        cost = reset_score.findViewById(R.id.cost);
        name.setText(R.string.reset_score);
        description.setText("");
        inventory.setText("");
        cost.setText("");
        layout.addView(reset_score);


        reset_inventory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences(
                        getString(R.string.servey_preference_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor;
                editor = sharedPref.edit();
                editor.putInt(getString(R.string.item1_key), 0);
                editor.putInt(getString(R.string.item2_key), 0);
                editor.putInt(getString(R.string.item3_key), 0);
                editor.putInt(getString(R.string.item4_key), 0);
                editor.putInt(getString(R.string.item5_key), 0);
                editor.putInt(getString(R.string.item6_key), 0);
                editor.putInt(getString(R.string.item7_key), 0);
                editor.putInt(getString(R.string.item8_key), 0);
                editor.putInt(getString(R.string.items_key), 0);
                editor.apply();
            }
        });
        reset_score.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences(
                        getString(R.string.servey_preference_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor;
                editor = sharedPref.edit();
                editor.putInt(getString(R.string.score_key), 0);
                editor.apply();
            }
        });




    }

}
