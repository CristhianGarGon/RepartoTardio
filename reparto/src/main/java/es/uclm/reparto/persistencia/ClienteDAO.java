package es.uclm.reparto.persistencia;

import es.uclm.reparto.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
	Cliente findByDni(String dni);
}
