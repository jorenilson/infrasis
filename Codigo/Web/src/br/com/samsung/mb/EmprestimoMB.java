package br.com.samsung.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.samsung.dao.GenericDao;
import br.com.samsung.model.Emprestimo;
import br.com.samsung.model.Material;




@ManagedBean
@ViewScoped
public class EmprestimoMB {
	private Long idMaterial;
	private Long idFabricante;
	private Emprestimo item = new Emprestimo();
	
	
	public Emprestimo getItem() {
		return item;
	}

	public void setItem(Emprestimo item) {
		this.item = item;
	}

	public Long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}

	public Long getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
	}
	
	public void adicionarItem() {
		GenericDao<Material>  dao = new GenericDao<Material>(Material.class);
		Material material = dao.buscarPorId(idMaterial);
		
		item.setMaterial(material);
		
		item = new Emprestimo();
	}
}
