package org.example.appCotacao;

public class ConversorDeMoedaService {
    private final CambioClient cambioClient;

    public  ConversorDeMoedaService(CambioClient cambioClient) {
        this.cambioClient = cambioClient;
    }

    private final double IMPOSTO = 0.3;
    public double converterParaReais(String moeda, double valor){
        double cotacao = cambioClient.buscarCotacao(moeda);
        return ((valor * cotacao) * IMPOSTO);
    }

}
