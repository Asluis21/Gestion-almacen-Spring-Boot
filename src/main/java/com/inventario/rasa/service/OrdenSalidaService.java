package com.inventario.rasa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.auth.UsuarioDTO;
import com.inventario.rasa.models.OrdenSalida;
import com.inventario.rasa.models.Producto;
import com.inventario.rasa.models.ProductoRetiro;
import com.inventario.rasa.models.Usuario;
import com.inventario.rasa.models.dto.OrdenSalidaDTO;
import com.inventario.rasa.models.dto.ProductoDTO;
import com.inventario.rasa.repository.IOrdenSalidaRepository;
import com.inventario.rasa.repository.IProductoRetiroRepository;
import com.inventario.rasa.repository.IUsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrdenSalidaService {
    
    private final IOrdenSalidaRepository ordenSalidaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IProductoRetiroRepository productoRetiroRepository;
    private final ProductoService productoService;

    @Transactional(readOnly = true)
    public List<OrdenSalidaDTO> listarOrdenes(){

        List<OrdenSalidaDTO> ordenSalidaDTO = new ArrayList<>();

        ordenSalidaRepository.findAll().forEach(o -> {
            OrdenSalidaDTO orden = new OrdenSalidaDTO();

            orden.setId(o.getId());
            orden.setFechaSalida(o.getFechaSalida().toString().substring(0, 10));

            orden.setObservacion(o.getObservacion());

            // List<ProductoDTO> productos = new ArrayList<>();
        
            // o.getProductos().forEach(p -> {
            //     ProductoDTO producto = new ProductoDTO();
            //     producto.setDescripcion(p.getDescripcion());
            //     producto.setCantidad(p.getCantidad());
            //     producto.setCategoria(p.getCategoria());
            //     producto.setAlmacen(p.getAlmacen());
            //     producto.setEstado(true);
            //     producto.setEstadoProducto(p.getEstadoProducto());
            //     producto.setPeso(p.getPeso());
            //     producto.setSerie(p.getSerie());
            //     producto.setUbicacion(p.getUbicacion());

            //     productos.add(producto);
            // });

            // orden.setProductos(productos);

            orden.setDestino(o.getDestino());
            orden.setUsuario(UsuarioDTO.usuarioAusuarioDto(o.getUsuario()));

            ordenSalidaDTO.add(orden);
        });

        return ordenSalidaDTO;
    }
    
    @Transactional(readOnly = true)
    public OrdenSalidaDTO encontrarOrdenSalidaById(Long id){
        OrdenSalida ordenSalida = ordenSalidaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Orden de salida no encontrada", 1));
        OrdenSalidaDTO ordenSalidaDTO = new OrdenSalidaDTO();
        
        ordenSalidaDTO.setId(ordenSalida.getId());
        ordenSalidaDTO.setFechaSalida(ordenSalida.getFechaSalida().toString().substring(0, 10));

        ordenSalidaDTO.setObservacion(ordenSalida.getObservacion());

        List<ProductoDTO> productos = new ArrayList<>();
        ordenSalida.getProductos().forEach(p -> {
            ProductoDTO producto = new ProductoDTO();
            producto.setDescripcion(p.getDescripcion());
            producto.setCantidad(p.getCantidad());
            producto.setCategoria(p.getCategoria());
            producto.setAlmacen(p.getAlmacen());
            producto.setEstado(true);
            producto.setEstadoProducto(p.getEstadoProducto());
            producto.setPeso(p.getPeso());
            producto.setSerie(p.getSerie());
            producto.setUbicacion(p.getUbicacion());

            productos.add(producto);
        });

        ordenSalidaDTO.setProductos(productos);
        ordenSalidaDTO.setDestino(ordenSalida.getDestino());
        ordenSalidaDTO.setUsuario(UsuarioDTO.usuarioAusuarioDto(ordenSalida.getUsuario()));

        return ordenSalidaDTO;
    }

    @Transactional
    public OrdenSalida crearOrdenSalida(OrdenSalidaDTO ordenSalidaDTO){

        List<ProductoRetiro> productosRetiros = new ArrayList<>();

        ordenSalidaDTO.getProductos().forEach(p -> {
            ProductoRetiro productoRetiro = new ProductoRetiro();
            productoRetiro.setDescripcion(p.getDescripcion());

            productoService.restarCantidad(p.getId(), p.getUltimaCantidadRetirada());

            productoRetiro.setCantidad(p.getUltimaCantidadRetirada());
            productoRetiro.setCategoria(p.getCategoria());
            productoRetiro.setAlmacen(p.getAlmacen());
            productoRetiro.setEstado(true);
            productoRetiro.setEstadoProducto(p.getEstadoProducto());
            productoRetiro.setPeso(p.getPeso());
            productoRetiro.setSerie(p.getSerie());
            productoRetiro.setUbicacion(p.getUbicacion());

            productosRetiros.add(productoRetiroRepository.save(productoRetiro));
        });

        OrdenSalida ordenEntrada = new OrdenSalida();
        ordenEntrada.setObservacion(ordenSalidaDTO.getObservacion());
        ordenEntrada.setProductos(productosRetiros);
        ordenEntrada.setDestino(ordenSalidaDTO.getDestino());

        Usuario usuarioEncontrado = usuarioRepository.findByUsername(ordenSalidaDTO.getUsuario().getUsername()).orElseThrow(() -> new EmptyResultDataAccessException("Usuario no encontrado", 1));

        ordenEntrada.setUsuario(usuarioEncontrado);
        ordenEntrada.setEstado(true);

        return ordenSalidaRepository.save(ordenEntrada);
    }

    @Transactional
    public OrdenSalida editarOrdenSalida(Long id, OrdenSalidaDTO ordenEntrada){
        OrdenSalida ordenSalidaEncontrada = ordenSalidaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Orden de salida no encontrada", 0));

        ordenSalidaEncontrada.setObservacion(ordenEntrada.getObservacion());

        Usuario usuarioEncontrado = usuarioRepository.findById(ordenEntrada.getUsuario().getId()).orElseThrow(() -> new EmptyResultDataAccessException("Usuario no encontrado", 1));

        ordenSalidaEncontrada.setUsuario(usuarioEncontrado);
        ordenSalidaEncontrada.setDestino(ordenEntrada.getDestino());

        return ordenSalidaEncontrada;
    }
}
