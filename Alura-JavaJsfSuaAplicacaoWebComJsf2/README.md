# Alura-JavaJsfSuaAplicacaoWebComJsf2
Aplicação desenvolvida seguindo o curso de JSF I da plataforma Alura

---------------------------------------------------------------------------
Seção 01 - Introdução ao desenvolvimento web com JSF

Aplicação Desktop?
Aplicação local e acessa diretamente o banco de dados
Componente ricos para o sistemas operacional
Orientados aos eventos
Telas mantém estado - Componentes se lembram do estados do usuário
Componentes ricos
	-Calendários, menus, drag&drop
Orientado ao evento
	-onclick, onchange
Stateful
	-Telas mantém estados

Tudo isso ganhou o nome de Rapid Application Development (Desenvolvimento Rápido de Aplicações).


Há desvantagens:
	Cada usuário tem uma cópia dessa aplicação
	É necessário atualizar em cada usuário a aplicação.
	Cliente gordo.
	É difícil debugar a aplicação, por rodar no usuário.
	Problemas de manutenção e gerenciabilidade.

Surgiram as aplicações WEB
	-Servidor central e os clientes acessam através do protocolo HTTP.
	-Necessita apenas do navagedor
	-Cada requisição trafega o HTML do servidor para o cliente
	-Toda regra executa no servidor
	-Tudo isso chamado de cliente magro

Facilita a manutenção e a gerenciabilidade
	-Necessário conhecer HTML, CSS e JavaScript para a Interface;
	-Modelo de Request-Response;
	-Conhecer o protocolo HTTP
	-Tudo a cargo do desenvolvedor

Desktop				      |	WEB
Componentes ricos		      |	html, css, js e bibliotecas
Orientado ao evento		      |	Orientado ao request-response
Mantem estado(stateful)		      |	Sem estado (http, stateless)
Dificulta a manutenção e gerenciamento|	Facilita a manutenção e gerenciamento

Ideal seria mesclar os dois lados e aproveitas as vantagens de cada um

Desktop para Web
Componentes ricos
Orientado ao evento
Mantém estado (stateful)
Facilita a manutenção e gerenciamento

Éssa é exatamente a ideia para componentes WEB

Componentes para WEB
	-JSF (JavaServer Faces)
	-ApacheWicket
	-Vaadin
	-Tapestry 5 - Code less, deliver more
	-GWT

JSF é um padrão Java EE para desenvolvimento web baseado em componentes mantido pelo JCP.


-Especificação JSF JSR-245
Java Specification Request
Java Community Process http://jcp.org


Desenvolvimento WEB Java
	-Especificação JSF 2 JSR-314
	-Mojarra (Pronuncia Morrará) (Implementação Referencial) http://javaserverfaces.java.net/
	-Apache MyFaces

Será utilizado o JSF com Primefaces


O primeiro projeto e os componentes poderosos do Primefaces
Para mostrar a simplicidade dos componentes ricos, preparamos para você um projeto base no Eclipse. Nos próximos vídeos veremos em detalhes a configuração desse projeto. Queremos com ele mostrar a simplicidade do desenvolvimento de interfaces ricas para web (Rich Internet Application) com componentes JSF.

As bibliotecas necessárias (JARs) já estão disponíveis na pasta lib e também temos uma página inicial criada, a olamundo.xhtml, como primeiro exemplo. Ela já está pronta para os componentes do PrimeFaces e tem um corpo que mostra um simples cabeçalho HTML.

Podemos iniciar o servidor e chamar a página no navegador http://localhost:8080/jsf/olamundo.xhtml. Aparecerá a nossa página de exemplo.

De volta ao Eclipse, vamos experimentar alguns componentes do PrimeFaces para ilustrar o uso. O primeiro será o calendário. Na página olamundo.xhtml, que está dentro da pasta WebContent do projeto jsf vamos digitar <calendar /> logo após o <br /> para referenciar o componente.

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
     xmlns:f="http://java.sun.com/jsf/core"
     xmlns:h="http://java.sun.com/jsf/html"
     xmlns:p="http://primefaces.org/ui">

<h:head>
     <title>Primeiro Exemplo usando JSF - olamundo.xhtml</title>
</h:head>

<h:body>
     <h2>Exemplo Ola Mundo com JSF 2.0 e PrimeFaces</h2>
     <br />
     <p:calendar />
</h:body>
</html>
Agora é só salvar e está pronto para testar no navegador. Temos então um input que renderiza automaticamente um calendário.

Como próximo componente vamos experimentar um Panel. Novamente, super simples de usar. Basta colocar p:panel e definir um cabeçalho e um corpo. Ao testar aparece o panel, mas ele está fixo na página, e não pode ser arrastado. Vamos voltar ao Eclipse e ativar essa funcionalidade com o componentes p:draggable. Aqui é preciso criar uma ligação entre panel e draggable pela ID do componente.

<!-- código omitido -->

     <p:panel id="panel"  header="JSF - Componentes para Web">
         arraste-me!
     </p:panel>
     <p:draggable for="panel" />

<!-- código omitido -->
Novamente vamos testar no navegador e agora sim podemos arrastar o panel livremente. Repare que em nenhum momento nos preocupamos com JavaScript, CSS ou HTML. Usamos apenas componentes para definir uma interface bastante agradável.

Por último, como vimos a agenda no showcase do PrimeFaces. Vamos testar esse componente também. Ele se chama p:schedule e também é de simples utilização.

     <p:schedule />
Ao atualizar no navegador, aparece a agenda sofisticada.

Introdução ao JSF com Mojarra e PrimeFaces
Como dito antes, nosso projeto utilizará a implementação Mojarra do JSF. Ela já define o modelo de desenvolvimento e oferece alguns componentes bem básicos. Nada além de inputs, botões e ComboBox simples. Não há componentes sofisticados dentro da especificação. Isto é proposital, pois o mundo web evolui rápido (principalmente na questão das interfaces gráficas).
Para atender a demanda dos desenvolvedores por componentes mais sofisticados, existem várias extensões do JSF que seguem o mesmo ciclo e modelo da especificação. São exemplos dessas bibliotecas: PrimeFaces, RichFaces ou IceFaces.


O JSF é um framework que une o melhor do desenvolvimento Web e Desktop. Pensando assim, podemos dizer que o JSF:

JSF tenta unir as vantagens do desenvolvimento Desktop (como o uso de componentes) e a facilidade de manutenção e implantação de aplicações Web. Por isso o JSF é chamado um framework Web baseado em componentes.

São características comuns no desenvolvimento RAD (Rapid Application Development) e que também podem ser encontradas no desenvolvimento com JavaServer Faces:
São as boas práticas do mundo orientado a objetos que o JSF tenta trazer para o mundo Web:
uso de componentes
desenvolvimento orientado ao evento
desenvolvimento stateful
Parabéns, você acertou!


Quais são as vantagens de seguir um especificação/padrão Java EE como JSF?
Sendo um padrão no desenvolvimento Web o JSF é mantido por várias entidades (pessoas, empresas e organizações) dentro do Java Community Process (http://jcp.org). Uma especificação evolui de longo prazo, é algo que é mantido por vários anos trazendo uma segurança sobre o futuro para o mercado.
JSF como padrão define o desenvolvimento de uma aplicação Java Web e isso independente de um fornecedor especifico (não há vendor-lockin). Sendo assim o mercado assimila o padrão rápido e fornece mais documentação e fontes em geral.

Claro que a qualidade e utilidade da implementação importam (entre outros critérios), mas muitas empresas e desenvolvedores preferem seguir a especificação da solução proprietária.

Mojarra e MyFaces são

Ambas, Mojarra e MyFaces, são implementações da especificação JSF. Mojarra é a implementação referencial da Oracle, MyFaces é a implementação da Apache Software Foundation.
Como as duas seguem a mesma especificação podemos escolher uma ou outra para desenvolvimento JSF.


A especificação define o modelo de desenvolvimento e oferece alguns componentes bem básicos, nada além de inputs, botões e combo boxes simples. Isso é intencional, porque:
A interface no desenvolvimento web evolui rapido, é algo dificil de padronizar em longo prazo. Por isso a especificação JSF apenas oferece componentes básicos.
Veremos componentes sofisticados e mais ricos nas extensões do JSF.

Existem as implementações do JSF da Oracle e da Apache e nada impede que outras implementações sejam criadas seguindo a especificação. Além delas, há extensões que adicionam componentes mais sofisticados. Exemplos delas são:
Também verifique os sites e showcases das extensões: primefaces, richfaces e icefaces.



----------------------------------------------------------------------------
Seção 02 - Configuração doambiente e os primeiros passos com JSF

Desenvolvimento Web com JSF

Projeto:jsf-livraria
Livro * <-----> * Autor


Entendendo o domínio da aplicação
O nosso projeto web facilitará o trabalho em uma Livraria, onde o usuário poderá cadastrar livros e autores. Trabalharemos com objetos do tipo Livro associados a autores. Trata-se de uma relacionamento muitos-para-muitos. Antes de modelarmos as classes, vamos criar e configurar o projeto.


Atenção na importação
Ao importar o projeto o Eclipse automaticamente fará uma validação rígida dos arquivos. Nesse caso Eclipse acusa a assinatura de alguns métodos do projeto. Para desabilitar essa validação basta acessar:

Menu Preferences -> Item Validation:

Tire:

1) Facelet HTML Validator

2 ) JSF View Application Configuration Validator

3) JSF View Validator

Isto é preciso pois o Eclipse quer que todos os métodos associados com o atributo action do componente h:commandButton devolvem obrigatoriamente um String (que não é preciso).

Configurando o servidor web
A IDE Eclipse já está rodando, mas é preciso fechar a janela de "boas-vindas" para ver a perspectiva padrão. Durante o treinamento, utilizaremos a perspectiva Java EE, mas podemos trocar a qualquer momento essa perspectiva por meio do botão "+" no lado superior direito do Eclipse.

O primeiro passo é configurar o Servlet Container Tomcat dentro do Eclipse. Para tal, utilizaremos a aba (view) Server. Caso essa janela tenha sido fechada, podemos reabri-la através do atalho Ctrl + 3 e digitando server.

Vamos então preparar o Tomcat definindo uma nova configuração do servidor. Na caixa de diálogo New server, escolheremos Apache Tomcat v7.0 Server, em seguida, apertando next, definimos o lugar onde o Tomcat foi instalado (ou descompactado). No nosso caso, como já temos uma distribuição do Tomcat baixada e extraída, vamos apontar para ela. Ao finalizar aparecerá o Tomcat na view Server.

Criação do projeto JSF
O próximo passo é criar o projeto web. Vamos no menu File -> New -> Dynamic Web Project. Chamaremos o projeto de jsf-livraria. É preciso verificar se o Tomcat está escolhido como servidor, inclusive se o module version encontra-se na versão 3.0.

No combo box Configuration, escolhemos Java Server Faces. Confirmaremos as duas próximas telas até chegar na tela JSF Capabilities.

Aqui configuraremos de onde vem a biblioteca JSF. Para este projeto, copiaremos o JAR após a criação do projeto. Ou seja, desabilitamos o uso de qualquer biblioteca fornecida pelo Eclipse.

Um pouco mais abaixo há a configuração do servlet que representa o controlador. Usaremos praticamente a mesma configuração. Apenas o mapeamento será alterado. Queremos que qualquer requisição que termine com *.xhtml seja processada pelo FacesServlet.

Ao confirmar, será gerado o novo projeto, visível ao lado esquerdo, no view project-explorer. Vamos arrastar o projeto ao servidor Tomcat para simplificar o deploy da aplicação.

Ao abrir o projeto, veremos a estrutura básica com as classes Java dentro da pasta src e todos os arquivos Web dentro da pasta WebContent.

Dentro do WebContent encontramos uma pasta WEB-INF com dois arquivos de configuração. O primeiro, web.xml, é relacionado com o especificação servlet. Ele contém a declaração do FacesServlet, com aquela configuração que alteramos na criação do projeto.

O segundo XML é o arquivo de configuração relacionado com o mundo JSF. Como o JSF na versão dois encoraja o uso de anotações, este arquivo torna-se pouco usado, sendo muito mais importante na primeira versão do JSF.

Como vimos no web.xml, há a configuração do FacesServlet. Essa classe faz parte da implementação JSF e deve estar presente. É preciso copiar o JAR do Mojarra para o nosso projeto.

Já baixamos a biblioteca e como se trata de um projeto web, o JAR deve ser copiado para a pasta WEB-INF/lib e assim fará parte do buildpath do projeto.

Começando a implementar nossa livraria
Tudo pronto para desenvolver com JSF. No projeto, vamos começar com o cadastro de livros. O objetivo é criar um formulário com os componentes da especificação.

Vamos selecionar a pasta WebContent, clicar com o botão direito, New HTML File. O arquivo se chama livro.xhtml. Cuidado com a extensão que deve ser XHTML. Ao apertar next, podemos escolher um template. Usaremos xhtml 1.0 transitional.

No arquivo apagaremos tudo que está dentro das tags HTML, pois utilizaremos os componentes JSF. Para declará-los é preciso adicionar um XML namespace na abertura da tag HTML.

Para tal, digitamos xmlns:h, onde o "h" é o apelido do namespace para a uri "http://java.sun.com/jsf/html". Ctrl + Espaço ajuda a auto-completar. Atenção para não confundir com o namespace JSTL.

<? xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
        xmlns:h="http://java.sun.com/jsf/html">

</html>
A partir da definição no cabeçalho, podemos usar o apelido "h" para declarar os componentes JSF. O primeiro componente que usaremos é o h:body que define o corpo da página. Dentro do body vamos declarar o formulário através da tag h:form. Repare que aqui, diferente da tag form do HTML, o componente JSF não possui um atributo action.


No formulário, usaremos o primeiro componente que captura uma entrada do usuário, o h:inputText. Além do inputText, vamos utilizar um botão para executar uma ação. A especificação define comandos para isto. Nesse caso, um h:commandButton. Com o atributo value definimos "Gravar", que aparecerá na tela.

<!-- cabeçalho omitido -->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">
    <h:body>
        <h:form>
            <h:inputText />
            <h:commandButton value="Gravar" />
        </h:form>
    </h:body>
</html>
Com o Tomcat rodando já podemos testar a página pelo navegador, accessando http://localhost:8080/jsf-livraria/livro.xhtml. O formulário aparecerá.

Controlador e Visão no JSF
Parece que chamamos diretamente a página livro.xhtml, mas isso não é verdade. Lembrando que no web.xml teremos o FacesServlet mapeado para receber qualquer requisição que termina com xhtml. Acontece que ao enviar a requisição, o Tomcat delega o fluxo para o servlet da nossa aplicação jsf-livraria. O servlet recebe a chamada e decide qual página chamará. É ele que está no controle do fluxo, e por isso também é chamado controlador.

O controlador lê o xhtml e instancia os componentes declarados. No final ele pede aos componentes a apresentação HTML e devolve o HTML como resposta para o navegador.


Podemos provar isso também no navegador. Ao visualizar o código fonte da página, aparece o HTML puro. Esse HTML é bem diferente da página xhtml.
Completando o formulário
Vamos continuar e completar o formulário. Primeiro utilizaremos o componente h:outputLabel para associar um label com o input. A ligação é feita pela id da componente inputText e o atributo for do outputLabel. Ao testar no navegador aparece o label na frente do input.

<!-- cabeçalho omitido -->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">
    <h:body>
        <h:form>
            <h:outputText value="Titulo: " for="titulo"/>
            <h:inputText id="titulo"/>
            <h:commandButton value="Gravar" />
        </h:form>
    </h:body>
</html>
Mesmo usando componentes JSF, podemos aproveitar as tags do mundo HTML. Vamos definir um cabeçalho na página usando a tag h1. Além disso, usaremos um fieldset para agrupar os elementos do formulário com um título indicado pela tag legend. Visualizando mais uma vez no navegador, podemos perceber que o formulário já está mais organizado.

<!-- cabeçalho omitido -->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">
<h:body>
        <h1>Novo Livro</h1>
        <h:form>
            <fieldset>
                <legend>Dados do Livro</legend>
                <h:outputLabel value="Titulo:" for="titulo" />
                <h:inputText id="titulo" />
            </fieldset>
        </h:form>
    </h:body>
Falta criar os componentes para o ISBN, preço e data de lançamento do Livro. Copiaremos o outputLabel e inputText para facilitar o trabalho. Primeiro para ISBN, segundo o preço e terceiro a data de lançamento. Visualizaremos mais uma vez o resultado no navegador.

<!-- cabeçalho omitido -->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">
    <h:body>
        <h1>Novo Livro</h1>
        <h:form>
            <fieldset>
                <legend>Dados do Livro</legend>
                    <h:outputLabel value="Titulo:" for="titulo" />
                    <h:inputText id="titulo" />
                    <h:outputLabel value="ISBN:" for="isbn" />
                    <h:inputText id="isbn" />
                    <h:outputLabel value="Preço:" for="preco" />
                    <h:inputText id="preco" />
                    <h:outputLabel value="Data de Lançamento:" for="dataLancamento" />
                    <h:inputText id="dataLancamento" />
                    <h:commandButton value="Gravar" />
            </fieldset>
        </h:form>
    </h:body>
</html>
Todos os componentes foram renderizados para HTML. Um após o outro, ocupando todo espaço horizontal da tela. Para melhorar o layout, utilizaremos o componente h:panelGrid, que funciona como um simples layoutmanager, organizando os componentes verticais.

Também podemos definir a quantidade de colunas no grid. Para tal, usamos o atributo columns do componente h:panelGrid para criar duas colunas:

<!-- cabeçalho omitido -->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">
    <h:body>
        <h1>Novo Livro</h1>
        <h:form>
            <fieldset>
                <legend>Dados do Livro</legend>
                <h:panelGrid columns="2">
                    <!-- inputs omitido -->
                    <h:commandButton value="Gravar" />
                </h:panelGrid>
            </fieldset>
        </h:form>
    </h:body>
</html>

Ligando Managed Beans a componentes visuais
Conseguimos criar a tela, mas também queremos executar uma ação para gravar o livro. Ou seja, quando o usuário apertar o botão "Gravar" no navegador, este componente disparará a execução de um método no lado do servidor. Para isso vamos criar uma classe, associando-a com o componente.

Cada componente possui atributos especiais para criar uma ligação com uma classe. No caso do commandButton, usaremos o atributo action. Nele definiremos a classe e o metódo a executar, mas primeiro é preciso criar esta classe.

Clique com o botão direito na pasta src, em seguinda, New -> Class. Podemos utilizar qualquer nome da classe. Em nosso caso, vamos chamá-la de LivroBean, dentro do package br.com.caelum.livraria.bean:

package br.com.caelum.livraria.bean;

public class LivroBean {
}
Na classe criaremos o método gravar(), que imprime apenas uma informação no console. Além disso, é preciso indicar que a classe será gerenciada pelo JSF. Isso é feito através da anotação @ManagedBean:

package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LivroBean {

    public void gravar() {
        System.out.println("Gravando livro ");
    }
}
Voltando ao formulário, podemos agora ligar o comando ao método gravar(). Essa ligação (ou binding) é feita com uma linguagem de expressão (Expression Language). Uma expressão sempre começa com #{ e termina com }, dentro dela usaremos o nome da classe, "ponto (.)" seguido do nome do método:

    <h:commandButton value="Gravar" action="#{livroBean.gravar}"/>
Antes de testar no navegador é preciso reiniciar o Tomcat. Ao atualizar o formulário no navegador e clicar no botão, podemos observar a saída no console do Eclipse.

Capturando dados do formulário com a classe LivroBean
O próximo passo é capturar os dados do formulário com a classe LivroBean. Para isso definiremos, para cada componente de input, um atributo no LivroBean. Primeiro o titulo e isbn, ambos do tipo String, depois preco do tipo double e a dataLancamento novamente do tipo String. Mais para frente veremos como trabalhar com datas do tipo Date ou Calendar. Por enquanto vamos deixar a data como String.

Além disso, é preciso gerar os getters e setters para cada atributo. Para isso, pressione Ctrl + 3, digitando GGAS. Selecione Generate Getters e Setters e, na janela, todos os atributos para confirmar. Vamos também formatar a classe usando o atalho Ctrl+Shift+F.

package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LivroBean {

    private String titulo;
    private String isbn;
    private double preco;
    private String dataLancamento;

    public void gravar() {
        System.out.println("Gravando livro ");
    }

    //getters e setters
}
Voltando ao livro.xhtml, ligaremos cada input com o atributo ou propriedade da classe LivroBean. Nos componentes de input usaremos o atributo value, pois queremos receber o valor desse componente, mas novamente através de Expression Language. No primeiro input: #{livroBean.titulo}. Faremos a mesma coisa para os demais inputs isbn, depois o preco e no final, a dataLancamento - sempre usando Expression Language:

<!-- cabeçalho omitido -->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">

    <h:body>
        <h1>Novo Livro</h1>
        <h:form>
            <fieldset>
                <legend>Dados do Livro</legend>
                <h:panelGrid columns="2">
                    <h:outputLabel value="Titulo:" for="titulo" />
                    <h:inputText id="titulo" value="#{livroBean.titulo}"/>
                    <h:outputLabel value="ISBN:" for="isbn" />
                    <h:inputText id="isbn" value="#{livroBean.isbn}"/>
                    <h:outputLabel value="Preço:" for="preco" />
                    <h:inputText id="preco" value="#{livroBean.preco}"/>
                    <h:outputLabel value="Data de Lançamento:" for="dataLancamento" />
                    <h:inputText id="dataLancamento" value="#{livroBean.dataLancamento}"/>
                    <h:commandButton value="Gravar" action="#{livroBean.gravar}"/>
                </h:panelGrid>
            </fieldset>
        </h:form>
    </h:body>

</html>
Falta mostrar no método gravar() algum valor do livro que está sendo gravado para podermos verificar o recebimento dos valores. Vamos concatenar o título na saída.

package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LivroBean {

    private String titulo;
    //outros atributos

    public void gravar() {
        System.out.println("Gravando livro " + this.titulo);
    }

    //getters e setters
}
É uma boa prática reiniciar o servidor para ter certeza que as alterações foram publicadas. Também é boa prática chamar o formulário de novo (ou seja, enviando um novo request) para garantir que todos os componentes foram atualizados. Ao atualizar podemos ver uma pequena mudança no formulário: o campo Preço já vem preenchido. Vamos inserir os dados e apertar em Gravar.

O Eclipse mostra Gravando livro com o título no console. No nosso caso, "Fausto". Tudo funcionando. Recebemos os dados do formulário no LivroBean.

Refatoração do ManagedBean para popular o domínio
Ao observar a classe LivroBean podemos criticar vários atributos "soltos". Muito provavelmente precisaremos deles em outras classes a medida que a aplicação crescer, pois eles representam um livro. Faz todo sentido separar a responsabilidade de cadastrar o livro da responsabilidade de ser um livro. Por isso, selecionaremos os atributos e extrairemos uma nova classe que representa um Livro.

Ainda usando o Eclipse, no menu Refactor, selecione Extract Class. Chamaremos a classe de Livro e geraremos os getters e setters. No LivroBean, criaremos um único atributo chamado livro. O Eclipse gerou um nova classe Livro com os atributos e getters/setters.

package br.com.caelum.livraria.bean;

public class Livro {
    private String titulo;
    private String isbn;
    private double preco;
    private String dataLancamento;

    //getters e setters
}
Na classe LivroBean podemos apagar todos os getters. Repare que sobrou apenas um atributo privado que necessita de um getter. Esse getter é utilizado pelos componentes para popular o livro:

@ManagedBean
public class LivroBean {

    private Livro livro = new Livro();

    public Livro getLivro() {
        return livro;
    }

    public void gravar() {
        System.out.println("Gravando livro " + this.livro.getTitulo());
    }
}
Após reiniciar o Tomcat, vamos testar novamente no navegador. Ao chamar o formulário recebemos uma exceção. Há algum problema na página livro.xhtml, na linha 13, com a expressão #{livroBean.titulo}. Ao analisar percebemos que não existe o atributo titulo no LivroBean.

Esse atributo está na classe Livro, mas não atualizamos o livro.xhtml. Na página é preciso alterar as expressões de cada input e declarar o caminho correto. Para o titulo usaremos agora: #{livroBean.livro.titulo}.

<!-- cabeçalho omitido -->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">

    <h:body>
        <h1>Novo Livro</h1>
        <h:form>
            <fieldset>
                <legend>Dados do Livro</legend>
                <h:panelGrid columns="2">
                    <h:outputLabel value="Titulo:" for="titulo" />
                    <h:inputText id="titulo" value="#{livroBean.livro.titulo}"/>
                    <h:outputLabel value="ISBN:" for="isbn" />
                    <h:inputText id="isbn" value="#{livroBean.livro.isbn}"/>
                    <h:outputLabel value="Preço:" for="preco" />
                    <h:inputText id="preco" value="#{livroBean.livro.preco}"/>
                    <h:outputLabel value="Data de Lançamento:" for="dataLancamento" />
                    <h:inputText id="dataLancamento" value="#{livroBean.livro.dataLancamento}"/>
                    <h:commandButton value="Gravar" action="#{livroBean.gravar}"/>
                </h:panelGrid>
            </fieldset>
        </h:form>
    </h:body>

</html>
Por fim, testaremos mais uma vez. Como alteramos apenas o formulário, não é preciso reiniciar o servidor. Vamos atualizar o formulário e inserir os dados. Ao gravar, nenhuma exceção foi lançada.



Durante a criação do projeto jsf-livraria, alteramos o conteúdo de URL Mapping Patterns, na seção JSF Capabilites, para *.xhtml. Com que propósito fizemos essa alteração?
JSF Servlet é definido no arquivo web.xml e fará o papel do controlador dentro do modelo JSF. Sendo mapeado como .xhtml ele recebe qualquer requisição que termine com .xhtml, por exemplo livro.xhtml.


Por ser um projeto web, em que diretório devemos importar a JAR da implementação JSF Mojarra?
Por padrão qualquer JAR da aplicação web deve estar dentro da pasta WEB-INF/lib.


Qual é o namespace correto para utilizar os componentes HTML da especificação JSF?
O namespace para utilizar os componentes HTML da especificação se chama http://java.sun.com/jsf/html. Ele é adicionado na abertura da tag <html>:
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">
   ...
</html


Quando abrimos nossa página, na URL http://localhost:8080/jsf-livraria/livro.xhtml, o Tomcat delega o fluxo para a nossa aplicação. O Servlet recebe a chamada e decide qual página exibirá. Seguindo essa lógica, que nome damos ao Servlet?
O servlet JSF tem o papel do controlador.


Como definir uma classe gerenciada pelo JSF?
Basta colocar a anotação @ManagedBean (do pacote javax.faces.bean) na classe:
@ManagedBean
public class LivroBean  {
Objetos dessa classe serão administrados pelo JSF. Ou seja, o JSF vai decidir quando criar um objeto da classe LivroBean. Podemos acompanhar o trabalho do JSF e colocar um método que é chamado automaticamente quando JSF instancia a LivroBean:

@ManagedBean
public class LivroBean  {

  @PostConstruct
  public void posCriacao() {
    System.out.println("objeto LivroBean foi criado");
  }
}
Repare a anotação @PostConstruct. Esses tipos de métodos também são chamados de callbacks.

Uma vez gerenciado pelo JSF podemos acessar o objeto da classe pela Expression Language dentro de uma página xhtml: #{livroBean}`


Cada componente possui atributos especiais para criar uma ligação com uma classe. No caso do h:commandButton, que atributo devemos declarar para indicar que classe usaremos?
Para ligar um comando com uma classe podemos utilizar o atributo action:
<h:commandButton value="Gravar" action="#{bean.metodo}" />
Há uma alternativa, os comandos também têm um atributo actionListener que funciona de maneira semelhante:

<h:commandButton value="Gravar" actionListener="#{bean.metodo}" />
@ManagedBean
public class Bean {

   public void metodo(ActionEvent event) { //javax.faces.event.ActionEvent
      //implementação
   }
}
A diferença é que um método usado no atributo actionListener sempre deve devolver void enquanto o outro poderia devolver uma String para definir a navegação. Veremos mais sobre a navegação no capítulo 7.


Quando adicionamos o arquivo .jar da biblioteca do Mojarra, incluímos a classe FacesServelet que estava descrita no arquivo web.xml e que ainda estava faltando no nosso projeto. Agora não temos mais nenhuma exception e passamos a ter no log do servidor o suporte do Mojarra para o contexto do nosso projeto (jsf-livraria).

Como vimos no vídeo, nosso ManagedBean ainda está com muitas informações do livro espalhadas pela classe. É importante separarmos a responsabilidade de cadastrar um livro, da responsabilidade de ser um livro, principalmente porque usaremos as informações do livro em várias partes do nosso sistema.


------------------------------------------------------------------------

Seção 03 - Entendendo MVC e integrando o banco de dados com JPA 2

MVC = Model View Controller

JPA para Persistencia

Ciclo de vida dos controladores do JSF
O controlador lê o xhtml
Instancia os componentes declarados
Arvore de componentes
Componente body possui inputs etc

Introdução
Na última aula criamos o projeto web e utilizamos os componentes da especificação JSF. Aprendemos como definir um formulário dentro de um arquivo xhtml. Os componentes foram associados com um ManagedBean, uma classe gerenciadada pelo JSF. O ManagedBean, por sua vez, utilizou o modelo para passar os valores.


Modelo arquitetural MVC: Model-View-Controller
Seguimos um modelo arquitetural de separação em três camadas na qual cada camada possui uma responsabilidade bem definida. A primeira camada é a do controlador, que recebe a requisição e decide qual página chamar. A segunda é a da visão (a definição da interface gráfica). E por último, temos o nosso modelo (que representa o domínio da aplicação). O ManagedBean é um intermediário e sua responsabilidade pode variar. Este modelo arquitetural é chamado Model-View-Controller ou MVC.

O ciclo de vida básico dos componentes JSF
Primeiramente chamamos o autor.xhtml pelo navegador e no lado do servidor, o controlador lê o XHTML. Como já dissemos antes, o controlador instancia os componentes declarados. O resultado é uma árvore de componentes. Nossa tela organiza-se numa estrutura hierárquica. No final, o componente body possui um form que possui um input e assim para frente.

É importante saber que esta árvore de componentes é criada só na primeira requisição (no primeiro GET) e depois fica guardada no sessão HTTP do usuário. O controlador pede dessa árvore em memória e devolve HTML para o navegador.

No próximo passo digitamos o nome do autor e submetemos o formulário. Agora a requisição é do tipo POST e o controlador recuperará apenas a árvore de componentes. Como digitamos o nome do autor no formulario, o controlador passa esse parâmetro da requisição para o componente correspondente. Ou seja, para o inputText. Quando o controlador pede o HTML no final, o componente também devolve o valor dele. Ou seja, o formulário continua preenchido.

Limpando o nosso formulário
Agora o nosso objetivo é limpar o formulário. Faz todo sentido limpar os inputs após a inserção do autor. Aqui é importante lembrar que ligamos o input ao modelo pela expression language. Quando usamos a expressão, o input passa o valor recebido para o modelo e chama o setter da classe Autor. Mas o input não só passa o valor, como também pede o valor do modelo antes de renderizar o HTML. A ligação é bidirecional.

Podemos provar esta ligação através do getter do modelo. Vamos abrir a classe Autor e sempre concatenar um String no getter.

Após reiniciar o servidor, e recarregar a página no navegador, percebemos que esse String já aparece na tela. Ou seja, o componente chamou o getter do modelo.

Para limpar o formulário então, podemos limpar o nosso modelo, mas antes vamos desfazer a alteração no getter. Para limpar o modelo instanciamos um novo autor no método gravar() da classe AutorBean. Assim o input recebe um novo autor sem nenhum valor. Novamente vamos testar no navegador. Ao preencher e submeter o formulário, o input fica vazio.

Em que modelo arquitetural o JSF se baseia?
O JSF segue o padrão arquitetural MVC, logo, ele inclui divisão em três camadas: modelo, visão e controlador.

No padrão MVC (Model-View-Controller), qual é o papel do modelo?
O modelo é o dominio, o coração da aplicação com as regras de negócios.


Quando gravamos um autor no banco, vemos que a página devolve o formulário ainda preenchido. Como fazemos para que o controlador devolva a página com o formulário em branco?
Devemos lembrar que ligamos o h:inputText ao modelo pela expression language. Quando usamos a expressão, o h:inputText passa o valor recebido para o modelo e chama o setter da classe Autor. Mas o input não só passa o valor, como também pede o valor do modelo antes de renderizar o HTML. A ligação é bidirecional, por exemplo:
<h:inputText value="#{autorBean.autor.nome}" />
Quando instanciamos um novo autor, o input recebe um Autor sem nenhum valor.

Os componentes definidos na página xhtml serão instanciados pelo controlador. O resultado disso é a árvore de componentes que fica guardada em memória. Em que momento o controlador cria esta árvore?
O controlador instancia os componentes apenas na primeira requisição (no primeiro GET).


Onde o controlador guarda a árvore de componentes?
A árvore de componentes (ou seja, a sua tela) fica salva na HttpSession. Como a HttpSession é um objeto exclusivo do usuário no lado do servidor, cada usuário terá guardadas a(s) sua(s) tela(s) em memória. Por isso o JSF é stateful (mantém o estado da tela entre requisições).

Usamos JPA com Hibernate. Igual ao JSF, JPA é um especificação Java EE e Hibernate é a implementação da especificação (como Mojarra é uma implementação da especificação JSF).
Dentro da classe br.com.caelum.livraria.dao.DAO usamos a interface principal do JPA, o EntityManager. É ele que persiste e recupera as entidades.

As entidades são as classe do modelo, no nosso caso o Livro e o Autor. Abra uma vez ambas as classes e observe as anotações de mapeamento (@Entity,@Id etc). Através dessas anotações JPA sabe ligar as classes à tabelas, inclusive gerando-as no banco de dados.

O input é algo parecido abaixo:
<input type="hidden" name="javax.faces.ViewState" id="javax.faces.ViewState" value="1061716021081662360:4138942744536137632" autocomplete="off" />
O número é a identificação da árvore de componentes. Com aquela identificação o controlador consegue recuperar a árvore da HttpSession já que na sessão vai ter pelo menos uma árvore para cada página.

Também há um artigo no blog da Caelum sobre este assunto: Lidando com o estado da view


------------------------------------------------------------------------------
Seção 04 - Completando o sistema e lidando com escopos do JSF 2

Nesta aula completaremos o formulário do Livro e criaremos o relacionamento entre Livro e Autor. A nossa aplicação já está rodando e vamos testar uma vez a inserção de um livro. Ao preencher o formulário e apertar o botão para gravar, recebemos uma exceção. 
A mensagem da exceção indica que não podemos gravar um livro sem autor. Isso faz sentido, pois não deve existir um livro sem autor. O teste se o livro possui um Autor ou não já foi implementado nesse projeto. Ao abrir o LivroBean, notamos que dentro do método gravar() há um if que verifica se existe pelo menos um Autor, caso contrário, lança a exceção já vista. @ManagedBean public class LivroBean { //outros trechos omitidos public void gravar() { System.out.println("Gravando livro " + this.livro.getTitulo()); if(livro.getAutores().isEmpty()) { throw new RuntimeException("Livro deve ter pelo menos um Autor"); } new DAO<Livro>(Livro.class).adiciona(this.livro); } }

Criação e definição do combobox
Vamos implementar o relacionamento no formulário livro.xhtml. Para selecionar o Autor do Livro, adicionaremos um combobox. Para isso criaremos um novo fieldset e, dentro dele, um legend indicando a seção do autor. Teremos um outputLabel com o valor Selecione Autor. O componente que representa um combobox se chama h:selectOneMenu. Vamos adicioná-lo depois do h:outputLabel. Além do combobox, também criamos um botão para confirmar a seleção do autor. O h:commandButton com o valor Gravar Autor. <fieldset> <legend>Dados do Autor</legend> <h:outputLabel value="Selecione Autor:" for="autor"/> <h:selectOneMenu id="autor"/> <h:commandButton value="Gravar Autor" /> </fieldset> A estrutura inicial já está pronta para ser visualizada no navegador. Ao atualizar a página, aparece a seção do autor com o combobox vazio.
Preenchendo o h:selectOneMenu
O próximo passo é o preenchimento do selectOneMenu com os autores e o usuário fará isso, selecionando o nome do autor. Para tal, precisamos preencher o selectOneMenu com "items". Isto é feito por um outro componente que não faz parte do namespace "h", sendo necessário declarar uma nova biblioteca no início da página. Ela também já vem com a especificação e tem a URI http://java.sun.com/jsf/core. Utilizaremos o apelido padrão "f" para referenciá-la. Após a declaração, podemos utilizar os novos componentes, no nosso caso, f:selectItems. Para especificar os itens do combobox, utilizaremos o atributo value com a expression language #{livroBean.autores}. Nessa expressão, chamaremos um método da classe LivroBean, que devolverá uma lista de autores. <fieldset> <legend>Dados do Autor</legend> <h:outputLabel value="Selecione Autor:" for="autor"/> <h:selectOneMenu id="autor"> <f:selectItems value="#{livroBean.autores}" /> </h:selectOneMenu> <h:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}"/> <fieldset> Ao abrir a classe, adicionaremos o método getAutores(). Dentro do mesmo, aproveitaremos o DAO, que possui o método listaTodos(). @ManagedBean public class LivroBean { //outros trechos omitidos public List<Autor> getAutores() { return new DAO<Autor>(Autor.class).listaTodos(); } } Voltando ao formulário, percebemos que falta definir o que queremos apresentar de cada autor. Vamos mostrar o nome do autor indicado pelo atributo itemLabel. Para isso é preciso ter uma variável autor. Ou seja, o f:selectItems vai disponibilizar para cada item um autor. Baseado nesse autor, vamos usar a expression language #{autor.nome} para acessar o atributo nome. Igualmente será associado um valor ao item. Usaremos a ID do autor, ou seja, #{autor.id}. <!-- outros codigos omitidos --> <h:selectOneMenu id="autor"> <f:selectItems value="#{livroBean.autores}" var="autor" itemLabel="#{autor.nome}" itemValue="#{autor.id}"/> </h:selectOneMenu> Já podemos visualizar o resultado no navegador. Ao atualizar a página, aparece o combobox populado.
Recuperando dos valores dentro do ManagedBean
Agora vamos implementar o botão Gravar Autor e chamar um método ao clicar no botão. Mas antes disso é preciso saber qual autor o usuário selecionou no combobox. O componente h:selectOneMenu, como outros inputs, também possui um atributo value que usaremos para capturar a ID do autor selecionado. <h:selectOneMenu value="#{livroBean.autorId}" id="autor"> <f:selectItems value="#{livroBean.autores}" var="autor" itemLabel="#{autor.nome}" itemValue="#{autor.id}"/> </h:selectOneMenu> No f:selectItems definimos como itemValue a ID do autor, e justamente essa ID que recebemos na classe LivroBean. Vamos então criar no LivroBean um atributo autorId com um getter e setter para capturar a ID do autor. @ManagedBean public class LivroBean { //outros trechos omitidos private Integer autorId; public void setAutorId(Integer autorId) { this.autorId = autorId; } public Integer getAutorId() { return autorId; } //outros trechos omitidos } Para finalizar devemos criar o método para gravar o autor. Aqui não há novidade. Usaremos a expression language #{livroBean.gravarAutor} para chamar o método do LivroBean. Vamos então voltar à classe para implementar o método. O método na verdade não grava no banco, e sim associa apenas o autor com o livro usando o relacionamento já definido, mas para isso é preciso carregar o autor, pois temos apenas a ID dele. Usaremos o DAO que possui um método buscaPorId(id) passando a ID do autor (autorId) como parâmetro. O retorno do método é o autor selecionado. Depois da busca do autor, vamos relacionar o livro com o autor pelo método auxiliar adicionaAutor(). Pronto! E quando gravarmos o livro, o JPA também criará o relacionamento no banco de dados. @ManagedBean public class LivroBean { //outros trechos omitidos public void gravarAutor() { Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId); this.livro.adicionaAutor(autor); } //outros trechos omitidos }
Resumo e teste dos componentes
Resumindo, o f:selectItems recebe uma lista de autores. No mesmo componente definimos o que queremos mostrar do autor e qual é o valor associado (e enviado no request). Para receber o autor selecionado, definimos o atributo autorId. Para associar o autor com o livro, criamos o método gravarAutor(), que é chamado pelo commandButton. Chegou a hora de testar a tela no navegador, mas antes vamos reiniciar o servidor. Ao atualizar a página aparece no navegador e o combobox preenchido com os autores. Ótimo! Vamos preencher os dados do livro. São eles: titulo, isbn, preço e data, e depois selecionaremos o autor. Não podemos esquecer de apertar o botão Gravar Autor para criar o relacionamento. No final gravamos o livro.
Entendendo os escopos no desenvolvimento web
Para nossa surpresa recebemos novamente uma exceção, e pior, recebemos a mesma exceção acusando que o livro não possui um autor. Vamos tentar de novo, mas agora, para deixar mais claro, imprimimos o nome do autor no método gravarAutor() para verificar a existência do autor. Ao voltar no formulário, vamos gravar o autor e verificar o console. Aqui está tudo certo, o autor foi relacionado com livro. A implementação está certa. Vamos testar de novo e gravar o livro, mas infelizmente o exceção continua. Para entender o que aconteceu, vamos visualizar o fluxo da aplicação. Após ter preenchido o formulário, selecionamos o autor e apertamos o botão Gravar Autor. Isso disparou um request que o controlador passou para a árvore de componentes, a nossa tela em memória. Como associamos os componentes com o LivroBean, o controlador cria um objeto dessa classe e junto com o LivroBean, também é criado o Livro. No método gravarAutor(), carregamos o Autor associando-o com o Livro. Até aqui é tudo como o esperado. Após isso, apertamos no formulário o botão Gravar para realmente gravar o Livro. Isso causou um novo request que novamente cai na nossa tela, porém agora é criado um NOVO livroBean e consequentemente um novo livro. Perdemos o relacionamento do livro com o autor pois o LivroBean antigo não existe mais. Em geral, cada request causa a criação de um novo LivroBean, pois a vida do Managed Bean dura apenas um request. Isso também se chama "escopo de requisição". Porém, na nossa aplicação, queremos uma vida mais longa pra ele. Queremos que o LivroBean exista enquanto a tela existir. Esse novo escopo se chama ViewScoped e sobrevive a vários requests.
Usando ViewScoped
A configuração do escopo no ManagedBean também é feita através de anotações. O padrão é o escopo de requisição que podemos deixar explícito com a anotação @RequestScoped. Para configurar o ViewScoped usaremos a anotação @ViewScoped.

@ManagedBean
@ViewScoped
public class LivroBean {
Após reiniciar o servidor e atualizar a página, testamos novamente no navegador. Vamos inserir os dados e selecionar o Autor. Não podemos esquecer de apertar o botão Gravar Autor. Agora podemos gravar o livro.

Dessa vez não recebemos nenhuma exceção, e ao verificar o console no Eclipse, podemos ver o SQL gerado pelo JPA. Foi inserido o livro e o relacionamento no banco de dados. Perfeito!

Exibindo autores do livro com o h:dataTable
Agora vamos melhorar mais um pouco a usabilidade da aplicação. Um livro pode ser escrito por vários autores e faz sentido mostrar todos os autores do livro antes de gravar o mesmo.
Para que isso seja feito, vamos renderizar uma tabela abaixo do combobox. Mãos a obra, então. O componente o recebe uma lista pelo atributo value, novamente pela expression language #{livroBean.autoresDoLivro}.

<fieldset>
    <legend>Dados do Autor</legend>

    <!-- outros trechos omitidos -->

    <h:dataTable value="#{livroBean.autoresDoLivro}" >

    </h:dataTable>
</fieldset>
Já no LivroBean, criaremos um método que devolve uma lista de autores. Ao abrir a classe, vamos criar o método getAutoresDoLivro(), que devolve os autores do livro atual.

@ManagedBean
@ViewScoped
public class LivroBean {

    //outros trechos omitidos

    public List<Autor> getAutoresDoLivro() {
        return this.livro.getAutores();
    }
Agora só falta declarar a variável que representa um autor. Depois disso vamos definir o que queremos mostrar em cada coluna. Para isso usaremos o componente , que conterá o componente . O outputText usa a variável para declarar o nome do autor pela expression language. No nosso caso vamos usar apenas uma coluna.

<fieldset>
    <legend>Dados do Autor</legend>

    <!-- outros trechos omitidos -->

    <h:dataTable value="#{livroBean.autoresDoLivro}" var="autor">
        <h:column>
            <h:outputText value="#{autor.nome}"/>
        </h:column>
    </h:dataTable>
</fieldset>
Após reiniciar o servidor e atualizar a página, testamos novamente o formulário. Vamos inserir os dados e selecionar o primeiro Autor. Ao apertar o botão, aparece o nome do autor abaixo do combobox. A mesma coisa acontece para o segundo e terceiro autor.


Qual componente devemos usar caso precisemos de um combobox?
O h:selectOneMenu é o componente próprio para a renderização de um combobox.

Como fazemos para popular os autores no h:selectOneMenu?
Adicionamos o componente f:selectItems dentro do h:selectOneMenu, e usamos os atributos value, var, itemLabel e itemValue para respectivamente, recuperar os autores provindos do managed bean, nomear a variável que representará o autor, definir o texto que será mostrado nas opções e o valor das opções.

Por que utilizamos o id do autor no atributo itemValue do componente f:selectItems?
Pois o LivroBean necessita receber o id do autor, para poder utilizar o DAO afim de acessar o autor que se encontra no banco de dados.

Qual é o efeito de anotar o LivroBean com @ViewScoped?
Caso esta anotação não esteja presente, uma instância diferente de LivroBean será usada à cada requisição.

Qual é o escopo padrão adotado por um ManagedBean?
Por padrão um ManagedBean adota o escopo da requisição (@RequestScoped).
Além dos escopos de requisição (@RequestScoped) e da tela (@ViewScoped) JSF também dá suporte ao escopo da sessão (@SessionScoped) e escopo da aplicação (@ApplicationScoped), tudo configurável pelas anotações.

Que componentes utilizamos para criar uma tabela de dados?
Utilizamos o componente h:dataTable referenciando a lista de dados pelo atributo value. Dentro do h:dataTable, utilizamos o h:column para definir as colunas que existirão na nossa tabela. No nosso exemplo, utilizamos também o h:outputText para renderizar os dados do autor dentro do h:column. Por exemplo:
<h:dataTable value="#{livroBean.autoresDoLivro}" var="autor">
    <h:column>
        <h:outputText value="#{autor.nome}"/>
    </h:column>
</h:dataTable>


---------------------------------------------------------------------------
Seção 05 - Conversão e validação de dados

Trabalhando com Datas
Abrindo a página livro.xhtml temos um input apontando para o atributo dataLancamento do objeto livro em livroBean utilizando a expression language #{livroBean.livro.dataLancamento}. Abrindo esta classe, vemos que o atributo é do tipo String, mas queremos mudá-lo para Calendar, pois a manipulação de datas em String nos causaria problemas.

Alterando o tipo de String para Calendar, queremos gravar no banco apenas a data, ignorando os segundos. Para isso, adicionamos a anotação JPA @Temporal que recebe como parâmetro a enum TemporalType.Date. Iniciaremos o atributo com a data atual através de Calendar.getInstance().

Precisamos criar novos getters e setters, sendo assim, apagaremos os anteriores e criaremos os novos usando o próprio Eclipse. Pronto, já temos o nosso modelo preparado.

package br.com.caelum.livraria.modelo;

//outros imports omitidos

import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livro implements Serializable{

    //outros atributos omitidos

    @Temporal(TemporalType.DATE)
    private Calendar dataLancamento = Calendar.getInstance();

    //outros atributos omitidos

    public Calendar getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Calendar dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    //outros getters e setters omitidos
}
Modificando o banco de dados
Como alteramos o tipo do atributo em nosso modelo, precisaremos recriar o nosso banco para refletir a alteração. O primeiro passo é alterar a classe PopulaBanco. Já temos um método privado parseData que realiza as conversões necessárias. Vamos agora modificar a linha de inserção de data no método geraLivro(..) para:

livro.setDataLancamento(parseData(data));
Vamos ao terminal nos conectar ao mysql. Apagaremos o banco com o comando drop database livrariadb, pois ele já não reflete a estrutura atual do nosso modelo. Com o banco apagado, criaremos novamente o banco livrariadb para que quando nossa aplicação entre no ar, o JPA crie novamente as tabelas baseado em nosso modelo. mysql -u root drop database livrariadb; create database livrariadb; Vamos agora executar a classe PopulaBanco.

Exibindo mensagens de erro através do h:messages
Repare que ao visualizar a página pela primeira vez temos a data de lançamento com um valor não esperado. Mesmo digitando alguns campos e salvando nada acontece. Voltando ao Eclipse, vemos em seu console que uma mensagem de aviso apareceu, mas não foi exibida para o usuário na página. Isto acontece porque houve um erro de validação com o campo data e não havia nenhum componente especializado para exibir esta mensagem automaticamente.

Utilizaremos o componente <h:messages> para exibir todas as mensagens geradas automaticamente pelo JSF. Outra alternativa seria utilizar <h:message>, mais seletivo, pois mostra apenas as mensagens de um componente específico.

<h:body>
    ##Novo Livro
    <h:form>
        <h:messages/>
        <fieldset>
Agora, ao tentarmos salvar novamente, a mensagem de erro é exibida na própria página. O JSF por padrão mostra a mensagem iniciando pelo id do formulário. Como não definimos nenhum id para ele, o JSF coloca automaticamente um para nós. Quando digitamos um valor válido, ainda recebemos uma mensagem de erro diferente, a respeito de um null converter.

Isto acontece, porque o JSF não pode prever qual formato de data desejamos utilizar. Por este motivo, a especificação já traz uma série de conversores, inclusive o de data.

Aplicando os conversores do JSF
Abrindo o formulário, adicionaremos para o inputText da data, o conversor <f:convertDateTime pattern="dd//MM/yyyy" timeZone="America/Sao_Paulo">. Nele temos um formato padrão de data através do atributo pattern e timeZone. O conversor só sabe lidar com objetos do tipo java.util.Date, por isso faremos o bind para #{livroBean.livro.dataLancamento.time}. Pelo padrão JavaBean, time corresponde ao método getTime() de Calendar, que retorna um java.util.Date. Este conversor também pode ser aplicado para componentes de saída, como o <h:outputText>.

<h:inputText id="dataLancamento" value="#{livroBean.livro.dataLancamento.time}">
    <f:convertDateTime pattern="dd/MM/yyyy" timeZone="America/Sao_Paulo" />
</h:inputText>
E no componente h:outputText:

<h:outputText value="#{livro.dataLancamento.time}" >
    <f:convertDateTime pattern="dd/MM/yyyy" timeZone="America/Sao_Paulo" />
</h:outputText>
Vamos visualizar a página livro.xhtml mais uma vez e inserir dados. Perceba que nenhum erro foi lançado e os dados foram gravados corretamente.

Componentes de validação do JSF
Não faz sentido gravarmos um livro sem título, sendo assim podemos modificar o comportamento do inputText para que isso não ocorra. Basta adicionarmos o atributo required="true". Com a alteração feita e testando o formulário no navegador, o JSF não permite a gravação das informações e ainda notifica o usuário através de uma mensagem padrão que o campo é necessário.

Podemos definir nossa própria mensagem no próprio inputText através do atributo requiredMessage="", em nosso caso, definiremos "Título obrigatório". Visualizando novamente a página, vemos a nossa mensagem logo após salvarmos o autor.

<h:inputText id="titulo" value="#{livroBean.livro.titulo}" 
        required="true" requiredMessage="Título obrigatório" />
Podemos ir além, definindo também um tamanho máximo para o campo título através de um validador padrão do JSF. Este validador é o <f:validateLength>. É através do atributo maximum que definimos o valor máximo de 40 caracteres.

Após salvar a página, já podemos testar o resultado sem precisarmos reiniciar o servidor. O JSF mais uma vez nos dá uma mensagem padrão de erro. Também podemos personalizar esta mensagem só que agora através do atributo validatorMessage="". Visualizando mais uma vez vemos que nossa mensagem é exibida.

<h:inputText id="titulo" value="#{livroBean.livro.titulo}" 
            required="true" requiredMessage="Título obrigatório"
            validatorMessage="Título não pode ser superior a 40">
        <f:validateLength maximum="40" />
</h:inputText>
Criando validadores personalizados
Como acabamos de ver, o JSF possui alguns converters e validadores padrões que podemos utilizar, mas nem sempre eles são suficientes. Por exemplo, queremos validar o inputText do ISBN, aceitando apenas números que comecem com o dígito 1 (um). O JSF tem uma solução para isso, que é a criação de validadores personalizados.

Nosso validador será um método no managedBean livroBean. O nome do método será comecaComDigitoUm. Ele recebe um FacesContext, um objeto que permite obter informações da view processada no momento. O segundo parâmetro é o UIComponent, o componente da view que está sendo validado. Por último, temos um objeto que é o valor digitado pelo usuário. Este método precisa lançar um ValidatorException, exceção que sinalizará para o JSF que algo saiu errado.

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

    //trechos do código omitidos

    public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {

        //aqui faremos a validação
    }
}
Sua implementação consiste em testar se o valor digitado começa com um, caso contrário, será lançada a exceção. A exceção recebe em seu construtor a mensagem com o texto que queremos mostrar para o usuário.

public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {

    String valor = value.toString();
    if (!valor.startsWith("1")) {
        throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
    }
}
Falta ainda associar o nosso validador personalizado ao inputText do ISBN. Isto é feito através do atributo validator, que apontará para #{livroBean.comecaComDigitoUm}.

<h:inputText id="isbn" value="#{livroBean.livro.isbn}" validator="#{livroBean.comecaComDigitoUm}"/>
Agora que terminamos a associação podemos testar o resultado. Vamos cadastrar os dados do livro, mas com um valor inválido de ISBN. Depois de associarmos um autor, clicamos em gravar. O JSF exibe a mensagem "Deveria começar com 1", indicando que nosso validador foi chamado. Se colocarmos um valor de ISBN válido, os dados são gravados.

Criação de mensagens próprias
Aproveitando o nosso conhecimento sobre mensagens, vamos ver o que acontece quando gravamos um livro sem autor. Recebemos uma exception, lançada intencionalmente pelo método gravar de livroBean. Podemos melhorar isto, lançando um FacesMessage, algo muito mais elegante.

Voltando à classe LivroBean, vamos trocar o lançamento da exception por uma mensagem. Obtemos uma referência do contexto JSF no momento da chamada do método através de FacesContext.getCurrentInstance(). Agora, adicionamos neste contexto uma mensagem através do método addMessage(). Este método recebe dois parâmetros, o primeiro é o client ID, isto é, id definido no XHTML do componente. Neste caso, utilizaremos autor. O segundo parâmetro é um objeto do tipo FacesMessage que recebe em seu construtor a mensagem que queremos mostrar para o usuário.

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

    // trechos do código omitidos 

    public void gravar() {
        System.out.println("Gravando livro " + this.livro.getTitulo());

        if (livro.getAutores().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("autor",  new FacesMessage("Livro deve ter pelo menos um Autor"));
            return;
        } else {
            new DAO<Livro>(Livro.class).adiciona(this.livro);
            this.livro = new Livro();
        }
    }
}
Vamos salvar as alterações e reiniciar o servidor. Visualizando a página mais uma vez, vamos tentar cadastrar um livro, mas sem adicionar um autor para ele. Quando gravamos, uma mensagem é exibida para o usuário.


A manipulação de datas em String não seria boa prática e causaria problemas de manutenção. Para nos ajudar, podemos alterar o atributo dataLancamento, da classe Livro, para que tipo?
Trabalhar com datas usando Calendar é muito mais fácil. É uma classe especializada para este dominio. Para criar uma instancia do tipo Calendar usaremos:
Calendar data = Calendar.getInstance()

Se quisermos ignorar o horário no banco de dados, podemos adicionar a anotação JPA @Temporal, passando como parâmetro a enum TemporalType.Date.


Que componente usamos quando queremos exibir automaticamente mensagens do JSF?
h:messages mostra todas as mensagens geradas pelo JSF.
Alternativamente, podemos usar h:message, que mostra apenas mensagens de um componente específico. Aqui usaremos a ID da componente para associar o input com h:message:

<h:message for="preco" />
<h:inputText id="preco" value="#{...}" />

O JSF não sabe por padrão que formato de data usar. Que conversor devemos usar para formatar a data?
Deve ser usado o componente f:convertDateTime para formatar a data, porém o conversor sabe lidar apenas com objetos do tipo java.util.Date. Por isso, devemos fazer um binding para #{livroBean.livro.dataLancamento.time}, onde time corresponde ao método getTime() da classe Calendar, que retorna um objeto do tipo java.util.Date.
Devemos também definir um pattern. No nosso caso queremos dia/mês/ano então usaremos dd/MM/yyyy:

<h:inputText id="dataLancamento" value="#{livroBean.livro.dataLancamento.time}">
    <f:convertDateTime pattern="dd/MM/yyyy" timeZone="America/Sao_Paulo" />
</h:inputText>
O conversor também pode ser utilizado nos componentes de output:

<h:outputText value="#{livro.dataLancamento.time}">
    <f:convertDateTime pattern="dd/MM/yyyy" timeZone="America/Sao_Paulo" />
</h:outputText>

Se quisermos tornar um campo obrigatório, que atributo do componente devemos usar?
Se usarmos apenas o required, o JSF nos retornará uma mensagem padrão caso ocorra algum erro. Se quisermos usar uma mensagem personalizada, podemos usar o atributo requiredMessage="mensagem".


No JSF, podemos criar nossos próprios validadores. Para isso, devemos criar um método em um ManagedBean, passando como parâmetros um FacesContext, um UIComponent e um Object. O que representam estes parâmetros, respectivamente?
O primeiro parâmetro é do tipo javax.faces.context.FacesContext e permite obter informações da view processada no momento.
O segundo parâmetro é do tipo javax.faces.component.UIComponent e é um referencia ao componente que está sendo validado, normalmente um UIInput.

O terceiro parâmetro é do tipo java.lang.Object e é um objeto que representa o valor digitado pelo usuário. O objeto já foi convertido.

Após criarmos nosso validador, como fazemos para usá-lo?
Cada componente de input possui um atributo validator onde passamos uma expressão como parâmetro para chamar o método de validação no ManagedBean. Por exemplo:
<h:inputText id="isbn" value="#{livroBean.livro.isbn}" validator="#{livroBean.com

(Para saber mais) Configurando o JSF para usar o TimeZone da nossa máquina!
Nosso sistema evoluiu e agora um novo requisito surgiu: a necessidade de salvar também o horário de lançamento do livro. Para isso, precisaremos fazer algumas alterações em nosso projeto:
1) Vamos alterar o padrão do conversor do h:inputText já que agora ele irá receber também o horário:

<h:inputText id="dataLancamento" value="#{livroBean.livro.dataLancamento.time}">
    <f:convertDateTime pattern="dd/MM/yyyy HH:mm" timeZone="America/Sao_Paulo" />
</h:inputText>
2) Também precisamos alterar o valor da saída, já que precisamos exibir o horário cadastrado. Ou seja, vamos alterar o valor do conversor também no h:outputText

<h:outputText value="#{livro.dataLancamento.time}">
    <f:convertDateTime pattern="dd/MM/yyyy HH:mm" timeZone="America/Sao_Paulo" />
</h:outputText>
3) Configurar a JPA para armazenar também o horário além da data.

Na classe Livro, troque o valor da anotação @Temporal de DATE para TIMESTAMP:

@Entity
public class Livro implements Serializable {
    // outros atributos

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataLancamento = Calendar.getInstance();

    // outros atributos
    // getters e setters
}
Reinicie o Tomcat e veja que agora que o campo Data de lançamento recebe também o horário conforme a imagem abaixo:
Mas, por que será que precisamos colocar o atributo timeZone em todos os nossos f:convertDateTime? Vamos conferir! Remova os atributos e recarregue a página. O que aconteceu?

Provavelmente o horário exibido agora no campo não é mais o mesmo horário marcado em seu computador (timezone America/Sao_paulo). Por que será que isso aconteceu?

Isso acontece porque o JSF usa por padrão o timezone GMT (UTC), como você pode conferir no código da classe DateTimeConverter clicando aqui. Por isso precisamos informar o timeZone toda vez que criamos um novo converter. Mas será que não há nenhuma forma de centralizar essa informação?

Há sim e muito fácil! Podemos dizer ao JSF que queremos utilizar o mesmo TimeZone usado pelo nosso sistema operacional. E para isso vamos declarar um novo parâmetro no arquivo web.xml:

<context-param>
    <param-name>
        javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE
    </param-name>
    <param-value>
        true
    </param-value>
</context-param>
Pronto! Reinicie o Tomcat e veja que o horário na aplicação está equivalente ao horário do sistema operacional mesmo sem utilizar o atributo timeZone.

Ao adicionar esse parâmetro dizemos ao JSF que queremos que ele utilize o TimeZone retornado pelo método getDefault() da classe TimeZone que é responsável por retornar o TimeZone usado pela máquina que roda a aplicação.
Podemos conferir essa informação na documentação do método getDefault(): https://docs.oracle.com/javase/7/docs/api/java/util/TimeZone.html#getDefault()


---------------------------------------------------------------------------
Seção 06 - Páginas mais dinâmicas com AJAX

Melhorando a usabilidade da aplicação
No último vídeo vimos como o JSF trata a conversão e validação de dados. Alteramos o formulário na página livro.xhtml para converter a data e apresentar no formato brasileiro. Nos outros inputs aplicamos regras de validação como no exemplo do titulo que verifica a existência do valor. Personalizamos a validação com nossa regra dentro do LivroBean. Para tal, criamos um método que executa uma validação e gera uma mensagem JSF caso haja algum problema. Para enfileirar uma mensagem FacesMessage, vimos como usar o FacesContext.
Voltando ao xhtml, aprendemos que os converters também se aplicam para h:outputText para formatar, por exemplo, preço ou data do livro. No autor.xhtml aplicamos a validação e aprendemos que h:message pode ser usado para mostrar a mensagem de um campo específico.

Vamos inicializar a aplicação uma vez e testar a interface. Ao chamar o livro.xhtml e gravar um livro inválido, o JSF mostra as mensagens de validação. Vamos testar também o formulário do autor. Um autor sem nome ou com menos de 5 caracteres é considerado inválido, e é sinalizado pelas mensagens ao lado do campo.

Vamos voltar ao formulário do livro e atualizar a tela para começar do zero. Agora podemos perceber um comportamento estranho. Repare que o formulário não aceita a inserção de um autor se o livro à cadastrar possuir campos com falhas de validação. No ponto de vista do formulário há de fato um problema com a validação, mas no ponto de vista do usuário não tentamos gravar o livro ainda, queremos apenas selecionar um autor.

Vamos analisar esse caso para entender melhor o que está acontecendo. Ao apertar o botão Gravar Autor foi enviada uma requisição para chamar o método gravarAutor() dentro do LivroBean. Mas antes disso o controlador passa todos os parâmetros da requisição para a árvore de componentes. O problema é que, ao apertarmos o botão, não só enviamos o ID do autor como também todos os outros dados do formulário. Acontece que os componentes receberam os valores para titulo, isbn etc, mas tudo em branco. Como associamos validações aos inputs, os componentes recusam-se a executar o método gravarAutor() do LivroBean, pois de fato há um problema de validação.

Entendendo AJAX em nossa aplicação
Para resolver isso vamos tentar enviar apenas uma parte do formulário. Ou seja, vamos enviar apenas os dados do autor. Não enviar todas as informações também pode ser vantajoso porque o processamento dos dados no lado do servidor pode ser demorado. Nesse caso o navegador espera sincronamente até a resposta chegar. Ele fica aguardando a resposta para atualizar a tela inteira e causa a famosa tela branca. Isso cria uma experiência ruim para o usuário final.
Então vamos mandar uma requisição por trás da interface, que não necessita de uma atualização completa da tela, e envia apenas uma parte do formulário. O usuário nem deve sentir que houve essa requisição. Assim que a resposta chegar, atualizamos apenas a parte da tela em questão. Essa forma de enviar as requisições se chama AJAX.

Aliando JSF 2 e AJAX
AJAX melhora a usabilidade da interface e, graças aos componentes JSF, pode ser feito de uma forma muito simples. Não é preciso saber detalhes sobre JavaScript e a atualização programática da tela. Tudo isso é feito de forma transparente para o desenvolvedor, bastando apenas ativar o AJAX no lugar desejado. Para tal existe um componente: f:ajax
Vamos associar f:ajax com o h:commandButton. O botão sabe então que vai enviar uma requisição AJAX que NÃO submete o formulário inteiro. Falta então definir o que queremos enviar nessa requisição. Claro, apenas o valor do h:combobox. Para isso existe o atributo execute, nele definimos os IDs dos componentes que queremos enviar na requisição.

<h:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}" >
    <f:ajax execute="autor" />
</h:commandButton>
Vamos adicionar no componente h:selectOneMenu o ID. O mesmo ID é utilizado no atributo execute do f:ajax. Assim o comando sabe que queremos enviar o valor do componentes na requisição.

<h:selectOneMenu value="#{livroBean.autorId}" id="autor">
    <!-- codigo omitido -->
</h:selectOneMenu>
Por último falta adicionar no início da página o componente h:head. Como AJAX é baseado no JavaScript, que roda no navegador, o JSF precisa associar no cabeçalho uma biblioteca JavaScript. Para tal precisa ter o h:head.

<html xmlns="http://www.w3.org/1999/xhtml"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:f="http://java.sun.com/jsf/core">

    <h:head />

    <h:body>
            <!-- codigo omitido -->
    </h:body>
</html>
Ao recarregar a página podemos visualizar o código fonte dela (no navegador Chrome e Firefox, pressione ctrl + u). O código gerado está um pouco mal formatado, mas podemos procurar o elemento head e lá dentro podemos ver a arquivo JavaScript adicionado automaticamente pelo JSF. É este arquivo que possui as funções AJAX, mas ainda bem que não precisamos nos preocupar com esses detalhes.

Vamos fechar essa janela e testar o formulário. Ao apertar o botão Gravar Autor parece que nada acontece na aplicação. Ao verificar o console do Eclipse podemos ver que o método gravarAutor() foi chamado, ou seja, o JSF fez a requisição AJAX e enviou o ID do Autor selecionado para o servidor.

Renderizando componentes do JSF com AJAX
Faltou ainda definir qual parte da página queremos atualizar. Quando selecionarmos o Autor seu nome deve aparecer na tabela abaixo do h:combobox. Aqui também é preciso configurar o componente f:ajax. O atributo render recebe os IDs dos componentes que queremos atualizar, no nosso caso o ID da tabela de autores. Como a tabela não possui ainda um ID, colocaremos um agora repetindo-o no atributo render. Pronto!
<h:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}" >
    <f:ajax execute="autor" render="tabelaAutores"/>
</h:commandButton>
Ao voltar no navegador e testar novamente o botão gravar, o Autor é enviado e a requisição AJAX atualiza a tabela. Repare que os dados do livro não foram enviados, não houve validação do titulo. Houve uma atualização parcial da página.

AJAX nos componentes de entrada
Ótimo, usamos AJAX com um h:commandButton, mas f:ajax não se limita aos comandos e também pode ser aplicado nos componentes de input. Vamos mostrar isso no h:inputText do titular. O f:ajax dentro de um input significa que os dados serão enviados via AJAX. Aqui há algumas opções para definir o momento exato da requisição. Podemos declarar que cada vez que soltamos uma tecla deve ser enviado uma requisição, ou quando o input recebe (ou perde) o focus. No exemplo submetemos os dados quando o campo perde o focus, indicado pelo atributo event com o valor blur.
Igual a parte anterior, também podemos especificar o que queremos atualizar após a requisição. A vantagem de usar AJAX no input é que a validação é executada na hora, quando o usuário digita no campo. Novamente irá melhorar a usabilidade, mas isso só funciona se atualizarmos as mensagens para apresentar possíveis problemas de validação. Aqui também é preciso fazer a associação com o ID do componente.

<h:inputText id="titulo" value="#{livroBean.livro.titulo}" required="true" 
    requiredMessage="Título obrigatório" validatorMessage="Título não pode ser superior a 40">
            <f:validateLength maximum="40" />
            <f:ajax event="blur" render="messages"/>
</h:inputText>
No navegador vamos atualizar página e testar o titular. Vamos deixar o campo em branco. Repare que, ao perder o focus, aparece na mesma hora o erro de validação.

Enviando o formulário inteiro
Por último vamos usar AJAX também no botão que grava o livro. Enviaremos todas as informações do livro com AJAX. Aqui não há novidade, temos a tag f:ajax dentro do botão. Também precisamos declarar os IDs dos componentes que queremos incluir na requisição. No atributo render podemos usar uma lista de IDs, porém muitos IDs ficam trabalhosos, facilitando erros. Por este motivo existe um atalho, o @form, para indicar o envio do formulário inteiro. Além do @form há outras possibilidades, como por exemplo @all. Ele indica que queremos enviar a página inteira.
Além disso, é preciso definir o que queremos atualizar. Nesse exemplo é preciso atualizar o formulário, indicado pelo @form e a tabela para o mostrar o novo livro. A tabela será referenciada novamente pelo ID, como já fizemos, porém repare que elas não estão no mesmo formulário. Colocando o ID apenas, significa que o f:ajax procurará o componente no mesmo formulário. Para encontrar o componente fora do form é preciso usar o caminho absoluto indicado pelo ":" na frente do ID.

<h:commandButton value="Gravar" action="#{livroBean.gravar}" >
    <f:ajax execute="@form" render="@form :tabelaLivros"/>
</h:commandButton>
Revisando as funcionalidades da aplicação
Vamos mostrar uma vez todas as funcionalidades abrindo novamente o navegador. Ao apertar o botão Gravar será enviado um request AJAX. Como há dados inválidos, as mensagens de validação são mostradas. Depois preenchemos o campo Titulo, que envia outro request AJAX após perder o focus. Ao preencher os outros dados e gravar o livro será testada a existência do Autor. Na escolha do Autor também usamos AJAX para enviá-lo e atualizar a sua tabela. No final podemos cadastrar o Livro, que causa a atualização do formulário e da tabela de livros. Repare que em nenhum momento a página foi atualizada totalmente, pois utilizamos AJAX.


Qual das opções abaixo descreve uma vantagem de usarmos requisições AJAX?
Além de evitar a famosa tela branca, o uso apropriado de requisições AJAX possibilita uma comunicação mais rápida com o servidor, dado que o mesmo só precisará responder com a parte da página que irá realmente mudar.
Como atualizamos apenas uma parte da página, em vez da página inteira, melhorando assim a experiência do usuário.

Por mais que esse recurso tenha inúmeras vantagens, sua implementação é mais complexa se feita manualmente. Felizmente, o JSF toma conta de toda essa complexidade para nós.

Que componente do JSF nos permite utilizar AJAX?
O componente disponibilizado pelo JSF para lidar com AJAX é o f:ajax.


Como indicamos os campos que serão submetidos com a requisição ajax?
Precisamos adicionar os IDs nos campos que desejamos submeter na requisição AJAX, e incluir esses IDs no atributo execute do componente f:ajax. Para, por exemplo, submeter o componente com a ID autor usamos:
<h:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}" >
    <f:ajax execute="autor" />
</h:commandButton>

Por que é necessário utilizar o componente h:head para trabalharmos com AJAX?
Como o AJAX depende diretamente de JavaScript, é necessário que o JSF importe sua própria biblioteca JavaScript. Isso só será feito, caso o componente h:head esteja presente.

Como indicamos os componentes que devem ser atualizados após a requisição AJAX?
Precisamos adicionar os IDs nos campos que desejamos atualizar após requisição AJAX, e incluir esses IDs no atributo render do componente f:ajax. Para, por exemplo, atualizar a tabela de autores usamos:
<h:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}" >
    <f:ajax execute="autor" render="tabelaAutores"/>
</h:commandButton>

Como podemos referênciar a IDs de componentes que não se encontram dentro do h:form?
Basta que a ID tenha o caracter : antes, dessa forma:
<f:ajax render=":tabelaLivros" execute="@form" />

Como fazemos para submeter todos os campos do formulário?
Assim como podemos utilizar @form no atributo execute, também podemos utilizá-lo no atributo render no caso de todo o formulário necessitar de ser atualizado.
É possível habilitar AJAX em componentes JSF através da tag f:ajax. Uma implementação válida é a seguinte:
<h:inputText id="isbn" value="#{livroBean.livro.isbn}"
        validator="#{livroBean.comecaComDigitoUm}">
    <f:ajax event="keypress" render="messages"/>
</h:inputText>
O JSF blinda o desenvolvedor de conhecer os detalhes de uma requisição AJAX.


----------------------------------------------------------------------------
Seção 07 - Navegação e o ciclo de vida dos componentes JSF

Fases do ciclo de vida dos componentes do JSF:
	HTTP POST faz uma requisição
	Fase 1 - Restore View ------> Arvore tela = session.getAttribute(idDaTela);
	Fase 2 - Apply Request Values ------>  String param = request.getParameter("autorId"); input.setSubmittedValue(param);
	Fase 3 - Process Validations ------> Integer autorId = Integer.parseInt(input.getSubmittedValue()); input.setValue(autorId);
	Fase 4 - Update Model Values -----> LivroBean livroBean = pegaLivroBean(); livroBean.setAutorId(input.getValue());
	Fase 5 - Invoke Application -----> livroBean.gravarAutor();
	Fase 6 - Render Response -----> String html = tela.getHtml(); response.getWriter().writer(html); (RESPOSTA DO HTML)



Navegação entre as paginas
Continuaremos a melhorar passo a passo nossa aplicação aprendendo ainda mais sobre JSF. Ao accessar a página livro.xhtml podemos ver o formulário para cadastrar livros. Uma situação bastante comum no dia-a-dia será cadastrar um livro quando ainda não há autor relacionado. Nesse caso é preciso chamar o formulário do autor manualmente, digitando a página no navegador. Com certeza isso não vai agradar nenhum usuário. Vamos melhorar então o formulário e criar um link abaixo do combobox .
O componente h:commandLink
No eclipse, vamos abrir a página livro.xhtml e procurar o fieldset do autor. Nele, abaixo do h:commandButton adicionaremos mais um comando, mas agora do tipo link, ou seja h:commandLink.
Esse componente também recebe no atributo value o nome do link, aqui Cadastrar novo autor. No atributo action podemos associar uma expressão ou colocar diretamente o nome página que queremos chamar. Nao podemos usar a extensão da página, apenas o nome sem xhtml. Só falta adicionar um nova linha para melhorar o layout, basta usar a tag HTML .

<h:commandLink value="Cadastrar novo autor" action="autor" />
Pronto, podemos testar no navegador. Ao atualizar aparece o link. Vamos apertar uma vez para verificar a funcionalidade, mas percebemos uma coisa estranha: no lugar de chamar a página autor.xhtml a validação foi executada! Repare no inicio do formulário os erros de validação. Não foi bem isso que queríamos.

Introdução ao ciclo da vida no JSF
Para entender melhor o que JSF faz por baixo dos panos, vamos lembrar do modelo de camadas. Aprendemos que o JSF segue um padrão arquitetural Model-View-Controller. Nele cada camada possui uma responsabilidade bem definida. A camada do controlador recebe todas as requisições e decide que tela ou árvore de componentes utilizar. A visão define toda interface e a camada do modelo fornece os dados. Os ManagedBeans representam a ligação entre modelo e visão.


Além dessa separação já percebemos que há um fluxo bem definido dentro desse modelo. Por exemplo, vimos que o JSF converte e valida automaticamente os parâmetros da requisição. Esses dados, caso não haja nenhum problema com conversão e validação, serão passados para o modelo. Depois os ManagedBeans usam os valores para executar um método.



Esses passos ou fases foram bem definidos pela especificação e fazem parte do ciclo da vida do JSF. Para entender por que o link não funcionou é preciso entender essas fases. Em geral, um bom entendimento sobre o ciclo de vida facilita muito o trabalho com o JSF.

Definição do PhaseListener
A especificação define 6 fases. Para visualizar e acompanhar o ciclo da vida podemos plugar uma classe chamada automaticamente pelo JSF quando uma fase inicia ou acaba. Criaremos esse PhaseListener para entender o ciclo da vida.


No Eclipse vamos abrir o arquivo de configuração do JSF: faces-config.xml que esta na pasta WEB-INF. Nele precisamos declarar uma tag lifecycle que por sua vez terá um elemento phase-listener. Dentro do phase-listener definiremos uma classe br.com.caelum.livraria.util.LogPhaseListener.



A classe não existe ainda, então vamos para a pasta src, digitando ctrl n para criar uma nova classe LogPhaseListener dentro do package br.com.caelum.livraria.util. Após confirmação é gerado o esboço da classe. Ela deve implementar a interface PhaseListener que já vem com a JAR do JSF.

    <lifecycle>
        <phase-listener>br.com.caelum.livraria.util.LogPhaseListener</phase-listener>
    </lifecycle>
Ao implementar a PhaseListener somos obrigados a implementar três métodos. Com a ajuda do Eclipse podemos gerar esses métodos automaticamente, basta digitar ctrl 1 antes do PhaseListener. Os métodos são afterPhase(..), beforePhase(..) e getPhaseId().

beforePhase(..) e afterPhase(..) são chamados antes e depois de uma fase e o getPhaseId() define qual fase o listener atende. Para nosso objetivo acionaremos todas as fases, indicado pelo retorno PhaseId.ANY_PHASE. Para fins educativos usaremos apenas o método beforeFase(..) e imprimir o nome da fase do evento recebido como parâmetro.

Segue a implementação do LogPhaseListener:

package br.com.caelum.livraria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener{

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent arg0) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("FASE: " + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
Ajustando os logs da aplicação
Antes de testarmos o Listener, alteraremos o log de Hibernate. No aquivo persistence.xml desabilitaremos o log do SQL. Nosso foco agora são as fases do JSF, o SQL gerado pode atrapalhar nesse momento. Para tal basta alterar a propriedade show_sql para false. No persistence.xml:
<property name="hibernate.show_sql" value="false" />
Pronto, podemos reiniciar o Tomcat e atualizar a página livro.xhtml no navegador. Ao voltar para Eclipse podemos ver que a console foi poluída pelo logs do Tomcat e Hibernate. Isso só aconteceu no inicialização da aplicação. Vamos limpar o console e repetir o processo. Novamente chamaremos a página pelo navegador.

Criação da view e renderização: RESTORE_VIEW e RENDER_RESPONSE
Agora o console do Eclipse só mostra a saída do nosso PhaseListener. Podemos ver que duas fases foram acionadas, a primeira chamada RESTORE_VIEW e outra, a sexta fase chamada RENDER_RESPONSE. Repare que o PhaseListener automaticamente numerou as fases.
Ao receber uma requisição HTTP do tipo GET o controlador iniciou o ciclo da vida da tela. Isso significa que ele leu o arquivo xhtml e instanciou todos os componentes. Como isso foi disparado pela requisição inicial está claro que não há nada a fazer além de renderizar a resposta.



Analisandos as fases: APPLY_REQUEST_VALUES e PROCESS_VALIDATION
No Eclipse novamente limparemos a console e mais uma vez no navegador usaremos o formulário para executar uma validação. Lembrando que o campo do título automaticamente envia uma requisição AJAX ao perder foco e consequentemente é executada a validação no lado do servidor.
Olhando o console no Eclipse podemos ver que dessa vez foram executadas 4 fases. Temos como segunda fase o APPLY_REQUEST_VALUES, e como terceira fase PROCESS_VALIDATION.

Vamos analisar também esse caso. Como sempre o controlador recebeu a requisição mas agora ela é do tipo POST. Isso significa que o controlador apenas recupera a árvore (por isso se chama RESTORE_VIEW). Após a recuperação da tela os componentes recebem o valor digitado pelo usuário que vem da requisição (dai vem o nome APPLY_REQUEST_VALUE). Nesse caso submetemos apenas o valor do titular, mas em branco. Em outras palavras, o componente do titular recebe um String vazio.

Na terceira fase acontece a conversão, se for preciso, e a validação. O titulo não precisa ser convertido pois o titulo é do tipo String, mas associamos uma validação com o componente. É justamente essa validação que falha. Em geral, se há um problema de conversão ou validação, o controlador pula automaticamente as fases quatro e cinco e pede renderizar o HTML com as mensagens de erro.



Todas as fases do ciclo
Agora testaremos o que acontece quando escolhemos um autor no combobox. Vamos apertar o botão e verificar a console de novo. Repare que agora foram executadas todas as seis fases. Na primeira fase (RESTORE_VIEW) foi recuperada a árvore de componentes da sessão, na segunda (APPLY_REQUEST_VALUES) os componentes receberam os parâmetros da requisição e na terceira (PROCESS_VALIDATION) todos os dados foram convertidos e validados.
Como nenhum erro ocorreu na terceira fase o controlador continua com a próxima que se chama UPDATE_VALUES. Aqui o modelo será atualizado com os valores convertidos na fase anterior. Neste caso, LivroBean recebe apenas ID do autor já que só enviamos essa parâmetro pelo AJAX. Em geral, nesta fase, tudo que definimos com a expression language nos componentes de inputs é atualizado no modelo.

Agora que o modelo já foi atualizado, nosso LivroBean já pode executar o comando definido via expression languague. Este comando é um método no próprio LivroBean e opera sobre os dados convertidos. Esta fase de chamada de métodos no ManagedBean é chamada de INVOKE_APPLICATION.

Por fim, o JSF devolve uma resposta para o usuário, o que é feito na última fase, RENDER_RESPONSE.



Resolvendo o problema de navegação e o atributo immediate
Abriremos a página e testaremos novamente o link. Ao voltarmos ao Eclipse, vemos no console as fases que foram processada. Houve um salto da fase PROCESS_VALIDATION para RENDER_RESPONSE, sendo assim, nenhum dado do modelo foi atualizado e nenhum método foi invocado, isto porque as fases UPDATE_MODEL e INVOKE_APPLICATION foram puladas.
Vamos realizar outro tipo de navegação, mas desta vez através de um método em LivroBean. Vamos alterar o atributo action que agora apontará para o método formAutor(). Fazemos isso através de expression languague #{livroBean.formAutor}. Com a classe aberta, adicionaremos o método, mas agora precisamos que seu retorno seja uma String, pois o retorno é a regra de navegação que o JSF seguira. Aqui vamos devolver apenas o nome da página, sem extensão. Adicionaremos também uma saída no console que será impressa apenas se o JSF executar a fase PROCESS_VALIDATION:

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

    //codigo omitido

    public String formAutor() {
        System.out.println("Chamanda o formulario do Autor");
        return "autor";
    }

    //codigo omitido
}
Vamos reiniciar o servidor, pois alteramos uma classe. Abrindo mais uma vez e testando o link, vemos que não mudou o fluxo. Olhando no console do Eclipse, percebemos que não foi invocado a fase INVOKE_APPLICATION, isto e, o JSF não executou o método formAutor().

O problema é que o nosso link com a regra de navegação está disparando a validação do formulário, quando na verdade queremos apenas seguir para outra página. Uma maneira de resolver este problema e adicionar o atributo immediate=true em nosso link. Isso fará que o método formAutor() é executado durante da segunda fase APPLY_REQUEST_VALUES, antes da validação. Assim a nossa regra de navegação será processada e automaticamente pularemos para última fase.

<h:commandLink value="Cadastrar novo autor" action="#{livroBean.formAutor}" immediate="true"/>
Finalmente a página do autor foi chamada. Podemos verificar também na saída do console. Foram executadas a primeira, segunda e sexta fase.



Redirecionamento pelo controlador no lado do servidor
Após ter executado o link podemos reparar que na barra de endereço do navegador é mostrado o endereço do livro e não o endereço do formulário que estamos vendo. Isto acontece porque a regra de navegação padrão do JSF e feita através de forward.
Vamos visualizar novamente os acontecimentos para entender melhor. Enviamos pelo link um request do tipo POST que caiu na árvore de componentes, mas a árvore do Livro. Aqui o comando executou o método formAutor() que devolveu a String autor. O controlador recebeu esse retorno e chamou a página autor.xhtml para instanciar os componentes. Após disso devolveu o HTML dessa tela para o navegador. Repare que o controlador fez um redirecionamento, mas tudo no lado do servidor. Enviamos apenas uma requisição e por isso o endereço no navegador ficou com livro.xhtml. A conseqüência disto é que o navegador sempre ficará um URL atrás.



Redirecionamento no cliente
Uma maneira de resolver isso é através de redirecionamento pelo navegador, no lado do cliente. Aqui o controlador devolve a resposta após ter recebido o retorno do comando. Nessa resposta avisamos o navegador para enviar um segundo request com o novo endereços /autor.xhtml. Ou seja, enviamos duas requisições. Para tal, vamos indicar ao controlador que queremos redirecionar pelo navegador. Isso é feito no método formAutor() com o retorno autor?faces-redirect=true.
@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

    //codigo omitido

    public String formAutor() {
        System.out.println("Chamanda o formulario do Autor");
        return "autor?faces-redirect=true";
    }

    //codigo omitido
}
Reiniciando a aplicação e testando mais uma vez. A regra de navegação e executada e a barra de endereço do navegador corresponde a página que estamos visualizando.



Faremos a mesma coisa com o método gravar() de AutorBean. Após salvar um autor, redirecionaremos para a página de livros. Para isso retornamos uma String indicando o redirecionamento para a página de livros. Salvando as alterações, reiniciando e visualizando. Após testar, vemos que o redirecionamento e feito com sucesso.


Para entendermos melhor a maneira na qual o JSF funciona, é preciso lembrarmos do modelo de camadas. Sabemos que o JSF segue o padrão MVC. Sendo assim, podemos afirmar que:
No modelo MVC é o controlador que decide qual view a usar e controla todo o fluxo do JSF.
	

O que acontece na terceira fase PROCESS_VALIDATION?
Na terceira fase acontece a conversão, se for preciso, e a validação. Se há um problema de conversão ou validação, é criado uma mensagem de erro e o controlador pula automaticamente as fases quatro e cinco para renderizar o HTML com as mensagens de erro através do componente h:messages.

O método gravar associado com o comando abaixo é executado em qual fase?
<h:commandButton value="Gravar" action="#{livroBean.gravar}" />
O método é chamado na fase INVOKE_APPLICATION que é a quinta fase no ciclo da vida JSF.


Dado um ManagedBean abaixo:
@ManagedBean
public class Bean {

    public String form() {
        return "produto";
    }
}
e um comando na página xhtml:

<h:commandButton value="Navegue" action="#{bean.form}" />
Para qual página o controlador vai redirecionar ao executar o comando?

Assumindo que estamos utilizando Facelets o controlador fará um redirecionamento no lado do servidor para a página produto.xhtml após a execução do método form(). Se fosse um redirecionamento pelo navegador (client-side )devemos usar o parâmetro produto?faces-redirect=true no retorno do método.
Mais sobre Facelets veremos no próximo capítulo.


O método form associado com o comando abaixo é executado em qual fase?
<h:commandButton value="Gravar" action="#{livroBean.form}"  immediate="true"/>
O método é chamado na fase APPLY_REQUEST_VALUES pois o comando tem o atributo immediate.


Ao executar o método abaixo é feito um redirecionamento no lado do servidor:
@ManagedBean
public class Bean {

    public String form() {
        return "produto";
    }
}
Como faremos para redirecionar no lado do cliente (pelo navegador)?

O padrão JSF é redirecionar para página no lado do servidor. Para chamar a página pelo navegador, ou seja enviar uma segunda requisição é preciso adicionar no retorno o parâmetro faces-redirect=true:
@ManagedBean
public class Bean {

    public String form() {
        return "produto?faces-redirect=true";
    }
}


Para saber mais: Forward e Redirect. Qual a diferença?
Após submetermos um formulário (quando clicamos em um h:commandLink também estamos submetendo um formulário) é necessário redirecionar para uma outra página para mostrar o devido feedback ao usuário. Como foi visto neste capítulo, podemos optar em utilizar o forward ou o redirect. Não se preocupe se você está um pouco confuso nesses conceitos, pois agora vamos falar um pouco mais sobre eles:

Redirecionamento no lado do servidor (forward)
Após o usuário realizar a requisição, o controlador do JSF irá chamar o método do nosso ManagedBean e aguardará o seu retorno. Após isso o controlador irá renderizar a página, cujo nome foi retornado pelo método e a enviará prontinha para o cliente como resposta.

Vamos analisar o fluxo na nossa aplicação? O que acontece ao clicar no botão Cadastrar novo autor?

<!-- livro.xhtml -->
<h:commandLink value="Cadastrar novo autor" action="#{livroBean.formAutor}" immediate="true"/>
// LivroBean.java
public String formAutor() {
    System.out.println("Chamanda o formulario do Autor");
    return "autor";
}


1) O cliente dispara uma requisição (submete o formulário) para o controlador;

2) O método formAutor() é chamado pelo controlador que armazena seu retorno;

3) O controlador utilizará a String retornada pelo método para buscar a View que deverá ser renderizada. Ao encontrá-la, ele irá realizar o processo de renderização;

4) Após a renderização, o seu HTML será retornado como resposta da requisição feita pelo usuário no passo 1 (a submissão do formulário).

Como toda requisição deve haver uma resposta, ao submetermos o formulário também precisamos de uma resposta. Só que nesse caso a resposta será o HTML de uma outra página. Por conta disso, o navegador nem sequer percebe que houve uma mudança de página e a URL na barra de endereços continua a mesma de antes da submissão.

Redirecionamento no lado do cliente (redirect)
Ao delegarmos o redirecionamento ao cliente, o servidor não devolverá mais a página renderizada como resposta mas pedirá ao cliente para que acesse a nova página.

Para dizer ao JSF que queremos realizar um redirecionamento no lado do cliente, precisamos adicionar o parâmetro faces-redirect=true:

// LivroBean.java
public String formAutor() {
     System.out.println("Chamanda o formulario do Autor");
     return "autor?faces-redirect=true";
}


1) O cliente dispara uma requisição (submete o formulário) para o controlador;

2) O método formAutor() é chamado pelo controlador;

3) O Controlador do JSF retorna a resposta ao navegador com o status 302 (moved temporarily). Esse código diz ao browser que ele precisará acessar a URL contida no header location da resposta.

4) O navegador, portanto, irá acessar a página contida no header Location

5) O controlador identifica a View e realiza o processo de renderização

6) O HTML da página é retornado ao browser.

Dessa forma o próprio navegador fica responsável por realizar o redirecionamento acessando outra página e por conta disso, a URL da barra de endereços é a da nova página que recebemos como resposta.

Podemos citar alguma vantagem em utilizar o redirecionamento no lado do cliente ao submeter um formulário de cadastro? Pesquise sobre a técnica Always redirect after Post e responda com suas próprias palavras.

O navegador, por padrão, sempre guarda a última requisição que é feita pelo usuário. Isso significa que requisição responsável pela submissão do formulário ficará salva no navegador. Por conta disso, caso o usuário atualize a página (o famoso F5) o browser irá processar novamente a requisição e realizar um novo cadastro.
Ao usar o redirecionamento do lado do cliente, a última requisição será o GET feito pelo browser para acessar a página do header Location e dessa forma, caso o usuário atualize a página será disparado novamente somente o último GET evitando qualquer efeito colateral na aplicação :)

Conhecimento sólido sobre o protocolo HTTP é fundamental para o desenvolvedor, caso queira saber mais assista nosso treinamento Curso HTTP: Entendendo a web por baixo dos panos.


Para saber mais: Retornos tipados

Todo retorno do nosso Managed Bean é apenas uma String que diz o nome do arquivo .xhtml que deverá ser renderizado. Se precisarmos usar o redirect (redirecionamento pelo navegador), é necessário colocar o parâmetro ?faces-redirect=true.
Uma alternativa seria, em vez de retornar strings, criar um tipo mais expressivo para indicar a View para o JSF. Por exemplo, na classe AutorBean poderíamos devolver um tipo RedirectView que define melhor a intenção do retorno:

// na classe AutorBean

public RedirectView gravar() {
    System.out.println("Gravando autor " + this.autor.getNome());
    new DAO<Autor>(Autor.class).adiciona(this.autor);
    this.autor = new Autor();

    return new RedirectView("livro");
}
Lembrando que esse tipo RedirectView não existe e precisaremos criá-lo. Portanto, crie uma classe no pacote br.com.caelum.livraria.util chamada RedirectView:

public class RedirectView {

    private String viewName;

    public RedirectView(String viewName) {
        this.viewName = viewName;
    }    
}
O controlador do JSF, ao encontrar um tipo diferente de String, chamará o método toString() em busca do nome da View. Vamos sobrescrever o método toString() retornando o nome da View concatenada com ?faces-redirect=true:

// RedirectView.java
@Override
public String toString() {
    return viewName + "?faces-redirect=true";
}
Faça o teste no seu projeto!


Já que estamos deixando os retornos tipados, por que não criar um tipo também para o forward (redirecionamento no lado do servidor)? Podemos criar uma outra classe chamada ForwardView e dessa vez não iremos concatenar o ?faces-redirect=true ao nome da View:
package br.com.caelum.livraria.util;

public class ForwardView {

    private String viewName;

    public ForwardView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String toString() {
        return viewName;
    }

}
E nos ManagedBean podemos usar:

//AutorBean
public ForwardView gravar() {
    System.out.println("Gravando autor " + this.autor.getNome());

    new DAO<Autor>(Autor.class).adiciona(this.autor);

    this.autor = new Autor();

    return new ForwardView("livro");
}
Ou seja, se quisermos um forward usamos a classe ForwardView e se quisermos redirect usamos RedirectView!



------------------------------------------------------------------------
Seção 08 - Templates reutilizáveis com Facelets

Para não precisar ficar copiando e colando códigos de cabeçalhos, como imagem de logo ou nome do usuário logado o JSF 2 vem com mecanismo de template, através de Facelets. Isso é um molde que pode ser atualizado para todas as páginas.
Template nada mais é uma página xhtml utilizando facelets. Por convensão seu nome começa com underscore/underline.


Código duplicado na view
Com a página livro.xhtml em edição, queremos adicionar o logo da Caelum em seu topo através da tag <h:graphicImage />. É necessário indicar a localização da imagem, mas primeiro entenderemos como o JSF fornece acesso a este recurso.

Por padrão, no JSF 2.0, imagens, scripts e arquivos de CSS devem estar dentro de um diretório chamado resources, que fica dentro da pasta WebContent. A pasta ainda não existe, logo, vamos criá-la. Agora, dentro da pasta resources, criaremos a pasta img e nela gravaremos o nosso logo.

Voltando a tag <h:grahicImage />, definiremos dois atributos. O primeiro é o library. Nele, preencheremos com o valor img, a pasta que contém nosso logo dentro de resources. O segundo é o name. Nele definimos o nome do arquivo, em nosso caso, logo.png. Vamos iniciar o servidor e verificar o resultado. O logo é exibido como esperado.

A ideia é termos o mesmo logo também em autor.xhtml. Ou seja, teremos um cabeçalho em todas as páginas. Repare que podemos fazer isto facilmente copiando e colando o código da página livro.xhtml para a página autor.xhtml.

O que aconteceria se além da imagem, nosso cabeçalho passasse a mostrar a informação do usuário logado no sistema? Com certeza isso nos daria problemas de manutenção, já que precisaríamos nos lembrar de atualizar o cabeçalho em todas as páginas.

Usando templates com Facelets
Para resolver problemas como este que o JSF vem com o mecanismo de templates através de facelets. Isto é, um molde que pode ser aproveitado por outras páginas. Toda vez que este molde for atualizado, todas as páginas que o seguirem também serão atualizadas.

O template nada mais é do que uma página xhtml utilizando facelets. Por convenção, seu nome inicia com underscore (_), sendo assim, vamos criar nosso primeiro template.



Configurando um template
Com a pasta WebContent selecionada, vamos utilizar o atalho do Eclipse Control + 3 seguido do texto New file. Apenas uma opção aparece. Tecle Enter para selecionar. O Eclipse solicita o nome do arquivo. Usaremos _template.xhtml.

Começaremos pela tag <html>. Nela, importaremos as bibliotecas que utilizaremos. Primeiro a do JSF, segundo a biblioteca do facelet com o namespace xmlns:ui="http://java.sun.com/jsf/facelets". Por padrão, temos a tag <h:head> e <h:body>.

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:ui="http://java.sun.com/jsf/facelets">

      <h:head />
      <h:body>
      </h:body>
</html>
Com essa estrutura mínima, vamos criar uma div com id cabecalho. Precisamos mover a tag <h:graphicImage /> da página livro.xhtml para o template, abrindo a página, cortando e colando em nosso template, dentro da div cabecalho.

Faz todo sentido aproveitarmos o logo em todas as nossas páginas, mas queremos também padronizar a exibição do título da página através da tag h1. Repare que a definição de seu conteúdo não é responsabilidade do template, mas de quem utilizá-lo.

Para isso, utilizaremos a tag com o name titulo como conteúdo da tag h1. Esta tag indicará para as páginas que utilizarem o template que há uma área com name titulo que pode ser preenchida:

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:ui="http://java.sun.com/jsf/facelets">

      <h:head />
      <h:body>
            <div id="cabecalho">
                        <h:graphicImage library="img" name="logo.png"/>
                        <h1><ui:insert name="titulo"></ui:insert></h1>
            </div>
      </h:body>
</html>
Não é apenas o título que muda de página para a página, mas o conteúdo da página. Sendo assim, vamos adicionar uma nova div com id conteudo e dentro dela a tag <ui:insert> com name conteudo:

<html xmlns="http://www.w3.org/1999/xhtml"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:ui="http://java.sun.com/jsf/facelets">

    <h:head/>
    <h:body>
        <div id="cabecalho">
            <h:graphicImage library="img" name="logo.png"/>
            <h1><ui:insert name="titulo"></ui:insert></h1>
        </div>

        <div id="conteudo">
            <ui:insert name="conteudo">
            </ui:insert>
        </div>
    </h:body>
</html>
Associação da página com o template
Falta fazer a associação da página livro.xhtml com o template. Isto é feito envolvendo todo o conteúdo da página pela tag . Agora precisamos indicar pelo atributo template, o nosso template _template.xhtml. Como estamos utilizando facelet na página, precisamos também importa-lo:

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://java.sun.com/jsf/html"
    xmlns:f="http://java.sun.com/jsf/core"
    xmlns:ui="http://java.sun.com/jsf/facelets">

  <ui:composition template="_template.xhtml">
     <!--codigo omitido -->
Após indicar que a nossa página utiliza um template, precisamos definir as áreas em aberto do template. A primeira delas é . Para cada do template, podemos ter um na página que o utiliza, adicionando a tag com o conteúdo Novo Livro. Podemos remover a tag h1, pois ela agora vem do template.

Por fim, precisamos colocar todo o conteúdo da página dentro da tag <ui:define>, mas agora com o name conteudo.

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://java.sun.com/jsf/html"
    xmlns:f="http://java.sun.com/jsf/core"
    xmlns:ui="http://java.sun.com/jsf/facelets">

<ui:composition template="_template.xhtml">
    <ui:define name="titulo">
            Novo Livro
    </ui:define>
    <ui:define name="conteudo">
        <h:form>
                <!--codigo omitido -->
        </h:form>
                <!--codigo omitido -->
    </ui:define>
</ui:composition>
</html>
Pronto, terminamos as alterações necessárias. Vamos visualizar no navegador. Conforme esperado, nossa página autor herdou o logo do template, ao mesmo tempo em que personalizou o título e o conteúdo da página.



Aplicando o template nas demais páginas
Faremos a mesma coisa com a página autor.xhtml. Voltando ao Eclipse, abrindo a página para edição, importando o facelets. Agora, vamos colocar todo o conteúdo da página dentro da tag <ui:composition>, sem esquecer de definir o nosso template(_template.xhtml). Agora falta definir o título, por isso utilizaremos a tag <ui:define name="titulo">, colocando o nosso título Novo Autor. Agora falta envolver todo o conteúdo da página na tag <ui:define name="conteudo">.

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://java.sun.com/jsf/html"
    xmlns:f="http://java.sun.com/jsf/core"
    xmlns:ui="http://java.sun.com/jsf/facelets">

<ui:composition template="_template.xhtml">
    <ui:define name="titulo">
            Novo Autor
        </ui:define>
    <ui:define name="conteudo">
        <h:form id="autor">
                <!--codigo omitido -->
        </h:form>
    </ui:define>
</ui:composition>
</html>
Agora, visualizando a página, vemos que o logo também é apresentado. Criamos um template usando facelets, que foi reutilizado tanto pela página livro, quanto autor, assim, reaproveitando o código e facilitando a manutenção.

Qual tag do JSF usamos para adicionar uma imagem à nossa página?
Definindo o alias do namespace com a letra h, usamos a tag h:graphicImage para adicionar uma imagem à nossa página. Lembrando que as imagens usadas devem estar dentro do diretório WebContent/resources/, e para uma organização melhor, dentro de resources/ criamos uma pasta img. Dentro da tag devemos definir dois atributos, o library, que preenchemos com o valor img, que é a pasta que contém nosso logo, e o name, onde é definido o nome do arquivo.

Qual namespace utilizamos para importar a biblioteca de facelets?
O namespace utilizado para importar a biblioteca de facelets é xmlns:ui="http://java.sun.com/jsf/facelets".

Como fazemos a associação de uma página com um template?
A associação é feita envolvendo todo o conteúdo da página que importará o template pela tag ui:composition, indicando pelo atributo template, o template a ser associado.

Dentre os itens citados abaixo, qual está relacionado com as vantagens do uso de templates?
Template é um molde que pode ser aproveitado por outras páginas, isso implica em muitas vantagens, entre elas a extração do código repetitivo para um único local, que diminui o tráfego de dados na requisição, torna o código mais limpo e diminui os problemas de manutenção, já que, se quisermos alterar o código, basta alterar no template, que todas as páginas que o seguirem também serão alteradas.


---------------------------------------------------------------------------
Seção 09 - Completando o CRUD

CRUD - Create Read Update Delete
Funções simples de um sistema.

famoso CRUD (Create, Read, Update, Delete).


mplementando a exclusão
Vamos começar com a exclusão de um livro. Na página livro.xhtml já temos a lista de livros. Nela, adicionaremos uma nova coluna, onde ficará o link para remover:

<h:dataTable value="#{livroBean.livros}" var="livro" id="tabelaLivros">
    <h:column>
        <f:facet name="header">Título</f:facet>
        <h:outputText value="#{livro.titulo}" />
    </h:column>
    <h:column>
        <f:facet name="header">ISBN</f:facet>
        <h:outputText value="#{livro.isbn}" />
    </h:column>
    <h:column>
        <f:facet name="header">Preço</f:facet>
        <h:outputText value="#{livro.preco}">
            <f:convertNumber type="currency" pattern="R$ #0.00"
                currencySymbol="R$" locale="pt_BR" />
        </h:outputText>
    </h:column>
    <h:column>
        <f:facet name="header">Data</f:facet>
        <h:outputText value="#{livro.dataLancamento.time}">
            <f:convertDateTime pattern="dd/MM/yyyy"
                timeZone="America/Sao_Paulo" />
        </h:outputText>
    </h:column>
    <!-- NOVA COLUNA AQUI -->
    <h:column>
        <f:facet name="header">Remover</f:facet>
        <h:commandLink value="Remover" />
    </h:column>
</h:dataTable>
Acessamos a página e... Obtivemos o seguinte erro: Remover: Este link está desativado, pois não está aninhado em um formulário JSF! O que exatamente aconteceu?

Acontece que, novamente usamos o componente <h:commandLink> e, como qualquer outro comando (UICommand), ele deve ficar dentro de um <h:form>. Vamos colocar <h:form>no início e final da tabela:

<h:form id="formTabelaLivros">
    <h:dataTable value="#{livroBean.livros}" var="livro" id="tabelaLivros">

        <!-- outras colunas omitidas -->

        <h:column>
            <f:facet name="header">Remover</f:facet>
            <h:commandLink value="Remover" />    
        </h:column>
    </h:dataTable>
</h:form>
Mas antes, devemos fazer uma pequena alteração no botão "Gravar", já que agora, para referenciar a tabela tabelaLivros, precisamos dizer que agora esta tabela está dentro do formulário formTabelaLivros:

<h:commandButton value="Gravar" action="#{livroBean.gravar}">
    <f:ajax execute="@form" render="@form :formTabelaLivros:tabelaLivros" />
</h:commandButton>
Agora, com o "Remover" visível, falta chamar um método no h:commandLink que realmente apague o livro no banco de dados, através do atributo action:

<h:form id="formTabelaLivros">
    <h:dataTable value="#{livroBean.livros}" var="livro" id="tabelaLivros">

        <!-- outras colunas omitidas -->

        <h:column>
            <f:facet name="header">Remover</f:facet>
            <h:commandLink value="Remover" action="#{livroBean.remover(livro)}" />
        </h:column>
    </h:dataTable>
</h:form>
Mas o método remover ainda não existe, vamos implementá-lo na classe LivroBean:

public void remover(Livro livro) {
    System.out.println("Removendo livro " + livro.getTitulo());
    new DAO<Livro>(Livro.class).remove(livro);
}
Ótimo! Já conseguimos remover um livro da lista de livros cadastrados, mas não podemos esquecer (como o instrutor no video) remover o livro da lista de livros guardada no bean:

public void remover(Livro livro) {
    System.out.println("Removendo livro " + livro.getTitulo());
    new DAO<Livro>(Livro.class).remove(livro);
    this.livros.remove(livro); //removendo da lista
}
E a alteração?
Com a remoção implementada, o próximo passo é fazer a alteração dos dados de um livro. Para isso, devemos ter uma nova coluna com um novo link, semelhante à coluna "Remover", que, ao ser clicado, carregue os dados do livro em questão no formulário. Após isso nós gravamos o formulário e salvamos as alterações.

Vamos dividir esse processo em dois passos, o primeiro é criar a coluna com o link que carregue os dados do livro no formulário; o segundo é como iremos gravar essas alterações do livro.

Começando com o primeiro passo, criamos mais uma coluna em index.xhtml:

<h:form id="formTabelaLivros">
    <h:dataTable value="#{livroBean.livros}" var="livro" id="tabelaLivros">

        <!-- outras colunas omitidas -->

        <h:column>
            <f:facet name="header">Remover</f:facet>
            <h:commandLink value="Remover" action="#{livroBean.remover(livro)}" />    
        </h:column>
        <h:column>
            <f:facet name="header">Alterar</f:facet>
            <h:commandLink value="Alterar" action="#{livroBean.carregar(livro)}" />
        </h:column>
    </h:dataTable>
</h:form>
Mas mais uma vez precisamos implementar o método carregar em LivroBean, já que o mesmo ainda não existe:

public void carregar(Livro livro) {
    System.out.println("Carregando livro " + livro.getTitulo());
    // O que fazer?
}
Mas como mostrar no formulário os dados do livro passado por parâmetro no método carregar? Vamos lembrar que na classe LivroBean temos um atributo livro, e esse atributo é o livro do nosso formulário! Logo, basta fazer com que esse atributo da classe receba o livro passado por parâmetro, assim os dados desse livro serão colocados no formulário:

public void carregar(Livro livro) {
    System.out.println("Carregando livro " + livro.getTitulo());
    this.livro = livro;
}
Agora podemos recarregar a página index.xhtml. Mas ao fazer isso recebemos outro erro! Uma exceção do tipo LazyInitializationException. Como resolver essa exceção?

Ajustando o relacionamento
O problema está no relacionamento dos livros com os seus autores. Um livro pode ter muitos autores, e um autor pode escrever vários livros, por isso a relação deles é de many-to-many (muitos para muitos). No Hibernate, qualquer relação *ToMany é LAZY, isso porque essas relações *toMany são provavelmente mais custosas, trazendo mais objetos para a memória. Ok, mas o que isso interfere no nosso projeto?

Isso significa que quando carregamos os livros, os autores não são carregados ao mesmo tempo. Ou seja, quando clicamos em "Alterar", conseguimos carregar os dados do livro, mas não os do autor! Por isso ocorre a exceção.

Para resolver isso, podemos dizer para o Hibernate para, quando carregar um livro, automaticamente carregar os seus autores, ou seja, ao invés de LAZY, queremos que a relação seja EAGER. Então lá na classe Livro, adicionamos essa opção na anotação:

@ManyToMany(fetch=FetchType.EAGER)
private List<Autor> autores = new ArrayList<Autor>();
Ótimo, com isso conseguimos carregar todos os dados de um livro, primeiro passo concluído! Agora vamos alterá-lo e salvá-lo, o que acontece? Nada!!!

Salvando as alterações de um livro
Mas afinal, o que aconteceu? Visualizando a estrutura da página index.xhtml, vemos que o botão "Gravar" chama o método de mesmo nome, da classe LivroBean. Vamos dar uma olhada nele:

public void gravar() {
    System.out.println("Gravando livro " + this.livro.getTitulo());

    if (livro.getAutores().isEmpty()) {
        FacesContext.getCurrentInstance().addMessage("autor",
                new FacesMessage("Livro deve ter pelo menos um Autor."));
        return;
    }

    new DAO<Livro>(Livro.class).adiciona(this.livro);        

    this.livro = new Livro();
}
Por sua vez, o método gravar chama o método adiciona, da classe DAO:

public void adiciona(T t) {

    // consegue a entity manager
    EntityManager em = new JPAUtil().getEntityManager();

    // abre transacao
    em.getTransaction().begin();

    // persiste o objeto
    em.persist(t);

    // commita a transacao
    em.getTransaction().commit();

    // fecha a entity manager
    em.close();
}
Se queremos alterar um livro, não deveríamos chamar o método adiciona, certo? E é exatamente isso que está ocorrendo. O método adiciona chama o método persist que não funciona com livros já cadastrados, com livros que já estejam no banco.

Para resolver isso, vamos voltar à classe LivroBean e modificar o método gravar. Se o livro não existir no banco, ou seja, se ele não tiver um id (for nulo), ele será adicionado no banco. Se o livro tiver um id, significa que ele já existe no banco, aí iremos atualizá-lo, chamando o método atualiza do DAO:

public void gravar() {
    System.out.println("Gravando livro " + this.livro.getTitulo());

    if (livro.getAutores().isEmpty()) {
        FacesContext.getCurrentInstance().addMessage("autor",
                new FacesMessage("Livro deve ter pelo menos um Autor."));
        return;
    }

    if (this.livro.getId() == null) {
        new DAO<Livro>(Livro.class).adiciona(this.livro);        
    } else {
        new DAO<Livro>(Livro.class).atualiza(this.livro);
    }

    this.livro = new Livro();
}
Agora experimente alterar o preço de um livro, por exemplo, e tente salvar essa alterações. Funcionou? Segundo passo concluído!

Excluindo autores do livro
Para finalizar, falta um último detalhe no nosso formulário. Caso adicionemos um autor erradamente, temos como excluí-lo? Atualmente não. Vamos então implementar essa funcionalidade, a remoção de autor.

A primeira coisa que devemos fazer é mexer no formulário, adicionando um "X" ao lado do autor, um link para removê-lo. Esse link chama o método removerAutorDoLivro, da classe LivroBean:

<h:dataTable value="#{livroBean.autoresDoLivro}" var="autor" id="tabelaAutores">
    <h:column>
        <h:outputText value="#{autor.nome}" />
    </h:column>
    <h:column>
        <h:commandLink value="X" action="#{livroBean.removerAutorDoLivro(autor)}" />
    </h:column>
</h:dataTable>
Como o método removerAutorDoLivro ainda não existe, vamos implementá-lo, lá na classe LivroBean. A classe Livro tem um método que retorna todos os autores, então vamos acessar esse método e excluir o autor passado por parâmetro, através do método remove:

public void removerAutorDoLivro(Autor autor) {
    this.livro.getAutores().remove(autor);
}
Por questões de boas práticas, queremos que o método removerAutorDoLivro só remova o autor, sem precisar acessar a lista de autores do livro, vamos destinar essa responsabilidade de acessar a lista de autores para a classe Livro:

public void removeAutor(Autor autor) {
    this.autores.remove(autor);        
}
E o método removerAutorDoLivro, da classe LivroBean, passará a chamar este método:

public void removerAutorDoLivro(Autor autor) {
    this.livro.removeAutor(autor);
}
O que aprendemos
O que é CRUD;
Remover o livro através do h:commandLink;
Relacionamento EAGER;
Alterar o livro e exclusão do autor;


É muito comum usarmos JSF com JPA. Contudo, o erro LazyInitializationException pode acontecer quando tentarmos acessar um relacionamento não inicializado. Uma das primeiras formas (existem outras, inclusive mais elegantes) é alterar o tipo de relacionamento seja @ManyToMany ou OneToMany. Essa alteração diz feito ao tipo de busca que é realizada. O padrão é preguiçoso, contudo, se quisermos alterá-lo para um carregamento do tipo ansioso fazemos:

A resposta correta é:
@ManyToMany(fetch=FetchType.EAGER)
private List<Autor> autores = new ArrayList<Autor>();
As anotações @OneToMany e @ManyToMany podem receber como parâmetro o tipo de busca através da propriedade fetch. Ela pode receber FetchType.LAZY (o padrão), que só busca os dados dos relacionamentos quando forem acessados pela primeira vez ou FetchType.EAGER, que já carrega os objetos do relacionamento com o objeto que contém o relacionamento.

Mais sobre JPA você verá nos treinamentos relacionados com a persistência:

https://cursos.alura.com.br/course/jpa

https://cursos.alura.com.br/course/jpa-avancado


Para saber mais: f:setPropertyActionListener
Vimos no video como alterar um livro. Colocamos na tabela uma coluna a mais para o link que carregar o livro para alteração:
<h:commandLink value="Alterar" action="#{livroBean.carregar(livro)}" />
O método carregar coloca o livro do parâmetro no formulário:

public void carregar(Livro livro) {
    System.out.println("Carregando livro " + livro.getTitulo());
    this.livro = livro;
}
Repare que esse método faz muito pouca coisa, é apenas um atribuição do parâmetro livro para o atributo this.livro (parecido com um setter). Há um atalho para tal atribuição que JSF oferece. Podemos usar o componente f:setPropertyActionListener com o mesmo efeito:

<h:commandLink value="Alterar" >
    <f:setPropertyActionListener target="#{livroBean.livro}" value="#{livro}" />
</h:commandLink>
Repare que o h:commandLink não possui o atributo action. A ação é definida pelo componente f:setPropertyActionListener.

O comando f:setPropertyActionListener deve ficar dentro da tag h:commandLink

Assim não precisamos do método carregar, mas é preciso implementar o setter para o atributo livro pois o atributo target exige o setLivro no LivroBean.

Sempre há várias formas de atingir um objetivo, no JSF isso não é diferente.
O componente f:setPropertyActionListener existe pois em versões anteriores do JSF não era possível passar parâmetros no método pelo atributo action do comando.

<!-- isso não funcionava no JSF 1.x -->
<h:commandLink value="Alterar" action="#{livroBean.carregar(livro)}" />
Hoje em dia podemos utilizar esses parâmetros na EL, então não há mais tanta necessidade de usar o componente f:setPropertyActionListener.


-----------------------------------------------------------------------------
Seção 10 - Atualizando para o JSF 2.2

É necessário mudar o arquivo faces-config para a nova configuração.
Mudar as importações nas páginas xhtml. Mudar da sun. alguma coisa para xmlns.jcp.org.


Atualização do JSF 2.2
Quando começamos o nosso projeto de livraria, usamos a versão 2.1 do JSF. Nesse meio tempo já saiu uma versão mais recente, a versão 2.2, razão suficiente para uma atualização, não?

O primeiro passo é baixar o novo JAR. Pegaremos o JAR do repositório do Maven através deste link.

O link está na explicação e no exercício também. O JAR novo ficará na pasta WebContent/WEB-INF/lib, mas não podemos esquecer de apagar a versão antiga do JAR.

Uma vez copiado o JAR, devemos deixar claro no arquivo de configuração faces-config.xml que estamos usando a versão 2.2. Faremos isso pelo novo cabeçalho que indica a versão, substituindo o antigo:

<faces-config version="2.2"
    xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd">

  <!-- resto omitido -->
Novo namespace do JSF 2.2
O JSF 2.2 trouxe vários bug-fixes, comparado com a versão 2.1, mas também algumas novidades. Veremos duas aqui nesse curso, as viewActions e pass through atributes.

No entanto, antes de realmente usar uma nova feature do JSF 2.2, devemos atualizar o namespace do mesmo! O que? Se lembra que todos os componentes JSF usam um prefixo, como h: ou f:? Esse prefixo na verdade é um atalho para não escrever uma URL (ou namespace) completa.

Até a versão JSF 2.2, todas as URLs começavam com http://java.sun.com, já que foi a antiga empresa Sun que começou com o desenvolvimento JSF. O tempo passou e a Sun nem existe mais, por isso os membros da especificação mudaram a URL (o namespace) para http://xmlns.jcp.org.

Ou seja, devemos substituir o namespace http://java.sun.com/jsf/html por http://xmlns.jcp.org/jsf/html, ok?

Então a declaração no início da página ficará:

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:f="http://xmlns.jcp.org/jsf/core"
    xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
Faremos a atualização em cada página xhtml.

Novidades do JSF 2.2
Entre as novidades do JSF 2.2, escolhemos duas que são bem úteis no dia a dia de um desenvolvedor JSF!

Já falamos em uma aula anterior que no mundo JSF toda comunicação entre navegador e servidor é feita através de requisições HTTP POST. Apenas a primeira requisição, aquela que constrói a árvore de componentes, é um HTTP GET, a partir daí tudo é um POST.

No entanto, imagine que o cliente está trabalhando na aplicação de livraria e gostaria de passar um link para outro funcionário que mostra o formulário do autor. Sabemos que o link do formulário é http://localhost:8080/livraria/autor.xhtml, só que esse formulário sempre fica vazio, pois ao chamar enviamos um GET que cria uma tela nova.

ViewAction
Queremos passar no formulário o autor a ser editado, e o formulário deverá vir preenchido automaticamente. Isso é algo muito comum na web e normalmente é feito através de um parâmetro na requisição GET, por exemplo:

http://localhost:8080/livraria/autor.xhtml?autorId=1

Ou seja, queremos enviar um GET mas já populando o formulário com o autor que possui a id 1! Como fazer isso? Bom, JSF 2.2 trouxe um recurso para tal, que foi batizado de viewAction. Podemos associar, ao construir a view, uma ação que vai carregar o autor automaticamente!

Perfeito, mas como funciona? Nada complicado, devemos adicionar no início da página um metadata, que define qual parâmetro queremos ler, e qual é a action/método a chamar:

<f:metadata>
    <f:viewParam name="autorId" value="#{autorBean.autorId}"/>
    <f:viewAction action="#{autorBean.carregarAutorPelaId}"/>
</f:metadata>
Vamos adicionar essas linhas no início da página autor.xhtml, logo abaixo de <ui:composition>.

Agora falta preparar o AutorBean, para realmente carregar o autor pela id. Vamos adicionar o atributo autorId, seu getter e setter, e um novo método com o nome definido no atributo action:

private Integer autorId;

public Integer getAutorId() {
    return autorId;
}

public void setAutorId(Integer autorId) {
    this.autorId = autorId;
}

public void carregarAutorPelaId() {
    this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
}
Isso já é todo o necessário para funcionar. Vamos testar e enviar uma requisição GET:

http://localhost:8080/livraria/autor.xhtml?autorId=1



O formulário já vem preenchido, ou seja, a nossa viewAction foi executada.

Atributos passThrough
Tudo mundo já ouviu falar de HTML5, certo? HTML5 é a nova versão da linguagem HTML, que traz importantes mudanças, entre eles várias novas tags para criar páginas mais semânticas.

Podemos ver as novas funcionalidades e a documentação do HTML 5 diretamente no site do W3Schools: http://www.w3schools.com/html/html_form_input_types.asp

Agora temos o problema que toda nossa página HTML é renderizada através dos componentes JSF. Perdemos o controle do HTML que é um tradeoff do mundo JSF. Mas felizmente o JSF 2.2 trouxe uma solução para aproveitar algumas novas tags do HTML5. Essa nova feature se chama passThroughAttribute.

A ideia é que podemos definir um atributo no componente, que é simplesmente passado para o HTML, sem alteração. Assim, podemos incluir atributos que são ignorados no mundo JSF, mas importam dentro do HTML.

Veja o exemplo abaixo, onde definimos um inputText que recebe um atributo type=”email” usando o componente aninhado f:passThroughAttribute. Baseado nesse atributo o navegador executa uma validação do e-mail digitado, mas no mundo JSF não há nenhuma importância:

<h:outputLabel value="Email:" for="email" />
<h:inputText value="#{autorBean.autor.email}" id="email">
      <f:passThroughAttribute name="type" value="email" />
</h:inputText>
Assim podemos usufruir dos tipos de inputs do HTML5, como type=”number” ou type=”date”, que não existem no JSF.

Vamos colocar o código no formulário dos dados do autor, da página autor.xhtml, logo após h:message:

<h:form id="autor">
    <fieldset>
        <legend>Dados do Autor</legend>
        <h:panelGrid columns="3">

            <h:outputLabel value="Nome:" for="nome" />
            <h:inputText id="nome" value="#{autorBean.autor.nome}"
                required="true">
                <f:validateLength minimum="5" />
                <f:ajax event="blur" render="messageNome" />
            </h:inputText>

            <h:message for="nome" id="messageNome" />

            <!-- NOVO CÓDIGO AQUI -->

            <h:outputLabel value="Email:" for="email" />
            <h:inputText id="email" value="#{autorBean.autor.email}"
                required="true">
                <f:passThroughAttribute name="type" value="email" />
            </h:inputText>

            <h:message for="email" id="messageEmail" />

            <h:commandButton value="Gravar" action="#{autorBean.gravar}" />
        </h:panelGrid>
    </fieldset>
</h:form>
Agora só falta criar o e-mail dentro do nosso modelo, na classe Autor:

@Entity
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String email; // Não esqueça de criar o gettter e setter
Vamos chamar novamente a página autor.xhtml (aproveitando a viewAction):

http://localhost:8080/livraria/autor.xhtml?autorId=1



A validação do campo e-mail é executada pelo navegador e não pelo JSF. É tudo client-side!

O que aprendemos
Usamos a versão 2.2 do JSF
Usamos o novo namespace para declarar os componentes
Testamos a viewAction para carregar um formulário pelo HTTP GET
Testamos passThroughAttribute para definir um novo atributo no HTML

Qual é o novo namespace da taglib html da especificação JSF?
Como a empresa SUN nem existe mais, os membros da especificação JSF decidiram alterar o namespace das tags:
HTML - http://xmlns.jcp.org/jsf/html
Core - http://xmlns.jcp.org/jsf/core
Facelets - http://xmlns.jcp.org/jsf/facelets
Aplicando isso na pagina XHTML fica:

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:f="http://xmlns.jcp.org/jsf/core"
    xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
Lembrando que o namespace é apenas uma identificação. O Eclipse ou a JVM não acessam essa URL na internet. Essa URL é procurada dentro de um arquivo XML que se encontra no JAR do Mojarra.

Observação: A sigla JCP significa Java Community Process. É a organização que gerencia as especificações do mundo Java e JavaEE.

---------------------------------------------------------------------------------
Seção 11 - Autenticação do usuário

Criando a página de login
Autenticação do usuário significa, basicamente, que é preciso fazer um login. Se nós precisamos fazer login, nós precisamos de um formulário para isso, isso quer dizer que precisamos criar mais um xhtml, o login.xhtml. Nesse xhtml devemos ter dois inputs, um para o e-mail do usuário e outro para a senha:

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:f="http://xmlns.jcp.org/jsf/core"
    xmlns:ui="http://xmlns.jcp.org/jsf/facelets">

    <ui:composition template="_template.xhtml">

        <ui:define name="titulo">
                Login
        </ui:define>

        <ui:define name="conteudo">
            <h:form id="login">
                <fieldset>
                    <legend>Login</legend>
                    <h:panelGrid columns="3">

                        <h:outputLabel value="Email:" for="email" />
                        <h:inputText id="email" value="#{loginBean.usuario.email}"
                            required="true">
                            <f:passThroughAttribute name="type" value="email" />
                        </h:inputText>

                        <h:message for="email" id="messageEmail" />

                        <h:outputLabel value="Senha:" for="senha" />
                        <h:inputText id="senha" value="#{loginBean.usuario.senha}"
                            required="true">
                            <f:passThroughAttribute name="type" value="password" />
                        </h:inputText>

                        <h:message for="senha" id="messageSenha" />

                        <h:commandButton value="Efetuar Login" 
                            action="#{loginBean.efetuaLogin}" />
                    </h:panelGrid>
                </fieldset>
            </h:form>
        </ui:define>
    </ui:composition>
</html>
Repare que, para esse formulário funcionar, precisamos de um modelo, o Usuario e de um novo bean, o LoginBean.

O modelo Usuario
O modelo, a classe Usuario será muito semelhante à classe Autor. A diferença é que, ao invés de nome, um usuário terá um e-mail:

@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String senha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
Criado o modelo, é preciso mapeá-lo no arquivo persistence.xhtml, abaixo do mapeamento da classe Autor:

<class>br.com.caelum.livraria.modelo.Usuario</class>
O intermediário do nosso modelo com a tela, o LoginBean
O último passo agora é criar o LoginBean, para fazer a intermediação do modelo Usuario com a tela login.xhtml. Essa classe terá o usuário que vêm do formulário e o seu getter:

@ManagedBean
@ViewScoped
public class LoginBean {

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }
}
Mas ainda falta o método que é chamado no botão "Efetuar Login", o método efetuaLogin:

public void efetuaLogin() {
    System.out.println("Fazendo login do usuário "
            + this.usuario.getEmail());
}
Por enquanto o método só imprime o e-mail do usuário que está se logando, mas após esse login, o que deve acontecer? Devemos verificar se o usuário e a senha existem, e se eles existirem, o usuário deve ser redirecionado para a página principal da nossa aplicação, a página livro.xhtml. Então dentro deste método, vamos fazer um redirecionamento para a página livro.xhtml, e isso não é novidade, nós já fizemos isso antes, quando um autor é cadastrado.

Logo após de cadastrar um autor, o usuário é redirecionado para a página livro.xhtml, então vamos fazer a mesma coisa que foi feita anteriormente:

public String efetuaLogin() {
    System.out.println("Fazendo login do usuário "
            + this.usuario.getEmail());

    return "livro?faces-redirect=true";
}
Com o formulário, modelo e bean criados, vamos testar a nossa aplicação. Repare que após clicarmos no botão "Efetuar Login", somos redirecionados para livro.xhtml!

Mas agora temos que fazer essa validação, temos que verificar se o usuário existe no banco, para aí sim ele ser redirecionado.

Verificando a existência no usuário
Se iremos verificar se o usuário existe no banco de dados, a primeira coisa que devemos fazer é ter um usuário no banco de dados! Então iremos abrir o MySQL e iremos fazer um insert para adicionar um usuário válido. Por exemplo:

mysql> use livrariadb;
mysql> insert into Usuario(email, senha) values ('nico.steppat@caelum.com.br', '12345');
Query OK, 1 row affected (0.05 sec)

mysql> select * from Usuario;
+----+----------------------------+-------+
| id | email                      | senha |
+----+----------------------------+-------+
|  1 | nico.steppat@caelum.com.br | 12345 |
+----+----------------------------+-------+
1 row in set (0.00 sec)
Agora que temos um usuário criado no banco, podemos testá-lo. Isso significa que se o usuário existe, ele é redirecionado para a página livro.xhtml, se não existe, não acontece nada, o método retorna null:

public String efetuaLogin() {
    System.out.println("Fazendo login do usuário "
            + this.usuario.getEmail());

    if (existe) { // Mas o que significa "existe"?
        return "livro?faces-redirect=true";
    }

    return null;
}
O existe terá que ser um booleano, ele guardará o retorno do método existe, que recebe o usuário do formulário como parâmetro e faz um select no banco de dados para verificar se esse usuário existe ou não. Mas vamos separar essa responsabilidade na classe UsuarioDao que iremos criar:

public String efetuaLogin() {
    System.out.println("Fazendo login do usuário "
            + this.usuario.getEmail());

    boolean existe = new UsuarioDao().existe(this.usuario);

    if (existe) {
        return "livro?faces-redirect=true";
    }

    return null;
}
Com o método efetuaLogin pronto, falta implementar a classe UsuarioDao, juntamente com o método existe. Esse método inicializará o EntityManager e criará uma query tipada(typed query), do tipo Usuario, pois a query retornará o usuário do banco de dados onde seu e-mail e senha for igual ao e-mail e senha do usuário passado por parâmetro.

public class UsuarioDao {

    public boolean existe(Usuario usuario) {

        EntityManager em = new JPAUtil().getEntityManager();
        TypedQuery<Usuario> query = em
                .createQuery(
                        "select u from Usuario u where u.email = :pEmail and u.senha = :pSenha",
                        Usuario.class);

        return false;
    }
}
Com a query criada, precisamos configurar os parâmetros pEmail e pSenha da query, que são o e-mail e senha do usuário passado por parâmetro, faremos isso através do método setParameter e depois iremos executar a query, através do método getSingleResult, pois a nossa query só retornará um único resultado, caso retorne algo. Para finalizar, o método retornará true se o resultado for diferente de null:

public boolean existe(Usuario usuario) {

    EntityManager em = new JPAUtil().getEntityManager();
    TypedQuery<Usuario> query = em
            .createQuery(
                    "select u from Usuario u where u.email = :pEmail and u.senha = :pSenha",
                    Usuario.class);

    query.setParameter("pEmail", usuario.getEmail());
    query.setParameter("pSenha", usuario.getSenha());

    Usuario resultado = query.getSingleResult();

    em.close();

    return resultado != null;
}
Testando com um login cadastrado no banco, vemos que está funcionando! Vamos agora testar com um login e senha que não existe... Deu uma exceção, a NoResultException.

Para resolver isso iremos realizar um try...catch na exceção. Caso essa exceção ocorra, ou seja, caso a query não retorne nenhum resultado, o método irá retornar false, para nada acontecer. Se não houver exceção, o método retorna true:

public boolean existe(Usuario usuario) {

    EntityManager em = new JPAUtil().getEntityManager();
    TypedQuery<Usuario> query = em
            .createQuery(
                    "select u from Usuario u where u.email = :pEmail and u.senha = :pSenha",
                    Usuario.class);

    query.setParameter("pEmail", usuario.getEmail());
    query.setParameter("pSenha", usuario.getSenha());

    try {
        Usuario resultado = query.getSingleResult();
    } catch (NoResultException ex) {
        return false;
    }

    em.close();

    return true;
}
Podemos testar agora. Com um usuário válido, continuamos sendo redirecionados para a página livro.xhtml e com um usuário inválido, nada acontece! Ótimo, a autenticação do usuário está funcionando corretamente. Mas ainda nada impede que acessemos a página livro.xhtml sem realizarmos login, através da url http://localhost:8080/livraria/livro.xhtml. Então o próximo passo que devemos implementar é a autorização do usuário, ou seja, só devemos conseguir acessar as páginas com o login realizado, e é isso que veremos no próximo capítulo!

O que aprendemos nesse capítulo:
Criamos um novo modelo (Usuário) e uma nova Bean(LoginBean);
TypedQuery para usuário;
Lidamos com erro de NoResultException;



Você se lembra dos Pass Through Attributes do capítulo anterior? Tudo bem em testar o seu conhecimento?
Uma das informações que vamos precisar pra autenticar o usuário é seu e-mail. Sabemos que o HTML5 possui um tipo de input chamado email e queremos utilizá-lo, podemos utilizar o Pass Through Attributes que já conhecemos pra isso. Qual das alternativas abaixo implementa corretamente?
Queremos definir o atributo name com type e o value com e-mail. Portanto, a alternativa mais correta seria:
<h:inputText id="email" value="#{loginBean.usuario.email}" required="true">
    <f:passThroughAttribute name="type" value="email" />
</h:inputText>



Vimos como aplicar os Pass-through Attributes no nosso view pela tag f:passThroughAttribute:
<h:inputText id="email" value="#{loginBean.usuario.email}" required="true">
    <f:passThroughAttribute name="type" value="email" />
</h:inputText>
Agora os Pass-through Elements "invertem" um pouco como declarar o componente JSF. Através desse elemento usamos HTML puro, mas declaramos o "mundo JSF" nos atributos. Veja a declaração do input que representa o email do autor:

<input type="email" jsf:id="email" jsf:value="#{autorBean.autor.email}" />
Repare que usamos a tag input do mundo HTML e a id e o value do JSF foram definidos pelos atributos!

Para tudo isso funcionar não podemos esquecer a importar o namespace:

xmlns:jsf="http://xmlns.jcp.org/jsf"



---------------------------------------------------------------------------
Seção 12 - Autorização pelo PhaseListener

No capítulo anterior, foi falado sobre a autenticação do usuário, como fazer o seu login. Mas nós só verificamos se o usuário estava correto ou não, o problema ainda é que podemos acessar livremente qualquer página da nossa aplicação através da sua URL específica, como localhost:8080/livraria/livro.xhtml ou localhost:8080/livraria/autor.xhtml, ou seja, atualmente não é necessário estar logado para acessar as páginas da nossa aplicação. Precisamos fechar o acesso às nossa páginas, somente um usuário com login feito que pode acessá-las, precisamos fazer a autorização do usuário, e é isso que faremos nesse capítulo.

O PhaseListener
Precisamos verificar cada requisição para nossa aplicação. Ou seja, antes de fazer qualquer coisa devemos checar se o usuário já fez login, se sim, ele pode continuar, se não, o usuário será redirecionado para a página de login. Se você já assistiu algum treinamento sobre Servlets, Spring MVC ou VRaptor, por exemplo, já usou um recurso para essa autorização do usuário, esse recurso é o filtro, ou interceptador. Precisamos filtrar, interceptar todas as requisições, para checar se o usuário já fez login.

Só que nós estamos no mundo JSF, então vamos focar no que o JSF nos oferece. Vamos utilizar um recurso já visto para filtrar as requisições, o PhaseListener, nada nos impede de utilizá-lo para algo mais sofisticado, como a autorização do usuário.

Implementando o nosso Autorizador
Nós já utilizamos o PhaseListener uma vez, na classe LogPhaseListener, então vamos copiá-la, mas mudando o seu nome, a nova classe se chamará Autorizador. Mas um PhaseListener precisa ser cadastrado no arquivo WebContent/WEB-INF/faces-config.xml, então adicione dentro da tag <lifecycle>:

<phase-listener>br.com.caelum.livraria.util.Autorizador</phase-listener>
Pronto, agora podemos focar no Autorizador. O PhaseListener anterior foi configurado para rodar em cada fase, mas no Autorizador queremos que ele rode apenas na primeira fase, a fase RESTORE_VIEW, pois é no início que iremos verificar se o usuário existe ou não.

Precisamos do nome da página pare verificar se ela é protegida ou não, e para isso é preciso que tenhamos em mãos a árvore de componentes. Para ter essa árvore de componentes, precisamos executar o Autorizador depois da fase, logo implementaremos o método afterPhase. A classe Autorizador ficará assim:

public class Autorizador implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
O primeiro passo é recuperar a árvore programaticamente, porque queremos saber o nome da página. Através do evento, pegamos o facesContext, que já conhecemos, ele é criado a cada requisição. E através desse facesContext que pegamos o elemento raiz da árvore de componentes, que programaticamente se dá através do método getViewRoot(), e através desse viewRoot que pegamos o nome da página, através do método getViewId(). Depois vamos imprimir esse nome para ver se está correto:

@Override
public void afterPhase(PhaseEvent event) {
    FacesContext context = event.getFacesContext();
    String nomePagina = context.getViewRoot().getViewId();

    System.out.println(nomePagina);
}
Mas porque queremos saber o nome da página? Porque há uma página que não queremos fazer a autorização, a página login.xhtml. Se o usuário está acessando essa página, ele está querendo se autenticar, então devemos deixá-lo continuar:

@Override
public void afterPhase(PhaseEvent event) {
    FacesContext context = event.getFacesContext();
    String nomePagina = context.getViewRoot().getViewId();

    System.out.println(nomePagina);

    if ("/login.xhtml".equals(nomePagina)) { 
        return;
    }
}
O return significa que não iremos fazer nada e que o código continuará normalmente com as fases seguintes. Se o código não entrar no if, significa que o usuário está tentando acessar uma outra página da nossa aplicação, então precisamos fazer uma verificação de acesso.

Como saber se o usuário fez login?
Na classe LoginBean, no método efetuaLogin, verificamos em um if se o usuário existe, então a ideia é que se o usuário existe, guardamos a informação em algum lugar, para depois poder testar nas próximas requisições se o usuário se autenticou ou não. Então é dentro desse if que guardaremos essa informação, mas como?

Geralmente, em uma aplicação web, usamos a sessão HTTP para guardar essa informação sobre o usuário, e aqui não será diferente. Mas como acessar essa sessão? Primeiro pegamos a instância do facesContext, e como já vimos anteriormente, pegamos-o em qualquer lugar através do método getCurrentInstance().

Com o context em mãos, podemos acessar objetos que rodam ao nível das servlets, através do método getExternalContext(). E com isso podemos pegar a sessão HTTP, utilizando o método getSessionMap(). A sessão HTTP na verdade é um mapa que podemos guardar dados através da relação chave-valor.

Então, na session map, adicionaremos através do método put, uma chave usuarioLogado, e o seu valor, que será o usuário que fez o login:

public class LoginBean {

    // restante do código

    public String efetuaLogin() {
        System.out.println("Fazendo login do usuário "
                + this.usuario.getEmail());

        FacesContext context = FacesContext.getCurrentInstance();

        if (existe) {
            context.getExternalContext().getSessionMap()
                    .put("usuarioLogado", this.usuario);

            return "livro?faces-redirect=true";
        }

        return null;
    }
}
Pronto! Então se o usuário existe banco, se ele se autenticou, guardamos as informações dele numa chave "usuarioLogado".

Agora precisamos acessar essas informações no Autorizador. Recuperamos as informações com quase o mesmo código, só que ao invés de fazer um put, fazemos um get na chave. E se esse usuário logado existir, ou seja, se ele não é nulo, então sabemos que ele realmente fez login e assim ele pode continuar:

@Override
public void afterPhase(PhaseEvent event) {

    FacesContext context = event.getFacesContext();
    String nomePagina = context.getViewRoot().getViewId();

    System.out.println(nomePagina);

    if ("/login.xhtml".equals(nomePagina)) {
        return;
    }

    Usuario usuarioLogado = (Usuario) context.getExternalContext()
            .getSessionMap().get("usuarioLogado");

    if(usuarioLogado != null) {
        return;
    }
}
Mas se chegamos ao final do código, significa que o usuário não acessou a página de login, e ele não se autenticou, ou seja, devemos redirecioná-lo para a página de login. Para isso, precisamos fazer uma navegação programaticamente com o JSF, e para isso o JSF possui um objeto específico para fazer essa navegação, o NavigationHandler.

Esse navegador vem do nosso context, mas antes temos que pegar os dados da aplicação, através do método getApplication(), para aí acessá-lo, chamando o método getNavigationHandler(). E a partir desse handler que chamamos o método handleNavigation, que recebe três parâmetros. O primeiro parâmetro é o context, que já temos; o segundo parâmetro seria o nome da nossa página caso a mesma estivesse cadastrada no faces-config.xml, mas como não utilizamos isso, utilizamos a string completa de redirecionamento da página, passaremos o valor null; e o terceiro é a página para onde queremos ir, e queremos ir para login.xhtml, logo "/login?faces-redirect=true".

Por fim, não basta definir somente a navegação, também precisamos renderizar a resposta, através do método renderResponse() do context, isso quer dizer que o JSF pulará todas as fases e irá logo para a último, para renderizar a resposta:

@Override
public void afterPhase(PhaseEvent event) {

    FacesContext context = event.getFacesContext();
    String nomePagina = context.getViewRoot().getViewId();

    System.out.println(nomePagina);

    if ("/login.xhtml".equals(nomePagina)) {
        return;
    }

    Usuario usuarioLogado = (Usuario) context.getExternalContext()
            .getSessionMap().get("usuarioLogado");

    if(usuarioLogado != null) {
        return;
    }

    NavigationHandler handler = context.getApplication().getNavigationHandler();
    handler.handleNavigation(context, null, "/login?faces-redirect=true");

    context.renderResponse();        
}
Agora o nosso Autorizador está pronto! Podemos testá-lo. Se acessamos a página livro.xhtml, somos redirecionados para a página de login! E a mesma coisa acontece caso acessemos a página autor.xhtml. Se fizermos login, conseguimos acessar as duas páginas! O que falta agora é fazer um botão para realizar o logout do usuário, e é isso que faremos no próximo capítulo.

O que aprendemos
Adquirir a root view;
Mais utilizações do PhaseListener;
Salvando dados na sessão HTTP;
Navegação automática;


Qual das alternativas abaixo usa corretamente o NavigationHandler pra redirecionar usuário para login.xhtml?
Através do NavigationHandler podemos definir a navegação programaticamente, basta chamar o método handleNavigation
NavigationHandler handler = context.getApplication().getNavigationHandler();
handler.handleNavigation(context, null, "/login?faces-redirect=true");
No entanto, não basta somente definir a navegação, precisamos dizer ao JSF que queremos pular todas as fases do ciclo de vida, indo direto a ultima fase: RenderResponse. Isso é feito através do FacesContext:

context.renderResponse();

Uma coisa interessante é exibir o nome do usuário que está na sessão na página. Como podemos fazer? Uma dica: pesquise sobre como acessar o atributo da sessão via Expression Language.
Podemos acessar o mapa da sessão através da expressão:
<h:outputText value="#{sessionScope['usuarioLogado']}" />

Queremos pegar o POJO usuario no mapa da sessão. E podemos acessar a propriedade email dentro dele:

<h:outputText value="#{sessionScope['usuarioLogado'].email}" />

Você poderia utilizar o h:outputText no nosso template!


Para saber mais: Navegação no JSF 1.x
Com JSF 2.x estamos acostumados a definir a navegação entre páginas confortavelmente no bean. Por exemplo usamos no método gravar do AutorBean:
    public String gravar() {
        //codigo omitido

        return "livro?faces-redirect=true";
    }
No JSF 1.x isso não era possível! Então como era feita a navegação?

Repare que o método gravar devolve uma string. A ideia desse retorno não era para ser o nome da página e sim algum sinal para o controlador JSF. Esse sinal também se chama de outcome. Então o nosso método ficaria:

    public String gravar() {
        //codigo omitido

        return "ok"; //devolvendo um sinal ou outcome
    }
Mas o que o controlador fará com esse outcome? A resposta é simples, vai procurá-lo no XML!! No JSF 1.x era obrigatório definir todas as navegações no faces-config.xml. Bastante burocrático, não?

Dentro do faces-config.xml fica toda navegação centralizada definida através de um elemento navigation-rule. Veja o exemplo:

<navigation-rule>
    <from-view-id>autor.xhtml</from-view-id>
    <navigation-case>
        <from-outcome>ok</from-outcome>
        <to-view-id>livro.xhtml</to-view-id>
        <redirect />
    </navigation-case>
</navigation-rule>
O JSF 2.x simplificou muito o desenvolvimento comparado com a versão 1.x, mas a navegação antiga continua válida e poderia ser utilizada no nosso projeto.

Hoje em dia raramente algum desenvolvedor usa a navegação antiga do JSF. Era simplesmente burocrático demais, mas tinha uma vantagem: Codificando os nomes das páginas dentro do bean, como estamos fazendo no nosso projeto, viola de certa forma a separação das camadas MVC. Seguindo o modelo MVC, os beans não devem conhecer os nomes das páginas pois criamos um acoplamento entre página e bean. Se precisamos renomear a página é preciso alterar a classe também.
No entanto, no dia a dia, essa violação é ignorada a favor da praticidade.




--------------------------------------------------------------------------------
Seção 13 - Logout e o escopo Flash

Realizando o logout do usuário
A primeira coisa que devemos fazer é criar um botão para essa tarefa, como é um botão que terá que aparecer em todas as páginas, o colocaremos no nosso _template.xhtml, logo abaixo do logo. Como é um commandLink, ele precisa estar dentro de um formulário:

<!-- restante do código -->
<h:body>
    <div id="cabecalho">
        <h:graphicImage library="img" name="logo.png" />

        <h:form>
            <h:commandLink value="Logout" action="#{loginBean.deslogar}" />
        </h:form>
        <h1>
            <ui:insert name="titulo"></ui:insert>
        </h1>
    </div>
    <!-- restante do código -->
</h:body>
O LoginBean já está criado, falta implementar o método deslogar. Mas como implementá-lo? Primeiramente, o método deve redirecionar o usuário para a página de login, logo o retorno dele será "login?faces-redirect=true". E para realizar o logout do usuário, basta remover a chave usuarioLogado do sessionMap, logo, sem a chave na sessão, o nosso autorizador não permitirá o acesso às páginas do sistema, para isso o usuário precisará realizar login novamente. Ou seja:

public String deslogar() {

    FacesContext context = FacesContext.getCurrentInstance();
    context.getExternalContext().getSessionMap().remove("usuarioLogado");

    return "login?faces-redirect=true";
}
Podemos testar agora, vamos realizar o login e logo em seguida o logout. Depois, se tentarmos acessar as páginas da aplicação, não conseguimos! Logo o logout foi realizado com sucesso.

Porém, repare uma coisa, o botão de logout aparece na página de login, e isso não é o ideal, já que não tem como o usuário realizar o logout se ele ainda nem fez o login, precisamos mudar isso, não queremos que esse botão apareça na página de login.

Mas o botão está no template, e o template está sendo utilizado por login.xhtml, então como alterar isso? Para essas situações, podemos usar o atributo rendered, definindo uma condição para quando o formulário do botão de logout será renderizado. A nossa condição é que esse formulário apareça somente quando o usuarioLogado não for nulo, então vamos deixar isso explícito:

<!-- restante do código -->
<h:body>
    <div id="cabecalho">
        <h:graphicImage library="img" name="logo.png" />

        <h:form rendered="#{usuarioLogado != null}">
            <h:commandLink value="Logout" action="#{loginBean.deslogar}" />
        </h:form>
        <h1>
            <ui:insert name="titulo"></ui:insert>
        </h1>
    </div>
    <!-- restante do código -->
</h:body>
Podemos testar mais uma vez e ver que tudo está funcionando como o esperado!

Mensagens de validação
O último detalhe que falta na nossa aplicação está referente ao formulário de login. Se clicarmos no botão "Efetuar Login", mas com os campos "Email" e "Senha" em branco, aparecem mensagens de validação, dizendo o motivo que o login não foi realizado.

Porém, se tentarmos realizar login com uma senha incorreta, por exemplo, nada aparece! O usuário fica sem saber o que realmente aconteceu. Precisamos melhorar isso, precisamos informar o usuário para o mesmo saber o que ele fez de errado.

Para melhorar isso, precisamos mudar o método efetuaLogin, da classe LoginBean. Como queremos mostrar mensagens de validação, de erro, precisamos colocá-las após o if, pois o código após o if só será executado se o usuário não existir no banco.

A primeira coisa que iremos mudar é o retorno do método, ele não retornará mais null, vamos deixar explícito para onde o usuário deve ser redirecionado caso o login dê falha, que é a página de login.

Agora, para exibir uma mensagem para o usuário, utilizaremos o já conhecido facesContext, chamando o seu método addMessage, que recebe dois parâmetros. O primeiro parâmetro é o nome do componente. Se você está criando uma mensagem e quer associá-la a um componente específico, ele precisa ficar explicitado aqui. Porém, não é o nosso caso, queremos uma mensagem global, relacionada ao formulário, então deixaremos o valor null; o segundo parâmetro é a mensagem em si, que é uma FacesMessage:

public String efetuaLogin() {
    System.out.println("Fazendo login do usuário "
            + this.usuario.getEmail());

    FacesContext context = FacesContext.getCurrentInstance();
    boolean existe = new UsuarioDao().existe(this.usuario);

    if (existe) {

        context.getExternalContext().getSessionMap()
                .put("usuarioLogado", this.usuario);

        return "livro?faces-redirect=true";
    }

    context.addMessage(null, new FacesMessage("Usuário não encontrado"));

    return "login";
}
Mas não basta só isso, agora precisamos alterar o formulário de login para exibir essa mensagem, e o componente do JSF que a exibe é o componente h:message. Vamos adicioná-lo antes do formulário de login:

<!-- restante do código -->

<ui:define name="conteudo">

    <h:messages />

        <h:form id="login">
<!-- restante do código -->
Podemos fazer agora o teste de tentar fazer o login com um usuário de senha errada... Ótimo, recebemos a mensagem. Mas e se realizarmos o primeiro teste que fizemos neste capítulo, se deixarmos os campos de e-mail e senha em branco, e tentarmos fazer o login... As mensagens também aparecem acima do formulário.

Não é isso que queremos, queremos que as mensagens específicas apareçam ao lado dos seus componentes específicos, e as mensagens globais acima do formulário. Para resolver isso é muito fácil, basta adicionar o atributo globalOnly no componente das mensagens e deixá-lo ativo, true:

<!-- restante do código -->

<ui:define name="conteudo">

    <h:messages globalOnly="true" />

        <h:form id="login">
<!-- restante do código -->
Ótimo! Agora conseguimos realizar tudo o que queríamos, vamos mudar apenas mais um detalhe. O correto, a boa prática, é fazer um redirecionamento após submeter um formulário, para limpar os dados da requisição, se não fizermos isso, um outro usuário poderia repetir a requisição, ou ver os dados da mesma, como e-mail e senha. Vamos então fazer um redirecionamento para a página de login, caso o usuário faça login incorretamente:

public String efetuaLogin() {
    System.out.println("Fazendo login do usuário "
            + this.usuario.getEmail());

    FacesContext context = FacesContext.getCurrentInstance();
    boolean existe = new UsuarioDao().existe(this.usuario);

    if (existe) {

        context.getExternalContext().getSessionMap()
                .put("usuarioLogado", this.usuario);

        return "livro?faces-redirect=true";
    }

    context.addMessage(null, new FacesMessage("Usuário não encontrado"));

    return "login?faces-redirect=true";
}
Mas com isso criamos um outro problema, as mensagens globais de validação não são mais exibidas! Acontece que o FacesContext só existe em uma requisição, e com o faces-redirect estamos fazendo uma segunda requisição, então a mensagem não existe mais.

Porém, temos como resolver isso com o JSF 2, para isso temos que acessar novamente o externalContext e nele há um escopo chamado flash. Esse escopo dura duas requisições, exatamente o que precisamos. Mas para quê queremos usar o flash? Para manter as mensagens. Então chamaremos mais um método, o setKeepMessages, passando true como parâmetro, isso quer dizer que as mensagens que estamos adicionando no flash serão mantidas por duas requisições:

public String efetuaLogin() {
    System.out.println("Fazendo login do usuário "
            + this.usuario.getEmail());

    FacesContext context = FacesContext.getCurrentInstance();
    boolean existe = new UsuarioDao().existe(this.usuario);

    if (existe) {

        context.getExternalContext().getSessionMap()
                .put("usuarioLogado", this.usuario);

        return "livro?faces-redirect=true";
    }

    context.getExternalContext().getFlash().setKeepMessages(true);
    context.addMessage(null, new FacesMessage("Usuário não encontrado"));

    return "login?faces-redirect=true";
}
Agora, mesmo com o redirecionamento, as mensagens são mantidas para mais uma requisição. Com isso completamos a nossa aplicação!

O que aprendemos:
Definimos condição com o atributo rendered;
Relembramos o FacesMessage;
Utilizamos o escopo flash do JSF 2;

Qual dos códigos só exibe o botão de deslogar quando existe um usuário logado ?
Devemos utilizar o atributo rendered, que serve para definir uma condição que diz quando o botão do formulário deve ser renderizado.
Fazemos o teste para ver se o usuarioLogado é diferente de null , e caso seja renderizamos o botão. Ficando assim:

<h:form rendered="#{usuarioLogado != null}">
    <h:commandLink value="Logout" action="#{loginBean.deslogar}" />
</h:form>

Vimos no treinamento que o método .addMessage() de FacesContext recebe dois parâmetros. Escolha a alternativa abaixo que melhor completa uma chamada deste método que retorna uma mensagem global, com o texto "Usuário não encontrado":

O método .addMessage recebe como primeiro parâmetro o nome do componente que você quer associar a mensagem. Caso deixemos o valor null ele assume que a mensagem será global. E como segundo parâmetro ele espera uma FacesMessage com o texto da nossa mensagem. Então a resposta certa seria:
context.addMessage(null, new FacesMessage("Usuário não encontrado"));

O componente do JSF que estamos usando para exibir mensagens é o <h:messages />. Se queremos configurá-lo para exibir somente mensagens globais, qual atributo devemos setar ?
O atributo correto é o globalOnly, que devemos setar com o valor true. Assim só serão exibidas as mensagens globais neste <h:messages/>. Logo, a resposta correta seria:
<h:messages globalOnly="true" />

As mensagens globais são aquelas que não estão associadas com um componente.


Sabemos que é uma boa prática sempre fazer um redirecionamento após submeter um formulário, para limpar os dados da requisição.
Só que configurando um faces-redirect após a requisição inicial, as nossas mensagens deixam de ser exibidas já que o FacesContext só dura uma requisição. Como podemos resolver esse problema do redirecionamento sem perder nossas mensagens de validação ?

Redirecionar após o envio de formulário é uma boa prática pois limpa os dados da requisição e evita de um outro usuário repetir a requisição ou ver os dados da mesma. Essa boa prática até foi promovida para se tornar um padrão no desenvolvimento web, também chamado de Post / Redirect / Get Pattern.
O problema com esse pattern é que perdemos as mensagens que foram criadas na primeira requisição Post. Para resolver o problema de perder as mensagens, devemos acessar o externalContext e habilitar o escopo Flash. Esse escopo dura exatamente duas requisições e nos permite manter nossas mensagens.

Para configurar o escopo Flash utilizamos o método setKeepMessages(true) , que diz que as mensagens que estamos adicionando ao Flash devem durar duas requisições.

Passando tudo para código, ficamos assim:

context.getExternalContext().getFlash().setKeepMessages(true);

o último vídeo falei que existe apenas mais UM curso/módulo sobre JSF no Alura. Na verdade criamos mais dois! Ambos são continuação desse treinamento e devem ser assistidos na sequencia:

Java e JSF 2 - II: Componentes ricos com Primefaces

e

Java e JSF 2 - III: Integração com CDI

O curso II foca no Primefaces no qual alteramos toda nossa interface (as páginas xhtml) para usufruir dos componentes ricos e elegantes de Primefaces. O curso III foca no design de código. Vamos refatorar toda nossa aplicação introduzindo o CDI para deixá-la pronto para o deploy.

Ambos os treinamentos são muito úteis para aqueles que querem utilizar JSF profissionalmente.
