package com.inventario.rasa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByCantidadGreaterThan(Integer cantidad);
}
