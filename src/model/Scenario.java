package model;

import java.util.ArrayList;
import java.util.List;


public class Scenario {

    private String name;
    private String description;
    private List<Dimension> dimensions;




    public Scenario(String name) {
        this.name = name;
        this.description = "";
        this.dimensions = new ArrayList<>();

    }

    public Scenario(String name, String description) {

        this.name = name;
        this.description = description;
        this.dimensions = new ArrayList<>();

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Dimension> getDimensions() {
        return dimensions;

    }

    public void addDimension(Dimension dimension) {
        this.dimensions.add(dimension);

    }

    public List<Metric> getAllMetrics() {

        List<Metric> allMetrics = new ArrayList<>();

        for (Dimension dim : dimensions) {
            allMetrics.addAll(dim.getMetrics());

        }

        return allMetrics;

    }

    public double getDimensionScore(String dimensionName) {

        for (Dimension dim : dimensions) {

            if (dim.getName().equals(dimensionName)) {
                return dim.calculateDimensionScore();

            }

        }

        return 0;

    }

    public Dimension getLowestScoreDimension() {

        if (dimensions.isEmpty()) return null;

        Dimension lowest = dimensions.get(0);
        double lowestScore = lowest.calculateDimensionScore();

        for (int i = 1; i < dimensions.size(); i++) {

            double currentScore = dimensions.get(i).calculateDimensionScore();

            if (currentScore < lowestScore) {
                lowestScore = currentScore;
                lowest = dimensions.get(i);

            }

        }

        return lowest;

    }

    @Override
    public String toString() {
        return String.format("Scenario{name='%s', dimensions=%d}", name, dimensions.size());

    }








}