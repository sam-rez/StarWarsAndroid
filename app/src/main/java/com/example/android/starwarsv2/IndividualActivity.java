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
        sv.setBackgroundResource(R.drawable.background);

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

        String shadowColor = getRandom(colors);

        switch (category){
            case MainActivity.PEOPLE:

                String color_people = getRandom(colors);
                String peopleName = b.getString("name").toLowerCase();
                TextView people_name = CreateTitle(color_people, peopleName);
                ll.addView(people_name);

                TextView people_height = new TextView(this);
                SetTypeFacePaddingColorShadowSize(people_height, color_people);
                people_height.setText("height: " + centimeters2feet(b.getString("height")));
                ll.addView(people_height);

                TextView people_mass = new TextView(this);
                SetTypeFacePaddingColorShadowSize(people_mass, color_people);
                if(!b.getString("mass").equals("unknown")) {
                    String massString = b.getString("mass");
                    String mass = convertKGtoLB(massString);
                    people_mass.setText("weight: " + mass + " lbs");
                }else{
                    people_mass.setText("weight: unknown");
                }
                ll.addView(people_mass);

                TextView hair_color = new TextView(this);
                SetTypeFacePaddingColorShadowSize(hair_color, color_people);
                hair_color.setText("hair color: " + b.getString("hair_color").toLowerCase());
                ll.addView(hair_color);

                TextView skin_color = new TextView(this);
                SetTypeFacePaddingColorShadowSize(skin_color, color_people);
                skin_color.setText("skin color: " + b.getString("skin_color").toLowerCase());
                ll.addView(skin_color);

                TextView birth_year = new TextView(this);
                SetTypeFacePaddingColorShadowSize(birth_year, color_people);
                birth_year.setText("birth year: " + b.getString("birth_year").toLowerCase());
                ll.addView(birth_year);

                TextView gender = new TextView(this);
                SetTypeFacePaddingColorShadowSize(gender, color_people);
                gender.setText("gender: " + b.getString("gender").toLowerCase());
                ll.addView(gender);

                break;

            case MainActivity.PLANETS:

                TextView planet_name = CreateTitle(shadowColor, b.getString("name").toLowerCase());
                ll.addView(planet_name);

                TextView population = new TextView(this);
                SetTypeFacePaddingColorShadowSize(population, shadowColor);
                population.setText("population: " + b.getString("population").toLowerCase());
                ll.addView(population);

                TextView diameter = new TextView(this);
                SetTypeFacePaddingColorShadowSize(diameter, shadowColor);
                if(b.getString("diameter").equals("unknown")){
                    diameter.setText("diameter: unknown");
                }else {
                    diameter.setText("diameter: " + b.getString("diameter").toLowerCase() + " kms");
                }
                ll.addView(diameter);

                TextView climate = new TextView(this);
                SetTypeFacePaddingColorShadowSize(climate, shadowColor);
                climate.setText("climate: " + b.getString("climate").toLowerCase());
                ll.addView(climate);

                TextView terrain = new TextView(this);
                SetTypeFacePaddingColorShadowSize(terrain, shadowColor);
                terrain.setText("terrain: " + b.getString("terrain").toLowerCase());
                ll.addView(terrain);

                TextView rotation_period = new TextView(this);
                SetTypeFacePaddingColorShadowSize(rotation_period, shadowColor);
                if(b.getString("rotation_period").equals("unknown")){
                    rotation_period.setText("rotation period: unknown");

                }else {
                    rotation_period.setText("rotation period: " + b.getString("rotation_period").toLowerCase() + " hours");
                }
                ll.addView(rotation_period);

                TextView orbital_period = new TextView(this);
                SetTypeFacePaddingColorShadowSize(orbital_period, shadowColor);
                if(b.getString("orbital_period").equals("unknown")){
                    orbital_period.setText("orbital period: unknown");

                }else {
                    orbital_period.setText("orbital period: " + b.getString("orbital_period").toLowerCase() + " days");
                }
                ll.addView(orbital_period);

                TextView gravity = new TextView(this);
                SetTypeFacePaddingColorShadowSize(gravity, shadowColor);
                gravity.setText("gravity: " + b.getString("gravity").toLowerCase());
                ll.addView(gravity);

                TextView surface_water = new TextView(this);
                SetTypeFacePaddingColorShadowSize(surface_water, shadowColor);
                if(b.getString("surface_water").equals("unknown")) {
                    surface_water.setText("surface water: unknown");
                }else{
                    surface_water.setText("surface water: " + b.getString("surface_water").toLowerCase() + " percent");
                }
                ll.addView(surface_water);

                break;

            case MainActivity.FILMS:


                TextView film_name = CreateTitle(shadowColor, b.getString("title").toLowerCase());
                ll.addView(film_name);

                TextView episode = new TextView(this);
                SetTypeFacePaddingColorShadowSize(episode, shadowColor);
                int episodeInt = b.getInt("episode");
                String episodeString = Integer.toString(episodeInt);
                episode.setText("episode: " + episodeString);
                ll.addView(episode);

                TextView director = new TextView(this);
                SetTypeFacePaddingColorShadowSize(director, shadowColor);
                director.setText("director: " + b.getString("director").toLowerCase());
                ll.addView(director);

                TextView opening_crawl = new TextView(this);
                SetTypeFacePaddingColorShadowSize(opening_crawl, shadowColor);
                opening_crawl.setTextSize(16);
                String openingCrawl = b.getString("opening_crawl").toLowerCase();
                opening_crawl.setText(openingCrawl);
                ll.addView(opening_crawl);

                break;

            case MainActivity.STARSHIPS:

                TextView starship_name = CreateTitle(shadowColor, b.getString("name").toLowerCase());
                ll.addView(starship_name);

                TextView manufacturer = new TextView(this);
                SetTypeFacePaddingColorShadowSize(manufacturer, shadowColor);
                manufacturer.setText("manufacturer: " + b.getString("manufacturer").toLowerCase());
                ll.addView(manufacturer);

                TextView model = new TextView(this);
                SetTypeFacePaddingColorShadowSize(model, shadowColor);
                model.setText("model: " + b.getString("model").toLowerCase());
                ll.addView(model);

                TextView class_starship = new TextView(this);
                SetTypeFacePaddingColorShadowSize(class_starship, shadowColor);
                class_starship.setText("class: " + b.getString("class").toLowerCase());
                ll.addView(class_starship);

                TextView cost = new TextView(this);
                SetTypeFacePaddingColorShadowSize(cost, shadowColor);
                if(b.getString("cost").equals("unknown")){
                    cost.setText("cost: unknown");
                }else {
                    cost.setText("cost: " + b.getString("cost").toLowerCase() + " galactic credits");
                }
                ll.addView(cost);

                TextView length = new TextView(this);
                SetTypeFacePaddingColorShadowSize(length, shadowColor);
                if(b.getString("length").equals("unknown")){
                    length.setText("length: unknown");
                }else {
                    length.setText("length: " + b.getString("length").toLowerCase() + " meters");
                }
                ll.addView(length);

                TextView crew = new TextView(this);
                SetTypeFacePaddingColorShadowSize(crew, shadowColor);
                crew.setText("crew size: " + b.getString("crew").toLowerCase());
                ll.addView(crew);

                TextView passengers = new TextView(this);
                SetTypeFacePaddingColorShadowSize(passengers, shadowColor);
                passengers.setText("passenger capacity: " + b.getString("passengers").toLowerCase());
                ll.addView(passengers);

                TextView speed = new TextView(this);
                SetTypeFacePaddingColorShadowSize(speed, shadowColor);
                if(b.getString("speed").equals("unknown")){
                    speed.setText("max speed: n/a");
                }else {
                    speed.setText("max speed: " + b.getString("speed").toLowerCase() + " kms");
                }
                ll.addView(speed);

                TextView consumables = new TextView(this);
                SetTypeFacePaddingColorShadowSize(consumables, shadowColor);
                consumables.setText("consumables: " + b.getString("consumables").toLowerCase());
                ll.addView(consumables);

                break;

            case MainActivity.VEHICLES:

                TextView vehicle_name = CreateTitle(shadowColor, b.getString("name").toLowerCase());
                ll.addView(vehicle_name);

                TextView manufacturer_vehicle = new TextView(this);
                SetTypeFacePaddingColorShadowSize(manufacturer_vehicle, shadowColor);
                manufacturer_vehicle.setText("manufacturer: " + b.getString("manufacturer").toLowerCase());
                ll.addView(manufacturer_vehicle);

                TextView class_vehicle = new TextView(this);
                SetTypeFacePaddingColorShadowSize(class_vehicle, shadowColor);
                class_vehicle.setText("class: " + b.getString("class").toLowerCase());
                ll.addView(class_vehicle);

                TextView cost_vehicle = new TextView(this);
                SetTypeFacePaddingColorShadowSize(cost_vehicle, shadowColor);
                if(b.getString("cost").equals("unknown")){
                    cost_vehicle.setText("cost: unknown");
                }else {
                    cost_vehicle.setText("cost: " + b.getString("cost").toLowerCase() + " galactic credits");
                }
                ll.addView(cost_vehicle);

                TextView length_vehicle = new TextView(this);
                SetTypeFacePaddingColorShadowSize(length_vehicle, shadowColor);
                if(b.getString("length").equals("unknown")){
                    length_vehicle.setText("length: unknown");
                }else {
                    length_vehicle.setText("length: " + b.getString("length").toLowerCase() + " meters");
                }
                ll.addView(length_vehicle);

                TextView crew_vehicle = new TextView(this);
                SetTypeFacePaddingColorShadowSize(crew_vehicle, shadowColor);
                crew_vehicle.setText("crew size: " + b.getString("crew").toLowerCase());
                ll.addView(crew_vehicle);

                TextView passengers_vehicle = new TextView(this);
                SetTypeFacePaddingColorShadowSize(passengers_vehicle, shadowColor);
                passengers_vehicle.setText("passenger capacity: " + b.getString("passengers").toLowerCase());
                ll.addView(passengers_vehicle);

                TextView consumables_vehicle = new TextView(this);
                SetTypeFacePaddingColorShadowSize(consumables_vehicle, shadowColor);
                consumables_vehicle.setText("consumables: " + b.getString("consumables").toLowerCase());
                ll.addView(consumables_vehicle);

                break;

            case MainActivity.SPECIES:

                TextView species_name = CreateTitle(shadowColor, b.getString("name").toLowerCase());
                ll.addView(species_name);

                TextView classification = new TextView(this);
                SetTypeFacePaddingColorShadowSize(classification, shadowColor);
                classification.setText("classification: " + b.getString("classification").toLowerCase());
                ll.addView(classification);

                TextView designation = new TextView(this);
                SetTypeFacePaddingColorShadowSize(designation, shadowColor);
                designation.setText("designation: " + b.getString("designation").toLowerCase());
                ll.addView(designation);

                TextView average_height = new TextView(this);
                SetTypeFacePaddingColorShadowSize(average_height, shadowColor);
                String heightInFeet = centimeters2feet(b.getString("average_height"));
                average_height.setText("average height: " + heightInFeet);
                ll.addView(average_height);

                TextView average_lifespan = new TextView(this);
                SetTypeFacePaddingColorShadowSize(average_lifespan, shadowColor);
                if(b.getString("average_lifespan").equals("unknown")){
                    average_lifespan.setText("average lifespan: unknown");
                }else if(b.getString("average_lifespan").equals("indefinite")){
                    average_lifespan.setText("average lifespan: indefinite");
                }else {
                    average_lifespan.setText("average lifespan: " + b.getString("average_lifespan").toLowerCase() + " years");
                }
                ll.addView(average_lifespan);

                TextView languages = new TextView(this);
                SetTypeFacePaddingColorShadowSize(languages, shadowColor);
                languages.setText("languages: " + b.getString("language").toLowerCase());
                ll.addView(languages);

                TextView eye_colors = new TextView(this);
                SetTypeFacePaddingColorShadowSize(eye_colors, shadowColor);
                eye_colors.setText("eye colors: " + b.getString("eye_colors").toLowerCase());
                ll.addView(eye_colors);

                TextView hair_colors = new TextView(this);
                SetTypeFacePaddingColorShadowSize(hair_colors, shadowColor);
                hair_colors.setText("hair colors: " + b.getString("hair_colors").toLowerCase());
                ll.addView(hair_colors);

                TextView skin_colors = new TextView(this);
                SetTypeFacePaddingColorShadowSize(skin_colors, shadowColor);
                skin_colors.setText("skin colors: " + b.getString("skin_colors").toLowerCase());
                ll.addView(skin_colors);

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

        if(!centimeters.equals("unknown") && !centimeters.equals("n/a")){
            double cm = Double.parseDouble(centimeters);

            int totalInches = (int)(cm/2.54);
            int inches = totalInches%12;
            int feet = totalInches/12;


            String height = feet + "'" + inches + "\"";
            return height;
        }else if (centimeters.equals("unknown")){
            return "unknown";
        }else
            return "\nn/a";
    }

    private TextView CreateTitle(String shadow_color, String name){

        TextView title = new TextView(this);
        title.setAllCaps(false);
        title.setTypeface(font);
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(0, 48, 0, 24);
        title.setTextColor(Color.WHITE);
        title.setShadowLayer(20,0,0,Color.parseColor(shadow_color));
        title.setText(name);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);

        return title;
    }

    private void SetTypeFacePaddingColorShadowSize(TextView textView, String shadowColor){

        textView.setPadding(PADDING_LEFT, 0, PADDING_RIGHT, 0);
        textView.setTypeface(font);
        textView.setTextColor(Color.WHITE);
        textView.setShadowLayer(20,0,0,Color.parseColor(shadowColor));
        textView.setTextSize(FONT_SIZE);

    }

}
