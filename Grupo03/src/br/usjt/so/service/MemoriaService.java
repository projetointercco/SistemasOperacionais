package br.usjt.so.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.so.dao.MemoriaDAO;
import br.usjt.so.entity.Memoria;

@Service
public class MemoriaService {
	private MemoriaDAO dao;
	
	@Autowired
	public MemoriaService(MemoriaDAO dao){
		this.dao = dao;
	}
	
	public Memoria atualizaMemoria(Memoria memoria) throws IOException{
		return dao.atualizaMemoria(memoria);
	}
	
	public Memoria carregar(int id) throws IOException{
		return dao.carregar(id);
	}

}
