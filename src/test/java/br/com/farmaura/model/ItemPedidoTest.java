package br.com.farmaura.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoTest {
    private Produto produto() { return new Produto("P1","SKU1","Produto",10.0,false,""); }
    @Test void construtorVazioDeveCriarItem() { assertNotNull(new ItemPedido()); }
    @Test void itemVazioTemSubtotalZero() { assertEquals(0,new ItemPedido().getSubTotal(),0.0001); }
    @Test void construtorDeveAssociarProduto() { Produto p=produto(); ItemPedido item=new ItemPedido(p,2,10,1); assertSame(p,item.getProduto()); }
    @Test void construtorDeveDefinirQuantidade() { assertEquals(2,new ItemPedido(produto(),2,10,1).getQuantidade()); }
    @Test void construtorDeveDefinirPreco() { assertEquals(10,new ItemPedido(produto(),2,10,1).getPrecoUnitario()); }
    @Test void construtorDeveDefinirDesconto() { assertEquals(1,new ItemPedido(produto(),2,10,1).getDesconto()); }
    @Test void subtotalSemDesconto() { assertEquals(20,new ItemPedido(produto(),2,10,0).getSubTotal(),0.0001); }
    @Test void subtotalComDesconto() { assertEquals(19,new ItemPedido(produto(),2,10,1).getSubTotal(),0.0001); }
    @Test void subtotalComQuantidadeUm() { assertEquals(9,new ItemPedido(produto(),1,10,1).getSubTotal(),0.0001); }
    @Test void subtotalComQuantidadeZero() { assertEquals(-1,new ItemPedido(produto(),0,10,1).getSubTotal(),0.0001); }
    @Test void subtotalComPrecoZero() { assertEquals(-2,new ItemPedido(produto(),2,0,2).getSubTotal(),0.0001); }
    @Test void subtotalComDescontoZero() { assertEquals(30,new ItemPedido(produto(),3,10,0).getSubTotal(),0.0001); }
    @Test void deveAlterarIdEConsultar() { ItemPedido i=new ItemPedido(); i.setId(8L); assertEquals(8L,i.getId()); }
    @Test void deveAlterarProduto() { ItemPedido i=new ItemPedido(); Produto p=produto(); i.setProduto(p); assertSame(p,i.getProduto()); }
    @Test void deveAlterarQuantidadePrecoEDesconto() { ItemPedido i=new ItemPedido(); i.setQuantidade(4); i.setPrecoUnitario(5); i.setDesconto(2); assertAll(() -> assertEquals(4,i.getQuantidade()), () -> assertEquals(5,i.getPrecoUnitario()), () -> assertEquals(2,i.getDesconto()), () -> assertEquals(18,i.getSubTotal(),0.0001)); }
}
