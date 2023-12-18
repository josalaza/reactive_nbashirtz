package com.example.nbashirtz.repositories;

import com.example.nbashirtz.models.DetalleEntrega;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleEntregaRepository extends R2dbcRepository<DetalleEntrega, Integer> {
}

