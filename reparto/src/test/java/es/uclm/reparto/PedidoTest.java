package es.uclm.reparto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import es.uclm.reparto.entidades.Cliente;
import es.uclm.reparto.entidades.Direccion;
import es.uclm.reparto.entidades.ItemMenu;
import es.uclm.reparto.entidades.Pedido;
import es.uclm.reparto.entidades.Repartidor;
import es.uclm.reparto.entidades.Restaurante;

public class PedidoTest {

    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido();
    }

    @Test
    public void testClienteYRestaurante() {
        Cliente c = new Cliente(); c.setNombre(null);
        Restaurante r = new Restaurante(); r.setNombre(null);
        pedido.setCliente(c);
        pedido.setRestaurante(r);
        assertEquals(null, pedido.getCliente().getNombre());
        assertEquals(null, pedido.getRestaurante().getNombre());
    }
    
    @Test
    public void testRepartidorAsignado() {
        Repartidor repartidor = new Repartidor();
        repartidor.setNombre(null);
        pedido.setRepartidor(repartidor);

        assertNotNull(pedido.getRepartidor());
        assertEquals(null, pedido.getRepartidor().getNombre());
    }


    @Test
    public void testTotalYEntregado() {
        pedido.setTotal(0);
        pedido.setEntregado(false);
        assertEquals(0, pedido.getTotal(), 0.001);
        assertFalse(pedido.isEntregado());
    }

    @Test
    public void testItemsPedido() {
        List<ItemMenu> items = new ArrayList<>();
        pedido.setItems(items);
        assertNotNull(pedido.getItems());
        assertTrue(pedido.getItems().isEmpty());
    }

    @Test
    public void testDireccionEntrega() {
        Direccion d = new Direccion();
        d.setCalle("Gran Vía");
        d.setNumero("12");
        pedido.setDireccionEntrega(d);

        assertNotNull(pedido.getDireccionEntrega());
        assertEquals("Gran Vía", pedido.getDireccionEntrega().getCalle());
    }
    
    @Test
    public void testEstadoRecogido() {
        pedido.setRecogido(false);
        assertFalse(pedido.isRecogido());
    }

    
    
}
