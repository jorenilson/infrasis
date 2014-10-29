package br.com.samsung.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.samsung.dao.GenericDao;
import br.com.samsung.model.Material;

@ViewScoped
@ManagedBean
public class materialMB implements Serializable {
	private Material material = new Material();
	private List<Material> materiais;

	@PostConstruct
	public void carregarEquipamentos() {
		materiais = new GenericDao<Material>(Material.class).listarTodos();
	}
	
	
	public void setMaterial(Material material) {
		this.material = material;
	}


	public Material getMaterial() {
		return material;
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
			dao.cadastrar(material);
		} else {
			dao.atualizar(material);
		}
		
		this.material = new Material();
		this.materiais = dao.listarTodos();
	}
	
	public void excluir() {
		GenericDao<Material> dao = new GenericDao<Material>(Material.class);
		dao.excluir(material);
		
		this.material = new Material();
		this.materiais = dao.listarTodos();
	}
}
