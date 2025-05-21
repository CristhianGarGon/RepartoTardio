package es.uclm.reparto;


import es.uclm.reparto.controladores.RegistroRestauranteController;
import es.uclm.reparto.persistencia.CodigoPostalDAO;
import es.uclm.reparto.persistencia.RestauranteDAO;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistroRestauranteController.class)
public class RegistroRestauranteControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private RestauranteDAO restauranteDAO;
    @MockBean private UsuarioDAO usuarioDAO;
    @MockBean private CodigoPostalDAO codigoPostalDAO;

    @Test
    public void testMostrarFormularioRegistroRestaurante() throws Exception {
        mockMvc.perform(get("/registroRestaurante"))
               .andExpect(status().isOk())
               .andExpect(view().name("registroRestaurante"))
               .andExpect(model().attributeExists("restaurante"));
    }
}