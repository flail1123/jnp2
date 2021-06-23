package com.example.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class MainPageController {
    private final SweaterWarehouse warehouse;
    protected final EmailRepository emailRepository;

    @Autowired
    public MainPageController(SweaterWarehouse warehouse, EmailRepository emailRepository){
        this.warehouse = warehouse;
        this.emailRepository = emailRepository;
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome";
    }

    //// To nie działa w thymeleaf:

//    @RequestMapping("/list")
//    public String listSweaters() {
//        int redQuantity = 0;
//        int yellowQuantity = 0;
//        int blueQuantity = 0;
//        StringBuilder answer = new StringBuilder("All sweaters:<br>");
//        for (Sweater s: warehouse.findAll()) {
//            if (s instanceof RedSweater)
//                redQuantity++;
//            if (s instanceof YellowSweater)
//                yellowQuantity++;
//            if (s instanceof BlueSweater)
//                blueQuantity++;
//        }
//        answer.append("Red sweaters: ").append(redQuantity).append("<br>");
//        answer.append("Blue sweaters: ").append(blueQuantity).append("<br>");
//        answer.append("Yellow sweaters: ").append(yellowQuantity).append("<br>");
//        return answer.toString();
//    }

    @RequestMapping(value = "/index")
    public String index(Model model) {
        int redQuantity = 0;
        int yellowQuantity = 0;
        int blueQuantity = 0;

        // count sweaters
        for (Sweater s: warehouse.findAll()) {
            if (s instanceof RedSweater)
                redQuantity++;
            if (s instanceof YellowSweater)
                yellowQuantity++;
            if (s instanceof BlueSweater)
                blueQuantity++;
        }

        Iterable<UserEmail> emails = emailRepository.findAll();
        String email1 = new String("");
        int i = 0;
        for (UserEmail e : emails)
            i++;
        if (i > 0)
            email1 = ((ArrayList<UserEmail>)emails).get(0).getEmail();

        // generate context
        Context c = new Context(blueQuantity, yellowQuantity, redQuantity, email1);
        model.addAttribute("context", c);
        model.addAttribute("userEmail", new UserEmail());
        return "shop";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String indexForm(@ModelAttribute UserEmail userEmail, Model model) {
        UserEmail email = emailRepository.save(userEmail);
        model.addAttribute("userEmail", new UserEmail());
        return "redirect:/index";
    }

}
