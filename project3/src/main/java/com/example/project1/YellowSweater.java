package com.example.project1;

import javax.persistence.Entity;

@Entity
public class YellowSweater extends Sweater {

    public YellowSweater() {
        super(189);
    }

    public YellowSweater(Long sweaterId) {
        super(sweaterId, 189);
    }

    @Override
    public void produce(SweaterWarehouse warehouse) {
        YellowSweater newSweater = new YellowSweater();
        warehouse.save(newSweater);
    }
}
