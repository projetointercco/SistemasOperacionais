package br.usjt.so.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Escalonador {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NotNull
private int id;

@NotNull
private int tamanho;

private int ultimaParticao;

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

public int getUltimaParticao() {
	return ultimaParticao;
}

public void setUltimaParticao(int ultimaParticao) {
	this.ultimaParticao = ultimaParticao;
}

@Override
public String toString() {
	return "Escalonador [id=" + id + ", tamanho=" + tamanho + ", ultimaParticao=" + ultimaParticao + "]";
}

}
