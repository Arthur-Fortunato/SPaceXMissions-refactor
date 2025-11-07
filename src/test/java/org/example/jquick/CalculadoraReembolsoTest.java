package org.example.jquick;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.DoubleArbitrary;
import net.jqwik.api.arbitraries.IntegerArbitrary;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.IntRange;
import org.example.reembolso.CalculadoraDeReembolso;
import org.example.reembolso.TipoTransporte;
import org.example.reembolso.Viagem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraReembolsoTest {
    CalculadoraDeReembolso calculadora = new CalculadoraDeReembolso();

    @Test
    void carroDaEmpresaNaoGeraReembolso() {
        Viagem viagem = new Viagem("SP", "BH", 600, TipoTransporte.CARRO_EMPRESA, 0.0, false);
        assertEquals(0.0, calculadora.calcular(viagem));
    }
    @Property
    void carroParticularReembolsaPorKm(@ForAll @IntRange(min = 1, max = 1000) int km){
        Viagem viagem = new Viagem("SP", "BH", km, TipoTransporte.CARRO_PARTICULAR, 0.0, true);
        double resultado = new CalculadoraDeReembolso().calcular(viagem);
        assertEquals(resultado, km*0.75);
    }
    @Property
    void aviaoCondicional(@ForAll @IntRange(min = 1, max = 500) int km,@ForAll @DoubleRange(min = 1.0, max = 10_000.0) double custo){
        Viagem viagem = new Viagem("SP", "BH", km, TipoTransporte.AVIAO, custo, true);
        if(km < 500){
            assertEquals(calculadora.calcular(viagem), 0.0);
        }
        assertEquals(calculadora.calcular(viagem), custo);
    }
    @Property
    boolean reembolsoNuncaNegativo(@ForAll("viagemRealista") Viagem v){
        return calculadora.calcular(v) >= 0;
    }
    @Provide
    Arbitrary<Viagem> viagemRealista(){
        Arbitrary<TipoTransporte> tipo = Arbitraries.of(TipoTransporte.values());
        Arbitrary<Boolean> usouCarroProprio = Arbitraries.of(true, false);
        return tipo.flatMap(transporte -> {
            Arbitrary<Integer> distancia;
            Arbitrary<Double> custo;
            switch (transporte) {
                case AVIAO:
                    distancia =  Arbitraries.integers().between(500, 5_000);
                    custo =  Arbitraries.doubles().between(500, 10_000);
                    break;

                case TREM:
                    distancia =  Arbitraries.integers().between(100, 2000);
                    custo =  Arbitraries.doubles().between(100, 5_000);
                    break;

                case CARRO_PARTICULAR:
                    distancia =  Arbitraries.integers().between(10, 2000);
                    custo =  Arbitraries.just(0.0);
                    break;

                case CARRO_EMPRESA:
                    distancia =  Arbitraries.integers().between(10, 2000);
                    custo =  Arbitraries.just(0.0);
                    break;

                default:
                    throw new IllegalArgumentException("Transporte inválido");
            }
            return Combinators.combine(distancia, custo,  usouCarroProprio).as(
                    (dist,custoTotal,carroProprio) -> new Viagem(
                            "A",
                            "B",
                            dist,
                            transporte,
                            custoTotal,
                            carroProprio
                    )
            );
        });
    }
    @Provide
    Arbitrary<Viagem> viagensValidas(){
        IntegerArbitrary distancias = Arbitraries.integers().between(300, 5000);
        DoubleArbitrary custos = Arbitraries.doubles().between(0.1, 5000.0);
        Arbitrary<TipoTransporte> tiposDeTransporte = Arbitraries.of(TipoTransporte.values());
        return Combinators.combine(distancias, custos, tiposDeTransporte)
                .as((distancia, custo, transporte) -> {
                    return new Viagem("A", "B", distancia, transporte, custo, transporte == TipoTransporte.CARRO_PARTICULAR);
                });
    }

}
