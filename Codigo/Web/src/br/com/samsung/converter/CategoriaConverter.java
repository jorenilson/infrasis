package br.com.samsung.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Categoria;
import br.com.samsung.modelo.dao.CategoriaDao;
import br.com.samsung.modelo.dao.JPAUtil;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigoString) {
		if (codigoString != null && codigoString.trim().length()>0){
			Integer codigo = Integer.valueOf(codigoString);
			EntityManager em = JPAUtil.getEntityManager();
			CategoriaDao categoriaDao = new CategoriaDao(em);
			return categoriaDao.buscar(codigo);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object categoriaObjeto) {
		if (categoriaObjeto != null){
			Categoria categoria = (Categoria) categoriaObjeto;
			return Integer.toString(categoria.getId());
		}
		return null;
	}

}
