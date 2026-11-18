
package br.com.farmaura.security;

import br.com.farmaura.model.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class PrincipalFarmaceutico implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Perfil perfil;
    private final boolean ativo;

    public PrincipalFarmaceutico(
            Long id,
            String username,
            String password,
            Perfil perfil,
            boolean ativo) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.perfil = perfil;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + perfil.name())
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ativo;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }
}
