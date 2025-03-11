package edu.kirkwood.threads;

public class ClockController {
    public static void main(String[] args) throws InterruptedException {
        Clock clock = new Clock();
        clock.start();
        Thread.sleep(5000);
        clock.interrupt();
    }
}
