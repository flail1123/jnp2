package com.example.project1;

import javax.persistence.*;

@Entity
public abstract class Sweater {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sweaterID;
    private Integer price;

    public Sweater(Integer price) {
        this.price = price;
    }

    public Sweater(Long sweaterId) {this.sweaterID = sweaterId;}

    public Sweater(Long sweaterID, Integer price) {
        this.sweaterID = sweaterID;
        this.price = price;
    }

    public Long getSweaterID() {
        return sweaterID;
    }

    public void setSweaterID(Long sweaterID) {
        this.sweaterID = sweaterID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                .replace("Sweater", "") + "{" +
                "ID=" + sweaterID +
                ", price=" + price.toString() +
                '}';
    }

    abstract public void produce(SweaterWarehouse warehouse);
}
