package com.example.project1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sweaterId;
    private String email;

//    public UserEmail(String email, Integer sweaterId) {
//        this.email = email;
//        this.sweaterId = sweaterId;
//    }

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
}
