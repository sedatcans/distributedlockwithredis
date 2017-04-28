package com.sedatcan;

/**
 * Created by sedatcans on 28.04.2017.
 */
public class Application {


    public static void main(String[] args) throws Exception {
        OnceConsoleWriter onceConsoleWriter = new OnceConsoleWriter("ApplicationLock");
        if (onceConsoleWriter.getWriterLock().tryLock()) {
            System.out.println("We are Started");
        }
    }

}
