package br.com.farmaura.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PerfilTest {
    @Test void deveConterTresPerfis() { assertEquals(3,Perfil.values().length); }
    @Test void deveConterTodosOsPerfis() { assertArrayEquals(new Perfil[]{Perfil.ADMIN,Perfil.FARMACEUTICO,Perfil.CLIENTE},Perfil.values()); }
    @Test void deveConverterNomeParaEnum() { assertEquals(Perfil.FARMACEUTICO,Perfil.valueOf("FARMACEUTICO")); }
}
