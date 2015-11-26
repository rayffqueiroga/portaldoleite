import java.util.ArrayList;
import java.util.List;

import models.DicaAssunto;
import models.DicaConselho;
import models.DicaDisciplina;
import models.DicaMaterial;
import models.Disciplina;
import models.Tema;
import models.User;
import models.dao.GenericDAOImpl;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;


public class Global extends GlobalSettings {

	private static GenericDAOImpl dao = new GenericDAOImpl();
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	@Override
	public void onStart(Application app) {
		Logger.info("Aplicação inicializada...");

		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				if(dao.findAllByClassName(Disciplina.class.getName()).size() == 0){
					criaDisciplinaTemas();
				}
			}
		});
	}
	
	@Override
	public void onStop(Application app){
	    JPA.withTransaction(new play.libs.F.Callback0() {
	    @Override
	    public void invoke() throws Throwable {
	        Logger.info("Aplicação finalizando...");
	        disciplinas = dao.findAllByClassName("Disciplina");

	        for (Disciplina disciplina: disciplinas) {
	        dao.removeById(Disciplina.class, disciplina.getId());
	       } 
	    }}); 
	}
	
	private void criaDisciplinaTemas(){
		Disciplina si1 = new Disciplina("Sistemas de Informação 1");
		si1.addTema(new Tema("Análise x Design"));
		si1.addTema(new Tema("Orientação a objetos"));
		si1.addTema(new Tema("GRASP"));
		si1.addTema(new Tema("GoF"));
		si1.addTema(new Tema("Arquitetura"));
		si1.addTema(new Tema("Play"));
		si1.addTema(new Tema("JavaScript"));
		si1.addTema(new Tema("HTML / CSS / Bootstrap"));
		si1.addTema(new Tema("Heroku"));
		si1.addTema(new Tema("Labs"));
		si1.addTema(new Tema("Minitestes"));
		si1.addTema(new Tema("Projeto"));
		
		Disciplina es = new Disciplina("Engenharia de Software");
		es.addTema(new Tema("Qualidade de Software"));
		es.addTema(new Tema("Gerencia de Equipe"));
		es.addTema(new Tema("SCRUM"));
		
		Disciplina ad1 = new Disciplina("Analise de Dados 1");
		ad1.addTema(new Tema("Introdução ao R/Octave"));
		ad1.addTema(new Tema("Mineração de Dados"));
		
		dao.persist(si1);
		dao.persist(es);
		dao.persist(ad1);
		
		// insere usuários
		User user1 = new User("user1@email.com", "123", "user1");
		user1.setNome("user1");
		User user2 = new User("user2@email.com", "123", "user2");
		user2.setNome("user2");
		User user3 = new User("user3@email.com", "123", "user3");
		user3.setNome("user3");
		User user4 = new User("user4@email.com", "123", "user4");
		user4.setNome("user4");
		User user5 = new User("user5@email.com", "123", "user5");
		user5.setNome("user5");
		User user6 = new User("user6@email.com", "123", "user6");
		user6.setNome("user6");
		User user7 = new User("user7@email.com", "123", "user7");
		user7.setNome("user7");
		User user8 = new User("user8@email.com", "123", "user8");
		user8.setNome("user8");
		User user9 = new User("user9@email.com", "123", "user9");
		user1.setNome("user9");
		User user10 = new User("user10@email.com", "123", "user10");
		user10.setNome("user10");
		
    	dao.persist(user1);
    	dao.persist(user2);
    	dao.persist(user3);
    	dao.persist(user4);
    	dao.persist(user5);
    	dao.persist(user6);
    	dao.persist(user7);
    	dao.persist(user8);
    	dao.persist(user9);
    	dao.persist(user10);
    	
    	// insere as dicas
    	DicaMaterial dica1 = new DicaMaterial("https://sites.google.com/site/prog2ufcg/p2/programa");
		si1.getTemaByNome("Orientação a objetos").addDica(dica1);
		dica1.setTema(si1.getTemaByNome("Orientação a objetos"));
		dica1.setUser("user2");
		
		DicaConselho dica2 = new DicaConselho("Java, play, html, css, javascript");
		si1.getTemaByNome("Projeto").addDica(dica2);
		dica2.setTema(si1.getTemaByNome("Projeto"));
		dica2.setUser("user5");
		
		DicaAssunto dica3 = new DicaAssunto("Teste de Software");
		es.getTemaByNome("Qualidade de Software").addDica(dica3);
		dica3.setTema(es.getTemaByNome("Qualidade de Software"));
		dica3.setUser("user7");
		
		DicaDisciplina dica4 = new DicaDisciplina("Probabilidade e Estatística", "Conhecimentos em estatística necessários");
		ad1.getTemaByNome("Estatística").addDica(dica4);
		dica4.setTema(ad1.getTemaByNome("Estatística"));
		dica4.setUser("user5");
		
		DicaMaterial dica5 = new DicaMaterial("http://www.r-bloggers.com/");
		ad1.getTemaByNome("RStudio").addDica(dica5);
		dica5.setTema(ad1.getTemaByNome("RStudio"));
		dica5.setUser("user4");
		
		//insere os votos nas dicas
		dica1.incrementaConcordancias();
		dica1.incrementaConcordancias();
		dica2.incrementaConcordancias();
		dica2.incrementaConcordancias();
		dica3.incrementaConcordancias();
		dica3.incrementaConcordancias();
		dica4.incrementaConcordancias();
		dica4.incrementaConcordancias();
		dica5.incrementaConcordancias();
		dica5.incrementaConcordancias();
		
		dao.persist(dica1);	
		dao.persist(dica2);	
		dao.persist(dica3);	
		dao.persist(dica4);	
		dao.persist(dica5);	
		
		dao.flush();
	}
}
