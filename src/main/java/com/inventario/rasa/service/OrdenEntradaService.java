package com.inventario.rasa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.auth.UsuarioDTO;
import com.inventario.rasa.models.OrdenEntrada;
import com.inventario.rasa.models.Usuario;
import com.inventario.rasa.models.dto.OrdenEntradaDTO;
import com.inventario.rasa.repository.IOrdenEntradaRepository;
import com.inventario.rasa.repository.IUsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrdenEntradaService {
    
    private final IOrdenEntradaRepository ordenEntradaRepository;
    private final IUsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<OrdenEntradaDTO> listarOrdenes(){

        List<OrdenEntradaDTO> ordenEntradaDTO = new ArrayList<>();

        ordenEntradaRepository.findAll().forEach(o -> {
            OrdenEntradaDTO orden = new OrdenEntradaDTO();

            orden.setId(o.getId());
            orden.setFechaEntrada(o.getFechaEntrada().toString().substring(0, 10));
            orden.setObservacion(o.getObservacion());
            orden.setProductos(o.getProductos());
            orden.setProveedor(o.getProveedor());
            orden.setPuntoLlegada(o.getPuntoLlegada());
            orden.setUsuario(UsuarioDTO.usuarioAusuarioDto(o.getUsuario()));

            ordenEntradaDTO.add(orden);
        });

        return ordenEntradaDTO;
    }

    @Transactional(readOnly = true)
    public OrdenEntradaDTO encontrarOrdenEntradaById(Long id){
        OrdenEntrada ordenEntrada = ordenEntradaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Orden de entrada no encontrada", 1));
        OrdenEntradaDTO ordenEntradaDTO = new OrdenEntradaDTO();
        
        ordenEntradaDTO.setId(ordenEntrada.getId());
        ordenEntradaDTO.setFechaEntrada(ordenEntrada.getFechaEntrada().toString().substring(0, 10));
        ordenEntradaDTO.setObservacion(ordenEntrada.getObservacion());
        ordenEntradaDTO.setProductos(ordenEntrada.getProductos());
        ordenEntradaDTO.setPuntoLlegada(ordenEntrada.getPuntoLlegada());
        ordenEntradaDTO.setUsuario(UsuarioDTO.usuarioAusuarioDto(ordenEntrada.getUsuario()));
        ordenEntradaDTO.setProveedor(ordenEntrada.getProveedor());

        return ordenEntradaDTO;
    }

    @Transactional
    public OrdenEntrada crearOrdenEntrada(OrdenEntradaDTO ordenEntradaDTO){

        OrdenEntrada ordenEntrada = new OrdenEntrada();
        ordenEntrada.setObservacion(ordenEntradaDTO.getObservacion());
        ordenEntrada.setProveedor(ordenEntradaDTO.getProveedor());



        ordenEntrada.setProductos(ordenEntradaDTO.getProductos());
        ordenEntrada.setPuntoLlegada(ordenEntradaDTO.getPuntoLlegada());

        Usuario usuarioEncontrado = usuarioRepository.findByUsername(ordenEntradaDTO.getUsuario().getUsername()).orElseThrow(() -> new EmptyResultDataAccessException("Usuario no encontrado", 1));

        ordenEntrada.setUsuario(usuarioEncontrado);
        ordenEntrada.setEstado(true);

        return ordenEntradaRepository.save(ordenEntrada);
    }

    @Transactional
    public OrdenEntrada editarOrdenEntrada(Long id, OrdenEntradaDTO ordenEntrada){
        OrdenEntrada ordenEntradaEncontrada = ordenEntradaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Orden de entrada no encontrada", 0));

        ordenEntradaEncontrada.setObservacion(ordenEntrada.getObservacion());
        ordenEntradaEncontrada.setProveedor(ordenEntrada.getProveedor());

        Usuario usuarioEncontrado = usuarioRepository.findById(ordenEntrada.getUsuario().getId()).orElseThrow(() -> new EmptyResultDataAccessException("Usuario no encontrado", 1));

        ordenEntradaEncontrada.setUsuario(usuarioEncontrado);
        ordenEntradaEncontrada.setPuntoLlegada(ordenEntrada.getPuntoLlegada());

        return ordenEntradaEncontrada;
    }


}
