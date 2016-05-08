package com.example.android.starwarsv2;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewCustom = (TextView) findViewById(R.id.test);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Starjedi.ttf");
        textViewCustom.setTypeface(myCustomFont);

        StarWarsApi.init();

        for(int i = 1; i <= 7; i++) {
            StarWarsApi.getApi().getAllPeople(i, new Callback<SWModelList<People>>() {
                @Override
                public void success(SWModelList<People> planetSWModelList, Response response) {
                    Log.e("TEST", "TESTING");
                    for (People p : planetSWModelList.results) {
                        System.out.println("Person:" + p.name);
                    }

                }

                @Override
                public void failure(RetrofitError error) {
                    System.out.print("failure");
                }
            });
        }

    }


}
