package br.usjt.so.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.so.entity.Escalonador;
import br.usjt.so.entity.Estado;
import br.usjt.so.entity.Memoria;
import br.usjt.so.entity.Particao;
import br.usjt.so.entity.Prioridade;
import br.usjt.so.entity.Processo;
import br.usjt.so.service.EscalonadorService;
import br.usjt.so.service.EstadoService;
import br.usjt.so.service.MemoriaService;
import br.usjt.so.service.ParticaoService;
import br.usjt.so.service.PrioridadeService;
import br.usjt.so.service.ProcessoService;

@Transactional
@Controller
public class ManterProcessosController {
	private ProcessoService ps;
	private PrioridadeService pris;
	private EstadoService es;
	private MemoriaService ms;
	private ParticaoService part;
	private EscalonadorService escal;

	@Autowired
	public ManterProcessosController(ProcessoService ps, PrioridadeService pris, EstadoService es, MemoriaService ms,
			ParticaoService part, EscalonadorService escal) {
		this.ps = ps;
		this.pris = pris;
		this.es = es;
		this.ms = ms;
		this.part = part;
		this.escal = escal;
	}

	/*
	 * @RequestMapping("index") public String inicio(){ return "index"; }
	 */

	private List<Processo> listarTodosProcessos() throws IOException {
		return ps.listarTodosProcessos();
	}

	private List<Processo> listarProcessosExecutando() throws IOException {
		return ps.listarProcessosExecutando();
	}

	@SuppressWarnings("unused")
	private List<Processo> listarProcessosBloqueados() throws IOException {
		return ps.listarProcessosBloqueados();
	}

	private List<Particao> listarTodasParticoesPelaOrdem() throws IOException {
		return part.listarTodasParticoesPelaOrdem();
	}

	private int carregaMemoriaTotal() throws IOException {
		Memoria memoria = ms.carregar(1);
		return memoria.getMemoriaMaxima();
	}

	private int carregaMemoriaAtual() throws IOException {
		Memoria memoria = ms.carregar(1);
		return memoria.getMemoriaAtual();
	}

	private void atualizaUltimaMemoria(int tamanho) throws IOException {
		Memoria memoria = ms.carregar(1);
		int atualizaMemoria = tamanho - memoria.getMemoriaUltima();
		int memoriaAtual = memoria.getMemoriaAtual();
		memoria.setMemoriaAtual(memoriaAtual + atualizaMemoria);
		memoria.setMemoriaUltima(tamanho);
		ms.atualizaMemoria(memoria);
	}

	private List<Prioridade> listarTodasPrioridades() throws IOException {
		return pris.listarTodasPrioridades();
	}

	private void alocarProcesso(Processo processo) throws IOException {
		List<Particao> particoes = part.listarParticoesDisponiveis();
		Escalonador escalonador = escal.carregar(1);

		for (Particao particao : particoes) {
			int tamanhoProcesso = processo.getDuracao();
			int quantoSobra = particao.getTamanho() - tamanhoProcesso;

			if (quantoSobra >= 0 && quantoSobra <= escalonador.getTamanho()) {
				escalonador.setTamanho(quantoSobra);
				escalonador.setUltimaParticao(particao.getId());

			}
		}

		Particao p = part.carregar(escalonador.getUltimaParticao());
		p.setTamanho(processo.getDuracao());
		p.setDisponibilidade((byte) 0);
		p.setProcesso(processo);

		// cria nova Particao e reinicializa o escalonador
		if (escalonador.getTamanho() > 0) {

			Particao novaParticao = new Particao();
			novaParticao.setDisponibilidade((byte) 1);
			novaParticao.setTamanho(escalonador.getTamanho());
			novaParticao.setOrdem(escalonador.getUltimaParticao());
			part.criarParticao(novaParticao);
		}

		escalonador.setTamanho(100);
		escalonador.setUltimaParticao(0);
	}

	private void desalocarProcesso(Processo processo) throws IOException {
		List<Particao> particoes = part.desalocarProcessoDaParticao(processo);
		for (Particao p : particoes) {
			p.setDisponibilidade((byte) 1);
			p.setProcesso(null);
		}

		// fazer a compactação das partiçoes
		List<Particao> particoes2 = listarTodasParticoesPelaOrdem();
		compactarParticoes(particoes2);

	}

	public void compactarParticoes(List<Particao> particoes) throws IOException {
		for (int i = 0; i < particoes.size() - 1; i++) {
			Particao p1 = particoes.get(i);
			Particao p2 = particoes.get(i + 1);

			if (p2 != null && p1 != null) {
				if (p2.getDisponibilidade() == 1 && p1.getDisponibilidade() == 1) {
					int novoTamanho = p1.getTamanho() + p2.getTamanho();
					p2.setTamanho(novoTamanho);
					part.deletarParticao(p1);
				}
			}
		}

	}

	@RequestMapping("index")
	public String inicio(Model model) {

		try {

			List<Processo> processos = new ArrayList<Processo>();

			if (listarProcessosExecutando().size() == 0) {
				processos = ps.listarProximoProcesso();
				for (Processo p : processos) {
					Date date = new Date();
					date.setTime(date.getTime() - 1000);
					p.setHoraInicio(date);
				}
			} else {
				processos = listarProcessosExecutando();
			}

			for (Processo processo : processos) {
				Date dataDeAgora = new Date();
				Date dataProcesso = processo.getHoraInicio();
				int segundos = (int) ((dataDeAgora.getTime() - dataProcesso.getTime()) / 1000);
				if (segundos > 0 && segundos < processo.getDuracao()) {
					Estado estado2 = es.carregar(3);
					processo.setEstado(estado2);
					processo.setDuracaoAtual(segundos);
					// atualizaUltimaMemoria(processo.getDuracaoAtual());
				} else {
					// altera o estado do processo para finalizado e coloca a
					// data de fim, atualiza a memória disponivel e atualiza o
					// inicio do próximo processo
					Estado estado = es.carregar(6);
					processo.setEstado(estado);
					processo.setHoraFim(new Date());
					atualizaUltimaMemoria(processo.getDuracao());
					Memoria memoria = ms.carregar(1);
					memoria.setMemoriaUltima(0);
					ms.atualizaMemoria(memoria);
					desalocarProcesso(processo);

					List<Processo> proximoProcesso = ps.listarProximoProcesso();
					for (Processo proc : proximoProcesso) {
						proc.setHoraInicio(new Date());
					}
				}
			}

			model.addAttribute("processosExecutando", processos);
			model.addAttribute("processos", ps.listarProcessosPorPrioridade());
			model.addAttribute("memoriaTotal", carregaMemoriaTotal());
			model.addAttribute("memoriaDisponivel", carregaMemoriaAtual());
			model.addAttribute("particoes", listarTodasParticoesPelaOrdem());

			return "index";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping("/inserir_processo")
	public String inserirProcesso(Model model) {
		try {

			model.addAttribute("prioridades", listarTodasPrioridades());
			model.addAttribute("memoriaTotal", carregaMemoriaTotal());
			model.addAttribute("memoriaDisponivel", carregaMemoriaAtual());

			return "InserirProcesso";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping("/adicionar_processo")
	public String criarProcesso(@Valid Processo processo, BindingResult result, Model model) throws IOException {

		int memoriaDisponivel = (carregaMemoriaAtual() - processo.getDuracao());

		if (result.hasFieldErrors("nome")) {
			try {
				model.addAttribute("prioridades", listarTodasPrioridades());
				model.addAttribute("memoriaTotal", carregaMemoriaTotal());
				model.addAttribute("memoriaDisponivel", carregaMemoriaAtual());

				return "InserirProcesso";
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			try {

				if (memoriaDisponivel >= 0) {

					Memoria memoria = ms.carregar(1);
					memoria.setMemoriaAtual(memoriaDisponivel);
					ms.atualizaMemoria(memoria);

					Prioridade prioridade = pris.carregar(processo.getPrioridade().getId());
					Estado estado = es.carregar(2);

					processo.setEstado(estado);
					processo.setPrioridade(prioridade);

					int id = ps.criarProcesso(processo);
					processo.setId(id);

					// alocar memória na partição
					alocarProcesso(processo);

					//return "NumeroProcesso";
					return "redirect:index";
				} else {
					model.addAttribute("prioridades", listarTodasPrioridades());
					model.addAttribute("memoriaIndisponivel", 0);
					model.addAttribute("memoriaTotal", carregaMemoriaTotal());
					model.addAttribute("memoriaDisponivel", carregaMemoriaAtual());

					return "InserirProcesso";
				}

			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
	}

	@RequestMapping("/listar_historico")
	public String listarHistoricoProcessos(Model model) {

		try {

			model.addAttribute("processos", listarTodosProcessos());
			return "HistoricoProcessos";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
