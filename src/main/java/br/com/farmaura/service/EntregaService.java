package br.com.farmaura.service;

import br.com.farmaura.model.Entrega;
import br.com.farmaura.model.StatusEntrega;
import br.com.farmaura.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public List<Entrega> listarTodas() {
        return entregaRepository.findAll();
    }

    public Optional<Entrega> buscarPorId(Integer id) {
        return entregaRepository.findById(id);
    }

    public Entrega salvar(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public Entrega atualizarStatus(Integer id, StatusEntrega novoStatus) {
        return entregaRepository.findById(id).map(entrega -> {
            entrega.setStatus(novoStatus);
            return entregaRepository.save(entrega);
        }).orElseThrow(() -> new RuntimeException("Entrega não encontrada"));
    }
}
