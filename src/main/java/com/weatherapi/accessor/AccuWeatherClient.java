package com.weatherapi.accessor;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Accu weather client created to fetch weather details using several external APIs.
 */
@Component
public class AccuWeatherClient {
    /**
     * Get weather details from rapid API's weather API as per eventAction required and city name.
     * @param city
     * @param eventAction
     * @param days
     * @param date
     * @return
     * @throws IOException
     */
    public String searchByCity(String city, String eventAction, int days, String date) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String weatherURL = "";
        if (eventAction.equals("current")) {
            weatherURL = "https://weatherapi-com.p.rapidapi.com/current.json?q=" + city;
        } else {
            weatherURL = "https://weatherapi-com.p.rapidapi.com/forecast.json?q=" + city + "&days=" + days + "&dt=" + date;
        }
        Request request = new Request.Builder()
                .url(weatherURL)
                .get()
                .addHeader("X-RapidAPI-Key", System.getenv("X-RapidAPI-Key"))
                .addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Get weather details using location key.
     * @param latitude
     * @param longitude
     * @param eventAction
     * @param days
     * @param date
     * @return
     * @throws IOException
     */
    public String searchBylocationKey(String latitude, String longitude, String eventAction, int days, String date)
            throws IOException {
        OkHttpClient client = new OkHttpClient();
        String weatherURL = "";
        if (eventAction.equals("current")) {
            weatherURL = "https://weatherapi-com.p.rapidapi.com/current.json?q=" + latitude + "%20%2C" + longitude;
        } else {
            weatherURL = "https://weatherapi-com.p.rapidapi.com/forecast.json?q=" + latitude + "%2C"
                    + longitude + "&days=" + days + "&dt=" + date;
        }
        Request request = new Request.Builder()
                .url(weatherURL)
                .get()
                .addHeader("X-RapidAPI-Key", System.getenv("X-RapidAPI-Key"))
                .addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Get latitude and longitude from address using Open cage external API.
     * @param address
     * @return
     */
    public String getGeolocationKey(String address) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(System.getenv("api_key"));
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition(); // get the coordinate pair of the first result
        String lat = firstResultLatLng.getLat().toString();
        String lng = firstResultLatLng.getLng().toString();
        return lat + ',' + lng;
    }
}
