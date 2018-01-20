package br.com.caelum.livraria.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.LivroDao;

@SuppressWarnings("serial")
public class LivroDataModel extends LazyDataModel<Livro> implements Serializable{
	
	@Inject
	private LivroDao dao;
	
	@PostConstruct
	public void init(){
		
		super.setRowCount(dao.quantidadeDeElementos());
	}
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
	    String titulo = (String) filtros.get("titulo");

	    return dao.listaTodosPaginada(inicio, quantidade, "titulo", titulo);
	}
	
}
