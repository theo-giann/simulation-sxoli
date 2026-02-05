package gr.university.simulation.model;

public class Order {
    private final Customer customer;
    private final int quantity;

    public Order(Customer customer, int quantity) {
        this.customer = customer;
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getQuantity() {
        return quantity;
    }
}

