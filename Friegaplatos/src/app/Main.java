package app;

import java.util.concurrent.TimeUnit;

public class Main {




    public static void main(String[] args) throws InterruptedException {
        CleanTray cleanTray = new CleanTray();

        DryTray dryTray = new DryTray();

        ThreadGroup colegas = new ThreadGroup("los colegas");


        Thread fregadorHilo = new Thread(colegas, new Fregador(cleanTray));
        Thread secadorHilo = new Thread(colegas, new Secador(cleanTray, dryTray));
        Thread organizadorHilo = new Thread(colegas, new Organizador(dryTray));

        fregadorHilo.start();
        secadorHilo.start();
        organizadorHilo.start();


        TimeUnit.SECONDS.sleep(60);


        colegas.interrupt();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("Cumpleaños feliz");



    }


}
