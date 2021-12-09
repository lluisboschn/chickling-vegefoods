// SPRINT 5

package cat.tecnocampus.ofc.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private double price;
    private String measure_unit;
    private String provider;
    private String vat_type;
    private String category;
    private LocalDate initial_date;
    private String day_of_week;
    private int num_of_periods;
    private String perioda;
    private String image;

    //private Periodicity period;

    public Product(String name, double price, String measure_unit, String provider, String vat_type,
                   String category, LocalDate initial_date, String day_of_week, int num_of_periods,
                   String period, String image) {
        this.name = name;
        this.price = price;
        this.measure_unit = measure_unit;
        this.provider = provider;
        this.vat_type = vat_type;
        this.category = category;
        this.initial_date = initial_date;
        this.day_of_week = day_of_week;
        this.num_of_periods = num_of_periods;
        this.perioda = period;
        this.image = image;
        this.id=this.generateId();
    }
    private String generateId() {
        return UUID.randomUUID().toString();
    }

}