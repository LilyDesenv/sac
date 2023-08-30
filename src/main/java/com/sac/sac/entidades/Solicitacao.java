package com.sac.sac.entidades;

import java.util.Objects;

public class Solicitacao {
    private Long Id;
    private String descricao;
    private Cliente cliente;
    private Tiposolicitacao tiposolicitacao;
    private Atendente atendente;
    public Solicitacao(){}
    public Solicitacao(Long id, String descricao, Cliente cliente, Tiposolicitacao tiposolicitacao, Atendente atendente) {
        this.Id = id;
        this.descricao = descricao;
        this.cliente = cliente;
        this.tiposolicitacao = tiposolicitacao;
        this.atendente = atendente;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
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
                "Id = " + Id +
                ", Descrição = '" + descricao + '\'' +
                ", Cliente = " + cliente.getNome() +
                ", Tipo de Solicitação = " + tiposolicitacao.getNome() +
                ", Atendente = " + atendente.getNome() +
                '}';
    }
}
