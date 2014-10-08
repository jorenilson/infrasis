package br.com.samsung.modelo.bean;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Mensagens {
	//Exibir mensagem
	public void exibirMensagem(Severity tipo, String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(tipo,summary,detail));
	}
}
