package com.sac.sac.controllers;

import com.sac.sac.domain.Atendente;
import com.sac.sac.dtos.AtendenteDTO;
import com.sac.sac.services.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/atendentes")
public class AtendenteController {

    @Autowired
    AtendenteService atendenteService;

    @PostMapping
    public ResponseEntity<Atendente> createAtendente(@RequestBody AtendenteDTO atendenteDTO){
        Atendente atendente = atendenteService.createAtendente(atendenteDTO);
        return new ResponseEntity<>(atendente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Atendente>> getAllAtendentes(){
        List<Atendente> atendentes = atendenteService.getAllAtendentes();
        return new ResponseEntity<>(atendentes, HttpStatus.OK);
    }
}
