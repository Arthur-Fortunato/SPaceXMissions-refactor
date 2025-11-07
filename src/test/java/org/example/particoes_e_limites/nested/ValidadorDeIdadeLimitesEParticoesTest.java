package org.example.particoes_e_limites.nested;

import org.example.particoes_limites.ValidadorDeIdades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidadorDeIdadeLimitesEParticoesTest {

    private ValidadorDeIdades validador;
    @BeforeEach
    public void setup(){
        validador = new ValidadorDeIdades();
    }

    @Nested
    class Limites {
        @Test
        @DisplayName("17 -> deve retornar false")
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

    @Nested
    class Particoes {
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
}
