package es.uclm.reparto;


import es.uclm.reparto.controladores.RestauranteController;
import es.uclm.reparto.entidades.Restaurante;
import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.persistencia.ItemMenuDAO;
import es.uclm.reparto.persistencia.RestauranteDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;


@WebMvcTest(RestauranteController.class)
class RestauranteControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private RestauranteDAO restauranteDAO;
    @MockBean private ItemMenuDAO itemMenuDAO;

    @Test
    void testMostrarMenu() throws Exception {
        mockMvc.perform(get("/restaurante/menu"))
                .andExpect(status().isOk())
                .andExpect(view().name("restauranteMenu"));
    }

    @Test
    public void testMostrarFormularioCrearMenu() throws Exception {
        mockMvc.perform(get("/restaurante/crearMenu"))
               .andExpect(status().isOk())
               .andExpect(view().name("crearMenu"))
               .andExpect(model().attributeExists("itemMenu"))
               .andExpect(model().attributeExists("tipos"));
    }

    @Test
    void testMostrarFormularioEditarMenu() throws Exception {
        // Crear un usuario simulado y un restaurante simulado
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setUsuario(usuario);

        // Mockear la sesión y el DAO
        when(restauranteDAO.findByUsuario(any())).thenReturn(restaurante);
        when(itemMenuDAO.findByRestaurante(restaurante)).thenReturn(List.of());

        mockMvc.perform(get("/restaurante/editarMenu")
                .sessionAttr("usuario", usuario)) // Simular usuario en sesión
                .andExpect(status().isOk())
                .andExpect(view().name("editarMenu"));
    }

}