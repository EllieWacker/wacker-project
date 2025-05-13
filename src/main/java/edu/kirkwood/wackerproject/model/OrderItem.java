package edu.kirkwood.wackerproject.model;

public class OrderItem {
    private String puppyID;
    private String puppyName;
    private double price;
    private double totalPrice;

    public OrderItem(String puppyID, String puppyName, double price, double totalPrice) {
        this.puppyID = puppyID;
        this.puppyName = puppyName;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public OrderItem() {
    }

    public String getPuppyID() {
        return puppyID;
    }

    public void setPuppyID(String puppyID) {
        this.puppyID = puppyID;
    }

    public String getPuppyName() {
        return puppyName;
    }

    public void setPuppyName(String puppyName) {
        this.puppyName = puppyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "puppyID='" + puppyID + '\'' +
                ", puppyName='" + puppyName + '\'' +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

}