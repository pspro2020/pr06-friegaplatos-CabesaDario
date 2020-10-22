package app;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Secador implements Runnable{
    private final CleanTray cleanTray;
    private final DryTray dryTray;


    public Secador(CleanTray cleanTray, DryTray dryTray) {
        this.cleanTray = cleanTray;
        this.dryTray = dryTray;
    }

    @Override
    public void run() {
        Integer plate;

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    plate = cleanTray.extractFromTray();
                } catch (InterruptedException e) {
                    System.out.println("El secador ha sío interrumpío mientras sacaba un plato de la bandeja de platos limpios");
                    return;
                }
                try {
                    dry(plate);
                } catch (InterruptedException e) {
                    System.out.println("El Secador ha sío interrumpío mientras secaba");
                    return;
                }

                try {
                    dryTray.addToTray(plate);
                } catch (InterruptedException e) {
                    System.out.println("El Secador ha sido interrumpido mientras colocaba un plato en la bandeja de los platos secos");
                    return;
                }
            }


        System.out.println("El Fregador ha sío interrumpío");
    }

    private void dry(Integer plate) throws InterruptedException{
        System.out.printf("El Secador está secando el plato #%d\n", plate);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1,4));


    }
}
