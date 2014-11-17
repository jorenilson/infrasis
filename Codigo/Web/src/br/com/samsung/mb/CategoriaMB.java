package br.com.samsung.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.samsung.dao.GenericDao;
import br.com.samsung.dao.JPAUtil;
import br.com.samsung.model.Categoria;


@Named
@RequestScoped
public class CategoriaMB implements Serializable{
	@Inject
	private Categoria categoria;
	private List<Categoria> categorias;
	private List<SelectItem>categoriaSelecionada;
	
	
	//Carrega todas as categorias cadastradas
	@PostConstruct
	public void carregarCategorias(){
		categorias = new GenericDao<Categoria>(Categoria.class).listarTodos();
	}

	
	
	public List<SelectItem> getCategoriaSelecionada() {
		if (categoriaSelecionada == null) {
			categoriaSelecionada = new ArrayList<SelectItem>();
			GenericDao<Categoria>daoCategoria = new GenericDao<Categoria>(Categoria.class);
			List<Categoria>listaCategorias = daoCategoria.listarTodos();
			
			if (listaCategorias != null && !listaCategorias.isEmpty()) {
				SelectItem item;
				for (Categoria categoriaLista : listaCategorias) {
					item = new SelectItem(categoriaLista, categoriaLista.getNome());
					categoriaSelecionada.add(item);
				}
			}
		}
		return categoriaSelecionada;
	}



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public List<Categoria> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public void salvar() {
		GenericDao<Categoria> dao = new GenericDao<Categoria>(Categoria.class);
		if (categoria.getId()== null){
			dao.cadastrar(categoria);
		}else{
			dao.atualizar(categoria);
		}
		this.categoria = new Categoria();
		this.categorias = dao.listarTodos();
	}
	
	public void excluir(){
		GenericDao<Categoria> dao = new GenericDao<Categoria>(Categoria.class);
		dao.excluir(categoria);
		
		this.categoria = new Categoria();
		this.categorias = dao.listarTodos();
	}

	
	
}