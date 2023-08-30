package com.sac.sac.entidades;

import java.util.Objects;

public class Atendente {
    private Long Id;
    private String nome;
    private TimeAtendimento timeAtendimento;

    public Atendente(){}
    public Atendente(Long id, String nome, TimeAtendimento timeAtendimento) {
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
        if (!(o instanceof Atendente atendente)) return false;
        return Objects.equals(getId(), atendente.getId()) && Objects.equals(getNome(), atendente.getNome()) && Objects.equals(timeAtendimento, atendente.timeAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), timeAtendimento);
    }
}
