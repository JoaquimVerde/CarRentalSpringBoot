package SpringBootCarRental.CarRentalSpringBoot.util;

public enum Messages {

    CAR_ID_NOT_FOUND("Car Id not found: "),
    CLIENT_ID_NOT_FOUND("Client Id not found: "),
    RENTAL_ID_NOT_FOUND("Rental Id not found: "),
    CLIENT_EMAIL_ALREADY_EXISTS("Client Email already exists"),
    CAR_PLATES_ALREADY_EXIST("Car license plate already exists"),
    CANNOT_DECREASE_KM("Cannot decrease km"),
    UNAVAILABLE_TO_RENT("Car unavailable to rent"),
    CANNOT_DELETE_CAR("Cannot delete this car: "),
    CANNOT_DELETE_CLIENT("Cannot delete this client: "),
    RETURN_DATE_CANNOT_BE_PAST("return date cannot be in the past."),
    CANNOT_FIND_THAT_BRAND_CAR("No car available of that brand."),
    CANNOT_FIND_THAT_EMAIL_CLIENT("No client available for that email.");


    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
