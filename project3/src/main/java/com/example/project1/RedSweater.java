package com.example.project1;

import javax.persistence.Entity;

@Entity
public class RedSweater extends Sweater {

    public RedSweater() {
        super(159);
    }

    public RedSweater(Long sweaterId) {
        super(sweaterId, 159);
    }

    @Override
    public void produce(SweaterWarehouse warehouse) {
        RedSweater newSweater = new RedSweater();
        warehouse.save(newSweater);
    }
}
