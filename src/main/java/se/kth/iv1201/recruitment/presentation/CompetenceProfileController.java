package se.kth.iv1201.recruitment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.Competence;
import java.util.List;
import java.util.Locale;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Controller
@Scope("session")
public class CompetenceProfileController {

    static final String COMPETENCE_PROFILE_PAGE_URL = "competence-profile";

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
        model.addAttribute("competenceProfiles", list);
        Locale currentLocale = getLocale();
        currentLocale.getLanguage();
        if(currentLocale.getLanguage().equals("en")){
            model.addAttribute("languageEN", list);
        }
        if(currentLocale.getLanguage().equals("se")){
            model.addAttribute("languageSE", list);
        }

        return COMPETENCE_PROFILE_PAGE_URL;
    }
}
