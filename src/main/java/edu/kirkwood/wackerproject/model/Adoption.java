package edu.kirkwood.wackerproject.model;

public class Adoption {
    private int adoptionID;
    private int applicationID;
    private String puppyID;
    private int userID;
    private String status;

    @Override
    public String toString() {
        return "Adoption{" +
                "adoptionID=" + adoptionID +
                ", applicationID=" + applicationID +
                ", puppyID='" + puppyID + '\'' +
                ", userID=" + userID +
                ", status='" + status + '\'' +
                '}';
    }

    public Adoption(int adoptionID, int applicationID, String puppyID, int userID, String status) {
        this.adoptionID = adoptionID;
        this.applicationID = applicationID;
        this.puppyID = puppyID;
        this.userID = userID;
        this.status = status;
    }

    public int getAdoptionID() {
        return adoptionID;
    }

    public void setAdoptionID(int adoptionID) {
        this.adoptionID = adoptionID;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public String getPuppyID() {
        return puppyID;
    }

    public void setPuppyID(String puppyID) {
        this.puppyID = puppyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
