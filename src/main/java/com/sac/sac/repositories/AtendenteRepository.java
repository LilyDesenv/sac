package com.sac.sac.repositories;

import com.sac.sac.domain.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtendenteRepository extends JpaRepository<Atendente,Long> {
    Optional<Atendente> findAtendenteById(Long id);
}
