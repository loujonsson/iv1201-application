package se.kth.iv1201.recruitment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.Competence;
import se.kth.iv1201.recruitment.domain.CompetenceProfile;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.PersonDTO;
import se.kth.iv1201.recruitment.presentation.account.UpdateAccountForm;
import se.kth.iv1201.recruitment.repository.CompetenceRepository;

import java.util.List;

@Controller
@Scope("session")
public class CompetenceProfileController {

    static final String COMPETENCE_PROFILE_PAGE_URL = "competence-profile";
    static final String SUCCESS_UPDATED_ACCOUNT_PAGE_URL = "update-account-success";

    @Autowired
    private RecruitmentService service;

    /**
     * A get request to show all competences
     *
     * @return All competences n the database
     */
    @RequestMapping("/competence-profile")
    private String listCompetences(Model model){
        List<Competence> list = service.getAllCompetences();
        System.out.println("list: " + list);
        model.addAttribute("competenceProfiles", list);

        return COMPETENCE_PROFILE_PAGE_URL;
    }
}
