package org.example.particoes_e_limites.senha;

import org.example.particoes_limites.ValidadorDeSenha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidadorDeSenhaLimiteTest {
    private ValidadorDeSenha validadorDeSenha;
    @BeforeEach
    void setUp(){
        validadorDeSenha = new ValidadorDeSenha();
    }

    @Test @DisplayName("Senha com 9 chars mesmo complexa não é forte")
    void validarSenhaCom9ENaoForte(){
        String senha9 = "1234567U!";
        assertEquals(9, senha9.length());
        assertNotEquals("FORTE", validadorDeSenha.classificar(senha9));
    }

    @Test @DisplayName("Senha com 10 chars é forte")
    void validarSenhaCom10EForte(){
        String senha10 = "1234567U!1";
        assertEquals(10, senha10.length());
        assertEquals("FORTE", validadorDeSenha.classificar(senha10));
    }
}
