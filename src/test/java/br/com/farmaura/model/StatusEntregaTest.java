package br.com.farmaura.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class StatusEntregaTest {
    @Test void deveConterTresStatus() { assertEquals(3,StatusEntrega.values().length); }
    @Test void deveConterTodosOsStatus() { assertArrayEquals(new StatusEntrega[]{StatusEntrega.EM_TRANSITO,StatusEntrega.ENTREGUE,StatusEntrega.EXTRAVIADO},StatusEntrega.values()); }
    @Test void deveConverterNomeParaEnum() { assertEquals(StatusEntrega.ENTREGUE,StatusEntrega.valueOf("ENTREGUE")); }
}
