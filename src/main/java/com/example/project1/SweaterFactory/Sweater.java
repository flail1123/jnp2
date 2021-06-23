package com.example.project1.SweaterFactory;

import com.example.project1.SweaterWarehouse;

import javax.persistence.*;

@Entity
public abstract class Sweater {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sweaterID;
    private Integer price;

    public Sweater(Integer price) {
        this.price = price;
    }

    public Sweater(int sweaterId) {this.sweaterID = sweaterId;}

    public Sweater(int sweaterID, Integer price) {
        this.sweaterID = sweaterID;
        this.price = price;
    }

    public int getSweaterID() {
        return sweaterID;
    }

    public void setSweaterID(int sweaterID) {
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
