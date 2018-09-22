package dev.hackathon.hackermen.hackathonentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.kwabenaberko.openweathermaplib.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

public class MainActivity extends AppCompatActivity{

    public String temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenWeatherMapHelper helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);


        helper.getCurrentWeatherByCityName("Chicago", new OpenWeatherMapHelper.CurrentWeatherCallback() {

            @Override
            public void onSuccess(CurrentWeather currentWeather) {


                Log.i("TAG",
                        "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon()+"\n"
                                +"Weather Description: " + currentWeather.getWeatherArray().get(0).getDescription() + "\n"
                                +"Max Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                                +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                                +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                );


                temperature = currentWeather.getWeatherArray().get(0).getDescription();
            }

            @Override
            public void onFailure(Throwable throwable) {


                Log.i("TAG", throwable.getMessage());

            }
        });
        

    }

}
