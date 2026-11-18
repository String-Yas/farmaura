package br.com.farmaura.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {
    private Cliente cliente() { return new Cliente("C1","PF","VAREJO","ATIVO"); }
    private Entrega entrega() { return new Entrega(1,"Rua A","Centro","10","01000-000","Brasil"); }
    private Produto produto() { return new Produto("P1","SKU1","Produto",10,false,""); }
    @Test void construtorVazioDeveCriarPedido() { assertNotNull(new Pedido()); }
    @Test void construtorCompletoDefineIdentificador() { assertEquals("V1",new Pedido("V1",cliente(),entrega()).getIdVenda()); }
    @Test void construtorCompletoAssociaCliente() { Cliente c=cliente(); assertSame(c,new Pedido("V1",c,entrega()).getCliente()); }
    @Test void construtorCompletoAssociaEntrega() { Entrega e=entrega(); assertSame(e,new Pedido("V1",cliente(),e).getEntrega()); }
    @Test void construtorCompletoIniciaPendente() { assertEquals(StatusPedido.PENDENTE,new Pedido("V1",cliente(),entrega()).getStatus()); }
    @Test void construtorCompletoInicializaListaVazia() { assertTrue(new Pedido("V1",cliente(),entrega()).getItens().isEmpty()); }
    @Test void deveAdicionarItem() { Pedido p=new Pedido("V1",cliente(),entrega()); p.adicionarItem(produto(),2,10,0); assertEquals(1,p.getItens().size()); }
    @Test void itemAdicionadoMantemProduto() { Pedido p=new Pedido("V1",cliente(),entrega()); Produto prod=produto(); p.adicionarItem(prod,1,10,0); assertSame(prod,p.getItens().get(0).getProduto()); }
    @Test void calcularTotalPedidoVazio() { assertEquals(0,new Pedido("V1",cliente(),entrega()).calcularTotal(),0.0001); }
    @Test void calcularTotalComUmItem() { Pedido p=new Pedido("V1",cliente(),entrega()); p.adicionarItem(produto(),2,10,1); assertEquals(19,p.calcularTotal(),0.0001); }
    @Test void calcularTotalComVariosItens() { Pedido p=new Pedido("V1",cliente(),entrega()); p.adicionarItem(produto(),2,10,0); p.adicionarItem(produto(),1,5,1); assertEquals(24,p.calcularTotal(),0.0001); }
    @Test void deveAlterarIdVenda() { Pedido p=new Pedido(); p.setIdVenda("V2"); assertEquals("V2",p.getIdVenda()); }
    @Test void deveAlterarClienteEEntrega() { Pedido p=new Pedido(); Cliente c=cliente(); Entrega e=entrega(); p.setCliente(c); p.setEntrega(e); assertAll(() -> assertSame(c,p.getCliente()), () -> assertSame(e,p.getEntrega())); }
    @Test void deveAlterarStatus() { Pedido p=new Pedido(); p.setStatus(StatusPedido.CANCELADO); assertEquals(StatusPedido.CANCELADO,p.getStatus()); }
    @Test void deveSubstituirListaDeItens() { Pedido p=new Pedido(); ArrayList<ItemPedido> itens=new ArrayList<>(); itens.add(new ItemPedido(produto(),1,7,0)); p.setItens(itens); assertSame(itens,p.getItens()); assertEquals(7,p.calcularTotal(),0.0001); }
}
