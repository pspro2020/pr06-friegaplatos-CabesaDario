package app;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Organizador implements Runnable{
    private final DryTray dryTray;



    public Organizador(DryTray dryTray) {
        this.dryTray = dryTray;
    }

    @Override
    public void run() {
        Integer plate;

        while (!Thread.currentThread().isInterrupted()) {
            try {
                plate = dryTray.extractFromTray();
            } catch (InterruptedException e) {
                System.out.println("El Organizador ha sío interrumpío mientras sacaba un plato de la bandeja de platos secos");
                return;
            }
            try {
                organize(plate);
            } catch (InterruptedException e) {
                System.out.println("El Organizador ha sío interrumpío mientras colocaba un plato en la alacena");
                return;
            }


        }


        System.out.println("El Organizador ha sío interrumpío");

    }

    private void organize(Integer plate) throws InterruptedException{

        System.out.printf("El Organizador está ordenando el plato #%d\n", plate);

        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1,3));



    }


}
