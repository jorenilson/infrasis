package br.com.samsung.teste;

import javax.persistence.EntityManager;

import br.com.samsung.modelo.bean.Equipamento;
import br.com.samsung.modelo.dao.EquipamentoDao;
import br.com.samsung.modelo.dao.JPAUtil;

public class TestaPesquisaEquipamento {
	public static void main(String[] args) {
		boolean achou;
		
		Equipamento equipamento = new Equipamento();
		equipamento.setAtivoFixo("0034394");
		EntityManager em = JPAUtil.getEntityManager();
		EquipamentoDao dao = new EquipamentoDao(em);
		
		achou = dao.buscarPorAtivo(equipamento);
		
		if (achou){
			System.out.println("Achou");
		}else{
			System.out.println("Não achou");
		}
	}
}
