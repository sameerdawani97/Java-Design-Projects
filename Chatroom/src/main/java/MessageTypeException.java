/**
 * Exception
 */
public class MessageTypeException extends Throwable {
    /**
     * prints the exception message thrown
     * @param message message
     */
    public MessageTypeException(String message){
        super(message);
        System.out.println(message);
    }
}
