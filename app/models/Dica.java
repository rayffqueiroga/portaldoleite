package models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import interfaces.SortTip;
import play.data.format.Formats.DateTime;
import sorts.SortLastTips;
import sorts.SortTipConflict;

@Table(name = "dica")
@Entity(name = "Dica")
public abstract class Dica implements Comparable<Dica> {
	
	public final static int MOST_RECENTS = 1;
	public final static int MOST_POSITIVES = 2;
	public final static int MOST_NEGATIVES = 3;
	
	@Id
	@GeneratedValue
	@Column
	private long id;

	@ManyToOne
	private Tema tema;

	@Column
	private String username;

	@ElementCollection
	@MapKeyColumn(name = "user_dica")
	@Column(name = "commentary")
	@CollectionTable(name = "users_comm", joinColumns = @JoinColumn(name = "dica_id") )
	private Map<String, String> usersCommentaries;

	@ElementCollection
	private List<String> usuariosQueJaVotaram;

	@ManyToMany(mappedBy = "dicasAdicionadas")
	private List<MetaDica> metadicas;

	@Column
	private int concordancias;

	@Column
	private int discordancias;
	
	@Column
	private Date createAt = new Date(); 

	@Column
	private int flag;

	@ElementCollection
	private List<String> usuarioqueQueJaDenunciaram;

	@Transient
	private DicaDisciplina instanciaDisciplina;

	private static SortTip mSort = new SortLastTips();

	public Dica() {
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
		this.usersCommentaries = new HashMap<String, String>();
	}

	public long getId() {
		return id;
	}

	public Map<String, String> getUsersCommentaries() {
		return usersCommentaries;
	}

	public void addUserCommentary(String login, String commentary) {
		usersCommentaries.put(login, commentary);
	}

	public abstract String getTexto();

	public int getConcordancias() {
		return concordancias;
	}

	public void setConcordancias(int concordancias) {
		this.concordancias = concordancias;
	}

	public void incrementaConcordancias() {
		this.concordancias = concordancias + 1;
	}

	public void incrementaDiscordancias() {
		this.discordancias = discordancias + 1;
	}

	public int getDiscordancias() {
		return discordancias;
	}

	public void setDiscordancias(int discordancias) {
		this.discordancias = discordancias;
	}

	public String getIndiceConcordancia() {
		int soma = concordancias + discordancias;
		if (soma == 0) {
			return "0";
		}
		return String.format("%.2f", this.getConcordancias() / ((float) soma));
	}

	public int getFlag() {
		return flag;
	}

	public void incrementaFlag() {
		this.flag = flag + 1;
	}

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public void addUsuarioQueVotou(String user) {
		usuariosQueJaVotaram.add(user);
	}

	public boolean wasVotedByUser(String user) {
		return usuariosQueJaVotaram.contains(user);
	}

	/**
	 * Método a ser usado no sort de lista de Dica para que as primeiras dicas
	 * da lista sejam as com mais concordâncias.
	 */
	@Override
	public int compareTo(Dica otherDica) {
		return mSort.getGreatThan(this, otherDica);
	}

	public static void setSortTip(SortTip newSortTip) {
		mSort = newSortTip;
	}

	public void checaTipoDica() {
		if (this.getTipo().equals("DicaDisciplina")) {
			this.instanciaDisciplina = (DicaDisciplina) this;
		}
	}

	public DicaDisciplina getInstanciaDisciplina() {
		return instanciaDisciplina;
	}

	public void addUsuarioFlag(String user) {
		this.usuarioqueQueJaDenunciaram.add(user);
	}

	public boolean wasFlaggedByUser(String user) {
		return usuarioqueQueJaDenunciaram.contains(user);
	}

	public void addMetaDica(MetaDica metadica) {
		this.metadicas.add(metadica);
	}

	public List<MetaDica> getMetaDicas() {
		return this.metadicas;
	}

	public boolean isUnvotable() {
		return this.concordancias >= 20 || this.discordancias >= 20;
	}
	
	public Date createdAt(){
		return createAt;
	}
	
	public void setCreatedAt(Date date){
		createAt = date;
	}

	public abstract String getTipo();
}
