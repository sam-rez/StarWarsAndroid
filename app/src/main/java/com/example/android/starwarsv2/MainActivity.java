package com.example.android.starwarsv2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.swapi.models.Film;
import com.swapi.models.People;
import com.swapi.models.Planet;
import com.swapi.models.SWModelList;
import com.swapi.sw.StarWarsApi;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

//TODO Make ItemView a button w/ onClickListeners
//TODO Fix scrolling repeat issue
//TODO spinner

/** People, Planets, Films, Starships, Vehicles, and Species **/

public class MainActivity extends AppCompatActivity {

    protected static final String PEOPLE = "People";
    protected static final String PLANETS = "Planets";
    protected static final String FILMS = "Films";
    protected static final String STARSHIPS = "Starships";
    protected static final String VEHICLES = "Vehicles";
    protected static final String SPECIES = "Species";

    protected Button peopleButton;
    protected Button planetsButton;
    protected Button filmsButton;
    protected Button starshipsButton;
    protected Button vehiclesButton;
    protected Button speciesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewCustom = (TextView) findViewById(R.id.test);
        peopleButton = (Button) findViewById(R.id.getPeopleButton);
        planetsButton = (Button) findViewById(R.id.getPlanetsButton);
        filmsButton = (Button) findViewById(R.id.getFilmsButton);
        starshipsButton = (Button) findViewById(R.id.getStarshipsButton);
        vehiclesButton = (Button) findViewById(R.id.getVehiclesButton);
        speciesButton = (Button) findViewById(R.id.getSpeciesButton);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Starjedi.ttf");
        textViewCustom.setTypeface(myCustomFont);
        peopleButton.setTypeface(myCustomFont);
        planetsButton.setTypeface(myCustomFont);
        filmsButton.setTypeface(myCustomFont);
        starshipsButton.setTypeface(myCustomFont);
        vehiclesButton.setTypeface(myCustomFont);
        speciesButton.setTypeface(myCustomFont);

        initAddListeners();

    }

    /**
     * Method is used to encapsulate the code that initializes and sets the
     * Event Listeners which will respond to requests to get data. This will
     * launch a new activity
     */
    protected void initAddListeners(){

        peopleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("ListViewType", PEOPLE);
                startActivity(intent);
            }
        });

        planetsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("ListViewType", PLANETS);
                startActivity(intent);
            }
        });

        filmsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("ListViewType", FILMS);
                startActivity(intent);
            }
        });

        starshipsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("ListViewType", STARSHIPS);
                startActivity(intent);
            }
        });

        vehiclesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("ListViewType", VEHICLES);
                startActivity(intent);
            }
        });

        speciesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("ListViewType", SPECIES);
                startActivity(intent);
            }
        });
    }

}
