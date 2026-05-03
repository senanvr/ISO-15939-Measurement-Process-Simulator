package model;

import java.awt.Color;

public enum QualityLevel {

    EXCELLENT("Excellent", new Color(0, 100, 0), 4.5, 5.0),

    GOOD("Good", new Color(0, 150, 0), 3.5, 4.5),

    NEEDS_IMPROVEMENT("Needs Improvement", new Color(255, 140, 0), 2.5, 3.5),

    POOR("Poor", Color.RED, 0.0, 2.5);

    private final String displayName;
    private final Color color;

    private final double minScore;
    private final double maxScore;

    QualityLevel(String displayName, Color color, double minScore, double maxScore) {

        this.displayName = displayName;

        this.color = color;

        this.minScore = minScore;

        this.maxScore = maxScore;

    }

    public String getDisplayName() {
        return displayName;

    }

    public Color getColor() {
        return color;

    }

    public static QualityLevel fromScore(double score) {

        if (score >= EXCELLENT.minScore) return EXCELLENT;

        if (score >= GOOD.minScore) return GOOD;

        if (score >= NEEDS_IMPROVEMENT.minScore) return NEEDS_IMPROVEMENT;


        return POOR;

    }

    @Override
    public String toString() {
        return displayName;
    }



}