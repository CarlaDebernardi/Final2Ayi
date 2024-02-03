package com.ayi.final2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name="productos")
public class Producto implements Serializable{
    private static final Long serialVersionUID = 1L;
    @Id
    @NotNull
    @PositiveOrZero(message = "Debe ingresar un número positivo entero, a partir de 0")
    @Max( value=5, message="Debe contener 5 dígitos")
    @Min( value=5, message="Debe contener 5 dígitos")
    @Column(name = "id_producto")
    private Integer plu;
    @Column(name = "codigo_ean")
    @NotEmpty
    @Size (min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    private String codigoEan;
    @NotEmpty
    @Size (min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    @Column(name = "nombre_producto")
    private String nombre;
    @Column(name = "descripcion_producto")
    @NotEmpty
    @Size (min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    private String descripcion;
    @Column(name = "marca")
    @NotEmpty
    @Size (min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    private String marca;
    @Column(name = "tipo")
    @NotEmpty
    @Size (min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    private String tipo;
    @Column (name="precio")
    @NotNull
    @PositiveOrZero(message = "Debe ingresar un número positivo, a partir de 0")
    private Double precio;
    @Column(name = "stock")
    @NotNull
    @PositiveOrZero(message = "Debe ingresar un número positivo entero, a partir de 0")
    @Max( value=2000)
    private Integer stock;
}

