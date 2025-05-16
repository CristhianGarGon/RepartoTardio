package es.uclm.reparto.persistencia;

import es.uclm.reparto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findByNickname(String nickname);
}
