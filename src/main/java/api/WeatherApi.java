package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class WeatherApi {

    private static final String API_KEY = "7c5284574b6edd0883ad588adfcba5b8";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    public static void main(String[] args) {
        String londonWeather = getCurrentWeather("London,UK");
        System.out.println("Current Weather in London, UK:");
        System.out.println(londonWeather);

        String tokyoForecast = getFiveDayForecast("Tokyo,JP");
        System.out.println("\n5-Day Forecast for Tokyo, Japan:");
        System.out.println(tokyoForecast);
    }

    private static String getCurrentWeather(String location) {
        String endpoint = BASE_URL + "weather?q=" + location + "&appid=" + API_KEY;
        return makeApiRequest(endpoint);
    }

    private static String getFiveDayForecast(String location) {
        String endpoint = BASE_URL + "forecast?q=" + location + "&appid=" + API_KEY;
        return makeApiRequest(endpoint);
    }

    private static String makeApiRequest(String endpoint) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

