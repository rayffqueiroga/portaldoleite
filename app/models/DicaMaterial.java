package models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="DicaMaterial")
public class DicaMaterial extends Dica{
	@Column
	private String url;
	
	public DicaMaterial() {
	}
	
	public DicaMaterial(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getTexto() {
		return getUrl();
	}
	
	@Override
	public String getTipo() {
		return "DicaMaterial";
	}
}
