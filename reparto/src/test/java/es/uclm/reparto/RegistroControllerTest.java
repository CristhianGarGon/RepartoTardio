/*package es.uclm.reparto;

import es.uclm.reparto.controladores.RegistroController;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistroController.class)
public class RegistroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private UsuarioDAO usuarioDAO;

    @Test
    public void testMostrarFormularioRegistro() throws Exception {
        mockMvc.perform(get("/registro"))
               .andExpect(status().isOk())
               .andExpect(view().name("registro"))
               .andExpect(model().attributeExists("usuario"));
    }
}*/