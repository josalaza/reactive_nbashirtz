package com.example.nbashirtz.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name = "detalle_entrega")

public class DetalleEntrega {

    @Id
    private Integer identificacion;
    private String ciudad;
    private String codigoPostal;
    private String direccion;
    private String telefono;


}