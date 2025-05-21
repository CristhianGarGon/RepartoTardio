package es.uclm.reparto;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import es.uclm.reparto.controladores.MenuController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMostrarMenuInicial() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("menuInicial"));
    }

    @Test
    public void testAnonimo() throws Exception {
        mockMvc.perform(get("/anonimo"))
               .andExpect(status().isOk())
               .andExpect(view().name("anonimo"));
    }
}