package edu.kirkwood.wackerproject.model;


public class Puppy {
    private String puppyID;
    private String breedID;
    private String litterID	;
    private String medicalRecordID;
    private String image;
    private String gender;
    private String adopted	;
    private boolean microchip	;
    private double price;
    private String breedDescription;

    public Puppy() {
    }

    public Puppy(String puppyID, String breedID, String litterID, String medicalRecordID, String image, String gender, String adopted, boolean microchip, double price, String breedDescription) {
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

    public String getAdopted() {
        return adopted;
    }

    public void setAdopted(String adopted) {
        this.adopted = adopted;
    }

    public boolean isMicrochip() {
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
