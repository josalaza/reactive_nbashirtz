package com.example.nbashirtz.repositories;

import com.example.nbashirtz.models.Original;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OriginalRepository extends R2dbcRepository <Original, Integer> {
    Flux<Original> findOriginalByEquipoContainsIgnoreCaseOrderByPrecioAsc(String equipo);

}


