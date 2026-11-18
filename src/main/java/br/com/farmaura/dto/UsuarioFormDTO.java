package br.com.farmaura.dto;

import br.com.farmaura.model.Perfil;

public class UsuarioFormDTO {
    private String username;
    private String senha;
    private Perfil perfil;
    private Boolean ativo;

    public UsuarioFormDTO() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
