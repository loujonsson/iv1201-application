package se.kth.iv1201.recruitment.presentation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.IllegalRecruitmentTransactionException;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.PersonDTO;
import se.kth.iv1201.recruitment.presentation.login.LoginForm;

import javax.validation.Valid;

/**
 * Handles HTTP requests for when user with missing attributes updates the existing account
 */
@Controller
@Scope("session")
public class UpdateAccountController {
    static final String UPDATE_PAGE_URL = "update";
    static final String UPDATE_ACCOUNT_PAGE_URL = "update-account";
    static final String SUCCESS_UPDATED_ACCOUNT_PAGE_URL = "update-account-success";
    static final String VERIFICATION_PAGE_URL = "verification";
    static final String EMAIL_VERIFICATION_PAGE_URL = "non-repudiation-email";
    private static final String CURRENT_ACCT_FORM_OBJ_NAME = "currentAcctForm";
    private PersonDTO currentUser;
    private PersonDTO personFromDB;

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
    @GetMapping("/" + UPDATE_PAGE_URL)
    public String showForm(Model model) {
        UpdateAccountForm updateAccountForm = new UpdateAccountForm();
        model.addAttribute("updateAccountForm", updateAccountForm);
        return UPDATE_ACCOUNT_PAGE_URL;
    }

    /**
     * Submits update account form and redirect to login for non-repudiation
     *
     * @param user user information from the UpdateAccountForm
     * @param result for validation
     * @return url to verification page
     */
    @PostMapping("/" + UPDATE_PAGE_URL)
    public String submitForm(@ModelAttribute("updateAccountForm") UpdateAccountForm user, BindingResult result){
        if(result.hasErrors()){
            return UPDATE_ACCOUNT_PAGE_URL;
        }
        personFromDB = service.checkUsernameDateOfBirthOrEmailExists(user.getUsername(), user.getDateOfBirth(), user.getEmail());
        user.setPersonId(personFromDB.getPersonId());

        currentUser = new Person(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateOfBirth(), Math.toIntExact(personFromDB.getPersonId()), personFromDB.getIsComplete());

        return "redirect:" + VERIFICATION_PAGE_URL;
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

    /**
     * Verification
     *
     * @param verificationForm
     * @return Login success page URL
     * @throws IllegalRecruitmentTransactionException
     */
    @RequestMapping(value = "/" + VERIFICATION_PAGE_URL, method = RequestMethod.POST)
    public String saveVerificationForm(@Valid @ModelAttribute("verificationForm") LoginForm verificationForm) throws IllegalRecruitmentTransactionException {
        PersonDTO userVerificationSuccess = service.checkLogin(verificationForm.getUsername(), verificationForm.getPassword());
        if(userVerificationSuccess == null){
            return "redirect:" + VERIFICATION_PAGE_URL + "?error";
        }
        if(personFromDB.getPassword().equals("")){
            return "redirect:" + EMAIL_VERIFICATION_PAGE_URL;
        }
        else if(userVerificationSuccess != null){
            service.updatePerson(currentUser.getUsername(), currentUser.getPassword(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmailAddress(), currentUser.getDateOfBirth(), true);
            return "redirect:" + SUCCESS_UPDATED_ACCOUNT_PAGE_URL;
        }else{
            return "redirect:" + VERIFICATION_PAGE_URL ;
        }

    }

    /**
     * Handles get request for verification page
     *
     * @return The login page url
     */
    @GetMapping("/" + VERIFICATION_PAGE_URL)
    public String showVerificationView(@ModelAttribute("verificationForm") LoginForm verificationForm){
        return VERIFICATION_PAGE_URL;
    }


    /**
     * Handles get request for non-repudiation email verification page
     *
     * @return The login page url
     */
    @GetMapping("/" + EMAIL_VERIFICATION_PAGE_URL)
    public String showNonRepudiationEmail(){
        return EMAIL_VERIFICATION_PAGE_URL;
    }

}
