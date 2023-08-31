package com.sac.sac.domain;

import jakarta.persistence.*;

import java.util.Objects;
@Entity(name = "solicitacao")
@Table(name = "solicitacao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusAtendimento statusAtendimento;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "tiposolicitacao_id")
    private Tiposolicitacao tiposolicitacao;
    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private Atendente atendente;
    @Transient
    private int ordemFila;


    public Solicitacao(){}
    public Solicitacao(Long id, String descricao, Cliente cliente, Tiposolicitacao tiposolicitacao, Atendente atendente,StatusAtendimento statusAtendimento) {
        this.id = id;
        this.descricao = descricao;
        this.cliente = cliente;
        this.tiposolicitacao = tiposolicitacao;
        this.atendente = atendente;
        this.statusAtendimento = statusAtendimento;
    }
    public Solicitacao( String descricao, Cliente cliente, Tiposolicitacao tiposolicitacao, Atendente atendente,StatusAtendimento statusAtendimento) {
        this.descricao = descricao;
        this.cliente = cliente;
        this.tiposolicitacao = tiposolicitacao;
        this.atendente = atendente;
        this.statusAtendimento = statusAtendimento;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tiposolicitacao getTiposolicitacao() {
        return tiposolicitacao;
    }

    public void setTiposolicitacao(Tiposolicitacao tiposolicitacao) {
        this.tiposolicitacao = tiposolicitacao;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public StatusAtendimento getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }

    public int getOrdemFila() {
        return ordemFila;
    }

    public void setOrdemFila(int ordemFila) {
        this.ordemFila = ordemFila;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solicitacao that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDescricao(), that.getDescricao()) && Objects.equals(getCliente(), that.getCliente()) && Objects.equals(getTiposolicitacao(), that.getTiposolicitacao()) && Objects.equals(getAtendente(), that.getAtendente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getCliente(), getTiposolicitacao(), getAtendente());
    }


    @Override
    public String toString() {
        return "\nSolicitação{" +
                "Id = " + id +
                ", Descrição = '" + descricao + '\'' +
                ", Cliente = " + cliente.getNome() +
                ", Tipo de Solicitação = " + tiposolicitacao.getNome() +
                ", Atendente = " + atendente.getNome() +
                '}';
    }
}
