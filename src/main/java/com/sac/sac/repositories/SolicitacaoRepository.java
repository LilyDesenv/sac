package com.sac.sac.repositories;

import com.sac.sac.domain.Cliente;
import com.sac.sac.domain.Solicitacao;
import com.sac.sac.domain.StatusAtendimento;
import com.sac.sac.domain.Tiposolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    Optional<Solicitacao> findSolicitacaoById(Long id);
    List<Solicitacao> findAllByStatusAtendimento(StatusAtendimento statusAtendimento);
    List<Solicitacao> findAllByStatusAtendimentoAndAndTiposolicitacao(StatusAtendimento statusAtendimento, Tiposolicitacao tiposolicitacao);
    List<Solicitacao> findAllByClienteAndTiposolicitacaoAndAndStatusAtendimento(Cliente cliente, Tiposolicitacao tiposolicitacao,StatusAtendimento statusAtendimento);

}
