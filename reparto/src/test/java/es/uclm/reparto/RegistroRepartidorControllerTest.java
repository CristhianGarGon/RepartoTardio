package es.uclm.reparto;

import es.uclm.reparto.controladores.RegistroRepartidorController;
import es.uclm.reparto.persistencia.CodigoPostalDAO;
import es.uclm.reparto.persistencia.RepartidorDAO;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistroRepartidorController.class)
public class RegistroRepartidorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private RepartidorDAO repartidorDAO;
    @MockBean private UsuarioDAO usuarioDAO;
    @MockBean private CodigoPostalDAO codigoPostalDAO;

    @Test
    public void testMostrarFormularioRegistroRepartidor() throws Exception {
        mockMvc.perform(get("/registroRepartidor"))
               .andExpect(status().isOk())
               .andExpect(view().name("registroRepartidor"))
               .andExpect(model().attributeExists("repartidor"))
               .andExpect(model().attributeExists("codigosDisponibles"));
    }
}