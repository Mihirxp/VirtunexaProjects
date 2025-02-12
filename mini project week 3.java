import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {

    public static void main(String[] args) {
        System.out.print("Enter city name: ");
        String city = System.console().readLine();
        String apiKey = "your_api_key_here";  
        getWeather(city, apiKey);
    }

    private static void getWeather(String city, String apiKey) {
        try {
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            if (jsonResponse.getInt("cod") == 200) {
                JSONObject main = jsonResponse.getJSONObject("main");
                JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
                JSONObject wind = jsonResponse.getJSONObject("wind");

                System.out.println("Weather in " + city + ":");
                System.out.println("Temperature: " + main.getDouble("temp") + "°C");
                System.out.println("Humidity: " + main.getInt("humidity") + "%");
                System.out.println("Description: " + weather.getString("description"));
                System.out.println("Wind Speed: " + wind.getDouble("speed") + " m/s");
                System.out.println("Pressure: " + main.getInt("pressure") + " hPa");
            } else {
                System.out.println("Error: Unable to fetch weather data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
