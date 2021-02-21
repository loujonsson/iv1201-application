package se.kth.iv1201.recruitment.presentation.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionHandlers implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //do something like logging

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) { //404
                //do something like logging
                return "error";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { //500
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
