package org.example.util.regras;

import org.example.model.Missao;

public class Sucesso implements RegraPontuacao {
    @Override
    public int aplicar(Missao missao) {
        return missao.isTeveSucesso() ? 50 : 0;
    }
}
