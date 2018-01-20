package br.com.caelum.livraria.tx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@InterceptorBinding //DIZ QUE A ANOTAÇÃO É UTILIZADO PARA ACIONAR O GERENCIADOR DE TRANSAÇÃO
@Target({ElementType.METHOD, ElementType.TYPE})//ARRAY QUE PERMITE A UTILIZAÇÃO DA ANOTAÇÃO EM MÉTODOS E EM CLASSES (TYPE)
@Retention(RetentionPolicy.RUNTIME)//VÁLIDA NA HORA DE RODAR
public @interface Transacional {

}
