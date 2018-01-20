package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Log;
import br.com.caelum.livraria.tx.Transacional;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped // A IMPORTA��O � DIFERENTE E MUDA PARA javax.faces.view.ViewScoped
// SERIALIZABLE � NECESS�RIO NO CDI PARA QUE JOGUE O OBJETO NO HD
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//IR� INJETAR O DAO NO BEAN (CDI) Context and Dependency Injection
	@Inject
	private AutorDao dao; //CDI faz new AutorDao() e injeta

	private Autor autor = new Autor();

	private Integer autorId;

	

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	@Log
	public void carregarAutorPelaId() {
		this.autor = this.dao.buscaPorId(autorId);
	}

	public Autor getAutor() {
		return this.autor;
	}
	
	@Log
	public List<Autor> getAutores() {

		return this.dao.listaTodos();
	}
	
	@Transacional
	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}

		// NECESS�RIO FAZER ISSO POIS QUANDO O BOT�O � CLICADO EM GRAVAR
		// A VIEW N�O PUXAR� O MESMO OBJETO, FAZENDO COM QUE O INPUT N�O VENHA
		// PREENCHIDO AP�S SER INSERIDO
		// UM NOVO AUTOR
		this.autor = new Autor();

		// ESSE RETORNO FAZ O REDIRECIONAMENTO DA P�GINA PARA A P�GINA LIVROS E
		// DO LADO DO CLIENTE
		// POR CONTA DO PAR�METRO FACES-REDIRECT=TRUE
		// return "livro?faces-redirect=true";

		// PARA DEFINIR MELHOR A INTEN��O DO RETORNO � UTILIZADO A LINHA ABAIXO:
		return new RedirectView("livro");

	}
	
	@Transacional
	public void remove(Autor autor) {
		System.out.println("Removendo autor");

		this.dao.remove(autor);
	}

	public void carregar(Autor autor) {
		System.out.println("Carregando autor");

		this.autor = autor;
	}

}
