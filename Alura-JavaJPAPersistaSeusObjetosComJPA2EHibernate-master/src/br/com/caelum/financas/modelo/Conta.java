package br.com.caelum.financas.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta {

	// DIZ QUAL É O ATRIBUTO QUE É O ID
	// GERÊNCIA A CHAVE PRIMEIRA, SEJA POR AUTO-INCREMENT OU POR SEQUENCE,
	// DEPENDENDO DO BANCO
	// SETA TAMBÉM A ESTRATÉGIA QUE ESTÁ UTILIZANDO, NO CASO É AUTO-INCREMENT
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titular;
	private String numero;
	private String banco;
	private String agencia;

	@OneToMany(mappedBy="conta")
	private List<Movimentacao> movimentacoes;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

}
