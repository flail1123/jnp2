package com.example.project1.SweaterFactory;

import com.example.project1.SweaterWarehouse;

import javax.persistence.Entity;

@Entity
public class BlueSweater extends Sweater {

    public BlueSweater() {
        super(289);
    }

    public BlueSweater(int sweaterId) {
        super(sweaterId, 289);
    }

    @Override
    public void produce(SweaterWarehouse warehouse) {
        BlueSweater newSweater = new BlueSweater();
        warehouse.save(newSweater);
    }
}
