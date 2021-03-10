package se.kth.iv1201.recruitment.presentation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.IllegalAttributeInsertionException;
import se.kth.iv1201.recruitment.domain.IllegalRecruitmentTransactionException;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.PersonDTO;

import javax.validation.Valid;

/**
 * Handles HTTP requests for when user with missing attributes updates the existing account
 */
@Controller
@Scope("session")
public class UpdateAccountController {

    static final String UPDATE_ACCOUNT_PAGE_URL = "update-account";
    static final String SUCCESS_UPDATED_ACCOUNT_PAGE_URL = "update-account-success";

    //static final String FAVICON_GET = "favicon.ico";
    private static final String CURRENT_ACCT_OBJ_NAME = "currentAcct";
    private static final String CURRENT_ACCT_FORM_OBJ_NAME = "currentAcctForm";

    @Autowired
    private RecruitmentService service;
    private PersonDTO currentPerson;

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
     * A get request for the update account page.
     *
     * @return The update account page url
     */
    @GetMapping("/" + UPDATE_ACCOUNT_PAGE_URL)
    public String showUpdateAccountView(@ModelAttribute("updateAccountForm") UpdateAccountForm updateAccountForm){
        return UPDATE_ACCOUNT_PAGE_URL;
    }
    @GetMapping("/update")
    public String showForm(Model model) {
        UpdateAccountForm updateAccountForm = new UpdateAccountForm();
        model.addAttribute("updateAccountForm", updateAccountForm);
/*
        model.addAttribute("email", currentPerson.getEmailAddress());
       model.addAttribute("username", currentPerson.getUsername());
        model.addAttribute("password", currentPerson.getPassword());
        model.addAttribute("firstName", currentPerson.getFirstName());
        model.addAttribute("lastName", currentPerson.getLastName());
        model.addAttribute("dateOfBirth", currentPerson.getDateOfBirth());
        */
        return UPDATE_ACCOUNT_PAGE_URL;
    }

    @PostMapping("/update")
    public String submitForm(@ModelAttribute("updateAccountForm") UpdateAccountForm user) {
        System.out.println(user);
        PersonDTO person = service.checkUsernameDateOfBirthOrEmailExists(user.getUsername(), user.getDateOfBirth(), user.getEmail());
        person.setUsername(user.getUsername());
        person.setPassword(user.getPassword());
        person.setFirstName(user.getFirstName());
        person.setLastName(user.getLastName());
        person.setEmail(user.getEmail());
        person.setDateOfBirth(user.getDateOfBirth());
        //person.setIsComplete(user.getIsComplete());
        //person.setRoleId(currentPerson.getRoleId());
        service.updatePerson(person);

        return "/" + SUCCESS_UPDATED_ACCOUNT_PAGE_URL;
    }

    /**
     * The update account form has been submitted.
     *
     * @param updateAccountForm
     * @param bindingResult
     * @param model
     * @return
     *
    @PostMapping("/" + UPDATE_ACCOUNT_PAGE_URL)
    public String saveForm(@Valid @ModelAttribute("updateAccountForm") UpdateAccountForm updateAccountForm, BindingResult bindingResult, Model model) throws IllegalAttributeInsertionException, IllegalRecruitmentTransactionException {
        if(bindingResult.hasErrors()) {
            model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, updateAccountForm);
            return "/" + UPDATE_ACCOUNT_PAGE_URL;
        }
        else {
            //TODO: update this method
            if (currentPerson != null) {
                model.addAttribute(CURRENT_ACCT_FORM_OBJ_NAME, updateAccountForm);
                return UPDATE_ACCOUNT_PAGE_URL;
            }

           /* if(service.checkUsernameExists(updateAccountForm.getUsername()) != null){
                if(!updateAccountForm.getUsername().equals(currentPerson.getUsername())) {
                    throw new IllegalAttributeInsertionException("A user with this username already exists!");
                }
            }
            if(service.checkEmailExists(updateAccountForm.getEmail()) != null){
                throw new IllegalAttributeInsertionException("A user with this email already exists!");
            }
            if(service.checkDateOfBirthExists(updateAccountForm.getDateOfBirth()) != null){
                throw new IllegalAttributeInsertionException("A user with this date of birth already exists!");
            }
            currentPerson = service.checkEmailExists(updateAccountForm.getEmail());

            if(currentPerson != null) {
                currentPerson.setUsername(updateAccountForm.getUsername());
                currentPerson.setPassword(updateAccountForm.getPassword());
                currentPerson.setFirstName(updateAccountForm.getFirstName());
                currentPerson.setLastName(updateAccountForm.getLastName());
                currentPerson.setEmail(updateAccountForm.getEmail());
                currentPerson.setDateOfBirth(updateAccountForm.getDateOfBirth());
                currentPerson.setRoleId(updateAccountForm.getRoleId());
                currentPerson.setIsComplete(updateAccountForm.getIsComplete());
                service.updatePerson((Person) currentPerson);
            }
            return "redirect:" + SUCCESS_UPDATED_ACCOUNT_PAGE_URL;
        }
    }*/

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
