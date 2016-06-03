package com.samrezaie.android.starwarsv2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.samrezaie.android.starwarsv2.ItemView;
import com.swapi.models.Film;

import java.util.List;

/**
 * Created by Sam on 5/15/16.
 */
public class FilmsAdapter extends BaseAdapter{


    /** The application Context in which this Adapter is being used. */
    private Context m_context;

    /** The data set to which this Adapter is bound. */
    private List<Film> m_filmsList;

    public FilmsAdapter(Context context, List<Film> filmsArrayList){

        this.m_context = context;
        this.m_filmsList = filmsArrayList;
    }

    @Override
    public int getCount() {
        return this.m_filmsList.size();
    }

    @Override
    public Object getItem(int position) {
        return m_filmsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemView itemView;

        if(convertView == null){
            itemView = new ItemView(this.m_context, this.m_filmsList.get(position));
        }else{
            itemView = new ItemView(this.m_context, this.m_filmsList.get(position));
        }
        return itemView;
    }

}
