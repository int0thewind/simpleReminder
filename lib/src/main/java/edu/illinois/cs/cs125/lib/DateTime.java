package edu.illinois.cs.cs125.lib;

public class DateTime {

    private int year, month, day, hour, minute;

    public DateTime(int setYear, int setMonth, int setDay, int setHour, int setMinute) {
        year = setYear;
        month = setMonth;
        day = setDay;
        hour = setHour;
        minute = setMinute;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
