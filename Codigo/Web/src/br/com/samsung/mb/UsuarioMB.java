package br.com.samsung.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;

import br.com.samsung.dao.JPAUtil;
import br.com.samsung.dao.UsuarioDao;
import br.com.samsung.model.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioMB implements Serializable {

}
