package models;

/**
 * Created by macie on 19.07.2017.
 */
public class WeatherInfo {
    private double temp;
    private double pressure;
    private double humidity;
    private int cloudy;

    public WeatherInfo(double temp, double pressure, double humidity, int cloudy) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.cloudy = cloudy;
    }

    public double getTemp() { return temp; }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public int getCloudy() {
        return cloudy;
    }
}
