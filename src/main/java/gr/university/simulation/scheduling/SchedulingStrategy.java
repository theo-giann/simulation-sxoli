package gr.university.simulation.scheduling;

import gr.university.simulation.model.Order;
import gr.university.simulation.model.Truck;

import java.util.List;

public interface SchedulingStrategy {

    List<Assignment> assign(List<Truck> trucks, List<Order> orders);

    final class Assignment {
        private final Truck truck;
        private final Order order;

        public Assignment(Truck truck, Order order) {
            this.truck = truck;
            this.order = order;
        }

        public Truck getTruck() {
            return truck;
        }

        public Order getOrder() {
            return order;
        }
    }
}

