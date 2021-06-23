package com.example.project1.SweaterFactory;

import com.example.project1.SweaterWarehouse;

import javax.persistence.Entity;

@Entity
public class YellowSweater extends Sweater {

    public YellowSweater() {
        super(189);
    }

    public YellowSweater(int sweaterId) {
        super(sweaterId, 189);
    }

    @Override
    public void produce(SweaterWarehouse warehouse) {
        YellowSweater newSweater = new YellowSweater();
        warehouse.save(newSweater);
    }
}
