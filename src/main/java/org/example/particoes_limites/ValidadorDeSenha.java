package org.example.particoes_limites;

public class ValidadorDeSenha {
    public String classificar(String senha){
        if (senha == null || senha.isBlank()) throw new IllegalArgumentException("Senha vazia não é permitida");
        if (senha.length() > 100) throw new IllegalArgumentException("Senha grande demais");

        boolean temLetra = senha.chars().anyMatch(Character::isLetter);
        boolean temDigito = senha.chars().anyMatch(Character::isDigit);
        boolean temEspecial = senha.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
        int tamanho = senha.length();

        if (tamanho < 8) return "FRACA";
        if (tamanho >= 10 && temLetra && temDigito && temEspecial) return "FORTE";
        if (tamanho >= 8 && temLetra && temDigito && !temEspecial) return "MEDIA";

        return "FRACA";
    }
}
