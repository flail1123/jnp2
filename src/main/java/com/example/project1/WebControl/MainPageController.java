package com.example.project1.WebControl;

import com.example.project1.EmailService.EmailRepository;
import com.example.project1.EmailService.UserEmail;
import com.example.project1.Global;
import com.example.project1.SweaterFactory.BlueSweater;
import com.example.project1.SweaterFactory.RedSweater;
import com.example.project1.SweaterFactory.Sweater;
import com.example.project1.SweaterFactory.YellowSweater;
import com.example.project1.SweaterWarehouse;
import com.example.project1.WebControl.Context;
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
        int choice = -1;
        int i = 0;
        for (UserEmail e : emails)
            i++;
        if (i > 0) {
            email1 = ((ArrayList<UserEmail>) emails).get(0).getEmail();
            choice = ((ArrayList<UserEmail>) emails).get(0).getSweaterId();
        }

        // generate context
        Context c = new Context(blueQuantity, yellowQuantity, redQuantity, email1, choice);
        model.addAttribute("context", c);
        model.addAttribute("userEmail", new UserEmail());
        return "shop";
    }

    private Integer findById(Integer id) {
        Iterable<Sweater> sweaters = warehouse.findAll();
        for (Sweater s : sweaters) {
            switch (id) {
                case 0:
                    if (s instanceof BlueSweater)
                        return s.getSweaterID();
                    break;
                case 1:
                    if (s instanceof YellowSweater)
                        return s.getSweaterID();
                    break;
                case 2:
                    if (s instanceof RedSweater)
                        return s.getSweaterID();
                    break;
                default:
                    return -1;
            }
        }
        return -1;
    }


    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String indexForm(@ModelAttribute UserEmail userEmail, Model model) {

        int boughtItem = -1;

        if (userEmail.getSweaterId() == Global.buyId1)
            boughtItem = findById(0);
        if (userEmail.getSweaterId() == Global.buyId2)
            boughtItem = findById(1);
        if (userEmail.getSweaterId() == Global.buyId3)
            boughtItem = findById(2);

        if (boughtItem != -1)
            warehouse.deleteById(boughtItem);

        UserEmail email = emailRepository.save(userEmail);
        model.addAttribute("userEmail", new UserEmail());
        return "redirect:/index";
    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String indexGet(@ModelAttribute UserEmail userEmail, Model model) {
//        UserEmail email = emailRepository.save(userEmail);
//        model.addAttribute("userEmail", new UserEmail());
//        return "redirect:/index";
//    }
}
