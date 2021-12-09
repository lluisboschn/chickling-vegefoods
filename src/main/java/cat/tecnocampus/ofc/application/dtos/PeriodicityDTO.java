// SPRINT 5

package cat.tecnocampus.ofc.application.dtos;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class PeriodicityDTO {
    private String id;
    private Calendar initial_date;
    private int dayOfWeek;
    private int numberOfPeriods;
    private String perioda;//weekly, monthly.....

    public PeriodicityDTO(String id, Calendar initial_date, int dayOfWeek, int numberOfPeriods, String perioda) {
        this.id = id;
        this.initial_date = initial_date;
        this.dayOfWeek = dayOfWeek;
        this.numberOfPeriods = numberOfPeriods;
        this.perioda = perioda;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Calendar getInitial_date() {
        return initial_date;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getNumberOfPeriods() {
        return numberOfPeriods;
    }

    public void setNumberOfPeriods(int numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
    }

    public String getPeriod() {
        return perioda;
    }

    public void setPeriod(String period) {
        this.perioda = period;
    }

}