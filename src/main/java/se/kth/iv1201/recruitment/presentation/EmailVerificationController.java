package se.kth.iv1201.recruitment.presentation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Scope("session")
public class EmailVerificationController {
    static final String EMAIL_VERIFICATION = "email-verification";

    @GetMapping("/" + EMAIL_VERIFICATION)
    public String showEmailView(){
        return "/" + EMAIL_VERIFICATION;
    }

}
