package edu.kirkwood.threads;

public class PrinterController {
    public static void main(String[] args) {
        Printer printer1 = new Printer("HP");
        printer1.start();

        Printer printer2 = new Printer("Canon");
        printer2.start();

        Printer printer3 = new Printer("Epson");
        printer3.start();

        Printer printer4 = new Printer("Brother");
        printer4.start();
    }
}
