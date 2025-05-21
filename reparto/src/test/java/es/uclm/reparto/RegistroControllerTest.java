
package es.uclm.reparto;

import es.uclm.reparto.controladores.RegistroController;
import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistroController.class)
public class RegistroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioDAO usuarioDAO;

    @Test
    public void testProcesarRegistro() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNickname("test");
        usuario.setPassword("1234");

        Mockito.when(usuarioDAO.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/registro")
                .param("nickname", "test")
                .param("password", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
