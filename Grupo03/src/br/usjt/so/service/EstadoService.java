package br.usjt.so.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.so.dao.EstadoDAO;
import br.usjt.so.entity.Estado;

@Service
public class EstadoService {
	private EstadoDAO dao;
	
	@Autowired
	public EstadoService(EstadoDAO dao){
		this.dao = dao;
	}
	
	public List<Estado> listarTodosEstados() throws IOException{
		return dao.listarTodosEstados();
	}
	
	public Estado carregar(int id) throws IOException{
		return dao.carregar(id);
	}

}
