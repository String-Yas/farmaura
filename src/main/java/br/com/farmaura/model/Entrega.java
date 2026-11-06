package br.com.farmaura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private String rua;
    private String bairro;
    private String numero;
    private String cep;
    private String pais;

    public Entrega() {}

    public Entrega(int id, String rua, String bairro, String numero, String cep, String pais) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.pais = pais;
        this.status = StatusEntrega.EM_TRANSITO;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public StatusEntrega getStatus() { return status; }
    public void setStatus(StatusEntrega status) { this.status = status; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}