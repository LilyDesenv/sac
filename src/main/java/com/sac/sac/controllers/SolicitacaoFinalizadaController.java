package com.sac.sac.controllers;

import com.sac.sac.domain.Solicitacao;
import com.sac.sac.domain.StatusAtendimento;
import com.sac.sac.dtos.SolicitacaoFinalizadaDTO;
import com.sac.sac.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/finalizar")
public class SolicitacaoFinalizadaController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @PostMapping
    public ResponseEntity<Solicitacao> updateSolicitacao(@RequestBody SolicitacaoFinalizadaDTO solicitacaoFinalizadaDTO){
        Solicitacao solicitacao = this.solicitacaoService.updateStatusSolicitacaoFinalizado (solicitacaoFinalizadaDTO);
        return new ResponseEntity<>(solicitacao, HttpStatus.OK);

    }
}
