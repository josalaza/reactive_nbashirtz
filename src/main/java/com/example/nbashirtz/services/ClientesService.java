package com.example.nbashirtz.services;



import com.example.nbashirtz.dto.DTOCliente;
import com.example.nbashirtz.models.Clientes;
import com.example.nbashirtz.models.DetalleEntrega;
import com.example.nbashirtz.repositories.ClientesRepository;
import com.example.nbashirtz.repositories.DetalleEntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ClientesService {

    private final ClientesRepository clienteRepository;
    private final DetalleEntregaRepository datosEntregaRepository;

    public Mono<DTOCliente> crearCliente(DTOCliente dtoClienteEntrada) {

        DetalleEntrega detalleEntrega = new DetalleEntrega();
        detalleEntrega.setCiudad(dtoClienteEntrada.ciudad());
        detalleEntrega.setCodigoPostal(dtoClienteEntrada.codigoPostal());
        detalleEntrega.setDireccion(dtoClienteEntrada.direccion());
        detalleEntrega.setTelefono(dtoClienteEntrada.telefono());

        Clientes cliente = new Clientes();
        cliente.setIdentificacion(dtoClienteEntrada.identificacion());
        cliente.setNombre(dtoClienteEntrada.nombre());
        cliente.setDatosEntrega(detalleEntrega);

        return Mono.zip(
                datosEntregaRepository.save(detalleEntrega),
                clienteRepository.save(cliente)
        ).map(tupla -> {
            DetalleEntrega detalleEntregaCreado = tupla.getT1();
            Clientes clienteCreado = tupla.getT2();
            return new DTOCliente(
                    clienteCreado.getIdentificacion(),
                    clienteCreado.getNombre(),
                    detalleEntregaCreado.getCiudad(),
                    detalleEntregaCreado.getCodigoPostal(),
                    detalleEntregaCreado.getDireccion(),
                    detalleEntregaCreado.getTelefono()
            );
        });
    }}
