package dev.hackathon.hackermen.hackathonentry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwabenaberko.openweathermaplib.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

public class MainActivity extends AppCompatActivity{

    private int tempMin;
    private final int[] tempMax = new int[1];
    private String weatherDescription;


    private OpenWeatherMapHelper helper;
    String locationString = "Fayetteville";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////// API Setup ////////////////
        helper = new OpenWeatherMapHelper();
        helper.setApiKey("8a76952c62d2687b3d5292d54d153fc7");
        helper.setUnits(Units.IMPERIAL);
        //////////////////////////////////////////

        getAverageTemp(locationString);
        setWeatherData(locationString, (TextView)findViewById(R.id.textView13));
        setTemperatureData(locationString, (TextView)findViewById(R.id.textView12));
        setHumidityData(locationString, (TextView)findViewById(R.id.textView7));
        setTempMax(locationString, (TextView)findViewById(R.id.textView8));
        setTempMin(locationString, (TextView)findViewById(R.id.textView9));
        setWindData(locationString, (TextView)findViewById(R.id.textView11));
        //setLocationData(locationString, (TextView)findViewById(R.id.textView10));
        setPrecipitationData(locationString, (TextView)findViewById(R.id.textView10));

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
                weatherData[0] = "temperature data "+Long.toString(Math.round(currentWeather.getMain().getTemp()));
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
                weatherData[0] = "humidity data: "+Double.toString(currentWeather.getMain().getHumidity());
                setTextOfView(weatherData[0], textView);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    protected int setTempMax(String location, final TextView textView) {
        final int[] weatherData = new int[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherData[0] = (int)currentWeather.getMain().getTempMax();
                setTextOfView(weatherData[0], textView);
                setGlobalTempMax(weatherData[0]);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    public void setGlobalTempMax(int tempMaxTemp){
        tempMax[0] = tempMaxTemp;
    }

    protected int setTempMin(String location, final TextView textView) {
        final int[] weatherData = new int[1];
        helper.getCurrentWeatherByCityName(location, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherData[0] = (int)currentWeather.getMain().getTempMax();
                setTextOfView(weatherData[0], textView);
                setGlobalTempMax(weatherData[0]);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("TAG", throwable.getMessage());
            }
        });
        return weatherData[0];
    }

    public void setGlobalTempMin(int tempMin){
        this.tempMin = tempMin;
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
                weatherData[0] = "precip: "+currentWeather.getWeatherArray().get(0).getDescription();
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
                int averageTemp = averageTempArray[0];
                if(averageTemp < 33) {
                    image.setImageResource(R.drawable.outfit0);
                } else if(averageTemp < 50) {
                    image.setImageResource(R.drawable.outfit1);
                } else if(averageTemp < 60) {
                    image.setImageResource(R.drawable.outfit2);
                } else if(averageTemp < 75) {
                    image.setImageResource(R.drawable.outfit4);
                } else if(averageTemp < 85) {
                    image.setImageResource(R.drawable.outfit3);
                } else if(averageTemp < 95) {
                    image.setImageResource(R.drawable.outfit5);
                } else if(averageTemp >= 95) {
                    image.setImageResource(R.drawable.outfit7);
                } else {
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

    public void setImage() {

    }

    public void setTextOfView(String input, TextView textView) {
        textView.setText(input);
    }

    public void setTextOfView(int input, TextView textView){
        String stringInput = Integer.toString(input);
        textView.setText(stringInput);
    }

}
