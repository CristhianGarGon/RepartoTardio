package es.uclm.reparto;


import es.uclm.reparto.controladores.AnonimoController;
import es.uclm.reparto.persistencia.ItemMenuDAO;
import es.uclm.reparto.persistencia.RestauranteDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnonimoController.class)
public class AnonimoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private RestauranteDAO restauranteDAO;
    @MockBean private ItemMenuDAO itemMenuDAO;

    @Test
    public void testBuscarRestaurantesAnonimo() throws Exception {
        mockMvc.perform(get("/anonimo/buscar"))
               .andExpect(status().isOk())
               .andExpect(view().name("buscarAnonimo"))
               .andExpect(model().attributeExists("restaurantes"));
    }
}