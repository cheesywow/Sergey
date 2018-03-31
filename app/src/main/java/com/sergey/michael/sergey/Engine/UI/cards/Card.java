package com.sergey.michael.sergey.Engine.UI.cards;

import android.content.Context;
import android.support.v7.widget.CardView;

public abstract class Card extends CardView {
    protected static int numCards;
    public Card(Context context) {
        super(context);
    }

    public abstract void onClickListener();
    public abstract void onFocusListener();

    public void removeCard(){

    }

}
