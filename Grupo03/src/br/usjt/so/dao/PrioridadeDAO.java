package br.usjt.so.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.so.entity.Prioridade;

@Repository
public class PrioridadeDAO {
	@PersistenceContext
	EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Prioridade> listarTodasPrioridades() throws IOException {
		return manager.createQuery("select pri from Prioridade pri").getResultList();
	}
	
	public Prioridade carregar(int id) throws IOException{
		return manager.find(Prioridade.class, id);
	}
	
}
