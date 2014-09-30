package br.com.samsung.controle.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Categoria;
import br.com.samsung.modelo.bean.Equipamento;
import br.com.samsung.modelo.dao.CategoriaDao;
import br.com.samsung.modelo.dao.JPAUtil;


@ManagedBean
public class CategoriaMB{

	private Categoria categoriaSelecionada;
	private Equipamento equipamento;
	private List<SelectItem>categoriasSelect;


	
	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		equipamento.setCategoria(categoriaSelecionada);
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}



	public List<SelectItem> getCategoriaSelect() {
		if (categoriasSelect == null){
			categoriasSelect = new ArrayList<SelectItem>();
			EntityManager em = JPAUtil.getEntityManager();
			CategoriaDao categoriaDao = new CategoriaDao(em);
			List<Categoria> listaCategoria = categoriaDao.listar();
			
			if (listaCategoria != null && !listaCategoria.isEmpty()){
				SelectItem item;
				for (Categoria categoriaLista : listaCategoria) {
					item = new SelectItem(categoriaLista, categoriaLista.getNome());
					categoriasSelect.add(item);
				}
			}
			em.close();
		}
		return categoriasSelect;
	}
}