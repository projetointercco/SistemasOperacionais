package br.usjt.so.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Processo {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NotNull
private int id;

@NotNull
@Size(min= 1, max= 15, message="O nome do processo deve estar entre 1 e 15 caracteres.")
private String nome;

@NotNull
private int duracao;

@Column(name="duracao_atual")
private int duracaoAtual;

@NotNull
@ManyToOne
@JoinColumn(name="id_prioridade")
private Prioridade prioridade;

@NotNull
private int tamanho;

@Column(name= "h_inicio")
private Date horaInicio;

@Column(name= "h_fim")
private Date horaFim;

@NotNull
@ManyToOne
@JoinColumn(name="id_estado")
private Estado estado;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public int getDuracao() {
	return duracao;
}

public void setDuracao(int duracao) {
	this.duracao = duracao;
}

public int getDuracaoAtual() {
	return duracaoAtual;
}

public void setDuracaoAtual(int duracaoAtual) {
	this.duracaoAtual = duracaoAtual;
}

public Prioridade getPrioridade() {
	return prioridade;
}

public void setPrioridade(Prioridade prioridade) {
	this.prioridade = prioridade;
}

public int getTamanho() {
	return tamanho;
}

public void setTamanho(int tamanho) {
	this.tamanho = tamanho;
}

public Date getHoraInicio() {
	return horaInicio;
}

public void setHoraInicio(Date horaInicio) {
	this.horaInicio = horaInicio;
}

public Date getHoraFim() {
	return horaFim;
}

public void setHoraFim(Date horaFim) {
	this.horaFim = horaFim;
}

public Estado getEstado() {
	return estado;
}

public void setEstado(Estado estado) {
	this.estado = estado;
}

@Override
public String toString() {
	return "Processo [id=" + id + ", nome=" + nome + ", duracao=" + duracao + ", duracaoAtual=" + duracaoAtual
			+ ", prioridade=" + prioridade + ", tamanho=" + tamanho + ", horaInicio=" + horaInicio + ", horaFim="
			+ horaFim + ", estado=" + estado + "]";
}

}
