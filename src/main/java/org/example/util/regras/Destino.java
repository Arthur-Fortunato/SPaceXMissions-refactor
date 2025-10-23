package org.example.util.regras;

import org.example.model.Missao;

public class Destino implements RegraPontuacao {
    @Override
    public int aplicar(Missao missao) {
        return switch(missao.getDestino().toLowerCase()){
            case "marte" -> 10;
            case "europa" -> -5;
            default -> 0;
        };
    }
}
