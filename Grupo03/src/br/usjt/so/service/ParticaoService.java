package br.usjt.so.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.so.dao.ParticaoDAO;
import br.usjt.so.entity.Particao;
import br.usjt.so.entity.Processo;

@Service
public class ParticaoService {
	private ParticaoDAO dao;
	
	@Autowired
	public ParticaoService(ParticaoDAO dao){
		this.dao = dao;
	}
	
	public List<Particao> listarParticoesDisponiveis() throws IOException{
		return dao.listarParticoesDisponiveis();
	}
	
	public List<Particao> listarTodasParticoes() throws IOException{
		return dao.listarTodasParticoes();
	}
	
	public int criarParticao(Particao particao) throws IOException{
		return dao.criarParticao(particao);
	}
	
	public List<Particao> desalocarProcessoDaParticao(Processo processo) throws IOException{
		return dao.desalocarProcessoDaParticao(processo);
	}
	
	public Particao carregar(int id) throws IOException{
		return dao.carregar(id);
	}
	
	public List<Particao> listarTodasParticoesPelaOrdem() throws IOException{
		return dao.listarTodasParticoesPelaOrdem();
	}
	
	public void deletarParticao(Particao particao) throws IOException{
		dao.deletarParticao(particao);
	}
	

}
