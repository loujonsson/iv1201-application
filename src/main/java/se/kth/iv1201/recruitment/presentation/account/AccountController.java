package se.kth.iv1201.recruitment.presentation.account;

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

/**
 * Handles all HTTP requests
 */
@Controller
@Scope("session")
public class AccountController {
    static final String DEFAULT_PAGE_URL = "/";
    static final String CREATE_ACCOUNT_PAGE_URL = "create-account";
    static final String SUCCESS_CREATE_ACCOUNT_PAGE_URL = "create-account-success";
    static final String FAVICON_GET = "favicon.ico";
    private static final String CURRENT_ACCT_OBJ_NAME = "currentAcct";
    private static final String CURRENT_ACCT_FORM_OBJ_NAME = "currentAcctForm";

    @Autowired
    private RecruitmentService service;
    private PersonDTO currentPerson;
    /**
     * Currently default view is Create account
     *
     * @return A response that redirects the browser to the Create account page
     */
    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return "redirect:" + CREATE_ACCOUNT_PAGE_URL;
    }

    /**
     * Currently default view is Create account
     *
     * @return A response that redirects the browser to the Create account page
     */
    @GetMapping(FAVICON_GET)
    public String faviconRedirection() {
        return "redirect:" + CREATE_ACCOUNT_PAGE_URL;
    }

    /**
     * A get request for the create account page.
     *
     * @return The create account page url
     */
    @GetMapping("/" + CREATE_ACCOUNT_PAGE_URL)
    public String showCreateAccountView(@ModelAttribute("createAccountForm") CreateAccountForm createAccountForm){
        return CREATE_ACCOUNT_PAGE_URL;
    }

    /**
     * The create account form has been submitted.
     *
     * @param createAccountForm
     * @param bindingResult
     * @param model
     * @return
     */
    //@RequestMapping(value = "/create-account", method = RequestMethod.POST)
    @PostMapping("/" + CREATE_ACCOUNT_PAGE_URL)
    public String saveForm(@Valid @ModelAttribute("createAccountForm") CreateAccountForm createAccountForm, BindingResult bindingResult, Model model) throws IllegalRecruitmentTransactionException {
        if(bindingResult.hasErrors()) {
            model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, createAccountForm);
            return "/" + CREATE_ACCOUNT_PAGE_URL;
        }
        else {

            if (currentPerson != null) {
                model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, createAccountForm);
                return CREATE_ACCOUNT_PAGE_URL;
            }

            service.createPerson(createAccountForm.getUsername(), createAccountForm.getPassword(), createAccountForm.getFirstName(), createAccountForm.getLastName(), createAccountForm.getEmail(), Integer.parseInt(createAccountForm.getDateOfBirth()), createAccountForm.getRoleId());
            return "redirect:" + SUCCESS_CREATE_ACCOUNT_PAGE_URL;
        }
    }

    /**
     * View consisting a success page when account has been successfully created
     *
     * @return url for success create account view
     */
    @GetMapping("/" + SUCCESS_CREATE_ACCOUNT_PAGE_URL)
    public String showSuccessCreateAccountView(){
        return SUCCESS_CREATE_ACCOUNT_PAGE_URL;
    }

    /**
     * Handles get request for login button on create acount page
     *
     * @return The login page url
     */
    @GetMapping("/" + LOGIN_PAGE_URL)
    public String showLoginView(@ModelAttribute("loginForm") LoginForm loginForm){
        return LOGIN_PAGE_URL;
    }

   // @RequestMapping(value="/login", method = RequestMethod.POST)
   @PostMapping("/" + LOGIN_PAGE_URL)
   public String saveLoginForm(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model){
       if(bindingResult.hasErrors()) {
           // TODO: Fråga gruppen om denna current acct form grejen kan återanvändas eller inte
           model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, loginForm);
           return "/" + LOGIN_PAGE_URL;
       }
       else {
           ApplicantDTO applicantLoginSuccess = service.checkLogin(loginForm.getUsername(), loginForm.getPassword());
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
        return SUCCESS_LOGIN_PAGE_URL;
    }

    public int forExampleTest(int a, int b){
        return a+b;
    }




}
