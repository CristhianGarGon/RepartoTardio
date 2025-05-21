package es.uclm.reparto;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import es.uclm.reparto.controladores.RegistroUsuarioController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(RegistroUsuarioController.class)
class RegistroUsuarioControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    void testMostrarSeleccionTipoUsuario() throws Exception {
        mockMvc.perform(get("/seleccionarTipoUsuario"))
                .andExpect(status().isOk())
                .andExpect(view().name("seleccionarTipoUsuario"));
    }

    @Test
    void testProcesarSeleccionCliente() throws Exception {
        mockMvc.perform(post("/seleccionarTipoUsuario")
                .param("tipoUsuario", "CLIENTE"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/registroCliente"));
    }

}
