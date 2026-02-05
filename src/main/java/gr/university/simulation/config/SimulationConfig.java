package gr.university.simulation.config;

import java.util.List;
import java.util.Map;

public final class SimulationConfig {
    private SimulationConfig() {}

    
    public static final int NUM_SIMULATIONS = 3;
    public static final int SIMULATION_DAYS = 30;

    
    public static final int NUM_TRUCKS = 3;
    public static final int TRUCK_CAPACITY = 100;

    
    public static final int CUSTOMERS_PER_DAY = 5;
    public static final int DEMAND_MIN = 5;
    public static final int DEMAND_MAX = 30;

    
    public static final double COST_PER_KM = 1.5;

    
    public static final List<String> CITIES = List.of(
            "Athens",
            "Thessaloniki",
            "Patras",
            "Larisa"
    );

    
    private static final Map<String, Integer> DISTANCES = Map.ofEntries(
            Map.entry(key("Athens", "Thessaloniki"), 500),
            Map.entry(key("Athens", "Patras"), 210),
            Map.entry(key("Athens", "Larisa"), 350),
            Map.entry(key("Thessaloniki", "Patras"), 700),
            Map.entry(key("Thessaloniki", "Larisa"), 150),
            Map.entry(key("Patras", "Larisa"), 400)
    );

    private static String key(String a, String b) {
        return a.compareTo(b) < 0 ? a + "|" + b : b + "|" + a;
    }

    public static int distanceBetween(String a, String b) {
        if (a.equals(b)) return 0;

        Integer d = DISTANCES.get(key(a, b));
        if (d == null) {
            throw new IllegalArgumentException("No distance defined for: " + a + " <-> " + b);
        }
        return d;
    }
}

