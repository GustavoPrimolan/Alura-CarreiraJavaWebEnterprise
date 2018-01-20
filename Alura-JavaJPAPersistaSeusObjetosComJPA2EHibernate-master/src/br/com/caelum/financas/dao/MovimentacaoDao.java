package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class MovimentacaoDao {

	EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}

	public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {
		//String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo order by m.valor desc";
		String jpql = "select distinct avg(m.valor) from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo group by m.data";
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", saida);
		
		List<Double> medias =  query.getResultList();
		return medias;
	}
	
	
	
	
}
