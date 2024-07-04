package com.inventario.rasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.OrdenSalida;

@Repository
public interface IOrdenSalidaRepository extends JpaRepository<OrdenSalida, Long> {
    
}
