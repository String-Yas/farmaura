
package br.com.farmaura.service;

import br.com.farmaura.model.Produto;
import br.com.farmaura.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(String id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletar(String id) {
        produtoRepository.deleteById(id);
    }

    // NOVO MÉTODO: busca as categorias cadastradas nos produtos
    public List<String> listarCategorias() {
        return produtoRepository.findAll()
                .stream()
                .map(Produto::getCategoriaTerapeutica)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(categoria -> !categoria.isEmpty())
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();
    }
}
