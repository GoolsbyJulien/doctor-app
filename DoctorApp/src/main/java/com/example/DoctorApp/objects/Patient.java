package com.example.DoctorApp.objects;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String firstname;
    private String lastname;
    private Date birthday;
    private String sex;
    private String phone;
    private String email;
    @ManyToOne()
    @JoinColumn(name = "name")
    private Injury injury;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Injury getInjury() {
        return injury;
    }

    public void setInjury(Injury injury) {
        this.injury = injury;
    }
}