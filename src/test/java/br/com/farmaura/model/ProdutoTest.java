package br.com.farmaura.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {
    @Test void construtorVazioDeveCriarProduto() { assertNotNull(new Produto()); }
    @Test void construtorBasicoDefineId() { assertEquals("P1",new Produto("P1","S1","Dipirona",12.5,false,"").getIdProduto()); }
    @Test void construtorBasicoDefineSku() { assertEquals("S1",new Produto("P1","S1","Dipirona",12.5,false,"").getSku()); }
    @Test void construtorBasicoDefineNome() { assertEquals("Dipirona",new Produto("P1","S1","Dipirona",12.5,false,"").getNomeProduto()); }
    @Test void construtorBasicoDefinePreco() { assertEquals(12.5,new Produto("P1","S1","Dipirona",12.5,false,"").getPrecoLista(),0.0001); }
    @Test void construtorBasicoDefineControlado() { assertFalse(new Produto("P1","S1","Dipirona",12.5,false,"").isControlado()); }
    @Test void construtorCompletoDefineCategoria() { assertEquals("Analgésico",new Produto("P1","S1","Dipirona",12.5,false,"", "Analgésico","500mg","img.png").getCategoriaTerapeutica()); }
    @Test void construtorCompletoDefineConcentracao() { assertEquals("500mg",new Produto("P1","S1","Dipirona",12.5,false,"", "Analgésico","500mg","img.png").getConcentracao()); }
    @Test void construtorCompletoDefineImagem() { assertEquals("img.png",new Produto("P1","S1","Dipirona",12.5,false,"", "Analgésico","500mg","img.png").getImg()); }
    @Test void deveAlterarIdESku() { Produto p=new Produto(); p.setIdProduto("P2"); p.setSku("S2"); assertAll(() -> assertEquals("P2",p.getIdProduto()), () -> assertEquals("S2",p.getSku())); }
    @Test void deveAlterarNomeEPreco() { Produto p=new Produto(); p.setNomeProduto("Paracetamol"); p.setPrecoLista(8.9); assertAll(() -> assertEquals("Paracetamol",p.getNomeProduto()), () -> assertEquals(8.9,p.getPrecoLista(),0.0001)); }
    @Test void deveAlterarControladoETarja() { Produto p=new Produto(); p.setControlado(true); p.setTarja("PRETA"); assertAll(() -> assertTrue(p.isControlado()), () -> assertEquals("PRETA",p.getTarja())); }
    @Test void deveAlterarCategoriaEConcentracao() { Produto p=new Produto(); p.setCategoriaTerapeutica("Vitaminas"); p.setConcentracao("1g"); assertAll(() -> assertEquals("Vitaminas",p.getCategoriaTerapeutica()), () -> assertEquals("1g",p.getConcentracao())); }
    @Test void deveAlterarImagem() { Produto p=new Produto(); p.setImg("produto.jpg"); assertEquals("produto.jpg",p.getImg()); }
    @Test void deveAceitarCamposTextuaisNulos() { Produto p=new Produto(); p.setIdProduto(null); p.setSku(null); p.setNomeProduto(null); p.setTarja(null); p.setCategoriaTerapeutica(null); p.setConcentracao(null); p.setImg(null); assertAll(() -> assertNull(p.getIdProduto()), () -> assertNull(p.getSku()), () -> assertNull(p.getNomeProduto()), () -> assertNull(p.getTarja()), () -> assertNull(p.getCategoriaTerapeutica()), () -> assertNull(p.getConcentracao()), () -> assertNull(p.getImg())); }
}
