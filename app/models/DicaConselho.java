package models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="DicaConselho")
public class DicaConselho extends Dica{
	@Column
	private String conselho;
	
	public DicaConselho() {
	}
	
	public DicaConselho(String conselho) {
		this.conselho = conselho;
	}

	public String getConselho() {
		return conselho;
	}

	public void setConselho(String conselho) {
		this.conselho = conselho;
	}

	@Override
	public String getTexto() {
		return getConselho();
	}
	
	@Override
	public String getTipo() {
		return "DicaConselho";
	}
}
