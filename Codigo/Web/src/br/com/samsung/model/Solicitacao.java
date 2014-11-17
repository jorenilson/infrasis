package br.com.samsung.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Solicitacao {
	@Id
	@GeneratedValue
	private Integer id;
	private String solicitante;
	//Somente data sem hora
	@Temporal(TemporalType.DATE)
	private Calendar data = Calendar.getInstance();
	
	@OneToMany(mappedBy = "solicitacao")
	private List<Emprestimo>itens = new ArrayList<Emprestimo>();

	public Integer getId() {
		return id;
	}
	
	public String getSolicitante() {
		return solicitante;
	}



	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}



	public void setId(Integer id) {
		this.id = id;
	}
	
	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public List<Emprestimo> getItens() {
		return itens;
	}

	public void setItens(List<Emprestimo> itens) {
		this.itens = itens;
	}
	
	public void adicionarEmprestimo(Emprestimo item) {
		this.itens.add(item);
	}
}
