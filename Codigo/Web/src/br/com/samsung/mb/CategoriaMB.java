package br.com.samsung.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.samsung.dao.GenericDao;
import br.com.samsung.model.Categoria;
import br.com.samsung.model.Material;


@ManagedBean
@ViewScoped
public class CategoriaMB{

	private Categoria categoria = new Categoria();
	private List<Categoria> categorias;
	
	
	//Carrega todas as categorias cadastradas
	@PostConstruct
	public void carregarCategorias(){
		categorias = new GenericDao<Categoria>(Categoria.class).listarTodos();
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
		if (categoria.getId() == null){
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