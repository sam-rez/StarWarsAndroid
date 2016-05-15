package com.example.android.starwarsv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.android.starwarsv2.adapters.*;
import com.swapi.models.Film;
import com.swapi.models.People;
import com.swapi.models.Planet;
import com.swapi.models.SWModelList;
import com.swapi.models.Species;
import com.swapi.models.Starship;
import com.swapi.models.Vehicle;
import com.swapi.sw.StarWars;
import com.swapi.sw.StarWarsApi;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/** Planets, Starships, Vehicles, People, Films and Species **/
/** Class that displays all the views for a particular category **/

public class ListActivity extends AppCompatActivity {

    /** Adapter used to bind an AdapterView to a ListView. */
    protected PeopleAdapter m_peopleAdapter;
    protected PlanetsAdapter m_planetsAdapter;
    protected FilmsAdapter m_filmsAdapter;
    protected StarshipsAdapter m_starshipsAdapter;
    protected VehiclesAdapter m_vehiclesAdapter;
    protected SpeciesAdapter m_speciesAdapter;

    /** ViewGroup used for maintaining a list of Views that each display Items **/
    protected ListView m_vwPeopleLayout;
    protected ListView m_vwPlanetsLayout;
    protected ListView m_vwFilmsLayout;
    protected ListView m_vwStarshipsLayout;
    protected ListView m_vwVehiclesLayout;
    protected ListView m_vwSpeciesLayout;

    /** Used to get items from API call. Respective adapters will copy from arraylist
       to their local arraylist **/
    protected ArrayList<People> peopleArrayList;
    protected ArrayList<Planet> planetArrayList;
    protected ArrayList<Film> filmArrayList;
    protected ArrayList<Starship> starshipArrayList;
    protected ArrayList<Vehicle> vehicleArrayList;
    protected ArrayList<Species> speciesArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        peopleArrayList = new ArrayList<>();
        planetArrayList = new ArrayList<>();
        filmArrayList = new ArrayList<>();
        starshipArrayList = new ArrayList<>();
        vehicleArrayList = new ArrayList<>();
        speciesArrayList = new ArrayList<>();

        m_peopleAdapter = new PeopleAdapter(this, peopleArrayList);
        m_planetsAdapter = new PlanetsAdapter(this, planetArrayList);
        m_filmsAdapter = new FilmsAdapter(this, filmArrayList);
        m_starshipsAdapter = new StarshipsAdapter(this, starshipArrayList);
        m_vehiclesAdapter = new VehiclesAdapter(this, vehicleArrayList);
        m_speciesAdapter = new SpeciesAdapter(this, speciesArrayList);

        initLayout();

        /** Find which category is requested **/
        Bundle b = getIntent().getExtras();
        String category = "";
        if(b != null){
            category = b.getString("ListViewType");
        }

        getItems(category);
    }

    protected void initLayout(){
        setContentView(R.layout.activity_list);
    }

    protected void getItems(String category){

        StarWarsApi.init();
        StarWars api = StarWarsApi.getApi();

        switch (category) {
            case MainActivity.PEOPLE:
                m_vwPeopleLayout = (ListView) findViewById(R.id.ItemListViewGroup);
                m_vwPeopleLayout.setAdapter(m_peopleAdapter);
                api.getAllPeople(1, new Callback<SWModelList<People>>() {
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
            case MainActivity.FILMS:
                m_vwFilmsLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwFilmsLayout.setAdapter(m_filmsAdapter);
                api.getAllFilms(1, new Callback<SWModelList<Film>>() {
                    @Override
                    public void success(SWModelList<Film> filmSWModelList, Response response) {
                        System.out.println("Count:"+ filmSWModelList.count);
                        for(Film f : filmSWModelList.results) {
                            filmArrayList.add(f);
                        }
                        m_filmsAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                    }
                });
                break;
            case MainActivity.PLANETS:
                m_vwPlanetsLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwPlanetsLayout.setAdapter(m_planetsAdapter);
                api.getAllPlanets(1, new Callback<SWModelList<Planet>>() {
                    @Override
                    public void success(SWModelList<Planet> planetSWModelList, Response response) {
                        for (Planet p : planetSWModelList.results) {
                            planetArrayList.add(p);
                        }
                        m_planetsAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        System.out.print("failure");
                    }
                });
                break;
            case MainActivity.STARSHIPS:
                m_vwStarshipsLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwStarshipsLayout.setAdapter(m_starshipsAdapter);
                api.getAllStarships(2, new Callback<SWModelList<Starship>>() {
                    @Override
                    public void success(SWModelList<Starship> starshipSWModelList, Response response) {
                        System.out.println("Count:"+ starshipSWModelList.count);
                        for(Starship s : starshipSWModelList.results) {
                            starshipArrayList.add(s);
                        }
                        m_starshipsAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                    }
                });
                break;
            case MainActivity.VEHICLES:
                m_vwVehiclesLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwVehiclesLayout.setAdapter(m_vehiclesAdapter);
                api.getAllVehicles(2, new Callback<SWModelList<Vehicle>>() {
                    @Override
                    public void success(SWModelList<Vehicle> vehicleSWModelList, Response response) {
                        System.out.println("Count:"+ vehicleSWModelList.count);
                        for(Vehicle v : vehicleSWModelList.results) {
                            vehicleArrayList.add(v);
                        }
                        m_vehiclesAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                    }
                });
                break;
            case MainActivity.SPECIES:
                m_vwSpeciesLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwSpeciesLayout.setAdapter(m_speciesAdapter);
                api.getAllSpecies(2, new Callback<SWModelList<Species>>() {
                    @Override
                    public void success(SWModelList<Species> speciesSWModelList, Response response) {
                        System.out.println("Count:"+ speciesSWModelList.count);
                        for(Species s : speciesSWModelList.results) {
                            speciesArrayList.add(s);
                        }
                        m_speciesAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                    }
                });
                break;
            default:
                Log.d("Shouldn't be here", "Shouldn't be here... ever");
        }
    }

}
