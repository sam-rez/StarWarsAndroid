package com.samrezaie.android.starwarsv2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.samrezaie.android.starwarsv2.ItemView;
import com.swapi.models.Planet;

import java.util.List;

/**
 * Created by Sam on 5/15/16.
 */
public class PlanetsAdapter extends BaseAdapter{


    /** The application Context in which this PlanetAdapter is being used. */
    private Context m_context;

    /** The data set to which this PlanetAdapter is bound. */
    private List<Planet> m_planetsList;

    public PlanetsAdapter(Context context, List<Planet> planetsArrayList){

        this.m_context = context;
        this.m_planetsList = planetsArrayList;
    }

    @Override
    public int getCount() {
        return this.m_planetsList.size();
    }

    @Override
    public Object getItem(int position) {
        return m_planetsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemView itemView;

        if(convertView == null){
            itemView = new ItemView(this.m_context, this.m_planetsList.get(position));
        }else{
            itemView = new ItemView(this.m_context, this.m_planetsList.get(position));
        }
        return itemView;
    }
}
