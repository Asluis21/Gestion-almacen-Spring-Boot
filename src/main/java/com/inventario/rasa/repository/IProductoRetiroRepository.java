package com.inventario.rasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.ProductoRetiro;

@Repository
public interface IProductoRetiroRepository extends JpaRepository<ProductoRetiro, Long>{
    
}
