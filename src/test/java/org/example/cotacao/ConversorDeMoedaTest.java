package org.example.cotacao;

import org.example.appCotacao.*;
import org.example.appCotacao.CambioClient;
import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class ConversorDeMoedaTest {
    @Mock
    CambioClient client;
    @InjectMocks
    ConversorDeMoedaService service;

    @Provide
    Arbitrary<Double> valores(){
        return Arbitraries.doubles()
                .between(0.0,10_000.0)
                .map(v -> Math.round(v * 100.0) / 100.0);
    }
    @Provide
    Arbitrary<String> moedas(){
        return Arbitraries.of("USD","EUR", "GBL");
    }
    @Property(tries = 1000)
    void conversaoNundaDaNegativo(@ForAll("moedas") String moeda, @ForAll("valores") Double valor){
        CambioClient mock = mock(CambioClient.class);
        when(mock.buscarCotacao("USD")).thenReturn(valor);
        when(mock.buscarCotacao("EUR")).thenReturn(valor);
        ConversorDeMoedaService service = new ConversorDeMoedaService(mock);
        double resultado = service.converterParaReais(moeda, valor);
        assertTrue(resultado >= 0);

    }
//    @Property(tries = 1000)
//    void conversorDeveRespeitarProporcionalidade(@ForAll("moedas") String moeda, @ForAll("valores") Double valor){
//        double resultado = service.converterParaReais(moeda, valor);
//        double resultadoDobro = service.converterParaReais(moeda, valor * 2);
//        assertEquals(resultado * 2, resultadoDobro);
//    }

    @Property(tries = 1)
    void deveTestarComMockito_01(){
        //1. Criando o Mock
        CambioClient mock = mock(CambioClient.class);

        //2. Configurar comportamento.
        when(mock.buscarCotacao("USD")).thenReturn(5.0);
        when(mock.buscarCotacao("EUR")).thenReturn(6.0);
        //3.0 Injetar o mock no servico
        ConversorDeMoedaService service = new ConversorDeMoedaService(mock);
        //4.0 Executar Resultado
        assertEquals(15.0, service.converterParaReais("USD", 10.0));
        assertEquals(18.0, service.converterParaReais("EUR", 10.0));
        verify(mock).buscarCotacao("USD");
        //verifyNoMoreInteractions(mock);
    }
    @Property(tries = 1)
    void deveTestarComMockito_02(){
        CambioClient mock = mock(CambioClient.class);
        when(mock.buscarCotacao("FFF")).thenThrow(new RuntimeException());
        ConversorDeMoedaService service2 = new ConversorDeMoedaService(mock);
        assertThrows(RuntimeException.class, () -> service.converterParaReais("FFF", 10.0));
        verify(mock,never()).buscarCotacao(anyString());
    }
    @Property(tries = 1)
    void emSequencia(){
        CambioClient mock = mock(CambioClient.class);
        when(mock.buscarCotacao("USD"))
                .thenReturn(5.0,5.1,4.9);
        ConversorDeMoedaService service2 = new ConversorDeMoedaService(mock);
        assertEquals(15, service2.converterParaReais("USD", 10), 1e-6);
        assertEquals(15.29, service2.converterParaReais("USD", 10), 0.1);
        assertEquals(14.7, service2.converterParaReais("USD", 10), 1e-6);

    }
    @Test
    void comAnnotation(){
        when(client.buscarCotacao("GBL")).thenReturn(6.5);
        service.converterParaReais("GBL", 10.0);
    }

}