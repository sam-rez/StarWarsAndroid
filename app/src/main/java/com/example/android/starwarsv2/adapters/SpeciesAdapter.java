package com.example.android.starwarsv2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.starwarsv2.ItemView;
import com.swapi.models.Planet;
import com.swapi.models.Species;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 5/15/16.
 */
public class SpeciesAdapter extends BaseAdapter{


    /** The application Context in which this Adapter is being used. */
    private Context m_context;

    /** The data set to which this Adapter is bound. */
    private List<Species> m_speciesList;

    public SpeciesAdapter(Context context, List<Species> speciesArrayList){

        this.m_context = context;
        this.m_speciesList = speciesArrayList;
    }

    @Override
    public int getCount() {
        return this.m_speciesList.size();
    }

    @Override
    public Object getItem(int position) {
        return m_speciesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemView itemView;

        if(convertView == null){
            itemView = new ItemView(this.m_context, this.m_speciesList.get(position));
        }else{
            itemView = (ItemView) convertView;
        }
        return itemView;

    }

}
