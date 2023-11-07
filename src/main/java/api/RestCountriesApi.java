package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestCountriesApi {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestBrazil = HttpRequest.newBuilder()
                .uri(URI.create("https://restcountries.com/v3.1/name/brazil"))
                .build();
        System.out.println(requestBrazil);

        client.sendAsync(requestBrazil, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(RestCountriesApi::parseBrazilInfo)
                .join();

        HttpRequest requestAfrica = HttpRequest.newBuilder()
                .uri(URI.create("https://restcountries.com/v3.1/region/africa"))
                .build();
        System.out.println(requestAfrica);

        client.sendAsync(requestAfrica, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(RestCountriesApi::parseAfricaCountries)
                .join();
    }

    private static void parseBrazilInfo(String responseBody) {
        JSONArray countries = new JSONArray(responseBody);
        JSONObject brazil = countries.getJSONObject(0);
        System.out.println("Brazil Info:"+countries);

        long population = brazil.getLong("population");
        double area = brazil.getDouble("area");
        JSONObject languages = brazil.getJSONObject("languages");
        String officialLanguage = languages.keys().next();
        String capital = brazil.getJSONArray("capital").getString(0);
        JSONArray latlng = brazil.getJSONArray("latlng");
        double latitude = latlng.getDouble(0);
        double longitude = latlng.getDouble(1);
        String region = brazil.getString("region");
        String subregion = brazil.getString("subregion");
        JSONObject currencies = brazil.getJSONObject("currencies");
        String currency = currencies.keys().next();

        System.out.println("Brazil Information:");
        System.out.println("Population: " + population);
        System.out.println("Area: " + area);
        System.out.println("Official Language: " + officialLanguage);
        System.out.println("Capital: " + capital);
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Region: " + region);
        System.out.println("Subregion: " + subregion);
        System.out.println("Currency: " + currency);
        System.out.println();
    }
    private static void parseAfricaCountries(String responseBody) {
        JSONArray countries = new JSONArray(responseBody);

        System.out.println("Countries in Africa:");
        for (int i = 0; i < countries.length(); i++) {
            String countryName = countries.getJSONObject(i).getJSONObject("name").getString("common");
            System.out.println(countryName);
        }
    }
}

