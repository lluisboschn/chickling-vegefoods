// SPRINT 5

package cat.tecnocampus.ofc.domain;

import java.util.Calendar;

public class Order {
    /*This class will not be used in this Sprint. However, we need to create it in order to avoid
    * problems with the attributes on other classes*/
    private String id;
    private Consumer consumer;
    private Calendar date;
    public Order(String id, Consumer consumer){
        this.id=id;
        this.consumer=consumer;
        this.date= Calendar.getInstance();
    }
}