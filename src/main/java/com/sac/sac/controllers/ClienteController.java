package com.sac.sac.controllers;

import com.sac.sac.domain.Cliente;
import com.sac.sac.dtos.ClienteDTO;
import com.sac.sac.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.createCliente(clienteDTO);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> clientes = this.clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

}
