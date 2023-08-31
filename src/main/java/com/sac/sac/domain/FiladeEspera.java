package com.sac.sac.domain;

import jakarta.persistence.*;

import java.util.Objects;
@Entity(name = "filadeEspera")
@Table(name = "filadeEspera")
public class FiladeEspera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "solicitacao_id")
    Solicitacao solicitacao;
    public FiladeEspera() {}
    public FiladeEspera(Long id, Solicitacao solicitacao) {
        this.id = id;
        this.solicitacao = solicitacao;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Solicitacao getSolicitacao() {
        return solicitacao;
    }
    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FiladeEspera that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSolicitacao(), that.getSolicitacao());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSolicitacao());
    }
}
