package org.example.jquick;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.example.model.Pessoa;
import org.example.model.Planeta;
import org.junit.jupiter.api.Assertions;

public class PrioridadesBasicas {
    @Property(tries = 10_000, edgeCases = EdgeCasesMode.FIRST)
    boolean propriedadeSomaCumutativa(@ForAll int a,@ForAll int b) {
        return a + b == b + a;
    }
    @Property
    boolean comprimentoPreservado(@ForAll String s) {
        return s.length() == new StringBuilder(s).reverse().toString().length();
    }
    @Property
    boolean concatenacaoPreservaOrdem(@ForAll String a,@ForAll String b) {
        return (a + b).startsWith(a);
    }
    @Property
    boolean divisaoComDenominadorValido(@ForAll int a,@ForAll int b) {
        Assume.that(b != 0);
        return (a % b) <= a;
    }
    @Property(seed = "42")
    boolean bugComNumero42(@ForAll @IntRange(min=400, max=4000) int a) {
        return a != 420;
    }
    @Property
    boolean testaImpares(@ForAll("somenteImpares") int n){
        return n % 2 != 0;
    }
    @Provide
    Arbitrary<Integer> somenteImpares(){
        return Arbitraries.integers().between(1,999)
                .filter(n -> n% 2 != 0);
    }
    @Property
    boolean testaQQString(@ForAll("palavras") String a, @ForAll("palavras") String b){
        return (a+b).startsWith(a);
    }
    @Provide
    Arbitrary<String> palavras(){
        return Arbitraries.strings().alpha().ofMinLength(3).ofMaxLength(10);
    }
    @Provide
    Arbitrary<String> nomesFixos(){
        return Arbitraries.of("Leo", "Pedro", "Joao");
    }
    @Property
    boolean containsQQNome(@ForAll("nomesFixos") String nome){
        return nome.contains(nome);
    }
    @Property
    void validarPessoa(@ForAll("geradorDePessoas") Pessoa pessoa){
        Assertions.assertNotNull(pessoa);
        Assertions.assertTrue(pessoa.getIdade() >= 18);
    }
    @Provide
    Arbitrary<Pessoa> geradorDePessoas(){
        Arbitrary<String > nomes = Arbitraries.
                strings().withCharRange('A', 'Z').ofMinLength(5).ofMaxLength(10);
        Arbitrary<Integer> idades =  Arbitraries.integers().between(18, 99);

        return Combinators.combine(nomes, idades).as(Pessoa::new);
    }
    @Property
    void cpfTemFormatoValido(@ForAll("cpfFalso") String cpf){
        Assertions.assertTrue(cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"));
    }
    @Provide
    Arbitrary<String> cpfFalso(){
        return Arbitraries.strings().numeric().ofLength(11)
                .map(s -> s.substring(0,3) + "." + s.substring(3,6) + "." + s.substring(6,9) + "-" + s.substring(9));
    }
    @Property
    void testandoEnums(@ForAll Planeta planeta){
        System.out.println(planeta.toString());
    }
    @Property
    void testandoEnumsPlanetasFiltrados(@ForAll("apenasPequenos") Planeta planeta){
        System.out.println(planeta.toString());
    }
    @Provide
    Arbitrary<Planeta> apenasPequenos(){
        return Arbitraries.of(Planeta.values()).filter(planeta -> planeta != Planeta.JUPITER);
    }
}
