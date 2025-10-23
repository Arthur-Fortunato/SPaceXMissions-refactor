package org.example;

import org.example.model.Missao;
import org.example.util.AvaliadorDeMissao;

public class App
{
    public static void main( String[] args )
    {
        Missao missao = new Missao("NASA", "Buzz Aldgring", true, false, true, "LUA", false);
        AvaliadorDeMissao avaliadorDeMissao = new AvaliadorDeMissao();
        int pontuacao = avaliadorDeMissao.calcularPontuacao(missao);
        System.out.println("Pontuação: " + pontuacao);
    }
}
