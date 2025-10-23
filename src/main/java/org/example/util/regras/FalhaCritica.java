package org.example.util.regras;

import org.example.model.Missao;

public class FalhaCritica implements RegraPontuacao {
    @Override
    public int aplicar(Missao missao){
        return missao.isTeveFalhaCritica() ? -30 : 0;
    }
}
