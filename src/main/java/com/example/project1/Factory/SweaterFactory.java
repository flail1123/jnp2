package com.example.project1.Factory;

import com.example.project1.BlueSweater;
import com.example.project1.RedSweater;
import com.example.project1.SweaterWarehouse;
import com.example.project1.YellowSweater;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SweaterFactory extends RouteBuilder {
    private final SweaterWarehouse warehouse;


    public SweaterFactory(SweaterWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    private String cronWithSecondsInterval(Integer interval) {
        return "0/" + interval.toString() + "+*+*+?+*+*";
    }

    @Override
    public void configure() throws Exception {
        final Integer RED_INTERVAL_SECONDS = 4;
        final Integer BLUE_INTERVAL_SECONDS = 4;
        final Integer YELLOW_INTERVAL_SECONDS = 4;
        final String RED_QUANTITY = "3";
        final String BLUE_QUANTITY = "3";
        final String YELLOW_QUANTITY = "3";

        String redInterval = cronWithSecondsInterval(RED_INTERVAL_SECONDS);
        from("quartz://myGroup/TimerRED?cron=" + redInterval)
                .to("direct:produceRed");

        String blueInterval = cronWithSecondsInterval(BLUE_INTERVAL_SECONDS);
        from("quartz://myGroup/TimerBLUE?cron=" + blueInterval)
                .to("direct:produceBlue");

        String yellowInterval = cronWithSecondsInterval(YELLOW_INTERVAL_SECONDS);
        from("quartz://myGroup/TimerYELLOW?cron=" + yellowInterval)
                .to("direct:produceYellow");


        Processor redProduction = new RedProduction(new RedSweater(), warehouse);
        Processor blueProduction = new BlueProduction(new BlueSweater(), warehouse);
        Processor yellowProduction = new YellowProduction(new YellowSweater(), warehouse);


        from("direct:produceRed")
                .setBody (simple (RED_QUANTITY))
                .process(redProduction);

        from("direct:produceBlue")
                .setBody (simple (BLUE_QUANTITY))
                .process(blueProduction);

        from("direct:produceYellow")
                .setBody (simple (YELLOW_QUANTITY))
                .process(yellowProduction);


//        from("direct:weather").to("file:C:/inputFolder?fileName=info.txt"); // &fileExist=Append


//        from("file:C:/inputFolder?noop=true").delay(500).doTry().setHeader("subject", simple("Your daily brief"))
//                .setHeader("to", simple("kmarkowski2000@outlook.com"))
//                .to("smtps://smtp.gmail.com:465?username=yourdailyinformation@gmail.com&password=a1b2c3d4!");
    }


}
