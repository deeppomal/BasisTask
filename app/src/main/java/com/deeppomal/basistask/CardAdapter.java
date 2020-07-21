package com.deeppomal.basistask;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


//CardAdapter class will control the display of the data
public class CardAdapter  extends ArrayAdapter<String> {

    public CardAdapter(Context context) {
        super(context, R.layout.card_items);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){

        TextView id = (TextView)(contentView.findViewById(R.id.id));
        TextView content = (TextView)(contentView.findViewById(R.id.content));

        id.setText((position+1+"/"+getCount()));
        content.setText(getItem(position));
        return contentView;
    }

}
