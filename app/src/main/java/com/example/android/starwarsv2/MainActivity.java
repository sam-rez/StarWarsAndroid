package com.example.android.starwarsv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.swapi.models.Film;
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

        StarWarsApi.init();

        StarWarsApi.getApi().getAllPlanets(2, new Callback<SWModelList<Planet>>() {
            @Override
            public void success(SWModelList<Planet> planetSWModelList, Response response) {
                Log.e("TEST", "TESTING");
                System.out.print(planetSWModelList);

            }

            @Override
            public void failure(RetrofitError error) {
                System.out.print("failure");
            }
        });


    }


}
