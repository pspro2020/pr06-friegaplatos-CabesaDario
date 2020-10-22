package app;

import java.util.ArrayList;

public class CleanTray {
    private final ArrayList<Integer> tray = new ArrayList<>();

    public void addToTray(Integer plate) throws InterruptedException {
        synchronized (this) {

            tray.add(plate);

            System.out.printf("Fregador ha añadido el plato #%d a la bandeja de los platos limpios\n", plate);

            notifyAll();

        }
    }

    public Integer extractFromTray() throws InterruptedException {
        Integer plate;
        synchronized (this) {
            while (tray.isEmpty()) {
                System.out.println("El Secador está esperando a que haya un plato en la bandeja de los platos limpios");
                wait();
            }
            plate = tray.remove(0);
            System.out.printf("El Secador ha sacado el plato #%d de la bandeja de platos limpios\n", plate);
            notifyAll();
            return plate;
        }
    }
}
