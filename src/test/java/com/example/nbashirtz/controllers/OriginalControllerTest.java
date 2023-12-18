package com.example.nbashirtz.controllers;

import com.example.nbashirtz.models.Original;
import com.example.nbashirtz.services.OriginalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OriginalControllerTest {

    @Mock
    private OriginalService originalService;

    @InjectMocks
    private OriginalController originalController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsultarProductosExitoso(){
        Original productoEsperado = new Original(512312, "1965", "nike", 34233F, "san antonio spurs");
        Original productoEsperado2 = new Original(61231221, "1998", "adidas", 4200F, "miami heat");
        Flux<Original> productosEsperados = Flux.just(productoEsperado, productoEsperado2);
        when(originalService.findAll()).thenReturn(productosEsperados);

        Flux<Original> resultado = originalController.consultarTodosLosProductos();
        resultado.subscribe();
        assertEquals(productosEsperados, resultado);
    }

    @Test
    void testActualizarProductoExitoso() {
        Integer idProducto = 123;
        Original productoEsperado = new Original(idProducto, "2023", "Nike", 99.99F, "Equipo");
        when(originalService.update(idProducto, productoEsperado)).thenReturn(Mono.just(productoEsperado));

        Mono<Original> resultado = originalController.actualizarProducto(idProducto, productoEsperado);

        assertEquals(productoEsperado, resultado.block());
    }

    @Test
    void testEliminarProductoExitoso() {
        Integer idProducto = 456;
        Original productoEsperado = new Original(idProducto, "2023", "Adidas", 79.99F, "Otro Equipo");
        when(originalService.deleteById(idProducto)).thenReturn(Mono.just(productoEsperado));

        Mono<Original> resultado = originalController.eliminarProducto(idProducto);

        assertEquals(productoEsperado, resultado.block());
    }


}