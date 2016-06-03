package com.samrezaie.android.starwarsv2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.samrezaie.android.starwarsv2.ItemView;
import com.swapi.models.People;

import java.util.List;

/**
 * Created by Sam on 5/10/16.
 */
public class PeopleAdapter extends BaseAdapter {
          /** The application Context in which this PeopleAdapter is being used. */
    private Context m_context;

    /** The data set to which this PeopleAdapter is bound. */
    private List<People> m_peopleList;

    public PeopleAdapter(Context context, List<People> peopleList){

        this.m_context = context;
        this.m_peopleList = peopleList;

    }

    @Override
    public int getCount() {
        return this.m_peopleList.size();
    }

    @Override
    public Object getItem(int position) {
        return m_peopleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemView itemView;

        if(convertView == null){
            itemView = new ItemView(this.m_context, this.m_peopleList.get(position));
        }else{
            //itemView = (ItemView)convertView;
            itemView = new ItemView(this.m_context, this.m_peopleList.get(position));

        }
        return itemView;

    }

}
