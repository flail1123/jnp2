package com.example.project1.Factory;
import com.example.project1.RedSweater;
import com.example.project1.Sweater;
import com.example.project1.SweaterWarehouse;
import com.example.project1.UserEmail;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RedProduction implements Processor {
    RedSweater sweater;
    SweaterWarehouse warehouse;

    public RedProduction(RedSweater s, SweaterWarehouse warehouse) {
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