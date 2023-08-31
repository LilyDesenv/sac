package com.sac.sac.services;

import com.sac.sac.domain.Atendente;
import com.sac.sac.domain.TimeAtendimento;
import com.sac.sac.dtos.AtendenteDTO;
import com.sac.sac.repositories.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendenteService {
    @Autowired
    AtendenteRepository repository;

    public Atendente findAtendenteById(Long id) throws Exception {
        return this.repository.findAtendenteById(id).orElseThrow(()->new Exception("Atendente não encontrado!"));
    }

    public List<Atendente> findAllByTimeAtendimento(TimeAtendimento timeAtendimento){
        return this.repository.findAllByTimeAtendimento(timeAtendimento);
    }

    public Atendente createAtendente(AtendenteDTO atendenteDTO){
        Atendente atendente = new Atendente();
        atendente.setNome(atendenteDTO.nome());
        atendente.setTime(atendenteDTO.timeAtendimento());
        saveAtendente(atendente);

        return atendente;
    }
    public void saveAtendente( Atendente atendente){
        this.repository.save(atendente);
    }

    public List<Atendente> getAllAtendentes(){
        return this.repository.findAll();
    }
}
