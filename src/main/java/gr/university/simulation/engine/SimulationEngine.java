package gr.university.simulation.engine;

import gr.university.simulation.config.SimulationConfig;
import gr.university.simulation.model.Customer;
import gr.university.simulation.model.Order;
import gr.university.simulation.model.Truck;
import gr.university.simulation.scheduling.SchedulingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationEngine {

    private final List<Truck> trucks;
    private final SchedulingStrategy strategy;
    private final Random rng;

    public SimulationEngine(List<Truck> trucks, SchedulingStrategy strategy, long seed) {
        this.trucks = trucks;
        this.strategy = strategy;
        this.rng = new Random(seed);
    }

    public DayResult runDay(int dayIndex) {
        
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < SimulationConfig.CUSTOMERS_PER_DAY; i++) {
            String city = SimulationConfig.CITIES.get(rng.nextInt(SimulationConfig.CITIES.size()));
            customers.add(new Customer(i, city));
        }

        List<Order> orders = new ArrayList<>();
        for (Customer c : customers) {
            int qty = SimulationConfig.DEMAND_MIN
                    + rng.nextInt(SimulationConfig.DEMAND_MAX - SimulationConfig.DEMAND_MIN + 1);
            orders.add(new Order(c, qty));
        }

        
        List<SchedulingStrategy.Assignment> assignments = strategy.assign(trucks, orders);

        int goodsToday = 0;
        int distanceTodayKm = 0;
        double costToday = 0.0;

        for (SchedulingStrategy.Assignment a : assignments) {
            Truck t = a.getTruck();
            Order o = a.getOrder();

            String destination = o.getCustomer().getCity();
            int distance = SimulationConfig.distanceBetween(t.getCurrentCity(), destination);

            t.moveTo(destination, distance, SimulationConfig.COST_PER_KM, o.getQuantity());

            goodsToday += o.getQuantity();
            distanceTodayKm += distance;
            costToday += distance * SimulationConfig.COST_PER_KM;
        }

        return new DayResult(dayIndex, goodsToday, distanceTodayKm, costToday);
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public String getStrategyName() {
        return strategy.toString();
    }

    public record DayResult(int day, int goodsMoved, int distanceKm, double cost) {}
}

