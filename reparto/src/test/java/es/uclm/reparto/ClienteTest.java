package es.uclm.reparto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import es.uclm.reparto.entidades.*;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
    }

    @Test
    public void testSetYGetNombre() {
        cliente.setNombre(null);
        assertEquals(null, cliente.getNombre());
    }

    @Test
    public void testAsignarDireccion() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Calle Mejorada");
        cliente.setDireccion(direccion);
        assertEquals("Calle Mejorada", cliente.getDireccion().getCalle());
    }

    @Test
    public void testAsignarUsuario() {
        Usuario u = new Usuario();
        u.setNickname("Ana123");
        cliente.setUsuario(u);
        assertEquals("Ana123", cliente.getUsuario().getNickname());
    }
    
    @Test
    public void testFavoritosList() {
        List<Restaurante> favoritos = new ArrayList<>();
        Restaurante r1 = new Restaurante(); r1.setNombre("Bar Paco");
        favoritos.add(r1);
        Restaurante r2 = new Restaurante(); r2.setNombre("Bar Pedro");
        favoritos.add(r2);
        cliente.setFavoritosList(favoritos);
        assertEquals(2, cliente.getFavoritosList().size());
        assertEquals("Bar Paco", cliente.getFavoritosList().get(0).getNombre());
        assertEquals("Bar Pedro", cliente.getFavoritosList().get(1).getNombre());
    }
}
