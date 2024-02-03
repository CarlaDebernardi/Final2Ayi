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

@Table(name="clientes")
public class Cliente implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;
    @NotEmpty
    @Size(min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    @Column(name = "nombre_cliente")
    private String nombre;
    @Column(name = "apellido_cliente")
    @NotEmpty
    @Size (min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    private String apellido;
    @Column(name = "fecha_ingreso")
    @NotEmpty
    @Size (min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    private String fechaDeIngreso;
    @Column(name = "telefono")
    @NotEmpty
    @Size (min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    private String telefono;
    @Column(name = "domicilio")
    @NotEmpty
    @Size (min=2, max=20, message = "Debe contener entre dos y 20 caracteres")
    private String domicilio;
}

