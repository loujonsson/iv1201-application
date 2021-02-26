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
import se.kth.iv1201.recruitment.domain.IllegalRecruitmentTransactionException;

@Controller
public class ExceptionHandlers implements ErrorController {
    public static final String ERROR_TYPE_KEY = "errorType";
    public static final String GENERIC_ERROR = "generic";
    public static final String SAVE_ACCOUNT_FAILED = "missing attribute";
    public static final String LOGIN_FAILED = "login";
    Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);
    static final String ERROR_URL = "error";

    @ExceptionHandler({IllegalUsernameInsertion.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleIllegalUsernameException(IllegalUsernameInsertion exception){
        logger.info(exception.toString());
        return "redirect:" + ERROR_URL + "/username";
    }

    @GetMapping("/" + ERROR_URL + "/{errorType}")
    //@RequestMapping(value = "errormessage", method = RequestMethod.GET)
    public String showErrorView(@PathVariable("errorType") String errorType, Model model){
        System.out.println("HEJ LOUUUUU");
        if(errorType.equals("username")){
            model.addAttribute("errorType", errorType);
            return "/" + ERROR_URL;
        }

        return "errorType";
        //return String.format("Username already exists2");
        //return "/" + ERROR_URL;
    }

    /**
     * Exception handler for broken business rules.
     *
     * @return An appropriate error page.
     */
    @ExceptionHandler(IllegalRecruitmentTransactionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(IllegalRecruitmentTransactionException exception, Model model) {
        logExceptionDebugLevel(exception);
        if (exception.getMessage().toUpperCase().contains("MISSING ATTRIBUTE")) {
            model.addAttribute(ERROR_TYPE_KEY, SAVE_ACCOUNT_FAILED);
        } else if (exception.getMessage().toUpperCase().contains("LOGIN")) {
            model.addAttribute(ERROR_TYPE_KEY, LOGIN_FAILED);
        } else {
            model.addAttribute(ERROR_TYPE_KEY, GENERIC_ERROR);
        }
        return "/" + ERROR_URL;
    }

    @RequestMapping("/" + ERROR_URL)
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) { //404
                logger.info("A 404 not found exception occurred.");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { //500
                logger.info("A 500 internal server error exception occurred.");
            }
            else if(statusCode == HttpStatus.BAD_REQUEST.value()) { //400
                logger.info("A 400 bad request occurred.");
            }
            else {
                logger.info("Unspecified error.");
            }
        }

        return "/" + ERROR_URL;
    }

    private void logExceptionDebugLevel(Exception exception) {
        logger.debug("Exception handler got {}: {}", exception.getClass().getName(), exception.getMessage(), exception);
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
