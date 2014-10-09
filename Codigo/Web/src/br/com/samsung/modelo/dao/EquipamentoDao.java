package br.com.samsung.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.samsung.modelo.bean.Equipamento;

public class EquipamentoDao {
	private final GenericDao<Equipamento> dao;
	private final EntityManager em;

	public EquipamentoDao(EntityManager em) {
		this.dao = new GenericDao<Equipamento>(em, Equipamento.class);
		this.em = em;
	}

	public void cadastrar(Equipamento equipamento) {
		em.persist(equipamento);
	}

	public void excluir(Equipamento equipamento) {
		em.remove(em.merge(equipamento));
	}

	public Equipamento buscar(Integer codigo) {
		return dao.buscar(codigo);
	}

	/**
	 * Este método irá receber como parâmetro o ativo informado e retornar um
	 * valor lógico caso tenha encontrado um equipamento com o mesmo ativo já
	 * cadastrado.
	 * 
	 * @param equipamento
	 * @return
	 */
	public boolean buscarPorAtivo(Equipamento equipamento) {
		boolean resultado = false;
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("FROM Equipamento u"
				+ " WHERE u.ativoFixo = :pAtivo OR u.serial = :pSerial");
		
		query.setParameter("pAtivo", equipamento.getAtivoFixo());
		query.setParameter("pSerial", equipamento.getSerial());
		
		resultado = !query.getResultList().isEmpty();
		em.getTransaction().commit();
		em.close();
		return resultado;
	}
	
	

	public void alterar(Equipamento equipamento) {
		dao.alterar(equipamento);
	}

	public List<Equipamento> listar() {
		return dao.listar();
	}
}
