// SPRINT 5

package cat.tecnocampus.ofc.application.dtos;


import java.time.LocalDate;
import java.util.UUID;


public class SubscriptionDTOAuxiliar {
    private String id;
    private String consumerMail;
    private String productId;
    private double amount;
    private double price;
    private LocalDate creation_date;
    private LocalDate close_date;

    public SubscriptionDTOAuxiliar(String id, String consumerMail, String productId, double amount, double price,
                                   LocalDate creation_date, LocalDate close_date) {
        this.id = id;
        this.consumerMail = consumerMail;
        this.productId = productId;
        this.amount = amount;
        this.price = price;
        this.creation_date = creation_date;
        this.close_date = close_date;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getConsumerMail() {
        return consumerMail;
    }

    public void setConsumerMail(String consumerMail) {
        this.consumerMail = consumerMail;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDate getClose_date() {
        return close_date;
    }

    public void setClose_date(LocalDate close_date) {
        this.close_date = close_date;
    }
}
