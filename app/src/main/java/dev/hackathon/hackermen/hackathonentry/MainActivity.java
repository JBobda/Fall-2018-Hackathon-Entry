package dev.hackathon.hackermen.hackathonentry;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwabenaberko.openweathermaplib.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private OpenWeatherMapHelper helper;
    private DrawerLayout drawer;
    private String locationString = Container.locationString;

    private int tempMin;
    private final int[] tempMax = new int[1];
    private String weatherDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //locationString = Container.locationString;

        /////////////// API Setup ////////////////
        helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);
        ////////////////////////////////////////

        getAverageTemp(Container.locationString);
        setWeatherData(Container.locationString, (TextView)findViewById(R.id.textView13));
        //setTemperatureData(locationString, (TextView)findViewById(R.id.textView12));
        setTemperatureData(Container.locationString, (TextView)findViewById(R.id.textView5));
        setHumidityData(Container.locationString, (TextView)findViewById(R.id.textView7));
        //setTempMax(locationString, (TextView)findViewById(R.id.textView8));
        //setTempMin(locationString, (TextView)findViewById(R.id.textView9));
        //setWindData(locationString, (TextView)findViewById(R.id.textView11));
        setLocationData(Container.locationString, (TextView)findViewById(R.id.textView12));
        //setPrecipitationData(locationString, (TextView)findViewById(R.id.textView12));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_message:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new MessageFragment()).commit();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }

    }

    public void onClick(View view){
        TextInputLayout tpl = (TextInputLayout) findViewById(R.id.textInputLayout);
        Container.locationString = tpl.getEditText().getText().toString();
        Log.i("LOCATION: ", Container.locationString);

        /*
        getAverageTemp(locationString);
        setWeatherData(locationString, (TextView)findViewById(R.id.textView13));
        //setTemperatureData(locationString, (TextView)findViewById(R.id.textView12));
        setTemperatureData(locationString, (TextView)findViewById(R.id.textView5));
        setHumidityData(locationString, (TextView)findViewById(R.id.textView7));
        //setTempMax(locationString, (TextView)findViewById(R.id.textView8));
        //setTempMin(locationString, (TextView)findViewById(R.id.textView9));
        //setWindData(locationString, (TextView)findViewById(R.id.textView11));
        setLocationData(locationString, (TextView)findViewById(R.id.textView12));
        //setPrecipitationData(locationString, (TextView)findViewById(R.id.textView12));
        */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    public void openNavigator(View view){
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }

    protected String setWeatherData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherData[0] = currentWeather.getWeatherArray().get(0).getDescription();
                String dataID = currentWeather.getId().toString();
                setTextOfView(weatherData[0], textView);
            }
            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    protected String setTemperatureData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherData[0] = Long.toString(Math.round(currentWeather.getMain().getTemp())) + "°";
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    protected String setHumidityData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherData[0] = "Humidity "+Integer.toString((int)(currentWeather.getMain().getHumidity()))+"%";
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    protected String setTempMax(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherData[0] = "max temp:"+Double.toString(currentWeather.getMain().getTempMax());
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    protected String setTempMin(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherData[0] = "min temp"+Double.toString(currentWeather.getMain().getTempMin());
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    protected int getAverageTemp(String location) {
        final int[] averageTempArray = new int[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                averageTempArray[0] = (int) ((currentWeather.getMain().getTempMax() + currentWeather.getMain().getTempMin()) / 2);
                ImageView image = (ImageView) findViewById(R.id.imageView2);
                String[] misc = new String[] {"Beanie", "No misc.", "No misc.", "No misc.", "No misc.", "Sunglasses", "Sunglasses", "No misc."};
                String[] jacket = new String[] {"Heavy Coat", "Hoodie", "No Jacket", "No Jacket", "No Jacket", "No Jacket", "No Jacket", "Raincoat"};
                String[] shirt = new String[] {"Long-sleeve", "Long-sleeve", "Long-sleeve", "T-shirt", "T-shirt", "T-shirt", "Tanktop", "T-shirt"};
                String[] pants = new String[] {"Winter Pants", "Blue Jeans", "Blue Jeans", "Khakis", "Shorts", "Shorts", "Shorts", "Shorts"};
                int averageTemp = averageTempArray[0];
                if(currentWeather.getWeatherArray().get(0).getDescription().contains("rain")) {
                    setTextOfView(misc[7], (TextView) findViewById(R.id.textView11));
                    setTextOfView(jacket[7], (TextView) findViewById(R.id.textView10));
                    setTextOfView(shirt[7], (TextView) findViewById(R.id.textView8));
                    setTextOfView(pants[7], (TextView) findViewById(R.id.textView9));
                    image.setImageResource(R.drawable.outfit8);
                } else if(averageTemp < 33) {
                    setTextOfView(misc[0], (TextView) findViewById(R.id.textView11));
                    setTextOfView(jacket[0], (TextView) findViewById(R.id.textView10));
                    setTextOfView(shirt[0], (TextView) findViewById(R.id.textView8));
                    setTextOfView(pants[0], (TextView) findViewById(R.id.textView9));
                    image.setImageResource(R.drawable.outfit0);
                } else if(averageTemp < 50) {
                    setTextOfView(misc[1], (TextView) findViewById(R.id.textView11));
                    setTextOfView(jacket[1], (TextView) findViewById(R.id.textView10));
                    setTextOfView(shirt[1], (TextView) findViewById(R.id.textView8));
                    setTextOfView(pants[1], (TextView) findViewById(R.id.textView9));
                    image.setImageResource(R.drawable.outfit1);
                } else if(averageTemp < 60) {
                    setTextOfView(misc[2], (TextView) findViewById(R.id.textView11));
                    setTextOfView(jacket[2], (TextView) findViewById(R.id.textView10));
                    setTextOfView(shirt[2], (TextView) findViewById(R.id.textView8));
                    setTextOfView(pants[2], (TextView) findViewById(R.id.textView9));
                    image.setImageResource(R.drawable.outfit2);
                } else if(averageTemp < 75) {
                    setTextOfView(misc[3], (TextView) findViewById(R.id.textView11));
                    setTextOfView(jacket[3], (TextView) findViewById(R.id.textView10));
                    setTextOfView(shirt[3], (TextView) findViewById(R.id.textView8));
                    setTextOfView(pants[3], (TextView) findViewById(R.id.textView9));
                    image.setImageResource(R.drawable.outfit4);
                } else if(averageTemp < 85) {
                    setTextOfView(misc[4], (TextView) findViewById(R.id.textView11));
                    setTextOfView(jacket[4], (TextView) findViewById(R.id.textView10));
                    setTextOfView(shirt[4], (TextView) findViewById(R.id.textView8));
                    setTextOfView(pants[4], (TextView) findViewById(R.id.textView9));
                    image.setImageResource(R.drawable.outfit3);
                } else if(averageTemp < 95) {
                    setTextOfView(misc[5], (TextView) findViewById(R.id.textView11));
                    setTextOfView(jacket[5], (TextView) findViewById(R.id.textView10));
                    setTextOfView(shirt[5], (TextView) findViewById(R.id.textView8));
                    setTextOfView(pants[5], (TextView) findViewById(R.id.textView9));
                    image.setImageResource(R.drawable.outfit5);
                } else if(averageTemp >= 95) {
                    setTextOfView(misc[6], (TextView) findViewById(R.id.textView11));
                    setTextOfView(jacket[6], (TextView) findViewById(R.id.textView10));
                    setTextOfView(shirt[6], (TextView) findViewById(R.id.textView8));
                    setTextOfView(pants[6], (TextView) findViewById(R.id.textView9));
                    image.setImageResource(R.drawable.outfit7);
                } else {
                    setTextOfView(misc[3], (TextView) findViewById(R.id.textView11));
                    setTextOfView(jacket[3], (TextView) findViewById(R.id.textView10));
                    setTextOfView(shirt[3], (TextView) findViewById(R.id.textView8));
                    setTextOfView(pants[3], (TextView) findViewById(R.id.textView9));
                    image.setImageResource(R.drawable.outfit4);
                }
            }
            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return averageTempArray[0];
    }

    protected String setWindData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherData[0] = "winddata"+Double.toString(currentWeather.getWind().getSpeed())+" "+Double.toString(currentWeather.getWind().getDeg());
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    protected String setLocationData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherData[0] = currentWeather.getName();
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    protected String setPrecipitationData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                boolean isRaining = false;
                if(currentWeather.getWeatherArray().get(0).getDescription().contains("rain")) {
                    isRaining = true;
                }
                weatherData[0] = "precip: "+isRaining;
                setTextOfView(weatherData[0], textView);
            }
            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    public int Calculate() {
        double tempMax = Double.parseDouble(setTempMax(locationString, (TextView)findViewById(R.id.textView8)));
        double tempMin = Double.parseDouble(setTempMin(locationString, (TextView)findViewById(R.id.textView9)));
        double averageTemp = (tempMax - tempMin) + tempMin;
        if(averageTemp < 33) {
            return 0;
        } else if(averageTemp < 50) {
            return 1;
        } else if(averageTemp < 60) {
            return 2;
        }
        return 0;
    }

    public void setTextOfView(String input, TextView textView) {
        textView.setText(input);
    }

}
