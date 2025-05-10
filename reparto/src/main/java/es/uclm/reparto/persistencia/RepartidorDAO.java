package es.uclm.reparto.persistencia;

import es.uclm.reparto.entidades.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepartidorDAO extends JpaRepository<Repartidor, Long> {
    // Métodos adicionales si necesitas funcionalidades específicas
}
