package edu.kirkwood.wackerproject.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Puppy, Integer> contents;

    public ShoppingCart(){
        contents = new HashMap<>();
    }

    public void addPuppy(Puppy puppy){
        // If the cart does not contain the puppy
        if(puppy == null){
            throw new NullPointerException("Puppy cannot be null");
        }
        if(!contents.containsKey(puppy)){
            contents.put(puppy, 1);
        }
    }

    public void deletePuppy(Puppy puppy) {
        if(puppy == null) {
            throw new IllegalArgumentException("Puppy cannot be null");
        }
        if(contents.containsKey(puppy)) {
            contents.remove(puppy);
        }
    }

    public Map<Puppy, Integer> getContents(){
        return contents;
    }

    public int getTotalPuppyCount(){
        int total = 0;
        for(int quantity : contents.values()){
            total += quantity;
        }
        return total;
    }

    public double getTotalPrice(){
        double total = 0;
        for(Map.Entry<Puppy, Integer> entry : contents.entrySet()){
            Puppy puppy = entry.getKey();
            double price = puppy.getPrice();
            total += price;
        }
        return total;
    }



    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Puppy puppy1 = PuppyDAO.getPuppyByID("ALit1One");
        Puppy puppy2 = PuppyDAO.getPuppyByID("ALit2One");
        Puppy puppy3 = PuppyDAO.getPuppyByID("ALit2One");
        cart.addPuppy(puppy1);
        cart.addPuppy(puppy2);
        cart.addPuppy(puppy3);
        cart.getContents().entrySet().forEach(item ->{
            System.out.print(item.getKey().getPuppyID());
            System.out.println(" = " + item.getKey().getPrice());
        });
        System.out.println("There are " + cart.getTotalPuppyCount() + " puppies in your cart");

        System.out.println("Your total is " + cart.getTotalPrice());
    }
}
