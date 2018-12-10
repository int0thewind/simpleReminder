package edu.illinois.cs.cs125.lib;

import com.sun.istack.internal.Nullable;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class DateTime implements Comparable<DateTime> {

    private int year, month, day, hour, minute;

    public DateTime() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        this.year = gregorianCalendar.
    }

    public DateTime(int setYear, int setMonth, int setDay, int setHour, int setMinute) {
        if (setYear < 2018 || setMonth < 1 || setMonth > 12 || setDay < 1 || setDay > 31
                || setHour < 1 || setHour > 24 || setMinute < 0 || setMinute > 59) {
            throw new IllegalArgumentException();
        }
        year = setYear;
        month = setMonth;
        day = setDay;
        hour = setHour;
        minute = setMinute;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTime dateTime = (DateTime) o;
        return year == dateTime.year &&
                month == dateTime.month &&
                day == dateTime.day &&
                hour == dateTime.hour &&
                minute == dateTime.minute;
    }

    @Override
    public int hashCode() {

        return Objects.hash(year, month, day, hour, minute);
    }

    @Override
    public String toString() {
        return this.year + "." + this.month + "." + this.day + "." + this.hour + "." + this.minute;
    }

    public int toInt() {
        return this.year * 10^8 + this.month * 10^6 + this.day * 10^4 + this.hour * 10^2 + this.minute;
    }

    @Override
    public int compareTo(DateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException();
        }
        return Integer.compare(this.toInt(), dateTime.toInt());
    }
}
