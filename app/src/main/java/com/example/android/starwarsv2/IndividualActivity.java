package com.example.android.starwarsv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swapi.models.People;

import java.util.ArrayList;

/**
 * Created by Sam on 5/17/16.
 */
public class IndividualActivity extends AppCompatActivity{

    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ll = (LinearLayout) findViewById(R.id.ll_ind);
        setContentView(R.layout.activity_individual);

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
                String name = b.getString("name");
                TextView textView = new TextView(this);
                textView.setText(name);
                ll.addView(textView);
                String height = b.getString("height");
                String massString = b.getString("mass");
                double mass = convertKGtoLB(massString);
                String hair_color = b.getString("hair_color");
                String skin_color = b.getString("skin_color");
                String birth_year = b.getString("birth_year");
                String gender = b.getString("gender");

                break;

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
