package br.com.farmaura.service;

import br.com.farmaura.dto.UsuarioDTO;
import br.com.farmaura.dto.UsuarioFormDTO;
import br.com.farmaura.model.Perfil;
import br.com.farmaura.model.Usuario;
import br.com.farmaura.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id).map(UsuarioDTO::new);
    }

    @Transactional
    public UsuarioDTO criar(UsuarioFormDTO dto) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Nome de usuário já cadastrado: " + dto.getUsername());
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfil(dto.getPerfil() != null ? dto.getPerfil() : Perfil.CLIENTE);
        usuario.setAtivo(dto.getAtivo() == null || dto.getAtivo());

        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(salvo);
    }

    @Transactional
    public UsuarioDTO atualizar(Long id, UsuarioFormDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));

        if (dto.getUsername() != null && !dto.getUsername().isBlank()
                && !dto.getUsername().equals(usuario.getUsername())) {
            if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
                throw new IllegalArgumentException("Nome de usuário já existe: " + dto.getUsername());
            }
            usuario.setUsername(dto.getUsername());
        }

        if (dto.getPerfil() != null) {
            usuario.setPerfil(dto.getPerfil());
        }

        if (dto.getAtivo() != null) {
            usuario.setAtivo(dto.getAtivo());
        }

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(salvo);
    }

    @Transactional
    public UsuarioDTO alternarStatus(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));
        usuario.setAtivo(!usuario.isAtivo());
        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(salvo);
    }

    @Transactional
    public void deletar(Long id, String usernameLogado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));

        if (usuario.getUsername().equalsIgnoreCase(usernameLogado)) {
            throw new IllegalArgumentException("Não é permitido excluir o seu próprio usuário logado.");
        }

        usuarioRepository.deleteById(id);
    }
}
