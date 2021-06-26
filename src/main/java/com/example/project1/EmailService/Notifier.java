package com.example.project1.EmailService;

import com.example.project1.Global;
import com.example.project1.SweaterWarehouse;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class Notifier extends RouteBuilder {
    private final EmailRepository emailRepository;
    private final SweaterWarehouse sweaterWarehouse;


    public Notifier(EmailRepository emailRepository, SweaterWarehouse sweaterWarehouse) {
        this.emailRepository = emailRepository;
        this.sweaterWarehouse = sweaterWarehouse;
    }

    private String cronWithSecondsInterval(Integer interval) {
        return "0/" + interval.toString() + "+*+*+?+*+*";
    }

    @Override
    public void configure() {

        String checkQuantityInterval = cronWithSecondsInterval(Global.CHECK_QUANTITY_INTERVAL_SECONDS);
        from("quartz://myGroup/TimerCHECK?cron=" + checkQuantityInterval)
                .to("direct:timeToCheck");



        CheckQuantityProcessor checkQuantityProcessor = new CheckQuantityProcessor(emailRepository, sweaterWarehouse);


        from("direct:timeToCheck").process(checkQuantityProcessor)
                .choice()
                .when(simple("${body[email]} != ''"))
                .delay(5).doTry().setHeader("subject", simple("${body[title]}"))
                .setHeader("to", simple("${body[email]}"))
                .setBody(simple("${body[message]}"))
                .to("smtps://smtp.gmail.com:465?username=yourdailyinformation@gmail.com&password=!A1B2C3D4!abcd");


    }


}
