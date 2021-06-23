package com.example.project1;

import java.util.Arrays;

public class Context {
    int[] available_quantity = new int[3];
    int users_choice;
    String users_email;

    public Context(int a, int b, int c) {
        this.available_quantity[0] = a;
        this.available_quantity[1] = b;
        this.available_quantity[2] = c;
        this.users_choice = -1;
        this.users_email = new String("");
    }

    public Context(int a, int b, int c, String s) {
        this.available_quantity[0] = a;
        this.available_quantity[1] = b;
        this.available_quantity[2] = c;
        this.users_choice = -1;
        this.users_email = s;
    }

    public Context(int c) {
        this.users_choice = c;
        this.users_email = new String("");
    }

    public Context(int c, String email) {
        this.users_choice = c;
        this.users_email = email;
    }

    public int getUsers_choice() {
        return users_choice;
    }

    public void setUsers_choice(int users_choice) {
        this.users_choice = users_choice;
    }

    public String getUsers_email() {
        return users_email;
    }

    public void setUsers_email(String users_email) {
        this.users_email = users_email;
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
