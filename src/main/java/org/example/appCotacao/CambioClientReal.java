package org.example.appCotacao;

import java.util.concurrent.ThreadLocalRandom;

public class CambioClientReal implements CambioClient {
    @Override
    public double buscarCotacao(String moeda){
        System.out.println("Chamando API externa para: " + moeda);
        int delay = ThreadLocalRandom.current().nextInt(1000, 5000);
        try {
            System.out.println("Buscando cotação com delay de: " + delay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return switch (moeda){
            case "USD" -> ThreadLocalRandom.current().nextDouble(3.0, 5.0);
            case "EUR" -> ThreadLocalRandom.current().nextDouble(4.0, 7.0);
            default -> 1.0;
        };
    }
}
