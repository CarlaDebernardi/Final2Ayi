package com.ayi.final2.service.implementation;
import com.ayi.final2.entity.Producto;
import com.ayi.final2.exception.ProductoNotFoundException;
import com.ayi.final2.repository.IProductoRepository;
import com.ayi.final2.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    IProductoRepository productoRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        Producto producto = productoRepository.getReferenceById(id);
        productoRepository.delete(producto);
    }


    @Override
    @Transactional
    public  Producto crearProducto(Producto producto) {
        productoRepository.save(producto);
        return producto;
    }

    @Override
    @Transactional
    public Producto encontrarProducto(Integer id) {
        try {
            Optional<Producto> entityOptional = productoRepository.findById(id);
            return entityOptional.get();
        } catch (ProductoNotFoundException e) {
            throw new ProductoNotFoundException("El código ingresado no correspondo a ningún producto registrado.");
        }
    }

    @Override
    @Transactional
    public Producto modificarCliente(Integer id, Producto producto){
        try {
            Optional <Producto> optionalProducto = Optional.ofNullable(productoRepository.getReferenceById(id));
            Producto productoUpdate = optionalProducto.get();
            if(producto.getNombre() != null){
                productoUpdate.setNombre(producto.getNombre());
            }
            if(producto.getCodigoEan() != null){
                productoUpdate.setCodigoEan(producto.getCodigoEan());
            }
            if(producto.getMarca() != null){
                productoUpdate.setMarca(producto.getMarca());
            }
            if(producto.getTipo() != null){
                productoUpdate.setTipo(producto.getTipo());
            }
            if(producto.getPrecio() != null){
                productoUpdate.setPrecio(producto.getPrecio());
            }

            if(producto.getStock() != null){
                productoUpdate.setStock(producto.getStock());
            }
            return productoRepository.save(productoUpdate);
        } catch (ProductoNotFoundException e){
            throw new ProductoNotFoundException("El código ingresado no correspondo a ningún producto registrado.");
        }
    }




}
