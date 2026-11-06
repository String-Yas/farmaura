package br.com.farmaura.service;

import br.com.farmaura.model.Pedido;
import br.com.farmaura.model.StatusPedido;
import br.com.farmaura.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(String id) {
        return pedidoRepository.findById(id);
    }

    public Pedido criarPedido(Pedido pedido) {
        if (pedido.getStatus() == null) {
            pedido.setStatus(StatusPedido.PENDENTE);
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarStatus(String id, StatusPedido novoStatus) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setStatus(novoStatus);
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void deletar(String id) {
        pedidoRepository.deleteById(id);
    }
}
