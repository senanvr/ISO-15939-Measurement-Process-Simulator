package model;

public enum Direction {

    HIGHER_IS_BETTER("Higher is better", "↑"),

    LOWER_IS_BETTER("Lower is better", "↓");


    private final String displayName;
    private final String symbol;



    Direction(String displayName, String symbol) {

        this.displayName = displayName;
        this.symbol = symbol;

    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSymbol() {

        return symbol;
    }

    @Override
    public String toString() {

        return displayName;

    }

}