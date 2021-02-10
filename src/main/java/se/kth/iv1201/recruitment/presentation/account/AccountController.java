package se.kth.iv1201.recruitment.presentation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.Applicant;
import se.kth.iv1201.recruitment.domain.ApplicantDTO;

import javax.validation.Valid;

/**
 * Handles all HTTP requests
 */
@Controller
@Scope("session")
public class AccountController {
    // pages on format <decriptionOfPage>_PAGE_URL (for now)
    static final String DEFAULT_PAGE_URL = "/";
    static final String CREATE_ACCOUNT_PAGE_URL = "create-account";
    static final String ACCT_PAGE_URL = "applicant-account";
    private static final String CURRENT_ACCT_OBJ_NAME = "currentAcct";

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
     * @param createAcctForm Content of the create account form.
     * @param model          Model objects used by the account page.
     * @return The account page url if validation succeeds.
     */
    @PostMapping("/" + CREATE_ACCOUNT_PAGE_URL)
    public String createAccount(@Valid CreateAccountForm createAcctForm, Model model) {
        currentApplicant = service.createApplicant(createAcctForm.getUsername(), createAcctForm.getPassword(), createAcctForm.getFirstName(), createAcctForm.getLastName(), createAcctForm.getEmail(), createAcctForm.getDateOfBirth());
        return showAcctPage(model);
    }

    private String showAcctPage(Model model) {
        if (currentApplicant != null) {
            model.addAttribute(CURRENT_ACCT_OBJ_NAME, currentApplicant);
        }
        return ACCT_PAGE_URL;
    }
}
