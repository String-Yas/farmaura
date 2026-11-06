package br.com.farmaura.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    private String idVenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedido> itens = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entrega_id")
    private Entrega entrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public Pedido() {}

    public Pedido(String idVenda, Cliente cliente, Entrega entrega) {
        this.idVenda = idVenda;
        this.cliente = cliente;
        this.entrega = entrega;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.PENDENTE;
    }

    public void adicionarItem(Produto produto, int quantidade, double precoUnitario, double desconto) {
        this.itens.add(new ItemPedido(produto, quantidade, precoUnitario, desconto));
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : itens) {
            total += item.getSubTotal();
        }
        return total;
    }

    public String getIdVenda() { return idVenda; }
    public void setIdVenda(String idVenda) { this.idVenda = idVenda; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }

    public Entrega getEntrega() { return entrega; }
    public void setEntrega(Entrega entrega) { this.entrega = entrega; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
}