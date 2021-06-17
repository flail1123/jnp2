package com.example.project1.Factory;

import com.example.project1.BlueSweater;
import com.example.project1.SweaterWarehouse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BlueProduction implements Processor {
    BlueSweater sweater;
    SweaterWarehouse warehouse;

    public BlueProduction(BlueSweater s, SweaterWarehouse warehouse) {
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
