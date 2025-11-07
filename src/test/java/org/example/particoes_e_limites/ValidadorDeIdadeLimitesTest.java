package org.example.particoes_e_limites;

import org.example.particoes_limites.ValidadorDeIdades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidadorDeIdadeLimitesTest {
    private ValidadorDeIdades validador;

    @BeforeEach
    public void setup(){
        validador = new ValidadorDeIdades();
    }
    @Test @DisplayName("17 -> deve retornar false")
    void idade17_deveRetornarFalse(){
        assertFalse(validador.podeCadastrar(17));
    }
    @Test @DisplayName("18 -> deve retornar true")
    void idade18_deveRetornarFalse(){
        assertTrue(validador.podeCadastrar(18), "18 já deveria poder cadastrar");
    }
    @Test @DisplayName("60 -> deve retornar true")
    void idade60_deveRetornarTrue(){
        assertTrue(validador.podeCadastrar(60), "60 ainda deveria poder cadastrar");
    }
    @Test @DisplayName("61 -> deve retornar false")
    void idade61_deveRetornarFalse(){
        assertFalse(validador.podeCadastrar(61),  "61 já não é possível o cadastro");
    }
    @Test @DisplayName("0 -> não deve lançar exception")
    void idade0_deveLancarException(){
        assertDoesNotThrow(() -> {
            boolean resposta = validador.podeCadastrar(0);
            assertFalse(resposta, "0 anos não pode cadastrar");
        });
    }
    @Test @DisplayName("150 -> não deve lançar exception")
    void idade150_deveLancarException(){
        assertDoesNotThrow(() -> {
            boolean resposta = validador.podeCadastrar(150);
            assertFalse(resposta, "150 anos não pode cadastrar");
        });
    }
    @Test @DisplayName("151 -> deve lançar exception")
    void idade151_deveLancarException(){
        assertThrows(IllegalArgumentException.class, () -> validador.podeCadastrar(151));
    }
}


