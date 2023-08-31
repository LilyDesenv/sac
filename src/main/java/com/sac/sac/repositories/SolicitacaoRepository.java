package com.sac.sac.repositories;

import com.sac.sac.domain.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    Optional<Solicitacao> findSolicitacaoById(Long id);

}
