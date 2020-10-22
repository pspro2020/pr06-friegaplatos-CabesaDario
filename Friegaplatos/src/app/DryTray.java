package app;

import java.util.ArrayList;

public class DryTray {
    private final ArrayList<Integer> tray = new ArrayList<>();

    public void addToTray(Integer plate) throws InterruptedException {
        synchronized (this) {

            tray.add(plate);

            System.out.printf("Secador ha añadido el plato #%d a la bandeja de los platos secos\n", plate);

            notifyAll();

        }
    }

    public Integer extractFromTray() throws InterruptedException {
        Integer plate;
        synchronized (this) {
            while (tray.isEmpty()) {
                System.out.println("El Organizador está esperando a que haya un plato en la bandeja de los platos secos");
                wait();
            }
            plate = tray.remove(0);
            System.out.printf("El Organizador ha sacado el plato #%d de la bandeja de platos secos\n", plate);
            notifyAll();
            return plate;
        }
    }

}
