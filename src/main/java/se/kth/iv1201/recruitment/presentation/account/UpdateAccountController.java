package se.kth.iv1201.recruitment.presentation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.PersonDTO;

/**
 * Handles HTTP requests for when user with missing attributes updates the existing account
 */
@Controller
@Scope("session")
public class UpdateAccountController {

    static final String UPDATE_ACCOUNT_PAGE_URL = "update-account";
    static final String SUCCESS_UPDATED_ACCOUNT_PAGE_URL = "update-account-success";
    static final String LOGIN_PAGE_URL = "login";

    @Autowired
    private RecruitmentService service;

    /**
     * A get request for the update account page.
     *
     * @return The update account page url
     */
    @GetMapping("/" + UPDATE_ACCOUNT_PAGE_URL)
    public String showUpdateAccountView(@ModelAttribute("updateAccountForm") UpdateAccountForm updateAccountForm){
        return UPDATE_ACCOUNT_PAGE_URL;
    }

    /**
     * Shows update account form
     *
     * @param model
     * @return update account page url
     */
    @GetMapping("/update")
    public String showForm(Model model) {
        UpdateAccountForm updateAccountForm = new UpdateAccountForm();
        model.addAttribute("updateAccountForm", updateAccountForm);
        return UPDATE_ACCOUNT_PAGE_URL;
    }

    /**
     * Submits update account form and redirect to login for non-repudiation
     *
     * @param user
     * @param model
     * @param result
     * @return login url
     */
    @PostMapping("/update")
    public String submitForm(@ModelAttribute("updateAccountForm") UpdateAccountForm user, Model model, BindingResult result){
        if(result.hasErrors()){
            return "/" + UPDATE_ACCOUNT_PAGE_URL;
        }
        PersonDTO personFromDB = service.checkUsernameDateOfBirthOrEmailExists(user.getUsername(), user.getDateOfBirth(), user.getEmail());
        user.setPersonId(personFromDB.getPersonId());
        service.updatePerson(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateOfBirth(), user.getIsComplete());

        return "/" + LOGIN_PAGE_URL;
    }

    /**
     * View consisting of a success page when account has been successfully created
     *
     * @return url for success update account view
     */
    @GetMapping("/" + SUCCESS_UPDATED_ACCOUNT_PAGE_URL)
    public String showSuccessCreateAccountView(){
        return SUCCESS_UPDATED_ACCOUNT_PAGE_URL;
    }

}
