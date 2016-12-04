package ru.itis.inform;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Иван on 16.11.2016.
 */

public class Weather {

    private String weatherUrl;

    public String[] getWeather() {
        return weather;
    }

    private String[] weather = new String[2];

    public double getTemp() {
        return temp;
    }

    private double temp;


    public Weather(String city) {
        this.weatherUrl =
                "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",ru&appid=5d3c4a0b75183c55d1a569c671f7d5a2";
    }

    public void runWeatherSearch() {

        URL url = getUrl(weatherUrl);

        String jsonString = parseUrl(url);

        System.out.println("JSON:\n" + jsonString);

        createJsonObject(jsonString);

    }

    public static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static URL getUrl(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createJsonObject(String resultJson) {
        try {
            JSONObject weatherJsonObject = (JSONObject) JSONValue.parseWithException(resultJson);

            JSONArray weatherArray = (JSONArray) weatherJsonObject.get("weather");

            JSONObject weatherData = (JSONObject) weatherArray.get(0);

            JSONObject object = (JSONObject) weatherJsonObject.get("main");

            temp = (double) object.get("temp");

            weather[0] = (String) weatherJsonObject.get("name");

            weather[1] = (String) weatherData.get("description");

        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

}
