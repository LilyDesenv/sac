package com.sac.sac.repositories;

import com.sac.sac.domain.Atendente;
import com.sac.sac.domain.TimeAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AtendenteRepository extends JpaRepository<Atendente,Long> {
    Optional<Atendente> findAtendenteById(Long id);
    List<Atendente> findAllByTimeAtendimento(TimeAtendimento timeAtendimento);
}
