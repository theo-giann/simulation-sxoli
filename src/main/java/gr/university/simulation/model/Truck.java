package gr.university.simulation.model;

public class Truck {
    private final int id;
    private final int capacity;
    private String currentCity;

    
    private int totalDistanceKm;
    private double totalCost;
    private int totalGoodsMoved;

    public Truck(int id, String startCity, int capacity) {
        this.id = id;
        this.currentCity = startCity;
        this.capacity = capacity;

        this.totalDistanceKm = 0;
        this.totalCost = 0.0;
        this.totalGoodsMoved = 0;
    }

    
    public void moveTo(String destinationCity, int distanceKm, double costPerKm, int goodsQuantity) {
        this.totalDistanceKm += distanceKm;
        this.totalCost += distanceKm * costPerKm;
        this.totalGoodsMoved += goodsQuantity;
        this.currentCity = destinationCity;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public int getTotalDistanceKm() {
        return totalDistanceKm;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getTotalGoodsMoved() {
        return totalGoodsMoved;
    }
}

