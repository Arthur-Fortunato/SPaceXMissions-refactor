package org.example.util.regras;

import org.example.model.Missao;

public class Veterano implements RegraPontuacao {
    @Override
    public int aplicar(Missao missao) {
        return missao.isVeterano() ? 20 : 0;
    }
}
