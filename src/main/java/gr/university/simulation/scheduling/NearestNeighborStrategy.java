package gr.university.simulation.scheduling;

import gr.university.simulation.config.SimulationConfig;
import gr.university.simulation.model.Order;
import gr.university.simulation.model.Truck;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighborStrategy implements SchedulingStrategy {

    @Override
    public List<Assignment> assign(List<Truck> trucks, List<Order> orders) {
        List<Assignment> out = new ArrayList<>();

        for (Order o : orders) {
            Truck bestTruck = null;
            int bestDistance = Integer.MAX_VALUE;

            for (Truck t : trucks) {
                int d = SimulationConfig.distanceBetween(
                        t.getCurrentCity(),
                        o.getCustomer().getCity()
                );
                if (d < bestDistance) {
                    bestDistance = d;
                    bestTruck = t;
                }
            }
            out.add(new Assignment(bestTruck, o));
        }
        return out;
    }

    @Override
    public String toString() {
        return "NEAREST_NEIGHBOR";
    }
}

