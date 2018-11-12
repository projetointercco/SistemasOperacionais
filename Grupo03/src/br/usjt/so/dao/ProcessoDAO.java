package br.usjt.so.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.so.entity.Processo;

@Repository
public class ProcessoDAO {
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Processo> listarTodosProcessos() throws IOException {
		return manager.createQuery("select p from Processo p").getResultList();
	}

	public int criarProcesso(Processo processo) {
		manager.persist(processo);
		return processo.getId();
	}

	@SuppressWarnings("unchecked")
	public List<Processo> listarProcessosExecutando() throws IOException {
		return manager.createQuery("select p from Processo p where p.estado = 3").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Processo> listarProcessosPorPrioridade() {
		return manager
				.createQuery(
						"select p from Processo p where p.estado = 1 or p.estado = 2 or p.estado = 5 order by p.prioridade desc, p.duracao desc")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Processo> listarProximoProcesso() {
		return manager
				.createQuery(
						"select p from Processo p where p.estado = 1 or p.estado = 2 or p.estado = 4 or p.estado = 5 order by p.prioridade desc, p.duracao desc")
				.setMaxResults(1).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Processo> listarProcessosBloqueados(){
		return manager.createQuery("select p from Processo p where p.estado = 4").getResultList();
	}

}
