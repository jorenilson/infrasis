package br.com.samsung.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.samsung.modelo.bean.Equipamento;

public class EquipamentoDao {
	private final GenericDao<Equipamento> dao;
	private final EntityManager em;
	
	public EquipamentoDao(EntityManager em){
		this.dao = new GenericDao<Equipamento>(em, Equipamento.class);
		this.em = em;
	}
	
	public void cadastrar(Equipamento equipamento){
		em.persist(equipamento);
	}
	
	public void excluir(Equipamento equipamento){
		em.remove(em.merge(equipamento));
	}
	
	public Equipamento buscar(Integer codigo){
		return dao.buscar(codigo);
	}
	
	public void alterar(Equipamento equipamento){
		em.merge(equipamento);
	}
	
	public List<Equipamento>listar(){
		return dao.listar();
	}
}
