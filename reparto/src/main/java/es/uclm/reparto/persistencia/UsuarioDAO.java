package es.uclm.reparto.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import es.uclm.Reparto.business.entity.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
  
}
