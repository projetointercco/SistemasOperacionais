package br.usjt.so.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.so.dao.EscalonadorDAO;
import br.usjt.so.entity.Escalonador;

@Service
public class EscalonadorService {
	private EscalonadorDAO dao;
	
	@Autowired
	public EscalonadorService(EscalonadorDAO dao){
		this.dao = dao;
	}
	
	public Escalonador carregar(int id) throws IOException{
		return dao.carregar(id);
	}
	
	public Escalonador atualizaEscalonador(Escalonador escalonador) throws IOException{
		return dao.atualizaEscalonador(escalonador);
	}

}
