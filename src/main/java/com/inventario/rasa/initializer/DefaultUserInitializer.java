package com.inventario.rasa.initializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.inventario.rasa.auth.AuthService;
import com.inventario.rasa.models.Almacen;
import com.inventario.rasa.models.Categoria;
import com.inventario.rasa.models.Producto;
import com.inventario.rasa.models.Proveedor;
import com.inventario.rasa.models.Rol;
import com.inventario.rasa.models.dto.AlmacenDTO;
import com.inventario.rasa.models.dto.OrdenEntradaDTO;
import com.inventario.rasa.models.dto.OrdenSalidaDTO;
import com.inventario.rasa.models.dto.ProductoDTO;
import com.inventario.rasa.models.dto.RolDTO;
import com.inventario.rasa.models.dto.UsuarioRequestDTO;
import com.inventario.rasa.service.AlmacenService;
import com.inventario.rasa.service.CategoriaService;
import com.inventario.rasa.service.OrdenEntradaService;
import com.inventario.rasa.service.OrdenSalidaService;
import com.inventario.rasa.service.ProductoService;
import com.inventario.rasa.service.ProveedorService;
import com.inventario.rasa.service.RolService;
import com.inventario.rasa.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DefaultUserInitializer implements CommandLineRunner {

    private final AuthService authService;
    private final RolService rolService;
    private final CategoriaService categoriaService;
    private final ProveedorService proveedorService;
    private final AlmacenService almacenService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final OrdenEntradaService ordenEntradaService;
    private final OrdenSalidaService ordenSalidaService;

    @Override
    public void run(String... args) throws Exception {
        
        //CATEGORIA
        // Categoria categoria = new Categoria();
        // categoria.setNombre("solidos");
        // categoria.setEstado(true);
        
        // Categoria categoria2 = new Categoria();
        // categoria2.setNombre("liquido");
        // categoria2.setEstado(true);
        
        // Categoria categoria3 = new Categoria();
        // categoria3.setNombre("gases");
        // categoria3.setEstado(true);

        // categoriaService.crearCategoria(categoria);
        // categoriaService.crearCategoria(categoria2);
        // categoriaService.crearCategoria(categoria3);

        // categoriaService.eliminarCategoriaByIdLogico(3L);

        //PROVEEDOR
        // Proveedor proveedor = new Proveedor();
        // proveedor.setNombre("LG");
        // proveedor.setRuc("20287310291");
        // proveedor.setEstado(true);
        
        // Proveedor proveedor2 = new Proveedor();
        // proveedor2.setNombre("Adidas");
        // proveedor2.setRuc("20929210291");
        // proveedor2.setEstado(true);

        // Proveedor proveedor3 = new Proveedor();
        // proveedor3.setNombre("Sol");
        // proveedor3.setRuc("20289110911");
        // proveedor3.setEstado(true);


        // proveedorService.crearProveedor(proveedor);
        // proveedorService.crearProveedor(proveedor2);
        // proveedorService.crearProveedor(proveedor3);

        // proveedorService.eliminarProveedorPorIdLogico(2L);

        // ROLES 
        // Rol rolOperario = new Rol();
        // rolOperario.setNombre("OPERARIO");
        // rolOperario.setEstado(true);

        // Rol rolJefe = new Rol();
        // rolJefe.setNombre("JEFE");
        // rolJefe.setEstado(true);

        // Rol rolAuxiliar = new Rol();
        // rolAuxiliar.setNombre("AUXILIAR");
        // rolAuxiliar.setEstado(true);

        
        // rolService.crearRol(rolJefe);
        // rolService.crearRol(rolOperario);
        // rolService.crearRol(rolAuxiliar);

        // rolService.eliminarRolByIdLogico(3L);
        
        // ALMACENES
        // Almacen almacen = new Almacen();
        // almacen.setNombre("Almacen principal");
        // almacen.setUbicacion("Av. Argentina #293");
        // almacen.setEstado(true);
        
        // Almacen almacen2 = new Almacen();
        // almacen2.setNombre("Almacen secundario");
        // almacen2.setUbicacion("Av. Peru #102");
        // almacen2.setEstado(true);

        // almacenService.crearAlmacen(almacen);
        // almacenService.crearAlmacen(almacen2);
        
        // almacenService.eliminarAlmacenByIdLogico(2L);

        // USUARIOS
        // UsuarioRequestDTO registerRequest = new UsuarioRequestDTO();
        // registerRequest.setApellidoPaterno("Alvarado");
        // registerRequest.setApellidoMaterno("Sanchez");
        // registerRequest.setCorreo("luis@gmail.com");
        // registerRequest.setDni("12345678");
        // registerRequest.setNombre("Luis");
        // registerRequest.setUsername("luis12");
        // registerRequest.setPassword("12345");
        // registerRequest.setEstado(true);
        

        // registerRequest.setRol(rolService.encontraRolById(1L));

        // authService.register(registerRequest);

        // PRODUCTOS
        // ProductoDTO producto = new ProductoDTO();
        // producto.setAlmacen(almacenService.encontrarAlmacenById(1L));
        // producto.setCantidad(30);
        // producto.setCategoria(categoriaService.encontrarCategoriaById(1L));
        // producto.setDescripcion("Lavadora M10");
        // producto.setEstadoProducto(true);
        // producto.setPeso("40kg");
        // producto.setSerie("series 1000");
        // producto.setUbicacion("zona 2, pasillo 1");
        
        // ProductoDTO producto2 = new ProductoDTO();
        // producto2.setAlmacen(almacenService.encontrarAlmacenById(1L));
        // producto2.setCantidad(50);
        // producto2.setCategoria(categoriaService.encontrarCategoriaById(1L));
        // producto2.setDescripcion("Televisor LCD");
        // producto2.setEstadoProducto(true);
        // producto2.setPeso("5kg");
        // producto.setSerie("series 1000");
        // producto2.setUbicacion("zona 3, pasillo 1");

        // List<ProductoDTO> productos = new ArrayList<>();
        // productos.add(producto);
        // productos.add(producto2);

        // ORDEN ENTRADA
        // OrdenEntradaDTO ordenEntradaDTO = new OrdenEntradaDTO();
        // ordenEntradaDTO.setProductos(productoService.crearProductos(productos));
        // ordenEntradaDTO.setProveedor(proveedorService.encontrarProveedorById(1L));
        // ordenEntradaDTO.setPuntoLlegada("Av. Duarez #123");
        // ordenEntradaDTO.setUsuario(usuarioService.encontrarUsuarioById(1L));
        
        // ordenEntradaService.crearOrdenEntrada(ordenEntradaDTO);


        // ORDEN SALIDA
        // OrdenSalidaDTO ordenSalidaDTO = new OrdenSalidaDTO();
        // ordenSalidaDTO.setDestino("Av. Abancay #12");
        // ordenSalidaDTO.setUsuario(usuarioService.encontrarUsuarioById(1L));
        // ordenSalidaDTO.setObservacion("");

        // productosArray.add(productoService.encontrarProductoById(1L));
        
        // List<ProductoDTO> productosArray = new ArrayList<>();

        // Producto p = productoService.encontrarProductoById(1L);
        // ProductoDTO productoDTO1 = new ProductoDTO();
        //         productoDTO1.setId(1L);
        //         productoDTO1.setDescripcion(p.getDescripcion());
        //         productoDTO1.setCantidad(p.getCantidad());
        //         productoDTO1.setCategoria(p.getCategoria());
        //         productoDTO1.setAlmacen(p.getAlmacen());
        //         productoDTO1.setUltimaCantidadRetirada(5);
        //         productoDTO1.setEstado(true);
        //         productoDTO1.setEstadoProducto(p.getEstadoProducto());
        //         productoDTO1.setPeso(p.getPeso());
        //         productoDTO1.setSerie(p.getSerie());
        //         productoDTO1.setUbicacion(p.getUbicacion());
        // productosArray.add(productoDTO1);

        // ordenSalidaDTO.setProductos(productosArray);

        // ordenSalidaDTO.setProductos(productosArray);

        // ordenSalidaService.crearOrdenSalida(ordenSalidaDTO);
    }
    
    

}
