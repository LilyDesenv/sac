package com.sac.sac.controllers;

import com.sac.sac.domain.*;
import com.sac.sac.dtos.SolicitacaoDTO;
import com.sac.sac.services.AtendenteService;
import com.sac.sac.services.ClienteService;
import com.sac.sac.services.SolicitacaoService;
import com.sac.sac.services.TipoSolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/sac")
public class SolicitacoesController {

    @Autowired
    private SolicitacaoService solicitacaoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private AtendenteService atendenteService;
    @Autowired
    private TipoSolicitacaoService tipoSolicitacaoService;

    @GetMapping
    public ResponseEntity<List<Solicitacao>> getAllSolicitacoes(){
        List<Solicitacao> solicitacoes = this.solicitacaoService.getAllByEmAndamento();
        //verifica se existem dados no banco para inserir os dados de teste somente uma vez
        if(solicitacoes.isEmpty()){
            CarregaDados();
            solicitacoes = this.solicitacaoService.getAllByEmAndamento();
        }
        //verifica se existem dados na fila de espera, e depois verifica se há atendentes para realocar
        List<Solicitacao> solicitacoesEmEspera = this.solicitacaoService.getAllByEmEspera();
        if(!(solicitacoesEmEspera.isEmpty())){
            //varredura do Array
            for (Solicitacao solicitacao:solicitacoesEmEspera ) {
                Atendente atendente = this.solicitacaoService.buscarAtendenteDisponivel(solicitacao);
                if(!(atendente.getId() == null)){
                    solicitacao.setAtendente(atendente);
                    solicitacao.getTiposolicitacao().setTimeAtendimento(this.solicitacaoService.timeByTipoSolicitacao(solicitacao));
                    solicitacao.setStatusAtendimento(StatusAtendimento.EMANDAMENTO);
                    this.solicitacaoService.saveSolicitacao(solicitacao);
                    solicitacoes = this.solicitacaoService.getAllByEmAndamento();
                }
            }

        }

        return new ResponseEntity<>(solicitacoes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Solicitacao> createSolicitacao(@RequestBody SolicitacaoDTO solicitacaoDTO) throws Exception {
        Solicitacao solicitacao = this.solicitacaoService.createSolicitacao(solicitacaoDTO);
        return new ResponseEntity<>(solicitacao, HttpStatus.CREATED);
    }





    //pré preenchimento de dados no banco para testes
    public void CarregaDados(){

        //criação dos dados dos tipos de solicitação
        Tiposolicitacao ts1 = new Tiposolicitacao("Problemas com Cartão",TimeAtendimento.CARTOES);
        Tiposolicitacao ts2 = new Tiposolicitacao("Contratação de Empréstimo",TimeAtendimento.EMPRESTIMOS);
        Tiposolicitacao ts3 = new Tiposolicitacao("Outros Assuntos",TimeAtendimento.OUTROS);
        assert this.tipoSolicitacaoService != null;
        this.tipoSolicitacaoService.saveTipoSolicitacao(ts1);
        this.tipoSolicitacaoService.saveTipoSolicitacao(ts2);
        this.tipoSolicitacaoService.saveTipoSolicitacao(ts3);


        //criação dos dados dos Clientes
        Cliente c1 = new Cliente("Maria da Silva");
        Cliente c2 = new Cliente("José Nogueira");
        Cliente c3 = new Cliente("Paulo Mendes");
        Cliente c4 = new Cliente("Bruno Bezerra");
        Cliente c5 = new Cliente("Ana Maria Barbosa");
        Cliente c6 = new Cliente("Mônica Almeida");
        Cliente c7 = new Cliente("Pedro Azevedo");
        Cliente c8 = new Cliente("Mário Oliveira");
        Cliente c9 = new Cliente("Luiz Melo");
        assert this.clienteService != null;
        this.clienteService.saveCliente(c1);
        this.clienteService.saveCliente(c2);
        this.clienteService.saveCliente(c3);
        this.clienteService.saveCliente(c4);
        this.clienteService.saveCliente(c5);
        this.clienteService.saveCliente(c6);
        this.clienteService.saveCliente(c7);
        this.clienteService.saveCliente(c8);
        this.clienteService.saveCliente(c9);


        //criação dos dados dos Atendentes
        Atendente a1 = new Atendente("Márcio Campos",TimeAtendimento.CARTOES);
        Atendente a2 = new Atendente("Édson Morales",TimeAtendimento.CARTOES);

        Atendente a3 = new Atendente("Carlos Eduardo",TimeAtendimento.EMPRESTIMOS);
        Atendente a4 = new Atendente("Silvia Brito",TimeAtendimento.EMPRESTIMOS);

        Atendente a5 = new Atendente("Marcelo Lima",TimeAtendimento.OUTROS);
        Atendente a6 = new Atendente("Clara Santos",TimeAtendimento.OUTROS);
        assert this.atendenteService != null;
        this.atendenteService.saveAtendente(a1);
        this.atendenteService.saveAtendente(a2);
        this.atendenteService.saveAtendente(a3);
        this.atendenteService.saveAtendente(a4);
        this.atendenteService.saveAtendente(a5);
        this.atendenteService.saveAtendente(a6);


        //criação das solicitações dos clientes
        Solicitacao s1 = new Solicitacao("Cartão não efetua compras",c1,ts1,a1,StatusAtendimento.EMANDAMENTO);
        Solicitacao s2 = new Solicitacao("Compra no Cartão não reconhecida",c2,ts1,a1,StatusAtendimento.EMANDAMENTO);
        Solicitacao s3 = new Solicitacao("Cartão roubado",c3,ts1,a1,StatusAtendimento.EMANDAMENTO);
        Solicitacao s4 = new Solicitacao("Cartão sem limite",c4,ts1,a2,StatusAtendimento.EMANDAMENTO);
        Solicitacao s5= new Solicitacao("Cartão vencido",c5,ts1,a2,StatusAtendimento.EMANDAMENTO);

        Solicitacao s6 = new Solicitacao("Pedido de empréstimo",c6,ts2,a3,StatusAtendimento.EMANDAMENTO);
        Solicitacao s7 = new Solicitacao("Negociação de prestação de empréstimo",c7,ts2,a3,StatusAtendimento.EMANDAMENTO);
        Solicitacao s8 = new Solicitacao("Cartão não efetua compras",c8,ts2,a4,StatusAtendimento.EMANDAMENTO);

        Solicitacao s9 = new Solicitacao("Atualizar dados de Endereço",c9,ts3,a5,StatusAtendimento.EMANDAMENTO);

        assert this.solicitacaoService != null;
        this.solicitacaoService.saveSolicitacao(s1);
        this.solicitacaoService.saveSolicitacao(s2);
        this.solicitacaoService.saveSolicitacao(s3);
        this.solicitacaoService.saveSolicitacao(s4);
        this.solicitacaoService.saveSolicitacao(s5);
        this.solicitacaoService.saveSolicitacao(s6);
        this.solicitacaoService.saveSolicitacao(s7);
        this.solicitacaoService.saveSolicitacao(s8);
        this.solicitacaoService.saveSolicitacao(s9);
    }

}
