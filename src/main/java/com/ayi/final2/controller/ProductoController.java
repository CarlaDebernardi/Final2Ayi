package com.ayi.final2.controller;

import com.ayi.final2.entity.Cliente;
import com.ayi.final2.entity.Producto;
import com.ayi.final2.exception.ClienteNotFoundException;
import com.ayi.final2.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
   public IProductoService productoService;

    @GetMapping("/all")
    public ResponseEntity<List<Producto>> getAll() {
        List<Producto> productos = productoService.listarProductos();
        try {
            if (productos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();}
            return ResponseEntity.ok(productos);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
    }


    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.encontrarProducto(id));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());}
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Producto producto ) {
        try {
            return new ResponseEntity(productoService.crearProducto(producto), HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El producto que desea ingresar ya se encuentra en la base de datos");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Producto producto) {
        try {
            return new ResponseEntity(productoService.modificarCliente(id, producto), HttpStatus.OK);
        } catch (ClienteNotFoundException clienteNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El código PLU ingresado no corresponde a un producto registrado.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam Integer id) {
        try {
            if (id == null || id.describeConstable().isEmpty()) {
                throw new IllegalArgumentException("El código PLU no puede estat vacío.");}

            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
