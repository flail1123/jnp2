package com.example.project1.SweaterFactory;

import com.example.project1.*;
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
    public void configure() {

        String redInterval = cronWithSecondsInterval(Global.RED_INTERVAL_SECONDS);
        from("quartz://myGroup/TimerRED?cron=" + redInterval)
                .to("direct:produceRed");

        String blueInterval = cronWithSecondsInterval(Global.BLUE_INTERVAL_SECONDS);
        from("quartz://myGroup/TimerBLUE?cron=" + blueInterval)
                .to("direct:produceBlue");

        String yellowInterval = cronWithSecondsInterval(Global.YELLOW_INTERVAL_SECONDS);
        from("quartz://myGroup/TimerYELLOW?cron=" + yellowInterval)
                .to("direct:produceYellow");


        Processor redProduction = new RedProduction(new RedSweater(), warehouse);
        Processor blueProduction = new BlueProduction(new BlueSweater(), warehouse);
        Processor yellowProduction = new YellowProduction(new YellowSweater(), warehouse);


        from("direct:produceRed")
                .setBody (simple (Global.RED_QUANTITY))
                .process(redProduction);

        from("direct:produceBlue")
                .setBody (simple (Global.BLUE_QUANTITY))
                .process(blueProduction);

        from("direct:produceYellow")
                .setBody (simple (Global.YELLOW_QUANTITY))
                .process(yellowProduction);


//        from("direct:weather").to("file:C:/inputFolder?fileName=info.txt"); // &fileExist=Append


//        from("file:C:/inputFolder?noop=true").delay(500).doTry().setHeader("subject", simple("Your daily brief"))
//                .setHeader("to", simple("kmarkowski2000@outlook.com"))
//                .to("smtps://smtp.gmail.com:465?username=yourdailyinformation@gmail.com&password=a1b2c3d4!");
    }


}
