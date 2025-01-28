public class InsufficientAmountException extends Exception {
    public InsufficientAmountException(){
        super("Please enter a valid amount");
    }
    public InsufficientAmountException(String message){
        super(message);
    }
}
