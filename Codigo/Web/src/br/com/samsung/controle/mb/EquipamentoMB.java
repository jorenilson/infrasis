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
	 * Método que irá realizar o Insert dos dados no bd.
	 */
	public void salvar() {
		
		try{
			EntityManager em = JPAUtil.getEntityManager();
			EquipamentoDao dao = new EquipamentoDao(em);
			em.getTransaction().begin();
			equipamento.setDtCadastro(Calendar.getInstance());
			if (equipamento.getId() != null) {
				dao.alterar(equipamento);
				addMessage("Sucesso", "Os dados foram alterados com êxito.");
			} else {
				dao.cadastrar(equipamento);
				addMessage("Sucesso", "Os dados foram cadastrados com êxito.");
			}
			em.getTransaction().commit();
			em.close();
			equipamento = new Equipamento();
			carregarEquipamentos();
		
		}catch(Exception e){
			addMessage("Erro", "Ocorreu um erro ao tentar salvar os dados.");
		}
	}

	public void excluir() {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			EquipamentoDao dao = new EquipamentoDao(em);
			em.getTransaction().begin();
			dao.excluir(equipamento);
			em.getTransaction().commit();
			em.close();
			carregarEquipamentos();
			addMessage("Sucesso", "Registro excluído com êxito.");
		} catch (Exception e) {
			addMessage("Erro", "Ocorreu um erro interno durante a tentativa de excluir o registro atual.");
		}
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
