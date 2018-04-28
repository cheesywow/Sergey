package com.sergey.michael.sergey.Engine.UI.cards;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sergey.michael.sergey.R;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private List<ReminderCard> cardList;
    private List<MyViewHolder> Holders = new ArrayList<>();
    private Activity activity;


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, inventory, cost;
        CardView card;

        MyViewHolder(View view) {
            super(view);
            card = view.findViewById(R.id.item_card);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            inventory = view.findViewById(R.id.inventory);
            cost = view.findViewById(R.id.cost);
        }
    }


    public CardAdapter(Activity activity, List<ReminderCard> cardList) {
        this.cardList = cardList;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final ReminderCard card = cardList.get(position);
        Holders.add(holder);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = activity.findViewById(R.id.shop_score);
                String display = tv.getText().toString();
                String trunc = display.split(" ")[1];
                String score_string = trunc.replaceAll(",", "");
                int score = Integer.parseInt(score_string);
                if(Integer.parseInt(holder.cost.getText().toString()) <= score) {
                    int newscore = score - Integer.parseInt(holder.cost.getText().toString());
                    tv.setText(MessageFormat.format("Score: {0}", newscore));

                    String num = holder.inventory.getText().toString();
                    String cut = num.split("x")[1];
                    int newcount = Integer.parseInt(cut) + 1;
                    holder.inventory.setText(MessageFormat.format("x{0}", newcount));
                    cardList.get(position).inventory = "" + newcount;
                    holder.cost.setText("" + (int) (Integer.parseInt(holder.cost.getText().toString()) * 1.2));
                    for (int i = 0; i < Holders.size(); i++) {
                        if (Integer.parseInt(Holders.get(i).cost.getText().toString()) > newscore) {
                            Holders.get(i).card.setBackgroundColor(0xFF949393);
                        }
                    }
                }
            }
        });
        holder.name.setText(card.name);
        holder.description.setText(card.description);
        holder.cost.setText(card.cost);
        holder.inventory.setText("x"+card.inventory);
        TextView tv = activity.findViewById(R.id.shop_score);
        String display = tv.getText().toString();
        String trunc = display.split(" ")[1];
        String score = trunc.replaceAll(",", "");
        int newscore = Integer.parseInt(score);
        if(Integer.parseInt(card.cost) > newscore){
            holder.card.setBackgroundColor(0xFF949393);
        }
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
