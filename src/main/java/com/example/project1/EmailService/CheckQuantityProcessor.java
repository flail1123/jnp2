package com.example.project1.EmailService;

import com.example.project1.Global;
import com.example.project1.SweaterFactory.BlueSweater;
import com.example.project1.SweaterFactory.RedSweater;
import com.example.project1.SweaterFactory.Sweater;
import com.example.project1.SweaterFactory.YellowSweater;
import com.example.project1.SweaterWarehouse;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class CheckQuantityProcessor implements Processor {
    EmailRepository emailRepository;
    SweaterWarehouse sweaterWarehouse;

    public CheckQuantityProcessor(EmailRepository emailRepository, SweaterWarehouse sweaterWarehouse) {
        this.emailRepository = emailRepository;
        this.sweaterWarehouse = sweaterWarehouse;
    }

    public void process(Exchange exchange) {
        Map<String, String> result = new HashMap<String, String>();
        int redQuantity = 0;
        int yellowQuantity = 0;
        int blueQuantity = 0;

        // count sweaters
        for (Sweater s: sweaterWarehouse.findAll()) {
            if (s instanceof RedSweater)
                redQuantity++;
            if (s instanceof YellowSweater)
                yellowQuantity++;
            if (s instanceof BlueSweater)
                blueQuantity++;
        }
        result.put("red", Integer.toString(redQuantity));
        result.put("yellow", Integer.toString(yellowQuantity));
        result.put("blue", Integer.toString(blueQuantity));
        LocalDate today = LocalDate.now();
        String email = "";
        String title = "Sweater notifier";
        String message = "";
        for (UserEmail u: emailRepository.findAll()){
            System.out.println("email: " + u.getEmail());
            System.out.println("id: " + u.getSweaterId());
            if (u.getSweaterId() == Global.id3 && redQuantity > 0){
                email = u.getEmail();
                emailRepository.delete(u);
                message = "Hi \n Your red sweater is now available in our store. \n Have a nice day \n Sweaters Paradise";
                break;
            }
            if (u.getSweaterId() == Global.id1 && blueQuantity > 0){
                email = u.getEmail();
                emailRepository.delete(u);
                message = "Hi \n Your blue sweater is now available in our store. \n Have a nice day \n Sweaters Paradise";
                break;
            }
            if (u.getSweaterId() == Global.id2 && yellowQuantity > 0){
                email = u.getEmail();
                emailRepository.delete(u);
                message = "Hi \n Your yellow sweater is now available in our store. \n Have a nice day \n Sweaters Paradise";
                break;
            }
            if (u.getSweaterId() == Global.id0 && !u.getLastNewsletter().equals(today) && LocalTime.now().isAfter(LocalTime.parse("10:00:00"))){
                email = u.getEmail();
                emailRepository.delete(u);
                u.setLastNewsletter(today);
                emailRepository.save(u);

                message = "Hi \n Check out our amazing discounts. Only today 10% discount on ALL SWEATERS. \n But be quick promotion stops at 3pm. \n Have a nice day \n Sweaters Paradise";
                title = "Newsletter";
                break;
            }
        }
        result.put("email", email);
        result.put("message", message);
        result.put("title", title);
        System.out.println(result+"!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("-----------------------------------------------------------------------");
        exchange.getIn().setBody(result);
    }
}



