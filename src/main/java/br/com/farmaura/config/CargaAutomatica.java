package br.com.farmaura.config;

import br.com.farmaura.model.Perfil;
import br.com.farmaura.model.Produto;
import br.com.farmaura.model.Usuario;
import br.com.farmaura.repository.ProdutoRepository;
import br.com.farmaura.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CargaAutomatica {

    @Bean
    public CommandLineRunner cadastrarUsuariosIniciais(UsuarioRepository repository,
                                                       ProdutoRepository produtoRepository,
                                                       PasswordEncoder passwordEncoder) {
        return args -> {
            // Cria o usuário ADMIN padrão se ainda não existir
            if (repository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setSenha(passwordEncoder.encode("admin123"));
                admin.setPerfil(Perfil.ADMIN);
                admin.setAtivo(true);
                repository.save(admin);
            }

            // Cria um usuário FARMACEUTICO padrão se ainda não existir
            if (repository.findByUsername("farmaceutico").isEmpty()) {
                Usuario farmaceutico = new Usuario();
                farmaceutico.setUsername("farmaceutico");
                farmaceutico.setSenha(passwordEncoder.encode("123456"));
                farmaceutico.setPerfil(Perfil.FARMACEUTICO);
                farmaceutico.setAtivo(true);
                repository.save(farmaceutico);
            }

            // Cria produtos iniciais caso o catálogo no banco esteja vazio
            if (produtoRepository.count() == 0) {
                produtoRepository.save(criarProduto(
                        "PROD-001", "SKU-DIP-500", "Dipirona Monoidratada", 8.50, false, "Sem Tarja",
                        "Analgésico", "500mg", "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?auto=format&fit=crop&w=300&q=80"
                ));
                produtoRepository.save(criarProduto(
                        "PROD-002", "SKU-AMO-500", "Amoxicilina Tri-hidratada", 28.90, true, "Vermelha",
                        "Antibiótico", "500mg", "https://images.unsplash.com/photo-1535585209827-a15fcdbc4c2d?auto=format&fit=crop&w=300&q=80"
                ));
                produtoRepository.save(criarProduto(
                        "PROD-003", "SKU-VIT-C", "Vitamina C + Zinco", 39.90, false, "Sem Tarja",
                        "Vitaminas", "1g", "https://images.unsplash.com/photo-1550572017-edd951baa74c?auto=format&fit=crop&w=300&q=80"
                ));
                produtoRepository.save(criarProduto(
                        "PROD-004", "SKU-PROT-60", "Protetor Solar Facial FPS 60", 62.00, false, "Sem Tarja",
                        "Perfumaria", "120g", "https://images.unsplash.com/photo-1598440947619-2c35fc9aa908?auto=format&fit=crop&w=300&q=80"
                ));
                produtoRepository.save(criarProduto(
                        "PROD-005", "SKU-PAR-750", "Paracetamol", 6.90, false, "Sem Tarja",
                        "Analgésico", "750mg", "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?auto=format&fit=crop&w=300&q=80"
                ));
                produtoRepository.save(criarProduto(
                        "PROD-006", "SKU-FRAL-M", "Fralda Infantil Pacote M", 45.90, false, "Sem Tarja",
                        "Infantil", "Tam M (40 un)", "https://images.unsplash.com/photo-1519689680058-324335c77eba?auto=format&fit=crop&w=300&q=80"
                ));
            }
        };
    }

    private Produto criarProduto(String id, String sku, String nome, double preco, boolean controlado,
                                 String tarja, String categoria, String concentracao, String img) {
        Produto p = new Produto();
        p.setIdProduto(id);
        p.setSku(sku);
        p.setNomeProduto(nome);
        p.setPrecoLista(preco);
        p.setControlado(controlado);
        p.setTarja(tarja);
        p.setCategoriaTerapeutica(categoria);
        p.setConcentracao(concentracao);
        p.setImg(img);
        return p;
    }
}
