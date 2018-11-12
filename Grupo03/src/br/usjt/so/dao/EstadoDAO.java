package br.usjt.so.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.so.entity.Estado;

@Repository
public class EstadoDAO {
	@PersistenceContext
	EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Estado> listarTodosEstados() throws IOException {
		return manager.createQuery("select e from Estado e").getResultList();
	}
	
	public Estado carregar(int id) throws IOException{
		return manager.find(Estado.class, id);
	}
	
}
