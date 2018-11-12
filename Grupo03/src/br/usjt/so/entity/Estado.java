package br.usjt.so.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NotNull
private int id;

@NotNull
private String nome;

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

@Override
public String toString() {
	return "Prioridade [id=" + id + ", nome=" + nome + "]";
}

}
