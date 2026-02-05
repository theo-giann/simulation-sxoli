package gr.university.simulation;

import gr.university.simulation.config.SimulationConfig;
import gr.university.simulation.engine.SimulationEngine;
import gr.university.simulation.engine.SimulationEngine.DayResult;
import gr.university.simulation.metrics.MetricsWriter;
import gr.university.simulation.model.Truck;
import gr.university.simulation.scheduling.FcfsStrategy;
import gr.university.simulation.scheduling.NearestNeighborStrategy;
import gr.university.simulation.scheduling.SchedulingStrategy;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        MetricsWriter.ensureResultsFolder();

        List<SchedulingStrategy> strategies = List.of(
                new FcfsStrategy(),
                new NearestNeighborStrategy()
        );

        for (SchedulingStrategy strategy : strategies) {

            for (int sim = 1; sim <= SimulationConfig.NUM_SIMULATIONS; sim++) {

                // 1) Create trucks
                List<Truck> trucks = new ArrayList<>();
                for (int i = 0; i < SimulationConfig.NUM_TRUCKS; i++) {
                    trucks.add(new Truck(
                            i,
                            SimulationConfig.CITIES.get(0),
                            SimulationConfig.TRUCK_CAPACITY
                    ));
                }

                // 2) Create engine
                SimulationEngine engine =
                        new SimulationEngine(trucks, strategy, sim);

                // 3) Run days
                List<DayResult> dayResults = new ArrayList<>();
                for (int day = 1; day <= SimulationConfig.SIMULATION_DAYS; day++) {
                    dayResults.add(engine.runDay(day));
                }

                // 4) Write metrics
                MetricsWriter.appendSimulationSummary(
                        "results/summary.csv",
                        sim,
                        engine.getStrategyName(),
                        engine.getTrucks()
                );

                MetricsWriter.appendDailyMetrics(
                        "results/daily.csv",
                        sim,
                        engine.getStrategyName(),
                        dayResults
                );

                System.out.println(
                        "Finished simulation " + sim +
                        " using strategy " + engine.getStrategyName()
                );
            }
        }

        System.out.println("ALL SIMULATIONS FINISHED ✅");
    }
}

