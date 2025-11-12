package org.example.appCotacao;

public class Main {
    public static void main(String[] args) {
        ConversorDeMoedaService conversorDeMoedaService = new ConversorDeMoedaService(new CambioClientReal());
        double v = conversorDeMoedaService.converterParaReais("EUR", 10.43);
        System.out.println("Resultado: " + v);
    }
}
