package com.example.android.starwarsv2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swapi.models.People;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Sam on 5/17/16.
 */
public class IndividualActivity extends AppCompatActivity{

    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ll = new LinearLayout(this);
        setContentView(ll);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundResource(R.drawable.stars);


        /** Find which category is requested **/
        Bundle b = getIntent().getExtras();
        String category = "";
        if(b != null) {
            category = b.getString("ListViewType");
        }

        getCategoryStats(b, category);

    }

    void getCategoryStats(Bundle b, String category){

        switch (category){
            case MainActivity.PEOPLE:
                TextView people_name = new TextView(this);
                people_name.setTextColor(Color.WHITE);
                people_name.setText(b.getString("name"));
                people_name.setTextSize(32);
                ll.addView(people_name);

                TextView people_height = new TextView(this);
                people_height.setTextColor(Color.WHITE);
                people_height.setText("Height: " + b.getString("height"));
                ll.addView(people_height);

                String massString = b.getString("mass");
                double mass = convertKGtoLB(massString);

                TextView people_mass = new TextView(this);
                people_mass.setTextColor(Color.WHITE);
                people_mass.setText("Weight: " + Double.toString(mass) + " pounds");
                ll.addView(people_mass);

                String hair_color = b.getString("hair_color");
                String skin_color = b.getString("skin_color");
                String birth_year = b.getString("birth_year");
                String gender = b.getString("gender");

                break;
            case MainActivity.PLANETS:

                String planet_name = b.getString("name");
                String climate = b.getString("climate");
                String diamter = b.getString("diameter");
                String population = b.getString("population");
                String rotation_period = b.getString("rotation_period");
                String terrain = b.getString("terrain");
                String gravity = b.getString("gravity");

            default:

        }

    }

    private double convertKGtoLB(String mass){

        double KGs, LBs;

        KGs = Double.parseDouble(mass);
        LBs = KGs * 2.2;
        return LBs;
    }

}
