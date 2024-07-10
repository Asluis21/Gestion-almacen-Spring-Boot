package com.inventario.rasa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.models.Categoria;
import com.inventario.rasa.models.Producto;
import com.inventario.rasa.models.ProductoEntrada;
import com.inventario.rasa.models.dto.ProductoDTO;
import com.inventario.rasa.models.dto.ProductoRetiroDTO;
import com.inventario.rasa.repository.IProductoEntradaRepository;
import com.inventario.rasa.repository.IProductoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductoService {
    
    private final IProductoRepository productoRepository;
    private final IProductoEntradaRepository productoEntradaRepository;
    private final CategoriaService categoriaService;

    //CREAR VARIOS PRODUCTOS
    @Transactional
    public List<ProductoEntrada> crearProductos(List<ProductoDTO> productosDTO){
        
        List<ProductoEntrada> productos = new ArrayList<>();
        
        productosDTO.forEach(p -> {

            Optional<Producto> productoOptional = productoRepository.findByDescripcionAndSerieAndAlmacen(p.getDescripcion(), p.getSerie(), p.getAlmacen());

            if(productoOptional.isPresent()){
                Producto productoEncontrado = productoOptional.get();
                productoEncontrado.setCantidad(productoEncontrado.getCantidad() + p.getCantidad());
                productoEncontrado.setUbicacion(p.getUbicacion());
                productoEncontrado.setEstadoProducto(p.getEstadoProducto());

            }else{
                Producto nuevoProducto = new Producto();
                nuevoProducto.setDescripcion(p.getDescripcion());
                nuevoProducto.setAlmacen(p.getAlmacen());
                nuevoProducto.setCantidad(p.getCantidad());
                nuevoProducto.setCategoria(p.getCategoria());
                nuevoProducto.setEstado(true);
                nuevoProducto.setEstadoProducto(p.getEstadoProducto());
                nuevoProducto.setPeso(p.getPeso());
                nuevoProducto.setSerie(p.getSerie());
                nuevoProducto.setUbicacion(p.getUbicacion());


                productoRepository.save(nuevoProducto);
            }
            
            ProductoEntrada nuevoProducto = new ProductoEntrada();
            nuevoProducto.setDescripcion(p.getDescripcion());
            nuevoProducto.setAlmacen(p.getAlmacen());
            nuevoProducto.setCantidad(p.getCantidad());
            nuevoProducto.setCategoria(p.getCategoria());
            nuevoProducto.setEstado(true);
            nuevoProducto.setEstadoProducto(p.getEstadoProducto());
            nuevoProducto.setPeso(p.getPeso());
            nuevoProducto.setSerie(p.getSerie());
            nuevoProducto.setUbicacion(p.getUbicacion());

            productos.add(nuevoProducto);


        });

        return productoEntradaRepository.saveAll(productos);
    }

    @Transactional(readOnly= true)
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }
    
    @Transactional(readOnly= true)
    public List<Producto> listarProductosByDescripcion(String descripcion){
        return productoRepository.findByDescripcionStartsWith(descripcion);
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

        Categoria categoriaEncontrada = categoriaService.encontrarCategoriaById(producto.getCategoria().getId());
        productoEncontrado.setCategoria(categoriaEncontrada);

        productoEncontrado.setDescripcion(producto.getDescripcion());
        productoEncontrado.setEstadoProducto(producto.getEstadoProducto());
        productoEncontrado.setPeso(producto.getPeso());
        productoEncontrado.setSerie(producto.getSerie());
        productoEncontrado.setUbicacion(producto.getUbicacion());

        return productoEncontrado;
    }
}
