package com.example.android.starwarsv2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.starwarsv2.ItemView;
import com.swapi.models.Planet;
import com.swapi.models.Starship;

import java.util.List;

/**
 * Created by Sam on 5/15/16.
 */
public class StarshipsAdapter extends BaseAdapter{

    /** The application Context in which this Adapter is being used. */
    private Context m_context;

    /** The data set to which this Adapter is bound. */
    private List<Starship> m_starshipsList;

    public StarshipsAdapter(Context context, List<Starship> starshipsArrayList){

        this.m_context = context;
        this.m_starshipsList = starshipsArrayList;
    }

    @Override
    public int getCount() {
        return this.m_starshipsList.size();
    }

    @Override
    public Object getItem(int position) {
        return m_starshipsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemView itemView;

        if(convertView == null){
            itemView = new ItemView(this.m_context, this.m_starshipsList.get(position));
        }else{
            itemView = new ItemView(this.m_context, this.m_starshipsList.get(position));
        }
        return itemView;
    }

}
