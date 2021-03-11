package se.kth.iv1201.recruitment.presentation.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.IllegalRecruitmentTransactionException;
import se.kth.iv1201.recruitment.domain.PersonDTO;

import javax.validation.Valid;

@Controller
@Scope("session")
public class LoginController {
    static final String LOGIN_PAGE_URL = "login";
    static final String SUCCESS_LOGIN_PAGE_URL = "login-success";
    private static final String CURRENT_ACCT_FORM_OBJ_NAME = "currentAcctForm";
    static final String LOGOUT_PAGE_URL = "logout";

    @Autowired
    private RecruitmentService service;

    /**
     * Handles get request for login button on create account page
     *
     * @return The login page url
     */
    @GetMapping("/" + LOGIN_PAGE_URL)
    public String showLoginView(@ModelAttribute("loginForm") LoginForm loginForm){
        return LOGIN_PAGE_URL;
    }

    /**
     * Login
     *
     * @param loginForm
     * @param bindingResult
     * @param model
     * @return Login success page URL
     * @throws IllegalRecruitmentTransactionException
     */
    @RequestMapping(value = "/" + LOGIN_PAGE_URL, params = {"error", "logout"}, method = RequestMethod.POST)
    public String saveLoginForm(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) throws IllegalRecruitmentTransactionException {
        if(bindingResult.hasErrors()) {
            model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, loginForm);
            return "/" + LOGIN_PAGE_URL;
        }
        else {
            PersonDTO applicantLoginSuccess = service.checkLogin(loginForm.getUsername(), loginForm.getPassword());
            if(applicantLoginSuccess != null){
                return "redirect:" + SUCCESS_LOGIN_PAGE_URL;
            }else{
                return "redirect:" + LOGIN_PAGE_URL;
            }
        }
    }

    /**
     * View consisting a success page if a user successfully logged in
     *
     * @return url for success login page view
     */
    @GetMapping("/" + SUCCESS_LOGIN_PAGE_URL)
    public String showSuccessLoginView(){
        System.out.println("hello from login");
        return SUCCESS_LOGIN_PAGE_URL;
    }

    @PostMapping("/" + LOGOUT_PAGE_URL)
    public String redirectToLogin(){
        System.out.println("hello from logout");
        return "redirect:" + LOGIN_PAGE_URL + "?logout";
    }

}
