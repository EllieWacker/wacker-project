package edu.kirkwood.wackerproject.model;


import java.util.Objects;

public class Puppy {
    private String puppyID;
    private String breedID;
    private String litterID	;
    private String medicalRecordID;
    private String image;
    private String gender;
    private boolean adopted	;
    private boolean microchip	;
    private double price;
    private String breedDescription;

    public Puppy() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Puppy puppy = (Puppy) o;
        return Double.compare(price, puppy.price) == 0 && Objects.equals(puppyID, puppy.puppyID) && Objects.equals(breedID, puppy.breedID) && Objects.equals(litterID, puppy.litterID) && Objects.equals(gender, puppy.gender) && Objects.equals(image, puppy.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(puppyID, breedID, litterID, gender, image, price);
    }

    public Puppy(String puppyID, String breedID, String litterID, String gender, String image, double price) {
        this.puppyID = puppyID;
        this.breedID = breedID;
        this.litterID = litterID;
        this.gender = gender;
        this.image = image;
        this.price = price;
    }

    public Puppy(String puppyID, String breedID, String litterID, String medicalRecordID, String image, String gender, boolean adopted, boolean microchip, double price, String breedDescription) {
        this.puppyID = puppyID;
        this.breedID = breedID;
        this.litterID = litterID;
        this.medicalRecordID = medicalRecordID;
        this.image = image;
        this.gender = gender;
        this.adopted = adopted;
        this.microchip = microchip;
        this.price = price;
        this.breedDescription = breedDescription;
    }

    public String getPuppyID() {
        return puppyID;
    }

    public void setPuppyID(String puppyID) {
        this.puppyID = puppyID;
    }

    public String getBreedID() {
        return breedID;
    }

    public void setBreedID(String breedID) {
        this.breedID = breedID;
    }

    public String getLitterID() {
        return litterID;
    }

    public void setLitterID(String litterID) {
        this.litterID = litterID;
    }

    public String getMedicalRecordID() {
        return medicalRecordID;
    }

    public void setMedicalRecordID(String medicalRecordID) {
        this.medicalRecordID = medicalRecordID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public boolean getMicrochip() {
        return microchip;
    }

    public void setMicrochip(boolean microchip) {
        this.microchip = microchip;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getBreedDescription() {
        return breedDescription;
    }

    public void setBreedDescription(String breedDescription) {
        this.breedDescription = breedDescription;
    }

    @Override
    public String toString() {
        return "Puppy{" +
                "puppyID='" + puppyID + '\'' +
                ", breedID='" + breedID + '\'' +
                ", litterID='" + litterID + '\'' +
                ", medicalRecordID='" + medicalRecordID + '\'' +
                ", image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", adopted='" + adopted + '\'' +
                ", microchip=" + microchip +
                ", price=" + price +
                ", breedDescription='" + breedDescription + '\'' +
                '}';
    }
}
