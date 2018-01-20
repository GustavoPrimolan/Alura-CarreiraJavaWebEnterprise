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
@ViewScoped // A IMPORTAÇÃO É DIFERENTE E MUDA PARA javax.faces.view.ViewScoped
// SERIALIZABLE É NECESSÁRIO NO CDI PARA QUE JOGUE O OBJETO NO HD
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//IRÁ INJETAR O DAO NO BEAN (CDI) Context and Dependency Injection
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

		// NECESSÁRIO FAZER ISSO POIS QUANDO O BOTÃO É CLICADO EM GRAVAR
		// A VIEW NÃO PUXARÁ O MESMO OBJETO, FAZENDO COM QUE O INPUT NÃO VENHA
		// PREENCHIDO APÓS SER INSERIDO
		// UM NOVO AUTOR
		this.autor = new Autor();

		// ESSE RETORNO FAZ O REDIRECIONAMENTO DA PÁGINA PARA A PÁGINA LIVROS E
		// DO LADO DO CLIENTE
		// POR CONTA DO PARÂMETRO FACES-REDIRECT=TRUE
		// return "livro?faces-redirect=true";

		// PARA DEFINIR MELHOR A INTENÇÃO DO RETORNO É UTILIZADO A LINHA ABAIXO:
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
