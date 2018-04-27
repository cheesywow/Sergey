package com.sergey.michael.sergey;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sergey.michael.sergey.Engine.UI.cards.CardAdapter;
import com.sergey.michael.sergey.Engine.UI.cards.ReminderCard;
import com.sergey.michael.sergey.Engine.Util.Toolbox;
import com.sergey.michael.sergey.Engine.Util.listeners.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ReminderCard> cardList = new ArrayList<>();
    private CardAdapter mAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Toolbox.setupNavigation(this,this, "Shop");
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mAdapter = new CardAdapter(cardList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //ReminderCard card = cardList.get(position);
                Toast.makeText(getBaseContext(), (position+1) + " is selected!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.servey_preference_file), Context.MODE_PRIVATE);

        int item1  = sharedPref.getInt(getString(R.string.item1_key), 0);
        int item2  = sharedPref.getInt(getString(R.string.item1_key), 0);
        int item3  = sharedPref.getInt(getString(R.string.item1_key), 0);
        int item4  = sharedPref.getInt(getString(R.string.item1_key), 0);
        int item5  = sharedPref.getInt(getString(R.string.item1_key), 0);
        int item6  = sharedPref.getInt(getString(R.string.item1_key), 0);
        int item7  = sharedPref.getInt(getString(R.string.item1_key), 0);
        int item8  = sharedPref.getInt(getString(R.string.item1_key), 0);



        String[] strings = new String[4];
        strings[0] = "Poker";   strings[1] = "1 point per second";
        strings[2] = "10";      strings[3] = ""+item1;
        makeReminder(this, strings);

        strings[0] = "Smacker"; strings[1] = "3 point per second";
        strings[2] = "25";      strings[3] = ""+item2;
        makeReminder(this, strings);

        strings[0] = "Puncher"; strings[1] = "5 point per second";
        strings[2] = "100";     strings[3] = ""+item3;
        makeReminder(this, strings);

        strings[0] = "Beater";  strings[1] = "10 points per second";
        strings[2] = "500";     strings[3] = ""+item4;
        makeReminder(this, strings);

        strings[0] = "Shocker"; strings[1] = "25 points per second";
        strings[2] = "2000";    strings[3] = ""+item5;
        makeReminder(this, strings);

        strings[0] = "Crusher"; strings[1] = "60 points per second";
        strings[2] = "7000";    strings[3] = ""+item6;
        makeReminder(this, strings);

        strings[0] = "Ripper";  strings[1] = "150 points per second";
        strings[2] = "30000";   strings[3] = ""+item7;
        makeReminder(this, strings);

        strings[0] = "Shredder"; strings[1] = "400 points per second";
        strings[2] = "500000";  strings[3] = ""+item8;
        makeReminder(this, strings);

    }

    @Override
    public void onResume() {
        super.onResume();
        Toolbox.activiateFullscreen(this);
    }

    public void makeReminder(Activity activity, String[] strings){
        ReminderCard card = new ReminderCard(activity);
        card.makeCard(strings);
        cardList.add(card);
        mAdapter.notifyDataSetChanged();
        //layout.addView(card,linear);
    }
}
