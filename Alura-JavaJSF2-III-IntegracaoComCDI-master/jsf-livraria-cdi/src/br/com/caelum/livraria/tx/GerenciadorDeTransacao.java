package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@SuppressWarnings("serial")
@Transacional // PEGA A ANOTAÇÃO QUE CRIEI
@Interceptor // NO MUNDO CDI, ESSES TIPOS DE CLASSE QUE FAZEM ALGO ANTES (EXECUTA BEGIN) E
				// DEPOIS(EXECUTA COMMIT) SE CHAMA INTERCEPTORS
				// É NECESSÁRIO COLOCAR A CLASSE O ARQUIVO BEANS.XML
public class GerenciadorDeTransacao implements Serializable {

	@Inject
	EntityManager manager;

	// EXECUTA TRANSAÇÃO E O CDI IRÁ GERENCIAR
	// CONTEXTO DE INVOCAÇÃO COMO PARÂMETRO
	// PARA SABER QUAL MÉTODO DO LIVRO BEAN DEVERÁ SER EXECUTADO
	// FAZ O GERÊNCIADOR EXECUTAR ESSE MÉTODO
	@AroundInvoke
	public Object executaTX(InvocationContext contexto) throws Exception {
		// ABRE TRANSAÇÃO
		manager.getTransaction().begin();

		System.out.println("Abrindo TX");

		// CHAMA OS DAOS QUE PRECISAM DE UMA TX (TRANSAÇÃO)
		Object resultado = contexto.proceed();

		System.out.println("Fechando TX");
		// COMMITA A TRANSAÇÃO
		manager.getTransaction().commit();

		return resultado;
	}

}
