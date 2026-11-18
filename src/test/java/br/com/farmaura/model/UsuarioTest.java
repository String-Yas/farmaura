package br.com.farmaura.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    @Test void construtorVazioDeveCriarUsuario() { assertNotNull(new Usuario()); }
    @Test void usuarioNovoTemIdNulo() { assertNull(new Usuario().getId()); }
    @Test void usuarioNovoEstaAtivoPorPadrao() { assertTrue(new Usuario().isAtivo()); }
    @Test void deveDefinirUsername() { Usuario u=new Usuario(); u.setUsername("yasmin"); assertEquals("yasmin",u.getUsername()); }
    @Test void deveDefinirSenha() { Usuario u=new Usuario(); u.setSenha("senha123"); assertEquals("senha123",u.getSenha()); }
    @Test void deveDefinirPerfilAdmin() { Usuario u=new Usuario(); u.setPerfil(Perfil.ADMIN); assertEquals(Perfil.ADMIN,u.getPerfil()); }
    @Test void deveDefinirPerfilFarmaceutico() { Usuario u=new Usuario(); u.setPerfil(Perfil.FARMACEUTICO); assertEquals(Perfil.FARMACEUTICO,u.getPerfil()); }
    @Test void deveDefinirPerfilCliente() { Usuario u=new Usuario(); u.setPerfil(Perfil.CLIENTE); assertEquals(Perfil.CLIENTE,u.getPerfil()); }
    @Test void deveDesativarUsuario() { Usuario u=new Usuario(); u.setAtivo(false); assertFalse(u.isAtivo()); }
    @Test void deveReativarUsuario() { Usuario u=new Usuario(); u.setAtivo(false); u.setAtivo(true); assertTrue(u.isAtivo()); }
    @Test void deveAceitarUsernameNuloNoObjetoJava() { Usuario u=new Usuario(); u.setUsername(null); assertNull(u.getUsername()); }
    @Test void deveAceitarSenhaNulaNoObjetoJava() { Usuario u=new Usuario(); u.setSenha(null); assertNull(u.getSenha()); }
    @Test void deveAceitarPerfilNuloNoObjetoJava() { Usuario u=new Usuario(); u.setPerfil(null); assertNull(u.getPerfil()); }
    @Test void deveAtualizarUsername() { Usuario u=new Usuario(); u.setUsername("cliente1"); u.setUsername("cliente2"); assertEquals("cliente2",u.getUsername()); }
    @Test void deveAtualizarSenhaEPerfil() { Usuario u=new Usuario(); u.setSenha("novaSenha"); u.setPerfil(Perfil.FARMACEUTICO); assertAll(() -> assertEquals("novaSenha",u.getSenha()), () -> assertEquals(Perfil.FARMACEUTICO,u.getPerfil())); }
}
