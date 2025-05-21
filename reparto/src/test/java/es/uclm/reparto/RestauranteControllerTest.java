package es.uclm.reparto;


import es.uclm.reparto.controladores.RestauranteController;
import es.uclm.reparto.persistencia.ItemMenuDAO;
import es.uclm.reparto.persistencia.RestauranteDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestauranteController.class)
public class RestauranteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private ItemMenuDAO itemMenuDAO;
    @MockBean private RestauranteDAO restauranteDAO;

    @Test
    public void testMostrarFormularioCrearMenu() throws Exception {
        mockMvc.perform(get("/restaurante/crearMenu"))
               .andExpect(status().isOk())
               .andExpect(view().name("crearMenu"))
               .andExpect(model().attributeExists("itemMenu"))
               .andExpect(model().attributeExists("tipos"));
    }
}