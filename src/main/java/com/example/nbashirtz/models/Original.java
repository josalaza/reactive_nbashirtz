package com.example.nbashirtz.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor

public class Original {

    @Id
    private Integer serial;
    private String modelo;
    private String marca;
    private Float precio;
    private String equipo;

}
