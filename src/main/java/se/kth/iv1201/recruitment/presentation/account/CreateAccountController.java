package se.kth.iv1201.recruitment.presentation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.IllegalRecruitmentTransactionException;
import se.kth.iv1201.recruitment.domain.IllegalUsernameInsertion;
import se.kth.iv1201.recruitment.domain.PersonDTO;

import javax.validation.Valid;

/**
 * Handles all HTTP requests
 */
@Controller
@Scope("session")
public class CreateAccountController {
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
    public String saveForm(@Valid @ModelAttribute("createAccountForm") CreateAccountForm createAccountForm, BindingResult bindingResult, Model model) throws IllegalUsernameInsertion, IllegalRecruitmentTransactionException {
        if(bindingResult.hasErrors()) {
            model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, createAccountForm);
            return "/" + CREATE_ACCOUNT_PAGE_URL;
        }
        else {

            if (currentPerson != null) {
                model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, createAccountForm);
                return CREATE_ACCOUNT_PAGE_URL;
            }

            if(service.checkUsernameExists(createAccountForm.getUsername()) != null){
                throw new IllegalUsernameInsertion("Username already exists!");
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
}
