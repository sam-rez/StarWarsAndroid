package com.example.android.starwarsv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swapi.models.Film;
import com.swapi.models.People;
import com.swapi.models.Planet;
import com.swapi.models.Species;
import com.swapi.models.Starship;
import com.swapi.models.Vehicle;

import org.w3c.dom.Text;

/**
 * Created by Sam on 5/7/16.
 */

/** Class to display views. Used when user selects a category. Views are displayed in LL vertical **/

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

    public ItemView(Context context, Planet p){
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_view, this, true);

        m_text = (TextView) findViewById(R.id.listText);
        m_text.setText(p.name);

        requestLayout();
    }


    public ItemView(Context context, Film f){
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_view, this, true);

        m_text = (TextView) findViewById(R.id.listText);
        m_text.setText(f.title);

        requestLayout();
    }

    public ItemView(Context context, Vehicle v){
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_view, this, true);

        m_text = (TextView) findViewById(R.id.listText);
        m_text.setText(v.name);

        requestLayout();
    }

    public ItemView(Context context, Species s){
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_view, this, true);

        m_text = (TextView) findViewById(R.id.listText);
        m_text.setText(s.name);

        requestLayout();
    }

    public ItemView(Context context, Starship s){
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_view, this, true);

        m_text = (TextView) findViewById(R.id.listText);
        m_text.setText(s.name);

        requestLayout();
    }

}
