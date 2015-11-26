package models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="DicaAssunto")
public class DicaAssunto extends Dica{
	@Column
	private String assunto;
	
	public DicaAssunto(){
	}
	
	public DicaAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	@Override
	public String getTexto() {
		return getAssunto();
	}

	@Override
	public String getTipo() {
		return "DicaAssunto";
	}
	
	
}
