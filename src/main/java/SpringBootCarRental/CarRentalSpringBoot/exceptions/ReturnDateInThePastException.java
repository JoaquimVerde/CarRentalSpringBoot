package SpringBootCarRental.CarRentalSpringBoot.exceptions;

public class ReturnDateInThePastException extends AppExceptions{
    public ReturnDateInThePastException(String message) {
        super(message);
    }
}
