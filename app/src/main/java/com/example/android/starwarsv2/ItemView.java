package com.example.android.starwarsv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swapi.models.People;

import org.w3c.dom.Text;

/**
 * Created by Sam on 5/7/16.
 */

/** Class to display individual views **/

public class ItemView extends LinearLayout {

    private TextView m_text;

    public ItemView(Context context, People p){
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_view, this, true);

        m_text = (TextView) findViewById(R.id.listText);
        m_text.setText(p.name);

        requestLayout();
    }


}
