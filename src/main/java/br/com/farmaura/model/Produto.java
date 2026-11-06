package br.com.farmaura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    private String idProduto;
    private String sku;
    private String nomeProduto;
    private double precoLista;
    private boolean controlado;
    private String tarja;

    public Produto() {}

    public Produto(String idProduto, String sku, String nomeProduto, double precoLista, boolean controlado, String tarja) {
        this.idProduto = idProduto;
        this.sku = sku;
        this.nomeProduto = nomeProduto;
        this.precoLista = precoLista;
        this.controlado = controlado;
        this.tarja = tarja;
    }

    public String getIdProduto() { return idProduto; }
    public void setIdProduto(String idProduto) { this.idProduto = idProduto; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public double getPrecoLista() { return precoLista; }
    public void setPrecoLista(double precoLista) { this.precoLista = precoLista; }

    public boolean isControlado() { return controlado; }
    public void setControlado(boolean controlado) { this.controlado = controlado; }

    public String getTarja() { return tarja; }
    public void setTarja(String tarja) { this.tarja = tarja; }
}