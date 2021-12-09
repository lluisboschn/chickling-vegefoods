// SPRINT 5

package cat.tecnocampus.ofc.application.exceptions;

public class ConsumerDoesNotExistException extends  RuntimeException{
    public ConsumerDoesNotExistException(String mail){
        super("Consumer " +mail + " does NOT exist");
    }
}
