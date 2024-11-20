package es.uclm.reparto.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import es.uclm.reparto.entidades.*;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
  
}