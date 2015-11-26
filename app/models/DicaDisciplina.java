package models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="DicaDisciplina")
public class DicaDisciplina extends Dica{
	@Column
	private String nomeDisciplina;
	
	@Column
	private String razao;
	
	public DicaDisciplina() {
	}
	
	public DicaDisciplina(String nomeDisciplina, String razao) {
		this.nomeDisciplina = nomeDisciplina;
		this.razao = razao;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	@Override
	public String getTexto() {
		return this.nomeDisciplina;
	}
	
	@Override
	public String getTipo() {
		return "DicaDisciplina";
	}
}
