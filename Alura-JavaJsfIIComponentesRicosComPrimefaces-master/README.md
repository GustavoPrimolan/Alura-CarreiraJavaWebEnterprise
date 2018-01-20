# Alura-JavaJsfIIComponentesRicosComPrimefaces

<h1>IMAGENS DO PROJETO</h1>
<img src="https://github.com/GustavoPrimolan/Alura-JavaJsfIIComponentesRicosComPrimefaces/blob/master/Sistema.PNG"/>
<img src="https://github.com/GustavoPrimolan/Alura-JavaJsfIIComponentesRicosComPrimefaces/blob/master/Sistema2.PNG"/>
<img src="https://github.com/GustavoPrimolan/Alura-JavaJsfIIComponentesRicosComPrimefaces/blob/master/Sistema%203.PNG"/>
------------------------------------------------------------------------------
<h1>Seção 01 - Apresentação e teste de avaliação</h1>

Melhorar a interface com primefaces e sua aplicabilidade.

Apresentação do projeto a criar
Nesse curso vamos focar os componentes da biblioteca famosa Primefaces! Com ela, não só iremos melhorar a interface da nossa aplicação, como também melhoraremos a sua usabilidade.

Vou apresentar o projeto que criaremos nesse cursos e mostrar as telas já com Primefaces. Na página de login vemos um medidor de segurança de senhas, um menu para navegação e realizar o logout, máscaras nos campos, um calendário para selecionar a data, uma tabela paginada, onde podemos filtrar e ordenar os dados facilmente, além de uma nova página de vendas e diversas outras melhorias!

Para vocês terem uma ideia de como a nossa aplicação ficará após o uso do Primefaces, seguem algumas imagens:

Então, neste treinamento veremos como aproveitar esses componentes para melhorar o design, a usabilidade das nossas telas, da nossa aplicação. Mas antes, vamos preparar o nosso ambiente para darmos continuidade com o curso.

Teste de avaliação
Como o curso representa a continuação do curso anterior preparamos um pequeno teste, uma auto-avaliação. São perguntas do tipo múltipla escolha para verificar o conhecimento sobre a especificação JSF, mas não há nenhum problema de pular o capítulo ou não passar nas questões. O teste também pode ser visto como uma pequena revisão e lembrar de alguns pontos importantes do treinamento anterior.

Acreditamos que você deve ter no mínimo 8 acertos, mas novamente, nada o impede de continuar o treinamento sem ter passado nos testes.


Na especificação JSF, como definir uma classe gerenciada pelo JSF?
Basta colocar a anotação @ManagedBean (do pacote javax.faces.bean) na classe:
@ManagedBean
public class LivroBean  {
Objetos dessa classe serão administrado pelo JSF. Ou seja, o JSF vai decidir quando criar um objeto da classe LivroBean. Uma vez gerenciado pelo JSF podemos acessar o objeto da classe pela Expression Language dentro de uma página xhtml: #{livroBean}


Quais são as características comuns no desenvolvimento RAD (Rapid Application Development) e que também podem ser encontradas no desenvolvimento com JavaServer Faces?
Resposta correta: Componentes ricos, orientado ao evento e mantém o estado dos componentes (stateful).
São as boas práticas do mundo orientado a objetos que o JSF tenta trazer para o mundo Web:

uso de componentes
desenvolvimento orientado ao evento
desenvolvimento que mantém o estado entre requisições (statefull)

Os componentes definidos na página xhtml serão instanciados pelo controlador padrão do JSF. O resultado disso é a árvore de componentes que fica guardada em memória. Em que momento o controlador cria esta árvore?

Resposta correta: Apenas na primeira requisição (no primeiro HTTP GET).
O controlador instancia os componentes apenas na primeira requisição (no primeiro HTTP GET) desse usuário. A partir dai todos os componentes dessa tela já ficam guardados em memória dentro da árvore de componentes.

Qual é o efeito de anotar um bean gerenciado pelo JSF com a anotação @ViewScoped?
Resposta correta: A instância desse bean existirá enquanto a tela existir.
@ViewScoped é uma anotação que define quanto tempo vive o bean. Esse tempo também é chamado de escopo! Nessa caso o bean vive enquanto a tela (árvore de componentes) vive.

Existem alguns escopos predefinidos no JSF:

RequestScoped - vive durante uma requisição
ViewScoped - vive enquanto acessa mesma pagina, enquanto a tela existe
SessionScoped - vive o tempo que durar a sessão do usuário
ApplicationScoped - vive pela aplicação toda


Cada componente possui atributos especiais para criar uma ligação com uma classe/bean. No caso do h:commandButton, que atributo devemos declarar para indicar que bean usaremos?
Para ligar um comando (ou seja h:commandButton ou h:commandLink) com um bean podemos utilizar o atributo action:
<h:commandButton value="Gravar" action="#{bean.metodo}" />
Há uma alternativa, os comandos também tem um atributo actionListener que funciona de maneira similar:

<h:commandButton value="Gravar" actionListener="#{bean.metodo}" />
@ManagedBean
public class Bean {

   public void metodo(ActionEvent event) { //javax.faces.event.ActionEvent, é opcional
      //implementação
   }
}
A diferença é que um método usado no atributo actionListener sempre deve devolver void enquanto o outro poderia devolver uma String para definir a navegação.

Veja o método form abaixo que está dentro de um bean gerenciado pelo JSF com o nome AutorBean:
    public String form() {
        return "produto";
    }
e um comando na página xhtml:

<h:commandButton value="Navegue" action="#{autorBean.form}" />
O que podemos dizer sobre a navegação?
Resposta correta: Após executar o método form é feito um redirecionamento server-side para a página produto.xhtml.
O retorno do método form é uma string que define o nome da página. Ou seja, será feito um redirecionamento para a página produto, no entanto server-side! Para ser client-side devemos adicionar o parâmetro faces-redirect na string:

    public String form() {
        return "produto?faces-redirect=true";
    }


JSF usa fases bem definidas que são executadas em cada requisição. O que acontece na terceira fase que se chama de PROCESS_VALIDATION?

Resposta correta: Na terceira fase acontece a conversão, se for preciso, e a validação.
O nome não é muito intuitivo mas nessa fase acontece a conversão e validação (se existe algum validador associado com o componente)

Na terceira fase acontece a conversão, se for preciso, e a validação. Se há um problema de conversão ou validação, é criado uma mensagem de erro e o controlador pula automaticamente as fases quatro e cinco para renderizar o HTML com as mensagens de erro através do componente h:message ou h:messages.

Segue também a imagem que mostra todas as fases do JSF:

FASES DO CICLO DE VIDA DE VIDA DO JSF
	RESTORE VIEW
	APPLY REQUEST VALUES
	PROCESS VALIDATIONS
	UPDATE MODEL VALUES
	INVOKE APPLICATION
	RENDER RESPONSE

É importante conhecer as fases e saber qual é a responsabilidade de cada uma.


Usando f:ajax do JSF, como podemos submeter todos os campos do formulário?
Resposta correta: Atribuímos o valor @form ao atributo execute do componente f:ajax.
Assim como podemos utilizar @form no atributo execute, também podemos utilizá-lo no atributo render no caso de todo o formulário necessitar de ser atualizado.

Por exemplo:

<h:form>
    <h:inputText id="nome" value="#{autorBean.nome}" />
    <h:inputText id="email" value="#{autorBean.email}" />
    <h:commandButton value="Cadastrar">
        <f:ajax execute="@form" action="#{autorBean.cadastrar}" />
    </h:commandButton>
</h:form>


O JSF 2 usa facelets para aplicar templates nas páginas xhtml.
Como fazemos a associação de uma página com um template? Por exemplo, tendo um página livro.xhtml, como podemos aplicar o _template.xhtml?

Resposta correta: No livro.xhtml usando a tag ui:composition com o nome do template
A associação é feita envolvendo todo o conteúdo da página que importará o template pela tag ui:composition, indicando pelo atributo template, o template a ser associado.

Por exemplo, vimos na aula do primeiro modulo na página livro.xhtml:

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://java.sun.com/jsf/html"
    xmlns:f="http://java.sun.com/jsf/core"
    xmlns:ui="http://java.sun.com/jsf/facelets"
    xmlns:p="http://primefaces.org/ui">

    <ui:composition template="_template.xhtml">
        <ui:define name="titulo">
                Novo Livro
        </ui:define>
        <ui:define name="conteudo">
            <h:form id="livro">
                <! -- conteudo omitido>            
            </h:form>
        </ui:define>
    </ui:composition>

</html>
Lembrando que através do ui:define preenchemos as lacunas do template!


Veja o pedaço de código xhtml:
<h:commandButton value="Gravar" action="#{livroBean.gravar}"  />
O método gravar do livroBean associado com o comando em cima é executado em qual fase?
Resposta correta: INVOKE_APPLICATION
Os comandos serão executados na quinta fase INVOKE_APPLICATION.

Só há uma exceção quando se usa a atributo immediate no componente:

<h:commandButton value="Gravar" action="#{livroBean.gravar}"  immediate="true"/>
Nesse caso o método gravar é chamado na segunda fase, APPLY_REQUEST_VALUES.



--------------------------------------------------------------------------------
<h1>Seção 02 - Começando com Primefaces</h1>


Configurando o ambiente
Se você já tem o ambiente pronto, pois assistiu ao primeiro treinamento sobre JSF, você pode pular essa parte e seguir com o curso, caso você não tenha assistido o treinamento, vamos ajudá-lo a configurar o seu ambiente.

Nesse treinamento, utilizaremos como IDE a versão Java EE do [Eclipse][1], você pode baixá-lo [aqui][2]. Após o download, você pode extrair o arquivo e começar a utilizá-lo. Como servidor, utilizaremos o [Tomcat 8][3], basta baixá-lo e extrair o zip. Além disso, também utilizaremos o MySQL como banco de dados, mas para a aplicação funcionar, devemos criar o database livrariadb. Abra o console do MySQL e digite:

mysql> create database livrariadb;
Com o database criado, vamos criar o server no Eclipse. Na aba Servers, clique no link disponibilizado para criar um novo servidor. Na tela que abrir, selecione a versão 8 do Tomcat e clique em Next >. Clique em Browse..., selecione o diretório onde o Tomcat foi extraído e clique em Finish.

O próximo passo é importar o projeto inicial, você pode baixá-lo [aqui][4]. Com o download feito, importe o projeto no Eclipse, clicando em Import -> Existing Projects into Workspace, marque a opção Select archive file, selecione o zip baixado e clique em Finish.

Por último, não esqueça de associar a aplicação com o Tomcat, clicando nele com o botão direito, e selecionando Add and Remove.... Depois basta selecionar o projeto, clicar em Add > e depois em Finish.

Ótimo, configuramos o ambiente e estamos prontos para começar a utilizar o Primefaces.

Testando a aplicação
Podemos agora testar a aplicação, acessando o link http://localhost:8080/livraria/livro.xhtml. Repare que somos redirecionados para a página de login, pois precisamos estar logados para poder acessar as páginas da nossa aplicação. Tente fazer o login com qualquer usuário e senha, assim o JPA criará as tabelas para nós e poderemos inserir um usuário válido no banco.

Há um script que vocês podem baixar [aqui][5], nele há comandos SQL para popular o nosso banco. Execute esses comandos e volte para a aplicação, agora podemos fazer o login e acessar a aplicação utilizando o usuário nico.steppat@caelum.com.br e a senha 12345 (ou com algum outro usuário que você tenha criado).

Após isso, podemos dar uma olhada no código da aplicação. Na pasta WebContent, temos três páginas, autor, livro e login, todos eles são arquivos xhtml e utilizam o _template.xhtml. Na pasta WEB-INF temos as bibliotecas e os arquivos faces-config.xml, que é o arquivo de configuração do JSF, e web.xml.

Cada tela possui um bean associado, que delega para os respectivos modelos, que são entidades (as tabelas do banco são criadas baseadas nesses modelos).

No pacote dao, temos um DAO genérico e um específico para o usuário, que faz a query de autenticação, para descobrir se o usuário está no banco, além de ter uma classe para popular o banco e a classe JPAUtil, para inicializar o JPA.

Por fim, no pacote util, temos os dois phases listener, o LogPhaseListener monitora o ciclo da vida, as fases do JSF e o Autorizador, que faz com que a nossa aplicação sempre passe pelo login.

Com tudo configurado e apresentada a nossa aplicação, começaremos com o Primefaces a partir do próximo video!



Primefaces Elite - Pagar suporte
Community download - Versão para comunidade

Primefaces utiliza AJAX por padrão e por conta disso, nos componentes dos botões é necessário colocar o que será submetido para o servidor.


Com o ambiente pronto e configurado, queremos estilizar a nossa aplicação, por exemplo, o ISBN tem um formato específico, então queremos colocar uma máscara no campo dele para facilitar o usuário a digitá-lo; colocar um calendário no campo de data de lançamento, entre outras funcionalidades... Mas aí você pode me falar que pode usar JavaScript, JQuery, CSS... Tudo isso para melhorar a aplicação. Mas aqui é um curso sobre JSF, a ideia é que um componente faça isso para nós!

O JSF por padrão só faz o básico "arroz com feijão", se quisermos algo a mais, precisamos utilizar componentes que estendem as funcionalidades do JSF. E uma das bibliotecas de componentes JSF que mais se destaca no mercado, é o Primefaces. Podemos acessar o seu site, e lá há uma sessão Demo, que demonstra todos os componentes disponíveis (e como utilizá-los) diretamente no navegador.

Mas antes de começar a experimentar esses componentes, vamos configurar o nosso projeto para utilizar o Primefaces. Primeiramente, temos que fazer o seu download, acessando esse link e procurando pela sessão Community Downloads. Lá baixaremos a versão mais recente, que na data de criação deste curso, é a versão 5.3. Feito o download, copie o arquivo jar para dentro da pasta WebContent/WEB-INF/lib.

Declaração do namespace
Pronto, agora podemos começar, e vamos começar pelo início, pela página login.xhtml. Como Primefaces é uma biblioteca de componentes, precisamos declarar o seu namespace, e o prefixo geralmente utilizado é o p, então vamos seguir esse padrão. Basta adicionar dentro da tag <html>, juntamente com os outros namespaces:

xmlns:p="http://primefaces.org/ui"
Os primeiros componentes do Primefaces
A ideia agora é utilizar cada vez mais os componentes do Primefaces. Vamos "primefacear" todo o conteúdo do formulário, começando pelo título "Login", ele passará a ser um <p:outputPanel>. Depois, o fieldset e legend, agora teremos um <p:fieldset>, que já tem um atributo legend, então podemos substituí-los. Após isso, a ideia é substituir todos os componentes padrões do JSF, que utilizam o prefixo h, pelo prefixo p, ou seja, o Primefaces não só define componentes novos, como também redefine os componentes padrões do JSF. A razão dele fazer isso é que o Primefaces dá "algo a mais" para esses componentes, pode ser que o componente já faça ajax, ou que já venha com um CSS aplicado, etc. Ou seja, não precisamos reaprender a API.

Então nosso formulário ficará assim:

<ui:define name="titulo">
    <p:outputPanel>Login</p:outputPanel>
</ui:define>

<ui:define name="conteudo">

    <p:messages globalOnly="true" />

    <h:form id="login">
        <p:fieldset legend="Login">
            <h:panelGrid columns="3">

                <p:outputLabel value="Email:" for="email" />
                <p:inputText id="email" value="#{loginBean.usuario.email}" required="true">
                    <f:passThroughAttribute name="type" value="email" />
                </p:inputText>
                <p:message for="email" id="messageEmail" />

                <p:outputLabel value="Senha:" for="senha" />
                <p:inputText id="senha" value="#{loginBean.usuario.senha}" required="true" />
                    <f:passThroughAttribute name="type" value="password" />
                </p:inputText>
                <p:message for="senha" id="messageSenha" />

                <p:commandButton value="Efetue Login" action="#{loginBean.efetuaLogin}" />
            </h:panelGrid>
        </p:fieldset>
    </h:form>
</ui:define>
Podemos acessar a página de login, e perceber que já há uma diferença notável na mesma. Podemos até efetuar login para ver se tudo continua funcionando normalmente... Mas não está! Ficamos clicando infinitamente no botão e nada acontece. O que está acontecendo?

Ajax por padrão
O botão do Primefaces já faz ajax por padrão, que não é um comportamento do commandButton padrão. Então, já que ele faz ajax por padrão, precisamos definir o que queremos submeter e quais partes da tela queremos renderizar, o que não estamos fazendo no momento, já que atualmente, quando clicamos no botão para efetuar login, só estamos submetendo o botão e não os campos do formulário. Então vamos mudar esse comportamento, para isso o Primefaces tem dois atributos, o process e o update. No process dizemos quais componentes queremos submeter, no nosso caso queremos submeter todo o formulário, então utilizaremos @form; e no update dizemos o que queremos atualizar na página, e no nosso caso também é o formulário, logo também terá como valor @form. Então o commandButton ficará assim:

<p:commandButton value="Efetue Login" action="#{loginBean.efetuaLogin}" update="@form" process="@form" />
Agora já podemos tentar fazer o login, mas opa... A nossa senha está aparecendo! Isso quer dizer que o passThroughAttribute não está funcionando, portanto vamos removê-lo e consultar novamente o demo do Primefaces para ver se ele nos oferece algum componente para substituí-lo. E no menu Input, vemos que há um Password, que nos oferece diversas opções, vamos utilizar o password com feedback, e olhando como utilizá-lo, vemos que, no nosso caso, basta trocar inputText por password e adicionar o atributo feedback="true":

<p:password id="senha" value="#{loginBean.usuario.senha}" feedback="true" required="true" />
Com isso, tudo volta a funcionar corretamente. Então o primeiro passo está concluído, o formulário de login já está utilizando o Primefaces. Nos próximos capítulos vamos focar nos outros formulários, então até lá!

O que aprendemos? - Objetivo dos Primefaces é estender os componentes JSF - Como importar a lib Primefaces - Primefaces redefine todos os componentes da especificação JSF (por exemplo h:inputText se torna p:inputText) - Usar os primeiros componentes específicos como p:fieldset ou p:password - Comandos do Primefaces usa AJAX por padrão



Sobre o Primefaces podemos dizer que:
A) São componentes que estendem os componentes padrões do JSF adicionando novas funcionalidades, inclusive criando componentes inéditos.

B) Permite aplicar um atrativo estético em nossas páginas sem conhecimento técnico de CSS com pouco esforço através do seu sistema de temas.

C) Abstrai muitas vezes o uso de bibliotecas como jQuery ou até mesmo o uso de JavaScript para tarefas corriqueiras como o uso máscaras e de calendários.

Primefaces, além de oferecer todos os componentes presentes na especificação do JSF, adiciona novos recursos para eles e também componentes inéditos para solucionar problemas do dia a dia. Inclusive o desenvolvedor não precisa ter conhecimento técnico de CSS, pois o sistema de temas da biblioteca permite criar páginas com visuais elegantes com zero de esforço por parte do programador. Ele também abstrai muito código JavaScript com componentes que resolvem problemas corriqueiros que demandam o uso desta linguagem.

Apesar de o Primefaces estender os componentes padrões do JSF, muitas vezes precisamos saber pequenos detalhes, como no caso do <p:commandButton> que realiza requisições AJAX por padrão. Quanto melhor conhecermos os nossos componentes mais produtividade ganhamos no dia a dia. Ainda temos um problema com o nosso p:inputText da senha, alteraremos este componente no próximo exercício.
Observação: Repare que o uso de AJAX não seria necessário nesse caso. O login é realizado, e caso haja sucesso, o usuário é redirecionado para livro.xhtml. Caso dê erro no login, ele também é redirecionado de volta para login.xhtml. Ou seja, sempre há navegação e a tela inteira é reconstruída. O componente p:commandButton usa AJAX por padrão, mas nada impede desabilitar esse comportamento através do atributo ajax="false":

<p:commandButton value="Efetue Login" action="#{loginBean.efetuaLogin}"  ajax="false">
Para sua comparação, segue o h:form da página login.xhtml:

<h:form id="login">
    <p:fieldset legend="Login">
        <h:panelGrid columns="3">

            <p:outputLabel value="Email:" for="email" />
            <p:inputText id="email" value="#{loginBean.usuario.email}" required="true">
                <f:passThroughAttribute name="type" value="email" />
            </p:inputText>
            <p:message for="email" id="messageEmail" />

            <p:outputLabel value="Senha:" for="senha" />
            <p:inputText id="senha" value="#{loginBean.usuario.senha}" required="true">
                <f:passThroughAttribute name="type" value="password" />
            </p:inputText>
            <p:message for="senha" id="messageSenha" />

            <p:commandButton value="Efetue Login" action="#{loginBean.efetuaLogin}" update="@form" process="@form" />
        </h:panelGrid>
    </p:fieldset>
</h:form>



------------------------------------------------------------------------
<h1>Seção 03 - Compoenentes ricos no formulário</h1>


Melhorando a página dos autores
Com a página de login terminada, podemos focar agora na página autor.xhtml. A primeira coisa que temos que fazer, assim como em login.xhtml, e declarar o namespace do Primefaces dentro da tag <html>, juntamente com os outros namespaces:

xmlns:p="http://primefaces.org/ui"
Com o namespace declarado, vamos começar pelo formulário de cadastro de autores. Alguns componentes nós já vimos, como o <p:outputPanel>; o <p:fieldset>, que já tem um atributo legend; <p:outputLabel>; <p:inputText> e o <p:message>. Então vamos começar fazendo as alterações que envolvem esses componentes:

<ui:define name="titulo">
    <p:outputPanel>Novo Autor</p:outputPanel>
</ui:define>

<ui:define name="conteudo">
    <h:form id="autor">
        <p:fieldset legend="Dados do Autor">
            <h:panelGrid columns="3">

                <p:outputLabel value="Nome:" for="nome" />
                <p:inputText id="nome" value="#{autorBean.autor.nome}" required="true">
                    <f:validateLength minimum="5" />
                    <f:ajax event="blur" render="messageNome" />
                </p:inputText>
                <p:message for="nome" id="messageNome" />

                <p:outputLabel value="Email:" for="email" />
                <p:inputText id="email" value="#{autorBean.autor.email}" required="true">
                    <f:passThroughAttribute name="type" value="email" />
                </p:inputText> 

                <p:message for="email" id="messageEmail" />

                <p:commandButton value="Gravar" action="#{autorBean.gravar}" />
            </h:panelGrid>
        </p:fieldset>
    </h:form>
    <!-- formulário de exibição dos autores -->
</ui:define>
Como o commandButton do Primefaces já usa ajax, precisamos definir o que queremos enviar, que no nosso caso é o formulário, e o que queremos atualizar. Além de atualizar o formulário, também precisamos atualizar o formulário que exibe os autores, vamos referenciá-lo através do seu id formTabelaAutors:

<p:commandButton value="Gravar" action="#{autorBean.gravar}" process="@form" update="@form :formTabelaAutors" />
Pronto, mas o que fizemos até agora foi basicamente uma revisão do capítulo anterior, ainda não há nenhuma novidade.

O campo de e-mail
Quem se lembra do treinamento anterior, sabe que utilizamos um passThroughAttribute no campo de e-mail, porque o JSF ainda não dá suporte para todas as tags do HTML5, então podemos definir um atributo do HTML5 que gostaríamos de renderizar.

Mas se olharmos o código fonte da nossa página, vemos um problema. Dizemos que o input deveria ser do tipo email, mas o componente do Primefaces também disse que o input é do tipo text, ou seja, nosso input tem dois tipos, está duplicado. O problema é que o passThroughAttribute é relativamente recente, e o Primefaces já dava suporte a uma forma de passar atributos para o inputText, utilizando o f:attribute:

<p:outputLabel value="Email:" for="email" />
<p:inputText id="email" value="#{autorBean.autor.email}" required="true">
    <f:attribute name="type" value="email" />
</p:inputText>
Agora o type="text" desapareceu, então tudo deveria funcionar corretamente, assim como a validação do e-mail, mas não é isso que acontece. O que está acontecendo é que o p:commandButton utiliza ajax, como já sabemos, então nós não passamos pela validação do navegador.

Para resolver esse problema, nós vamos utilizar um componente que já existe na especificação JSF, o validator. Iremos utilizar um validatorRegex, que aplicará um padrão em cima dos dados que usuário digitar, ou seja, esses dados terão que coincidir com o padrão que nós definirmos. E o padrão que iremos definir é que o nosso e-mail terá qualquer caractere (.) uma ou mais vezes (+), depois terá que vir um arroba (@) juntamente com qualquer caractere (.), novamente uma ou mais vezes (+). Logo, nossa regex será .+@.+:

<p:outputLabel value="Email:" for="email" />
<p:inputText id="email" value="#{autorBean.autor.email}" required="true">
    <f:attribute name="type" value="email" />
    <f:validateRegex pattern=".+@.+" />
</p:inputText>
Além disso, queremos que isso funcione com ajax (assim como o nome) para renderizar uma mensagem e vamos adicionar mais um atributo do HTML5, o placeholder, com o texto "Email do autor":

<p:inputText id="email" value="#{autorBean.autor.email}" required="true">
    <f:attribute name="type" value="email" />
    <f:passThroughAttribute name="placeholder" value="Email do autor" />
    <f:validateRegex pattern=".+@.+" />
    <f:ajax event="blur" render="messageEmail" />
</p:inputText>
Agora, se colocarmos um e-mail errado, ou seja, sem arroba (@) recebemos uma mensagem de erro de validação, mas é uma mensagem bem complicada do usuário entender. Para melhorar isso, podemos definir uma mensagem de validação no inputText, através do atributo validatorMessage:

<p:inputText id="email" value="#{autorBean.autor.email}" required="true" validatorMessage="Email inválido">
    <f:attribute name="type" value="email" />
    <f:passThroughAttribute name="placeholder" value="Email do autor" />
    <f:validateRegex pattern=".+@.+" />
    <f:ajax event="blur" render="messageEmail" />
</p:inputText>



DATA NO SITE DO PRIMEFACES SERVE PARA MOSTRAR OS DADOS
PRIMEFACES UTILIZAR OS ICONES DO JQUERY - SE QUISER ALGUM ÍCONE APENAS PROCURAR NA PÁGINA DE ÍCONES DO JQUERY



Com o formulário de cadastro de autores pronto, podemos focar na lista de exibição dos autores.

Assim como no JSF padrão, também há um [DataTable][1] no Primefaces, mas no nosso caso, não mostramos muitas informações de cada autor cadastrado na aplicação, apenas seu nome e um link para alterá-lo e removê-lo. Por isso não utilizaremos o DataTable, e o Primefaces oferece algo que nos atende melhor, o [DataList][2] (vamos deixar o DataTable para ser utilizado no livro.xtml).

Começaremos com um DataList simples, bem parecido com o exemplo do site. Vamos colocá-lo dentro do formulário de exibição dos autores, exibindo os nomes e e-mails:

<h:form id="formTabelaAutors">
    <p:dataList value="#{autorBean.autores}" var="autor" type="ordered">
        <f:facet name="header">
            Autores
        </f:facet>

        #{autor.nome} - #{autor.email}
    </p:dataList>
    <!-- h:dataTable -->
</h:form>
Mas não queremos números à frente dos autores, isso nós definimos no atributo type. Quando type é ordered, temos os números; unordered temos pontos; e o que queremos é definition, no qual não aparece nada. Vamos também já colocar os links de alteração e remoção no dataList, à frente do nome e e-mail do autor:

<h:form id="formTabelaAutors">
    <p:dataList value="#{autorBean.autores}" var="autor" type="definition">
        <f:facet name="header">
            Autores
        </f:facet>

        <h:commandLink value="altera">
            <f:setPropertyActionListener value="#{autor}" target="#{autorBean.autor}" />
        </h:commandLink>

        <h:commandLink value="remove" action="#{autorBean.remover(autor)}" />

        #{autor.nome} - #{autor.email}
    </p:dataList>
    <!-- h:dataTable -->
</h:form>
Mas ficou algo bem feio... Pegando inspiração mais uma vez na [página][2] do DataList, vemos que podemos utilizar ícones ao invés de texto! Basta remover o atributo value e utilizar o atributo styleClass, dizendo qual ícone queremos usar. No exemplo do site, temos styleClass="ui-icon ui-icon-search", e mais algum estilo (que também iremos utilizar) no atributo style. Mas esse é o ícone de uma lupa, que não cabe muito na nossa aplicação, como vamos descobrir quais ícones podemos utilizar?

Olhando o código fonte da página, vemos que o Primefaces utiliza por debaixo dos panos o jQuery, e é de lá que vêm esses ícones. Então, fazendo uma rápida busca no Google, achamos o [site][3] onde temos todos os ícones disponíveis. Utilizaremos o ui-icon-pencil para representar a alteração e o ui-icon-trash para representar a remoção do autor:

<h:form id="formTabelaAutors">
    <p:dataList value="#{autorBean.autores}" var="autor" type="definition">
        <f:facet name="header">
            Autores
        </f:facet>

        <h:commandLink styleClass="ui-icon ui-icon-pencil" style="float:left;margin-right:10px">
            <f:setPropertyActionListener value="#{autor}" target="#{autorBean.autor}" />
        </h:commandLink>

        <h:commandLink styleClass="ui-icon ui-icon-trash" style="float:left;margin-right:10px" 
            action="#{autorBean.remover(autor)}" />

        #{autor.nome} - #{autor.email}
    </p:dataList>
    <!-- h:dataTable -->
</h:form>
Ótimo, agora já temos um layout mais apresentável. Mas quando alteramos ou removemos um autor, repara que a página dá uma "piscada", isso porque o nosso commandLink não está utilizando ajax, pois não estamos utilizando o commandLink do Primefaces. Vamos alterar os commandLinks para serem os do Primefaces. Mas lembrando que agora teremos que definir o que queremos atualizar e submeter na página. Nos dois casos queremos submeter somente os dados do próprio link, então utilizaríamos process="@this", mas esse já é o padrão do commandLink do Primefaces, então não precisamos explicitar isso, precisamos explicitar apenas o update. Na hora de remover, precisamos atualizar o formulário, logo o valor será @form e na hora de alterar, temos que atualizar o formulário de cadastro de autores, que tem como id autor, logo o valor de update será :autor:

<h:form id="formTabelaAutors">
    <p:dataList value="#{autorBean.autores}" var="autor" type="definition">
        <f:facet name="header">
            Autores
        </f:facet>

        <p:commandLink styleClass="ui-icon ui-icon-pencil" style="float:left;margin-right:10px" update=":autor">
            <f:setPropertyActionListener value="#{autor}" target="#{autorBean.autor}" />
        </p:commandLink>

        <p:commandLink styleClass="ui-icon ui-icon-trash" style="float:left;margin-right:10px" 
            action="#{autorBean.remover(autor)}" update="@form" />

        #{autor.nome} - #{autor.email}
    </p:dataList>
    <!-- h:dataTable -->
</h:form>
Com o nosso dataList pronto, podemos remover a tabela antiga, tornando a tela mais agradável.



O Primefaces também oferece ícones para utilizarmos na nossa aplicação, você pode vê-los aqui. Vamos utilizá-los na nossa aplicação, no lugar dos ícones do jQuery.

Primeiramente, a ideia é copiar do exemplo e colar na nossa página, para definir o ícone de alteração, utilizamos icon="fa fa-fw fa-edit" e o de remoção é icon="fa fa-fw fa-remove":

<h:form id="formTabelaAutors">
    <p:dataList value="#{autorBean.autores}" var="autor" type="definition">
        <f:facet name="header">
            Autores
        </f:facet>

        <p:commandLink icon="fa fa-fw fa-edit" update=":autor">
            <f:setPropertyActionListener value="#{autor}" target="#{autorBean.autor}" />
        </p:commandLink>

        <p:commandLink icon="fa fa-fw fa-remove" action="#{autorBean.remover(autor)}" 
            update="@form" />

        #{autor.nome} - #{autor.email}
    </p:dataList>
</h:form>
Mas não funciona... Se consultarmos a documentação dos ícones, vemos que precisamos definir um context-param no web.xml. Então vamos fazer isso:

<context-param>
    <param-name>primefaces.FONT_AWESOME</param-name>
    <param-value>true</param-value>
</context-param>
E também precisamos alterar a página, pois os ícones não funcionam com commandLink, somente com commandButton:

<h:form id="formTabelaAutors">
    <p:dataList value="#{autorBean.autores}" var="autor" type="definition">
        <f:facet name="header">
            Autores
        </f:facet>

        <p:commandButton icon="fa fa-fw fa-edit" update=":autor">
            <f:setPropertyActionListener value="#{autor}" target="#{autorBean.autor}" />
        </p:commandButton>

        <p:commandButton icon="fa fa-fw fa-remove" action="#{autorBean.remover(autor)}" 
            update="@form" />

        #{autor.nome} - #{autor.email}
    </p:dataList>
</h:form>
É uma alternativa para os ícones do jQuery, você pode utilizar o de sua preferência.

E com isso concluímos a página autor.xhtml, a partir dos próximos capítulos iremos alterar a página livro.xhtml.

O que aprendemos?

podemos usar f:attribute invés de f:passThroughAttribute
como aplicar um REGEX pelo componente f:validateRegex
Primefaces usa jQuery por baixo dos panos
Como utilizar o p:dataList
Podemos usar o icones do jQuery ou FontAwesome


Vimos no video que o Primefaces introduziu outros icones que se chamam de FontAwesome:
http://www.primefaces.org/showcase/ui/misc/fa.xhtml

Vamos aplicá-los?

1 - Dentro da p:dataList troque todos os p:commandLink com p:commandButton.

2 - Nos comandos apague os dois atributos relacionados com CSS. Remove style e styleClass dos comandos.

3 - Ainda nos comandos use o atributo icon para definir o ícone do FontAwesome. Para a funcionalidade remover use o icon="fa fa-fw fa-remove", e para alterar use icon="fa fa-fw fa-edit".

4 - Antes de testar não podemos esquecer de adicionar um context-param no web.xml:

<context-param>
    <param-name>primefaces.FONT_AWESOME</param-name>
    <param-value>true</param-value>
</context-param>
5 - Agora sim, reinicie o Tomcat para verificar os novos ícones na página de autores.


--------------------------------------------------------------------
<h1>Seção 04 - Mais usabilidade na página de livros</h1>

Primefaces na página de livros
Começaremos a alterar a página livro.xhtml, mas essa tarefa será dividida em dois capítulos. Neste capítulo, focaremos no formulário de cadastro de livros e no próximos alteraremos a tabela de exibição dos livros cadastrados.

Antes de utilizar o Primefaces na página, não podemos nos esquecer de declarar o seu namespace dentro da tag <html>:

xmlns:p="http://primefaces.org/ui"
E vamos mexer no que já foi visto no treinamento, adicionar o componente <h:outputPanel> e alterar o <fieldset>, <h:outputLabel>, <h:inputText> e o <h:messages>. Você pode dar um simples CTRL+F e substituir esses componentes pelos os do Primefaces. Vamos trocar o panelGrid também, para utilizar o do Primefaces:

<ui:define name="titulo">
    <p:outputPanel>Novo Livro</p:outputPanel>
</ui:define>

<ui:define name="conteudo">
    <h:form>

        <p:messages id="messages" />

        <p:fieldset legend="Dados do Livro">
            <p:panelGrid columns="2">

                <p:outputLabel value="Titulo:" for="titulo" />
                <p:inputText id="titulo" value="#{livroBean.livro.titulo}"
                    required="true" requiredMessage="Título obrigatório"
                    validatorMessage="Título não pode ser superior a 40">
                    <f:validateLength maximum="40" />
                    <f:ajax event="blur" render="messages" />
                </p:inputText>

                <p:outputLabel value="ISBN:" for="isbn" />
                <p:inputText id="isbn" value="#{livroBean.livro.isbn}"
                    validator="#{livroBean.comecaComDigitoUm}" />

                <p:outputLabel value="Preço:" for="preco" />
                <p:inputText id="preco" value="#{livroBean.livro.preco}" />

                <p:outputLabel value="Data de Lançamento:" for="dataLancamento" />
                <p:inputText id="dataLancamento"
                    value="#{livroBean.livro.dataLancamento.time}">
                    <f:convertDateTime pattern="dd/MM/yyyy"
                        timeZone="America/Sao_Paulo" />
                </p:inputText>

            </p:panelGrid>

        </p:fieldset>

        <p:fieldset legend="Dados do Autor">

            <p:outputLabel value="Selecione Autor:" for="autor" />
            <h:selectOneMenu value="#{livroBean.autorId}" id="autor">
                <f:selectItems value="#{livroBean.autores}" var="autor"
                    itemLabel="#{autor.nome}" itemValue="#{autor.id}" />
            </h:selectOneMenu>
            <h:commandButton value="Gravar Autor"
                action="#{livroBean.gravarAutor}">
                <f:ajax execute="autor" render="tabelaAutores" />
            </h:commandButton>

            <br />

            <h:commandLink value="Cadastrar novo autor"
                action="#{livroBean.formAutor}" immediate="true" />

            <h:dataTable value="#{livroBean.autoresDoLivro}" var="autor"
                id="tabelaAutores">
                <h:column>
                    <h:outputText value="#{autor.nome}" />
                </h:column>
                <h:column>
                    <h:commandLink value="X" action="#{livroBean.removerAutorDoLivro(autor)}"/>
                </h:column>
            </h:dataTable>
        </p:fieldset>
        <h:commandButton value="Gravar" action="#{livroBean.gravar}">
            <f:ajax execute="@form" render="@form :formTabelaLivros:tabelaLivros" />
        </h:commandButton>
    </h:form>
    <!-- tabela de exibição dos autores -->
</ui:define>
Máscara de input
Agora, temos três campos que precisam de um tratamento especial, o ISBN, que tem um formato específico, logo poderíamos colocar uma máscara para facilitar o usuário; o Preço, pois não podem ser digitadas letras nesse campo e a Data de Lançamento, pois queremos colocar um calendário para auxiliar o usuário.

Olhando o [ShowCase de Inputs][1] do Primefaces, vemos que nele há um [InputMask][2], um [InputNumber][3] e um [Calendar][4], que atendem aos nossos campos. Vamos começar pelo InputMask, que se chama <p:inputMask> e tem um atributo chamado mask, no qual definimos a máscara que queremos, no nosso caso será 999-9-99-999999-9, que é o padrão do ISBN, onde 9 representa um número qualquer. Então o input do ISBN ficará assim:

<p:outputLabel value="ISBN:" for="isbn" />
<p:inputMask id="isbn" value="#{livroBean.livro.isbn}"
    validator="#{livroBean.comecaComDigitoUm}" mask="999-9-99-999999-9" />
Repare que agora o input do ISBN só aceita números e num formato específico. Agora veremos o preço, o InputNumber, mas há um problema, ele só existe a partir da versão 5.3.4 e a última versão disponibilizada para a comunidade (no momento da criação deste curso) é a versão 5.3, então infelizmente não podemos utilizá-lo (caso a versão 5.4 já esteja disponibilizada para download, você pode baixá-la e testar esse componente).

Então pularemos para o Calendar, veja que há vários modos de como utilizá-lo, vamos utilizar o Popup. Basta trocar p:inputText por p:calendar e adicionar o atributo mask="true", pois também queremos uma máscara nesse campo:

<p:outputLabel value="Data de Lançamento:" for="dataLancamento" />
<p:calendar id="dataLancamento" value="#{livroBean.livro.dataLancamento.time}" 
    pattern="dd/MM/yyyy" timeZone="America/Sao_Paulo" mask="true" />
Com isso, os dados do livros estão terminados, para completar faltam os dados do autor.


Podemos começar utilizando o selectOneMenu do Primefaces, basta trocar o prefixo h por p e depois alterar o botão de gravar autor, repare que antes estávamos fazendo ajax através do f:ajax, mas como o botão do Primefaces já faz ajax por padrão, podemos remover esse componente, mas aproveitando seus atributos, o execute e o render do f:ajax serão o process e o update do p:commandButton, respectivamente. Mas o botão também tem que submeter seus dados, então também adicionaremos @this ao process:

<p:fieldset legend="Dados do Autor">

    <p:outputLabel value="Selecione Autor:" for="autor" />
    <p:selectOneMenu value="#{livroBean.autorId}" id="autor">
        <f:selectItems value="#{livroBean.autores}" var="autor"
            itemLabel="#{autor.nome}" itemValue="#{autor.id}" />
    </p:selectOneMenu>
    <p:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}"
        process="@this autor" update="tabelaAutores" />
    <!-- restante do código -->    
</p:fieldset>
Vamos remover o <br />, pois agora queremos que o link para cadastrar novos autores fique ao lado do botão de gravar, além de mudá-lo para usar o Primefaces também. Só que como o link do Primefaces também utiliza ajax, vamos falar que queremos atualizar a página inteira, já que queremos mudar de página, logo o valor de update será @all:

<p:fieldset legend="Dados do Autor">

    <p:outputLabel value="Selecione Autor:" for="autor" />
    <p:selectOneMenu value="#{livroBean.autorId}" id="autor">
        <f:selectItems value="#{livroBean.autores}" var="autor"
            itemLabel="#{autor.nome}" itemValue="#{autor.id}" />
    </p:selectOneMenu>
    <p:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}"
        process="@this autor" update="tabelaAutores" />

    <p:commandLink value="ou cadastrar novo autor"
        action="#{livroBean.formAutor}" immediate="true" update="@all" />
    <!-- restante do código -->    
</p:fieldset>
Mas repare que os componentes não estão alinhados, isso porque não temos um container para organizá-los. Então vamos utilizar o p:panelGrid para fazer essa organização, como temos 4 componentes, queremos que o panelGrid tenha 4 colunas:

<p:fieldset legend="Dados do Autor">

    <p:panelGrid columns="4">
        <p:outputLabel value="Selecione Autor:" for="autor" />
        <p:selectOneMenu value="#{livroBean.autorId}" id="autor">
            <f:selectItems value="#{livroBean.autores}" var="autor"
                itemLabel="#{autor.nome}" itemValue="#{autor.id}" />
        </p:selectOneMenu>
        <p:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}"
            process="@this autor" update="tabelaAutores" />

        <p:commandLink value="ou cadastrar novo autor"
            action="#{livroBean.formAutor}" immediate="true" update="@all" />
    </p:panelGrid>
    <!-- restante do código -->    
</p:fieldset>
Tabela de autores simples
Agora só falta a tabela que mostra os autores do livro. Nessa tabela iremos fazer somente o mínimo, trocando o prefixo h por p dos componentes dataTable e column. E adicionar o atributo emptyMessage do dataTable, essa mensagem aparecerá quando não houver nenhum dado na tabela, ou seja, nenhum autor cadastrado:

<p:dataTable value="#{livroBean.autoresDoLivro}" var="autor" 
    id="tabelaAutores" emptyMessage="Nenhum autor">
    <p:column>
        <h:outputText value="#{autor.nome}" />
    </p:column>
    <p:column>
        <h:commandLink value="X" action="#{livroBean.removerAutorDoLivro(autor)}"  />
    </p:column>
</p:dataTable>
Mas tente remover um autor, repare que ele faz a validação do formulário, isso acontece porque o link de remoção é um h:commandLink, e ele submete o formulário inteiro, mas queremos submeter apenas os dados do link, então vamos alterá-lo para ser o commandLink do Primefaces e configurar o atributo process como @this. E o que será atualizado? A tabela toda, então update será o id da tabela, tabelaAutores:

<p:dataTable value="#{livroBean.autoresDoLivro}" var="autor" 
    id="tabelaAutores" emptyMessage="Nenhum autor">
    <p:column>
        <h:outputText value="#{autor.nome}" />
    </p:column>
    <p:column>
        <p:commandLink value="X" action="#{livroBean.removerAutorDoLivro(autor)}" 
            update="tabelaAutores" process="@this" />
    </p:column>
</p:dataTable>
Para finalizar este capítulo, falta atualizar o botão que realmente grava o livro no banco de dados, para ser um componente do Primefaces. Aqui será a mesma coisa que fizemos anteriormente, os atributos execute e render do f:ajax serão o process e o update do p:commandButton:

<p:commandButton value="Gravar" action="#{livroBean.gravar}" 
    process="@form" update="@form :formTabelaLivros:tabelaLivros" />
Ótimo, o formulário de cadastro de livros está pronto. No próximos capítulo iremos alterar a tabela de exibição dos livros cadastrados, finalizando assim a página livro.xhtml.



Vemos que o campos ISBN tem um padrão específico a ser seguido, e não queremos que o usuário coloque outra coisa. No PrimeFaces temos como fazer isso com algum componente. Qual seria? E qual padrão deve ser seguido? Exemplo:
ISBN: 123-4-56-789101-1

Você pode procurar esse componente aqui.

O componente é o <p:inputMask> e definimos nossa máscara através do atributo mask="". Fazemos isso com dígitos colocando o "9". No nosso caso, tem que seguir o padrão: 999-9-99-999999-9.
Repare que agora o input do ISBN só aceita números e num formato específico.

Queremos que o Calendar também siga um padrão, e que seja mais fácil para o usuário inserir o dado.
Olhando no site do PrimeFaces, qual componente usar?

O componente que usamos no vídeo foi o:
<p:calendar id="data" pattern="dd/MM/yyyy" mask="true">
Porém, não era a única resposta correta! Entrando no site do PrimeFaces novamente, podemos ver que temos vários exemplos, e um deles é utilizando um botão ao lado do input, em vez de um popup:

<p:calendar id="data" pattern="dd/MM/yyyy" mask="true" mode="inline">
Ou usando um botão explicito para mostrar o calendário:

<p:calendar id="data" pattern="dd/MM/yyyy" mask="true" showOn="button">
Não se prenda apenas ao vídeo, você pode olhar o showcase do PrimeFaces, e observar se alguma outra opção encaixa melhor no seu projeto!


Vamos atacar os dados do autor dentro do formulário do livro. Primeiro troque o selectOneMenu para o do PrimeFaces. Precisamos mudar o botão também, além dos outputLabel.
Então fica assim:

<p:fieldset legend="Dados do Autor">
    <p:outputLabel value="Selecione Autor:" for="autor" />
    <p:selectOneMenu value="#{livroBean.autorId}" id="autor">
        <f:selectItems value="#{livroBean.autores}" var="autor"
            itemLabel="#{autor.nome}" itemValue="#{autor.id}" />
    </p:selectOneMenu>
    <p:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}">
        <f:ajax execute="autor" render="tabelaAutores">
    </p:commandButton>
    <!-- restante do código -->    
</p:fieldset>
Porém, lembre que o PrimeFaces já faz Ajax por padrão, será que precisamos de <f:ajax>?

Como podemos modificar para que fique apenas com o componente do PrimeFaces?
O correto é:
<p:commandButton value="Gravar Autor" action="#{livroBean.gravarAutor}"
        process="@this autor" update="tabelaAutores" />
Ao trocarmos pelo botão do PrimeFaces que ja realiza nosso Ajax, precisamos apenas substituir execute e render por process e update.

Não podemos esquecer também que queremos enviar a action do botão Gravar Autor, e por isso, precisamos adicionar o @this para o que será processado.


---------------------------------------------------------------------------------
<h1>Seção 05 - Dados tabulares com Primefaces</h1>

Como foi falado no capítulo anterior, neste capítulo iremos finalizar a página livro.xhtml, alterando a tabela que exibe os livros cadastrados. Nesse momento, o dataTable está assim:

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

    <h:column>
        <f:facet name="header">Alterar</f:facet>
        <h:commandLink value="altera" action="#{livroBean.carregar(livro)}"/>
    </h:column>                    

    <h:column>
        <f:facet name="header">Remover</f:facet>
        <h:commandLink value="remove" action="#{livroBean.remover(livro)}"/>
    </h:column>    
</h:dataTable>
Podemos começar com o que já vimos no capítulo anterior, trocando o prefixo dos componentes dataTable e column, para utilizar o Primefaces. Podemos também adicionar um f:facet para definir o cabeçalho da tabela, e remover os f:facets das colunas, já que o p:column já tem o atributo headerText (você pode fazer de qualquer um dos modos, ou utiliza o headerText, ou f:facet, ambos funcionam):

<p:dataTable value="#{livroBean.livros}" var="livro" id="tabelaLivros">
    <f:facet name="header">Livros</f:facet>
    <p:column headerText="Título">
        <h:outputText value="#{livro.titulo}" />
    </p:column>
    <p:column headerText="ISBN">
        <h:outputText value="#{livro.isbn}" />
    </p:column>
    <p:column headerText="Preço">
        <h:outputText value="#{livro.preco}">
            <f:convertNumber type="currency" pattern="R$ #0.00"
                currencySymbol="R$" locale="pt_BR" />
        </h:outputText>
    </p:column>
    <p:column headerText="Data">
        <h:outputText value="#{livro.dataLancamento.time}">
            <f:convertDateTime pattern="dd/MM/yyyy"
                timeZone="America/Sao_Paulo" />
        </h:outputText>
    </p:column>

    <p:column headerText="Alterar">
        <h:commandLink value="altera" action="#{livroBean.carregar(livro)}"/>
    </p:column>                    

    <p:column headerText="Remover">
        <h:commandLink value="remove" action="#{livroBean.remover(livro)}"/>
    </p:column>    
</p:dataTable>
Ordenando os livros
Com isso, o visual da tabela já está praticamente pronto, o que podemos fazer agora é adicionar novos recursos. Na [página][1] do dataTable há os recursos que ele oferece. O primeiro recurso que utilizaremos é o [Sort][2], que serve para ordenar as coluna baseado nos valores que quisermos. Vamos fazer isso para todas as tabelas com os valores do livro, basta adicionar o atributo sortBy na coluna, com o valor que queremos ordenar:

<p:dataTable value="#{livroBean.livros}" var="livro" id="tabelaLivros">
    <f:facet name="header">Livros</f:facet>
    <p:column headerText="Título" sortBy="#{livro.titulo}">
        <h:outputText value="#{livro.titulo}" />
    </p:column>
    <p:column headerText="ISBN" sortBy="#{livro.isbn}">
        <h:outputText value="#{livro.isbn}" />
    </p:column>
    <p:column headerText="Preço" sortBy="#{livro.preco}">
        <h:outputText value="#{livro.preco}">
            <f:convertNumber type="currency" pattern="R$ #0.00"
                currencySymbol="R$" locale="pt_BR" />
        </h:outputText>
    </p:column>
    <p:column headerText="Data" sortBy="#{livro.dataLancamento.time}">
        <h:outputText value="#{livro.dataLancamento.time}">
            <f:convertDateTime pattern="dd/MM/yyyy"
                timeZone="America/Sao_Paulo" />
        </h:outputText>
    </p:column>

    <p:column headerText="Alterar">
        <h:commandLink value="altera" action="#{livroBean.carregar(livro)}"/>
    </p:column>                    

    <p:column headerText="Remover">
        <h:commandLink value="remove" action="#{livroBean.remover(livro)}"/>
    </p:column>
</p:dataTable>
Ajustando a camada de persistência
Agora podemos testar... Mas não funciona! O problema está na nossa aplicação, quando clicamos na coluna que queremos ordenar, o Hibernate seleciona os dados no banco de dados, é assim toda vez que clicamos em alguma coluna, por isso o Primefaces não consegue ordená-los. Precisamos fazer um cache dos dados da nossa aplicação, para aí o Primefaces conseguir ordená-los.

Precisamos alterar o método getLivros, da classe LivroBean. Esse método que é chamado cada vez que clicamos em alguma coluna, e ele lista todos os livros chamando o método listaTodos do DAO. Isso não faz sentido, não precisamos carregar os livros toda hora, só precisamos listar os livros quando a sessão for iniciada ou quando adicionamos um livro, de resto podemos trabalhar com eles em memória.

Para resolver isso, vamos alterar a classe LivroBean, primeiro criando na classe um atributo livros. Dentro do método getLivros, se esse atributo for nulo, ou seja, se ainda não tivermos carregado os livros, nós chamamos o método listaTodos do DAO para carregá-los; se o atributo não for nulo, significa que já carregamos os livros, então não precisamos fazer nada:

private List<Livro> livros;

public List<Livro> getLivros() {

    DAO<Livro> dao = new DAO<Livro>(Livro.class);

    if(this.livros == null) {
        this.livros = dao.listaTodos();            
    }

    return livros;
}
Mas também precisamos atualizar a lista de livros se adicionarmos algum novo, caso contrário ele será listado somente se recarregarmos a página. Então, no método gravar, vamos listar os livros sempre que um for adicionado:

public void gravar() {
    System.out.println("Gravando livro " + this.livro.getTitulo());

    if (livro.getAutores().isEmpty()) {
        FacesContext.getCurrentInstance().addMessage("autor",
                new FacesMessage("Livro deve ter pelo menos um Autor."));
        return;
    }

    DAO<Livro> dao = new DAO<Livro>(Livro.class);

    if(this.livro.getId() == null) {
        dao.adiciona(this.livro);

        // Novo livro adicionado, listamos todos os livros novamente
        this.livros = dao.listaTodos();
    } else {
        dao.atualiza(this.livro);
    }

    this.livro = new Livro();
}
Reiniciando o Tomcat e testando a ordenação, vemos que agora tudo funciona corretamente! Podemos voltar a testar novos recursos do dataTable do Primefaces.


O próximo recurso que iremos utilizar é o Paginator, pois teremos muitos livros e não podemos mostrar todos esses livros numa página só, por isso podemos paginar a tabela. E para fazer isso é bem simples, basta adicionar a tabela o atributo paginator="true" e o atributo rows com o número de linhas que queremos exibir por página:

<p:dataTable value="#{livroBean.livros}" var="livro" id="tabelaLivros" 
    paginator="true" rows="5">
        <!-- conteúdo da tabela -->
</p:dataTable>
Filtrando os elementos da tabela
Por último, aplicaremos o Filter para o usuário poder buscar o título do livro que ele quiser. Para isso, temos que adicionar o atributo filterBy na coluna que queremos (Livros), dizendo por qual valor queremos filtrar, mas não só isso, precisamos também definir um filterMatchMode. Nesse atributo precisamos definir que, conforme o usuário vai digitando o que procura, onde esse valor tem que aparecer, se o título deve começar ou terminar com o valor digitado, se deve conter o valor ou esse valor tem que ser exato. É isso que definimos nesse atributo, e no nosso caso queremos que o título comece com o que o usuário for digitando, logo seu valor será startsWith:

<f:facet name="header">Livros</f:facet>
<p:column headerText="Título" sortBy="#{livro.titulo}" 
    filterBy="#{livro.titulo}" filterMatchMode="startsWith">
    <h:outputText value="#{livro.titulo}" />
</p:column>
Perfeito, agora conseguimos filtrar nossos livros, e com isso terminamos de "primefacear" a página livro.xhtml, mas ainda podemos fazer mais melhorias, fiquem à vontade para testá-las.

No próximo capítulo melhoraremos a navegação da nossa aplicação, criando um menu para essa tarefa, até lá!

Se testarmos a página inteira, podemos achar outro problema. Ao adicionar um novo livro, ele não entra no dataTable. Isso acontece pois cacheamos a lista de livros no exercício anterior, e agora não estamos mais atualizando ela. Isso significa que gravamos o livro no banco mas a lista não recebeu o livro. Podemos consertar isso adicionando algum código no método gravar().
O que poderiamos adicionar? Dica, essa é a função gravar:

public void gravar() {
    System.out.println("Gravando livro " + this.livro.getTitulo());

    if (livro.getAutores().isEmpty()) {
        FacesContext.getCurrentInstance().addMessage("autor",
                new FacesMessage("Livro deve ter pelo menos um Autor."));
        return;
    }

    DAO<Livro> dao = new DAO<Livro>(Livro.class);

    if(this.livro.getId() == null) {
        dao.adiciona(this.livro);
    } else {
        dao.atualiza(this.livro);
    }

    this.livro = new Livro();
}
Dica 2: Olhe o exercício anterior!

O correto fica:
public void gravar() {
    System.out.println("Gravando livro " + this.livro.getTitulo());

    if (livro.getAutores().isEmpty()) {
        FacesContext.getCurrentInstance().addMessage("autor",
                new FacesMessage("Livro deve ter pelo menos um Autor."));
        return;
    }

    DAO<Livro> dao = new DAO<Livro>(Livro.class);

    if(this.livro.getId() == null) {
        dao.adiciona(this.livro);

        // Novo livro adicionado, listamos todos os livros novamente
        this.livros = dao.listaTodos();
    } else {
        dao.atualiza(this.livro);
    }

    this.livro = new Livro();
}
Faça essa alteração no seu código!

A cada vez que estivermos adicionando um livro no banco, atualizamos o atributo livros do nosso objeto e assim, o dataTable será atualizado também com a nova inserção.


O próximo recurso que iremos utilizar é o Paginator, pois teremos muitos livros e não podemos mostrar todos esses livros numa página só, por isso podemos paginar a tabela. Isso é uma qualidade na programação orientada a componentes, pois não precisamos nos preocupar com nada além de utilizá-lo.
Como podemos então, adicionar páginas para a tabela de livros?
Adicionar páginas é bem simples, basta a tabela ter o atributo paginator="true" e o atributo rows="5" com o número de linhas que queremos exibir por página. O correto então fica:
<p:dataTable value="#{livroBean.livros}" var="livro" id="tabelaLivros" 
    paginator="true" rows="5">
        <!-- conteúdo da tabela -->
</p:dataTable>
Agora sabendo da paginação com Primefaces, implemente essa alteração na sua tabela!



Opcional: Filtrando outras colunas

Agora vamos filtrar também as colunas isbn, preço e data:
1- Adicione o filtro na coluna do ISBN, o MatchMode será contains:

<p:column headerText="ISBN" sortBy="#{livro.isbn}" filterBy="#{livro.isbn}" filterMatchMode="contains">
    <h:outputText value="#{livro.isbn}" />
</p:column>
2 - Adicione o mesmo filtro na coluna do data:

<p:column headerText="Data" sortBy="#{livro.dataLancamento.time}" filterBy="#{livro.dataLancamento.time}" filterMatchMode="contains">
    <h:outputText value="#{livro.dataLancamento.time}">
        <f:convertDateTime pattern="dd/MM/yyyy" timeZone="America/Sao_Paulo" />
    </h:outputText>
</p:column>
3 - Agora vamos atacar o preço. Adicione o atributo filterBy="#{livro.preco}". Nessa coluna não podemos usar o matchmode pois os valores dessa coluna são numéricos. No entanto, podemos plugar um método que decide se um elemento deve ser filtrado ou não! Use o atributo filterFunction no componente p:column do preço, como listado em baixo:

<p:column headerText="Preço" sortBy="#{livro.preco}"  filterBy="#{livro.preco}" filterFunction="#{livroBean.precoEhMenor}" >
    <h:outputText value="#{livro.preco}">
        <f:convertNumber type="currency" pattern="R$ #0.00" currencySymbol="R$" locale="pt_BR" />
    </h:outputText>
</p:column>
4 - Repare que o filterFunction faz um binding com um método no LivroBean. Crie esse método na classe LivroBean com a assinatura seguinte:

public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale) { //java.util.Locale

}
O primeiro parâmetro é o valor da coluna, o segundo é o filtro, o terceiro define a locale (por exemplo pt, en ou es). Agora precisamos devolver true se o valor passa pelo filtro.

5 - Segue a implementação do método precoEhMenor que parece ser complexo mas não é!

public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale) { // java.util.Locale

        //tirando espaços do filtro
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

            // comparando os valores, compareTo devolve um valor negativo se o value é menor do que o filtro
            return precoColuna.compareTo(precoDigitado) < 0;

        } catch (NumberFormatException e) {

            // usuario nao digitou um numero
            return false;
        }
}
Passe pelos comentários para entender o código! Depois tente filtrar pelo preço. Devem aparecer todos os livro que possuem um preço menor do que o filtro.


Opcional: Testando p:ring

Estamos aprendendo Primefaces, certo? Então nada mais justo de ver um componente extra, mesmo não fazendo parte da aplicação!
Essa vez gostaria de apresentar o componente p:ring:

http://www.primefaces.org/showcase/ui/data/ring.xhtml

É um componente que mostra os dados em uma roda, pode ser útil quando precisa visualizar uma pequena quantidade de dados com um visual diferenciado. Vamos lá:

1 - Crie uma nova página ring.xhtml (pode copiar, por exemplo, a página login.xhtml)

2 - Na nova página, dentro da tag ui:define name="conteudo" crie um h:form

<ui:define name="conteudo">
    <h:form id="ringForm">

    </h:form>
</ui:define>
3 - Dentro do h:form vamos definir o p:ring aproveitando os livros do LivroBean:

<p:ring id="basic" value="#{livroBean.livros}" var="livro">
    <p:outputPanel style="text-align: center;" layout="block">
        #{livro.titulo}
    </p:outputPanel>
</p:ring>
4 - Salve tudo e chama a página pelo navegador:

http://localhost:8080/livraria/ring.xhtml

VER OPINIÃO DO INSTRUTOR
Opinião do Instrutor

Conseguiu visualizar os livros?
Vamos criar mais um extra e usar um dialogo para mostrar os detalhes do livro (como apresentado no demo do Primefaces). Para isso:

1 - Coloque o diálogo abaixo logo após do componente p:ring, mas dentro do h:form:

<p:dialog id="livroDialog" header="Informações do Livro" widgetVar="livroDialog" modal="true" showEffect="fade" hideEffect="fade" resizable="false" width="300">
     <p:outputPanel id="livroDetail" style="text-align:center" layout="block">
        <p:panelGrid  columns="2" rendered="#{not empty livroBean.livro}" columnClasses="value"  style="width:100%;">
            <f:facet name="header">
                #{livroBean.livro.titulo} 
            </f:facet>

            <h:outputText value="Preço" />
            <h:outputText value="#{livroBean.livro.preco}" />

            <h:outputText value="ISBN" />
            <h:outputText value="#{livroBean.livro.isbn}" />
        </p:panelGrid>
    </p:outputPanel>
</p:dialog>
2 - E no componente p:ring, dentro do componente p:outputPanel logo após da EL (#{livro.titulo}) adicione a chamada do diálogo:

<br />
<p:commandButton update=":ringForm:livroDetail" icon="ui-icon-search" title="View" oncomplete="PF('livroDialog').show();">
    <f:setPropertyActionListener value="#{livro}" target="#{livroBean.livro}" />
</p:commandButton>
3 - Salve e teste novamente!

Opcional: Buscando dados sob demanda com LazyDataModel do Primefaces

Até então nossa paginação está sendo feita apenas em memória já que a lista de livros ainda está sendo carregada por completo. O ideal é que, para melhorar a performance, realizemos a paginação também no banco de dados. Assim carregamos os produtos somente que iremos ver naquele momento.
Para fazer isso o Primefaces possui um componente chamado LazyDataModel. Que é responsável por buscar uma quantidade (definida pelo rows) de instâncias de um modelo (no nosso caso Livro). Nossa missão agora é alterar um pouco nosso código e fazer uso desse magnífico componente :). Vamos lá!

1) Crie uma classe no pacote br.com.caelum.livraria.modelo com o nome LivroDataModel que herde da classe LazyDataModel. A classe deve ser tipada com generics com o tipo do nosso modelo (o Livro):

public class LivroDataModel extends LazyDataModel<Livro> {

}
2) Precisamos dizer ao LazyDataModel qual é o valor máximo de registros que possuímos de livros. Vamos fazer isso em seu construtor chamando o método setRowCount() passando a quantidade. Podemos usar o método contaTodos do nosso DAO genérico.

public LivroDataModel() {
    super.setRowCount(???);
}
Vamos precisar buscar a quantidade de livros que possuímos no banco e para isso vamos precisar instanciar nosso DAO genérico dentro da classe LivroDataModel. Além disso, crie um método quantidadeDeElementos dentro da classe DAO com a seguinte implementação:

// classe DAO
public int quantidadeDeElementos() {
        EntityManager em = new JPAUtil().getEntityManager();
        long result = (Long) em.createQuery("select count(n) from " + classe.getSimpleName() + " n")
                .getSingleResult();
        em.close();

        return (int) result;
    }
O construtor da classe LivroDataModel ficará:

public LivroDataModel() {
    super.setRowCount(dao.quantidadeDeElementos());
}
3) Precisamos também sobreescrever o método load que será responsável por buscar uma quantidade de livros no banco de dados e devolvê-la ao dataTable:

@Override
public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {

}
Para buscar a lista paginada, a JPA nos ajuda através dos métodos setFirstResult e setMaxResult da interface Query. Porém já criamos um método que faz busca paginada para você :). Ele se chama listaTodosPaginada e está dentro do DAO genérico:

@Override
public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
    return dao.listaTodosPaginada(inicio, quantidade);
}
4) Crie um atributo do tipo LivroDataModel dentro da classe LivroBean. (Não esqueça de criar o getter para ele)

public class LivroBean implements Serializable {
    // outros atributos

    private LivroDataModel livroDataModel = new LivroDataModel();

    // outros atributos
    // getters setters
}
5) Por último, altere o valor do atributo value do componente p:dataModel para realizar o binding com o LivroDataModel. Além disso, coloque lazy=true:

<h:form id="formTabelaLivros">
    <p:dataTable value="#{livroBean.livroDataModel}" var="livro" id="tabelaLivros" paginator="true" rows="5" lazy="true">
Reinicie o servidor, a paginação deve continuar funcionando. Porém dessa forma, buscamos os dados no banco conforme precisamos deles :) Mas ainda temos um problema: precisamos alimentar o filtro, ou seja, buscar os livros pelo título. Como fazer?

Podemos receber os valores que foram entrados pelo usuário nos filtros através do último parâmetro do método load da classe LivroDataModel:

@Override
public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
    String titulo = (String) filtros.get("titulo");        
    return dao.listaTodosPaginada(inicio, quantidade);
}
Vamos alterar um pouco o método listaTodosPaginada para aplicar o filtro na busca:

// classe DAO
public List<T> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
    EntityManager em = new JPAUtil().getEntityManager();
    CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
    Root<T> root = query.from(classe);

    if(valor != null)
        query = query.where(em.getCriteriaBuilder().like(root.<String>get(coluna), valor + "%"));

    List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

    em.close();
    return lista;
}
Estamos usamos a Criteria API da JPA 2.0 para implementar essa busca. Caso queira aprender mais sobre ela pode assistir nosso treinamento de JPA.

Por fim, no método load vamos passar os novos argumentos ao método listaTodosPaginada:

@Override
public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
    String titulo = (String) filtros.get("titulo");

    return dao.listaTodosPaginada(inicio, quantidade, "titulo", titulo);
}
Agora, após reiniciarmos o servidor podemos ver o filtro funcionando.
Para trabalhar de forma lazy o p:dataTable precisa fazer uma requisição ajax e portanto é necessário que ele se encontre dentro de um h:form. Tudo bem? :)


Opcional: Filtrando elementos com p:selectOneMenu

Nossa lista de livros tende a ser muito grande, uma boa prática seria oferecer para o usuário uma forma de filtrar as informações para que ele veja somente o que lhe interessa. Um exemplo clássico e que é muito comum é quando se vai à uma livraria e nos deparamos com diversas estantes separando os livros pelo gênero. Podemos (e vamos) implementar algo parecido em nossa aplicação. A ideia é que em nossa lista possamos ter um filtro para selecionarmos os livros de um gênero específico, como na imagem abaixo:


Com ajuda do Primefaces implementar esse requisito é algo bem fácil, vamos lá? :)

1) Vamos criar uma nova coluna, para mostrar o gênero na nossa dataTable:

<p:column headerText="Gênero" sortBy="#{livro.genero}"  filterBy="#{livro.genero}" filterMatchMode="startsWith">
    <h:outputText value="#{livro.genero}" />
</p:column>
Vamos criar, na classe Livro, um atributo genero:

// Livro.java
private String genero;

public void setGenero(String genero) {
    this.genero = genero;
}

public String getGenero() {
    return genero;
}
2) Dentro dessa coluna, colocaremos o componente p:selectOneMenu do primefaces para realizar o filtro:

<p:column headerText="Gênero" sortBy="#{livro.genero}" filterBy="#{livro.genero}" filterMatchMode="startsWith">

        <p:selectOneMenu>
            <f:selectItem itemLabel="Selecione..." itemValue="#{null}" noSelectionOption="true" />
            <f:selectItems value="#{livroBean.generos}" />
        </p:selectOneMenu>

    <h:outputText value="#{livro.genero}" />
</p:column>
O que irá acontecer quando eu selecionar um componente? Por enquanto nada :) Precisamos dizer que queremos filtrar a lista a cada mudança de valor do p:selectOneMenu. Para isso, vamos usar o atributo onchange:

<p:selectOneMenu onchange="PF('tabelaLivros').filter()">

Como estamos usando Javascript nesse atributo para controlar o dataTable, precisamos identificá-lo com uma variável Javascript usando o widgetVar.

No p:dataTable coloque o atributo widgetVar="tabelaLivros"

<p:dataTable value="#{livroBean.livros}" widgetVar="tabelaLivros" var="livro" id="tabelaLivros" paginator="true" rows="5">

4) Para o Primefaces identificar esse componente como um filtro, precisamos passar essa informação através de um f:facet

<p:column headerText="Gênero" sortBy="#{livro.genero}" filterBy="#{livro.genero}" filterMatchMode="startsWith">
    <f:facet name="filter">
        <p:selectOneMenu onchange="PF('tabelaLivros').filter()">
            <f:selectItem itemLabel="Selecione..." itemValue="#{null}" noSelectionOption="true" />
            <f:selectItems value="#{livroBean.generos}" />
         </p:selectOneMenu>
    </f:facet>
    <h:outputText value="#{livro.genero}" />
</p:column>
5) Crie uma lista de gêneros no LivroBean (não esqueça de criar o getter):

// LivroBean.java
private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");

public List<String> getGeneros() {
    return generos;
}
6) Coloque o atributo genero no modelo Livro, não se esqueça dos getters e setters

7) Vamos criar um novo campo no p:panelGrid para cadastrarmos agora livros com gênero

<p:outputLabel value="Gênero:" for="genero" />                            
<p:selectOneMenu value="#{livroBean.livro.genero}" id="genero">
    <f:selectItem itemLabel="Selecione..." itemValue="#{null}" />
    <f:selectItems value="#{livroBean.generos}" /> 
</p:selectOneMenu>
8) Reinicie o Tomcat, cadastre um novo livro com algum gênero e faça o teste do filtro :)

VER OPINIÃO DO INSTRUTOR
Opinião do Instrutor

Gostou do filtro? Há várias opções de componentes que podemos utilizar no lugar do p:selectOneMenu, vale a pena conferir na documentação!
http://www.primefaces.org/showcase/ui/data/datatable/filter.xhtml


---------------------------------------------------------------------------
<h1>Seção 06 - Melhorando a navegação com menus</h1>

Qual menu a usar?
Como sempre, iremos primeiramente olhar o ShowCase do Primefaces, lá tem uma sessão Menu, com várias opções, iremos utilizar o MenuButton. Podemos copiar todo o exemplo e ir adaptando-o para a nossa aplicação. Como queremos que o menu apareça na aplicação toda, devemos colocá-lo no _template.xhtml, mas não podemos nos esquecer que, antes de mais nada, devemos importar o namespace do Primefaces, dentro da tag <html>:

xmlns:p="http://primefaces.org/ui"
E o exemplo, dentro da div cabecalho:

<div id="cabecalho">
    <h:graphicImage library="img" name="logo.png" />

    <h:form>
        <p:growl id="messages" showDetail="true"/>

        <p:menuButton value="Options">
            <p:menuitem value="Save" actionListener="#{menuView.save}" update="messages" icon="ui-icon-disk"/>
            <p:menuitem value="Update" actionListener="#{menuView.update}" update="messages" icon="ui-icon-arrowrefresh-1-w"/>
            <p:menuitem value="Delete" actionListener="#{menuView.delete}" ajax="false" icon="ui-icon-close"/>
            <p:separator />
            <p:menuitem value="Homepage" url="http://www.primefaces.org" icon="ui-icon-extlink"/>
        </p:menuButton>
    </h:form>

    <h:form rendered="#{usuarioLogado != null}">
        <h:commandLink value="Logout" action="#{loginBean.deslogar}"/>
    </h:form>

    <h1>
        <ui:insert name="titulo"></ui:insert>
    </h1>
</div>
Ajustando os ícones
Vamos começar a adaptar o menu, só precisaremos de três itens: as páginas de login e autor e o logout. Vamos começar pelo logout, basta copiar os atributos do commandLink que já existe, removendo os antigos, só deixando o ícone (mas no caso utilizaremos o ícone fa fa-fw fa-sign-out):

<p:menuButton value="Options">
    <p:menuitem value="Save" actionListener="#{menuView.save}" update="messages" icon="ui-icon-disk"/>
    <p:menuitem value="Update" actionListener="#{menuView.update}" update="messages" icon="ui-icon-arrowrefresh-1-w"/>
    <p:separator />
    <p:menuitem value="Logout" value="Logout" action="#{loginBean.deslogar}" icon="fa fa-fw fa-sign-out" />
</p:menuButton>
Os outros itens são atualmente o Save e o Update, que virarão os itens Livros e Autores, com o atributo action fazendo o faces-redirect para as páginas livro e autor, respectivamente, e utilizando o ícone fa fa-fw fa-edit. Vamos alterar também o nome do menu, ao invés de Options, será Menu:

<p:menuButton value="Menu">
    <p:menuitem value="Livros" action="livro?faces-redirect=true" icon="fa fa-fw fa-edit" />
    <p:menuitem value="Autores" action="autor?faces-redirect=true" icon="fa fa-fw fa-edit" />
    <p:separator />
    <p:menuitem value="Logout" action="#{loginBean.deslogar}" icon="fa fa-fw fa-sign-out" />
</p:menuButton>
Ótimo, mas o menu também aparece na página de login, para resolver isso vamos fazer a mesma solução utilizada no commandLink antigo de logout, utilizando o atributo render do formulário:

<h:form rendered="#{usuarioLogado != null}">
    <p:growl id="messages" showDetail="true"/>

    <p:menuButton value="Menu">
        <p:menuitem value="Livros" action="livro?faces-redirect=true" icon="fa fa-fw fa-edit" />
        <p:menuitem value="Autores" action="autor?faces-redirect=true" icon="fa fa-fw fa-edit" />
        <p:separator />
        <p:menuitem value="Logout" action="#{loginBean.deslogar}" icon="fa fa-fw fa-sign-out" />
    </p:menuButton>
</h:form>
Pequenos ajustes no logout
Com isso já podemos remover o commandLink antigo de logout, deixando o template mais limpo.

Para finalizar o menu, vamos mover o p:growl para fora do formulário. Esse componente irá mostrar as mensagens de uma forma diferente, como se aparecessem notificações na tela.

<div id="cabecalho">
    <h:graphicImage library="img" name="logo.png" />

    <p:growl id="messages" showDetail="true" />

    <h:form rendered="#{usuarioLogado != null}">    
        <p:menuButton value="Menu">
            <p:menuitem value="Livros" action="livro?faces-redirect=true" icon="fa fa-fw fa-edit" />
            <p:menuitem value="Autores" action="autor?faces-redirect=true" icon="fa fa-fw fa-edit" />
            <p:separator />
            <p:menuitem value="Logout" action="#{loginBean.deslogar}" icon="fa fa-fw fa-sign-out" />
        </p:menuButton>
    </h:form>

    <h1>
        <ui:insert name="titulo"></ui:insert>
    </h1>
</div>
Mas se formos testar, parece que nada mudou, as mensagens continuam sendo exibidas como antes. Isso porque como estamos fazendo ajax, devemos definir qual componente devemos atualizar, então deveríamos alterar cada página dizendo que esse componente growl também deverá ser atualizado. Mas para evitar isso, o growl já tem um atributo para se auto-atualizar, o autoUpdate. Com isso não precisamos mexer nos componentes das outras páginas:

<p:growl id="messages" showDetail="true" autoUpdate="true" />
Reparem que agora as mensagens são exibidas na tela como antigamente e também na forma de notificações, tornando a página mais intuitiva.

p:growl é um componente que mostrar as mensagens de maneira sobreposta (overlay) na tela. É uma forma mais elegante de notificar o usuário da sua aplicação.
Vamos testar o p:growl então! Mãos à obra :)

1 - Aplique o componente p:growl no arquivo _template.xhtml logo em cima do componente h:form que possui o nosso menu:

    <p:growl id="growl" showDetail="true" autoUpdate="true" />
2 - Para testar componente p:growl erre no login, o p:growl já deveria mostrar as mensagens!

VER OPINIÃO DO INSTRUTOR
Opinião do Instrutor

Tente também as duas opções do showcase:
    <p:growl id="growl" showDetail="true" autoUpdate="true" sticky="true" />
sticky, através dessa config é preciso fechar a mensagens explicitamente
showDetail, mostra também os detalhes da mensagem
autoUpdate, o componente se atualiza sozinho



No Showcase há vários outros tipos de menu. Tente uma vez usar o MenuBar ou Menu simples!
VER OPINIÃO DO INSTRUTOR
Opinião do Instrutor

Por exemplo, usando o componente p:menu com sub-menus:
<p:menu toggleable="true">
    <p:submenu label="Cadastros">
        <p:menuitem value="Livros" action="livro?faces-redirect=true"  icon="fa fa-fw fa-edit" />
        <p:menuitem value="Autor" action="autor?faces-redirect=true"  icon="fa fa-fw fa-edit" />
    </p:submenu>
    <p:submenu label="Outros">
        <p:menuitem value="Logout" action="#{loginBean.deslogar}" icon="fa fa-fw fa-sign-out"/>
    </p:submenu>
</p:menu>
Ou usando o componente p:menubar:

<p:menubar>
    <p:submenu label="Cadastros" icon="ui-icon-document">
            <p:menuitem value="Livros" action="livro?faces-redirect=true"  icon="fa fa-fw fa-edit" />
                 <p:separator />
            <p:menuitem value="Autor" action="autor?faces-redirect=true"  icon="fa fa-fw fa-edit"/>
    </p:submenu>

     <p:submenu label="Outros">
        <p:menuitem value="Logout" action="#{loginBean.deslogar}" icon="fa fa-fw fa-sign-out"/>
    </p:submenu>
     <p:submenu label="Primefaces">
        <p:menuitem value="ShowCase" url="http://primefaces.org/showcase" icon="fa fa-fw fa-sign-out"/>
    </p:submenu>
</p:menubar>



-----------------------------------------------------------------------------
<h1>Seção 07 - Aplicando temas</h1>

O que são temas?
Com o menu da aplicação pronto, veremos neste capítulo os temas do Primefaces. Todos os componentes do Primefaces já têm um design, mas não existe um só design, existem vários tipos de temas. E uma coisa legal é que o usuário pode mudar dinamicamente esses temas através de uma simples ação. É isso que faremos neste capítulo, implementaremos o [ThemeSwitcher][1] do Primefaces.



Repare que há diversos temas disponíveis, os componentes continuam sendo os mesmos, mas com o visual diferente.

Alterando o tema com ThemaSwitcher
Vamos disponibilizar esse ThemeSwitcher para o usuário, primeiro copiando o exemplo, sem o f:selectItems, para dentro do _template.xhtml:

<div id="cabecalho">
    <h:graphicImage library="img" name="logo.png" />

        <h:form>
            <p:themeSwitcher id="basic" style="width:165px">
                <f:selectItem itemLabel="Choose Theme" itemValue="" />
            </p:themeSwitcher>
        </h:form>

        <!-- menu -->
</div>
Para fim de testes, vamos adicionar dois temas na mão, os temas aristo e vader:

<h:form>
    <p:themeSwitcher id="basic" style="width:165px">
        <f:selectItem itemLabel="Choose Theme" itemValue="" />
        <f:selectItem itemLabel="Aristo" itemValue="aristo" />
        <f:selectItem itemLabel="Vader" itemValue="vader" />
    </p:themeSwitcher>
</h:form>
Podemos visualizar a página agora, repare que o switcher já aparece, mas quando tentamos utilizar o tema vader, vemos que não funciona, somente o aristo. O problema é que no jar do Primefaces, dentro da pasta META-INF/resources, há somente a pasta primefaces-aristo, ou seja, cada tema deveria ter uma pasta correspondente. Só que o jar padrão do Primefaces só disponibiliza a pasta primefaces-aristo para nós.

Para baixar o jar com os outros temas, devemos ir até a página [Theme Gallery][2] e ir até o site do [repositório][3] do Primefaces. Lá há a pasta all-themes, que é o jar que contém todos os temas disponíveis, você pode baixá-lo diretamente [aqui][4]. Após o download, coloque o jar dentro da pasta WebContent/WEB-INF/lib da nossa aplicação.

Agora podemos testar novamente e o tema vader funciona! Mas quando fazemos o login, o tema muda, a configuração do switcher está para somente uma página, sempre que mudamos de página, o tema volta para o padrão, que é o arisco.


Na [documentação][5] do Primefaces, mostra como resolver esse problema do tema não ser persistido entra a navegação das páginas. Nós podemos dizer qual o tema queremos através de um context-param no arquivo web.xml, mas também podemos dizer que o tema é dinâmico utilizando expression language, o usuário que irá escolher o tema de sua preferência. Então, lá no web.xml, adicione:

<context-param>
    <param-name>primefaces.THEME</param-name>
    <param-value>#{temaBean.tema}</param-value>
</context-param>
Guardando a configuração no bean
Repare que a expression language chama o tema, da classe TemaBean, então vamos criar essa classe. Ela terá o atributo tema, com seus getters e setters, já pré-inicializado como vader, ou seja, a aplicação iniciará com o design desse tema. Lembrando que esse bean deverá ser anotado com @ManagedBean e ele terá que sobreviver durante várias requisições, ele tem que ser válido durante a sessão, logo ele também será anotado com @SessionBean:

@ManagedBean
@SessionScoped
public class TemaBean {

    private String tema = "vader";

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
Reiniciando o servidor e testando a aplicação, vemos que agora a aplicação já inicia com o tema vader. E navegando entre as páginas, vemos que o tema continua. Mas se mudarmos para arisco e trocar de página, o tema volta para o vader, então o problema original persistiu! Isso porque temos que mudar mais uma coisa. Se o usuário troca o tema através do switcher, precisamos reconfigurar o atributo do TemaBean. Para isso, vamos definir o atributo value do themeSwitcher, dizendo que ele é o tema do TemaBean:

<p:themeSwitcher id="basic" style="width:165px" value="#{temaBean.tema}">
    <f:selectItem itemLabel="Choose Theme" itemValue="" />
    <f:selectItem itemLabel="Aristo" itemValue="aristo" />
    <f:selectItem itemLabel="Vader" itemValue="vader" />
</p:themeSwitcher>
Só que por padrão, o themeSwitcher não utiliza ajax, então vamos adicionar a tag f:ajax para ele passar a fazê-lo:

<p:themeSwitcher id="basic" style="width:165px" value="#{temaBean.tema}">
    <f:selectItem itemLabel="Choose Theme" itemValue="" />
    <f:selectItem itemLabel="Aristo" itemValue="aristo" />
    <f:selectItem itemLabel="Vader" itemValue="vader" />
    <f:ajax />
</p:themeSwitcher>
Ótimo, agora tudo funciona como o esperado! Só que reparem que o switcher aparece também na página de login, para isso não acontecer vamos adicionar o atributo rendered no formulário, como já fizemos anteriormente:

<h:form rendered="#{usuarioLogado != null}">
    <p:themeSwitcher id="basic" style="width:165px" value="#{temaBean.tema}">
        <f:selectItem itemLabel="Choose Theme" itemValue="" />
        <f:selectItem itemLabel="Aristo" itemValue="aristo" />
        <f:selectItem itemLabel="Vader" itemValue="vader" />
        <f:ajax />
    </p:themeSwitcher>
</h:form>
Agora vamos colocar o switcher no lado direito da página, pois ele não é um elemento tão importante da página a ponto de dividir espaço com o menu. Então vamos colocá-lo do lado do logo, adicionando um panelGrid de duas colunas para agrupar esses dois componentes e colocando um estilo no formulário, através do atributo style, para jogá-lo para a direita:

<h:panelGrid columns="2">
    <h:graphicImage library="img" name="logo.png" />

    <h:form rendered="#{usuarioLogado != null}" style="position:absolute; right: 10px">
        <p:themeSwitcher id="basic" style="width:165px" value="#{temaBean.tema}">
            <f:selectItem itemLabel="Choose Theme" itemValue="" />
            <f:selectItem itemLabel="Aristo" itemValue="aristo" />
            <f:selectItem itemLabel="Vader" itemValue="vader" />
            <f:ajax />
        </p:themeSwitcher>
    </h:form>
</h:panelGrid>

Por fim, vamos disponibilizar todos os temas para o usuário escolher o que ele quiser, não mais só o aristo ou vader. Para isso, no TemaBean, vamos criar o método getTemas, que retorna um array de Strings com todos os temas disponíveis:

public String[] getTemas() {
    return new String[] { "afterdark", "afternoon", "afterwork", "aristo",
            "black-tie", "blitzer", "bluesky", "bootstrap", "casablanca",
            "cupertino", "cruze", "dark-hive", "delta", "dot-luv",
            "eggplant", "excite-bike", "flick", "glass-x", "home",
            "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
            "overcast", "pepper-grinder", "redmond", "rocket", "sam",
            "smoothness", "south-street", "start", "sunny", "swanky-purse",
            "trontastic", "ui-darkness", "ui-lightness", "vader" };
}
Mas como tornar isso dinâmico no template? Ao invés de criar um f:selectItem para cada tema, utilizaremos o componente f:selectItems, que irá receber todos os temas de uma vez só, através do método que acabamos de criar, para fazer um laço. Esse laço terá uma variável tema, que representa o tema a cada iteração, e essa variável será o valor dos atributos itemLabel e itemValue:

<p:themeSwitcher id="basic" style="width:165px" value="#{temaBean.tema}">
    <f:selectItem itemLabel="Choose Theme" itemValue="" />
    <f:selectItems value="#{temaBean.temas}" var="tema" itemLabel="#{tema}" itemValue="#{tema}" />
    <f:ajax />
</p:themeSwitcher>
Com isso conseguimos disponibilizar dinamicamente os itens para o usuário e finalizar este capítulo!



--------------------------------------------------------------------------------
<h1>Seção 08 - Gráfico com Primefaces</h1>

Introduzindo as vendas de um livro
Por que devemos preparar mais um modelo? Porque na nossa aplicação só temos os modelos de Livro e Autor, que não são bons exemplos de dados a serem representados em um gráfico. Por isso criaremos uma nova classe que será a Venda justamente para representar as negociações de cada livro na forma de um gráfico BarChart.

Essa classe, bem simples, terá os atributos livro e quantidade, com seus getters e setters além de um construtor que recebe esses dois atributos por parâmetro:

public class Venda {

    private Livro livro;
    private Integer quantidade;

    public Venda(Livro livro, Integer quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
    }

    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
Se temos um modelo, precisamos também do bean, já que teremos uma tela para mostrar os gráficos e cada tela no JSF precisa de um bean. Essa classe simulará algumas vendas, o ideal seria que as mesmas viessem do banco de dados, mas no nosso caso não há isso, então as vendas serão feitas em memória.

Teremos um método que retornará uma lista de vendas, mas como as vendas tem um livro, então precisamos pegar a lista deles também, utilizando o nosso já conhecido DAO:

@ManagedBean
@ViewScoped
public class VendasBean {

    public List<Venda> getVendas() {

        List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
        List<Venda> vendas = new ArrayList<Venda>();

        return vendas;

    }
}
Populando as vendas
Mas por enquanto a lista de vendas está vazia. Para populá-la, vamos fazer um foreach nos livros, e a cada iteração nós criamos uma nova venda, adicionando-a na lista.

public List<Venda> getVendas() {

        List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
        List<Venda> vendas = new ArrayList<Venda>();

        for (Livro livro : livros) {
            vendas.add(new Venda(livro, quantidade)); // o que será a quantidade?
        }

        return vendas;

    }
Falta criar a quantidade que será um número aleatório, vamos fazer isso utilizando a classe Random que recebe um seed por parâmetro, para sempre gerar um valor não repetido e em uma sequência. Mas como queremos sempre os mesmos valores randômicos, valores fixos, vamos fixar o valor do seed em 1234. Com isso, o atributo quantidade utilizará esse random, chamando o método nextInt, que serve para gerar um número randômico inteiro para nós, mas precisamos dizer qual será o valor máximo desse número, iremos dizer que queremos que o valor máximo de quantidade seja 500:

public List<Venda> getVendas() {

    List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
    List<Venda> vendas = new ArrayList<Venda>();

    Random random = new Random(1234);

    for (Livro livro : livros) {
        Integer quantidade = random.nextInt(500);
        vendas.add(new Venda(livro, quantidade));
    }

    return vendas;
}
Já conseguimos gerar uma lista de vendas, falta agora criar o xhtml e gerar o gráfico.
Podemos criar a página vendas.xhtml agora. Para facilitar, podemos copiar a página autor.xhtml e remover tudo que estava dentro da tag ui:define:

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:f="http://xmlns.jcp.org/jsf/core"
    xmlns:p="http://primefaces.org/ui" 
    xmlns:ui="http://xmlns.jcp.org/jsf/facelets">

    <ui:composition template="_template.xhtml">

        <ui:define name="titulo">
                <p:outputPanel>Vendas</p:outputPanel>
        </ui:define>

        <ui:define name="conteudo">

        </ui:define>

    </ui:composition>

</html>
O modelo do gráfico
Na [página][1] do BarChart já há um exemplo de como representar o gráfico no xhtml, vamos copiar a primeira linha (que representa o BarChart na vertical) e colocar no vendas.xhtml, dentro do ui:define. Mas vamos alterar o atributo model do exemplo, para ele utilizar o método getVendasModel, que ainda iremos criar na classe VendasBean:

<ui:define name="conteudo">
    <p:chart type="bar" model="#{vendasBean.vendasModel}" style="height:300px" />        
</ui:define>
Para implementar o método getVendasModel, vamos observar o exemplo que tem no ShowCase da classe ChartView.java. Ela possui um método chamado initBarModel que iremos copiar o seu conteúdo e adaptá-lo a nossa aplicação:

public BarChartModel getVendasModel() {

    BarChartModel model = new BarChartModel();

    ChartSeries boys = new ChartSeries();
    boys.setLabel("Boys");
    boys.set("2004", 120);
    boys.set("2005", 100);
    boys.set("2006", 44);
    boys.set("2007", 150);
    boys.set("2008", 25);

    ChartSeries girls = new ChartSeries();
    girls.setLabel("Girls");
    girls.set("2004", 52);
    girls.set("2005", 60);
    girls.set("2006", 110);
    girls.set("2007", 135);
    girls.set("2008", 120);

    model.addSeries(boys);
    model.addSeries(girls);

    return model;
}
O model representa os dados do nosso gráfico e o series são os dados específicos do nosso modelo, no nosso caso será o livro (seu título) e a quantidade de vendas.

Você já pode testar e verificar que já funciona, apesar de ainda não termos colocados os dados das vendas. Mas antes, adicione a página de vendas no nosso menu. Após isso, visualize-a:

<p:menuitem value="Vendas" action="vendas?faces-redirect=true" icon="fa fa-fw fa-bar-chart" />
A primeira chartSerie, no exemplo boys, é representada em azul, e girls em amarelo. Mas vamos representar as vendas adaptando o exemplo para a nossa aplicação. Vamos remover o chartSerie girls e mudar o boys, que passará a ser chamado de vendaSerie e terá Vendas 2016 como label:

public BarChartModel getVendasModel() {

    BarChartModel model = new BarChartModel();

    ChartSeries vendaSerie = new ChartSeries();
    vendaSerie.setLabel("Vendas 2016");

    model.addSeries(vendaSerie);

    return model;
}
Mas ainda não colocamos as vendas no gráfico, pois ainda não temos acesso a elas, para isso vamos criar uma variável vendas, que chama o método getVendas e iterar por cada venda através de um foreach. Para cada venda, adicionamos na série o título do seu livro e a quantidade, através do método set:

public BarChartModel getVendasModel() {

    BarChartModel model = new BarChartModel();

    ChartSeries vendaSerie = new ChartSeries();
    vendaSerie.setLabel("Vendas 2016");

    List<Venda> vendas = getVendas();

    for (Venda venda : vendas) {
        vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
    }

    model.addSeries(vendaSerie);

    return model;
}
Agora já vemos um gráfico de exemplo para as vendas dos nossos livros.


Para terminar, vamos adicionar mais um chartSerie, representando as vendas de 2015. Basta copiar o código anterior, mudando o label e o nome da variável para vendasSerie2015:
public BarChartModel getVendasModel() {

    BarChartModel model = new BarChartModel();

    ChartSeries vendaSerie = new ChartSeries();
    vendaSerie.setLabel("Vendas 2016");

    List<Venda> vendas = getVendas();

    for (Venda venda : vendas) {
        vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
    }

    model.addSeries(vendaSerie);

    ChartSeries vendaSerie2015 = new ChartSeries();
    vendaSerie2015.setLabel("Vendas 2015");

    vendas = getVendas();

    for (Venda venda : vendas) {
        vendaSerie2015.set(venda.getLivro().getTitulo(),
                venda.getQuantidade());
    }

    model.addSeries(vendaSerie2015);

    return model;
}
Vamos testar, mas as duas séries estão idênticas. Isso porque estamos gerando sempre os mesmos valores por causa do seed fixo no método getVendas. Para resolver isso, vamos alterar o método getVendas para receber o seed por parâmetro. Assim, quando chamarmos o método getVendas, podemos passar um seed diferente, para termos valores diferentes nas séries. Os métodos ficarão assim:

public BarChartModel getVendasModel() {

    BarChartModel model = new BarChartModel();

    ChartSeries vendaSerie = new ChartSeries();
    vendaSerie.setLabel("Vendas 2016");

    List<Venda> vendas = getVendas(1234);

    for (Venda venda : vendas) {
        vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
    }

    model.addSeries(vendaSerie);

    ChartSeries vendaSerie2015 = new ChartSeries();
    vendaSerie2015.setLabel("Vendas 2015");

    vendas = getVendas(4321);

    for (Venda venda : vendas) {
        vendaSerie2015.set(venda.getLivro().getTitulo(),
                venda.getQuantidade());
    }

    model.addSeries(vendaSerie2015);

    return model;
}

public List<Venda> getVendas(long seed) {

    List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
    List<Venda> vendas = new ArrayList<Venda>();

    Random random = new Random(seed);

    for (Livro livro : livros) {
        Integer quantidade = random.nextInt(500);
        vendas.add(new Venda(livro, quantidade));
    }

    return vendas;
}
Agora fica para vocês verem as outras opções, o que podemos fazer com os gráficos no Primefaces. Uma coisa legal que podemos fazer é a exibição animada, basta habilitá-la passando o valor true para o método setAnimate do model:

public BarChartModel getVendasModel() {

    BarChartModel model = new BarChartModel();
    model.setAnimate(true);

    // restante do método
}
Há várias outras opções de interatividade, para customizar os valores, etc. Não deixem de olhar o ShowCase do Primefaces.

Com isso, chegamos ao final do nosso treinamento, em que conseguimos customizar totalmente a nossa aplicação, utilizando componentes ricos e fáceis de se customizar. Até o próximo treinamento! Alias, essa aplicação ainda não está finalizada! No próximo curso vamos arrumar todas as classes usando CDI:

https://cursos.alura.com.br/course/jsf-cdi

O que aprendemos: - Usar o componente p:chart - Preencher o gráfico com o modelo - User series para representar uma continuação de valores - Gerar valores aleatórios através da classe java.util.Random

Muitas aplicações, principalmente jogos, usam números aleatórios. Esses números também podem ser úteis para criar cenários para testes. No nosso exemplo, os números aleatórios foram úteis para preencher as vendas com uma quantidade!
No mundo Java temos uma classe que possui a responsabilidade de criar números pseudoaleatórios, o java.util.Random:

https://docs.oracle.com/javase/8/docs/api/java/util/Random.html

Nesse exercício vamos ver rapidamente as principais funcionalidades do Random. Para testar crie uma classe simples com um método main:

package br.com.caelum.livraria.teste;

import java.util.Random;

public class TesteRandom {

  public static void main(String[] args) {

        Random gerador = new Random();

        int resultado = gerador.nextInt(10);
        System.out.println(resultado);

        int resultado2 = gerador.nextInt(10);
        System.out.println(resultado2);
    }

}
Execute algumas vezes essa classe e veja que aparecem os números entre 0 e 9 aleatoriamente.

Com essa classe não podemos apenas gerar inteiros como também valores do tipo double, long ou boolean, por exemplo:

Random geradorBoolean = new Random();

boolean valor = geradorBoolean.nextBoolean();
System.out.println(valor);

boolean valor2 = geradorBoolean.nextBoolean();
System.out.println(valor2);
Agora para que usamos esse seed? O seed ou semente alimenta o gerador com um valor inicial, e a partir desse valor serão gerados os próximos valores. Se a gente passa sempre o mesmo seed serão gerados os mesmos valores!! Teste isso, por exemplo:

Random geradorBoolean = new Random(10);//seed inicial é 10

boolean valor = geradorBoolean.nextBoolean(); //sempre true
System.out.println(valor);

boolean valor2 = geradorBoolean.nextBoolean(); //sempre false
System.out.println(valor2);
Agora pensando contrário, se a gente passa sempre um seed diferente, serão gerados sempre valores diferentes, correto? Normalmente, você usa como seed os millisegundos do sistema, por exemplo:

long millis = System.currentTimeMillis();
Random geradorBoolean = new Random(millis);

boolean valor = geradorBoolean.nextBoolean();
System.out.println(valor);

boolean valor2 = geradorBoolean.nextBoolean();
System.out.println(valor2);
Entendendo o seed podemos então gerar sequências de valores iguais ou diferentes!! Tudo bem?

Analise o código a seguir:
<ui:define name="conteudo">
    <p:chart type="bar" model="#{vendasBean.vendasModel}" style="height:300px" />        
</ui:define>
Podemos inferir que o método getVendasModel() do ManagedBean VendasBean deverá ter como retorno:

Pelo atributo type podemos perceber que o tipo por ser Bar exige um BarChartModel.
As outras opções são encontradas abaixo e cada uma delas possui um type condizente com o tipo do ChartModel:

DonutChartModel -> http://www.primefaces.org/showcase/ui/chart/donut.xhtml

PieChartModel -> http://www.primefaces.org/showcase/ui/chart/pie.xhtml

BubbleChartModel -> http://www.primefaces.org/showcase/ui/chart/bubble.xhtml

LineChartModel -> http://www.primefaces.org/showcase/ui/chart/line.xhtml

Explore a documentação via showcases do PrimeFaces e escolha o gráfico que melhor se adapta ao seu cenário. As opções são muitas! Divirta-se!

No primefaces temos tanto a representação do modelo de gráfico, feito por um ChartModel como temos que representar os dados desse modelo.
Dado que estamos usando um BarChartModel é correto afirmar que os dados são representados por um:

Durante esse capítulo utilizamos de um ChartSeries para representar as vendas.
public BarChartModel getVendasModel() {

    BarChartModel model = new BarChartModel();

    ChartSeries vendaSerie = new ChartSeries();
    vendaSerie.setLabel("Vendas 2016");

    List<Venda> vendas = getVendas();

    for (Venda venda : vendas) {
        vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
    }

    model.addSeries(vendaSerie);

    return model;
}
Analisando a documentação podemos notar que um ChartSeries apresenta duas subclasses possíveis: BarChartSeries e LineChartSeries.

Veja aqui se a curisiodade bateu: http://www.primefaces.org/docs/api/5.3/org/primefaces/model/chart/ChartSeries.html


O grande foco desse treinamento foi o uso do Primefaces para enriquecer a nossa interface. Vimos vários componentes e como integrá-los na nossa aplicação.
O nosso projeto evoluiu mas ainda há muito para melhorar! Por exemplo, aquela classe de Venda deve ser uma entidade, certo? Também há vários problemas na camada de persistência. O nosso problema ainda é como nós tratamos os DAO's e o JPA em geral. Não que a aplicação não funcione, mas ela poderia ser muito melhor, mais fácil de manter!

O próximo módulo, JSF 2 e CDI, ataca esses pontos e introduz conceitos importantes como a inversão de controle e a injeção de dependências. Espero encontrar você no próximo módulo:
