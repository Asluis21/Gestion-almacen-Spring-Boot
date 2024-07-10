package com.inventario.rasa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.Almacen;
import com.inventario.rasa.models.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByCantidadGreaterThan(Integer cantidad);

    List<Producto> findByDescripcionStartsWith(String descripcion);

    Optional<Producto> findByDescripcionAndSerieAndAlmacen(String descripcion, String serie, Almacen almacen);
}
