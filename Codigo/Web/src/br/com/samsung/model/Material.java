package br.com.samsung.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Material {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private Categoria categoria;
	private String modelo;
	@ManyToOne
	private Fabricante fabricante;
	private String serial;
	private String ativoFixo;
	@Temporal(TemporalType.DATE)
	private Calendar dtCadastro;
	private String descricao;
	private String Status;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String nSerie) {
		this.serial = nSerie;
	}

	public String getAtivoFixo() {
		return ativoFixo;
	}

	public void setAtivoFixo(String ativoFixo) {
		this.ativoFixo = ativoFixo;
	}

	public Calendar getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Calendar dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	
}