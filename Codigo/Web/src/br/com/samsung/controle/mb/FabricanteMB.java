package br.com.samsung.controle.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Fabricante;
import br.com.samsung.modelo.dao.FabricanteDao;
import br.com.samsung.modelo.dao.JPAUtil;

@ManagedBean
public class FabricanteMB {
	private Fabricante fabricanteSelecionado;
	private List<SelectItem> fabricanteSelect;

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

	public List<SelectItem> getFabricanteSelect() {
		if (fabricanteSelect == null) {
			fabricanteSelect = new ArrayList<SelectItem>();
			EntityManager em = JPAUtil.getEntityManager();
			FabricanteDao fabricanteDao = new FabricanteDao(em);
			List<Fabricante> listaFabricante = fabricanteDao.listar();
			if (listaFabricante != null && !listaFabricante.isEmpty()) {
				SelectItem item;
				for (Fabricante fabricanteLista : listaFabricante) {
					item = new SelectItem(fabricanteLista, fabricanteLista.getNome());
					fabricanteSelect.add(item);
				}
			}
		}
		return fabricanteSelect;
	}

}
