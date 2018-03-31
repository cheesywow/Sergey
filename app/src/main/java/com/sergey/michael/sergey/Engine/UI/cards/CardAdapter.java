package com.sergey.michael.sergey.Engine.UI.cards;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sergey.michael.sergey.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private List<ReminderCard> cardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,  note, date, time;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            note = (TextView) view.findViewById(R.id.note);
            date = (TextView) view.findViewById(R.id.date);
            time = (TextView) view.findViewById(R.id.time);
        }
    }


    public CardAdapter(List<ReminderCard> moviesList) {
        this.cardList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ReminderCard card = cardList.get(position);
        holder.title.setText(card.title);
        holder.note.setText(card.note);
        holder.date.setText(card.date);
        holder.time.setText(card.time);

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
