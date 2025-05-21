package es.uclm.reparto;


import es.uclm.reparto.controladores.ClienteController;
import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.persistencia.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private RestauranteDAO restauranteDAO;
    @MockBean private ItemMenuDAO itemMenuDAO;
    @MockBean private ClienteDAO clienteDAO;
    @MockBean private PedidoDAO pedidoDAO;
    @MockBean private RepartidorDAO repartidorDAO;
    @MockBean private CodigoPostalDAO codigoPostalDAO;

    @Test
    public void testMostrarMenuCliente() throws Exception {
        mockMvc.perform(get("/cliente/menu")
               .sessionAttr("usuario", new Usuario())) // Mock de usuario
               .andExpect(status().isOk())
               .andExpect(view().name("clienteMenu"))
               .andExpect(model().attributeExists("usuario"));
    }


    @Test
    public void testBuscarRestaurantesCliente() throws Exception {
        mockMvc.perform(get("/cliente/buscarRestaurantes"))
               .andExpect(status().isOk())
               .andExpect(view().name("buscarRestaurantes"))
               .andExpect(model().attributeExists("restaurantes"));
    }
}