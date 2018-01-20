# Alura-JavaJSF2-III-IntegracaoComCDI

<h1>Seção 01 - Integração do CDI com JSF 2</h1>

Um dos principais assuntos discutidos quando começamos a trabalhar com Orientação a Objetos é a questão de manter o baixo acoplamento e a alta coesão.

Crescemos no mundo OO ouvindo isso de todos os lados, mas de fato, qual o real problema que o alto acoplamento nos traz? Primeiro, precisamos entender o que é um código acoplado.

Um código com alto acoplamento é quando uma classe conhece muitos detalhes de suas dependências, de modo que qualquer alteração em uma das dependências faz com que a classe que possui a dependência tenha que ser alterada.

Em nosso código, temos um exemplo claro do quão o alto acoplamento pode ser maléfico. A classe DAO requisita dentro do método existe um objeto do tipo EntityManager, através da classe JPAUtil, onde centralizamos a criação dos nossos EntityManagers.

Suponha que a classe JPAUtil precise agora instanciar EntityManagers de algum outro banco de dados e não somente do único banco presente no arquivo persistence.xml. Precisaríamos, neste caso, que a classe JPAUtil fosse alterada para ter dois tipos de EntityManagerFactory, uma para cada unidade de persistência que trabalhamos.

A classe JPAUtil precisará saber a partir de qual EntityManagerFactory ela criará as instâncias de EntityManager. Uma possível estratégia, seria passar na chamada do método getEntityManager uma String ou algo similar, que diga à classe JPAUtil qual EntityManagerFactory utilizar para criar o EntityManager. Teríamos um código parecido com este:

public class JPAUtil {

    private static EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("livraria");

    private static EntityManagerFactory emf2 = 
        Persistence.createEntityManagerFactory("banco");

    public EntityManager getEntityManager(String factory) {
        if ("notas".equals(factory)) {
            return emf.createEntityManager();
        } else if("notas2".equals(factory)) {
            return emf2.createEntityManager();
        }
    }
}
Automaticamente, teremos um erro de compilação dentro da classe DAO e não somente nela, mas em todas as classes que fazem referência a classe JPAUtil, e que na atual abordagem que estamos usando, podem vir a se tornar muitas. Um claro indício que nosso código está acoplado demais.

Um outro teste simples seria introduzir um novo parâmetro no construtor do DAO, por exemplo:

public DAO(Class<T> classe, boolean loga) {
    this.classe = classe;
}
Logo recebemos vários erros de compilação. Estamos espalhando a criação do nosso DAO pelos beans do JSF, praticamente sem controle. Mais um claro indício que nosso código está acoplado demais.

Estratégias para diminuir o acoplamento
Vimos que a maneira que estamos trabalhando atualmente em nosso design está mais atrapalhando do que ajudando. Isso graças ao alto grau de acoplamento que obtivemos ao longo do tempo. Mas como resolver isso? Como desacoplar o código e não afetá-lo com qualquer mudança?

A fábrica de EntityManager que criamos e chamamos de JPAUtil pode ser considerada uma forma de diminuir o acoplamento em nossas classes. Ela nos poupa de termos que criar o EntityManagerFactory toda vez que precisarmos de um.

Porém, como vimos anteriormente, ela nos ajuda mas ainda assim o acoplamento é grande.

O grande problema é que o código da classe DAO busca a dependência que precisa para realizar suas tarefas. Isso também aplica-se para os beans do JSF, todos eles buscam as dependências. E se ao invés de buscarmos esta dependência, recebêssemos ela? A classe DAO passaria agora a receber qualquer EntityManager, não importando de onde ele veio. Em nossos testes, poderíamos criar o EntityManager com base em um banco de dados em memória, por exemplo, o HyperSql Database (HSQLDB), ou ainda nem usar um banco de dados, simulando o objeto.

Inversão de controle
Queremos arrumar a nossa camada de persistência, mas infelizmente o JSF sozinho não consegue resolver esses problemas de acoplamento.

Precisamos de um framework que consegue gerenciar o EntityManager e o nosso DAO. Todos esses news espalhados pela aplicação devem sumir!

O framework que tem todo esse poder dentro da especificação JavaEE é o CDI. O "Context and Dependency Injection for the Java EE platform" (CDI) é uma especificação que diz como devemos trabalhar com nossas dependências. Ele assume o gerenciamento delas, define quanto tempo vivem e quando criar.

Nosso objetivo então é integrar o JSF com CDI para arrumar a nossa camada de persistência, mas antes de realmente arrumar a casa, vamos integrar os dois frameworks.


Você deve se lembrar que usamos a anotação @ManagedBean para declarar um bean gerenciado pelo JSF. Repare que eu falei gerenciado! O JSF também tem inversão de controle, mas apenas para ManagedBeans. Através dessa anotação, falamos para o JSF tomar conta dos nossos beans, o JSF então cria, quando precisar, um novo ManagedBean. Normalmente isso acontece quando usarmos a expression language acessando o bean.

Agora não queremos mais que o JSF gerencie esses beans, pois isso vai ser o trabalho do CDI. E o CDI é tão poderoso que não só gerencia os beans, como também os DAOs e o EntityManager!

Configuração do CDI
Chega de papo então, vamos começar a usar o CDI! O primeiro passo é baixar o JAR. A implementação do CDI se chama WELD e como estamos dentro de um servlet container, usaremos o weld-servlet.jar.

Esse JAR deve ficar dentro da pasta WebContent/WEB-INF/lib.

Como qualquer outra especificação, o CDI também precisa de uma configuração inicial, na verdade são duas pequenas configurações. A primeira é o arquivo context.xml, que fica no diretório WebContent/META-INF:

<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Resource name="BeanManager"
        auth="Container"
        type="javax.enterprise.inject.spi.BeanManager"
        factory="org.jboss.weld.resources.ManagerObjectFactory"/>
</Context>
O CDI chama o gerenciador, um BeanManager, e nesse arquivo declaramos exatamente qual BeanManager concreto queremos utilizar. Quando o Tomcat sobe, ele carrega automaticamente esse arquivo e inicializa o CDI (o BeanManager), ok?

A segunda configuração é o arquivo especifico do CDI. A especificação servlet tem web.xml, JSF tem o seu faces-config.xml e o CDI tem o seu beans.xml. Esse arquivo deve ser criado na pasta WebContent/WEB-INF (ao lado dos "irmãos" web.xml e faces-config.xml):

<!-- WebContent/WEB-INF/beans.xml -->
<beans>
</beans>
O beans.xml ficará vazio por enquanto, mas veremos mais para frente configurações nele.


Com a configuração feita, podemos começar a usar o CDI. Vamos primeiro mexer no AutorBean: apagar anotação @ManagedBean e usar a anotação do CDI @Named. Além disso, o CDI também dá suporte ao escopo de view mas a anotação possui um outro import. A anotação continuar sendo @ViewScoped, mas agora ela será do pacote javax.faces.view.

Muito cuidado na hora de importar a nova anotação @ViewScoped:

@Named
@ViewScoped //javax.faces.view.ViewScoped
public class AutorBean {
Faremos a mesma mudança no LivroBean, apagar o @ManagedBean e usar as anotações do CDI:

@Named
@ViewScoped //javax.faces.view.ViewScoped
public class LivroBean {
Mudamos apenas a forma de gerenciar o bean. Passamos o controle dos beans para o CDI (inversão de controle).

Lidando com a Passivação
O LivroBean já está pronto, mas ao reiniciar o Tomcat aparece um erro no console. O CDI exige pela especificação que qualquer Bean que esteja em um escopo maior do que a requisição implemente a interface Serializable.

Isso acontece pois o CDI usa um recurso que se chama de passivação que é nada mais do que gravar os dados de um objeto no disco quando o Tomcat se reinicializa.

Resumindo, quando usamos um escopo maior do que a requisição, como o ViewScoped ou SessionScoped, devemos implementar a interface Serializable:

@Named
@ViewScoped //javax.faces.view.ViewScoped
public class AutorBean implements Serializable {
@Named
@ViewScoped //javax.faces.view.ViewScoped
public class LivroBean implements Serializable {
Agora sim podemos testar e reiniciar a Tomcat. Deve subir sem problemas e a tela livro.xhtml deve continuar funcionando normalmente.

Faça as mesmas alterações nas classes LoginBean e VendasBean.

Na classe TemaBean, utilizamos a anotação @SessionScoped, nesse caso o novo import será do pacote javax.enterprise.context:

@Named
@SessionScoped //javax.enterprise.context.SessionScoped
public class TemaBean implements Serializable { ...

O que aprendemos?
Quais problemas traz o alto acoplamento
Configurar o CDI no Tomcat
Gerenciar os Managed Bean`s com CDI (inversão de controle com CDI)


Queremos utilizar o CDI na nossa aplicação para gerenciar os Beans.
Fazemos isso...
O correto é trocarmos nossos @ManagedBean por @Named, pois agora quem irá gerenciá-los é o CDI. Além disso, as anotações @ViewScoped e @SessionScoped agora devem vir, respectivamente, dos pacotes javax.faces.view.ViewScoped e javax.enterprise.context.SessionScoped.
Mas vamos lembrar do vídeo? Ainda resta uma coisa! O CDI exige pela especificação que qualquer Bean que esteja em um escopo maior do que a requisição implemente a interface java.io.Serializable.

Isso acontece pois o CDI usa um recurso que se chama de passivação que é nada mais do que gravar os dados de um objeto no disco quando o Tomcat se reinicializa. Então teremos que implementar Serializable:

@Named
@ViewScoped //javax.faces.view.ViewScoped
public class AutorBean implements Serializable {
O Eclipse dará um warning, que você pode: Ignorar, Ignorar com a anotação @SupressWarnings ou adicionar o atributo da classe. Para receber essas opções, basta apertar Ctrl+1 no Eclipse.


Acoplamento é um dos princípios fundamentais da Orientação a Objetos. Será que no nosso projeto, estamos implementando da maneira mais desacoplada possível? Considerando as afirmativas abaixo, quais estão corretas sobre o assunto?
1- Em nosso código, temos um exemplo de baixo acoplamento. Nossas Beans abrem um novo DAO a cada método, ao mudarmos por exemplo o construtor da classe DAO, não precisaríamos mexer em nada nas outras classes.

2- Em nosso código, temos um exemplo de alto acoplamento. Nossas Beans abrem um novo DAO a cada método, ao mudarmos por exemplo o construtor da classe DAO, precisaríamos mexer em todos os métodos de todas as Beans do projeto, para que se adequassem ao novo construtor.

3- A classe DAO requisita dentro do método existe um objeto do tipo EntityManager, através da classe JPAUtil, onde centralizamos a criação dos nossos EntityManagers. Isso é um código bom, encapsulado, pois caso JPAUtil queira instanciar um EntityManager de um outro banco de dados, bastaria passar na chamada do método getEntityManager uma String ou algo similar, que diga à classe JPAUtil qual EntityManagerFactory utilizar.

4- A fábrica de EntityManager que criamos e chamamos de JPAUtil pode ser considerada uma forma de diminuir o acoplamento em nossas classes. Ela nos poupa de termos que criar o EntityManagerFactory toda vez que precisarmos de um.

Vamos analisar as afirmativas?
1- Errada! Essa parte do código tem um alto acoplamento, exatamente porque se mudássemos o construtor da classe DAO, teríamos que alterá-lo em todos os métodos das nossas Beans!

2- Certa! Como explicado anteriormente, teríamos que refatorar muitas partes do nosso código, o que indica um mau-cheiro.

3- Errada! Caso recebêssemos um String em nosso getEntityManager, automaticamente teríamos um erro de compilação dentro da classe DAO e em todas as outras que se utilizam de JPAUtil.

4- Certa! A classe JPAUtil nos ajuda, porém o acoplamento ainda é grande, mas em maior parte o problema está na classe DAO.


Para saber mais: Nada se inventa tudo se copia


O CDI é uma especificação relativamente nova, mas os conceitos, principalmente a inversão de controle, são discutidos e usados há muito tempo na plataforma Java.
Pensando na inversão de controle, o pioneiro foi o Spring Framework. Tanto que o Spring se chamava antigamente Spring IoC (Inversion of Control), deixando claro no nome o grande diferencial. Hoje em dia o Spring é muito mais do que apenas um framework de IoC, mas no núcleo, na raiz mesmo, ele é um gerenciador de objetos igual ao CDI.

Olhando mais um pouco na história, podemos dizer que o Spring é o avô do CDI, mas não podemos esquecer o pai, certo? O pai do CDI é o antigo JBoss SEAM. O JBoss SEAM foi criado para melhorar o JSF 1.x, introduzindo várias melhorias que depois foram especificadas através do JSF 2.x e CDI! Por isso o JBoss SEAM foi descontinuado já que as especificações JSF e CDI já assumiram toda responsabilidade que o JBoss SEAM tinha.

Por exemplo, a anotação @Named ganhou esse nome por influência do JBoss SEAM que tinha uma anotação de mesmo nome.

O site do JBoss SEAM continua no ar, mas deixa claro que é um legado:

http://seamframework.org/

Isso foi só um pouco da historia do CDI, como nasceu e quem são seus antecessores :)
Mais para frente nesse curso mostrarei para você como substituir na nossa aplicação o CDI pelo Spring. Ambos são concorrentes de certa forma e por isso vamos rodar a nossa livraria também com Spring!

Para quem ficou interessado no Spring, aqui no Alura já temos um treinamento bastante completo (mas não usa JSF, usa Spring MVC):

https://cursos.alura.com.br/course/spring-mvc-1-criando-aplicacoes-web

https://cursos.alura.com.br/course/springmvc-2-integracao-cache-seguranca-e-templates



-----------------------------------------------------------------------------
<h1>Seção 02 - Injeção de dependências com CDI</h1>

CDI serve para tomar conta dos acoplamentos (Conexão entre as classes)


CDI - Context and Dependency Injection

Injeção de Dependências e Contextos


Gerenciando DAO e EntityManager com CDI
O CDI já está tomando conta dos nosso beans, que eram responsabilidade do JSF, mas a nossa motivação para usar o CDI era na verdade a camada de persistência. Mostramos no vídeo anterior que há uma acoplamento forte entre a classe DAO e JPAUtil, e os beans com o DAO. Chegou o momento de arrumar a casa e fazer que o CDI tome conta do DAO e do EntityManager!

A primeira coisa que devemos fazer é mexer na classe DAO. Repare que em todos os métodos nós damos new em JPAUtil, o que mostra o acoplamento da nossa aplicação. Vamos remover todas as linhas que dão new nessa classe. O acoplamento não existe mais, mas a classe DAO está cheia de erros, pois cada método depende do EntityManager e ele não existe mais!

Como o EntityManager é dependência de todos os métodos, dizemos que ele é uma dependência da classe. E o que fazemos com essa dependência? Como a classe precisa dela, vamos adicioná-la ao seu construtor;

public class DAO<T> {

    private final Class<T> classe;
    private EntityManager em;

    public DAO(EntityManager em, Class<T> classe) {
        this.em = em;
        this.classe = classe;
    }

    // métodos omitidos
}
Mas agora todas os beans que utilizam a classe DAO estão com erro, vamos resolvê-los.

Vamos começar com a classe AutorBean, repare que muitos métodos dão new no DAO, isso significa que o DAO será uma dependência da classe! Criamos o atributo, colocamos-o no construtor e substituímos os news (new DAO<Autor>(Autor.class)) da classe pelo novo atributo (this.dao):

public class AutorBean implements Serializable {

    private DAO<Autor> dao;

    public AutorBean (DAO<Autor> dao) {
        this.dao = dao;
    }

    // restante do código
}
Só que quem agora administra os beans é o CDI, mas o CDI precisa de um construtor padrão, e nós fizemos um construtor que recebe o DAO como dependência, aí o CDI não funcionará! A solução para isso é nós pedirmos para o CDI resolver esse DAO para a gente.

Quem cria o bean quando a aplicação sobe, é o CDI. A ideia agora é utilizar o mesmo pensamento para o DAO, quem vai criá-lo é o CDI! Mas como fazer isso?

O CDI vai injetar o DAO! Injetar significa passar uma instância pronta para usar. Para isso, vamos utilizar a anotação @Inject no atributo, quando o CDI vê essa anotação, ele saberá que o bean "quer" ou "precisa" de um DAO e consequentemente criará uma instância dele e a disponibilizará para nós:

public class AutorBean implements Serializable {

    @Inject
    private DAO<Autor> dao;

    // restante do código, agora sem o construtor
}
O CDI inverte o controle, inverter significa resolver a dependência e injetá-la. Não é mais a classe AutorBean que dá um new no DAO. O CDI está no controle, busca e injeta a dependência.

Essa forma de inversão de controle é chamado de injeção de dependências.

Mas lembra que o CDI precisa de um construtor padrão? Pois é, a classe DAO não tem um! Por isso vamos mostrar como o CDI lida com o DAO criando um DAO específico. O DAO específico significa que teremos um DAO para cada entidade do modelo, ou seja a classe Autor terá AutorDao e a Livro, um LivroDao:

public class AutorBean implements Serializable {

    @Inject
    private AutorDao dao;

    // restante do código, agora sem o construtor
}
Então vamos criar a classe AutorDao, com os métodos que já conhecemos (use o Eclipse para ajudá-lo nessa tarefa):

public class AutorDao {

    public Autor buscaPorId(Integer autorId) {
        return null;
    }

    public void adiciona(Autor autor) {
    }

    public void atualiza(Autor autor) {
    }

    public void remove(Autor autor) {
    }

    public List<Autor> listaTodos() {
        return null;
    }
}
Agora precisamos implementar esses métodos. Todos os métodos utilizarão o EntityManager, então ele será uma dependência. O que fazemos com as dependências? Injetamos!

public class AutorDao {

    @Inject
    EntityManager em;

    // restante do código
}
Com isso podemos agora finalmente implementar os métodos. Mas reparem uma coisa, esses métodos já foram implementados no DAO genérico, certo? Então porque não utilizá-los? Para utilizá-los, vamos criar um método que cria uma instância do DAO genérico para nós, e a guardará num atributo dao. Esse método se chamará init:

public class AutorDao {

    @Inject
    EntityManager em;

    private DAO<Autor> dao;

    void init() {
        this.dao = new DAO<Autor>(em, Autor.class);
    }

    // restante do código
}
Pronto, já temos o EntityManager e os métodos disponíveis para nós, agora basta chamar o método do DAO genérico em cada método correspondente da classe AutorDao:

public class AutorDao {

    @Inject
    EntityManager em;

    private DAO<Autor> dao;

    void init() {
        this.dao = new DAO<Autor>(this.em, Autor.class);
    }

    public Autor buscaPorId(Integer autorId) {
        return this.dao.buscaPorId(autorId);
    }

    public void adiciona(Autor autor) {
        this.dao.adiciona(autor);
    }

    public void atualiza(Autor autor) {
        this.dao.atualiza(autor);
    }

    public void remove(Autor autor) {
        this.dao.remove(autor);
    }

    public List<Autor> listaTodos() {
        return this.dao.listaTodos();
    }

}
Então o CDI tomará conta do AutorDao, porque vamos injetá-lo o AutorBean. O CDI criará o AutorBean e verá que ele precisa de um AutorDao, por isso ele também irá criá-lo. No AutorDao o CDI verá que ele precisa de um EntityManager e irá instanciá-lo. Agora só precisamos que o CDI chame o método init automaticamente quando instancia a classe AutorDao, para isso vamos anotar o método com @PostConstruct. Com isso o CDI chamará esse método assim que inicializar o AutorDao, inicializando também o DAO genérico:

public class AutorDao {

    @Inject
    EntityManager em;

    private DAO<Autor> dao;

    @PostConstruct
    void init() {
        this.dao = new DAO<Autor>(this.em, Autor.class);
    }

    // restante dos métodos
}
Estamos quase lá! Voltando na classe AutorBean, vemos que o CDI injeta o AutorDao nela, isso significa que ele executa um simples new AutorDao(). Já na classe AutorDao, o CDI também injeta um EntityManager, ou seja, new EntityManager... Mas o EntityManager é uma interface, logo não podemos instanciá-lo!

Então precisamos "ensinar" o CDI a instanciar o EntityManager, mas como?

Ensinando o CDI a criar um EntityManager
Vamos dar uma olhada na classe JPAUtil, que é responsável por levantar o JPA:

public class JPAUtil {

    private static EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("livraria");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close(EntityManager em) {
        em.close();
    }
}
Queremos que o CDI tome conta desse EntityManager. Então vamos "dizer" para o CDI que o método getEntityManager produz um EntityManager! Vamos devagar, o método getEntityManager devolve um EntityManager novo, então sabe criá-lo, certo? Olhando para os padrões de projeto, podemos dizer que o método é uma fábrica, aplicando o padrão factory method. Só que o CDI chama esses métodos de fábrica de Producer. É apenas um outro nome para algo muito comum.

Para deixar claro que o método sabe criar um EntityManager, devemos usar a anotação @Produces em cima do método:

@Produces
public EntityManager getEntityManager() {
    return emf.createEntityManager();
}
Com isso, o CDI conhece o método e sabe que ele devolve um EntityManager. Mas ele terá uma dúvida, o CDI desejará saber quantas vezes queremos criar um EntityManager dentro da aplicação. Iremos produzir um um novo EntityManager a cada requisição, para dizer isso ao CDI basta adicionar a anotação @RequestScoped:

@Produces
@RequestScoped //javax.enterprise.context.RequestScoped;
public EntityManager getEntityManager() {
    return emf.createEntityManager();
}
Produzimos um EntityManager a cada requisição, mas ainda temos um último problema. Veja na classe DAO que todos os métodos fecham o EntityManager! Ou seja, se removermos um autor, não conseguiremos gravar um novo na mesma requisição, o que não pode acontecer! Só devemos fechar o EntityManager depois da requisição. Então vamos apagar todas linhas que fecham o EntityManager (em.close()) na classe DAO.

Agora basta avisarmos ao CDI como fechar o EntityManager. Já temos um método close em JPAUtil. E para chamar um método quando a requisição acaba, o CDI possui uma anotação @Disposes:

public void close(@Disposes EntityManager em) {
    em.close();
}
Perfeito, o JPAUtil também foi "CDI-ficado"!

Por último, como o AutorBean sobrevive por mais de uma requisição, ele e seus atributos precisam implementar a interface Serializable. Por isso faça as classes AutorDao e DAO implementar essa interface.

Mas ainda falta fazer a mesma mudança no LivroBean e criar o LivroDao. Isso vai ficar para o exercício! Vamos começar?

O que aprendemos?
Gerenciar qualquer classe com CDI;
Criar um Producer com CDI para inicializar o EntityManager;
Injetar a dependências com CDI usando @Inject.



Aplicamos a injeção de dependência nesse capítulo. Vimos que o CDI, como container, usa a injeção de dependência para melhorar o design.
Leia com atenção as 3 afirmações abaixo:

A) Com injeção de dependência temos menos acoplamento no código.

B) Com injeção de dependências estamos controlando o ciclo da vida dos objetos.

C) Com injeção de dependências não estamos criando os objetos, o container assume essa responsabilidade.

Qual das afirmações são verdadeiras?


A) Com injeção de dependência temos menos acoplamento no código.
Correto, como não estamos mais instanciando os objetos, não estamos acoplados no construtor ou alguma fábrica como a JPAUtil.

B) Com injeção de dependências estamos controlando o ciclo da vida dos objetos.

Errado, todo o ciclo da vida é administrado pelo CDI. O CDI sabe quanto tempo vive um objeto (por exemplo por request ou por sessão etc). Esse controle não está no nosso código.

C) Com injeção de dependências não estamos criando os objetos, o container assume essa responsabilidade.

Correto, o container criar os objetos.

Em geral, através da Injeção de dependência tiramos muito controle sobre os nosso objetos e passamos para o container CDI. Por isso a injeção de dependência é um forma de inversão de controle!

Para quem gosta de saber mais sobre a inversão de controle e a injeção de dependência segue o capítulo do livro de arquitetura e design da Caelum:

http://www.arquiteturajava.com.br/livro/gerencie-suas-dependencias-atraves-de-injecao.pdf



Vimos no video que usamos um método nos DAOs específicos para inicializar o DAO generico, por exemplo no AutorDao:
public class AutorDao implements Serializable{

    @Inject
    EntityManager em;

    private DAO<Autor> dao;

    @PostConstruct
    void init() {
        this.dao = new DAO<Autor>(this.em, Autor.class);
    }

    //outros métodos omitidos
}
Quando será chamado o método init?
Resposta correta: Logo após criação do objeto e injeção do EntityManager.
O que acontece é que o CDI instancia o AutorDao usando o construtor padrão (1) (construtor sem argumentos), depois injeta o EntityManager (2), e depois chama o método com @PostConstruct(3). A injeção com CDI sempre segue esses 3 passos.

1) Chamando construtor padrão
2) Injeção
3) Chamando método com @PostConstruct
O uso do @PostConstruct é opcional, ou seja, o terceiro passo é só executado quando existe um método com essa anotação.


Fizemos uma grande refatoração, apagamos código e adicionamos classes. Segue um pequeno checklist para você verificar o seu projeto:
LivroBean injeta AutorDao e LivroDao
AutorBean injeta AutorDao
o AutorDao e o LivroDao injetam o EntityManager e usam @PostConstruct
o método getEntityManager do JPAUtil é um Producer, o método close usa @Disposes
as classes AutorDao, LivroDao e DAO implementam a interface Serializable
Tudo verificado? Não há mais erro de compilação? Então podemos subir o Tomcat! Fique atento ao console no Eclipse!


Para saber mais: Métodos de callback

A anotação @PostConstruct sinaliza que aquele método deve se chamado logo após da criação e da injeção. Esse método também é chamado de Callback. O método faz parte do ciclo da vida do objeto, aqui quando o objeto nasce.
Como existe uma anotação que indica o início do ciclo, também existe um método que indica o fim do ciclo da vida!

Por exemplo, podemos colocar no AutorBean os seguintes métodos:

@Named
@ViewScoped //javax.faces.view.ViewScoped
public class AutorBean implements Serializable{

    //atributos omitidos

    @PostConstruct
    void init() {
        System.out.println("AutorBean está nascendo ....");
    }

    @PreDestroy
    void morte() {
        System.out.println("AutorBean está morrendo ....");
    }
Como o AutorBean está no escopo da tela (ViewScoped), ele vai morrer quando a tela morre.Isso pode ser um pouco difícil de simular. Para ver o nosso método morte realmente funcionar, é mais fácil colocar o AutorBean temporariamente no escopo da requisição (javax.enterprise.context.RequestScoped):

@Named
//@ViewScoped //javax.faces.view.ViewScoped
@RequestScoped //apenas para testar os callbacks
public class AutorBean implements Serializable{

Bem vindo no mundo do inversão de controle! Repare que os dois métodos init e destroy não são chamados pelo nosso código e sim pelo CDI!
E não esqueça de colocar o AutorBean de volta no ViewScoped (javax.faces.view.ViewScoped).



------------------------------------------------------------------------
<h1>Gerenciando transações com CDI</h1>

Quem deveria controlar a Transação?
A nossa camada de persistência já está bem melhor graças ao CDI e a injeção de dependências. No entanto, olhando no DAO genérico há ainda um código duplicado, estamos gerenciando a transação na mão:

public class DAO<T> implements Serializable {

    private final Class<T> classe;
    private EntityManager em;

    public DAO(EntityManager em, Class<T> classe) {
        this.classe = classe;
        this.em = em;
    }

    public void adiciona(T t) {
        // abre transacao
        em.getTransaction().begin();

        // persiste o objeto
        em.persist(t);

        // commita a transacao
        em.getTransaction().commit();
    }

    //restante dos métodos omitido
}
Ou seja, o DAO está no controle do gerenciamento da transação, mas você já sabe quem deveria gerenciá-la. O CDI existe para inverter o controle, será que ele também pode tomar controle da transação?

Repare que essa questão é um pouco diferente da injeção de dependências. A dependência para gerenciar a transação é o EntityManager e ele já está no DAO. Queremos apenas que as chamadas de begin e commit não fiquem no DAO e sim sejam centralizadas em uma outra classe.

Centralizar o gerenciamento da transação
Vamos tentar criar essa classe, que se chamará GerenciadorDeTransacao, no pacote br.com.caelum.livraria.tx, com um método executaTX, que executa o begin e commit:

public class GerenciadorDeTransacao implements Serializable {

    public void executaTX() {
        manager.getTransaction().begin();

        //chama o método do DAO que precisa de TX

        manager.getTransaction().commit();
    }
}
Com isso, podemos remover todos os begins e commits da classe DAO genérica.

Mas agora criamos dois problemas:

1) Estamos usando o manager que não existe nessa classe.

2) Como vamos saber qual método devemos chamar entre begin e commit?

O primeiro problema é fácil de resolver, pois o manager é uma dependência. Vamos injetá-la:

public class GerenciadorDeTransacao implements Serializable {

    @Inject
    EntityManager manager;

    public void executaTX() {

        manager.getTransaction().begin();

        // chamar os daos que precisam de um TX

        manager.getTransaction().commit();
    }
}
Criando a anotação @Transacional
O segundo problema já é muito mais difícil de se resolver, como vamos definir no nosso código os pontos que precisam de uma transação? A ideia então é fazer com que os métodos, de alguma maneira, sinalizem que precisam de uma transação (que precisam da nossa classe GerenciadorDeTransacao). Essa sinalização pode ser feita através de uma anotação!

Por exemplo, dentro do AutorBean, sabemos que o método gravar e remover precisam de uma transação. Vamos anotá-los:

@Named
@ViewScoped
public class AutorBean implements Serializable {

    @Transacional
    public String gravar() {
        // código omitido
    }

    @Transacional
    public void remover(Livro livro) {
        // código omitido;
    }
}
A anotação @Transacional não existe, é uma invenção nossa e por isso devemos criá-la, senão o código nem compila.

package br.com.caelum.livraria.tx;

public @interface Transacional {

}
Quando criamos uma anotação, deve ficar claro que ela representa uma configuração e não uma implementação. A anotação @Transacional existe para configurar no método que é preciso ter uma transação. Nada mais do que isso, quem realmente vai chamar begin e commit é a nossa classe GerenciadorDeTransacao.

Target e Rentention da anotação
Quando definimos uma nova anotação, algumas outras configurações genéricas são necessárias. A JVM deve saber onde esse anotação pode ser utilizada. Existem anotações que podem ser utilizadas em cima da classe (por exemplo @Named), existem outras que funcionam em cima do atributo (como @Inject). A nossa anotação deve funcionar em cima do método, certo? Então vamos deixar isso explicito:

@Target({ ElementType.METHOD })
public @interface Transacional {

}
Além disso, devemos definir que a anotação faz parte da execução. Como assim? Talvez um ou outro aluno já viu a anotação @Override. Através dessa anotação o compilador do Java sabe que queremos sobrescrever um método e verifica a sintaxe. Ou seja, essa anotação é para o compilador. A nossa anotação é diferente e não há um impacto no compilador, ela deve funcionar na hora de executar (RUNTIME):

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Transacional {

}
Ótimo! Do ponto de vista do Java padrão a nossa anotação já está pronta, tanto que nosso código compila perfeitamente. Mas do ponto de vista do nosso objetivo (gerenciar a transação) ainda não resolve.

Associar a anotação com a transação
O que falta é associar a nossa anotação @Transacional com a classe GerenciadorDeTransacao. É essa a tarefa do CDI, ajudar a criar essa ligação. Uma vez feito, o CDI sabe que, ao encontrar a anotação @Transacional, deve chamar o método executaTX do GerenciadorDeTransacao. Então vamos lá!

Na classe GerenciadorDeTransacao, vamos usar a anotação também:

@Transacional
public class GerenciadorDeTransacao implements Serializable {
Para nossa surpresa, o código não compila. O problema é que definimos na anotação que ela só pode ser utilizada em cima de um método! Vamos corrigir isso:

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Transacional {

}
O ElementType.TYPE significa a anotação também está valida em cima da classe.

O código voltou a compilar, fizemos a associação da anotação @Transacional com classe GerenciadorDeTransação. No mundo CDI, esses tipos de classe que fazem algo antes (no nosso caso, executar o begin) e depois (commit) se chamam Interceptors. Devemos deixar isso claro no CDI, anotando a classe GerenciadorDeTransação com @Interceptor e a anotação com @InterceptorBinding, essa anotação diz que @Transacional está associada a um interceptador:

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
@InterceptorBinding
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Transacional {

}
Continuando a execução
Mas isso ainda não é suficiente. Repare que o método executaTX tem ainda esse comentário // chamar os daos que precisam de um TX! Hum, como vamos fazer isso? Claro que o CDI ajudará, pois é ele quem realmente sabe qual método precisa da transação, através da anotação @Transacional.

O CDI passará um parâmetro no método executaTX, que guarda a informação de quem precisa da transação. Esse objeto tem um nome “bonito”: contexto de invocação ou em inglês InvocationContext. Através dele, podemos pedir para chamar o método, mas como não sabemos diretamente o nome do método foi dado um nome bem genérico, proceed():

public void executaTX(InvocationContext contexto) throws Exception {

    manager.getTransaction().begin();

    // chamar os daos que precisam de um TX
    contexto.proceed();

    manager.getTransaction().begin();
}
Ao chamar proceed() devemos lançar uma exceção: throws Exception

Então, quando chamamos context.proceed() continuaremos com a execução, ou seja o método será executado. Depois do método executar, a execução volta e comita a transação. Repare o fluxo no método executa:

begin da transação
continuar a execução do método anotado com @Transacional
commit da transação
Mas como o CDI, ao ver a anotação @Transacional, saberá qual método deve executar? É executado algo antes e depois da chamada do método. O mundo do CDI chama o antes e depois de Around (ao redor), por isso devemos usar a anotação @AroundInvoke em cima do método:

@AroundInvoke
public void executaTX(InvocationContext contexto) throws Exception {

    manager.getTransaction().begin();

    // chamar os daos que precisam de um TX
    contexto.proceed();

    manager.getTransaction().begin();
}
Lidando com retorno no interceptador
Repare também que o método gravar do LivroBean, por exemplo, retorna uma String, mas poderia ser qualquer objeto. Como está o nosso método executaTX agora, esse retorno ficaria perdido. O método proceed devolve um objeto que representa o possível retorno dos métodos anotados. Vamos então retornar esse objeto no método executaTX:

@AroundInvoke
public Object executaTX(InvocationContext contexto) throws Exception {

    manager.getTransaction().begin();

    // chamar os daos que precisam de um TX
    Object resultado = contexto.proceed();

    manager.getTransaction().commit();

    return resultado;
}
Configuração do interceptador no beans.xml
A implementação do método já está perfeita, mas não esqueça de anotar também com @Transacional os métodos gravar e remover de AutorBean.

Por fim, precisamos configurar o configurar o GerenciadorDeTransacao no arquivo WebContent/WEB-INF/beans.xml:

<beans>
    <interceptors>
        <class>br.com.caelum.livraria.tx.GerenciadorDeTransacao</class>
    </interceptors>
</beans>
Podemos testar a nossa aplicação agora e ver que tudo continua funcionando corretamente! Foi um pouco trabalhoso criar um interceptador, mas criamos algo bem genérico e fácil de reutilizar. Os interceptadores são algo bem úteis e fazem parte de qualquer aplicação web. Bora fazer exercícios?


<h2>Vantagens de interceptador</h2>

Em diversos momentos surge a necessidade de interceptar a execução de métodos de negócio com códigos que não fazem parte da lógica principal.
Qual é a principal vantagem de usar interceptadores?

O Interceptador é um dos padrões de projetos (Design Pattern) bem importante do mundo Java e não é exclusividade do CDI. Eles também existem para EJB, Spring e vários outros frameworks do mundo Java!
Os Interceptadores ajudam a separar as responsabilidades da cada classe. Por exemplo, em vez de o DAO cuidar das chamadas de persistência e transação, tiramos a parte de transação e centralizamos dentro de um interceptador. Isso facilita a manutenção das nossas classes.

Claro, há desvantagens: podemos dizer que nosso código se torna mais difícil de entender, só olhando no DAO não é suficiente para saber como a transação está sendo gerenciada.

<h2>Desafio: Interceptor de Log</h2>
Já criamos um interceptador para gerenciar a transação. Isso foi um exemplo clássico de interceptador mas existem vários outros.
Imagine que você gostaria de medir o tempo de execução dos métodos nos seus DAOs especificos. Para fazer uma medição bastaria usar o método currentTimeMillis() da classe System em todos os métodos do DAO:

long antes = System.currentTimeMillis();

//execute aqui o codigo que usa o EntityManager

long depois = System.currentTimeMillis();
System.out.println(depois - antes);
Claro que a gente poderia repetir esse código em todos os métodos dos nossos DAOs especificos mas já aprendemos uma forma melhor de organizar o nosso código!

E aqui vem o desafio:

1) Crie uma anotação @Log que será utilizada nos métodos do DAO, por exemplo:

public class LivroDao {

@Log
public List<T> listaTodos() {
    //codigo omitido
}
Observação: A anotação só vai funcionar nos DAOs específicos (LivroDao, AutorDao etc). O DAO genérico não está sendo gerenciado pelo CDI.

2) Crie um interceptador TempoDeExecucaoInterceptor e associe-o com a anotação @Log. No método do interceptador mede o tempo e imprime no console.

3) No interceptador imprima, além do tempo gasto, o nome do método que foi executado no DAO. Dica: Você pode pegar o nome do método através do InvocationContext

4) Não esqueça de configurar o interceptador no beans.xml!

Mãos à obra!

Transação e LOG são dois exemplos clássicos para utilizar um interceptador e também chamados do requisitos não funcionais. Há vários outros requisitos não funcionais que podem ser implementados através de interceptadores como por exemplo: Cache, Segurança, Validação, Controle de error ou Auditoria.
Segue uma possível implementação:

//a anotação @Log:
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@InterceptorBinding
public @interface Log {
}
E a classe TempoDeExecucaoInterceptor:

@SuppressWarnings("serial")
@Interceptor
@Log
public class TempoDeExecucaoInterceptor  implements Serializable{

    @AroundInvoke
    public Object log(InvocationContext contexto) throws Exception {

        long inicio = System.currentTimeMillis();

        String metodo = contexto.getMethod().getName();

        Object proceder = contexto.proceed();

        long fim = System.currentTimeMillis();

        long resultado = fim - inicio;

        System.out.println("Método executado: " + metodo + " Tempo execução: " + resultado);

        return proceder;    
    }    
}
E o novo interceptador deve ser adicionado no arquivo beans.xml:

<beans>
    <interceptors>
        <class>br.com.caelum.livraria.tx.GerenciadorDeTransacao</class>
        <class>br.com.caelum.livraria.tx.TempoDeExecucaoInterceptor</class>
    </interceptors>
</beans>


--------------------------------------------------------------------------------------------------------------
<h1>Seção 04 - Completando a aplicação</h1>

Ainda falta para alterar a estrutura de login da nossa aplicação, ou seja, as classes UsuarioDao e LoginBean. Vamos começar pelo UsuarioDao, nessa classe ainda estamos instanciando e fechando o EntityManager, mas essas duas responsabilidades agora são do CDI. Vamos remover o EntityManager atual e injetá-lo. Mas não podemos esquecer de remover a linha que fecha o EntityManager (em.close()) e de implementar Serializable:

public class UsuarioDao implements Serializable {

    @Inject
    EntityManager em;

    // restante do código
    // remova em.close();
}
E no LoginBean, vamos injetar o UsuarioDao:

@Named
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    UsuarioDao dao;

    public String efetuaLogin() {
        System.out.println("fazendo login do usuario "
                + this.usuario.getEmail());

        FacesContext context = FacesContext.getCurrentInstance();
        boolean existe = dao.existe(this.usuario);
        // restante do código comentado
    }
    // restante do código comentado
}
Produzindo e injetando o FacesContext
Reparem mais uma coisa na classe LoginBean, nos métodos efetuaLogin e deslogar, ambos criam o FacesContext, e como nós já sabemos isso o torna uma dependência da classe. E o que fazemos com as dependências? Injetamos! Então injete o FacesContext e remova suas declarações (FacesContext context = FacesContext.getCurrentInstance();):

@Named
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    FacesContext context;

    // restante do código
    // remova as linhas FacesContext context = FacesContext.getCurrentInstance();
}
Mas quem precisa criar o FacesContext é o JSF, então o CDI não pode criar esse FacesContext. Então o que iremos fazer? Algo parecido com o que fizemos com o EntityManager, vamos criar um Producer.

Vamos criar a classe JsfUtil, com um método que retorna um FacesContext. Lembrando que precisamos mostrar para o CDI que esse método é um produtor através da anotação @Produces e que queremos criar o FacesContext uma vez por requisição, através da anotação @RequestScoped:

public class JsfUtil {

    @Produces
    @RequestScoped // javax.enterprise.context.RequestScoped
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

}
Na classe LivroBean, podemos também injetar o FacesContext. Injete-o e substitua FacesContext.getCurrentInstance() pelo nome do atributo injetado.

Podemos testar e ver que a injeção e produção do FacesContext está funcionando corretamente.


Atualmente, estamos simulando as vendas, e não pegando-as do banco de dados, ou seja, a classe Venda não é uma entidade. E um dos nossos objetivos para finalizar a aplicação é justamente colocar as vendas no banco de dados, e não mais produzi-las em memória.

Então vamos lá, a primeira coisa para definir Venda como uma entidade é anotar a classe com @Entity:

@Entity
public class Venda {
Uma entidade deve definir uma chave primária, então vamos criar um atributo id para ser essa chave. Vamos anotá-lo com @Id, justamente para dizer que esse atributo será a chave primária no banco, e com @GeneretedValue, para o banco gerenciar o seu valor, assim não precisamos atribui-lo.

Além disso, nessa classe temos um relacionamento, várias vendas são associadas a um livro, então vamos anotar o atributo livro com @ManyToOne.

Precisamos também criar um construtor padrão para a classe, já que é uma exigência do CDI:

@Entity
public class Venda {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Livro livro;
    private Integer quantidade;

    public Venda() {
    }

    // restante do código
}
Pronto, para finalizar precisamos adicionar a nova entidade no arquivo META-INF/persistence.xml. Adicione juntamente com as outras entidades:

<class>br.com.caelum.livraria.modelo.Venda</class>
Podemos agora reiniciar o Tomcat, e fazer login na aplicação, só para inicializar o JPA e a nova tabela ser criada.

Vamos agora entrar no MySQL (mysql -u root) e visualizar a tabela:

mysql> use livrariadb;
mysql> show tables;
+----------------------+
| Tables_in_livrariadb |
+----------------------+
| Autor                |
| Livro                |
| Livro_Autor          |
| Usuario              |
| Venda                |
| hibernate_sequence   |
+----------------------+
6 rows in set (0.00 sec)

mysql> desc Venda;
+------------+---------+------+-----+---------+----------------+
| Field      | Type    | Null | Key | Default | Extra          |
+------------+---------+------+-----+---------+----------------+
| id         | int(11) | NO   | PRI | NULL    | auto_increment |
| quantidade | int(11) | YES  |     | NULL    |                |
| livro_id   | int(11) | YES  | MUL | NULL    |                |
+------------+---------+------+-----+---------+----------------+
3 rows in set (0.00 sec)
Ótimo, a tabela já está lá, mas ela ainda está vazia, vamos então inserir algumas vendas. ATENÇÃO: Repare que para inserir uma venda, precisamos do id do livro, então os inserts só serão válidos com um id de um livro válido. Então antes dê um select nos livros e veja seus ids. Exemplo:

mysql> select * from Livro;
+----+----------------+-------------------+-------+------------------+
| id | dataLancamento | isbn              | preco | titulo           |
+----+----------------+-------------------+-------+------------------+
|  1 | 2016-02-26     | 112-3-54-986321-5 |  49.9 | MEAN             |
|  2 | 2016-02-27     | 156-0-16-963266-3 |  49.9 | Arquitetura Java |
|  3 | 2016-03-11     | 123-1-45-632526-6 |  39.9 | AngularJS        |
|  4 | 2016-03-15     | 151-9-61-911961-9 |  39.9 | TDD              |
|  5 | 2016-03-15     | 136-5-44-515951-5 |  29.9 | Primefaces       |
+----+----------------+-------------------+-------+------------------+
5 rows in set (0.00 sec)

mysql> insert into Venda (quantidade, livro_id) values (126, 1);
Query OK, 1 row affected (0.00 sec)

mysql> insert into Venda (quantidade, livro_id) values (257, 2);
Query OK, 1 row affected (0.00 sec)

mysql> insert into Venda (quantidade, livro_id) values (58, 3);
Query OK, 1 row affected (0.00 sec)

mysql> insert into Venda (quantidade, livro_id) values (199, 4);
Query OK, 1 row affected (0.00 sec)

mysql> insert into Venda (quantidade, livro_id) values (313, 5);
Query OK, 1 row affected (0.00 sec)
Você pode inserir uma venda para cada livro cadastrado no seu banco. Com o modelo preparado, precisamos preparar o VendasBean para pegar as vendas do banco e montar o gráfico, pois ainda estamos criando as vendas em memória.

Selecionando as vendas do banco de dados
O método que gera as vendas aleatoriamente é o método getVendas. Vamos modificá-lo para ele fazer um select no banco de dados, para isso vamos injetar o EntityManager e criar a query que seleciona todas as vendas do banco:

@Named
@ViewScoped
public class VendasBean implements Serializable {

    @Inject
    private EntityManager manager;

    // getVendasModel comentado

    public List<Venda> getVendas(long seed) {

        List<Venda> vendas = this.manager.createQuery("select v from Venda v",
                Venda.class).getResultList();

        return vendas;
    }
}
Com o método pronto, não precisamos mais do seed, pois ele era utilizado pelo Random, então ele não será mais passado por parâmetro para o método getVendas, vamos removê-lo.

Não precisamos mais injetar o LivroDao também, podemos removê-lo.

No método getVendasModel(), nós simulávamos duas séries de vendas. Vamos deixar apenas uma, pois não temos tantas informações no banco de dados para distinguir uma série de outra. Vamos remover a série de 2015:

public BarChartModel getVendasModel() {

    BarChartModel model = new BarChartModel();
    model.setAnimate(true);

    ChartSeries vendaSerie = new ChartSeries();
    vendaSerie.setLabel("Vendas 2016");

    List<Venda> vendas = getVendas();
    for (Venda venda : vendas) {
        vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
    }

    model.addSeries(vendaSerie);

    return model;
}
Podemos visualizar a página de vendas e ver o novo gráfico das vendas, mas dessa vez com as vendas cadastradas no banco de dados!


Para finalizar, na classe Livro, tivemos um problema no primeiro módulo do treinamento JSF, e para resolver esse problema fizemos um mapeamento que não é o ideal:

@Entity
public class Livro implements Serializable {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<Autor>();

    // restante do código
}
Muitos livros podem ter muitos autores, por isso temos o relacionamento many-to-many e o tipo EAGER significa que quando um livro é carregado, automaticamente são carregados os autores.

Normalmente esse tipo de relacionamento é uma má prática, pois nem sempre quando carregamos um livro, queremos carregar os autores, e isso pode gerar um custo de memória muito grande, em um projeto maior, por exemplo.

Vamos voltar o relacionamento para LAZY, que já é o padrão do mesmo:

@Entity
public class Livro implements Serializable {

    @ManyToMany
    private List<Autor> autores = new ArrayList<Autor>();

    // restante do código
}
Isso significa que ao carregar o livro, não serão carregados automaticamente os autores. O problema que isso gerará para nós é que, ao tentar carregar um livro para alterá-lo, seus autores não estarão carregados, ou seja, não conseguiremos exibi-los, por isso receberemos um LazyInitializationException.

Pare resolver isso, temos que alterar o método carregar da classe LivroBean, pois é esse método que é executado quando carregamos um livro. Atualmente ele está assim:

public void carregar(Livro livro) {
    System.out.println("Carregando livro");
    this.livro = livro;
}
Ou seja, colocamos o livro da tabela no nosso formulário, e é esse livro da tabela não terá os autores carregados. Por isso vamos carregar explicitamente o livro da tabela, através de uma nova conexão, utilizando o LivroDao. Nessa nova conexão, carregando o livro novamente, o JPA conseguirá carregar os autores:

public void carregar(Livro livro) {
    System.out.println("Carregando livro");
    this.livro = this.livroDao.buscaPorId(livro.getId());
}
Podemos testar agora, e tentar alterar um livro. Repare agora que os seus autores são carregados, e nenhuma exceção é lançada no console! Problema resolvido.

<h2>Relembrando JPA</h2>

Quando tornamos uma de nossa classes uma entidade JPA precisamos no mínimo:
Anotar a classe com @Entity e definir algum de seus atributos como chave através da anotação @Id. Além disso, precisamos adicionar a classe no arquivo persistence.xml. O uso de @GeneratedValue é opcional, mas altamente recomendado para atributos numéricos que anotados com @Id.
No Alura temos dois treinamentos que focam no JPA:

Persista seus objetos com a JPA2 e Hibernate: https://cursos.alura.com.br/course/jpa
Otimizações com JPA2 e Hibernate: https://cursos.alura.com.br/course/jpa-avancado

Um relacionamento @OneToMany e @ManyToMany possui por padrão o tipo de carregamento Lazy (preguiçoso). Contudo, podemos alterar este padrão através de:
Vejamos um exemplo no qual tornamos o relacionamento entre livro e autores como EAGER:
@Entity
public class Livro {

    // código anterior omitido

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Autor> autores;
}
Lembre-se que o padrão é FetchType.LAZY e normalmente boa prática. Tenha cuidado ao usar EAGER.

-----------------------------------------------------------------------------------------------------------------
<h1>Seção 05 - Integração com outras tecnologias: Maven, Spring e EJB</h1>
Começando daqui? Você pode fazer o DOWNLOAD completo do projeto do capítulo anterior e continuar seus estudos a partir deste capítulo.
Integração com o Maven
Nesse momento, você consegue imaginar o projeto fora do Eclipse? Sem ele ficamos perdidos. Estamos bastante acoplados com essa ferramenta, porém muitas vezes queremos rodar em outro lugar ou fazer deploy do nosso projeto. Além disso, perceba que estamos copiando todas as bibliotecas (.jar) para dentro da nossa pasta lib. Caso haja atualização de alguma delas, como por exemplo do Hibernate que estamos usando, teríamos que baixar os novos .jar, deletar os antigos e colocar dentro da pasta lib novamente. Isso dá bastante trabalho e nos faz perder muita produtividade.

O Maven vem para ajudar no gerenciamento do projeto, não é perfeito, porém é muito utilizado no mercado. Para aprender melhor a como fazer essa integração e a integração com outros projetos, nós temos um curso de Maven aqui no Alura.

Criando um novo projeto
Vamos criar um novo projeto, que terá a integração com o Maven. Para isso, iremos em New -> Project -> Maven Project. Selecionamos Create a simple project (skip archetype selection), pois iremos criar tudo do zero.

Algumas configurações na próxima página e depois Finish:

Group Id: Esse é o nosso pacote, que no caso é br.com.caelum;
Artifact Id: Esse é o nome do projeto. Já temos livraria no workspace, então vamos colocar livraria-maven;
Version: 0.0.1-SNAPSHOT;
Packaging: war;
Name: Livraria Alura;
Agora, iremos copiar todos os pacotes do projeto livraria (apenas os pacotes, sem a pasta META-INF) para dentro da pasta src/main/java do novo projeto. Nada compila ainda, mas não se preocupe. Agora sim iremos copiar a pasta META-INF, para dentro de src/main/resources. Por último, vamos copiar tudo que está dentro da pasta WebContent e colar na pasta src/main/webapp.

pom.xml
O pom.xml é o arquivo XML do Maven. Vamos lembrar que queremos que o Maven gerencie nossos .jar e acabe com o trabalho manual. Para isso, precisamos informar ao Maven quais dependências ele irá gerenciar. Dentro do XML, nós informamos isso ao Maven através da tag <dependencies></dependencies>. Porém fica difícil saber a versão, o nome da dependência, e por isso existe um site específico para nos ajudar, o mvnrepository.com. Nele podemos procurar por dependências dentro do repositório do Maven na internet.

Por exemplo, podemos procurar a nossa implementação do CDI, que tem como .jar o arquivo weld-servlet-2.3.3.Final.jar. No mvnrepository.com, pesquisamos por weld servlet. No link, ele te mostra todas as versões disponíveis do .jar no repositório do Maven. Clicamos na versão desejada (2.3.3.Final) e copiamos o XML de configuração do Maven:

<dependency>
    <groupId>org.jboss.weld.servlet</groupId>
    <artifactId>weld-servlet</artifactId>
    <version>2.3.3.Final</version>
</dependency>
Adicionada a dependência, o Maven começa a baixar o .jar do seu repositório e o guarda dentro de uma pasta local. Isso pode demorar um pouco. Você pode ver quais dependências estão sendo gerenciadas pelo Maven em Java Resources -> Libraries -> Maven Dependencies. Já separamos aqui os .jar do mvnrepository necessários (Se você já conhece o Maven, pode baixar o pom.xml completo aqui):

weld-servlet
validation-api
primefaces
mysql-connector
hibernate
jsf-api
jsf-impl
Porém, alguns .jar podem não existir no repositório do Maven. Para isso, precisamos adicionar no pom o repositório externo, para aí sim adicionar a dependência. Isso acontece com o primefaces themes. Você pode achar o repositório do PrimeFaces aqui. Fora de <dependencies>, você adiciona o novo repositório:

<repository>  
    <id>prime-repo</id>  
    <name>PrimeFaces Maven Repository</name>  
    <url>http://repository.primefaces.org</url>  
    <layout>default</layout>  
</repository>
E agora podemos pegar aqui as dependências que não existiam no mvnrepository, porém existem no repositório deles adicionado.

<dependency>  
    <groupId>org.primefaces.themes</groupId>  
    <artifactId>all-themes</artifactId>  
    <version>1.0.10</version>  
</dependency>
Removendo Erros
Com as dependências todas gerenciadas pelo Maven, a maior parte dos erros sumiu. Porém, ao criarmos o projeto no Eclipse como Maven Project, ele acabou por se utilizar de algumas configurações bem antigas. A versão da JRE usada é a 1.5, e precisamos da JRE 1.8. Por isso, precisamos ir em properties do projeto, e no Java Compiler, desmarcamos a opção Enable project specific settings. Agora em Java Build Path, apagamos a Library JRE System Library [J2SE-1.5] e adicionamos uma nova, com a JRE 1.8.

Ainda sobra um erro, por causa da versão do Java project facet, basta clicarmos no erro, apertar Ctrl+1 e mudar o Java facet para 1.8.

Rodando com o Maven
Como falado no início, podemos também rodar a aplicação através do Maven, para assim não ficarmos tão ligados ao Eclipse. Para isso, botão direito no projeto, e Run As... -> Maven Build. Em goals, ou seja, quando executarmos com o Maven, queremos que ele, por exemplo, dê um clean, compile o projeto e gere um WAR para deploy, para isso, colocamos:

clean compile package
Agora podemos rodar o projeto tanto com o Eclipse quanto com o Maven. Para terminar, vamos tirar o projeto antigo do Tomcat, adicionar o livraria-maven e rodar o Tomcat. Repare que tudo está funcionando normalmente.

Antes de entrarmos no universo de Spring, vamos revisar o que acontecia quando usávamos o CDI junto ao projeto desenvolvido. Observe a imagem a seguir:



Então o CDI é um container que administra os nosso objetos: nosso bean, nossos DAOs e também levanta toda a camada do JPA. Nós ficávamos via CDI injetando esses objetos para não ficarmos presos às implementações e usar menos news, e usar ainda menos o JPAUtil. Para fazer a transação, nós criamos um interceptador com CDI para deixar bem modularizado e centralizado. Logo o CDI ajuda com essas classes de infraestrutura de camadas: criando e amarrando esses objetos para nós.

Aí você deve estar se perguntando... Mas e o Spring? Onde ele entra nesse contexto!? Na verdade, o Spring faz o mesmo que o CDI, só que com mais opções. A popularização dele foi justamente nessa parte central de amarração de objetos. Aliás, o CDI, que é uma especificação, só existe pelo sucesso que esse núcleo do Spring fez. Logo o CDI e o Spring são concorrentes diretos.

Adaptando nosso projeto ao uso do Spring poderíamos usar a mesma representação já usada com CDI, com poucas alterações, como pode-se perceber pela imagem abaixo:



Da mesma forma que o CDI, o Spring é um container que irá criar nosso bean, os DAOs e inicializar o JPA. Além disso, também possui interceptadores para transação, log e há até sofisticados interceptadores sobre segurança, que é o Spring Security.

A diferença dele é que as configurações são um pouco diferentes e vem mais preparado para a utilização, sem a necessidade de criarmos producers e interceptadores da transação, por exemplo, além de outras coisas. Devemos ficar atentos para as anotações que também mudam: o bean se torna um @Controller, apesar de @Named (usado no CDI) também funcionar, e os DAOs se tornam um @Repository. Para as amarrações, temos algumas alternativas, embora o mais comum é utilizarmos um @Inject (similar ao do CDI), atente-se que no caso de um EntityManager devemos usar um @PersistenceContext, em vez de @Inject.

Em resumo, poderíamos dizer que o que vai mudar não é o conceito, continuamos usando um container que administra nossos objetos, vamos fazer utilização de interceptadores e injeção de dependências. Tudo isso deixou o Spring popular e ele foi o pioneiro, pode-se dizer que o CDI na verdade só especificou isso dentro de JavaEE. Sabendo disso tudo vamos analisar como ficaria o nosso projeto para trabalharmos com esse framework tão famoso.

Faça o download do projeto já configurado aqui.

No Eclipse, vamos importar esse projeto como um Existing Maven Project:



Selecione o projeto baixado anteriormente. O Eclipse já identifica o pom.xml e vamos dar um Finish para que as dependências sejam baixadas.

Abrindo o projeto e expandindo o src/main/java, é possível perceber que não temos mais o pacote br.com.caelum.livraria.tx, já que o Spring tem isso pronto para ser utilizado, não havendo a necessidade de criarmos um interceptador.

Expandindo o src/main/resources/META-INF percebemos um persistence.xml que é igual ao anterior.

Vamos entrar agora nas configurações do projeto web, fazemos isso expandindo src/main/webapp/WEB-INF e verificando o arquivo web.xml:

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:web="http://java.sun.com/xml/ns/javaee"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
    version="3.0">

    <display-name>livraria</display-name>

    <listener>
        <listener-class>
            org.springframework.web.context.ContextLoaderListener
        </listener-class>
    </listener>
    <listener>
        <listener-class>
            org.springframework.web.context.request.RequestContextListener
        </listener-class>
    </listener>

    <!-- restante do arquivo -->

</web-app>
Note que há uma configuração de dois listeners, usados justamente para o servidor iniciar o container do Spring. Essa configuração é análoga ao usada no CDI, com o bean.xml e o context.xml.

Ainda analisando o arquivo web.xml, é possível perceber que temos um filtro:

<filter>
    <filter-name>openEntityManagerInViewFilter</filter-name>
    <filter-class> org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>openEntityManagerInViewFilter</filter-name>
    <url-pattern>*.xhtml</url-pattern>
</filter-mapping>
Esse filtro, OpenEntityManagerInViewFilter, é usado para abertura e fechamento de um EntityManager no início e fim de uma requisição, respectivamente. Fazendo um comparativo com o CDI, nós tínhamos utilizado um producer, onde implementávamos essa forma de abrir e fechar o EntityManager. Com o Spring, já temos esses facilitadores prontos e nos preocupamos somente em configurá-lo.

Além dessas configurações comentadas temos as básicas do JSF com o Primefaces.

Ainda dentro de src/main/webapp/WEB-INF, vamos verificar o arquivo faces-config.xml, que possui uma configuração a mais:

<?xml version="1.0" encoding="UTF-8"?>

<faces-config version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd">

    <application>
        <message-bundle>resources.application</message-bundle>
        <el-resolver>org.springframework.web.jsf.el.SpringBeanFacesELResolver</el-resolver>
        <locale-config>
            <default-locale>en</default-locale>
        </locale-config>
    </application>

    <lifecycle>
        <phase-listener>br.com.caelum.livraria.util.Autorizador</phase-listener>
        <phase-listener>br.com.caelum.livraria.util.LogPhaseListener</phase-listener>
    </lifecycle>

</faces-config>
Esse el-resolver, como pode-se imaginar, serve justamente para que as Strings das Expression Languages utilizadas nas nossas views do JSF possam ser processadas por um bean. Lembre-se que quem faz essa ligação é o próprio Spring.

Por último, mas não menos importante, temos o arquivo applicationContext.xml, que é bem similar ao arquivo bean.xml do CDI. Vamos analisá-lo:

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-4.1.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx-4.1.xsd">

    <!-- For Scanning the packages net.javaonline.spring.inventory & net.javaonline.web.jsf.inventory 
        and registering the beans with the applicationContext -->
    <context:component-scan base-package="br.com.caelum.livraria" />
    <context:annotation-config />
    <tx:annotation-driven />


    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="entityManagerFactory" ref="entityManagerFactory" />
    </bean>

    <bean id="entityManagerFactory"
        class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="persistenceUnitName" value="livraria" />
    </bean>

</beans>
Na última configuração desse arquivo, temos:

<bean id="entityManagerFactory"
    class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    <property name="persistenceUnitName" value="livraria" />
</bean>
Esse bean é o responsável por ler as informações do nosso persistence.xml. A property de name persistenceUnitName recebe como livraria como valor, que é o mesmo valor usado no src/main/resources/META-INF/persistence.xml:

<persistence-unit name="livraria" transaction-type="RESOURCE_LOCAL">
Logo que a aplicação é inicializada pelo servidor, essa configuração sobe a JPA com essa unidade de persistência.

Ainda no applicationContext.xml, encontramos a configuração do gerenciador de transação. Nele fazemos uso de uma própria classe do Spring, que resolve isso para nós:

<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
    <property name="entityManagerFactory" ref="entityManagerFactory" />
</bean>
Perceba que além das configurações vistas acima, temos ainda as que habilitam o uso de anotações:

<context:component-scan base-package="br.com.caelum.livraria" />
<context:annotation-config />
<tx:annotation-driven />
Com todas as configurações feitas, vamos analisar como ficariam as nossas classes de DAOs. Lembre-se que as anotações recebem nomes diferentes no mundo Spring:

@Repository
@SuppressWarnings("serial")
public class AutorDao implements Serializable{


    @PersistenceContext
    EntityManager em;

    private DAO<Autor> dao;

    @PostConstruct
    void init() {
        this.dao = new DAO<Autor>(this.em, Autor.class);
    }

    // restante do código

}
Perceba que os DAOs são chamados de Repository e além disso injetamos um EntityManager através de um @PersistenceContext, e não um @Inject.

Analisando agora nossos beans, temos:

@SuppressWarnings("serial")
@Controller
public class AutorBean implements Serializable{

    private Autor autor = new Autor();

    @Inject
    private AutorDao dao;

    private Integer autorId;

    // restante do código
}
Perceba que ao invés de usar um @Named, que até funcionaria, o Spring usa como nomenclatura um @Controller. Já na injeção de dependência do AutorDao, basta usarmos um @Inject.

Portanto, usar o Spring no nosso projeto não exigiu grandes esforços. Aqui demos uma pincelada nele, se você tem interesse em se aprofundar nesse framework tão consagrado e poderoso veja o nosso curso aqui mesmo no Alura: Curso Spring MVC: É hora de criar uma webapp com Spring MVC4.

Até agora, trabalhamos com a especificações mais importantes do Java EE mas ainda não experimentamos rodar esse projeto em um servidor de aplicação. O que faremos agora é utilizar o JBoss WildFly para rodar nosso projeto. O que será que iremos precisar alterar no projeto? Vamos continuar usando JSF e o CDI, mas em um servidor de aplicação quem gerencia a JPA e as transações é um container, mais sofisticado que o CDI, chamado EJB. Então no nosso código, pra injetar oEntityManager vamos utilizar o EJB container que fará a injeção para nós. Contudo, não mexeremos em nada de JSF e CDI. Então vamos baixar o projeto preparado para rodar no container Java EE.

https://s3.amazonaws.com/caelum-online-public/jsf-cdi/stages/livraria-maven-wildfly-completo.zip

Quando importamos o projeto pode demorar um pouco, porque o Maven vai baixar e compilar nosso projeto. Olhando nosso pom.xml vemos as dependências do projeto. A novidade é que temos uma dependência principal relacionada com Java EE, a java-ee-api.

<dependency>
    <groupId>javax</groupId>
    <artifactId>javaee-api</artifactId>
    <version>7.0</version>
</dependency>
Veja que seu escopo é provided. Isso significa que a dependência fará parte do projeto apenas na hora de compilar (e não no WAR), porque ela será fornecida pelo servidor de aplicação. JSF, CDI, JPA e toda especificação JavaEE será suprimida pelo nosso servidor de aplicação WildFly.

Baixando o JBoss Wildfly
Precisamos baixar o servidor de aplicação em http://www.wildfly.org/downloads. A versão 10 é a que será usada, mas versões anteriores (8 e 9) a esta também devem ser compatíveis. Com o arquivo baixado, vamos descompactá-lo e em seguida associá-lo ao Eclipse.

No Eclipse, a aba de servidores já há o Tomcat. Vamos clicar com o direito na área branca e selecionar a opção New -> Server. Dependendo se você já instalou o Wildfly ou não, pode ser que ele não faça parte da lista, sendo necessário clicar em Show Downloadable Server Adapter para instalar o JBoss AS Tools. A instalação do JBoss AS Tools pode demorar um pouco.

Com a opção do Wildfly aparecendo, vamos selecioná-la e vinculá-la ao diretório do servidor de aplicação que descompactamos.

Para adicionar o projeto no Wildfly, basta clicarmos com o botão direito , e ir em Add and Remove... , selecionar a livraria-wildfly, clicar em Adicionar e em seguida em Finish.

Entendendo o projeto
Agora que como usamos EJB no projeto, nossa camada de persistência mudou um pouco. Se olharmos o pocote br.com.caelum.livraria.bean , vemos que não precisamos mexer em nada. A única coisa que mudou é que agora não temos mais nosso pacote br.com.caelum.livraria.tx, pois assim como o Spring, agora nós temos um servidor mais sofisticado, que assume o gerenciamento da transação e assume essa responsabilidade.



EJB Stateless
Se olharmos no nosso AutorDao vemos que agora temos a anotação @Stateless, que significa que esse DAO agora é um EJB, o que nos diz que agora ele pode receber um EntityManager aonde anotamos com @PersistenceContext. Se você quer aprender com mais detalhes sobre esse mundo do Java EE, você pode conferir aqui no treinamento de Java EE do Alura. E se você quiser saber mais detalhes sobre EJB, pode conferir aqui neste curso focado neste assunto.

Preparando o DataSource
Se tentarmos rodar agora, vamos ver que o nosso servidor irá quebrar. Vemos nos erros que ele já encontra nossos DAO's, e carrega eles corretamente, mas vemos que ele dá um erro em uma livraria-ds, acusando uma dependência não satisfeita. Mas o que será isso ? Bom, o que mudou no projeto foi a nossa camada de persistência, então vamos dar uma olhada no nosso persistence.xml. Observamos que ele não tem mais nenhuma configuração, não tem mais usuários, url's de banco de dados e nem mais nada disso. Mas podemos notar que aqui encontramos a nossa configuração de datasource, que é exatamente o livraria-ds.

<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
    version="2.0">

    <persistence-unit name="livraria" transaction-type="JTA"><!-- aqui temos o datasource -->

        <jta-data-source>java:/livraria-ds</jta-data-source>

        <class>br.com.caelum.livraria.modelo.Usuario</class>
        <class>br.com.caelum.livraria.modelo.Livro</class>
        <class>br.com.caelum.livraria.modelo.Autor</class>
        <class>br.com.caelum.livraria.modelo.Venda</class>

        <properties>
            <property name="hibernate.hbm2ddl.auto" value="update" />
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5InnoDBDialect" />
        </properties>
    </persistence-unit>

</persistence>
Um dos recursos que o Wildfly nos oferece é um datasource. Um datasource é normalmente um provedor de conexões, um pool de conexões. Quando o Wildfly inicializa, ele inicializa esses provedores de conexões, e a nossa aplicação faz uma solicitação a esse datasource através do nome livrariads. Só que quando subimos a aplicação, ela solicitou um datasource que ainda não configuramos. Ou seja, temos essa dependência a configurar.

Configurando o modulo
Vamos acesar o JBoss Wildflay fora do Eclipse. Entrando em wildfly -> modules -> system -> layers -> base -> com, vamos criar uma nova pasta: mysql. A ideia é que cadastremos o driver dentro do JBoss, e esse driver é o módulo que o JBoss irá carregar. Dentro da pasta mysql, vamos criar outra: main, e dentro dela colocaremos o .jar do mysql. Para que o JBoss interprete essa pasta e esse arquivo como módulo, é necessário um arquivo de configuração module.xml, que disponibilizamos para você aqui. O que esse arquivo faz é cadastrar o .jar como módulo do JBoss.

<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.38.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
  </dependencies>
</module>
`
Segue também o link do JAR do driver utilizado que deve estar na pasta com/mysql/main:

http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.38/mysql-connector-java-5.1.38.jar

Configurar o datasource
Porém agora temos um módulo do JBoss, como se fosse uma biblioteca disponível para ele usar. O que queremos é um dizer para o JBoss que esse módulo é um Driver. Por isso, voltamos a pasta raiz do Wildfly -> standalone -> configuration, e vamos mexer no arquivo standalone.xml. Vamos procurar por um elemento <drivers>, e cadastrar o nosso novo driver. Devemos adicionar:

<driver name="com.mysql" module="com.mysql">
    <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
</driver>
Após isso, só falta configurar o datasource, adicione o seguinte xml dentro do elemento <datasources>:

<datasource jndi-name="java:/livraria-ds" pool-name="livrariaDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:mysql://localhost:3306/livrariadb</connection-url>
    <driver>com.mysql</driver>
    <pool>
        <min-pool-size>10</min-pool-size>
        <max-pool-size>100</max-pool-size>
        <prefill>true</prefill>
    </pool>
    <security>
        <user-name>root</user-name>
    </security>
</datasource>
Com isso, o JBoss irá cadastrar o driver e disponibilizar o datasource. Rodando a aplicação, vemos que está tudo configurado, não recebemos a exceção, nos indica que o contexto foi registrado e tudo funciona!

Parabéns por terminar o curso, alguma dúvida, sempre utilize o nosso fórum!

Até agora, trabalhamos com a especificações mais importantes do Java EE mas ainda não experimentamos rodar esse projeto em um servidor de aplicação. O que faremos agora é utilizar o JBoss WildFly para rodar nosso projeto. O que será que iremos precisar alterar no projeto? Vamos continuar usando JSF e o CDI, mas em um servidor de aplicação quem gerencia a JPA e as transações é um container, mais sofisticado que o CDI, chamado EJB. Então no nosso código, pra injetar oEntityManager vamos utilizar o EJB container que fará a injeção para nós. Contudo, não mexeremos em nada de JSF e CDI. Então vamos baixar o projeto preparado para rodar no container Java EE.

https://s3.amazonaws.com/caelum-online-public/jsf-cdi/stages/livraria-maven-wildfly-completo.zip

Quando importamos o projeto pode demorar um pouco, porque o Maven vai baixar e compilar nosso projeto. Olhando nosso pom.xml vemos as dependências do projeto. A novidade é que temos uma dependência principal relacionada com Java EE, a java-ee-api.

<dependency>
    <groupId>javax</groupId>
    <artifactId>javaee-api</artifactId>
    <version>7.0</version>
</dependency>
Veja que seu escopo é provided. Isso significa que a dependência fará parte do projeto apenas na hora de compilar (e não no WAR), porque ela será fornecida pelo servidor de aplicação. JSF, CDI, JPA e toda especificação JavaEE será suprimida pelo nosso servidor de aplicação WildFly.

Baixando o JBoss Wildfly
Precisamos baixar o servidor de aplicação em http://www.wildfly.org/downloads. A versão 10 é a que será usada, mas versões anteriores (8 e 9) a esta também devem ser compatíveis. Com o arquivo baixado, vamos descompactá-lo e em seguida associá-lo ao Eclipse.

No Eclipse, a aba de servidores já há o Tomcat. Vamos clicar com o direito na área branca e selecionar a opção New -> Server. Dependendo se você já instalou o Wildfly ou não, pode ser que ele não faça parte da lista, sendo necessário clicar em Show Downloadable Server Adapter para instalar o JBoss AS Tools. A instalação do JBoss AS Tools pode demorar um pouco.

Com a opção do Wildfly aparecendo, vamos selecioná-la e vinculá-la ao diretório do servidor de aplicação que descompactamos.

Para adicionar o projeto no Wildfly, basta clicarmos com o botão direito , e ir em Add and Remove... , selecionar a livraria-wildfly, clicar em Adicionar e em seguida em Finish.

Entendendo o projeto
Agora que como usamos EJB no projeto, nossa camada de persistência mudou um pouco. Se olharmos o pocote br.com.caelum.livraria.bean , vemos que não precisamos mexer em nada. A única coisa que mudou é que agora não temos mais nosso pacote br.com.caelum.livraria.tx, pois assim como o Spring, agora nós temos um servidor mais sofisticado, que assume o gerenciamento da transação e assume essa responsabilidade.



EJB Stateless
Se olharmos no nosso AutorDao vemos que agora temos a anotação @Stateless, que significa que esse DAO agora é um EJB, o que nos diz que agora ele pode receber um EntityManager aonde anotamos com @PersistenceContext. Se você quer aprender com mais detalhes sobre esse mundo do Java EE, você pode conferir aqui no treinamento de Java EE do Alura. E se você quiser saber mais detalhes sobre EJB, pode conferir aqui neste curso focado neste assunto.

Preparando o DataSource
Se tentarmos rodar agora, vamos ver que o nosso servidor irá quebrar. Vemos nos erros que ele já encontra nossos DAO's, e carrega eles corretamente, mas vemos que ele dá um erro em uma livraria-ds, acusando uma dependência não satisfeita. Mas o que será isso ? Bom, o que mudou no projeto foi a nossa camada de persistência, então vamos dar uma olhada no nosso persistence.xml. Observamos que ele não tem mais nenhuma configuração, não tem mais usuários, url's de banco de dados e nem mais nada disso. Mas podemos notar que aqui encontramos a nossa configuração de datasource, que é exatamente o livraria-ds.

<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
    version="2.0">

    <persistence-unit name="livraria" transaction-type="JTA"><!-- aqui temos o datasource -->

        <jta-data-source>java:/livraria-ds</jta-data-source>

        <class>br.com.caelum.livraria.modelo.Usuario</class>
        <class>br.com.caelum.livraria.modelo.Livro</class>
        <class>br.com.caelum.livraria.modelo.Autor</class>
        <class>br.com.caelum.livraria.modelo.Venda</class>

        <properties>
            <property name="hibernate.hbm2ddl.auto" value="update" />
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5InnoDBDialect" />
        </properties>
    </persistence-unit>

</persistence>
Um dos recursos que o Wildfly nos oferece é um datasource. Um datasource é normalmente um provedor de conexões, um pool de conexões. Quando o Wildfly inicializa, ele inicializa esses provedores de conexões, e a nossa aplicação faz uma solicitação a esse datasource através do nome livrariads. Só que quando subimos a aplicação, ela solicitou um datasource que ainda não configuramos. Ou seja, temos essa dependência a configurar.

Configurando o modulo
Vamos acesar o JBoss Wildflay fora do Eclipse. Entrando em wildfly -> modules -> system -> layers -> base -> com, vamos criar uma nova pasta: mysql. A ideia é que cadastremos o driver dentro do JBoss, e esse driver é o módulo que o JBoss irá carregar. Dentro da pasta mysql, vamos criar outra: main, e dentro dela colocaremos o .jar do mysql. Para que o JBoss interprete essa pasta e esse arquivo como módulo, é necessário um arquivo de configuração module.xml, que disponibilizamos para você aqui. O que esse arquivo faz é cadastrar o .jar como módulo do JBoss.

<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.38.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
  </dependencies>
</module>
`
Segue também o link do JAR do driver utilizado que deve estar na pasta com/mysql/main:

http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.38/mysql-connector-java-5.1.38.jar

Configurar o datasource
Porém agora temos um módulo do JBoss, como se fosse uma biblioteca disponível para ele usar. O que queremos é um dizer para o JBoss que esse módulo é um Driver. Por isso, voltamos a pasta raiz do Wildfly -> standalone -> configuration, e vamos mexer no arquivo standalone.xml. Vamos procurar por um elemento <drivers>, e cadastrar o nosso novo driver. Devemos adicionar:

<driver name="com.mysql" module="com.mysql">
    <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
</driver>
Após isso, só falta configurar o datasource, adicione o seguinte xml dentro do elemento <datasources>:

<datasource jndi-name="java:/livraria-ds" pool-name="livrariaDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:mysql://localhost:3306/livrariadb</connection-url>
    <driver>com.mysql</driver>
    <pool>
        <min-pool-size>10</min-pool-size>
        <max-pool-size>100</max-pool-size>
        <prefill>true</prefill>
    </pool>
    <security>
        <user-name>root</user-name>
    </security>
</datasource>
Com isso, o JBoss irá cadastrar o driver e disponibilizar o datasource. Rodando a aplicação, vemos que está tudo configurado, não recebemos a exceção, nos indica que o contexto foi registrado e tudo funciona!

Parabéns por terminar o curso, alguma dúvida, sempre utilize o nosso fórum!

<h2>Um pouco sobre Maven</h2>
O Maven é uma ferramenta para gerenciar as dependências e fazer o build do projeto. O Maven faz parte da Apache e está presente na grande maioria dos projetos Java. Por exemplo, o projeto Alura usa Maven e o projeto VRaptor também!


https://maven.apache.org/

A grande vantagem disso é, que o Maven define vários padrões para projetos Java. Uma vez aprendido esses padrões você sabe criar o projeto, mesmo não conhecendo ou conhecendo pouco sobre o mesmo.

Por exemplo, o código fonte (as classes) sempre estão na pasta src/main/java, os arquivos de configuração sempre estão na pasta src/main/resources, os testes sempre estão na pasta src/main/test e assim para frente. Isso é bom pois não precisamos aprender a estrutura de cada projeto específico!

Além disso, o Maven padroniza o build em si. Com Maven sempre usamos o comando clean para limpar o projeto, sempre usamos compile para compilar, sempre o comando package para empacotar. Tanto faz se o projeto é um JAR, WAR ou outro tipo!

Com Maven também ganhamos a independência da IDE de desenvolvimento. Um projeto configurado com Maven funciona na linha de comando, funciona no Eclipse, Netbeans ou IntelliJ. É só importar e a IDE se configura baseado no pom.xml!

O Maven também é a base de várias outras ferramentas ligadas ao ciclo de vida do projeto. Com ele podemos plugar ferramentas de qualidade de código e começar a usar servidores de integração contínua. Enfim, o Maven é uma ferramenta extremamente útil (mas claro, não é perfeito) e vale muito investir nele.

No Alura temos um treinamento que dá uma boa introdução ao Maven:

https://cursos.alura.com.br/course/maven

<h2>Você conhece o Spring Framework?</h2>

Na aula mostramos brevemente como usar o Spring com JSF e JPA. Qual foi o objetivo ao introduzir o Spring?
Na aula substituímos o CDI com o Spring. Conceitualmente Spring é um container de Inversão de controle, igual ao CDI. Ambos são concorrentes.
A diferença do Spring é que ele possui vários outros projetos ao redor dele, como por exemplo o Spring MVC, Spring Security ou Spring Boot (e tem muito mais). Enquanto o CDI é "apenas" um container de inversão de controle, o Spring Framework tem muitos outros recursos a oferecer.

No Alura temos vários cursos que apresentam os principais recursos do Spring. Vou passar para você dois cursos:

Spring MVC I: Criando aplicações web https://cursos.alura.com.br/course/spring-mvc-1-criando-aplicacoes-web

Spring MVC II: Integração, cache, segurança e templates https://cursos.alura.com.br/course/springmvc-2-integracao-cache-seguranca-e-templates

Para quem gostaria de saber sobre Spring, quando usar e quais são as diferencias dele, tenho um link interessante para você:

http://blog.caelum.com.br/revisitando-a-batalha-spring-x-java-ee-em-detalhes/

<h2>Que tal usar EJB?</h2>
Nesse capítulo estamos mostrando algumas combinações de frameworks comumente encontrado em aplicações JSF. Usamos o CDI, vimos um pouco do Spring mas claro que não pode faltar o EJB!
Talvez o Enterprise Java Bean (EJB) seja o padrão Java EE mais famoso e talvez também o mais importante. Mas qual é a responsabilidade dos EJBs dentro de uma aplicação JavaEE?

Obs: Fique tranquilo se você estiver inseguro com essa pergunta pois o treinamento deu apenas uma pequena introdução sobre EJB!
Resposta correta: Inversão de Controle: Oferecendo vários serviços para a aplicação como Persistência, Transação, Segurança ou Remotabilidade.
Resposta errada: Componentes ricos na view: Oferecendo componentes ricas na camada de visualização com AJAX e templates.

EJB não se mete na view, esse é o trabalho do JSF!

Resposta errada: Inversão de Controle e Injeção de dependência apenas: Oferencendo um container simples para IoC e DI apenas.

Esse é o papel do CDI. Apesar do fato que o EJB inverte o controle e possui também uma forma de injeção de dependência, os EJBs vão muito além disso. Pensando apenas em IoC e DI temos o CDI como solução. Com EJB não é preciso criar um producer nem o gerenciador de transação, pois isso já vem pronto. No entanto, para usar EJB é preciso usar um servidor de aplicação como o JBoss Wildfly:



EJB é um tópico que merece uma grande atenção e por isso criamos um treinamento só focando nesse poderoso padrão. Se você gostaria de usar Java EE puro e conhecer melhor um servidor de aplicação, aconselho assistir o treinamento EJB no Alura! Segue o link do curso:

https://cursos.alura.com.br/course/ejb
