package com.example.nbashirtz.controllers;

import com.example.nbashirtz.models.Original;
import com.example.nbashirtz.services.OriginalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/original")
@AllArgsConstructor

public class OriginalController {

    OriginalService originalService;

    @PostMapping("/")
    public  Mono<Original> crearCamisetaOriginal(@RequestBody Original original){
        return originalService.save(original);
    }

    @GetMapping("/")
    public Flux<Original> consultarTodosLosProductos(){
        return originalService.findAll();
    }

    @PutMapping("/{id}")
    public Mono<Original> actualizarProducto(@PathVariable Integer id, @RequestBody Original original) {
        return originalService.update(id, original);
    }

    @DeleteMapping("/{id}")
    public Mono<Original> eliminarProducto(@PathVariable Integer id) {
        return originalService.deleteById(id) ;
    }
}
