package com.example.android.starwarsv2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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


/** People, Planets, Films, Starships, Vehicles, and Species **/

//TODO scrollview buttons

public class MainActivity extends AppCompatActivity {

    protected static final String PEOPLE = "people";
    protected static final String PLANETS = "planets";
    protected static final String FILMS = "films";
    protected static final String STARSHIPS = "starships";
    protected static final String VEHICLES = "vehicles";
    protected static final String SPECIES = "species";

    private String yellowUpPress = "";
    private String yellowDownPress = "";

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
        getSupportActionBar().hide();

        TextView title = (TextView) findViewById(R.id.title_main);
        peopleButton = (Button) findViewById(R.id.getPeopleButton);
        planetsButton = (Button) findViewById(R.id.getPlanetsButton);
        filmsButton = (Button) findViewById(R.id.getFilmsButton);
        starshipsButton = (Button) findViewById(R.id.getStarshipsButton);
        vehiclesButton = (Button) findViewById(R.id.getVehiclesButton);
        speciesButton = (Button) findViewById(R.id.getSpeciesButton);

        Typeface starjedi = Typeface.createFromAsset(getAssets(), "fonts/Starjedi.ttf");
        Typeface starjhol = Typeface.createFromAsset(getAssets(), "fonts/Starjhol.ttf");
        title.setTypeface(starjhol);

        yellowUpPress = getResources().getString(R.string.yellowUpPress);
        yellowDownPress = getResources().getString(R.string.yellowDownPress);

        peopleButton.setTypeface(starjedi);
        planetsButton.setTypeface(starjedi);
        filmsButton.setTypeface(starjedi);
        starshipsButton.setTypeface(starjedi);
        vehiclesButton.setTypeface(starjedi);
        speciesButton.setTypeface(starjedi);

        initAddListeners();

    }

    /**
     * Method is used to encapsulate the code that initializes and sets the
     * Event Listeners which will respond to requests to get data. This will
     * launch a new activity
     */
    protected void initAddListeners(){

        peopleButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    peopleButton.setBackgroundColor(Color.parseColor(yellowDownPress));
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    peopleButton.setBackgroundColor(Color.parseColor(yellowUpPress));
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("ListViewType", PEOPLE);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        planetsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    planetsButton.setBackgroundColor(Color.parseColor(yellowDownPress));
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    planetsButton.setBackgroundColor(Color.parseColor(yellowUpPress));
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("ListViewType", PLANETS);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        filmsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    filmsButton.setBackgroundColor(Color.parseColor(yellowDownPress));
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    filmsButton.setBackgroundColor(Color.parseColor(yellowUpPress));
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("ListViewType", FILMS);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        starshipsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    starshipsButton.setBackgroundColor(Color.parseColor(yellowDownPress));
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    starshipsButton.setBackgroundColor(Color.parseColor(yellowUpPress));
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("ListViewType", STARSHIPS);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        vehiclesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    vehiclesButton.setBackgroundColor(Color.parseColor(yellowDownPress));
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    vehiclesButton.setBackgroundColor(Color.parseColor(yellowUpPress));
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("ListViewType", VEHICLES);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        speciesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    speciesButton.setBackgroundColor(Color.parseColor(yellowDownPress));
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    speciesButton.setBackgroundColor(Color.parseColor(yellowUpPress));
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("ListViewType", SPECIES);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

}
