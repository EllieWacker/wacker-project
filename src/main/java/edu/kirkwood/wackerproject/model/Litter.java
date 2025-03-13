package edu.kirkwood.wackerproject.model;

import java.sql.Date;

public class Litter {
    private String litterID;
    private String fatherDogID;
    private String motherDogID;
    private String image;
    private Date dateOfBirth;
    private Date goHomeDate;
    private int numberPuppies;

    public Litter() {
    }

    public Litter(String litterID, String fatherDogID, String motherDogID, String image, Date dateOfBirth, Date goHomeDate, int numberPuppies) {
        this.litterID = litterID;
        this.fatherDogID = fatherDogID;
        this.motherDogID = motherDogID;
        this.image = image;
        this.dateOfBirth = dateOfBirth;
        this.goHomeDate = goHomeDate;
        this.numberPuppies = numberPuppies;
    }

    public String getLitterID() {
        return litterID;
    }

    public void setLitterID(String litterID) {
        this.litterID = litterID;
    }

    public String getFatherDogID() {
        return fatherDogID;
    }

    public void setFatherDogID(String fatherDogID) {
        this.fatherDogID = fatherDogID;
    }

    public String getMotherDogID() {
        return motherDogID;
    }

    public void setMotherDogID(String motherDogID) {
        this.motherDogID = motherDogID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getGoHomeDate() {
        return goHomeDate;
    }

    public void setGoHomeDate(Date goHomeDate) {
        this.goHomeDate = goHomeDate;
    }

    public int getNumberPuppies() {
        return numberPuppies;
    }

    public void setNumberPuppies(int numberPuppies) {
        this.numberPuppies = numberPuppies;
    }

    @Override
    public String toString() {
        return "Litter{" +
                "litterID='" + litterID + '\'' +
                ", fatherDogID='" + fatherDogID + '\'' +
                ", motherDogID='" + motherDogID + '\'' +
                ", image='" + image + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", goHomeDate='" + goHomeDate + '\'' +
                ", numberPuppies=" + numberPuppies +
                '}';
    }
}
