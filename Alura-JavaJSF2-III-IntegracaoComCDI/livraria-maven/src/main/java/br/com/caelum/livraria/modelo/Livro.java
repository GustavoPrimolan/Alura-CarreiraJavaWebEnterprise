package br.com.caelum.livraria.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Livro implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String titulo;
	private String genero;
	private String isbn;
	private double preco;
	//@Temporal(TemporalType.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataLancamento = Calendar.getInstance();
	
	//CARREGADO DE FORMA LAZY - PREGUIÇOSAMENTE POIS NÃO CARREGA OS AUTORES
	//@ManyToMany
	//SOLUÇÃO SUJA ABAIXO
	//QUANDO CARREGA O LIVRO, AUTOMATICAMENTE SÃO CARREGADO OS AUTORES - SOLUÇÃO SUJA fecht=FetchtYPE.EAGER
	//@ManyToMany(fetch=FetchType.EAGER)
	//POR PADRÃO É LAZY - AO CARREGAR O LIVRO, NÃO SERÃO CARREGADO AUTOMATICAMENTE OS AUTORES
	@ManyToMany
	private List<Autor> autores = new ArrayList<Autor>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}
	
	public void removeAutor(Autor autor){
		this.autores.remove(autor);
	}
	
	
	
	
	
	
}