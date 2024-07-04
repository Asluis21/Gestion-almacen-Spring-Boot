package com.inventario.rasa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.models.Producto;
import com.inventario.rasa.models.dto.ProductoDTO;
import com.inventario.rasa.models.dto.ProductoRetiroDTO;
import com.inventario.rasa.repository.IProductoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductoService {
    
    private final IProductoRepository productoRepository;

    //CREAR VARIOS PRODUCTOS
    @Transactional
    public List<Producto> crearProductos(List<ProductoDTO> productosDTO){
        List<Producto> productos = new ArrayList<>();
        
        productosDTO.forEach(p -> {
            Producto nuevoProducto = new Producto();
            nuevoProducto.setDescripcion(p.getDescripcion());
            nuevoProducto.setAlmacen(p.getAlmacen());
            nuevoProducto.setCantidad(p.getCantidad());
            nuevoProducto.setCantidadEntrada(p.getCantidad());
            nuevoProducto.setCategoria(p.getCategoria());
            nuevoProducto.setEstado(true);
            nuevoProducto.setEstadoProducto(p.getEstadoProducto());
            nuevoProducto.setPeso(p.getPeso());
            nuevoProducto.setSerie(p.getSerie());
            nuevoProducto.setUbicacion(p.getUbicacion());

            productos.add(nuevoProducto);
        });

        return productoRepository.saveAll(productos);
    }

    @Transactional(readOnly= true)
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }
    
    @Transactional(readOnly= true)
    public List<Producto> listarProductosGreaterThanZero(){
        return productoRepository.findByCantidadGreaterThan(0);
    }
    
    @Transactional
    public Producto restarCantidad(Long idProducto, int cantidadRetiro){

        Producto productoEncontrado = productoRepository.findById(idProducto).orElseThrow(() -> new EmptyResultDataAccessException("No se encontro el producto", 1));
        if(productoEncontrado.getCantidad() - cantidadRetiro < 0) throw new IllegalArgumentException("La cantidad ingresada no debe superar a la cantidad del producto");
        productoEncontrado.setCantidad(productoEncontrado.getCantidad() - cantidadRetiro);

        return productoEncontrado;
    }
    
    @Transactional
    public Producto encontrarProductoById(Long id){

        return productoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Producto no encontrado", 1));
    }


    
    @Transactional
    public Producto editarProducto(Long id, ProductoDTO producto){
        
        Producto productoEncontrado = productoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No se encontro el producto", 1));

        productoEncontrado.setAlmacen(producto.getAlmacen());
        productoEncontrado.setCantidad(producto.getCantidad());
        productoEncontrado.setCategoria(producto.getCategoria());
        productoEncontrado.setDescripcion(producto.getDescripcion());
        productoEncontrado.setEstadoProducto(producto.getEstadoProducto());
        productoEncontrado.setPeso(producto.getPeso());
        productoEncontrado.setSerie(producto.getSerie());
        productoEncontrado.setUbicacion(producto.getUbicacion());

        return productoEncontrado;
    }
}
