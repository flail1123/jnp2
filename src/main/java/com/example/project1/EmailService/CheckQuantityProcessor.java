package com.example.project1.EmailService;

import com.example.project1.SweaterFactory.BlueSweater;
import com.example.project1.SweaterFactory.RedSweater;
import com.example.project1.SweaterFactory.Sweater;
import com.example.project1.SweaterFactory.YellowSweater;
import com.example.project1.SweaterWarehouse;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;

import java.awt.*;


public class CheckQuantityProcessor implements Processor {
    EmailRepository emailRepository;
    SweaterWarehouse sweaterWarehouse;
    public CheckQuantityProcessor(EmailRepository emailRepository, SweaterWarehouse sweaterWarehouse) {
        this.emailRepository = emailRepository;
        this.sweaterWarehouse = sweaterWarehouse;
    }
    public void process(Exchange exchange) {

        System.out.println(exchange.getIn().getBody(String.class));
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
        String result = "";
        for (UserEmail u: emailRepository.findAll()){
            System.out.println("email: " + u.getEmail());
            System.out.println("id: " + u.getSweaterId());
            if (u.getSweaterId() == 0 && redQuantity > 0){
                result = u.getEmail();
                emailRepository.delete(u);
                break;
            }
            if (u.getSweaterId() == 1 && blueQuantity > 0){
                result = u.getEmail();
                emailRepository.delete(u);
                break;
            }
            if (u.getSweaterId() == 2 && yellowQuantity > 0){
                result = u.getEmail();
                emailRepository.delete(u);
                break;
            }
        }

        System.out.println(result+"!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("-----------------------------------------------------------------------");
        exchange.getIn().setBody(result);
    }


}



