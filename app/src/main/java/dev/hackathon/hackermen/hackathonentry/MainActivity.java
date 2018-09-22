package dev.hackathon.hackermen.hackathonentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kwabenaberko.openweathermaplib.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWeatherData("Fayetteville", (TextView)findViewById(R.id.textView13));
        setTemperatureData("Fayetteville", (TextView)findViewById(R.id.textView12));
        setHumidityData("Fayetteville", (TextView)findViewById(R.id.textView7));
        setTempMax("Fayetteville", (TextView)findViewById(R.id.textView8));
        setTempMin("Fayetteville", (TextView)findViewById(R.id.textView9));
        setWindData("Fayetteville", (TextView)findViewById(R.id.textView10));
        setLocationData("Fayetteville", (TextView)findViewById(R.id.textView11));
    }

    protected void setWeatherData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                Log.i("TAG",
                        "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon()+"\n"
                                +"Weather Description: " + currentWeather.getWeatherArray().get(0).getDescription() + "\n"
                                +"Max Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                                +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                                +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                );
                weatherData[0] = currentWeather.getWeatherArray().get(0).getDescription();
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }

        });
    }

    protected void setTemperatureData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                Log.i("TAG",
                        "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon()+"\n"
                                +"Weather Description: " + currentWeather.getWeatherArray().get(0).getDescription() + "\n"
                                +"Max Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                                +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                                +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                );
                weatherData[0] = Long.toString(Math.round(currentWeather.getMain().getTemp()));
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }

        });
    }

    protected void setHumidityData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                Log.i("TAG",
                        "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon()+"\n"
                                +"Weather Description: " + currentWeather.getWeatherArray().get(0).getDescription() + "\n"
                                +"Max Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                                +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                                +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                );
                weatherData[0] = Double.toString(currentWeather.getMain().getHumidity());
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }

        });
    }

    protected void setTempMax(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                Log.i("TAG",
                        "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon()+"\n"
                                +"Weather Description: " + currentWeather.getWeatherArray().get(0).getDescription() + "\n"
                                +"Max Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                                +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                                +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                );
                weatherData[0] = Double.toString(currentWeather.getMain().getTempMax());
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }

        });
    }

    protected void setTempMin(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                Log.i("TAG",
                        "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon()+"\n"
                                +"Weather Description: " + currentWeather.getWeatherArray().get(0).getDescription() + "\n"
                                +"Max Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                                +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                                +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                );
                weatherData[0] = Double.toString(currentWeather.getMain().getTempMin());
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }

        });
    }

    protected void setWindData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                Log.i("TAG",
                        "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon()+"\n"
                                +"Weather Description: " + currentWeather.getWeatherArray().get(0).getDescription() + "\n"
                                +"Max Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                                +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                                +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                );
                weatherData[0] = Double.toString(currentWeather.getWind().getSpeed());
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }

        });
    }

    protected void setLocationData(String location, final TextView textView) {
        final String[] weatherData = new String[1];
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                Log.i("TAG",
                        "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon()+"\n"
                                +"Weather Description: " + currentWeather.getWeatherArray().get(0).getDescription() + "\n"
                                +"Max Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                                +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                                +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                );
                weatherData[0] = currentWeather.getName();
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }

        });
    }


    public void setTextOfView(String input, TextView textView) {
        textView.setText(input);
    }

}
