package org.example.reembolso;

public class CalculadoraDeReembolso {
    public double calcular(Viagem viagem){
        switch (viagem.getTransporte()){
            case AVIAO:
                if (viagem.getDistanciaEmKm() < 500){
                    return 0.0;
                }
                return viagem.getCustoTotal();

            case TREM:
                return viagem.getCustoTotal() * 0.5;

            case CARRO_PARTICULAR:
                return viagem.getDistanciaEmKm() * 0.75;

            case CARRO_EMPRESA:
                return 0.0;

//            case BICICLETA:
//                if (viagem.getDistanciaEmKm() > 50) return 0.0;
//                return viagem.getDistanciaEmKm() * 0.15;
//
//            case ONIBUS:
//                if (viagem.getDistanciaEmKm() < 10 || viagem.getDistanciaEmKm() > 2000) return 0.0;
//                return viagem.getCustoTotal();
            default:
                throw new IllegalArgumentException("Transporte inválido.");
        }
    }
}
