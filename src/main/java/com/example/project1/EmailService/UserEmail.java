package com.example.project1.EmailService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class UserEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sweaterId;
    private String email;
    private LocalDate lastNewsletter;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSweaterId() {
        return sweaterId;
    }

    public void setSweaterId(Integer sweaterId) {
        this.sweaterId = sweaterId;
    }

    public LocalDate getLastNewsletter() {
        return lastNewsletter;
    }

    public void setLastNewsletter(LocalDate lastNewsletter) {
        this.lastNewsletter = lastNewsletter;
    }


}
