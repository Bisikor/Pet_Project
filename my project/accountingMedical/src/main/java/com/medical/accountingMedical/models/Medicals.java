package com.medical.accountingMedical.models;

import javax.persistence.*;

@Entity
public class Medicals {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Medicals() {
    }

    public Medicals(String nameOfMedical, String expirationDate, String numbers, String about) {
        this.nameOfMedical = nameOfMedical;
        this.expirationDate = expirationDate;
        this.numbers = numbers;
        this.about = about;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String nameOfMedical, expirationDate,numbers,about;

    public String getNameOfMedical() {
        return nameOfMedical;
    }

    public void setNameOfMedical(String nameOfMedical) {
        this.nameOfMedical = nameOfMedical;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
