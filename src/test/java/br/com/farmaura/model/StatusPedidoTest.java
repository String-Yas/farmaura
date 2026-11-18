package br.com.farmaura.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class StatusPedidoTest {
    @Test void deveConterCincoStatus() { assertEquals(5,StatusPedido.values().length); }
    @Test void deveConterTodosOsStatus() { assertArrayEquals(new StatusPedido[]{StatusPedido.PENDENTE,StatusPedido.PROCESSANDO,StatusPedido.ENVIADO,StatusPedido.ENTREGUE,StatusPedido.CANCELADO},StatusPedido.values()); }
    @Test void deveConverterPendente() { assertEquals(StatusPedido.PENDENTE,StatusPedido.valueOf("PENDENTE")); }
    @Test void deveConverterEnviado() { assertEquals(StatusPedido.ENVIADO,StatusPedido.valueOf("ENVIADO")); }
    @Test void deveConverterCancelado() { assertEquals(StatusPedido.CANCELADO,StatusPedido.valueOf("CANCELADO")); }
}
