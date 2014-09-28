package br.com.samsung.modelo.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.samsung.modelo.bean.Fabricante;

public class FabricanteDao implements Serializable {
	private final GenericDao<Fabricante> dao;
	private final EntityManager em;
	
	public FabricanteDao(EntityManager em){
		this.dao = new GenericDao<Fabricante>(em, Fabricante.class);
		this.em = em;
	}

	public void adicionar(Fabricante t) {
		dao.adicionar(t);
	}

	public void excluir(Fabricante t) {
		dao.excluir(t);
	}

	public Fabricante buscar(Integer id) {
		return dao.buscar(id);
	}

	public List<Fabricante> listar() {
		return dao.listar();
	}	
}
