package com.sac.sac.controllers;

import com.sac.sac.domain.Solicitacao;
import com.sac.sac.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/filaEspera")
public class SolicitacaoEsperaController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public ResponseEntity<List<Solicitacao>> getAllSolicitacoesEmEspera(){
        List<Solicitacao> solicitacoes = solicitacaoService.getAllByEmEspera();
        return new ResponseEntity<>(solicitacoes, HttpStatus.OK);
    }
}
