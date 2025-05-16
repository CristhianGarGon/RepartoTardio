package es.uclm.reparto.persistencia;

import es.uclm.reparto.entidades.Restaurante;
import es.uclm.reparto.entidades.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteDAO extends JpaRepository<Restaurante, Long> {
    Restaurante findByUsuario(Usuario usuario);
    List<Restaurante> findByNombreContainingIgnoreCase(String filtro);
}	