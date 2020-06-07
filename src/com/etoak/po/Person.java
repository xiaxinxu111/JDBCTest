package com.etoak.po;

import java.util.Date;

public class Person {
    private Integer id;
    private String name;
    private String pass;
    private String gender;
    private Integer age;
    private String hobby;
    private Double salary;
    private String email;
    private Date birth;
    private String level;
    private String status;

    public Person() {
    }


    public Person(Integer id, String name, String pass, String gender, Integer age, String hobby, Double salary, String email, Date birth, String level, String status) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.gender = gender;
        this.age = age;
        this.hobby = hobby;
        this.salary = salary;
        this.email = email;
        this.birth = birth;
        this.level = level;
        this.status = status;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", level='" + level + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

