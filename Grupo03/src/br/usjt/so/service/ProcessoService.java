package br.usjt.so.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.so.dao.ProcessoDAO;
import br.usjt.so.entity.Processo;

@Service
public class ProcessoService {
	private ProcessoDAO dao;
	
	@Autowired
	public ProcessoService(ProcessoDAO dao){
		this.dao = dao;
	}
	
	public int criarProcesso(Processo processo) throws IOException{	
		processo.setHoraInicio(new Date());
		return dao.criarProcesso(processo);
	}
	
	public List<Processo> listarTodosProcessos() throws IOException{
		return dao.listarTodosProcessos();
	}
	
	public List<Processo> listarProcessosExecutando() throws IOException{
		return dao.listarProcessosExecutando();
	}
	
	public List<Processo> listarProcessosPorPrioridade() throws IOException{
		return dao.listarProcessosPorPrioridade();
	}
	
	public List<Processo> listarProximoProcesso() throws IOException{
		return dao.listarProximoProcesso();
	}
	
	public List<Processo> listarProcessosBloqueados() throws IOException{
		return dao.listarProcessosBloqueados();
	}
	
}
