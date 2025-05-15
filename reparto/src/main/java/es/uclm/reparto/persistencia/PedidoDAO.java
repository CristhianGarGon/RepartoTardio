package es.uclm.reparto.persistencia;

import es.uclm.reparto.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {
}
