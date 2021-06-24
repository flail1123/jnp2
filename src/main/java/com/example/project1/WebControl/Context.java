package com.example.project1.WebControl;

import com.example.project1.Global;

import java.util.Arrays;

public class Context {
    int[] available_quantity = new int[3];
    int users_choice;
    String users_email;

    // default email value
    public final String empty = new String("");
    // Different ids below are for reliable distinction between requested procedure.
    // id sent when demanding notifying
    public final int id1 = 0;
    public final int id2 = 1;
    public final int id3 = 2;
    // id sent when buying object
    public final int idb1 = Global.buyId1;
    public final int idb2 = Global.buyId2;
    public final int idb3 = Global.buyId3;

    public Context(int a, int b, int c) {
        this.available_quantity[0] = a;
        this.available_quantity[1] = b;
        this.available_quantity[2] = c;
        this.users_choice = -1;
        this.users_email = new String("");
    }


    public Context(int c) {
        this.users_choice = c;
        this.users_email = new String("");
    }

    public Context(int c, String email) {
        this.users_choice = c;
        this.users_email = email;
    }

    public int[] getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int[] available_quantity) {
        this.available_quantity = available_quantity;
    }

    @Override
    public String toString() {
        return "Request{" +
                "available_quantity=" + Arrays.toString(available_quantity) +
                ", users_choice=" + users_choice +
                ", users_email='" + users_email + '\'' +
                '}';
    }
}
