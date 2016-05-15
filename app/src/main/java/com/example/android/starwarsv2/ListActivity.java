package com.example.android.starwarsv2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.swapi.models.Film;
import com.swapi.models.People;
import com.swapi.models.SWModelList;
import com.swapi.sw.StarWars;
import com.swapi.sw.StarWarsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/** Planets, Spaceships, Vehicles, People, Films and Species **/
/** Class that displays all the views for a particular category **/

public class ListActivity extends AppCompatActivity {

    /** Adapter used to bind an AdapterView to List of People. */
    protected PeopleAdapter m_peopleAdapter;

    /** ViewGroup used for maintaining a list of Views that each display Items */
    protected ListView m_vwPeopleLayout;

    protected ArrayList<People> peopleArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        peopleArrayList = new ArrayList<>();
        m_peopleAdapter = new PeopleAdapter(this, peopleArrayList);

        initLayout();

        Bundle b = getIntent().getExtras();
        String value = "";
        if(b != null){
            value = b.getString("ListViewType");
        }

        getItems(value);

    }

    protected void initLayout(){

        setContentView(R.layout.activity_list);
        m_vwPeopleLayout = (ListView) findViewById(R.id.ItemListViewGroup);
        m_vwPeopleLayout.setAdapter(m_peopleAdapter);

    }

    protected void getItems(String item){

        StarWarsApi.init();
        StarWars api = StarWarsApi.getApi();

        switch (item) {
            case MainActivity.PEOPLE:
                StarWarsApi.getApi().getAllPeople(1, new Callback<SWModelList<People>>() {
                    @Override
                    public void success(SWModelList<People> planetSWModelList, Response response) {
                        for (People p : planetSWModelList.results) {
                            peopleArrayList.add(p);
                        }
                        m_peopleAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        System.out.print("failure");
                    }
                });
                break;
            case "Films":
                api.getAllFilms(2, new Callback<SWModelList<Film>>() {
                    @Override
                    public void success(SWModelList<Film> filmSWModelList, Response response) {
                        System.out.println("Count:"+ filmSWModelList.count);
                        for(Film f : filmSWModelList.results) {
                            System.out.println("Title:" + f.title);
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {
                    }
                });

            default:
        }
    }

}
