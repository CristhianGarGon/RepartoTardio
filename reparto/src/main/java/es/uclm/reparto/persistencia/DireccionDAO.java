package es.uclm.reparto.persistencia;

import es.uclm.reparto.entidades.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionDAO extends JpaRepository<Direccion, Long> {
}
