package com.sac.sac.services;

import com.sac.sac.controllers.SolicitacoesController;
import com.sac.sac.entidades.Atendente;
import com.sac.sac.entidades.Solicitacao;
import com.sac.sac.entidades.TimeAtendimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {
    @Autowired
    private SolicitacoesController solicitacoesController;

    public void validaSolicitacao(){

    }


    //verifica se o atendente já está com 3 clientes em atendimento
    public Boolean verificaQntClienteByAtendente(List<Solicitacao> solicitacoes, Atendente atendente){
        int count = 0;
        //varredura do array
        for (Solicitacao s: solicitacoes ) {
            //verifica se o atendente passado pelo parâmetro está na listagem
            if(s.getAtendente() == atendente){
                count = count+1;
            }
        }
        return count > 3;
    }

    //verifica se o tipo de Solicitação está de acordo com o time correto
    public Boolean verificaAtendenteByTimeSolicitacao(Solicitacao solicitacao){
        //verifica tipo problema cartões para atendente do time cartões (ID:1)
        if(solicitacao.getTiposolicitacao().getId() == 1L){
            return solicitacao.getAtendente().getTime() == TimeAtendimento.CARTOES;
        //verifica tipo contratação de empréstimo para atendente do time emprestimo (ID:2)
        }else if(solicitacao.getTiposolicitacao().getId() == 2L){
            return solicitacao.getAtendente().getTime() == TimeAtendimento.EMPRESTIMOS;
        //verifica tipo problema Outros Assuntos para atendente do time Outros Assuntos (ID:3)
        }else if(solicitacao.getTiposolicitacao().getId() == 3L){
            return solicitacao.getAtendente().getTime() == TimeAtendimento.OUTROS;
        }else{
            return false;
        }

    }
    public Boolean verificaSolicitacaoRepetida(List<Solicitacao> solicitacoes,Solicitacao solicitacao){
        int count = 0;
        for (Solicitacao s: solicitacoes ) {
            if ((s.getCliente() == solicitacao.getCliente())
                    && (s.getTiposolicitacao() == solicitacao.getTiposolicitacao())
            ) {
                count = count + 1;
            }
        }
        return count > 1;
    }
}


