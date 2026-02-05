package gr.university.simulation.scheduling;

import gr.university.simulation.model.Order;
import gr.university.simulation.model.Truck;

import java.util.ArrayList;
import java.util.List;

public class FcfsStrategy implements SchedulingStrategy {

    @Override
    public List<Assignment> assign(List<Truck> trucks, List<Order> orders) {
        List<Assignment> out = new ArrayList<>();
        int idx = 0;

        for (Order o : orders) {
            Truck t = trucks.get(idx % trucks.size());
            out.add(new Assignment(t, o));
            idx++;
        }
        return out;
    }

    @Override
    public String toString() {
        return "FCFS";
    }
}

