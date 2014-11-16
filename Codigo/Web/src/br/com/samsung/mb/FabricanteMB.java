package br.com.samsung.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.samsung.dao.GenericDao;
import br.com.samsung.dao.JPAUtil;
import br.com.samsung.model.Fabricante;

@Named
@RequestScoped
public class FabricanteMB {
	//private Fabricante fabricanteSelecionado;
	private List<Fabricante> fabricantes;
	private Fabricante fabricante = new Fabricante();
	private List<SelectItem>fabricanteSelecionado;
	
	@PostConstruct
	public void carregarFabricantes(){
		fabricantes = new GenericDao<Fabricante>(Fabricante.class).listarTodos();
	}
	
	
	
	public List<SelectItem> getFabricanteSelecionado() {
		if (fabricanteSelecionado == null) {
			fabricanteSelecionado = new ArrayList<SelectItem>();
			EntityManager em = JPAUtil.getEntityManager();
			GenericDao<Fabricante>daoFabricante = new GenericDao<Fabricante>(Fabricante.class);
			List<Fabricante>listaFabricantes = daoFabricante.listarTodos();
			
			if (listaFabricantes != null && !listaFabricantes.isEmpty()) {
				SelectItem item;
				for (Fabricante fabricanteLista : listaFabricantes) {
					item = new SelectItem(fabricanteLista, fabricanteLista.getNome());
					fabricanteSelecionado.add(item);
				}
				
			}
		}
		return fabricanteSelecionado;		
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
