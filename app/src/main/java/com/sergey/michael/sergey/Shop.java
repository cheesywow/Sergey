package com.sergey.michael.sergey;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;
import com.sergey.michael.sergey.Engine.UI.cards.CardAdapter;
import com.sergey.michael.sergey.Engine.UI.cards.ReminderCard;
import com.sergey.michael.sergey.Engine.Util.Toolbox;
import com.sergey.michael.sergey.Engine.Util.listeners.RecyclerTouchListener;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {

    private List<ReminderCard> cardList = new ArrayList<>();
    private CardAdapter mAdapter;
    int score;

    ReminderCard[] cards = new ReminderCard[8];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Toolbox.setupNavigation(this,this, "Shop");
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);

        mAdapter = new CardAdapter(this,cardList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //ReminderCard card = cardList.get(position);
                // Toast.makeText(getBaseContext(), (position+1) + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.servey_preference_file), Context.MODE_PRIVATE);


        int inventory;

        String[] strings = new String[4];
        inventory  = sharedPref.getInt(getString(R.string.item1_key), 0);
        strings[0] = "Poker";   strings[1] = "1 point per second";
        strings[2] = "10";      strings[3] = ""+inventory;
        cards[0] = makeReminder(this, strings);

        inventory  = sharedPref.getInt(getString(R.string.item2_key), 0);
        strings[0] = "Smacker"; strings[1] = "3 point per second";
        strings[2] = "25";      strings[3] = ""+inventory;
        cards[1] = makeReminder(this, strings);

        inventory  = sharedPref.getInt(getString(R.string.item3_key), 0);
        strings[0] = "Puncher"; strings[1] = "5 point per second";
        strings[2] = "100";     strings[3] = ""+inventory;
        cards[2] = makeReminder(this, strings);

        inventory  = sharedPref.getInt(getString(R.string.item4_key), 0);
        strings[0] = "Beater";  strings[1] = "10 points per second";
        strings[2] = "500";     strings[3] = ""+inventory;
        cards[3] = makeReminder(this, strings);

        inventory  = sharedPref.getInt(getString(R.string.item5_key), 0);
        strings[0] = "Shocker"; strings[1] = "25 points per second";
        strings[2] = "2000";    strings[3] = ""+inventory;
        cards[4] = makeReminder(this, strings);

        inventory  = sharedPref.getInt(getString(R.string.item6_key), 0);
        strings[0] = "Crusher"; strings[1] = "60 points per second";
        strings[2] = "7000";    strings[3] = ""+inventory;
        cards[5] = makeReminder(this, strings);

        inventory  = sharedPref.getInt(getString(R.string.item7_key), 0);
        strings[0] = "Ripper";  strings[1] = "150 points per second";
        strings[2] = "30000";   strings[3] = ""+inventory;
        cards[6] = makeReminder(this, strings);

        inventory  = sharedPref.getInt(getString(R.string.item8_key), 0);
        strings[0] = "Shredder"; strings[1] = "400 points per second";
        strings[2] = "500000";  strings[3] = ""+inventory;
        cards[7] = makeReminder(this, strings);


    }

    @Override
    public void onResume() {
        super.onResume();
        Toolbox.activiateFullscreen(this);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.servey_preference_file), Context.MODE_PRIVATE);
        TextView tv = findViewById(R.id.shop_score);
        score  = sharedPref.getInt(getString(R.string.score_key), 0);
        tv.setText(MessageFormat.format("Score: {0}", score));
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences(
                        getString(R.string.servey_preference_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sharedPref.edit();
        editor.putInt(getString(R.string.item1_key), Integer.parseInt(cardList.get(0).inventory));
        editor.putInt(getString(R.string.item2_key), Integer.parseInt(cardList.get(1).inventory));
        editor.putInt(getString(R.string.item3_key), Integer.parseInt(cardList.get(2).inventory));
        editor.putInt(getString(R.string.item4_key), Integer.parseInt(cardList.get(3).inventory));
        editor.putInt(getString(R.string.item5_key), Integer.parseInt(cardList.get(4).inventory));
        editor.putInt(getString(R.string.item6_key), Integer.parseInt(cardList.get(5).inventory));
        editor.putInt(getString(R.string.item7_key), Integer.parseInt(cardList.get(6).inventory));
        editor.putInt(getString(R.string.item8_key), Integer.parseInt(cardList.get(7).inventory));
        int items =0;
        for(int i = 0; i< cardList.size(); i++){
           items += Integer.parseInt(cardList.get(i).inventory);
        }
        editor.putInt(getString(R.string.items_key), items);
        TextView tv = findViewById(R.id.shop_score);
        String display = tv.getText().toString();
        String trunc = display.split(" ")[1];
        String score = trunc.replaceAll(",", "");
        editor.putInt(getString(R.string.score_key), Integer.parseInt(score));
        editor.apply();
    }

    public ReminderCard makeReminder(Activity activity, String[] strings){
        ReminderCard card = new ReminderCard(activity);
        card.makeCard(strings);
        cardList.add(card);
        mAdapter.notifyDataSetChanged();
        //layout.addView(card,linear);
        return card;
    }
}
