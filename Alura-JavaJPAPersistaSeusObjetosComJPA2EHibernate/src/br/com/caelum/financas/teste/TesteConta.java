package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setId(1);
		conta.setTitular("Danilo");
		conta.setAgencia("123");
		conta.setBanco("Banco do Brasil");
		conta.setNumero("456");

		// CRIA O ENTITY MANAGER COM A FABRICA
		EntityManager em = new JPAUtil().getEntityManager();

		// COMEÇA UMA TRANSAÇÃO
		em.getTransaction().begin();
		conta = em.find(Conta.class, 1);
		
		// FAZ A PERSISTENCIA
		em.remove(conta);

		conta.setBanco("Bradesco");

		// COMMITA A TRANSAÇÃO
		em.getTransaction().commit();

		// FECHA O ENTITY MANAGER
		em.close();
		
	}
}
