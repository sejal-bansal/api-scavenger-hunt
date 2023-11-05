package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class CurrencyConverterApi {

    private final OkHttpClient httpClient = new OkHttpClient();
    private final String apiKey = "12be5cf2890545822a519cca";

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) throws IOException {
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + fromCurrency + "/" + toCurrency + "/" + amount;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Parse the response to extract the conversion result
            String responseBody = response.body().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            double conversionResult = jsonObject.getDouble("conversion_result");

            return conversionResult;
        }
    }

    public static void main(String[] args) {
        CurrencyConverterApi converter = new CurrencyConverterApi();
        try {
            double usdToEur = converter.convertCurrency("USD", "EUR", 100);
            System.out.println("100 USD is " + usdToEur + " EUR");

            double jpyToGbp = converter.convertCurrency("JPY", "GBP", 1000);
            System.out.println("1000 JPY is " + jpyToGbp + " GBP");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

