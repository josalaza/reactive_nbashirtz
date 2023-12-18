package com.example.nbashirtz.services;


import com.example.nbashirtz.models.Original;
import com.example.nbashirtz.repositories.OriginalRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OriginalService {

    private final Logger LOGGER = LoggerFactory.getLogger(OriginalService.class);
    private OriginalRepository originalRepository;

    public Mono<Original> save(Original original) {
        return originalRepository.save(original);
    }

    public Mono<Original> findById(Integer id) {
        return originalRepository.findById(id)
                .onErrorResume(throwable -> handleRepositoryError("Error al consultar la camiseta con id= " + id, throwable))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Camiseta con id= " + id + " no encontrado").getMostSpecificCause()));
    }

    public Flux<Original> findAll() {
        return originalRepository.findAll()
                .onErrorResume(throwable -> handleRepositoryError("Error al consultar todos las camisetas", throwable))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ningún producto encontrado").getMostSpecificCause()));
    }

    public Mono<Void> deleteAll() {
        return originalRepository.deleteAll()
                .onErrorResume(throwable -> handleRepositoryError("Error al borrar todas las camisetas", throwable))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Productos no borrados").getMostSpecificCause()));
    }

    public Mono<Original> deleteById(Integer id) {
        return originalRepository.findById(id)
                .flatMap(producto -> originalRepository.deleteById(producto.getSerial()).thenReturn(producto))
                .onErrorResume(throwable -> handleRepositoryError("Error al borrar una ceamiseta con id= " + id, throwable))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Camisetas con id= " + id + " no borrado").getMostSpecificCause()));
    }


    public Mono<Original> update(Integer id, Original updatedOriginal) {
        return originalRepository.findById(id)
                .flatMap(existingOriginal -> {
                    existingOriginal.setModelo(updatedOriginal.getModelo());
                    existingOriginal.setPrecio(updatedOriginal.getPrecio());
                    return originalRepository.save(existingOriginal);
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Camiseta con id= " + id + " no encontrado para actualizar").getMostSpecificCause()));
    }

    public Mono<Long> count() {
        return originalRepository.count()
                .onErrorResume(throwable -> handleRepositoryError("Error al contar productos", throwable));
    }

    private <T> Mono<T> handleRepositoryError(String errorMessage, Throwable throwable) {
        LOGGER.error(errorMessage, throwable);
        return Mono.empty();
    }
}
