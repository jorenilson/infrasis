package br.com.samsung.modelo.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.samsung.modelo.bean.Categoria;

public class CategoriaDao implements Serializable {
	private final GenericDao<Categoria>dao;
	private final EntityManager em;
	
	public CategoriaDao(EntityManager em){
		this.dao = new GenericDao<Categoria>(em, Categoria.class);
		this.em = em;
	}
	
	public void adicionar(Categoria t) {
		dao.adicionar(t);
	}
	public void excluir(Categoria t) {
		dao.excluir(t);
	}
	
	public Categoria buscar(Integer codigo) {
		return dao.buscar(codigo);
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public String toString() {
		return dao.toString();
	}

	//Método que irá listar todas as categorias cadastradas no BD
	public List<Categoria> listar() {
		return dao.listar();
	}
}
