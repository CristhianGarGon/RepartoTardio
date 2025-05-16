package es.uclm.reparto.persistencia;

import es.uclm.reparto.entidades.Repartidor;
import es.uclm.reparto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepartidorDAO extends JpaRepository<Repartidor, Long> {
    Repartidor findByUsuario(Usuario usuario);
}
