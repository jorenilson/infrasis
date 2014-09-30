package br.com.samsung.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.samsung.controle.mb.FabricanteMB;
import br.com.samsung.modelo.bean.Fabricante;
import br.com.samsung.modelo.dao.FabricanteDao;
import br.com.samsung.modelo.dao.JPAUtil;

@FacesConverter(forClass=Fabricante.class)
public class FabricanteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigoString) {
		if(codigoString != null && codigoString.trim().length()>0){
			Integer codigo = Integer.valueOf(codigoString);
			EntityManager em = JPAUtil.getEntityManager();
			FabricanteDao fabricanteDao = new FabricanteDao(em);
			return fabricanteDao.buscar(codigo);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object fabricanteObjeto) {
		if (fabricanteObjeto != null){
			Fabricante fabricante = (Fabricante) fabricanteObjeto;
			return Integer.toString(fabricante.getId());
		}
		return null;
	}

}
