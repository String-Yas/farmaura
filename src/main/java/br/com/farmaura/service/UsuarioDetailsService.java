package br.com.farmaura.service;

import br.com.farmaura.model.Usuario;
import br.com.farmaura.repository.UsuarioRepository;
import br.com.farmaura.security.PrincipalFarmaceutico;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public UsuarioDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return new PrincipalFarmaceutico(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getSenha(),
                usuario.getPerfil(),
                usuario.isAtivo()
        );
    }
}