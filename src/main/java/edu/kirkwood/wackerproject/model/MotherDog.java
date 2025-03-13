package edu.kirkwood.wackerproject.model;


public class MotherDog {
    private String motherDogID;
    private String breedID;
    private String personality;
    private String energyLevel;
    private String barkingLevel;
    private String trainability;
    private String image;
    private String description;

    public MotherDog() {
    }

    public MotherDog(String motherDogID, String breedID, String personality, String energyLevel, String barkingLevel, String trainability, String image, String description) {
        this.motherDogID = motherDogID;
        this.breedID = breedID;
        this.personality = personality;
        this.energyLevel = energyLevel;
        this.barkingLevel = barkingLevel;
        this.trainability = trainability;
        this.image = image;
        this.description = description;
    }

    public String getMotherDogID() {
        return motherDogID;
    }

    public void setMotherDogID(String motherDogID) {
        this.motherDogID = motherDogID;
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
        return "MotherDog{" +
                "motherDogID='" + motherDogID + '\'' +
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
