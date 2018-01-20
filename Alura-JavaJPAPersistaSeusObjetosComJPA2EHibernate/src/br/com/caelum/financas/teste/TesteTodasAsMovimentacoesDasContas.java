package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteTodasAsMovimentacoesDasContas {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		//join fetch PEGA APENAS CONTAS QUE TEM MOVIMENTAÇÕES RELACIONADAS A ELAS
		//EXISTEM CONTAS QUE NÃO TEM, POIS ISSO TEMOS QUE USAR O LEFT JOIN
		//O DISTINCT É NECESSÁRIO PARA QUE TRAGA APENAS UMA VEZ A CONTA
		//join fetch SIGNIFICA QUE EU QUERO JUNTAR AS MOVIMENTAÇÕES NA CONTA
		String jpql = "select distinct c from Conta c left join fetch c.movimentacoes";
		
		Query query = em.createQuery(jpql);
		
				
		List<Conta> todasAsContas = query.getResultList();
		
		
		for (Conta conta : todasAsContas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Movimentacoes: " );
			//ELE SÓ BUSCA NO BANCO QUANDO CHAMAMOS O ATRIBUTO
			//É CHAMADO DE N+1, POIS PARA CADA UMA DAS CONTAS ELE FAZ MAIS UM SELECT PARA BUSCAR AS MOVIMENTAÇÕES
			System.out.println(conta.getMovimentacoes());
			
		}
		
	}
	
}
