package br.com.samsung.controle.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
	 * Preencher o datatable com as informa��es vindas do Bd.
	 */
	@PostConstruct
	public void carregarEquipamentos() {
		EntityManager em = JPAUtil.getEntityManager();
		EquipamentoDao dao = new EquipamentoDao(em);
		listaEquipamentos = dao.listar();
		em.close();
	}

	/*
	 * M�todo que ir� cadastrar um novo equipamento.
	 */
	public void salvar() {
		try{
			EntityManager em = JPAUtil.getEntityManager();
			EquipamentoDao dao = new EquipamentoDao(em);
			em.getTransaction().begin();
			equipamento.setDtCadastro(Calendar.getInstance());
			if (equipamento.getId() != null) {
				dao.alterar(equipamento);
				exibirMensagem("Sucesso", "Os dados foram alterados com �xito.");
			} else {
				dao.cadastrar(equipamento);
				exibirMensagem("Sucesso", "Os dados foram cadastrados com �xito.");
			}
			em.getTransaction().commit();
			em.close();
			equipamento = new Equipamento();
			carregarEquipamentos();
		
		}catch(Exception e){
			exibirMensagem("Erro", "Ocorreu um erro ao tentar salvar os dados.");
		}
	}

	public void excluir() {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			EquipamentoDao equipamentoDao = new EquipamentoDao(em);
			em.getTransaction().begin();
			equipamentoDao.excluir(equipamento);
			em.getTransaction().commit();
			em.close();
			carregarEquipamentos();
			exibirMensagem("Sucesso", "Registro exclu�do com �xito.");
		} catch (Exception e) {
			exibirMensagem("Erro", "Ocorreu um erro interno durante a tentativa de excluir o registro atual.");
		}
	}

	//Exibir mensagem
	public void exibirMensagem(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
