// SPRINT 5

package cat.tecnocampus.ofc.application;


import cat.tecnocampus.ofc.application.daos.IConsumerDAO;
import cat.tecnocampus.ofc.application.daos.IOrderDAO;
import cat.tecnocampus.ofc.application.daos.ISubscriptionDAO;
import cat.tecnocampus.ofc.application.dtos.OrderDTO;
import cat.tecnocampus.ofc.application.dtos.SubscriptionDTO;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableScheduling
public class ScheduleController {
    private IConsumerDAO consumerDAO;
    private ISubscriptionDAO subscriptionDAO;
    private IOrderDAO orderDAO;

    //GENERATE ALL ORDERS
    @Scheduled(cron = "* * 2 * * 1")
    public void generateAllOrders(){
        List<SubscriptionDTO> subscriptions=subscriptionDAO.getAllSubscriptions();
        orderDAO.generateOrderfromSubscription(subscriptions);
    }

    //CANVI D'ESTAT DE ORDER
    // @Scheduled(cron ="0 0 * * * *")
    @Scheduled(cron = "* * 23.45 * * 5")
    public void changeOrdersState (){
        for(OrderDTO order:orderDAO.getAllOrders()){
            if (order.getClose_date().compareTo(LocalDate.now()) <= 0 ){
                //We haven't reached the close date
                orderDAO.UpdateOrderEstate(order.getId(), "OPEN");
            }
            else if (order.getDat_deliver().compareTo(LocalDate.now()) >= 0 ){
                //We have reached the close date
                orderDAO.UpdateOrderEstate(order.getId(), "DELIVERED");
            } else if (order.getDat_deliver().compareTo(LocalDate.now()) < 0){
                //The order is open
                orderDAO.UpdateOrderEstate(order.getId(), "CLOSE");

                //enviem mail
                sendMail(order.getEmail(),order.getId());
            }
        }
    }

    public static void sendMail(String recipient, String order){
        //TODO https://www.youtube.com/watch?v=A7HAB5whD6I
        System.out.println("Preparing to send email...");
        Properties properties=new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "openFoodClub@gmail.com";
        String password="ofc123321";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });

        Message message =prepareMessage(session, myAccountEmail, recipient, order);

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient,String order) {
        try{
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Closed order");
            message.setText("We inform you that your order "+order+" has been closed. You can check");
            return message;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }


}