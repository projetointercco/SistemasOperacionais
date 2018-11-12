package br.usjt.so.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Particao {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NotNull
private int id;

@NotNull
private int tamanho;

@NotNull
private byte disponibilidade;

@ManyToOne
@JoinColumn(name="id_processo")
private Processo processo;

private int ordem;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getTamanho() {
	return tamanho;
}

public void setTamanho(int tamanho) {
	this.tamanho = tamanho;
}

public byte getDisponibilidade() {
	return disponibilidade;
}

public void setDisponibilidade(byte disponibilidade) {
	this.disponibilidade = disponibilidade;
}

public Processo getProcesso() {
	return processo;
}

public void setProcesso(Processo processo) {
	this.processo = processo;
}

public int getOrdem() {
	return ordem;
}

public void setOrdem(int ordem) {
	this.ordem = ordem;
}

@Override
public String toString() {
	return "Particao [id=" + id + ", tamanho=" + tamanho + ", disponibilidade=" + disponibilidade + ", processo="
			+ processo + ", ordem=" + ordem + "]";
}

}
