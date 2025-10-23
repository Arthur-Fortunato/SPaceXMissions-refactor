package org.example.util.regras;

import org.example.model.Missao;

public class MissaoSecreta implements RegraPontuacao{
    @Override
    public int aplicar(Missao missao) {
        return missao.isSecreta() ? 100 : 0;
    }
}
