package es.uclm.reparto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import es.uclm.reparto.entidades.*;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testSetYGetNombre() {
        restaurante.setNombre(null);
        assertEquals(null, restaurante.getNombre());
    }

    @Test
    public void testAsignarDireccion() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Centro");
        restaurante.setDireccion(direccion);
        assertEquals("Centro", restaurante.getDireccion().getCalle());
    }

    @Test
    public void testAsignarUsuario() {
        Usuario u = new Usuario();
        u.setNickname("tacobar");
        restaurante.setUsuario(u);
        assertEquals("tacobar", restaurante.getUsuario().getNickname());
    }
    
    @Test
    public void testMenuItems() {
        ItemMenu item = new ItemMenu();
        item.setNombre("Ramen");
        List<ItemMenu> menu = new ArrayList<>();
        menu.add(item);
        restaurante.setMenu(menu);
        assertEquals(1, restaurante.getMenu().size());
        assertEquals("Ramen", restaurante.getMenu().get(0).getNombre());
    }
}
