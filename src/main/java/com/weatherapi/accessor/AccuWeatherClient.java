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

@Component
public class AccuWeatherClient {

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
                .addHeader("X-RapidAPI-Key", "11f02f1ca5msh8bf3d300dfc67dep125fe9jsn18ebc0cf38f0")
                .addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

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
                .url("https://weatherapi-com.p.rapidapi.com/current.json?q=" + latitude + "%20%2C" + longitude)
                .get()
                .addHeader("X-RapidAPI-Key", "11f02f1ca5msh8bf3d300dfc67dep125fe9jsn18ebc0cf38f0")
                .addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getGeolocationKey(String address) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("9df551d9b73743dd94fdac2ea461e1fe");
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);
        //request.setRestrictToCountryCode("za"); // restrict results to a specific country
        //request.setBounds(18.367, -34.109, 18.770, -33.704); // restrict results to a geographic bounding box (southWestLng, southWestLat, northEastLng, northEastLat)

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition(); // get the coordinate pair of the first result
        String lat = firstResultLatLng.getLat().toString();
        String lng = firstResultLatLng.getLng().toString();
        return lat + ',' + lng;
    }
}