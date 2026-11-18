package br.com.farmaura.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EntregaTest {
    @Test void construtorVazioDeveCriarEntrega() { assertNotNull(new Entrega()); }
    @Test void construtorCompletoDeveDefinirId() { assertEquals(1,new Entrega(1,"Rua A","Centro","10","01000-000","Brasil").getId()); }
    @Test void construtorCompletoDeveDefinirRua() { assertEquals("Rua A",new Entrega(1,"Rua A","Centro","10","01000-000","Brasil").getRua()); }
    @Test void construtorCompletoDeveDefinirBairro() { assertEquals("Centro",new Entrega(1,"Rua A","Centro","10","01000-000","Brasil").getBairro()); }
    @Test void construtorCompletoDeveDefinirNumero() { assertEquals("10",new Entrega(1,"Rua A","Centro","10","01000-000","Brasil").getNumero()); }
    @Test void construtorCompletoDeveDefinirCep() { assertEquals("01000-000",new Entrega(1,"Rua A","Centro","10","01000-000","Brasil").getCep()); }
    @Test void construtorCompletoDeveDefinirPais() { assertEquals("Brasil",new Entrega(1,"Rua A","Centro","10","01000-000","Brasil").getPais()); }
    @Test void construtorCompletoIniciaEmTransito() { assertEquals(StatusEntrega.EM_TRANSITO,new Entrega(1,"Rua A","Centro","10","01000-000","Brasil").getStatus()); }
    @Test void deveAlterarId() { Entrega e=new Entrega(); e.setId(9); assertEquals(9,e.getId()); }
    @Test void deveAlterarStatus() { Entrega e=new Entrega(); e.setStatus(StatusEntrega.ENTREGUE); assertEquals(StatusEntrega.ENTREGUE,e.getStatus()); }
    @Test void deveAlterarRua() { Entrega e=new Entrega(); e.setRua("Av. Paulista"); assertEquals("Av. Paulista",e.getRua()); }
    @Test void deveAlterarBairro() { Entrega e=new Entrega(); e.setBairro("Bela Vista"); assertEquals("Bela Vista",e.getBairro()); }
    @Test void deveAlterarNumero() { Entrega e=new Entrega(); e.setNumero("200"); assertEquals("200",e.getNumero()); }
    @Test void deveAlterarCepEPais() { Entrega e=new Entrega(); e.setCep("22222-000"); e.setPais("Brasil"); assertAll(() -> assertEquals("22222-000",e.getCep()), () -> assertEquals("Brasil",e.getPais())); }
    @Test void deveAceitarCamposTextuaisNulos() { Entrega e=new Entrega(); e.setRua(null); e.setBairro(null); e.setNumero(null); e.setCep(null); e.setPais(null); assertAll(() -> assertNull(e.getRua()), () -> assertNull(e.getBairro()), () -> assertNull(e.getNumero()), () -> assertNull(e.getCep()), () -> assertNull(e.getPais())); }
}
