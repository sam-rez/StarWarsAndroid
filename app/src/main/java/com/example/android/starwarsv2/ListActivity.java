package com.example.android.starwarsv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.starwarsv2.adapters.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.swapi.models.Film;
import com.swapi.models.People;
import com.swapi.models.Planet;
import com.swapi.models.SWModelList;
import com.swapi.models.Species;
import com.swapi.models.Starship;
import com.swapi.models.Vehicle;
import com.swapi.sw.StarWars;
import com.swapi.sw.StarWarsApi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    protected TextView title;
    ProgressDialog progress;

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
        title = (TextView)findViewById(R.id.list_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Starjedi.ttf");
        title.setTypeface(font);
        getSupportActionBar().hide();

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
                title.setText(category);
                m_vwPeopleLayout = (ListView) findViewById(R.id.ItemListViewGroup);
                m_vwPeopleLayout.setAdapter(m_peopleAdapter);
                m_vwPeopleLayout.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), IndividualActivity.class);
                        intent.putExtra("ListViewType", MainActivity.PEOPLE);
                        intent.putExtra("name", peopleArrayList.get(position).name);
                        intent.putExtra("height", peopleArrayList.get(position).height);
                        intent.putExtra("mass", peopleArrayList.get(position).mass);
                        intent.putExtra("hair_color", peopleArrayList.get(position).hairColor);
                        intent.putExtra("skin_color", peopleArrayList.get(position).skinColor);
                        intent.putExtra("birth_year", peopleArrayList.get(position).birthYear);
                        intent.putExtra("gender", peopleArrayList.get(position).gender);
                        startActivity(intent);
                    }
                });
                progress = ProgressDialog.show(this, "Loading", "Loading", true);
                for(int i = 1; i < 10; i++) {
                    api.getAllPeople(i, new Callback<SWModelList<People>>() {
                        @Override
                        public void success(SWModelList<People> planetSWModelList, Response response) {
                            for (People p : planetSWModelList.results) {
                                peopleArrayList.add(p);
                                System.out.println(p.name);
                            }
                            progress.dismiss();
                            m_peopleAdapter.notifyDataSetChanged();
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            System.out.print("failure");
                        }
                    });
                }
                break;
            case MainActivity.PLANETS:
                title.setText(category);
                m_vwPlanetsLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwPlanetsLayout.setAdapter(m_planetsAdapter);
                m_vwPlanetsLayout.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListActivity.this, IndividualActivity.class);
                        intent.putExtra("ListViewType", MainActivity.PLANETS);
                        intent.putExtra("name", planetArrayList.get(position).name);
                        intent.putExtra("climate", planetArrayList.get(position).climate);
                        intent.putExtra("diameter", planetArrayList.get(position).diameter);
                        intent.putExtra("population", planetArrayList.get(position).population);
                        intent.putExtra("rotation_period", planetArrayList.get(position).rotationPeriod);
                        intent.putExtra("terrain", planetArrayList.get(position).terrain);
                        intent.putExtra("gravity", planetArrayList.get(position).gravity);
                    }
                });
                for(int i = 1; i < 8; i++) {
                    api.getAllPlanets(i, new Callback<SWModelList<Planet>>() {
                        @Override
                        public void success(SWModelList<Planet> planetSWModelList, Response response) {
                            for (Planet p : planetSWModelList.results) {
                                planetArrayList.add(p);
                                m_planetsAdapter.notifyDataSetChanged();
                                System.out.println(p.name);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            System.out.print("failure");
                        }
                    });
                }
                break;
            case MainActivity.FILMS:
                title.setText(category);
                m_vwFilmsLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwFilmsLayout.setAdapter(m_filmsAdapter);
                m_vwFilmsLayout.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListActivity.this, IndividualActivity.class);
                        intent.putExtra("ListViewType", MainActivity.FILMS);
                    }
                });
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
            case MainActivity.STARSHIPS:
                title.setText(category);
                m_vwStarshipsLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwStarshipsLayout.setAdapter(m_starshipsAdapter);
                m_vwStarshipsLayout.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListActivity.this, IndividualActivity.class);
                        intent.putExtra("ListViewType", MainActivity.STARSHIPS);
                    }
                });
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
                title.setText(category);
                m_vwVehiclesLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwVehiclesLayout.setAdapter(m_vehiclesAdapter);
                m_vwVehiclesLayout.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListActivity.this, IndividualActivity.class);
                        intent.putExtra("ListViewType", MainActivity.VEHICLES);
                    }
                });
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
                title.setText(category);
                m_vwSpeciesLayout = (ListView)findViewById(R.id.ItemListViewGroup);
                m_vwSpeciesLayout.setAdapter(m_speciesAdapter);
                m_vwSpeciesLayout.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListActivity.this, IndividualActivity.class);
                        intent.putExtra("ListViewType", MainActivity.SPECIES);
                    }
                });
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

    private void savePeopleArrayList(ArrayList<People> arrayList){

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(arrayList);

        editor.putString("PeopleArrayList", json);
        editor.commit();

    }

    private ArrayList<People> getPeopleArrayList(){

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("PeopleArrayList", null);
        Type type = new TypeToken<ArrayList<People>>() {}.getType();
        ArrayList<People> arrayList = gson.fromJson(json, type);
        return arrayList;

    }

}
