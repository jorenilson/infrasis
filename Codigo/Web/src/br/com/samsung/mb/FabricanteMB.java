package br.com.samsung.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.samsung.dao.GenericDao;
import br.com.samsung.model.Fabricante;

@ManagedBean
@ViewScoped
public class FabricanteMB {
	private Fabricante fabricanteSelecionado;
	private List<Fabricante> fabricantes;
	private Fabricante fabricante = new Fabricante();
	
	@PostConstruct
	public void carregarFabricantes(){
		fabricantes = new GenericDao<Fabricante>(Fabricante.class).listarTodos();
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}
	
	public void salvar(){
		GenericDao<Fabricante> dao = new GenericDao<Fabricante>(Fabricante.class);
		if(fabricante.getId() == null){
			dao.cadastrar(fabricante);
		}else{
			dao.atualizar(fabricante);
		}
		
		this.fabricante = new Fabricante();
		this.fabricantes = dao.listarTodos();
	}
	
	public void excluir(){
		GenericDao<Fabricante> dao = new GenericDao<Fabricante>(Fabricante.class);
		dao.excluir(fabricante);
		
		this.fabricante = new Fabricante();
		this.fabricantes = dao.listarTodos();
	}
	
}
