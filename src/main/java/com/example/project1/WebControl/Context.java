package com.example.project1.WebControl;

import com.example.project1.Global;

import java.time.LocalTime;

public class Context {
    int[] available_quantity = new int[3];
    public String[] available_to_string = new String[3];
    int users_choice;
    String users_email;
    public final String empty = "";

    public final int id0 = Global.id0;
    public final int id1 = Global.id1;
    public final int id2 = Global.id2;
    public final int id3 = Global.id3;

    public final int idb0 = Global.newsletterId;
    public final int idb1 = Global.buyId1;
    public final int idb2 = Global.buyId2;
    public final int idb3 = Global.buyId3;

    public String redPrice = Global.redPrice + " €";
    public String bluePrice = Global.bluePrice + " €";
    public String yellowPrice = Global.yellowPrice + " €";

    public Context(int a, int b, int c) {
        this.available_quantity[0] = a;
        this.available_quantity[1] = b;
        this.available_quantity[2] = c;
        this.available_to_string[0] = "Available: " + a;
        this.available_to_string[1] = "Available: " + b;
        this.available_to_string[2] = "Available: " + c;
        this.users_choice = -1;
        this.users_email = new String("");
        if (LocalTime.now().isAfter(LocalTime.parse("10:00:00")) && LocalTime.now().isBefore(LocalTime.parse("13:00:00"))){
            this.redPrice = ((int)(0.9 * Global.redPrice)) + " €";
            this.bluePrice = ((int)(0.9 * Global.bluePrice)) + " €";
            this.yellowPrice = ((int)(0.9 * Global.yellowPrice)) + " €";
        }
    }

    public int[] getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int[] available_quantity) {
        this.available_quantity = available_quantity;
    }

}
