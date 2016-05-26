package com.example.android.starwarsv2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swapi.models.People;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sam on 5/17/16.
 */

//TODO lowercase people_name
//TODO height truncate

public class IndividualActivity extends AppCompatActivity{

    LinearLayout ll;
    Typeface font;
    private String [] colors = {/*green*/ "#39FF14", /*red*/ "#FF2502", /*blue*/ "#0064FF", "#FFFFFF"};
    final private int FONT_SIZE = 26;
    final private int PADDING_LEFT = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ll = new LinearLayout(this);
        setContentView(ll);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundResource(R.drawable.stars);

        getSupportActionBar().hide();

        font = Typeface.createFromAsset(getAssets(), "fonts/Starjedi.ttf");

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

                String color_people = getRandom(colors);
                TextView people_name = CreateTitle(color_people, b.getString("name"));
                ll.addView(people_name);

                TextView people_height = new TextView(this);
                SetTypeFacePaddingColorSize(people_height, color_people);
                people_height.setText("height: " + centimeters2feet(b.getString("height")));
                ll.addView(people_height);

                TextView people_mass = new TextView(this);
                SetTypeFacePaddingColorSize(people_mass, color_people);
                if(!b.getString("mass").equals("unknown")) {
                    String massString = b.getString("mass");
                    int mass = convertKGtoLB(massString);
                    people_mass.setText("weight: " + Double.toString(mass) + " lbs");
                }else{
                    people_mass.setText("weight: unknown");
                }
                ll.addView(people_mass);

                TextView hair_color = new TextView(this);
                SetTypeFacePaddingColorSize(hair_color, color_people);
                hair_color.setText("hair color: " + b.getString("hair_color"));
                ll.addView(hair_color);

                TextView skin_color = new TextView(this);
                SetTypeFacePaddingColorSize(skin_color, color_people);
                skin_color.setText("skin color: " + b.getString("skin_color"));
                ll.addView(skin_color);

                TextView birth_year = new TextView(this);
                SetTypeFacePaddingColorSize(birth_year, color_people);
                birth_year.setText("birth year: " + b.getString("birth_year"));
                ll.addView(birth_year);

                TextView gender = new TextView(this);
                SetTypeFacePaddingColorSize(gender, color_people);
                gender.setText("gender: " + b.getString("gender"));
                ll.addView(gender);

                break;

            case MainActivity.PLANETS:

                String color_planets = getRandom(colors);

                TextView planet_name = CreateTitle(color_planets, b.getString("name"));
                ll.addView(planet_name);

                TextView population = new TextView(this);
                SetTypeFacePaddingColorSize(population, color_planets);
                population.setText("population: " + b.getString("population"));
                ll.addView(population);

                TextView diameter = new TextView(this);
                SetTypeFacePaddingColorSize(diameter, color_planets);
                diameter.setText("diameter: " + b.getString("diameter"));
                ll.addView(diameter);

                TextView climate = new TextView(this);
                SetTypeFacePaddingColorSize(climate, color_planets);
                climate.setText("climate: " + b.getString("climate"));
                ll.addView(climate);

                TextView terrain = new TextView(this);
                SetTypeFacePaddingColorSize(terrain, color_planets);
                terrain.setText("terrain: " + b.getString("terrain"));
                ll.addView(terrain);

                TextView rotation_period = new TextView(this);
                SetTypeFacePaddingColorSize(rotation_period, color_planets);
                rotation_period.setText("rotation period: " + b.getString("rotation_period"));
                ll.addView(rotation_period);

                TextView gravity = new TextView(this);
                SetTypeFacePaddingColorSize(gravity, color_planets);
                gravity.setText("gravity: " + b.getString("gravity"));
                ll.addView(gravity);

                break;

            case MainActivity.FILMS:

                String color_films = getRandom(colors);

                TextView film_name = CreateTitle(color_films, b.getString("title"));
                ll.addView(film_name);

                TextView episode = new TextView(this);
                SetTypeFacePaddingColorSize(episode, color_films);
                episode.setText("episode: " + b.getString("episode"));
                ll.addView(episode);

                TextView director = new TextView(this);
                SetTypeFacePaddingColorSize(director, color_films);
                director.setText("director: " + b.getString("director"));
                ll.addView(director);

                TextView opening_crawl = new TextView(this);
                SetTypeFacePaddingColorSize(opening_crawl, color_films);
                opening_crawl.setTextSize(14);
                opening_crawl.setText("opening crawl: \n" + b.getString("opening_crawl"));
                ll.addView(opening_crawl);

                break;

            case MainActivity.STARSHIPS:

//                intent.putExtra("ListViewType", MainActivity.STARSHIPS);
//                intent.putExtra("name", starshipArrayList.get(position).name);
//                intent.putExtra("manufacturer", starshipArrayList.get(position).manufacturer);
//                intent.putExtra("model", starshipArrayList.get(position).model);
//                intent.putExtra("class", starshipArrayList.get(position).starshipClass);
//                intent.putExtra("cost", starshipArrayList.get(position).costInCredits);
//                intent.putExtra("length", starshipArrayList.get(position).length);
//                intent.putExtra("crew", starshipArrayList.get(position).crew);
//                intent.putExtra("passengers", starshipArrayList.get(position).passengers);
//                intent.putExtra("speed", starshipArrayList.get(position).maxAtmospheringSpeed);
//                intent.putExtra("consumables", starshipArrayList.get(position).consumables);

                break;

            case MainActivity.VEHICLES:

//                intent.putExtra("ListViewType", MainActivity.VEHICLES);
//                intent.putExtra("name", vehicleArrayList.get(position).name);
//                intent.putExtra("manufacturer", vehicleArrayList.get(position).manufacturer);
//                intent.putExtra("class", vehicleArrayList.get(position).vehicleClass);
//                intent.putExtra("length", vehicleArrayList.get(position).length);
//                intent.putExtra("cost", vehicleArrayList.get(position).costInCredits);
//                intent.putExtra("crew", vehicleArrayList.get(position).crew);
//                intent.putExtra("passengers", vehicleArrayList.get(position).passengers);
//                intent.putExtra("consumables", vehicleArrayList.get(position).consumables);

                break;

            case MainActivity.SPECIES:

//                intent.putExtra("name", speciesArrayList.get(position).name);
//                intent.putExtra("classification", speciesArrayList.get(position).classification);
//                intent.putExtra("designation", speciesArrayList.get(position).designation);
//                intent.putExtra("average_height", speciesArrayList.get(position).averageHeight);
//                intent.putExtra("average_lifespan", speciesArrayList.get(position).averageLifespan);
//                intent.putExtra("eye_colors", speciesArrayList.get(position).eyeColors);
//                intent.putExtra("hair_colors", speciesArrayList.get(position).hairColors);
//                intent.putExtra("skin_colors", speciesArrayList.get(position).skinColors);
//                intent.putExtra("language", speciesArrayList.get(position).language);

                break;

            default:
                Log.d("Shouldn't be here", "Shouldn't be here... ever");
        }

    }

    public String getRandom(String [] array) {

        int rnd = new Random().nextInt(array.length);
        return array[rnd];

    }

    private int convertKGtoLB(String mass){

        double KGs, LBs;

        String massWithoutCommas = mass.replace(",", "");
        KGs = Double.parseDouble(massWithoutCommas);
        LBs = KGs * 2.2;
        return (int)LBs;
    }

    private String centimeters2feet(String centimeters){

        if(!centimeters.equals("unknown")){
            double cm = Double.parseDouble(centimeters);

            int totalInches = (int)(cm/2.54);
            int inches = totalInches%12;
            int feet = totalInches/12;


            String height = feet + "'" + inches + "\"";
            return height;
        }else{
            return "unknown";
        }
    }

    private TextView CreateTitle(String color, String name){

        TextView title = new TextView(this);
        title.setAllCaps(false);
        title.setTypeface(font);
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(0, 48, 0, 24);
        title.setTextColor(Color.parseColor(color));
        title.setText(name);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);

        return title;
    }

    private void SetTypeFacePaddingColorSize(TextView textView, String color){

        textView.setPadding(PADDING_LEFT, 0, 0, 0);
        textView.setTypeface(font);
        textView.setTextColor(Color.parseColor(color));
        textView.setTextSize(FONT_SIZE);

    }

}
