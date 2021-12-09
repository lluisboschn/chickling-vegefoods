// SPRINT 5

package cat.tecnocampus.ofc.application.dtos;


import java.time.LocalDate;
import java.util.UUID;


public class SubscriptionDTO {
    private String id;
    private String consumerMail;
    private ProductDTO product;
    private double amount;
    private double price;
    private LocalDate creation_date;
    private LocalDate close_date;
    private String consumerId;

   public SubscriptionDTO(String id, String consumerMail, ProductDTO product, double amount, double price, String consumerId) {
        this.id = id;
        this.consumerMail = consumerMail;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.creation_date = LocalDate.now();
        this.close_date = creation_date.plusDays(7);
        this.consumerId=consumerId;
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
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

    public String getConsumerId() {
        return consumerId;
    }

}
