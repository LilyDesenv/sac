package com.sac.sac.services;

import com.sac.sac.domain.*;
import com.sac.sac.dtos.SolicitacaoDTO;
import com.sac.sac.dtos.SolicitacaoFinalizadaDTO;
import com.sac.sac.repositories.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {
    @Autowired
    private SolicitacaoRepository repository;
    @Autowired
    private AtendenteService atendenteService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TipoSolicitacaoService tipoSolicitacaoService;

    public Solicitacao createSolicitacao(SolicitacaoDTO solicitacaoDTO) throws Exception {
        Cliente cliente = this.clienteService.findClienteById(solicitacaoDTO.ClienteId());
        Tiposolicitacao tiposolicitacao = this.tipoSolicitacaoService.findTiposolicitacaoById(solicitacaoDTO.TiposolicitacaoId());

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setCliente(cliente);
        solicitacao.setTiposolicitacao(tiposolicitacao);
        solicitacao.setDescricao(solicitacaoDTO.descricao());

        validaSolicitacao(solicitacao);

        //verifica se há algum atendente com menos de 3 clientes
        Atendente atendente = buscarAtendenteDisponivel(solicitacao);
        if (atendente.getId() == null) {
            //adicionar solicitação na fila de espera
            solicitacao.setStatusAtendimento(StatusAtendimento.EMESPERA);
            saveSolicitacao(solicitacao);

            throw new Exception("Todos os Atendentes estão em atendimento no momento! Aguarde na fila. ");
        }else {
            solicitacao.setAtendente(atendente);
            solicitacao.getTiposolicitacao().setTimeAtendimento(timeByTipoSolicitacao(solicitacao));
            solicitacao.setStatusAtendimento(StatusAtendimento.EMANDAMENTO);
            saveSolicitacao(solicitacao);
        }
        return solicitacao;

    }

    public void validaSolicitacao(Solicitacao s) throws Exception {
        //verifica se já existe um atendimento em andamento para o cliente do mesmo tipo de solicitação
        if(verificaSolicitacaoRepetida(s)){
            throw new Exception("Já existe uma solicitação em andamento para o tipo de problema informado!");
        }
    }

    public TimeAtendimento timeByTipoSolicitacao(Solicitacao s){
        if(s.getTiposolicitacao().getNome().equalsIgnoreCase("Problemas com Cartão")  ){
            return TimeAtendimento.CARTOES;
        } else if (s.getTiposolicitacao().getNome().equalsIgnoreCase("Contratação de Empréstimo")) {
            return TimeAtendimento.EMPRESTIMOS;
        }else {
            return TimeAtendimento.OUTROS;
        }


    }

    public List<Solicitacao> getAllByEmEspera(){
        List<Solicitacao> solicitacoes = this.repository.findAllByStatusAtendimento(StatusAtendimento.EMESPERA);
        int ordemfila = 0;
        if(!(solicitacoes.isEmpty())){
            for (Solicitacao s:solicitacoes) {
                ordemfila = ordemfila+1;
                s.setOrdemFila(ordemfila);
            }
        }
        return solicitacoes;
    }

    public List<Solicitacao> getAllByEmAndamento(){
        return this.repository.findAllByStatusAtendimento(StatusAtendimento.EMANDAMENTO);
    }

    public Solicitacao findSolicitacaoById(Long id) throws Exception {
        return this.repository.findSolicitacaoById(id).orElseThrow(() -> new Exception("Solicitação não encontrada!"));
    }

    public Solicitacao updateStatusSolicitacaoFinalizado(SolicitacaoFinalizadaDTO solicitacaoFinalizadaDTO){
        Solicitacao s = new Solicitacao();
        s.setId(solicitacaoFinalizadaDTO.solicitacaoId());
        s.setStatusAtendimento(StatusAtendimento.FINALIZADO);
        this.saveSolicitacao(s);
        return s;
    }

    public void saveSolicitacao(Solicitacao s){
        this.repository.save(s);
    }


    //método para buscar o atendente do time que não esteja atendendo mais de 3 clientes
    public Atendente buscarAtendenteDisponivel(Solicitacao solicitacao){
        Atendente atendenteDisponivel = new Atendente();
    //Listagem para trazer todas as solicitacoes em andamento do tipo de atendimento informado
        List<Solicitacao> solicitacoes = repository.findAllByStatusAtendimentoAndAndTiposolicitacao(StatusAtendimento.EMANDAMENTO,solicitacao.getTiposolicitacao());
    //Listagem para trazer todos os atendentes do time informado pelo tipo de problema
        List<Atendente> atendentes = atendenteService.findAllByTimeAtendimento(solicitacao.getTiposolicitacao().getTimeAtendimento());
        int countCliente;
    //varredura da listagem dos atendentes
        for (Atendente atendente: atendentes) {
            countCliente = 0;
            //varredura da listagem das solicitações por atendente
            for (Solicitacao s : solicitacoes) {
                if(s.getAtendente() == atendente){
                    countCliente = countCliente+1;
                }
            }
            if(countCliente < 3){
                atendenteDisponivel = atendente;
                break;
            }
        }
        return atendenteDisponivel;

    }

    public Boolean verificaSolicitacaoRepetida(Solicitacao solicitacao) {
        int count = 0;
        List<Solicitacao> solicitacoes = repository.findAllByClienteAndTiposolicitacaoAndAndStatusAtendimento(solicitacao.getCliente(), solicitacao.getTiposolicitacao(), StatusAtendimento.EMANDAMENTO);
        return !(solicitacoes.isEmpty());
    }
}


