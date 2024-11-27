package es.uclm.reparto.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uclm.reparto.entidades.Restaurante;

public interface RestauranteDAO extends JpaRepository<Restaurante, Long> {
}
