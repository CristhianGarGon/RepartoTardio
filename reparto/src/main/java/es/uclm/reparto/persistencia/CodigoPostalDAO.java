package es.uclm.reparto.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uclm.reparto.entidades.CodigoPostal;

public interface CodigoPostalDAO extends JpaRepository<CodigoPostal, Long> {
    CodigoPostal findByCodigo(String codigo);
    
    
}
