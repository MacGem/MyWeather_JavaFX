package models;

import models.services.IWeatherObserver;
import models.services.WeatherService;

/**
 * Created by macie on 16.08.2017.
 */
public class WeatherObserver implements IWeatherObserver {
    WeatherService weatherService =  WeatherService.getService();

    public WeatherObserver() {
        weatherService.registerObserver(this);
    }

    @Override
    public void onWeatherUpdate(WeatherInfo info) {
        System.out.println("obserwuje pogode!");
    }
}
