package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String efetuarLogin(){
		System.out.println("Fazendo login do usuario: " + this.usuario.getEmail());
		
		boolean existe = new UsuarioDao().existe(this.usuario);
		//FAZER A AUTENTICA��O PARA VERIFICAR SE O USU�RIO PODE ACESSAR AS OUTRAS P�GINAS
		FacesContext context = FacesContext.getCurrentInstance();
		if(existe){
			
			//COLOCA OS DADOS DO USU�RIO NA SESSION
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
					
					
			return "livro?faces-redirect=true";
		}
		
		
		//PRIMEIRO PAR�METRO ASSOCIA COM ALGUM COMPONENTE E O SEGUNDO
		//MOSTRA A MENSAGEM EM S�
		//ASSOCIADO AO COMPONENTE
		//context.addMessage("login:email", new FacesMessage("Usu�rio n�o encontrato"));
		
		
		//O FLASH DURA 2 REQUISI��ES PARA QUE A MENSAGEM DO USU�RIO N�O ENCONTRADO N�O SUMA
		context.getExternalContext().getFlash().setKeepMessages(true);
		//ASSOCIA AO COMPONENTE MESSAGES
		context.addMessage(null, new FacesMessage("Usu�rio n�o encontrado"));
		return "login?faces-redirect=true";
	}
	
	public String deslogar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
	
}
