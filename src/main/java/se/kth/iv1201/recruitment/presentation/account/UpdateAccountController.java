package se.kth.iv1201.recruitment.presentation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;
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

    @Autowired
    private RecruitmentService service;

    private PersonDTO oldPerson;

    /**
     * A get request for the update account page.
     *
     * @return The update account page url
     */
    @GetMapping("/" + UPDATE_ACCOUNT_PAGE_URL)
    public String showUpdateAccountView(@ModelAttribute("updateAccountForm") UpdateAccountForm updateAccountForm){
        return UPDATE_ACCOUNT_PAGE_URL;
    }

    /*
    @GetMapping("/update-account")
    public String showUpdateAccountView(@ModelAttribute("person") Person user){
        return "update-account";
    }*/
    /*
    @RequestMapping(value = "/update-person", method = RequestMethod.GET)
    public String initUpdatePersonForm(PersonDTO person, Model model) {
        PersonDTO newPerson = service.getPersonIdData(person.getPersonId());
        System.out.println("initUpdatePerson newPerson id: " + newPerson.getPersonId());
        model.addAttribute("person", newPerson);
        System.out.println("initUpdatePerson person: " + newPerson);
        return "/update-person";
    }

    @RequestMapping(value = "/update-person", method = RequestMethod.POST)
    public String processUpdatePersonForm(@Valid @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "/update-person";
        } else {
            //PersonDTO personFromDb = service.getPersonIdData(person.getPersonId());
            System.out.println("before saving person: " + person);
       UPDATE_ACCOUNT_PAGE_URL     service.savePerson(person);
            return "redirect:/update-account-success";
        }
    }*/
    /*
    @GetMapping("/update")
    public String showForm(Model model){
        Person user = new Person();
        model.addAttribute("person", user);
        return "update-account";
    }
    @PostMapping("/update")
    public String submitForm(@ModelAttribute("person") Person user){
        System.out.println("user: "+user);
        PersonDTO person = service.checkUsernameDateOfBirthOrEmailExists(user.getUsername(), user.getDateOfBirth(), user.getEmailAddress());
        System.out.println("person in sumbit form: " + person);
        //user.setPersonId(person.getPersonId());
        //System.out.println("user2: "+user);
        service.updatePerson(person, user);
        // service.updatePerson(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateOfBirth(), user.getIsComplete());
        service.savePerson(person);

        return "/" + SUCCESS_UPDATED_ACCOUNT_PAGE_URL;
    }*/

    @GetMapping("/update")
    public String showForm(Model model) {
        UpdateAccountForm updateAccountForm = new UpdateAccountForm();
        model.addAttribute("updateAccountForm", updateAccountForm);
        return UPDATE_ACCOUNT_PAGE_URL;
    }


    @PostMapping("/update")
    public String submitForm(@ModelAttribute("updateAccountForm") UpdateAccountForm user, Model model, BindingResult result){
        if(result.hasErrors()){
            return "/"+UPDATE_ACCOUNT_PAGE_URL;
        }
        System.out.println("user: "+user);
        PersonDTO personFromDB = service.checkUsernameDateOfBirthOrEmailExists(user.getUsername(), user.getDateOfBirth(), user.getEmail());
        System.out.println("person in submit form: " + personFromDB);
        user.setPersonId(personFromDB.getPersonId());
        System.out.println("user2: "+user);
        //service.updatePerson(personFromDB, user);
        service.updatePerson(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateOfBirth(), user.getIsComplete());
        //service.savePerson(personFromDB);

        /*person.setUsername(user.getUsername());
        person.setPassword(user.getPassword());
        person.setFirstName(user.getFirstName());
        person.setLastName(user.getLastName());
        person.setEmail(user.getEmail());
        person.setDateOfBirth(user.getDateOfBirth());
        person.setIsComplete(user.getIsComplete());*/

       //ersonDTO person = service.getPersonIdData(user.getPersonId());

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
