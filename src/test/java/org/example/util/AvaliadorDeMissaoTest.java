package org.example.util;

import org.example.model.Missao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvaliadorDeMissaoTest {
    AvaliadorDeMissao avaliadorDeMissao;
    @BeforeEach
    void setUp(){
        avaliadorDeMissao = new AvaliadorDeMissao();
    }
    @Test
    @DisplayName("Deve calcular pontuacao para missao bem sucedida.")
    public void pontuacaoBemSucedida() {
        Missao missao = new Missao("NASA", "Buzz Aldgring", true, false, false, "LUA", false);
        int pontuacao = avaliadorDeMissao.calcularPontuacao(missao);
        assertEquals(150, pontuacao);
    }
    @Test
    @DisplayName("Deve calcular pontuacao para missao bem sucedida e secreta.")
    public void pontuacaoBemSucedidaESecreta() {
        Missao missao = new Missao("NASA", "Buzz Aldgring", true, false, false, "LUA", true);
        int pontuacao = avaliadorDeMissao.calcularPontuacao(missao);
        assertEquals(250, pontuacao);
    }
    @Test
    @DisplayName("Deve calcular pontuacao para missao com falha critica")
    public void temFalhaCritica() {
        Missao missao = new Missao("NASA", "Buzz Aldgring", false, true, false, "LUA", false);
        int pontuacao = avaliadorDeMissao.calcularPontuacao(missao);
        assertEquals(70, pontuacao);
    }
    @Test
    @DisplayName("Deve calcular pontuacao com astronauta veterano")
    public void astronautaVeterano() {
        Missao missao = new Missao("NASA", "Buzz Aldgring", true, false, true, "LUA", false);
        int pontuacao = avaliadorDeMissao.calcularPontuacao(missao);
        assertEquals(170, pontuacao);
    }
    @Test
    @DisplayName("Deve calcular pontuacao para astronauta que foi para Marte")
    public void pontuacaoParaMarte() {
        Missao missao = new Missao("NASA", "Buzz Aldgring", false, false, false, "MARTE", false);
        int pontuacao = avaliadorDeMissao.calcularPontuacao(missao);
        assertEquals(110, pontuacao);
    }
    @Test
    @DisplayName("Deve calcular pontuacao para astronauta que foi para europa")
    public void pontuacaoParaEuropa() {
        Missao missao = new Missao("NASA", "Buzz Aldgring", false, false, false, "EUROPA", false);
        int pontuacao = avaliadorDeMissao.calcularPontuacao(missao);
        assertEquals(95, pontuacao);
    }
    @Test
    @DisplayName("Deve calcular pontuacao para astronauta que foi para Marte")
    public void pontuacaoParaEuropaMinusculo() {
        Missao missao = new Missao("NASA", "Buzz Aldgring", false, false, false, "eURoPa", false);
        int pontuacao = avaliadorDeMissao.calcularPontuacao(missao);
        assertEquals(95, pontuacao);
    }

}
