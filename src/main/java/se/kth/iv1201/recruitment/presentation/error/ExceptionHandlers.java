package se.kth.iv1201.recruitment.presentation.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ExceptionHandlers implements ErrorController {
    final Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @GetMapping("error")
    public String handleError(HttpServletRequest request) {
        //do something like logging

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            logger.info(statusCode.toString()); //statuskod alltid 404? Skumt...

            if(statusCode == HttpStatus.NOT_FOUND.value()) { //404
                //do something like logging
                //logger.info("A 404 not found exception occurred.");
                return "error";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { //500
                //do something like logging
                logger.info("A 500 internal server error exception occurred.");
                return "error";
            }
            else if(statusCode == HttpStatus.BAD_GATEWAY.value()) { //502
                //do something like logging
                return "error";
            }
            else if(statusCode == HttpStatus.BAD_REQUEST.value()) { //400
                //do something like logging
                return "error";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) { //403
                //do something like logging
                return "error";
            }
            else if(statusCode == HttpStatus.MOVED_PERMANENTLY.value()) { //301
                //do something like logging
                return "error";
            }
            else if(statusCode == HttpStatus.REQUEST_TIMEOUT.value()) { //408
                //do something like logging
                return "error";
            }
            else if(statusCode == HttpStatus.TOO_MANY_REQUESTS.value()) { //429
                //do something like logging
                return "error";
            }
            else if(statusCode == HttpStatus.UNAUTHORIZED.value()) { //401
                //do something like logging
                return "error";
            }
        }

        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
