package br.usjt.so.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.so.entity.Escalonador;

@Repository
public class EscalonadorDAO {
	@PersistenceContext
	EntityManager manager;
	
	public Escalonador carregar(int id) throws IOException{
		return manager.find(Escalonador.class, id);
	}
	
	public Escalonador atualizaEscalonador(Escalonador escalonador) throws IOException{
		return manager.merge(escalonador);
	}
	
}
