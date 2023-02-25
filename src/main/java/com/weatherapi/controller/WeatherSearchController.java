package com.weatherapi.controller;

import com.weatherapi.accessor.AccuWeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WeatherSearchController {

    @Autowired
    private AccuWeatherClient accuWeatherClient;

    /**
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat formDate = new SimpleDateFormat("yyyy-MM-dd");
        // String strDate = formDate.format(System.currentTimeMillis()); // option 1
        String strDate = formDate.format(new Date()); // option 2
        return strDate;
    }

    /**
     * @param input
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getWeather", method = RequestMethod.GET, produces = "application/json")
    public String getCurrentWeatherByCity(@RequestParam String input) throws IOException {
        return getResponseforWeather(input, "current", 1, getCurrentDate().toString());
    }

    /**
     * @param input
     * @param days
     * @param date
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getForecast", method = RequestMethod.GET, produces = "application/json")
    public String getWeatherForecast(@RequestParam String input, @RequestParam int days, @RequestParam String date)
            throws IOException {
        return getResponseforWeather(input, "forecast", days, date);
    }

    /**
     * @param address
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getWeatherNearestCity", method = RequestMethod.GET, produces = "application/json")

    public String getWeatherNearestCity(@RequestParam String address) throws IOException {
        String latlng = accuWeatherClient.getGeolocationKey(address);
        return getResponseforWeather(latlng, "current", 1, getCurrentDate());
    }

    /**
     * @param input
     * @param eventAction
     * @param days
     * @param date
     * @return
     * @throws IOException
     */
    public String getResponseforWeather(String input, String eventAction, int days, String date) throws IOException {

        String response = null;
        String inputType;
        if (Character.isDigit(input.charAt(0))) {
            inputType = "Integer";
        } else {
            inputType = "Character";
        }
        switch (inputType) {
            case "Integer":
                if (input.contains(",")) {
                    String[] locationKey = input.split(",");
                    response = accuWeatherClient.searchBylocationKey(locationKey[0], locationKey[1], eventAction, days, date);
                } else {
                    String postalCode = input;
                    response = accuWeatherClient.searchByCity(postalCode, eventAction, days, date);
                }
                break;
            case "Character":
                String cityName = input;
                response = accuWeatherClient.searchByCity(cityName, eventAction, days, date);
        }

        return response;
    }
}
