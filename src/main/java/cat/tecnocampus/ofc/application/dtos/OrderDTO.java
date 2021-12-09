// SPRINT 5

package cat.tecnocampus.ofc.application.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class OrderDTO {//Order ha de tenir tot (usuari, periodicity, etc)
    private String id;
    private String email;
    //private Calendar date;
    private LocalDate dat_deliver;
    private LocalDate close_date;
    private String status;

    private String idSubs;
    private ProductDTO productId;
    private double amount;
    private double price;
    private LocalDate date;//creation date of the subscription

    public OrderDTO(String id, String email, LocalDate close_date, String status,
                    String idSubs, ProductDTO productId, double amount,
                    double price) {
        this.id=id;
        this.email = email;
        this.date = LocalDate.now();
        this.dat_deliver = LocalDate.now().plusDays(6);
        this.close_date = close_date;
        this.status = status;
        this.idSubs = idSubs;
        this.productId = productId;
        this.amount = amount;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDat_deliver() {
        return dat_deliver;
    }

    public void setDat_deliver(LocalDate dat_deliver) {
        this.dat_deliver = dat_deliver;
    }

    public LocalDate getClose_date() {
        return close_date;
    }

    public void setClose_date(LocalDate close_date) {
        this.close_date = close_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdSubs() {
        return idSubs;
    }

    public void setIdSubs(String idSubs) {
        this.idSubs = idSubs;
    }

    public ProductDTO getProductId() {
        return productId;
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

    public LocalDate getDate() {
        return date;
    }

}
