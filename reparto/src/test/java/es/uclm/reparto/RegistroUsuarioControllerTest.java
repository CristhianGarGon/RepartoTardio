package es.uclm.reparto;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import es.uclm.reparto.controladores.RegistroUsuarioController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistroUsuarioController.class)
public class RegistroUsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMostrarSeleccionTipoUsuario() throws Exception {
        mockMvc.perform(get("/seleccionarTipoUsuario"))
               .andExpect(status().isOk())
               .andExpect(view().name("seleccionarTipoUsuario"));
    }
}