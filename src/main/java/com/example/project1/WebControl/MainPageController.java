package com.example.project1.WebControl;

import com.example.project1.EmailService.EmailRepository;
import com.example.project1.EmailService.UserEmail;
import com.example.project1.Global;
import com.example.project1.SweaterFactory.BlueSweater;
import com.example.project1.SweaterFactory.RedSweater;
import com.example.project1.SweaterFactory.Sweater;
import com.example.project1.SweaterFactory.YellowSweater;
import com.example.project1.SweaterWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainPageController {
    private final SweaterWarehouse warehouse;
    protected final EmailRepository emailRepository;

    @Autowired
    public MainPageController(SweaterWarehouse warehouse, EmailRepository emailRepository){
        this.warehouse = warehouse;
        this.emailRepository = emailRepository;
    }

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

        // generate context
        Context c = new Context(blueQuantity, yellowQuantity, redQuantity);
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
}
