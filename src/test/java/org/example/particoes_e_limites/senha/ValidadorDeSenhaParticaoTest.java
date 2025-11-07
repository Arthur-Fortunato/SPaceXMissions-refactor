package org.example.particoes_e_limites.senha;

import org.example.particoes_limites.ValidadorDeSenha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidadorDeSenhaParticaoTest {
    private ValidadorDeSenha validadorDeSenha;
    @BeforeEach
    void setUp(){
        validadorDeSenha = new ValidadorDeSenha();
    }
    @Test @DisplayName("Senha vazia deve lançar exception")
    void senhaVaziaLancaException(){
        assertThrows(IllegalArgumentException.class, () -> validadorDeSenha.classificar(""));
    }
    @Test @DisplayName("Senha longa deve lançar exception")
    void senhaLongaLancaException(){
        String senhaGrande = "a".repeat(200);
        assertThrows(IllegalArgumentException.class, () -> validadorDeSenha.classificar(senhaGrande));
    }
    @Test @DisplayName("Senha forte com mais de 10 chars deve ser válida")
    void senhaForteComMaisDe10Chars(){
        String senha = "AbcD2R23FF!";
        String resultado = validadorDeSenha.classificar(senha);
        assertEquals("FORTE", resultado);
    }

}
