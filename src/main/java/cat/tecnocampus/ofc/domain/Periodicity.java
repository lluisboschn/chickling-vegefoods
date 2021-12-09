// SPRINT 5

package cat.tecnocampus.ofc.domain;

import java.util.Calendar;

public class Periodicity {
    private Calendar initial_date;
    private int dayOfWeek;
    private int numberOfPeriods;
    private String perioda;//weekly, monthly.....

    public Periodicity(Calendar initial_date, int dayOfWeek, int numberOfPeriods, String period) {
        this.initial_date = initial_date;
        this.dayOfWeek = dayOfWeek;
        this.numberOfPeriods = numberOfPeriods;
        this.perioda = period;
    }

    /*public PeriodicityDTO PeriodicityToDTO(){

        return new PeriodicityDTO(initial_date,dayOfWeek,numberOfPeriods,perioda);
    }*/
}