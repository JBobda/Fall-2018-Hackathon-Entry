package dev.hackathon.hackermen.hackathonentry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Utilities {

    protected void Calculate(String rootobj) {
        System.out.println(rootobj);
    }

    protected void APICalls() {
        String locationName = "fayetteville";
        String sURL = "http://api.openweathermap.org/data/2.5/weather?APPID=8a76952c62d2687b3d5292d54d153fc7&q="+locationName;
        try {
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootobj = root.getAsJsonObject();
            String temp = rootobj.get("").getAsString();
        } catch(Exception e) {
            System.out.println("f " + e);
        }
    }




}
