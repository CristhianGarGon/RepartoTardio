package es.uclm.reparto;


import es.uclm.reparto.controladores.RegistroClienteController;
import es.uclm.reparto.persistencia.ClienteDAO;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistroClienteController.class)
class RegistroClienteControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ClienteDAO clienteDAO;
    @MockBean private UsuarioDAO usuarioDAO;

    @Test
    void testMostrarFormularioRegistroCliente() throws Exception {
        mockMvc.perform(get("/registroCliente"))
                .andExpect(status().isOk())
                .andExpect(view().name("registroCliente"));
    }
}
