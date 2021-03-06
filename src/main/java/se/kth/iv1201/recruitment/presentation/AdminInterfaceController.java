package se.kth.iv1201.recruitment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;

/**
 * Controller for the admin interface
 */
@Controller
@Scope("session")
public class AdminInterfaceController {
    static final String ADMIN_INTERFACE_PAGE_URL = "admin";
    static final String LOGOUT_PAGE_URL = "logout";

    @Autowired
    private RecruitmentService service;

    /**
     * Handles get request for admin interface
     *
     * @return The admin interface page url
     */
    @GetMapping("/" + ADMIN_INTERFACE_PAGE_URL)
    public String showAdminInterfaceView(){
        return ADMIN_INTERFACE_PAGE_URL;
    }

    /**
     * Handles post request from admin interface page
     *
     * @return Log out url
     */
    @PostMapping("/" + ADMIN_INTERFACE_PAGE_URL)
    public String redirectToLogout(){
        return "redirect:" + LOGOUT_PAGE_URL;
    }
}
