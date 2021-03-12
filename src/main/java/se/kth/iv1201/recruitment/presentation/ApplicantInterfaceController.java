package se.kth.iv1201.recruitment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;

/**
 * Controller for the applicant interface
 */
@Controller
@Scope("session")
public class ApplicantInterfaceController {
    static final String APPLICANT_INTERFACE_PAGE_URL = "application";
    static final String LOGOUT_PAGE_URL = "logout";

    @Autowired
    private RecruitmentService service;

    /**
     * Handles get request for applicant interface
     *
     * @return The admin interface page url
     */
    @GetMapping("/" + APPLICANT_INTERFACE_PAGE_URL)
    public String showAdminInterfaceView(){
        return APPLICANT_INTERFACE_PAGE_URL;
    }

    /**
     * Handles post request from admin interface page
     *
     * @return Log out url
     */
    @PostMapping("/" + APPLICANT_INTERFACE_PAGE_URL)
    public String redirectToLogout(){
        return "redirect:" + LOGOUT_PAGE_URL;
    }
}
