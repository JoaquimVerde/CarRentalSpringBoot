package SpringBootCarRental.CarRentalSpringBoot.util;

public enum Messages {

    CAR_ID_NOT_FOUND ("Car Id not found: "),
    CLIENT_ID_NOT_FOUND ("Client Id not found: "),
    RENTAL_ID_NOT_FOUND ("Rental Id not found: "),
    CLIENT_EMAIL_ALREADY_EXISTS ("Client Email already exists"),
    CAR_PLATES_ALREADY_EXIST ("Car license plate already exists"),
    CANNOT_DECREASE_KM ("Cannot decrease km"),
    UNAVAILABLE_TO_RENT("Car unavailable to rent");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
