package se.kth.iv1201.recruitment.presentation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.IllegalAttributeInsertionException;
import se.kth.iv1201.recruitment.domain.IllegalRecruitmentTransactionException;
import se.kth.iv1201.recruitment.domain.PersonDTO;

import javax.validation.Valid;

/**
 * Handles HTTP requests for when user with missing attributes updates the existing account
 */
@Controller
@Scope("session")
public class UpdateAccountController {

    static final String DEFAULT_PAGE_URL = "/";
    static final String UPDATE_ACCOUNT_PAGE_URL = "update-account";
    static final String SUCCESS_UPDATED_ACCOUNT_PAGE_URL = "update-account-success";

    //static final String FAVICON_GET = "favicon.ico";
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
        return "redirect:" + UPDATE_ACCOUNT_PAGE_URL;
    }

    /*
     * Currently default view is Create account
     *
     * @return A response that redirects the browser to the Create account page
     *
    @GetMapping(FAVICON_GET)
    public String faviconRedirection() {
        return "redirect:" + UPDATE_ACCOUNT_PAGE_URL;
    }*/

    /**
     * A get request for the create account page.
     *
     * @return The create account page url
     */
    @GetMapping("/" + UPDATE_ACCOUNT_PAGE_URL)
    public String showCreateAccountView(@ModelAttribute("createAccountForm") CreateAccountForm createAccountForm){
        return UPDATE_ACCOUNT_PAGE_URL;
    }

    /**
     * The create account form has been submitted.
     *
     * @param createAccountForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/" + UPDATE_ACCOUNT_PAGE_URL)
    public String saveForm(@Valid @ModelAttribute("createAccountForm") CreateAccountForm createAccountForm, BindingResult bindingResult, Model model) throws IllegalAttributeInsertionException, IllegalRecruitmentTransactionException {
        if(bindingResult.hasErrors()) {
            model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, createAccountForm);
            return "/" + UPDATE_ACCOUNT_PAGE_URL;
        }
        else {
            if (currentPerson != null) {
                model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, createAccountForm);
                return UPDATE_ACCOUNT_PAGE_URL;
            }

            if(service.checkUsernameExists(createAccountForm.getUsername()) != null){
                throw new IllegalAttributeInsertionException("A user with this username already exists!");
            }
            if(service.checkEmailExists(createAccountForm.getEmail()) != null){
                throw new IllegalAttributeInsertionException("A user with this email already exists!");
            }
            if(service.checkDateOfBirthExists(createAccountForm.getDateOfBirth()) != null){
                throw new IllegalAttributeInsertionException("A user with this date of birth already exists!");
            }

            service.createPerson(createAccountForm.getUsername(), createAccountForm.getPassword(), createAccountForm.getFirstName(), createAccountForm.getLastName(), createAccountForm.getEmail(), createAccountForm.getDateOfBirth(), createAccountForm.getRoleId(), createAccountForm.getIsComplete());
            return "redirect:" + SUCCESS_UPDATED_ACCOUNT_PAGE_URL;
        }
    }

    /**
     * View consisting a success page when account has been successfully created
     *
     * @return url for success create account view
     */
    @GetMapping("/" + SUCCESS_UPDATED_ACCOUNT_PAGE_URL)
    public String showSuccessCreateAccountView(){
        return SUCCESS_UPDATED_ACCOUNT_PAGE_URL;
    }

}
