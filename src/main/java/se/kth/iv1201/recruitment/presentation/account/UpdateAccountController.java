package se.kth.iv1201.recruitment.presentation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        PersonDTO person= service.checkUsernameDateOfBirthOrEmailExists(user.getUsername(), user.getDateOfBirth(), user.getEmail());
       /* person.setUsername(user.getUsername());
        person.setPassword(user.getPassword());
        person.setFirstName(user.getFirstName());
        person.setLastName(user.getLastName());
        person.setEmail(user.getEmail());
        person.setDateOfBirth(user.getDateOfBirth());
        person.setIsComplete(user.getIsComplete());*/
        //person.setRoleId(currentPerson.getRoleId());
       //ersonDTO person = service.getPersonIdData(user.getPersonId());
        service.updatePerson(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(),
                             user.getDateOfBirth(), person.getRoleId(), user.getIsComplete());

        return "/" + SUCCESS_UPDATED_ACCOUNT_PAGE_URL;
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
