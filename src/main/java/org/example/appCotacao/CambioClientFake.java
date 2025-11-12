package org.example.appCotacao;

public class CambioClientFake implements CambioClient {
    @Override
    public double buscarCotacao(String moeda){
        System.out.println("Chamando API FAKE externa para: " + moeda);

        return switch (moeda){
            case "USD" -> 5.0;
            case "EUR" -> 6.0;
            default -> 1.0;
        };
    }
}
