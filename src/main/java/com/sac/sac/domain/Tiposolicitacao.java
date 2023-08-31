package com.sac.sac.domain;

import jakarta.persistence.*;

import java.util.Objects;
@Entity(name = "tipoSolicitacao")
@Table(name = "tipoSolicitacao")
public class Tiposolicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TimeAtendimento timeAtendimento;

    public Tiposolicitacao(){}
    public Tiposolicitacao(Long id, String nome, TimeAtendimento timeAtendimento) {
        this.id = id;
        this.nome = nome;
        this.timeAtendimento = timeAtendimento;
    }
    public Tiposolicitacao( String nome, TimeAtendimento timeAtendimento) {
        this.nome = nome;
        this.timeAtendimento = timeAtendimento;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public TimeAtendimento getTimeAtendimento() {
        return timeAtendimento;
    }
    public void setTimeAtendimento(TimeAtendimento timeAtendimento) {
        this.timeAtendimento = timeAtendimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tiposolicitacao that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome()) && Objects.equals(timeAtendimento, that.timeAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), timeAtendimento);
    }
}
