package com.example.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
    private SweaterWarehouse warehouse;
    @Autowired
    public MainPageController(SweaterWarehouse warehouse){
        this.warehouse = warehouse;
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome";
    }

    @RequestMapping("/list")
    public String listSweaters() {
        int redQuantity = 0;
        int yellowQuantity = 0;
        int blueQuantity = 0;
        StringBuilder answer = new StringBuilder("All sweaters:<br>");
        for (Sweater s: warehouse.findAll()) {
            if (s instanceof RedSweater)
                redQuantity++;
            if (s instanceof YellowSweater)
                yellowQuantity++;
            if (s instanceof BlueSweater)
                blueQuantity++;
        }
        answer.append("Red sweaters: ").append(redQuantity).append("<br>");
        answer.append("Blue sweaters: ").append(blueQuantity).append("<br>");
        answer.append("Yellow sweaters: ").append(yellowQuantity).append("<br>");
        return answer.toString();
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
        return "shop";
    }

}
