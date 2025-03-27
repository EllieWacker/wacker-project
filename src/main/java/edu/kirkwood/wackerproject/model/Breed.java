package edu.kirkwood.wackerproject.model;

public class Breed {
    private String breedID;
    private int numPuppies;

    public Breed(String breedID, int numPuppies) {
        this.breedID = breedID;
        this.numPuppies = numPuppies;
    }

    public String getBreedID() {
        return breedID;
    }

    public void setBreedID(String breedID) {
        this.breedID = breedID;
    }

    public int getNumPuppies() {
        return numPuppies;
    }

    public void setNumPuppies(int numPuppies) {
        this.numPuppies = numPuppies;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "breedID=" + breedID +
                ", numPuppies=" + numPuppies +
                '}';
    }
}
