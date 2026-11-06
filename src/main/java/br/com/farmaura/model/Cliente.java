package br.com.farmaura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    private String idCliente;
    private String tipoCliente;
    private String segmento;
    private String status;

    public Cliente() {}

    public Cliente(String idCliente, String tipoCliente, String segmento, String status) {
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
        this.segmento = segmento;
        this.status = status;
    }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }

    public String getSegmento() { return segmento; }
    public void setSegmento(String segmento) { this.segmento = segmento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}