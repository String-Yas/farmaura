package br.com.farmaura.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    @Test void construtorVazioDeveCriarCliente() { assertNotNull(new Cliente()); }
    @Test void construtorCompletoDevePreencherId() { assertEquals("C01", new Cliente("C01","PF","VAREJO","ATIVO").getIdCliente()); }
    @Test void construtorCompletoDevePreencherTipo() { assertEquals("PF", new Cliente("C01","PF","VAREJO","ATIVO").getTipoCliente()); }
    @Test void construtorCompletoDevePreencherSegmento() { assertEquals("VAREJO", new Cliente("C01","PF","VAREJO","ATIVO").getSegmento()); }
    @Test void construtorCompletoDevePreencherStatus() { assertEquals("ATIVO", new Cliente("C01","PF","VAREJO","ATIVO").getStatus()); }
    @Test void deveAlterarId() { Cliente c=new Cliente(); c.setIdCliente("C02"); assertEquals("C02",c.getIdCliente()); }
    @Test void deveAlterarTipo() { Cliente c=new Cliente(); c.setTipoCliente("PJ"); assertEquals("PJ",c.getTipoCliente()); }
    @Test void deveAlterarSegmento() { Cliente c=new Cliente(); c.setSegmento("CORPORATIVO"); assertEquals("CORPORATIVO",c.getSegmento()); }
    @Test void deveAlterarStatus() { Cliente c=new Cliente(); c.setStatus("INATIVO"); assertEquals("INATIVO",c.getStatus()); }
    @Test void deveAceitarIdNulo() { Cliente c=new Cliente(); c.setIdCliente(null); assertNull(c.getIdCliente()); }
    @Test void deveAceitarTipoNulo() { Cliente c=new Cliente(); c.setTipoCliente(null); assertNull(c.getTipoCliente()); }
    @Test void deveAceitarSegmentoNulo() { Cliente c=new Cliente(); c.setSegmento(null); assertNull(c.getSegmento()); }
    @Test void deveAceitarStatusNulo() { Cliente c=new Cliente(); c.setStatus(null); assertNull(c.getStatus()); }
    @Test void deveAceitarStringsVazias() { Cliente c=new Cliente("","","",""); assertAll(() -> assertEquals("",c.getIdCliente()), () -> assertEquals("",c.getTipoCliente()), () -> assertEquals("",c.getSegmento()), () -> assertEquals("",c.getStatus())); }
    @Test void devePermitirAtualizarDadosDoCliente() { Cliente c=new Cliente("C01","PF","VAREJO","ATIVO"); c.setSegmento("ONLINE"); c.setStatus("INATIVO"); assertAll(() -> assertEquals("ONLINE",c.getSegmento()), () -> assertEquals("INATIVO",c.getStatus())); }
}
