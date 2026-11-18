package br.com.farmaura.dto;

import br.com.farmaura.model.Perfil;
import br.com.farmaura.model.Usuario;

public class UsuarioDTO {
    private Long id;
    private String username;
    private Perfil perfil;
    private boolean ativo;

    public UsuarioDTO() {}

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.perfil = usuario.getPerfil();
        this.ativo = usuario.isAtivo();
    }

    public UsuarioDTO(Long id, String username, Perfil perfil, boolean ativo) {
        this.id = id;
        this.username = username;
        this.perfil = perfil;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
