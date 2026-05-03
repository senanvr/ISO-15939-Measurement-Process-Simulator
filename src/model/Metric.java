package model;

public class Metric {

    private String name;
    private int coefficient;
    private Direction direction;
    private double minRange;
    private double maxRange;
    private String unit;
    private double rawValue;
    private double calculatedScore;

    public Metric(String name, int coefficient,Direction direction,double minRange, double maxRange, String unit, double rawValue) {

        this.name = name;

        this.coefficient = coefficient;

        this.direction = direction;

        this.minRange = minRange;

        this.maxRange = maxRange;

        this.unit = unit;

        this.rawValue = rawValue;

        this.calculatedScore = 0;

        calculateScore();

    }

    public String getName() {
        return name;
    }

    public int getCoefficient() {

        return coefficient;

    }

    public Direction getDirection() {

        return direction;
    }

    public double getMinRange() {

        return minRange;

    }

    public double getMaxRange() {

        return maxRange;

    }

    public String getUnit() {

        return unit;

    }

    public double getRawValue() {
        return rawValue;

    }

    public double getCalculatedScore() {

        return calculatedScore;

    }

    public void setRawValue(double rawValue) {
        this.rawValue = rawValue;

        calculateScore();

    }

    public void setName(String name) {
        this.name = name;

    }

    public void setCoefficient(int coefficient) {

        this.coefficient = coefficient;
    }

    public void setDirection(Direction direction) {

        this.direction = direction;

    }

    public void setMinRange(double minRange) {
        this.minRange = minRange;

    }

    public void setMaxRange(double maxRange) {
        this.maxRange = maxRange;

    }


    public void setUnit(String unit) {
        this.unit = unit;

    }

    public void calculateScore() {

        double normalized = (rawValue - minRange) / (maxRange - minRange);

        normalized = Math.max(0, Math.min(1, normalized));

        double score;

        if (direction == Direction.HIGHER_IS_BETTER) {

            score = 1 + normalized * 4;

        } else {

            score = 5 - normalized * 4;

        }

        calculatedScore = Math.round(score * 2) / 2.0;

        calculatedScore = Math.max(1.0, Math.min(5.0, calculatedScore));

    }

    @Override
    public String toString() {

        return String.format("Metric{name='%s', value=%.2f, score=%.1f}", name, rawValue, calculatedScore);
    }


}