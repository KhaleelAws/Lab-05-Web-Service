// Rowan Diepenbrock
// Luz Martinez
// Ethan Tran
// Developed by: Aws Khaleel

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WeatherService {

    // Two major cities per state (50 states + DC)
    private static final Map<String, String[]> STATE_CITIES = Map.ofEntries(
        Map.entry("AL", new String[]{"Birmingham", "Mobile"}),
        Map.entry("AK", new String[]{"Anchorage", "Fairbanks"}),
        Map.entry("AZ", new String[]{"Phoenix", "Tucson"}),
        Map.entry("AR", new String[]{"Little Rock", "Fayetteville"}),
        Map.entry("CA", new String[]{"Los Angeles", "San Francisco"}),
        Map.entry("CO", new String[]{"Denver", "Colorado Springs"}),
        Map.entry("CT", new String[]{"Hartford", "New Haven"}),
        Map.entry("DE", new String[]{"Wilmington", "Dover"}),
        Map.entry("FL", new String[]{"Miami", "Orlando"}),
        Map.entry("GA", new String[]{"Atlanta", "Savannah"}),
        Map.entry("HI", new String[]{"Honolulu", "Hilo"}),
        Map.entry("ID", new String[]{"Boise", "Idaho Falls"}),
        Map.entry("IL", new String[]{"Chicago", "Springfield"}),
        Map.entry("IN", new String[]{"Indianapolis", "Fort Wayne"}),
        Map.entry("IA", new String[]{"Des Moines", "Cedar Rapids"}),
        Map.entry("KS", new String[]{"Wichita", "Topeka"}),
        Map.entry("KY", new String[]{"Louisville", "Lexington"}),
        Map.entry("LA", new String[]{"New Orleans", "Baton Rouge"}),
        Map.entry("ME", new String[]{"Portland", "Bangor"}),
        Map.entry("MD", new String[]{"Baltimore", "Frederick"}),
        Map.entry("MA", new String[]{"Boston", "Worcester"}),
        Map.entry("MI", new String[]{"Detroit", "Grand Rapids"}),
        Map.entry("MN", new String[]{"Minneapolis", "Saint Paul"}),
        Map.entry("MS", new String[]{"Jackson", "Gulfport"}),
        Map.entry("MO", new String[]{"Kansas City", "St. Louis"}),
        Map.entry("MT", new String[]{"Billings", "Missoula"}),
        Map.entry("NE", new String[]{"Omaha", "Lincoln"}),
        Map.entry("NV", new String[]{"Las Vegas", "Reno"}),
        Map.entry("NH", new String[]{"Manchester", "Nashua"}),
        Map.entry("NJ", new String[]{"Newark", "Jersey City"}),
        Map.entry("NM", new String[]{"Albuquerque", "Santa Fe"}),
        Map.entry("NY", new String[]{"New York", "Buffalo"}),
        Map.entry("NC", new String[]{"Charlotte", "Raleigh"}),
        Map.entry("ND", new String[]{"Fargo", "Bismarck"}),
        Map.entry("OH", new String[]{"Columbus", "Cleveland"}),
        Map.entry("OK", new String[]{"Oklahoma City", "Tulsa"}),
        Map.entry("OR", new String[]{"Portland", "Eugene"}),
        Map.entry("PA", new String[]{"Philadelphia", "Pittsburgh"}),
        Map.entry("RI", new String[]{"Providence", "Warwick"}),
        Map.entry("SC", new String[]{"Charleston", "Columbia"}),
        Map.entry("SD", new String[]{"Sioux Falls", "Rapid City"}),
        Map.entry("TN", new String[]{"Nashville", "Memphis"}),
        Map.entry("TX", new String[]{"Houston", "Dallas"}),
        Map.entry("UT", new String[]{"Salt Lake City", "Provo"}),
        Map.entry("VT", new String[]{"Burlington", "Rutland"}),
        Map.entry("VA", new String[]{"Virginia Beach", "Richmond"}),
        Map.entry("WA", new String[]{"Seattle", "Spokane"}),
        Map.entry("WV", new String[]{"Charleston", "Morgantown"}),
        Map.entry("WI", new String[]{"Milwaukee", "Madison"}),
        Map.entry("WY", new String[]{"Cheyenne", "Casper"}),
        Map.entry("DC", new String[]{"Washington D.C.", "Georgetown"})
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Weather Web Service");
        System.out.println("Developed by: Aws Khaleel");
        System.out.print("Enter date (MM/DD/YYYY): ");
        String dateInput = scanner.nextLine();

        System.out.print("Enter time (HH:MM): ");
        String timeInput = scanner.nextLine();

        LocalDateTime dateTime;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            dateTime = LocalDateTime.parse(dateInput + " " + timeInput, formatter);
        } catch (Exception e) {
            System.out.println("Invalid date or time format. Using current date/time instead.");
            dateTime = LocalDateTime.now();
        }

        Random random = new Random();
        List<String[]> data = new ArrayList<>();

        System.out.printf("\n%-4s | %-20s | %-22s | %-10s | %-10s%n",
                "ST", "CITY", "DATE/TIME", "TEMP (°C)", "TEMP (°F)");
        System.out.println("--------------------------------------------------------------------");

        for (var entry : STATE_CITIES.entrySet()) {
            String state = entry.getKey();
            for (String city : entry.getValue()) {
                double tempC = 5 + (random.nextDouble() * 30);
                double tempF = (tempC * 9 / 5) + 32;

                System.out.printf("%-4s | %-20s | %-22s | %-10.1f | %-10.1f%n",
                        state, city, dateTime, tempC, tempF);

                data.add(new String[]{
                    state, city, dateTime.toString(),
                    String.format("%.1f", tempC),
                    String.format("%.1f", tempF)
                });
            }
        }

        saveToCSV(data);
        System.out.println("\nData saved to 'all_states_temperatures.csv'.");
    }

    private static void saveToCSV(List<String[]> data) {
        try (FileWriter writer = new FileWriter("all_states_temperatures.csv")) {
            writer.write("State,City,DateTime,TempC,TempF\n");
            for (String[] row : data) {
                writer.write(String.join(",", row) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}
