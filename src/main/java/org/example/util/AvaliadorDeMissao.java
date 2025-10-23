package org.example.util;

import org.example.model.Missao;
import org.example.util.regras.*;

import java.util.List;

public class AvaliadorDeMissao {
    private final List<RegraPontuacao> regras = List.of(
            new Sucesso(),
            new FalhaCritica(),
            new Veterano(),
            new Destino(),
            new MissaoSecreta()
    );

    public int calcularPontuacao(Missao missao) {
        return 100 + regras.stream().mapToInt(regra -> regra.aplicar(missao)).sum();
    }
}
