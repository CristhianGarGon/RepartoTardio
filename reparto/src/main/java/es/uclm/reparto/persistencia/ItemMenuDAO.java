package es.uclm.reparto.persistencia;

import es.uclm.reparto.entidades.ItemMenu;
import es.uclm.reparto.entidades.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemMenuDAO extends JpaRepository<ItemMenu, Long> {
    List<ItemMenu> findByRestaurante(Restaurante restaurante);
}