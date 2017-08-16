package models.services;

import models.Config;
import models.WeatherInfo;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macie on 19.07.2017.
 */
public class WeatherService {
    //instancja do singletonu (tworzymy obiekt)
    private static WeatherService ourService = new WeatherService(new HttpService());
    public static WeatherService getService() {
        return ourService;
    }

    private List<IWeatherObserver> observerList = new ArrayList<>();
    private HttpService httpService;
    private double temp;
    private double pressure;
    private double humidity;
    private int cloudy;


    //konsruktor do singletonu
    private WeatherService(HttpService httpService){
        this.httpService = httpService;
    }

    public void makeCall (String city) throws IOException {
        parseJson(httpService.connectAndResponse(Config.APP_URL + "?q=" + city + "&appid=" + Config.APP_ID));
    }

    private  void parseJson(String json){
        JSONObject rootObject = new JSONObject(json);

        if(rootObject.getInt("cod") !=200){
            System.out.println("miasto nie istnieje");
            temp =0;
            humidity = 0;
            pressure =0;
            cloudy= 0;
            return;
        }


        JSONObject mainObject = rootObject.getJSONObject("main");
        temp = mainObject.getDouble("temp")-273;
        pressure = mainObject.getDouble("pressure");
        humidity = mainObject.getDouble("humidity");

        JSONObject cloudyObject = rootObject.getJSONObject("clouds");
        cloudy = cloudyObject.getInt("all");

        notifyOBservers();
    }

    public void registerObserver(IWeatherObserver observer){
        observerList.add(observer);
    }

    public void notifyOBservers(){
        WeatherInfo weatherInfo = new WeatherInfo(temp,pressure,humidity,cloudy);
        for(IWeatherObserver iWeatherObserver : observerList){
            iWeatherObserver.onWeatherUpdate(weatherInfo);
        }
    }
}
