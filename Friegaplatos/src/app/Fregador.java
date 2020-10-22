package app;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Fregador implements Runnable{
    private final CleanTray cleanTray;
    private int plateNumber;

    public Fregador(CleanTray cleanTray) {
        this.cleanTray = cleanTray;
    }

    @Override
    public void run() {
        Integer plate;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                plate = fregarPlato();
            } catch (InterruptedException e) {
                System.out.println("El fregador ha sío interrumpío mientras fregaba un plato");
                return;
            }
            try {
                cleanTray.addToTray(plate);
            } catch (InterruptedException e) {
                System.out.println("El Fregador ha sío interrumpío mientras añadía un plato a la bandeja de losplatos limpios");
                return;
            }
        }
        System.out.println("El Fregador ha sío interrumpío");
    }

    private int fregarPlato() throws InterruptedException{
        Integer plate = ++plateNumber;
        System.out.printf("El Fregador está fregando el plato #%d\n", plate);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(4,9));
        return plate;
    }
}
