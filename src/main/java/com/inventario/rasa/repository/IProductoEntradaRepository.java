package com.inventario.rasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.ProductoEntrada;

@Repository
public interface IProductoEntradaRepository extends JpaRepository<ProductoEntrada, Long>{
    
}
