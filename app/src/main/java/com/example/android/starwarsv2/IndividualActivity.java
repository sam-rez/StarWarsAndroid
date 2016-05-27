package com.example.android.starwarsv2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.swapi.models.People;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sam on 5/17/16.
 */

public class IndividualActivity extends AppCompatActivity{

    ScrollView sv;
    LinearLayout ll;
    Typeface font;
    private String [] colors = {/*green*/ "#39FF14", /*red*/ "#FF2502", /*blue*/ "#0064FF"};
    final private int FONT_SIZE = 26;
    final private int PADDING_LEFT = 18;
    final private int PADDING_RIGHT = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        sv = new ScrollView(this);
        setContentView(sv);
        ll = new LinearLayout(this);

        sv.addView(ll);
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
                TextView people_name = CreateTitle(color_people, b.getString("name").toLowerCase());
                ll.addView(people_name);

                TextView people_height = new TextView(this);
                SetTypeFacePaddingColorSize(people_height, color_people);
                people_height.setText("height: " + centimeters2feet(b.getString("height")));
                ll.addView(people_height);

                TextView people_mass = new TextView(this);
                SetTypeFacePaddingColorSize(people_mass, color_people);
                if(!b.getString("mass").equals("unknown")) {
                    String massString = b.getString("mass");
                    String mass = convertKGtoLB(massString);
                    people_mass.setText("weight: " + mass + " lbs");
                }else{
                    people_mass.setText("weight: unknown");
                }
                ll.addView(people_mass);

                TextView hair_color = new TextView(this);
                SetTypeFacePaddingColorSize(hair_color, color_people);
                hair_color.setText("hair color: " + b.getString("hair_color").toLowerCase());
                ll.addView(hair_color);

                TextView skin_color = new TextView(this);
                SetTypeFacePaddingColorSize(skin_color, color_people);
                skin_color.setText("skin color: " + b.getString("skin_color").toLowerCase());
                ll.addView(skin_color);

                TextView birth_year = new TextView(this);
                SetTypeFacePaddingColorSize(birth_year, color_people);
                birth_year.setText("birth year: " + b.getString("birth_year").toLowerCase());
                ll.addView(birth_year);

                TextView gender = new TextView(this);
                SetTypeFacePaddingColorSize(gender, color_people);
                gender.setText("gender: " + b.getString("gender").toLowerCase());
                ll.addView(gender);

                break;

            case MainActivity.PLANETS:

                String color_planets = getRandom(colors);

                TextView planet_name = CreateTitle(color_planets, b.getString("name").toLowerCase());
                ll.addView(planet_name);

                TextView population = new TextView(this);
                SetTypeFacePaddingColorSize(population, color_planets);
                population.setText("population: " + b.getString("population").toLowerCase());
                ll.addView(population);

                TextView diameter = new TextView(this);
                SetTypeFacePaddingColorSize(diameter, color_planets);
                diameter.setText("diameter: " + b.getString("diameter").toLowerCase());
                ll.addView(diameter);

                TextView climate = new TextView(this);
                SetTypeFacePaddingColorSize(climate, color_planets);
                climate.setText("climate: " + b.getString("climate").toLowerCase());
                ll.addView(climate);

                TextView terrain = new TextView(this);
                SetTypeFacePaddingColorSize(terrain, color_planets);
                terrain.setText("terrain: " + b.getString("terrain").toLowerCase());
                ll.addView(terrain);

                TextView rotation_period = new TextView(this);
                SetTypeFacePaddingColorSize(rotation_period, color_planets);
                rotation_period.setText("rotation period: " + b.getString("rotation_period").toLowerCase());
                ll.addView(rotation_period);

                TextView gravity = new TextView(this);
                SetTypeFacePaddingColorSize(gravity, color_planets);
                gravity.setText("gravity: " + b.getString("gravity").toLowerCase());
                ll.addView(gravity);

                break;

            case MainActivity.FILMS:

                String color_films = getRandom(colors);

                TextView film_name = CreateTitle(color_films, b.getString("title").toLowerCase());
                ll.addView(film_name);

                TextView episode = new TextView(this);
                SetTypeFacePaddingColorSize(episode, color_films);
                int episodeInt = b.getInt("episode");
                String episodeString = Integer.toString(episodeInt);
                episode.setText("episode: " + episodeString);
                ll.addView(episode);

                TextView director = new TextView(this);
                SetTypeFacePaddingColorSize(director, color_films);
                director.setText("director: " + b.getString("director").toLowerCase());
                ll.addView(director);

                TextView opening_crawl = new TextView(this);
                SetTypeFacePaddingColorSize(opening_crawl, color_films);
                opening_crawl.setTextSize(16);
                String openingCrawl = b.getString("opening_crawl").toLowerCase();
                opening_crawl.setText(openingCrawl);
                ll.addView(opening_crawl);

                break;

            case MainActivity.STARSHIPS:


                String color_starships = getRandom(colors);

                TextView starship_name = CreateTitle(color_starships, b.getString("name").toLowerCase());
                ll.addView(starship_name);

                TextView manufacturer = new TextView(this);
                SetTypeFacePaddingColorSize(manufacturer, color_starships);
                manufacturer.setText("manufacturer: " + b.getString("manufacturer").toLowerCase());
                ll.addView(manufacturer);

                TextView model = new TextView(this);
                SetTypeFacePaddingColorSize(model, color_starships);
                manufacturer.setText("model: " + b.getString("model").toLowerCase());
                ll.addView(model);

                TextView class_starship = new TextView(this);
                SetTypeFacePaddingColorSize(class_starship, color_starships);
                manufacturer.setText("class: " + b.getString("class").toLowerCase());
                ll.addView(class_starship);

                TextView cost = new TextView(this);
                SetTypeFacePaddingColorSize(cost, color_starships);
                manufacturer.setText("cost: " + b.getString("cost").toLowerCase());
                ll.addView(cost);

                TextView length = new TextView(this);
                SetTypeFacePaddingColorSize(length, color_starships);
                manufacturer.setText("length: " + b.getString("length").toLowerCase());
                ll.addView(length);

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

    private String convertKGtoLB(String mass){

        double KGs, LBs;

        String massWithoutCommas = mass.replace(",", "");
        KGs = Double.parseDouble(massWithoutCommas);
        LBs = KGs * 2.2;
        return new DecimalFormat("#.#").format(LBs);
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
        title.setTextColor(Color.WHITE);
        title.setShadowLayer(40,0,0,Color.parseColor(color));
        title.setText(name);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);

        return title;
    }

    private void SetTypeFacePaddingColorSize(TextView textView, String color){

        textView.setPadding(PADDING_LEFT, 0, PADDING_RIGHT, 0);
        textView.setTypeface(font);
        textView.setTextColor(Color.WHITE);
        textView.setShadowLayer(40,0,0,Color.parseColor(color));
        textView.setTextSize(FONT_SIZE);

    }

}
