package model;

public enum Mode {

    EDUCATION("Education"),

    HEALTH("Health"),

    CUSTOM("Custom (Bonus)");

    private final String displayName;

    Mode(String displayName) {

        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;

    }



    @Override
    public String toString() {
        return displayName;

    }



}