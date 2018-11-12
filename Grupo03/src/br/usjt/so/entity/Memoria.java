package br.usjt.so.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Memoria {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NotNull
private int id;

@NotNull
@Column(name="max_memory")
private int memoriaMaxima;

@Column(name="atual_memory")
private int memoriaAtual;

@Column(name="ultima_memory")
private int memoriaUltima;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getMemoriaMaxima() {
	return memoriaMaxima;
}

public void setMemoriaMaxima(int memoriaMaxima) {
	this.memoriaMaxima = memoriaMaxima;
}

public int getMemoriaAtual() {
	return memoriaAtual;
}

public void setMemoriaAtual(int memoriaAtual) {
	this.memoriaAtual = memoriaAtual;
}

public int getMemoriaUltima() {
	return memoriaUltima;
}

public void setMemoriaUltima(int memoriaUltima) {
	this.memoriaUltima = memoriaUltima;
}

@Override
public String toString() {
	return "Memoria [id=" + id + ", memoriaMaxima=" + memoriaMaxima + ", memoriaAtual=" + memoriaAtual
			+ ", memoriaUltima=" + memoriaUltima + "]";
}

}
