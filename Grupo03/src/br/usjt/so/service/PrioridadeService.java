package br.usjt.so.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.so.dao.PrioridadeDAO;
import br.usjt.so.entity.Prioridade;

@Service
public class PrioridadeService {
	private PrioridadeDAO dao;
	
	@Autowired
	public PrioridadeService(PrioridadeDAO dao){
		this.dao = dao;
	}
	
	public List<Prioridade> listarTodasPrioridades() throws IOException{
		return dao.listarTodasPrioridades();
	}
	
	public Prioridade carregar(int id) throws IOException{
		return dao.carregar(id);
	}

}
