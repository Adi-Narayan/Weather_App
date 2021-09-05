package android.example.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

public class weatherData
{
    private String mTemperature, mIcon, mcity, mweatherType;
    private int mCondition;

    public static weatherData fromJson(JSONObject jsonObject)
    {
        try
        {
            weatherData weatherD = new weatherData();
            weatherD.mcity = jsonObject.getString("name");
            weatherD.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherD.mweatherType = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            weatherD.mIcon = updateWeatherIcon(weatherD.mCondition);
            double tempResult = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;
            int roundedValue = (int)Math.rint(tempResult);
            weatherD.mTemperature = Integer.toString(roundedValue);
            return weatherD;
        }
        catch(JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    private static String updateWeatherIcon(int condition)
    {
        if(condition>=0 && condition<300)
        {
            return "thunderstorm";
        }
        else if(condition>=300 && condition<400)
        {
            return "lightrain";
        }
        else if(condition>=400 && condition<600)
        {
            return "rain";
        }
        else if(condition>=600 && condition<700)
        {
            return "snow";
        }
        else if(condition>700 && condition<=771)
        {
            return "fog";
        }
        if(condition==781)
        {
            return "tornado";
        }
        if(condition==800)
        {
            return "clear sky";
        }
        else if(condition==801 || condition==802)
        {
            return "cloudy";
        }
        else if(condition==803||condition==804)
        {
            return "overcast";
        }
        else
        return "Weather couldn't be found.";


    }

    public String getmTemperature()
    {
        return mTemperature+" °C";
    }

    public String getmIcon()
    {
        return mIcon;
    }
    public String getMcity()
    {
        return mcity;
    }

    public String getMweatherType()
    {
        return mweatherType;
    }
}
