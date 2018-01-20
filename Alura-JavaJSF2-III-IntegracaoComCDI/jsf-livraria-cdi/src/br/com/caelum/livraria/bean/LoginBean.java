package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private FacesContext context;
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String efetuarLogin(){
		System.out.println("Fazendo login do usuario: " + this.usuario.getEmail());
		
		boolean existe = usuarioDao.existe(this.usuario);
		//FAZER A AUTENTICAÇÃO PARA VERIFICAR SE O USUÁRIO PODE ACESSAR AS OUTRAS PÁGINAS
		if(existe){
			
			//COLOCA OS DADOS DO USUÁRIO NA SESSION
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
					
					
			return "livro?faces-redirect=true";
		}
		
		//O FLASH DURA 2 REQUISIÇÕES PARA QUE A MENSAGEM DO USUÁRIO NÃO ENCONTRADO NÃO SUMA
		context.getExternalContext().getFlash().setKeepMessages(true);
		//ASSOCIA AO COMPONENTE MESSAGES
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));
		return "login?faces-redirect=true";
	}
	
	public String deslogar(){
		
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
	
}
