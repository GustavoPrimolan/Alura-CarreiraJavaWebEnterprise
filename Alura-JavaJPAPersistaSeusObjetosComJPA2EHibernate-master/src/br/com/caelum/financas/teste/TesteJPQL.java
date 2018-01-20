package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPQL {
	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		

		Conta conta = new Conta();
		conta.setId(2);
		
		
		em.getTransaction().begin();
		
		
		//DENTRO DE MOVIMENTAÇÃO PEGA A CONTA E DENTRO DA CONTA PEGA O ID
		//M TRAZ O OBJETO INTEIRO
		//String jpql = "select m from Movimentacao m where m.conta.id = 2";
		
		
		//COMPARAR A CONTA COM O OBJETO CONTA
		//: para colocar o parâmetro e o P É CONVENSÃO
		//ORDENA POR VALOR DECRESCENTE
		String jpql = "select m from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo order by m.valor desc";
		
		
		Query query = em.createQuery(jpql);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		query.setParameter("pConta", conta);
		
		
		List<Movimentacao> resultados = query.getResultList();
		
		
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("ID conta: " + movimentacao.getConta().getId());
		}
		
		em.getTransaction().commit();
		
		em.close();
		
		
	}
	
}
