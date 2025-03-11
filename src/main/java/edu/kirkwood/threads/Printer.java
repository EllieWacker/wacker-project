package edu.kirkwood.threads;

import org.jetbrains.annotations.NotNull;

public class Printer extends Thread {
    public Printer(@NotNull String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.printf("The '%s' Printer started!\n", this.getName());


        System.out.printf("The '%s' Printer stopped!\n", this.getName());
    }
}
