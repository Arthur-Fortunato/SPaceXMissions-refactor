package org.example.particoes_e_limites;

import org.example.particoes_limites.ValidadorDeIdades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidadorDeIdadeParticoesTest {
    private ValidadorDeIdades validador;
    /*
        A) Lança Exception -> -5
        B) Lança Exception -> 999
        C) True -> idade = 30
        D) False -> 80
        E) False -> 10
    */
    @BeforeEach
    public void setup(){
        validador = new ValidadorDeIdades();
    }
    @Test @DisplayName("-5 -> Idade Negativa deve lançar exception")
    void idadeNegativaDeveLancarException(){
        validador.podeCadastrar(5);
        assertThrows(IllegalArgumentException.class, () -> validador.podeCadastrar(-5));
    }

    @Test @DisplayName("10 -> Menor de idade n pode cadastrar")
    void menorNaoPodeCadastrar(){
        boolean resultado = validador.podeCadastrar(10);
        assertFalse(resultado);
    }

    @Test @DisplayName("30 -> Idade valida deve cadastrar")
    void podeCadastrar(){
        boolean resultado = validador.podeCadastrar(30);
        assertTrue(resultado);
    }

    @Test @DisplayName("80 -> idade superior mas ainda humana")
    void naoPodeCadastrar(){
        boolean resultado = validador.podeCadastrar(80);
        assertFalse(resultado);
    }

    @Test @DisplayName("999 -> Menor de idade n pode cadastrar")
    void idadeNaoHumanaDeveLancarException(){
        assertThrows(IllegalArgumentException.class, () -> validador.podeCadastrar(999));
    }
}

