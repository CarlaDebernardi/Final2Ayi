package com.ayi.final2.service;

import com.ayi.final2.entity.Producto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductoService {
    @Transactional(readOnly = true)
    List<Producto> listarProductos();

    @Transactional
    void eliminar(Integer id);

    @Transactional
    Producto crearProducto(Producto producto);

    @Transactional
    Producto encontrarProducto(Integer id);


    @Transactional
    Producto modificarProducto(Integer id, Producto producto);
}
