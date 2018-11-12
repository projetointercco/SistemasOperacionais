package br.usjt.so.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.so.entity.Memoria;

@Repository
public class MemoriaDAO {
	@PersistenceContext
	EntityManager manager;
	
	public Memoria atualizaMemoria(Memoria memoria) throws IOException{
		return manager.merge(memoria);
	}
	
	public Memoria carregar(int id) throws IOException{
		return manager.find(Memoria.class, id);
	}
	
}
