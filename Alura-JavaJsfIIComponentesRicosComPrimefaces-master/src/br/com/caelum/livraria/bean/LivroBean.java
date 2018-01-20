package br.com.caelum.livraria.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.LivroDataModel;

//INDICAR QUE A CLASSE SERÁ GERENCIADA PELO JSF
@ManagedBean
// SEM COLOCAR O VIEWSCOPE, EM CADA REQUEST SEMPRE É CRIADO UM OBJETO NOVO
// E SEMPRE É PERDIDO A RELAÇÃO DO LIVRO COM O AUTOR
// POR CONTA DISSO É NECESSÁRIO UTILIZAR O VIEWSCOPE
// QUE MANTÉM O OBJETO POR MAIS TEMPO
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;

	private List<Livro> livros;
	
	private LivroDataModel livroDataModel = new LivroDataModel();
	
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");

	public List<String> getGeneros() {
	    return generos;
	}
	
	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}

	public void setLivroDataModel(LivroDataModel livroDataModel) {
		this.livroDataModel = livroDataModel;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public void carregarLivroPelaId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(livroId);
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Livro getLivro() {
		return this.livro;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public List<Livro> getLivros() {
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if (this.livros == null) {
			this.livros = dao.listaTodos();

		}
		return livros;
	}

	public void removeAutor(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}

	public void gravar() {

		System.out.println("Gravando livro: " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			// LANÇA UMA EXCEPTION PARA O USUÁRIO COM O TÍTULO COM A MENSAGEM
			// ABAIXO
			// PODE SER MELHORADO
			// throw new RuntimeException("Livro deve ter pelo menos um
			// Autor.");
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
		}

		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if (this.livro.getId() == null) {
			System.out.println("Gravou");

			dao.adiciona(this.livro);
			this.livros = dao.listaTodos();
		} else {
			System.out.println("Atualizou");
			dao.atualiza(this.livro);
		}

		this.livro = new Livro();
		// this.livro=null;

	}

	public void remover(Livro livro) {
		System.out.println("Removendo livro");
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		dao.remove(livro);
		this.livros = dao.listaTodos();

	}

	public void carregar(Livro livro) {
		System.out.println("Carregando livro");
		this.livro = livro;
	}

	public String formAutor() {
		System.out.println("Chamada do formulário do Autor");
		// RETORNANDO APENAS AUTOR A MUDANÇA DE PÁGINA SERÁ FEITA DO LADO DO
		// SERVIDOR
		// MANTENDO O NOME DA PÁGINA DA QUAL O USUÁRIO ESTAVA ANTES DE MUDAR
		// return "autor";

		// RETORNANDO A LINHA ABAIXO FAZ COM QUE O O REDIRECIONAMENTO SEJA FEITO
		// DO LADO DO CLIENTE
		return "autor?faces-redirect=true";
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
		}
	}

	public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale) { // java.util.Locale

		// tirando espaços do filtro
		String textoDigitado = (filtroDigitado == null) ? null : filtroDigitado.toString().trim();

		System.out.println("Filtrando pelo " + textoDigitado + ", Valor do elemento: " + valorColuna);

		// o filtro é nulo ou vazio?
		if (textoDigitado == null || textoDigitado.equals("")) {
			return true;
		}

		// elemento da tabela é nulo?
		if (valorColuna == null) {
			return false;
		}

		try {
			// fazendo o parsing do filtro para converter para Double
			Double precoDigitado = Double.valueOf(textoDigitado);
			Double precoColuna = (Double) valorColuna;

			// comparando os valores, compareTo devolve um valor negativo se o
			// value é menor do que o filtro
			return precoColuna.compareTo(precoDigitado) < 0;

		} catch (NumberFormatException e) {

			// usuario nao digitou um numero
			return false;
		}
	}

}
