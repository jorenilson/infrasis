package br.com.samsung.controle.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Categoria;
import br.com.samsung.modelo.bean.Equipamento;
import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.EquipamentoDao;
import br.com.samsung.modelo.dao.JPAUtil;

@ViewScoped
@ManagedBean
public class EquipamentoMB implements Serializable {
	Usuario usuario;
	private Equipamento equipamento = new Equipamento();
	private Categoria categoria = new Categoria();

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();

	public List<Equipamento> getListaEquipamentos() {
		return listaEquipamentos;
	}

	/*
	 * Preencher o datatable com as informações vindas do Bd.
	 */
	@PostConstruct
	public void carregarEquipamentos() {
		EntityManager em = JPAUtil.getEntityManager();
		EquipamentoDao dao = new EquipamentoDao(em);
		listaEquipamentos = dao.listar();
		em.close();
	}

	/*
	 * Método que irá cadastrar um novo equipamento.
	 */
	public void salvar() {
		boolean pesqAtivo = false;
		EntityManager em = JPAUtil.getEntityManager();
		EquipamentoDao dao = new EquipamentoDao(em);
		em.getTransaction().begin();
		equipamento.setDtCadastro(Calendar.getInstance());
		if (equipamento.getId() != null) {
			dao.alterar(equipamento);
			em.getTransaction().commit();
			exibirMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Os dados foram alterados com êxito.");
		} else {
			pesqAtivo = dao.buscarPorAtivo(equipamento);
			if (pesqAtivo){
				exibirMensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Já existe um equipamento cadastrado com essas informações.");
			}else{
				dao.cadastrar(equipamento);
				em.getTransaction().commit();
				exibirMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Os dados foram cadastrados com êxito.");
			}
		}
		em.close();
		equipamento = new Equipamento();
		carregarEquipamentos();
	}
	
	
	public void excluir() {
		EntityManager em = JPAUtil.getEntityManager();
		EquipamentoDao equipamentoDao = new EquipamentoDao(em);
		em.getTransaction().begin();
		equipamentoDao.excluir(equipamento);
		em.getTransaction().commit();
		em.close();
		carregarEquipamentos();
		exibirMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Registro excluído com êxito.");
	}

	//Exibir mensagem
	public void exibirMensagem(Severity tipo, String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(tipo,summary,detail));
	}
}
