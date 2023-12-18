package com.example.nbashirtz.repositories;


import com.example.nbashirtz.models.Clientes;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends R2dbcRepository<Clientes, String> {
}