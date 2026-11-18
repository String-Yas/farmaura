package br.com.farmaura.controller;

import br.com.farmaura.model.Cliente;
import br.com.farmaura.model.Perfil;
import br.com.farmaura.model.Usuario;
import br.com.farmaura.repository.ClienteRepository;
import br.com.farmaura.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UsuarioRepository usuarioRepository,
            ClienteRepository clienteRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth/cadastrar")
    public String cadastrarUsuario(
            @RequestParam String username,
            @RequestParam String senha,
            @RequestParam(required = false) String idCliente,
            @RequestParam(required = false) String tipoCliente) {

        if (usuarioRepository.findByUsername(username).isPresent()) {
            return "redirect:/?erroCadastro=usuario_existente";
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername(username);
        novoUsuario.setSenha(passwordEncoder.encode(senha));

        // Cadastro público sempre cria CLIENTE
        novoUsuario.setPerfil(Perfil.CLIENTE);
        novoUsuario.setAtivo(true);

        usuarioRepository.save(novoUsuario);

        if (idCliente != null && !idCliente.isBlank()) {
            Cliente cliente = new Cliente(
                    idCliente,
                    tipoCliente != null ? tipoCliente : "Fisica",
                    "Varejo",
                    "Ativo"
            );
            clienteRepository.save(cliente);
        }

        return "redirect:/?sucessoCadastro=true";
    }
}