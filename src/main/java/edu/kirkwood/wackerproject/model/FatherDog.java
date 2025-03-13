package edu.kirkwood.wackerproject.model;

import java.sql.Date;

public class FatherDog {
    private String fatherDogID;
    private String breedID;
    private String personality;
    private String energyLevel;
    private String barkingLevel;
    private String trainability;
    private String image;
    private String description;

    public FatherDog() {
    }

    public FatherDog(String fatherDogID, String breedID, String personality, String energyLevel, String barkingLevel, String trainability, String image, String description) {
        this.fatherDogID = fatherDogID;
        this.breedID = breedID;
        this.personality = personality;
        this.energyLevel = energyLevel;
        this.barkingLevel = barkingLevel;
        this.trainability = trainability;
        this.image = image;
        this.description = description;
    }

    public String getFatherDogID() {
        return fatherDogID;
    }

    public void setFatherDogID(String fatherDogID) {
        this.fatherDogID = fatherDogID;
    }

    public String getBreedID() {
        return breedID;
    }

    public void setBreedID(String breedID) {
        this.breedID = breedID;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(String energyLevel) {
        this.energyLevel = energyLevel;
    }

    public String getBarkingLevel() {
        return barkingLevel;
    }

    public void setBarkingLevel(String barkingLevel) {
        this.barkingLevel = barkingLevel;
    }

    public String getTrainability() {
        return trainability;
    }

    public void setTrainability(String trainability) {
        this.trainability = trainability;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FatherDog{" +
                "fatherDogID='" + fatherDogID + '\'' +
                ", breedID='" + breedID + '\'' +
                ", personality='" + personality + '\'' +
                ", energyLevel='" + energyLevel + '\'' +
                ", barkingLevel='" + barkingLevel + '\'' +
                ", trainability='" + trainability + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
