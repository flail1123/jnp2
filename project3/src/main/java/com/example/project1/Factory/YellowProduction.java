package com.example.project1.Factory;

import com.example.project1.SweaterWarehouse;
import com.example.project1.YellowSweater;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class YellowProduction implements Processor {
    YellowSweater sweater;
    SweaterWarehouse warehouse;

    public YellowProduction(YellowSweater s, SweaterWarehouse warehouse) {
        this.sweater = s;
        this.warehouse = warehouse;
    }

    public void process(Exchange exchange) {
        String in = exchange.getIn().getBody(String.class);
        int howMany = Integer.parseInt(in);

        for (int i = 0; i < howMany; i++)
            sweater.produce(warehouse);

        exchange.getIn().setBody("");
    }
}
