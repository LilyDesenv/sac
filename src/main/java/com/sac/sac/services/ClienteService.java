package com.sac.sac.services;

import com.sac.sac.domain.Cliente;
import com.sac.sac.dtos.ClienteDTO;
import com.sac.sac.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository repository;



    public Cliente findClienteById(Long id) throws Exception {
        return this.repository.findClienteById(id).orElseThrow(( ()-> new Exception("Cliente não encontrado!")));
    }

    public Cliente createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.nome());
        saveCliente(cliente);

        return cliente;
    }

    public void saveCliente(Cliente cliente){
        this.repository.save(cliente);
    }


    public List<Cliente> getAllClientes() {
        return this.repository.findAll();
    }
}
