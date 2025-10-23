package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

// POJO -> Pain Old Java Object
// -> GETTER AND SETTER
// -> CONSTRUTOR DEFAULT
@Getter
@AllArgsConstructor
public class Missao {
    private String agencia;
    private String nomeDoAstronauta;
    private boolean teveSucesso;
    private boolean teveFalhaCritica;
    private boolean veterano;
    private String destino;
    private boolean Secreta;
}
