// SPRINT 5

package cat.tecnocampus.ofc.application;


import cat.tecnocampus.ofc.application.daos.IConsumerDAO;
import cat.tecnocampus.ofc.application.daos.IOrderDAO;
import cat.tecnocampus.ofc.application.daos.IProductDAO;
import cat.tecnocampus.ofc.application.daos.ISubscriptionDAO;
import cat.tecnocampus.ofc.application.dtos.ConsumerDTO;
import cat.tecnocampus.ofc.application.dtos.OrderDTO;
import cat.tecnocampus.ofc.application.dtos.ProductDTO;
import cat.tecnocampus.ofc.application.dtos.SubscriptionDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OFCController {

    private IConsumerDAO consumerDAO;
    private ISubscriptionDAO subscriptionDAO;
    private IOrderDAO orderDAO;
    private IProductDAO productDAO;

    //1. crear llistes
    //2. interficie que simuli dao i implementem alla -servie o component ambles llistes -> new de consumer i producte
    public OFCController(IConsumerDAO consumerDAO,ISubscriptionDAO subscriptionDAO,IOrderDAO orderDAO, IProductDAO productDAO) {
        this.consumerDAO = consumerDAO;
        this.subscriptionDAO=subscriptionDAO;
        this.orderDAO=orderDAO;
        this.productDAO=productDAO;
    }

    public void addSubscription (SubscriptionDTO subscription){
        subscriptionDAO.addSubscription(subscription);
    }

    public List<SubscriptionDTO> getSubscriptions (String mailCons){
       return subscriptionDAO.getSubscriptions(mailCons);
    }
    /*TODO

     public void addSubscription (SubscriptionDTOAuxiliar subscription){
        subscriptionDAO.addSubscription(subscription);
    }
     public List<SubscriptionDTO> getSubscriptions (String idCons){
        String mail=consumerDAO.getConsumerByEmail(idCons).getEmail();
       return subscriptionDAO.getSubscriptions(mail);
    }
    * */

    public void generateOrderfromSubscription(String mailCons){
        List<SubscriptionDTO> subscriptions=subscriptionDAO.getSubscriptions(mailCons);
        orderDAO.generateOrderfromSubscription(subscriptions);
    }

    public void updateOrder(String idOrder, String amount)  {
        orderDAO.updateOrder( idOrder, amount);
    }

    public List<OrderDTO> getOrders(String mailCons) {
        List<OrderDTO> result= new ArrayList<OrderDTO>();
        result.addAll(orderDAO.getOrders(mailCons));
        return result;
    }
    public List<ProductDTO> getProducts(){
        return productDAO.getAllProducts();
    }
}
