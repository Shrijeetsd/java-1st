import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class CurrencyConverter {

    public static void main(String[] args) {
        try {
            // Allow the user to choose the base and target currency
            String baseCurrency = getUserInput("Enter the base currency code: ");
            String targetCurrency = getUserInput("Enter the target currency code: ");

            // Fetch real-time exchange rates from a reliable API
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

            // Take input from the user for the amount to convert
            double amountToConvert = Double.parseDouble(getUserInput("Enter the amount to convert: "));

            // Convert the input amount using the fetched exchange rate
            double convertedAmount = amountToConvert * exchangeRate;

            // Display the result
            System.out.printf("%.2f %s is equal to %.2f %s\n", amountToConvert, baseCurrency, convertedAmount,
                    targetCurrency);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static String getUserInput(String message) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        return reader.readLine();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String apiKey = "YOUR_API_KEY"; // Replace with your actual API key
        String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Parse the JSON response to get the exchange rate
                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getJSONObject("rates").getDouble(targetCurrency);
            }
        } else {
            throw new RuntimeException("Failed to fetch exchange rates. HTTP error code: " + responseCode);
        }
    }
}
