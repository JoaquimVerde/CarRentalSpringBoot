package SpringBootCarRental.CarRentalSpringBoot.aspects;

import SpringBootCarRental.CarRentalSpringBoot.exceptions.*;
import SpringBootCarRental.CarRentalSpringBoot.util.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class AppExceptionHandler {



    @ExceptionHandler(value = {AppExceptions.class})
    public ResponseEntity<String> AppException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An app error has occurred");
    }

    @ExceptionHandler(value = {RentalIdNotFoundException.class})
    public ResponseEntity<String> RentalIdNotFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No such Rental ID.");
    }

    @ExceptionHandler(value = {ClientIdNotFoundException.class})
    public ResponseEntity<String> ClientIdNotFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No such Client ID.");
    }

    @ExceptionHandler(value = {CarIdNotFoundException.class})
    public ResponseEntity<String> CarIdNotFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No such Car ID.");
    }

    @ExceptionHandler(value = {CannotDeleteException.class})
    public ResponseEntity<String> CannotDelete(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("The Car or Client already has a registered rental and therefore cannot be deleted.");
    }




}
