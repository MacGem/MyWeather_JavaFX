package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import models.WeatherInfo;
import models.services.HttpService;
import models.services.IWeatherObserver;
import models.services.WeatherService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, IWeatherObserver{
    @FXML
    Button buttonShowWeather;

    @FXML
    TextField textCity;

    @FXML
    Label labelWeather;



    private WeatherService weatherService = WeatherService.getService();

    public void initialize(URL location, ResourceBundle resources) {
        buttonShowWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    weatherService.makeCall(textCity.getText());
//                    labelWeather.setText("Temperatura: "+ weatherService.getTemp() +"\n"
//                    + "Cisnienie: " + weatherService.getHumidity()+"\n"
//                    + "Chmura: " + weatherService.getCloudy() +"\n"
//                    + "Wilgotnosc: " + weatherService.getPressure());

                    //zwykle wyswitlanie
//                    weatherService.makeCall(textCity.getText());
//                    System.out.println("temp: " + weatherService.getTemp());
//                    System.out.println("pressure: " + weatherService.getPressure());
//                    System.out.println("humidity: " + weatherService.getHumidity());
//                    System.out.println("cloudy:" + weatherService.getCloudy());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onWeatherUpdate(WeatherInfo info) {
        System.out.println("pogoda zaktualizowana!!!!!!!!!!!!");
        textCity.setText("Temperatura: "+ info.getTemp() +"\n"
                    + "Cisnienie: " + info.getPressure()+"\n"
                    + "Chmura: " + info.getCloudy() +"\n"
                    + "Wilgotnosc: " + info.getHumidity());

    }

//    private void handleKeyPressed(KeyEvent e) {
//        if (e.getCode() == KeyCode.ENTER) {
//            textCity.getText();
//            textCity.clear();
//        }
//    }
}