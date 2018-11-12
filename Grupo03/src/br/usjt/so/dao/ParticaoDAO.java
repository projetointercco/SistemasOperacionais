package br.usjt.so.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.so.entity.Particao;
import br.usjt.so.entity.Processo;

@Repository
public class ParticaoDAO {
	@PersistenceContext
	EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Particao> listarParticoesDisponiveis() throws IOException{
		return manager.createQuery("select p from Particao p where p.disponibilidade = 1").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Particao> listarTodasParticoes() throws IOException {
		return manager.createQuery("selec p from Particao p").getResultList();
	}
	
	public int criarParticao(Particao particao){
		manager.persist(particao);
		return particao.getId();
	}
	
	@SuppressWarnings("unchecked")
	public List<Particao> desalocarProcessoDaParticao(Processo processo) throws IOException{
		processo = manager.find(Processo.class, processo.getId());
		
		String jpsql = "select p from Particao p where p.processo= :processo";
		Query query = manager.createQuery(jpsql);
		query.setParameter("processo", processo);
		
		List<Particao> result = query.getResultList();
		return result;
		
	}
	
	public Particao carregar(int id) throws IOException{
		return manager.find(Particao.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Particao> listarTodasParticoesPelaOrdem() throws IOException{
		return manager.createQuery("select p from Particao p order by p.ordem asc, p.id desc").getResultList();
	}
	
	public void deletarParticao(Particao particao) throws IOException{
		manager.remove(particao);
	}
	
}
