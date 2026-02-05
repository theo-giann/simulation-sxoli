package gr.university.simulation.model;

public class Customer {
    private final int id;
    private final String city;

    public Customer(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }
}

