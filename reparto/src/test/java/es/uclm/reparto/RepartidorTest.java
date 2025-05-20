package es.uclm.reparto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import es.uclm.reparto.entidades.*;

public class RepartidorTest {

    private Repartidor repartidor;

    @BeforeEach
    public void setUp() {
        repartidor = new Repartidor();
    }

    @Test
    public void testNombreYApellidos() {
        repartidor.setNombre(null);
        repartidor.setApellidos(null);
        assertEquals(null, repartidor.getNombre());
        assertEquals(null, repartidor.getApellidos());
    }
    
    @Test
    public void testNif() {
        repartidor.setNif(null);
        assertEquals(null, repartidor.getNif());
    }


    @Test
    public void testEficiencia() {
        repartidor.setEficiencia(-1);
        assertEquals(-1, repartidor.getEficiencia());
    }

    @Test
    public void testZonas() {
        List<CodigoPostal> zonas = new ArrayList<>();
        zonas.add(new CodigoPostal("45622"));
        zonas.add(new CodigoPostal("45600"));
        repartidor.setZonas(zonas);
        assertEquals(2, repartidor.getZonas().size());
    }

    @Test
    public void testUsuario() {
        Usuario u = new Usuario(); u.setNickname("PacoRep");
        repartidor.setUsuario(u);
        assertEquals("PacoRep", repartidor.getUsuario().getNickname());
    }
    
}
