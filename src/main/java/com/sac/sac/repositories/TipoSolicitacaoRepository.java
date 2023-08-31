package com.sac.sac.repositories;

import com.sac.sac.domain.Tiposolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoSolicitacaoRepository extends JpaRepository<Tiposolicitacao,Long> {
    Optional<Tiposolicitacao> findTiposolicitacaoById(Long id);
}
