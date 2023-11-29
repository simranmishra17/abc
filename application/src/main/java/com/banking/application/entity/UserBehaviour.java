package com.banking.application.entity;

import jakarta.persistence.*;

import java.util.Date;

@Table(name = "user_behaviour")
@Entity
public class UserBehaviour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column
    private int day;

    @Column
    private int month;
    @Column
    int year;

    @Column
    int count;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
