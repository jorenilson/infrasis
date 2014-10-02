package br.com.samsung.controle.mb;

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

import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;

@ViewScoped
@ManagedBean
public class UsuarioMB implements Serializable {
	private Usuario usuario = new Usuario();

	/**
	 * Método que irá permitir aos preencher os atributos do usuário.
	 * 
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
	 * Método que retornará uma lista de todos os usuários previamente
	 * cadastrados no sistema.
	 * 
	 * @return
	 */
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * Método que irá carregar todos os usuários cadastrados no Sistema e que
	 * serão exibidos no grid principal do Formulário.
	 */
	@PostConstruct
	public void carregarUsuarios() {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDao usuarioDao = new UsuarioDao(em);
		listaUsuarios = usuarioDao.listar();
		em.close();
	}

	/**
	 * Este método irá excluir o registro selecionado do banco de dados da
	 * aplicação.
	 */
	public void excluir() {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDao usuarioDao = new UsuarioDao(em);
		em.getTransaction().begin();
		usuarioDao.excluir(usuario);
		em.getTransaction().commit();
		em.close();
		carregarUsuarios();
	}

	/**
	 * Método que irá realizar o Insert dos dados no BD.
	 */
	public void salvar() {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			UsuarioDao daoUsuario = new UsuarioDao(em);
			em.getTransaction().begin();
			this.usuario.setDtCadastro(Calendar.getInstance());
			if (this.usuario.getId() != null) {
				daoUsuario.alterar(this.usuario);
			} else {
				daoUsuario.cadastrar(this.usuario);
			}
			em.getTransaction().commit();
			em.close();
			this.usuario = new Usuario();
			carregarUsuarios();
		} catch (Exception e) {
			System.out.println("Erro");
		}

	}
}
