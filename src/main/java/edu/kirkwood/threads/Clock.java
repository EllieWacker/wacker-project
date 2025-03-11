package edu.kirkwood.threads;

public class Clock extends Thread  {
    public void run() {
        while (true) {
            System.out.println("Tick"); // writes the word Tick to the console once a second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("BOOM!");
    }
}
