package org.example.particoes_limites;

public class ValidadorDeIdades {
    // Retorna true se pode cadastrar, false se não pode
    /*
        Idade mínima para criar conta: 18 anos.
        Idade máxima: 60 anos.
        Tudo fora disso rejeita.
        Valores negativos ou absurdos (>150) devem lançar exceção
    */

    public boolean podeCadastrar(int idade){
        if (idade < 0 || idade > 150) throw new IllegalArgumentException("Idade inválida");
        return idadeEntre18E60(idade);
    }
    private boolean idadeEntre18E60(int idade){
        return idade >= 18 && idade <= 60;
    }
}
