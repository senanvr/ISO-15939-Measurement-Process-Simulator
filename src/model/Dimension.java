package model;

import java.util.ArrayList;

import java.util.List;

public class Dimension {
    private String name;
    private int coefficient;
    private List<Metric> metrics;



    public Dimension(String name, int coefficient) {

        this.name = name;

        this.coefficient = coefficient;

        this.metrics = new ArrayList<>();

    }

    public String getName() {
        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public int getCoefficient() {
        return coefficient;

    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;

    }

    public List<Metric> getMetrics() {

        return metrics;
    }

    public void addMetric(Metric metric) {
        this.metrics.add(metric);

    }

    public double calculateDimensionScore() {

        if (metrics.isEmpty()) return 0;

        double totalWeightedScore = 0;
        double totalCoefficient = 0;

        for (Metric metric : metrics) {

            metric.calculateScore();

            totalWeightedScore += metric.getCalculatedScore() * metric.getCoefficient();

            totalCoefficient += metric.getCoefficient();

        }

        if (totalCoefficient == 0) return 0;

        return totalWeightedScore / totalCoefficient;

    }

    @Override
    public String toString() {
        return String.format("Dimension{name='%s', coeff=%d, metrics=%d}", name, coefficient, metrics.size());

    }

}