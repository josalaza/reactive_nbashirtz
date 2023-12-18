package com.example.nbashirtz.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Clientes {

    @Id
    private String identificacion;
    private String nombre;
    private DetalleEntrega datosEntrega;

}