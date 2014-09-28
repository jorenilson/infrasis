package br.com.samsung.modelo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipoEquipamento {
	@Id
	@GeneratedValue
	private int id;
	private String nome;
}
