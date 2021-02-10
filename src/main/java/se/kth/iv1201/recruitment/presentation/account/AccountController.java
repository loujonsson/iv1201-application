package se.kth.iv1201.recruitment.presentation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.ApplicantDTO;

/**
 * Handles all HTTP requests
 */
@Controller
@Scope("session")
public class AccountController {
    // pages on format <decriptionOfPage>_PAGE_URL (for now)
    static final String DEFAULT_PAGE_URL = "/";
    static final String CREATE_ACCOUNT_PAGE_URL = "create-account";
    @Autowired
    private RecruitmentService service;
    private ApplicantDTO currentApplicant;

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
     * A get request for the create account page.
     *
     * @return The create account page url
     */
    @GetMapping("/" + CREATE_ACCOUNT_PAGE_URL)
    public String showCreateAccountView(CreateAccountForm createAccountForm){
        return CREATE_ACCOUNT_PAGE_URL;
    }

    /**
     * The create account form has been submitted.
     *
     * @param createAccountForm Content of the create account form.
     */
    @PostMapping("/" + CREATE_ACCOUNT_PAGE_URL)
    public void createAccount(CreateAccountForm createAccountForm) {
        System.out.println(createAccountForm.getFirstName());

        currentApplicant = service.createApplicant(createAccountForm.getUsername(), createAccountForm.getFirstName(), createAccountForm.getLastName(),
                createAccountForm.getDateOfBirth(), createAccountForm.getEmail(), createAccountForm.getPassword());

        //return "redirect:" + CREATE_ACCOUNT_PAGE_URL;
    }

}
