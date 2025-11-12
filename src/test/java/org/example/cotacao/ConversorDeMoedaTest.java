package org.example.cotacao;

import net.jqwik.api.*;
import org.example.appCotacao.CambioClient;
import org.example.appCotacao.CambioClientFake;
import org.example.appCotacao.ConversorDeMoedaService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConversorDeMoedaTest {
    @Provide
    Arbitrary<Double> valores(){
        return Arbitraries.doubles()
                .between(0.0, 10_000.0)
                .map(v -> Math.round(v * 100.0) / 100.0);
    }

    @Provide
    Arbitrary<String> moedas(){
        return Arbitraries.of("USD", "EUR", "GBL");
    }

    @Property(tries = 150)
    void conversaoNuncaDaNegativo(@ForAll("moedas") String moeda, @ForAll("valores") Double valor){
        ConversorDeMoedaService service = new ConversorDeMoedaService(new CambioClientFake());
        double resultado = service.converterParaReais(moeda, valor);
        assertTrue(resultado >= 0);
    }

    @Property(tries = 150)
    void conversorDeveRespeitarProporcionalidade(@ForAll("moedas") String moeda, @ForAll("valores") Double valor){
        ConversorDeMoedaService service = new ConversorDeMoedaService(new CambioClientFake());
        double resultado = service.converterParaReais(moeda, valor);
        double resultadoDobro = service.converterParaReais(moeda, valor * 2);
        assertEquals(resultado * 2, resultadoDobro);
    }

    @Test
    void deveTestarComMockito_01(){
        //1. Criando o mock
        CambioClient mock = mock(CambioClient.class);

        //2. Configurar comportamento
        when(mock.buscarCotacao("USD")).thenReturn(5.0);
        when(mock.buscarCotacao("EUR")).thenReturn(6.0);
        //3.0 Injetar o mock no serviço
        ConversorDeMoedaService service = new ConversorDeMoedaService(mock);
        //4.0 Executar resultado
        assertEquals(15.0, service.converterParaReais("USD", 10.0));
        assertEquals(18.0, service.converterParaReais("EUR", 10.0));
    }
}
