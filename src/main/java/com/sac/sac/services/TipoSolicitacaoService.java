package com.sac.sac.services;

import com.sac.sac.domain.Tiposolicitacao;
import com.sac.sac.repositories.TipoSolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoSolicitacaoService {
    @Autowired
    TipoSolicitacaoRepository repository;

    public Tiposolicitacao findTiposolicitacaoById(Long id) throws Exception {
        return this.repository.findTiposolicitacaoById(id).orElseThrow(()-> new Exception("Tipo de solicitação não encontrado!"));
    }

    public void saveTipoSolicitacao(Tiposolicitacao tiposolicitacao){
        this.repository.save(tiposolicitacao);
    }
}
