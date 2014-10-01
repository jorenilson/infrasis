package br.com.samsung.controle.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;






import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;

@ViewScoped
@ManagedBean
public class UsuarioMB implements Serializable {
	private Usuario usuario = new Usuario();
	
	/**
	 * M�todo que ir� permitir aos preencher os atributos
	 * do usu�rio.
	 * @return
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> listaUsuarios = new ArrayList<Usuario>();

	
	/**
	 * M�todo que retornar� uma lista de todos os
	 * usu�rios previamente cadastrados no sistema.
	 * @return
	 */
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * M�todo que ir� carregar todos os usu�rios cadastrados no
	 * Sistema e que ser�o exibidos no grid principal do Formul�rio.
	 */
	@PostConstruct
	public void carregarUsuarios(){
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDao dao = new UsuarioDao(em);
		listaUsuarios = dao.listar();
		em.close();
	}
	
	
	/**
	 * Este m�todo ir� excluir o registro selecionado do banco de dados
	 * da aplica��o.
	 */
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDao daoUsuario = new UsuarioDao(em);
		em.getTransaction().begin();
		daoUsuario.excluir(usuario);
		em.getTransaction().commit();
		em.close();
		carregarUsuarios();
	}
	
	/**
	 * M�todo que ir� realizar o Insert dos dados no BD.
	 */
	public void salvar(){
		try{
			EntityManager em = JPAUtil.getEntityManager();
			UsuarioDao daoUsuario = new UsuarioDao(em);
			em.getTransaction().begin();
			usuario.setDtCadastro(Calendar.getInstance());
			if(usuario.getId() != null){
				daoUsuario.alterar(usuario);
			}else{
				daoUsuario.cadastrar(usuario);
			}
			em.getTransaction().commit();
			em.close();
			usuario = new Usuario();
			carregarUsuarios();
		}catch(Exception e){
			
		}
		
	}
	
	public void addMessage(String summary, String detail){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
