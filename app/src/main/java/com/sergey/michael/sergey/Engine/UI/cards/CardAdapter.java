package com.sergey.michael.sergey.Engine.UI.cards;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sergey.michael.sergey.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private List<ReminderCard> cardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, inventory, cost;
        CardView card;

        public MyViewHolder(View view) {
            super(view);
            card = view.findViewById(R.id.item_card);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            inventory = (TextView) view.findViewById(R.id.inventory);
            cost = (TextView) view.findViewById(R.id.cost);
        }
    }


    public CardAdapter(List<ReminderCard> moviesList) {
        this.cardList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ReminderCard card = cardList.get(position);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newscore =  Integer.parseInt(card.inventory)+1;
                holder.inventory.setText("1000");
            }
        });
        holder.name.setText(card.name);
        holder.description.setText(card.description);
        holder.cost.setText(card.cost);
        holder.inventory.setText(card.inventory);



    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
