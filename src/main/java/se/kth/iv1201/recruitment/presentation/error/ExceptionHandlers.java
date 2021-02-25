package se.kth.iv1201.recruitment.presentation.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.kth.iv1201.recruitment.domain.IllegalUsernameInsertion;

@Controller
public class ExceptionHandlers implements ErrorController {
    Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);
    static final String ERROR_URL = "error";

    @ExceptionHandler({IllegalUsernameInsertion.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleIllegalUsernameException(IllegalUsernameInsertion exception){
        logger.info(exception.toString());
        return "redirect:" + ERROR_URL + "/username";
    }

    @GetMapping("/" + ERROR_URL + "/{errorType}")

    public String showErrorView(@PathVariable("errorType") String errorType, Model model){
        System.out.println("HEJ LOUUUUU");
        if(errorType.equals("username")){
            model.addAttribute("errorType", errorType);
            return "/" + ERROR_URL;
        }
        return String.format("Username already exists2");
       // return ERROR_URL;
    }

    @RequestMapping("/" + ERROR_URL)
    public String handleError(HttpServletRequest request) {
        //do something like logging

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            //logger.info(statusCode.toString()); //statuskod alltid 404? Skumt...

            if(statusCode == HttpStatus.NOT_FOUND.value()) { //404
                logger.info("A 404 not found exception occurred.");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { //500
                logger.info("A 500 internal server error exception occurred.");
            }
            else if(statusCode == HttpStatus.BAD_GATEWAY.value()) { //502
                //do something like logging
            }
            else if(statusCode == HttpStatus.BAD_REQUEST.value()) { //400
                logger.info("A 400 bad request occurred.");
            }
            else {
                //unspecified error
            }
        }

        return "/" + ERROR_URL;
    }

    /*
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class MyResourceNotFoundException extends RuntimeException {
        public MyResourceNotFoundException() {
            super();
        }
        public MyResourceNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
        public MyResourceNotFoundException(String message) {
            super(message);
        }
        public MyResourceNotFoundException(Throwable cause) {
            super(cause);
        }
    }*/

    @Override
    public String getErrorPath() {
        return null;
    }
}
