package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	
	private Integer autorId;
	
	
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPelaId(){
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}
	
	
	public Autor getAutor() {
		return this.autor;
	}

	public List<Autor> getAutores() {

		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
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

	public void remove(Autor autor) {
		System.out.println("Removendo autor");

		new DAO<Autor>(Autor.class).remove(autor);
	}

	public void carregar(Autor autor) {
		System.out.println("Carregando autor");

		this.autor = autor;
	}

}
