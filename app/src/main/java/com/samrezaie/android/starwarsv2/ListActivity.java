package com.samrezaie.android.starwarsv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.samrezaie.android.starwarsv2.adapters.*;
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
import java.util.Collections;

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
    protected ArrayList<People> peoplePreSortedList;
    protected ArrayList<Planet> planetArrayList;
    protected ArrayList<Film> filmArrayList;
    protected ArrayList<Starship> starshipArrayList;
    protected ArrayList<Vehicle> vehicleArrayList;
    protected ArrayList<Species> speciesArrayList;

    protected TextView title;
    ProgressDialog progress;

    protected int numPeople, numPlanets, numFilms, numStarships, numVehicles, numSpecies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        peopleArrayList = new ArrayList<>();
        peoplePreSortedList = new ArrayList<>();

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

//                SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear().commit();

                for (int i = 1; i < 10; i++) {

                    //check if should save or get arraylist
                    SharedPreferences spPeople = getSharedPreferences("sp", MODE_PRIVATE);

                    if(!spPeople.contains("people_array_list" + i)) {

                        final int iFinal = i;
                        api.getAllPeople(i, new Callback<SWModelList<People>>() {
                            @Override
                            public void success(SWModelList<People> peopleSWModelList, Response response) {

                                for (People p : peopleSWModelList.results) {
                                    peopleArrayList.add(p);

                                }
                                //save arraylist to sharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                if(peopleArrayList.size() > numPeople){
                                    numPeople = peopleArrayList.size();
                                    editor.putInt("numPeople", numPeople);
                                }

                                peopleArrayList = sortPeopleArrayList(peopleArrayList);
                                m_peopleAdapter.notifyDataSetChanged();

                                Gson gson = new Gson();
                                String json = gson.toJson(peopleArrayList);
                                editor.putString("people_array_list" + iFinal, json);
                                editor.commit();

                                progress.dismiss();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                System.out.print("failure");
                            }
                        });
                    }else{

                        Gson gson = new Gson();
                        String json = spPeople.getString("people_array_list" + i, null);
                        Type type = new TypeToken<ArrayList<People>>(){}.getType();

                        ArrayList<People> peopleArrayList2 = gson.fromJson(json, type);
                        progress.dismiss();

                        if(peopleArrayList2.size() == spPeople.getInt("numPeople", numPeople)) {
                            for(People p: peopleArrayList2){
                                peopleArrayList.add(p);
                            }
                            m_peopleAdapter.notifyDataSetChanged();
                        }

                    }//end if-else
                }//end for

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
                        intent.putExtra("orbital_period", planetArrayList.get(position).orbitalPeriod);
                        intent.putExtra("terrain", planetArrayList.get(position).terrain);
                        intent.putExtra("gravity", planetArrayList.get(position).gravity);
                        intent.putExtra("surface_water", planetArrayList.get(position).surfaceWater);
                        startActivity(intent);
                    }
                });
                progress = ProgressDialog.show(this, "Loading", "Loading", true);

                for(int i = 1; i < 8; i++) {

                    //check if should save or get arraylist
                    SharedPreferences spPlanets = getSharedPreferences("sp", MODE_PRIVATE);

                    if(!spPlanets.contains("planet_array_list"+ i)) {

                        final int iFinal = i;
                        api.getAllPlanets(i, new Callback<SWModelList<Planet>>() {
                            @Override
                            public void success(SWModelList<Planet> planetSWModelList, Response response) {

                                for (Planet p : planetSWModelList.results) {
                                    planetArrayList.add(p);
                                }
                                //save arraylist to sharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                if(planetArrayList.size() > numPlanets){
                                    numPlanets = planetArrayList.size();
                                    editor.putInt("numPlanets", numPlanets);
                                }

                                planetArrayList = sortPlanetArrayList(planetArrayList);
                                m_planetsAdapter.notifyDataSetChanged();

                                Gson gson = new Gson();
                                String json = gson.toJson(planetArrayList);
                                editor.putString("planet_array_list" + iFinal, json);
                                editor.commit();
                                progress.dismiss();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                System.out.print("failure");
                            }
                        });
                    }else{
                        Gson gson = new Gson();
                        String json = spPlanets.getString("planet_array_list" + i, null);
                        Type type = new TypeToken<ArrayList<Planet>>(){}.getType();

                        ArrayList<Planet> planetArrayList2 = gson.fromJson(json, type);
                        progress.dismiss();

                        if(planetArrayList2.size() == spPlanets.getInt("numPlanets", numPlanets)) {
                            for(Planet p: planetArrayList2){
                                planetArrayList.add(p);
                            }
                            m_planetsAdapter.notifyDataSetChanged();
                        }
                    }//end if-else
                }//end for
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
                        intent.putExtra("title", filmArrayList.get(position).title);
                        intent.putExtra("episode", filmArrayList.get(position).episodeId);
                        intent.putExtra("director", filmArrayList.get(position).director);
                        intent.putExtra("opening_crawl", filmArrayList.get(position).openingCrawl);
                        startActivity(intent);
                    }
                });

                progress = ProgressDialog.show(this, "Loading", "Loading", true);

                SharedPreferences spFilms = getSharedPreferences("sp", MODE_PRIVATE);

                if(!spFilms.contains("film_array_list")) {

                    api.getAllFilms(1, new Callback<SWModelList<Film>>() {
                        @Override
                        public void success(SWModelList<Film> filmSWModelList, Response response) {

                            for (Film f : filmSWModelList.results) {
                                filmArrayList.add(f);
                            }

                            //save arraylist to sharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            filmArrayList = sortFilmArrayList(filmArrayList);
                            m_filmsAdapter.notifyDataSetChanged();

                            Gson gson = new Gson();
                            String json = gson.toJson(filmArrayList);
                            editor.putString("film_array_list", json);
                            editor.commit();

                            progress.dismiss();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                        }
                    });

                }else{
                    Gson gson = new Gson();
                    String json = spFilms.getString("film_array_list", null);
                    Type type = new TypeToken<ArrayList<Film>>(){}.getType();

                    ArrayList<Film> filmArrayList2 = gson.fromJson(json, type);
                    progress.dismiss();

                    for(Film f: filmArrayList2){
                        filmArrayList.add(f);
                    }
                    m_filmsAdapter.notifyDataSetChanged();
                }
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
                        intent.putExtra("name", starshipArrayList.get(position).name);
                        intent.putExtra("manufacturer", starshipArrayList.get(position).manufacturer);
                        intent.putExtra("model", starshipArrayList.get(position).model);
                        intent.putExtra("class", starshipArrayList.get(position).starshipClass);
                        intent.putExtra("cost", starshipArrayList.get(position).costInCredits);
                        intent.putExtra("length", starshipArrayList.get(position).length);
                        intent.putExtra("crew", starshipArrayList.get(position).crew);
                        intent.putExtra("passengers", starshipArrayList.get(position).passengers);
                        intent.putExtra("speed", starshipArrayList.get(position).maxAtmospheringSpeed);
                        intent.putExtra("consumables", starshipArrayList.get(position).consumables);
                        startActivity(intent);
                    }
                });
                progress = ProgressDialog.show(this, "Loading", "Loading", true);

                for(int i = 1; i < 5; i++) {

                    //check if should save or get arraylist
                    SharedPreferences spStarships = getSharedPreferences("sp", MODE_PRIVATE);

                    if(!spStarships.contains("starship_array_list" + 1)) {

                        final int iFinal = i;
                        api.getAllStarships(i, new Callback<SWModelList<Starship>>() {
                            @Override
                            public void success(SWModelList<Starship> starshipSWModelList, Response response) {
                                System.out.println("Count:" + starshipSWModelList.count);
                                for (Starship s : starshipSWModelList.results) {
                                    starshipArrayList.add(s);
                                }
                                //save arraylist to sharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                if(starshipArrayList.size() > numStarships){
                                    numStarships = starshipArrayList.size();
                                    editor.putInt("numStarships", numStarships);
                                }

                                starshipArrayList = sortStarshipArrayList(starshipArrayList);
                                m_starshipsAdapter.notifyDataSetChanged();

                                Gson gson = new Gson();
                                String json = gson.toJson(starshipArrayList);
                                editor.putString("starship_array_list" + iFinal, json);
                                editor.commit();

                                progress.dismiss();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                            }
                        });
                    }else{

                        Gson gson = new Gson();
                        String json = spStarships.getString("starship_array_list" + i, null);
                        Type type = new TypeToken<ArrayList<Starship>>(){}.getType();

                        ArrayList<Starship> starshipArrayList2 = gson.fromJson(json, type);
                        progress.dismiss();

                        if(starshipArrayList2.size() == spStarships.getInt("numStarships", numStarships)) {
                            for(Starship s: starshipArrayList2){
                                starshipArrayList.add(s);
                            }
                            m_peopleAdapter.notifyDataSetChanged();
                        }

                    }//end if-else

                }//end for

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
                        intent.putExtra("name", vehicleArrayList.get(position).name);
                        intent.putExtra("manufacturer", vehicleArrayList.get(position).manufacturer);
                        intent.putExtra("class", vehicleArrayList.get(position).vehicleClass);
                        intent.putExtra("length", vehicleArrayList.get(position).length);
                        intent.putExtra("cost", vehicleArrayList.get(position).costInCredits);
                        intent.putExtra("crew", vehicleArrayList.get(position).crew);
                        intent.putExtra("passengers", vehicleArrayList.get(position).passengers);
                        intent.putExtra("consumables", vehicleArrayList.get(position).consumables);
                        startActivity(intent);
                    }
                });
                progress = ProgressDialog.show(this, "Loading", "Loading", true);

                for(int i = 1; i < 5; i++) {

                    //check if should save or get arraylist
                    SharedPreferences spVehicles = getSharedPreferences("sp", MODE_PRIVATE);

                    if(!spVehicles.contains("vehicle_array_list" + i)) {

                        final int iFinal = i;
                        api.getAllVehicles(i, new Callback<SWModelList<Vehicle>>() {
                            @Override
                            public void success(SWModelList<Vehicle> vehicleSWModelList, Response response) {

                                for (Vehicle v : vehicleSWModelList.results) {
                                    vehicleArrayList.add(v);
                                }

                                //save arraylist to sharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                if(vehicleArrayList.size() > numVehicles){
                                    numVehicles = vehicleArrayList.size();
                                    editor.putInt("numVehicles", numVehicles);
                                }

                                vehicleArrayList = sortVehicleArrayList(vehicleArrayList);
                                m_vehiclesAdapter.notifyDataSetChanged();

                                Gson gson = new Gson();
                                String json = gson.toJson(vehicleArrayList);
                                editor.putString("vehicle_array_list" + iFinal, json);
                                editor.commit();

                                progress.dismiss();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                            }
                        });

                    }else{

                        Gson gson = new Gson();
                        String json = spVehicles.getString("vehicle_array_list" + i, null);
                        Type type = new TypeToken<ArrayList<Vehicle>>(){}.getType();

                        ArrayList<Vehicle> vehicleArrayList2 = gson.fromJson(json, type);
                        progress.dismiss();

                        if(vehicleArrayList2.size() == spVehicles.getInt("numVehicles", numVehicles)) {
                            for (Vehicle v : vehicleArrayList2) {
                                vehicleArrayList.add(v);
                            }
                            m_vehiclesAdapter.notifyDataSetChanged();
                        }
                    }
                }
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
                        intent.putExtra("name", speciesArrayList.get(position).name);
                        intent.putExtra("classification", speciesArrayList.get(position).classification);
                        intent.putExtra("designation", speciesArrayList.get(position).designation);
                        intent.putExtra("average_height", speciesArrayList.get(position).averageHeight);
                        intent.putExtra("average_lifespan", speciesArrayList.get(position).averageLifespan);
                        intent.putExtra("eye_colors", speciesArrayList.get(position).eyeColors);
                        intent.putExtra("hair_colors", speciesArrayList.get(position).hairColors);
                        intent.putExtra("skin_colors", speciesArrayList.get(position).skinColors);
                        intent.putExtra("language", speciesArrayList.get(position).language);
                        startActivity(intent);
                    }
                });

                progress = ProgressDialog.show(this, "Loading", "Loading", true);

                for(int i = 1; i < 5; i++) {

                    //check if should save or get arraylist
                    SharedPreferences spSpecies = getSharedPreferences("sp", MODE_PRIVATE);

                    if(!spSpecies.contains("species_array_list" + i)) {

                        final int iFinal = i;
                        api.getAllSpecies(i, new Callback<SWModelList<Species>>() {
                            @Override
                            public void success(SWModelList<Species> speciesSWModelList, Response response) {

                                for (Species s : speciesSWModelList.results) {
                                    speciesArrayList.add(s);
                                }
                                //save arraylist to sharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                if(speciesArrayList.size() > numSpecies){
                                    numSpecies = speciesArrayList.size();
                                    editor.putInt("numSpecies", numSpecies);
                                }

                                speciesArrayList = sortSpeciesArrayList(speciesArrayList);
                                m_speciesAdapter.notifyDataSetChanged();

                                Gson gson = new Gson();
                                String json = gson.toJson(speciesArrayList);
                                editor.putString("species_array_list" + iFinal, json);
                                editor.commit();

                                progress.dismiss();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                            }
                        });
                    }else {

                        Gson gson = new Gson();
                        String json = spSpecies.getString("species_array_list" + i, null);
                        Type type = new TypeToken<ArrayList<Species>>() {}.getType();

                        ArrayList<Species> speciesArrayList2 = gson.fromJson(json, type);
                        progress.dismiss();

                        if (speciesArrayList2.size() == spSpecies.getInt("numSpecies", numSpecies)) {
                            for (Species s : speciesArrayList2) {
                                speciesArrayList.add(s);
                            }
                            m_speciesAdapter.notifyDataSetChanged();
                        }
                    }
                }
                break;
            default:
                Log.d("Shouldn't be here", "Shouldn't be here... ever");
        }
    }


    private ArrayList<People> sortPeopleArrayList(ArrayList<People> arrayList){

        for(int i = 0; i < arrayList.size(); i++){
            for(int j = 0; j < arrayList.size(); j++){
                if(arrayList.get(i).name.compareTo(arrayList.get(j).name) < 0){
                    Collections.swap(arrayList, i, j);
                }
            }
        }
        return arrayList;
    }

    private ArrayList<Planet> sortPlanetArrayList(ArrayList<Planet> arrayList){

        for(int i = 0; i < arrayList.size(); i++){
            for(int j = 0; j < arrayList.size(); j++){
                if(arrayList.get(i).name.compareTo(arrayList.get(j).name) < 0){
                    Collections.swap(arrayList, i, j);
                }
            }
        }
        return arrayList;
    }

    private ArrayList<Film> sortFilmArrayList(ArrayList<Film> arrayList){

        for(int i = 0; i < arrayList.size(); i++){
            for(int j = 0; j < arrayList.size(); j++){
                if(arrayList.get(i).episodeId < arrayList.get(j).episodeId){
                    Collections.swap(arrayList, i, j);
                }
            }
        }
        return arrayList;
    }

    private ArrayList<Starship> sortStarshipArrayList(ArrayList<Starship> arrayList){

        for(int i = 0; i < arrayList.size(); i++){
            for(int j = 0; j < arrayList.size(); j++){
                if(arrayList.get(i).name.compareTo(arrayList.get(j).name) < 0){
                    Collections.swap(arrayList, i, j);
                }
            }
        }
        return arrayList;
    }

    private ArrayList<Vehicle> sortVehicleArrayList(ArrayList<Vehicle> arrayList){

        for(int i = 0; i < arrayList.size(); i++){
            for(int j = 0; j < arrayList.size(); j++){
                if(arrayList.get(i).name.compareTo(arrayList.get(j).name) < 0){
                    Collections.swap(arrayList, i, j);
                }
            }
        }
        return arrayList;
    }

    private ArrayList<Species> sortSpeciesArrayList(ArrayList<Species> arrayList){

        for(int i = 0; i < arrayList.size(); i++){
            for(int j = 0; j < arrayList.size(); j++){
                if(arrayList.get(i).name.compareTo(arrayList.get(j).name) < 0){
                    Collections.swap(arrayList, i, j);
                }
            }
        }
        return arrayList;
    }

}
