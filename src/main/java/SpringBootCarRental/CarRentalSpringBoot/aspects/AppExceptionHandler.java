package SpringBootCarRental.CarRentalSpringBoot.aspects;

import SpringBootCarRental.CarRentalSpringBoot.exceptions.AppExceptions;
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
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An App error occurred.");
    }




}
