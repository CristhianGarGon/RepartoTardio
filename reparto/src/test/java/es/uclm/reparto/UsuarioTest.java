package es.uclm.reparto;

import es.uclm.reparto.entidades.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testSetAndGetNickname() {
        Usuario u = new Usuario();
        u.setNickname("cliente123");
        assertEquals("cliente123", u.getNickname());
    }

    @Test
    public void testPasswordMatch() {
        Usuario u = new Usuario();
        u.setPassword("secreta");
        assertTrue(u.getPassword().equals("secreta"));
    }
}