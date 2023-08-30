package com.sac.sac.controllers;

import com.sac.sac.entidades.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/sac")
public class SolicitacoesController {

    @GetMapping
    public List<Solicitacao> getObjects(){

        //criação dos dados dos tipos de solicitação
        Tiposolicitacao ts1 = new Tiposolicitacao(1L,"Problemas com Cartão",TimeAtendimento.CARTOES);
        Tiposolicitacao ts2 = new Tiposolicitacao(2L,"Contratação de Empréstimo",TimeAtendimento.EMPRESTIMOS);
        Tiposolicitacao ts3 = new Tiposolicitacao(3L,"Outros Assuntos",TimeAtendimento.OUTROS);

        //criação dos dados dos Clientes
        Cliente c1 = new Cliente(1L,"Maria da Silva");
        Cliente c2 = new Cliente(2L,"José Nogueira");
        Cliente c3 = new Cliente(3L,"Paulo Mendes");
        Cliente c4 = new Cliente(4L,"Bruno Bezerra");
        Cliente c5 = new Cliente(5L,"Ana Maria Barbosa");
        Cliente c6 = new Cliente(6L,"Mônica Almeida");
        Cliente c7 = new Cliente(7L,"Pedro Azevedo");
        Cliente c8 = new Cliente(8L,"Mário Oliveira");
        Cliente c9 = new Cliente(9L,"Luiz Melo");

        //criação dos dados dos Atendentes
        Atendente a1 = new Atendente(1L,"Márcio Campos",TimeAtendimento.CARTOES);
        Atendente a2 = new Atendente(2L,"Édson Morales",TimeAtendimento.CARTOES);

        Atendente a3 = new Atendente(3L,"Carlos Eduardo",TimeAtendimento.EMPRESTIMOS);
        Atendente a4 = new Atendente(4L,"Silvia Brito",TimeAtendimento.EMPRESTIMOS);

        Atendente a5 = new Atendente(5L,"Marcelo Lima",TimeAtendimento.OUTROS);
        Atendente a6 = new Atendente(6L,"Clara Santos",TimeAtendimento.OUTROS);

        //criação das solicitações dos clientes
        Solicitacao s1 = new Solicitacao(1L,"Cartão não efetua compras",c1,ts1,a1);
        Solicitacao s2 = new Solicitacao(2L,"Compra no Cartão não reconhecida",c2,ts1,a1);
        Solicitacao s3 = new Solicitacao(3L,"Cartão roubado",c3,ts1,a1);
        Solicitacao s4 = new Solicitacao(4L,"Cartão sem limite",c4,ts1,a2);
        Solicitacao s5= new Solicitacao(5L,"Cartão vencido",c5,ts1,a2);

        Solicitacao s6 = new Solicitacao(6L,"Pedido de empréstimo",c6,ts2,a3);
        Solicitacao s7 = new Solicitacao(7L,"Negociação de prestação de empréstimo",c7,ts2,a3);
        Solicitacao s8 = new Solicitacao(8L,"Cartão não efetua compras",c8,ts2,a4);

        Solicitacao s9 = new Solicitacao(9L,"Atualizar dados de Endereço",c9,ts3,a5);

        List<Solicitacao> solicitacoes = Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9);

        return solicitacoes;
    }
}
