package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

//INDICAR QUE A CLASSE SERÁ GERENCIADA PELO JSF
@ManagedBean
//SEM COLOCAR O VIEWSCOPE, EM CADA REQUEST SEMPRE É CRIADO UM OBJETO NOVO
//E SEMPRE É PERDIDO A RELAÇÃO DO LIVRO COM O AUTOR
//POR CONTA DISSO É NECESSÁRIO UTILIZAR O VIEWSCOPE
//QUE MANTÉM O OBJETO POR MAIS TEMPO
@ViewScoped
public class LivroBean {
	
	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;
	
	
	
	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public void carregarLivroPelaId(){
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

	public Livro getLivro(){
		return this.livro;
	}
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros(){
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public void removeAutor(Autor autor){
		this.livro.removeAutor(autor);
	}
	
	public void gravarAutor(){
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}
	
	
	
	
	public void gravar() {
		
		System.out.println("Gravando livro: " + this.livro.getTitulo());
		
		if(livro.getAutores().isEmpty()){
			//LANÇA UMA EXCEPTION PARA O USUÁRIO COM O TÍTULO COM A MENSAGEM ABAIXO
			//PODE SER MELHORADO
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
		}
		
		if(this.livro.getId() == null){
			System.out.println("Gravou");
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		}else{
			System.out.println("Atualizou");
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		
		
		
		
		
	}
	
	
	public void remover(Livro livro){
		System.out.println("Removendo livro");
		
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	
	
	public void carregar(Livro livro){
		System.out.println("Carregando livro");
		this.livro = livro;
	}
	
	public String formAutor(){
		System.out.println("Chamada do formulário do Autor");
		//RETORNANDO APENAS AUTOR A MUDANÇA DE PÁGINA SERÁ FEITA DO LADO DO SERVIDOR
		//MANTENDO O NOME DA PÁGINA DA QUAL O USUÁRIO ESTAVA ANTES DE MUDAR
		//return "autor";
		
		//RETORNANDO A LINHA ABAIXO FAZ COM QUE O O REDIRECIONAMENTO SEJA FEITO DO LADO DO CLIENTE
		return "autor?faces-redirect=true";
	}
	
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		if(!valor.startsWith("1")){
			throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
		}
	}
	
}
