package com.sac.sac.entidades;

import java.util.Objects;

public class Tiposolicitacao {

    private Long Id;
    private String nome;
    private TimeAtendimento timeAtendimento;

    public Tiposolicitacao(){}
    public Tiposolicitacao(Long id, String nome, TimeAtendimento timeAtendimento) {
        this.Id = id;
        this.nome = nome;
        this.timeAtendimento = timeAtendimento;
    }

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        this.Id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public TimeAtendimento getTime() {
        return timeAtendimento;
    }
    public void setTime(TimeAtendimento timeAtendimento) {
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
