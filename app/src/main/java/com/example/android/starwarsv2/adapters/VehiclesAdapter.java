package com.example.android.starwarsv2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.starwarsv2.ItemView;
import com.swapi.models.Vehicle;

import java.util.List;

/**
 * Created by Sam on 5/15/16.
 */
public class VehiclesAdapter extends BaseAdapter{


    /** The application Context in which this Adapter is being used. */
    private Context m_context;

    /** The data set to which this Adapter is bound. */
    private List<Vehicle> m_vehiclesList;

    public VehiclesAdapter(Context context, List<Vehicle> vehiclesArrayList){

        this.m_context = context;
        this.m_vehiclesList = vehiclesArrayList;
    }

    @Override
    public int getCount() {
        return this.m_vehiclesList.size();
    }

    @Override
    public Object getItem(int position) {
        return m_vehiclesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemView itemView;

        if(convertView == null){
            itemView = new ItemView(this.m_context, this.m_vehiclesList.get(position));
        }else{
            itemView = new ItemView(this.m_context, this.m_vehiclesList.get(position));
        }
        return itemView;
    }

}
