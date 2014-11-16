package br.com.samsung.mb;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.samsung.dao.GenericDao;
import br.com.samsung.model.Emprestimo;
import br.com.samsung.model.Material;


@Named
@ViewScoped
public class EmprestimoMB {
	@Inject
	private Material material;
	@Inject
	private Emprestimo emprestimo;
	private Long idMaterial;
	
	
	public Long getIdMaterial() {
		return idMaterial;
	}
	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}
	public Material getMaterial() {
		return material;
	}
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	
	//Adição de Material à Nota Fiscal
	public void adicionarItem() {
		GenericDao<Material>dao = new GenericDao<Material>(Material.class);
		//Carregando os dados do material
		
	}
	
}
