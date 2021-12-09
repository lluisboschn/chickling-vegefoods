// SPRINT 5

package cat.tecnocampus.ofc.domain;

import java.util.ArrayList;
import java.util.List;

public class Consumer {
    private String id;
    private String name;
    private String secondName;
    private String email;
    private List<Order> allOrders;
    private List<Subscription> subscribedProducts;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getAllOrders() {
        return allOrders;
    }


    public List<Subscription> getSubscribedProducts() {
        return subscribedProducts;
    }

    public void setSubscribedProducts(List<Subscription> subscribedProducts) {
        this.subscribedProducts = subscribedProducts;
    }

    public Consumer(String id, String name, String secondName, String email, List<Order> allOrders, List<Subscription> subscribedProducts) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.allOrders = allOrders;
        this.subscribedProducts = subscribedProducts;
    }

    public Consumer(String id, String name, String secondName, String email) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.allOrders = new ArrayList<Order>();
        this.subscribedProducts = new ArrayList<Subscription>();
    }

    /*public ConsumerDTO ConsumerToDTO(){
        return new ConsumerDTO(id, name,secondName,email);
    }
   */
}
