package br.com.caelum.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(query="select distinct avg(m.valor) from Movimentacao m where m.conta = :pConta"
		+ " and m.tipo = :pTipo group by m.data", name="MediasPorDiaETipo")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//BigDecimal POSSUI MAIS PRECISAO
	private BigDecimal valor;
	
	//Enumereted IRÁ PEGAR UM ENUM E O TIPO DO ENUM É STRING (PARA PEGAR A STRING DO ENUM)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	//CADA BANCO TEM UMA FORMA DE LIDAR COM DATA DE UMA FORMA DIFERENTE
	//TIMESTAMP = DATA E HORA
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	private String descricao;

	
	//MUITAS MOVIMENTAÇÕES PARA UMA CONTA
	@ManyToOne
	private Conta conta;
	
	
	@ManyToMany
	private List<Categoria> categorias;
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	
	
}
