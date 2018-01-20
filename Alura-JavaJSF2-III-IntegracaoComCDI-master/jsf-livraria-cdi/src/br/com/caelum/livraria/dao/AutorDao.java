package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;

@SuppressWarnings("serial")
public class AutorDao implements Serializable{

	@Inject
	EntityManager em; //CDI FAZ new EntityManager
	private DAO<Autor> dao;

	//QUANDO CDI CRIA O DAO ELE JÁ EXECUTA O MÉTODO QUE ESTÁ COM O PostConstruct
	@PostConstruct
	void init() {
		this.dao = new DAO<Autor>(this.em, Autor.class);
	}

	public Autor buscaPorId(Integer autorId) {

		return this.dao.buscaPorId(autorId);
	}

	public List<Autor> listaTodos() {
		return this.dao.listaTodos();
	}

	public void adiciona(Autor autor) {
		this.dao.adiciona(autor);
	}

	public void atualiza(Autor autor) {
		this.dao.atualiza(autor);
	}

	public void remove(Autor autor) {
		this.dao.atualiza(autor);
	}

}
