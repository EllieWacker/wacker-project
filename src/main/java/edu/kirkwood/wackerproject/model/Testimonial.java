package edu.kirkwood.wackerproject.model;

public class Testimonial  {
    private String testimonialId;
    private int adoptionId;
    private String image;
    private String details;
    private int rating;


    public Testimonial(String testimonialId, int adoptionId, String image, String details, int rating) {
        this.testimonialId = testimonialId;
        this.adoptionId = adoptionId;
        this.image = image;
        this.details = details;
        this.rating = rating;
    }

    public Testimonial() {
    }

    public String getTestimonialId() {
        return testimonialId;
    }

    public void setTestimonialId(String testimonialId) {
        this.testimonialId = testimonialId;
    }

    public int getAdoptionId() {
        return adoptionId;
    }

    public void setAdoptionId(int adoptionId) {
        this.adoptionId = adoptionId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Testimonial{" +
                "testimonialId='" + testimonialId + '\'' +
                ", adoptionId=" + adoptionId +
                ", image='" + image + '\'' +
                ", details='" + details + '\'' +
                ", rating=" + rating +
                '}';
    }
}