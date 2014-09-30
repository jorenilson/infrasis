package br.com.samsung.teste;

import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Equipamento;
import br.com.samsung.modelo.bean.Usuario;
import br.com.samsung.modelo.dao.JPAUtil;
import br.com.samsung.modelo.dao.UsuarioDao;

public class CadastrarUsuario {
	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDao daoUsuario = new UsuarioDao(em);
		Usuario usuario = new Usuario();
		Equipamento equipamento = new Equipamento();
		
		
		em.getTransaction().begin();
		
		
		usuario.setNome("Ricardo Oliveira");
		usuario.setMatricula(66009922);
		usuario.setSenha("senha");
		usuario.setLogin("ricardo.seda");
		
		daoUsuario.inserir(usuario);
		em.getTransaction().commit();
		em.close();
	}
}
