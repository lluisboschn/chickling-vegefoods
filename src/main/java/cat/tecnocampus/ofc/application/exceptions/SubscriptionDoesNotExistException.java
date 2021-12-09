// SPRINT 5

package cat.tecnocampus.ofc.application.exceptions;

public class SubscriptionDoesNotExistException extends  RuntimeException{
    public SubscriptionDoesNotExistException(String id){
        super("Subscription with id=" + id + " does NOT exist");
    }
}
