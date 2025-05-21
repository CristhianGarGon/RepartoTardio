package es.uclm.reparto;

import es.uclm.reparto.controladores.RepartidorController;
import es.uclm.reparto.persistencia.PedidoDAO;
import es.uclm.reparto.persistencia.RepartidorDAO;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RepartidorController.class)
class RepartidorControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private PedidoDAO pedidoDAO;
    @MockBean private RepartidorDAO repartidorDAO;
    @MockBean private UsuarioDAO usuarioDAO;

    @Test
    void testMostrarPedidosPendientes() throws Exception {
        mockMvc.perform(get("/repartidor/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(view().name("notificaciones"));
    }

    @Test
    void testMostrarPedidosParaEntrega() throws Exception {
        mockMvc.perform(get("/repartidor/entregarPedido"))
                .andExpect(status().isOk())
                .andExpect(view().name("entregarPedido"));
    }

    @Test
    void testMostrarPedidosAsignados() throws Exception {
        mockMvc.perform(get("/repartidor/recogerPedido"))
                .andExpect(status().isOk())
                .andExpect(view().name("recogerPedido"));
    }
}
