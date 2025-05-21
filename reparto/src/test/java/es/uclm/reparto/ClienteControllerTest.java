package es.uclm.reparto;

import es.uclm.reparto.controladores.ClienteController;
import es.uclm.reparto.entidades.*;
import es.uclm.reparto.persistencia.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private RestauranteDAO restauranteDAO;
    @MockBean private ItemMenuDAO itemMenuDAO;
    @MockBean private ClienteDAO clienteDAO;
    @MockBean private PedidoDAO pedidoDAO;
    @MockBean private RepartidorDAO repartidorDAO;
    @MockBean private CodigoPostalDAO codigoPostalDAO;

    private MockHttpSession session;
    private Usuario usuario;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        session = new MockHttpSession();
        usuario = new Usuario();
        usuario.setNickname("cliente");
        session.setAttribute("usuario", usuario);

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setUsuario(usuario);
    }

    @Test
    void testMostrarMenuCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/menu").session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("clienteMenu"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("usuario"));
    }

    @Test
    void testBuscarRestaurantes() throws Exception {
        when(restauranteDAO.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/buscarRestaurantes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("buscarRestaurantes"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("restaurantes"));
    }

    @Test
    void testMostrarFormularioPedido_RestauranteExiste() throws Exception {
        Restaurante r = new Restaurante();
        r.setId(1L);
        when(restauranteDAO.findById(1L)).thenReturn(Optional.of(r));
        when(itemMenuDAO.findByRestaurante(r)).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/realizarPedido/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("realizarPedido"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("restaurante"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("menu"));
    }

    @Test
    void testMostrarFormularioPedido_RestauranteNull() throws Exception {
        when(restauranteDAO.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/realizarPedido/99"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/cliente/buscarRestaurantes"));
    }

    @Test
    void testVerMenuRestaurante() throws Exception {
        Restaurante r = new Restaurante();
        r.setId(1L);
        when(restauranteDAO.findById(1L)).thenReturn(Optional.of(r));
        when(itemMenuDAO.findAll()).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/verMenu/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("verMenuRestaurante"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("restaurante"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("menu"));
    }

    @Test
    void testVerFavoritos() throws Exception {
        when(clienteDAO.findByUsuario(usuario)).thenReturn(cliente);
        cliente.setFavoritosList(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/verFavoritos").session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("verFavoritos"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("favoritos"));
    }

   /* @Test
    void testVerPedidosEntregados() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setUsuario(usuario);

        Restaurante restaurante = new Restaurante();
        restaurante.setNombre("Taco House");

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEntregado(true);
        pedido.setRestaurante(restaurante); // ✅ Esto soluciona el error de Thymeleaf

        when(clienteDAO.findByUsuario(any())).thenReturn(cliente);
        when(pedidoDAO.findAll()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/cliente/verPedidosEntregados").sessionAttr("usuario", usuario))
                .andExpect(status().isOk())
                .andExpect(view().name("pedidosEntregados"))
                .andExpect(model().attributeExists("pedidos"));
    }*/

	

}
