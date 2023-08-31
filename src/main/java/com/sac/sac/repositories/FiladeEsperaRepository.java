package com.sac.sac.repositories;

import com.sac.sac.domain.FiladeEspera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiladeEsperaRepository extends JpaRepository<FiladeEspera, Long> {
    Optional<FiladeEspera> findFiladeEsperaById(Long id);
}
