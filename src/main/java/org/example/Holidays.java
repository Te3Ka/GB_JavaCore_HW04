package org.example;


public enum Holidays {
    NEW_YEAR(12, 31),
    DEFENDER_FATHERLAND_DAY(2, 23),
    INTERNATIONAL_WOMENS_DAY(3, 8),
    NOTHING(0, 0);

    private final int month;
    private final int day;

    Holidays(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
