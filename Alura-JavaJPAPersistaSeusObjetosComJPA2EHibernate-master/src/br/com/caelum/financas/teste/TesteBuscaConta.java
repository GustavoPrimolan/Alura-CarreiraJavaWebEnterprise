package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteBuscaConta {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager(); 
		em.getTransaction().begin();
		
		Conta conta = em.find(Conta.class, 1);
		
		conta.setTitular("Joao");
		
		em.getTransaction().commit();
		
		
		System.out.println(conta.getTitular());
		
		

		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();
		
		conta.setTitular("Leonardo");
		//TRANSFORMAR DETACHED PARA MERGE E ENTÃO DEIXA MANAGED
		em2.merge(conta);
		
		em2.getTransaction().commit();
		em2.close();
		
		
	}
	
}
