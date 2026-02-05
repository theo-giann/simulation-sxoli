package gr.university.simulation.metrics;

import gr.university.simulation.engine.SimulationEngine;
import gr.university.simulation.model.Truck;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class MetricsWriter {

    private MetricsWriter() {}

    /**
     * Ensures that the results folder exists.
     */
    public static void ensureResultsFolder() {
        File dir = new File("results");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Writes per-simulation summary metrics (one row per truck).
     */
    public static void appendSimulationSummary(
            String csvPath,
            int simulationId,
            String strategyName,
            List<Truck> trucks
    ) throws IOException {

        boolean fileExists = new File(csvPath).exists();

        try (FileWriter fw = new FileWriter(csvPath, true)) {
            if (!fileExists) {
                fw.write("simulation,strategy,truckId,totalDistanceKm,totalCost,totalGoodsMoved\n");
            }

            for (Truck t : trucks) {
                fw.write(
                        simulationId + "," +
                        strategyName + "," +
                        t.getId() + "," +
                        t.getTotalDistanceKm() + "," +
                        String.format("%.2f", t.getTotalCost()) + "," +
                        t.getTotalGoodsMoved() + "\n"
                );
            }
        }
    }

    /**
     * Writes daily metrics (one row per simulated day).
     */
    public static void appendDailyMetrics(
            String csvPath,
            int simulationId,
            String strategyName,
            List<SimulationEngine.DayResult> dayResults
    ) throws IOException {

        boolean fileExists = new File(csvPath).exists();

        try (FileWriter fw = new FileWriter(csvPath, true)) {
            if (!fileExists) {
                fw.write("simulation,strategy,day,goodsMoved,distanceKm,cost\n");
            }

            for (SimulationEngine.DayResult dr : dayResults) {
                fw.write(
                        simulationId + "," +
                        strategyName + "," +
                        dr.day() + "," +
                        dr.goodsMoved() + "," +
                        dr.distanceKm() + "," +
                        String.format("%.2f", dr.cost()) + "\n"
                );
            }
        }
    }
}

