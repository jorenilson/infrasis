package br.com.samsung.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.samsung.dao.GenericDao;
import br.com.samsung.model.Material;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class MaterialMB implements Serializable {
	
	@Inject
	private Material material;
	private List<Material> materiais;

	@PostConstruct
	public void carregarMateriais() {
		materiais = new GenericDao<Material>(Material.class).listarTodos();
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
		System.out.println("Selecionado foi: "+material.getId());
	}

	public List<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}

	public void salvar() {
		GenericDao<Material> dao = new GenericDao<Material>(Material.class);
		
		if (material.getId() == null) {
			material.setDtCadastro(Calendar.getInstance());
			material.setStatus("Cadastrado");
			dao.cadastrar(material);
		} else {
			dao.atualizar(material);
		}
		resetaMaterial();
		carregarMateriais();
	}
	
	public void excluir() {
		GenericDao<Material> dao = new GenericDao<Material>(Material.class);
		
		dao.excluir(material);
		this.material = new Material();
		this.materiais = dao.listarTodos();
	}
	
	public void resetaMaterial() {
		this.material = new Material();
	}
}
