package com.inventario.rasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.OrdenEntrada;

@Repository
public interface IOrdenEntradaRepository extends JpaRepository<OrdenEntrada, Long>{
    
}
