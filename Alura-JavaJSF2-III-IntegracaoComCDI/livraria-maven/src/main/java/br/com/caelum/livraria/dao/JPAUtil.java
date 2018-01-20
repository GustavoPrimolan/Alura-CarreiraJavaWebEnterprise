package br.com.caelum.livraria.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria");
	
	//CDI CHAMA A FABRICA DE PRODUTOR
	//SEMPRE QUANDO QUISER GERAR UM EntityManager o CDI IR� PRODUZIR ESSE M�TODO PARA SER CRIADO UMA ENTITY MANAGER
	@Produces
	//PARA CADA REQUISI��O SER� EXECUTADO O M�TODO PARA CRIAR A ENTITY MANAGER
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	
	//M�TODO QUE FECHA O ENTITY MANAGER
	//DISPOSES FAZ FECHAR NO FIM DE CADA REQUISI��O
	public void close(@Disposes EntityManager em){
		em.close();
	}

}
