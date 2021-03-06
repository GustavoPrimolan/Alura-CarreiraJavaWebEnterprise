package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Venda;

@SuppressWarnings("serial")
public class VendasDao implements Serializable{
	
	@Inject
	EntityManager em;
	private DAO<Venda> dao;
	
	@PostConstruct
	public void init() {
		this.dao = new DAO<Venda>(this.em, Venda.class);
	}
	
	public List<Venda> listaTodos(){
		
		return this.dao.listaTodos();
		
	}
	
}
