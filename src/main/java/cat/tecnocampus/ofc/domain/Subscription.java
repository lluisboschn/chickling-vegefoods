// SPRINT 5

package cat.tecnocampus.ofc.domain;

import java.util.Date;

public class Subscription {
    private String id;
    private Product product;
    private double amount;
    private double price;
    private Date date;

    public Subscription(String id, Product product, double amount, double price, Date date) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.date = date;
    }
}