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
@ControllerAdvice
public class ExceptionHandlers implements ErrorController {
    public static final String ERROR_TYPE_KEY = "errorType";
    public static final String GENERIC_ERROR = "generic";
    public static final String SAVE_ACCOUNT_FAILED = "missing attribute";
    public static final String LOGIN_FAILED = "login";
    Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);
    static final String ERROR_URL = "error";

    /**
     * Handles when IllegalUsernameInsertion exception is thrown
     * @param exception
     * @return Redirection to /error/username
     */
    @ExceptionHandler(IllegalUsernameInsertion.class)
    public String handleIllegalUsernameException(IllegalUsernameInsertion exception){
        logger.info(exception.toString());
        return "redirect:" + ERROR_URL + "/username";
    }

    /**
     * Depending on error type rendering error message to view
     *
     * @param errorType type of error
     * @param model Model
     * @return Error type key
     */
    @GetMapping("/" + ERROR_URL + "/{errorType}")
    public String showErrorView(@PathVariable("errorType") String errorType, Model model){
        if(errorType.equals("username")){
            model.addAttribute("errortype", "Username already exists!");
        }

        return ERROR_TYPE_KEY;
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

    /**
     * Error handler
     *
     * @param request HTTP request
     * @return Error page url
     */
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

    /**
     * Get error path based on error url
     *
     * @return The error url
     */
    @Override
    public String getErrorPath() {
        return "/" + ERROR_URL;
    }
}
