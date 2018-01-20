# Alura-JavaJPAPersistaSeusObjetosComJPA2EHibernate

----------------------------------------------------------------------------------------------
<h1>Seção 01 - Introdução à JPA e Hibernate</h1>

Em se tratando de Java, geralmente o termo JDBC é bastante comum, sendo responsável por conversar com o banco de dados a partir de uma aplicação Java. Trata-se da sigla que significa Java Database Connectivity e, junto à ela, usamos um driver específico que traduz esta comunicação.

Neste exemplo de um projeto, temos classes que não são tão importantes, como o ConnectionFactory e BD. Em algum momento de nossas vidas aprendemos que isolamos os SQLs em um padrão de projetos denominado DAO (Data Access Object).

Neste caso, temos um DAO especialista para trabalhar com o modelo da nossa aplicação, que é a classe Conta. É uma conta comum, com dados como banco, titular, agência e número.

Vamos abrir o arquivo ContaDAO para ver o que ele contém; reparem que não há muito, apenas um CRUD. Na aba "Outline", ao lado direito, veremos que ele possui os métodos adiciona(), altera(), remove(), procura(), listaPaginada(), e por aí vai.

No entanto, esta classe chega a alcançar quase 200 linhas de código, o que significa que demandou certo trabalho, justamente porque é preciso manter este SQL (para inserir ou buscar no banco de dados), não bastando escrever somente a classe.

Isto é um problema, pois temos um código muito extenso por causa dos DAOs. Voltando ao método adiciona(), vamos supor que por um acaso houve uma mudança na nossa especificação do sistema, e precisaremos retirar o atributo titular desta conta.

Se fazemos isto no banco de dados, precisaremos fazer o mesmo na classe, replicando a mudança em todas as queries. Se não temos mais a coluna titular, não faz mais sentido fazermos update passando-o, pois isto ocasionará em erro, além de dar certo trabalho.

Imaginem esta situação em um DAO com mais operações, atributos e outros. Da mesma maneira, não tendo mais o titular, não precisaríamos mais de getTitular(), então, na classe Conta, precisaríamos remover tanto o get quanto o set, bem como o atributo.

O que acontecerá com o DAO? Ele irá quebrar, pois todos os métodos utilizam o get ou set. Portanto, teremos que reescrever todos estes métodos grandes por conta da tradução necessária deste modelo orientado a objetos (conta) ao mundo relacional (SQLs).

Isto implicará em um trabalho absurdamente grande em um projeto real. Seria muito interessante se conseguíssemos evitá-lo, a partir de uma ferramenta que cuidasse desta diferença de dois paradigmas - orientado a objetos e relacional.

Na prática, poderíamos ter um método adiciona() que pudesse realizar banco.adiciona(conta);, porém ele não existe. Ou seja, seria muito legal se conseguíssemos, de um lado, fazer chamadas usando o Java, às quais resultassem em chamadas SQL, na criação e relatório de contas, sem termos que nos preocupar com a tradução entre os dois paradigmas.


Não queremos mais ficar transformando objetos em queries SQL, pois isto se responsabilizaria pela manutenção dos modelos, código do DAO e todos estes problemas. Isso caberá ao Mapeador Objeto-Relacional, que neste caso será o JPA (Java Persistence API).

Sem dúvida a Java DataBase Connectivity fornece uma excelente API para trabalharmos com bancos relacionais. Porém, há algumas consequências que precisamos avaliar na hora de escolher as tecnologias para nosso projeto. Dentre as que você viu nesse vídeo, qual das alternativas abaixo mais se encaixa?
Ao trabalharmos com JDBC precisamos traduzir os campos para colunas das tabelas. Para isso, existe a necessidade de se conhecer o modelo relacional.
Além de saber como acessar os dados de alguma entidade específica, precisamos preparar os dados para ser enviados via SQL para o banco. Ou seja, violamos o Single Responsability Principle e usamos a JPA para assumir a responsabilidade de fazer a tradução do mundo OO para o mundo relacional.

Ao fazer o mapeamento objeto relacional na mão, ficamos responsável pela conexão dos dois, quando é necessário fazer alguma alteração o trabalho é grande.
O Hibernate é uma ferramenta que faz esse mapeamento objeto relacional.
O Hibernate ficou tão famoso que Sun fez uma especificação chamada JPA (Java Persistence API)

O que faremos a partir de agora é utilizar uma ferramenta para a realização deste mapeamento para nós, denominada Hibernate, que ficou bastante famosa na comunidade, criando-se uma especificação chamada Java Persistence API.

Da mesma maneira como tínhamos no JDBC, também temos um projeto, contas-jpa, que servirá para demonstração de uso. Não se preocupem com as bibliotecas pois elas já estão inclusas e futuramente veremos com detalhes como baixá-las e colocá-las em seu projeto.

Recomendamos a alteração de uso da perspectiva Java EE para Java no Eclipse, para fins de organização e melhoria de visualização.
Abrindo a pasta src, selecionaremos a classe conta, que é o modelo. Reparem que aqui, no entanto, temos anotações a mais para "ensinarmos" ao Hibernate o que significa estes itens da classe.

O que significa o atributo id, e como indicamos ao Hibernate que isto será uma chave privada? Anotamos com @Id, indicando também em que local está o banco e as credenciais para inserção de contas. Faremos isto no arquivo persistence.xml, em "src > META-INF".

Vamos abrí-lo para verificar seu funcionamento. Percebam que configuramos um banco com MySQL passando a string de conexão com o banco jdbc, usuário (root) e senha do banco (vazio).

Abrindo a classe TesteJPA, veremos que nela instanciamos uma conta, populando as informações relacionadas a ela e, no final, pedimos que a JPA persista (salve) uma conta.

Por ora, não nos preocuparemos com EntityManager ou EntityManagerFactory. Vamos focar apenas na persistência desta conta, executando a aplicação e vendo o que acontece:

insert
into
    Conta
    (agencia, banco, numero, titular)
values
    (?, ?, ?, ?)
Dispara-se uma query insert em uma tabela Conta para a criação do registro setAgencia. Não há uma linha de SQL nesta classe do projeto, pois quem realiza o mapeamento do mundo orientado a objetos do relacional, de dados, é o Hibernate.

Isto significa que, se por um acaso quisermos deletar titular e tudo aquilo que for relativo a ele, não deixando de remover o setter também, e executarmos novamente a classe, ele continuará fazendo a query, desta vez sem o titular. Não quebramos o código de maneira alguma.


Quando trabalhamos com JPA, há um arquivo que usamos para fazer as configurações.
O arquivo src/META-INF/persistence.xml é importantíssimo para manter configurações de acesso ao banco e de recursos da JPA.

Analise o código abaixo:
Conta conta = new Conta();
conta.setTitular("Leonardo");

EntityManager em = Persistence.createEntityManagerFactory("financas")
                              .createEntityManager();

em.persist(conta);

em.close();

Para operações que alteram o estado do banco, precisamos colocá-las dentro de transações.
Ao fazer operações de escrita precisamos necessariamente definir os limites de uma transação (begin, commit, rollback).

Resumo
Vimos neste capítulo os problemas de termos códigos SQL em nossas classes e o desencontro entre o mundo orientado a objetos e o mundo relacional. Vimos também como solucionar este problema através de um framework ORM, que realiza o mapeamento objeto-relacional de nossas classes. Tudo isso através do Hibernate, uma biblioteca de mapeamento objeto-relacional que ficou tão famosa ao ponto de inspirar a criação da especificação JPA dentro do JavaEE, que por sua vez, utiliza o próprio Hibernate entre outros como implementação.


----------------------------------------------------------------------
<h1>Seção 02 - Primeiros passos com a JPA</h1>

JPA = Expecificação
Hibernate = Implementação

Baixar a implementação do site www.hibernate.org.
Dentro da pasta required é onde ficam as bibliotecas do hibernate.

Nesse capítulo começaremos a trabalhar em um projeto com JPA. Já temos um ambiente de desenvolvimento pré-configurado baseado no Java SE 8 e utilizaremos como IDE o Eclipse IDE for Java EE Developers - todos disponíveis nos links indicados.
Além disso não esqueça de baixar todos os JARs do Hibernate e do MySQL como visto no vídeo e no texto abaixo. Também segue o download para o arquivo de configuração: persistence.xml

Estamos usando Eclipse na versão Java EE Developers que pode ser baixado no link indicado. O primeiro passo é criar um projeto Java que chamaremos de financas. É um projeto Java padrão, ainda sem um servidor web. Ao finalizar, a estrutura básica é criada com a pasta src. Além desta, criaremos outra chamada lib que conterá todas as bibliotecas.

Vamos usar o JPA com Hibernate, então precisaremos baixar os JARs no site do Hibernate. Acessando hibernate.org seguiremos o link ORM. Nessa página, você poderá ver um botão com o texto Download.



Com o ZIP em mãos, vamos descompactar o arquivo e entrar na pasta da distribuição. Dessa pasta vamos copiar todos os JARs obrigatórios (required). Basta arrastá-los para pasta lib do nosso projeto. Não podemos esquecer o JAR do JPA, já que faremos uso do Hibernate através dele.

Como vimos, o JPA abstrai do desenvolvedor a camada SQL, mesmo assim é preciso copiar o JAR do driver JDBC. Em nosso caso usaremos o driver do MySQL. O JDBC Driver for MySQL (Connector/J) pode ser baixado aqui.

Todos os JARs foram copiados, agora só falta adicioná-los no classpath do projeto: ao selecionar os JARs, botão direito: "Build Path > Add to Build Path".



Vamos preparar o modelo. Na pasta src criaremos uma nova classe Conta no pacote br.com.caelum.financas.modelo. É essa classe que queremos mapear para uma tabela.

Ela vai definir um atributo id que será do tipo Integer. Esse atributo representa a chave primária. É boa prática usar chaves auxiliares e simples a favor de chaves compostas. Isso facilita não só o trabalho com JPA, como também simplifica o desenvolvimento Web.

Os próximos atributos serão titular, numero, banco e agencia, todos do tipo String. Só falta gerar os getters e setters.

public class Conta {

    private Integer id;
    private String titular;
    private String banco; 
    private String agencia;
    private String numero;
       // getters e setters
Para mapear a classe Conta é preciso anotá-la com @Entity do pacote javax.persistence. Entidades (ou Entities) são as classes que têm uma tabela associada. Além disso, é obrigatório declarar o atributo que representa a chave primária com @Id. A anotação @GeneratedValue é opcional, e é muito comum usá-la com chaves auxiliares. Com ela indicamos que o banco deve atribuir o valor da chave, e não a aplicação. Ao inserir uma conta no banco, automaticamente será alocada uma ID.

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String titular;
    private String numero;
    private String banco;
    private String agencia;
Para criarmos getters e setters, usaremos o atalho Ctrl + 3 + ggas, selecionando todos os atributos. Assim, temos nossa entidade pronta! Vamos partir para as configurações. O Hibernate precisa saber onde se localiza o banco de dados, então definiremos isto usando login e senha, no arquivo persistence.xml.

Pela especificação esse arquivo deve ficar na pasta META-INF. Vamos criá-la no src copiando para dentro dela o arquivo persistence.xml, que você já baixou.

Na unidade de persistência, logo em seu início, está a definição do provedor JPA (JPA Provider), e o Hibernate, bem como o nome da nossa entidade, Conta, com endereço completo. Além disso, declaramos as propriedades de conexão como o driver, URL, login e senha, e algumas propriedades específicas do Hibernate.

Repare que definimos um dialeto para MySQL. Através desta classe o Hibernate será capaz de gerar o SQL específico para nosso banco. Isto quer dizer que há dialetos específicos para os diversos bancos e suas versões.

A propriedade hbm2ddl é muito útil para ambientes de testes e provoca a criação e atualização automática das tabelas e colunas no banco de dados. Com essa configuração habilitada o Hibernate gerará uma tabela com o mesmo nome da entidade, gerando SQL, o qual poderemos pedir que ele o mostre antes de executar. Assim fica mais fácil de entender o trabalho do JPA.

<persistence xmlns="http://java.sun.com/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
        http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
    version="2.0">

    <!-- unidade de persistencia com o nome financas -->
    <persistence-unit name="financas">

        <!-- Implementação do JPA, no nosso caso Hibernate -->
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <!-- Aqui são listadas todas as entidades -->
        <class>br.com.caelum.financas.modelo.Conta</class>

        <properties>    
            <!-- Propriedades JDBC -->
            <property name="javax.persistence.jdbc.driver" 
                value="com.mysql.jdbc.Driver" />

            <property name="javax.persistence.jdbc.url" 
                value="jdbc:mysql://localhost/financas" />

            <property name="javax.persistence.jdbc.user" value="root" />
            <property name="javax.persistence.jdbc.password" value="" />

            <!-- Configurações específicas do Hibernate -->
            <property name="hibernate.dialect" 
                value="org.hibernate.dialect.MySQL5InnoDBDialect" />

            <property name="hibernate.hbm2ddl.auto" value="update" />
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true" />
        </properties>
    </persistence-unit>
</persistence>
Chegou a hora de testar o JPA. Para isso criaremos uma classe auxiliar chamada TesteConta no pacote br.com.caelum.financas.teste, com um simples método main.

public class TesteConta {

    public static void main(String[] args) {

        Conta conta = new Conta();
        conta.setTitular("Leonardo");
        conta.setBanco("Caixa Economica");
        conta.setAgencia("123");
        conta.setNumero("456");

    }
}
Agora precisaremos chamar o JPA para de fato persistirmos estes dados no banco, a partir de @EntityManager, classe principal para a operação de tudo que quisermos em nossas entidades:

EntityManagerFactory emf =  Persistence.createEntityManagerFactory("financas");
EntityManager em = emf.createEntityManager();
Para instanciarmos o EntityManagerFactory usaremos a classe Persistence, que representa o persistence.xml, a partir do qual criaremos o EntityManagerFactory passando o nome da unidade de persistência (aquele que agrupa todas as configurações que fizemos, de Provider, conta). Podemos querer fazer integração com o MySQL, com o Postgre, bastando criar mais de uma unidade de persistência.

Em nosso caso, por ora teremos apenas uma, chamada financas, e criaremos uma EntityManager utilizando a nossa Factory. Feito isto, sabemos que depois que o usarmos é preciso fechá-lo em nosso sistema. Solicitaremos ao EntityManager, ou seja, ao JPA, para que esta conta seja persistida, e para isto, abriremos e comitaremos uma transação:

EntityManagerFactory emf =  Persistence.createEntityManagerFactory("financas");
EntityManager em = emf.createEntityManager();


em.getTransaction().begin();
em.persist(conta);
em.getTransaction().commit();

em.close();
emf.close();
Vamos testar! Mas antes disso abriremos persistence.xml para verificarmos o nome do banco que usaremos (financas). Acessaremos o terminal e digitaremos mysql -u root para entrarmos no MySQL.

Depois, digitaremos create database financas; para criar o banco, e use financas; para usá-lo. Veremos suas tabelas por meio de show tables;. Como não há nenhuma, nada será mostrado.

Executaremos a classe TesteConta e veremos que foi feito create table Conta, ou seja, a tabela antes inexistente foi criada, inserindo-se a conta. Vamos ver se deu certo? No terminal, digitaremos show tables; novamente. Obteremos:

+--------------------+
| Tables_in_financas |
+--------------------+
| Conta              |
+--------------------+
1 row in set (0.00 sec)
Usando select * from Conta;, teremos o registro abaixo:

+----+---------+------------------+--------+----------+
| id | agencia | banco            | numero | titular  |
+----+---------+------------------+--------+----------+
|  1 | 123     | Caixa Economica  | 456    | Leonardo |
+----+---------+------------------+--------+----------+
1 row in set (0.00 sec)
Recapitulando, o que fizemos foi usar o Hibernate para persistir e gerenciar as entidades no banco de dados. Não há uma linha de MySQL neste projeto, pois quem o gerenciou para nós foi o próprio Hibernate, o qual cria as queries e as envia ao banco.

Relembrando que o Hibernate é uma biblioteca externa, portanto precisamos baixar seu zip colocando os arquivos JARs nela, que se encontram em "lib > required". A partir daí, configuramos o Hibernate com tudo que é necessário para seu funcionamento.

Já que utilizamos o Hibernate, usamos também o JPA, uma especificação; por trás dele estamos usando a implementação, o Hibernate. Cada uma das nossas entidades precisam estar entre tags <class>, e as configurações relativas ao local do banco, quais deles utilizamos (com os respectivos login e senha), e aquelas próprias do Hibernate, a linguagem utilizada, estratégia de criação de tabelas, se as queries serão mostradas no console ou não.

Definem-se configurações acerca da Entity para que o Hibernate saiba o que deve ser gerenciado, com @Id para informar que o atributo private Integer id; é uma chave primária, e que estamos usando a estratégia de auto increment (IDENTITY).



Uma das configurações que fazemos no arquivo persistence.xml é selecionar o dialeto que usaremos. Por exemplo:
<property name="hibernate.dialect" 
                value="org.hibernate.dialect.MySQL5InnoDBDialect" />
Qual é a função do dialeto?
O Dialeto também serve como forma de escolhermos recursos do banco que serão usados como por exemplo no MySQL onde podemos utilizar o MyISAM (storage strategy) que não possuem transações e integridade referencial (foreign key constraint).

Um item muito importante da JPA é a interface EntityManager, onde por meio dela conseguimos abstrair o mundo relacional e focar apenas em objetos. Para conseguir uma instância de EntityManager precisamos configurar propriedades no arquivo persistence.xml e obter a instância através da classe Persistence, como mostrado no código abaixo:
EntityManagerFactory entityManagerFactory = Persistence
                      .createEntityManagerFactory("financas");

EntityManager manager = entityManagerFactory.createEntityManager();

manager.close();
Das alternativas abaixo, qual delas é correta?
As configurações relacionadas ao acesso banco de dados ficam dentro da sessão persistence-unit. A JPA não limita o número de unidades de persistência (o que é útil quando precisamos de mais de um banco por aplicação, como veremos no próximo exercício) e por isso precisamos escolher um para usar no método createEntityManagerFactory.


Para saber mais: É possível trabalhar com múltiplos bancos?
Algumas aplicações podem precisar de mais de um banco de dados. Seja pra trabalhar com vários clientes ou até mesmo estratégias de sharding. A JPA permite que façamos isso simplesmente adicionando mais uma <persistence-unit> ao arquivo persistence.xml:

<persistence ...>

    <persistence-unit name="financas-mysql">

        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <class>br.com.caelum.financas.modelo.Conta</class>

        <properties>
            ...
        </properties>

    </persistence-unit>

    <persistence-unit name="financas-postgres">

        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <class>br.com.caelum.financas.modelo.Conta</class>

        <properties>
            ...
        </properties>

    </persistence-unit>
</persistence>
`
Dessa forma, basta escolher a unidade de persistência no método createEntityManagerFactory:

EntityManager em = Persistence.createEntityManagerFactory("financas-mysql").createEntityManager();

EntityManager em2 = Persistence.createEntityManagerFactory("financas-postgres").createEntit


Como você percebeu, ao trabalharmos com Hibernate precisamos colocar em nosso projeto várias bibliotecas que são usadas internamente pelo próprio provider.


Suportar manualmente as dependências de suas dependências é um trabalho árduo e que não gostaríamos de ter em um projeto real. Para isso (e várias outras coisas também), existem os gerenciadores de dependências como o Maven.

É possível utilizar Maven com JPA e Hibernate colocando no arquivo pom.xml a seguinte dependência:

<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>5.2.8.Final</version>
</dependency>

Quando criamos o persistence.xml colocamos uma propriedade que indica que queremos usar o Hibernate Mapping automático:
<property name="hibernate.hbm2ddl.auto" value="update"/>

Quando colocamos o valor dessa propriedade como update significa que qualquer alteração incremental nas nossas classes de modelo (ou a criação de novas classes) também ocorrerão nas tabelas quando utilizarmos novamente a JPA. Isso significa que ao criarmos uma nova classe ou inserirmos um novo campo em alguma entidade, o provider usado irá fazer as alterações no banco de dados. É importante que, ao utilizarmos essa estratégia, façamos varreduras periódicas no banco para eliminar campos e tabelas não mais usadas.

Quando queremos sempre um banco limpo ao iniciar a aplicação podemos utilizar a estratégia create que permite com que o provider remova todos os dados do banco e crie as tabelas baseando-se nos seus modelos.

É possível deletarmos todos os dados do banco ao terminarmos o EntityManagerFactory (ou SessionFactory caso esteja usando Hibernate sem JPA), basta usarmos a estratégia create-drop.

Por último podemos desabilitar totalmente o Hibernate Mapping usando a estratégia none.

----------------------------------------------------------------------------------------------------------------
<h1>Seção 03 - Gerenciamento de estados pelo EntityManager</h1>

Começando deste capítulo? Você pode baixar um projeto com tudo o que foi feito até agora.
Você também precisará colocar a classe PopulaConta que foi usada no vídeo dentro do pacote br.com.caelum.financas.teste.

No último vídeo vimos como criar uma entidade e mapeá-la para o banco de dados por meio do Hibernate. Usamos as anotações típicas: @Entity, @Id e @GeneratedValue.

Depois definimos através do persistence.xml as configurações mais genéricas, como o provedor (ou Provider), a conexão com o banco de dados e as propriedades do Hibernate. Criamos o EntityManager persistindo uma conta no banco de dados.

Vimos que a classe Persistence representa o arquivo persistence.xml, que permite a criação da instância de EntityManagerFactory com base no persistence unity configurado na tag <persistence-unit> (neste caso, financas).

Abrimos uma transação, persistimos a conta, será realizado um insert; no console, vimos o porquê de termos habilitado esta função, em hibernate.show_sql, que está como true. Se colocarmos false, ele não irá mais mostrá-la. Comitamos a transação para que de fato esta conta seja inserida.

Esta fábrica precisará ser reutilizada em outros testes e situações que veremos futuramente. Seria interessante, portanto, se conseguíssemos isolá-la em alguma classe para que não precisemos ficar criando-a a todo momento. Não precisamos de mais de uma fábrica para toda a aplicação.

Criaremos, então, uma classe denominada JPAUtil no pacote br.caelum.financas.util. Nela, criaremos um atributo estático para garantir que teremos apenas uma instância desta fábrica em memória, e para a qual solicitaremos a criação de um EntityManager a ser retornado por meio do método getEntityManager():

public class JPAUtil {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");

    public EntityManager getEntityManager() {
         return emf.createEntityManager();
    }
}
Feito isto, em TesteConta poderemos deletar a linha abaixo:

EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
para simplesmente instanciarmos o JPAUtil realizando sua importação, assim como o getEntityManager:

EntityManager em = new JPAUtil().getEntityManager();
Desta forma, poderemos também excluir a linha emf.close();, pois não existe nenhuma referência à fábrica. Criaremos mais uma classe, já que para os demais testes precisaremos de outras contas populadas no banco de dados. Esta classe se denominará PopulaConta e se localizará em br.com.caelum.financas.util (o arquivo da classe está disponível no início da transcrição).

Executaremos esta classe, lembrando de iniciarmos nosso banco de dados antes. No terminal, vamos ver se estas contas realmente foram para o banco, repetindo o procedimento de listagem das tabelas feito no vídeo anterior. Obteremos seis contas, as quais usaremos nos próximos testes.



<h2>Conhecendo o estado Managed</h2>
Já sabemos como persistir uma conta, agora aprenderemos a buscar estas contas no banco de dados utilizando o JPA. Faremos um teste para isto, criando a classe TesteBuscaConta, no mesmo pacote de testes usado anteriormente.
Como se trata de uma classe executável, precisaremos de um método main. Para sua criação, poderíamos usar o Persistence, no entanto já isolamos esta fábrica em uma classe denominada JPAUtil, conforme visto no vídeo anterior.

public static void main(String[] args) {

    EntityManager em = new JPAUtil().getEntityManager();
    em.getTransaction().begin();

    Conta conta = em.find(Conta.class, 1);


    em.getTransaction().commit();
}
Queremos buscar a conta de id 1, que é o parâmetro da chave primária, o id do registro do banco que queremos buscar, e é importante que ele seja do mesmo tipo colocado na própria classe da entidade (Conta).

Consultando o banco, verificaremos qual o titular desta conta específica: Leonardo. Imprimiremos este nome para ver se o funcionamento está correto:

public static void main(String[] args) {

    EntityManager em = new JPAUtil().getEntityManager();
    em.getTransaction().begin();

    Conta conta = em.find(Conta.class, 1);

    System.out.println(conta.getTitular());

    em.getTransaction().commit();
}
Executando-a, teremos:

Hibernate: 
    select
        conta0_.id as id1_0_0_,
        conta0_.agencia as agencia2_0_0_,
        conta0_.banco as banco3_0_0_,
        conta0_.numero as numero4_0_0_,
        conta0_.titular as titular5_0_0_ 
    from
        Conta conta0_
    where
        conta0_.id=?
Leonardo
De novo, não há nenhuma linha de SQL neste teste, o select foi feito pelo Hibernate. Seguiremos alterando o nome do título, pois agora queremos que a conta seja do João:

public static void main(String[] args) {

    EntityManager em = new JPAUtil().getEntityManager();
    em.getTransaction().begin();

    Conta conta = em.find(Conta.class, 1);

    conta.setTitular("João");

    System.out.println(conta.getTitular());

    em.getTransaction().commit();
}
Executando-se a classe, além de buscar e imprimir, o Hibernate realizou um update, que verificamos se está correto no terminal, digitando select * from Conta;. Como será que isto ocorre? A JPA conseguiu sincronizar os dados da Conta com os do registro do banco de dados.

Isto acontece porque o método find() nos devolve uma instância de Conta considerado como estado Managed (gerenciado), estado da entidade da JPA cujos dados são automaticamente sincronizados com o banco de dados.

Caso quiséssemos alterar a agência, por exemplo, por meio de conta.setAgencia("456");, repare que apenas um update será feito, pois será verificado que o nome continua sendo "João", alterando-se apenas a agência.

Se repetirmos a mesma execução, o Hibernate realiza apenas a busca, sem nenhum update, pois nenhuma alteração foi feita, ou seja, a conta em memória é igual à conta no banco de dados.

Com o JPA, o objetivo é sempre trazer os objetos para o estado Managed, já que assim eles serão gerenciados e automaticamente sincronizados com o banco.

Sobre o estado Managed podemos afirmar que:
A característica do estado Managed é a sincronização automática.


Criamos uma conta da seguinte forma:
Conta conta = new Conta();
conta.setTitular("Leonardo");
conta.setBanco("Caixa Econômica");
conta.setAgencia("123");
conta.setNumero("456");

EntityManager em = new JPAUtil().getEntityManager();

em.getTransaction().begin();
em.persist(conta);
em.getTransaction().commit();

em.close();
Depois chamamos o método persist() para que a conta fosse inserida ao banco de dados. Sem isto, ela não seria salva e sumiria completamente caso a aplicação terminasse. Esse estado é chamado Transiente(ou Transient) e a tarefa do método persist() é justamente alterar esse estado para Gerenciado(Managed).



Ou seja, a nova conta passou para Managed só após ter chamado o método persist() e, a partir de agora, o JPA vai sincronizar todas as suas alterações. Podemos provar isso alterando o nome do banco, após ter chamado o persist():

em.persist(conta);

conta.setBanco("Bradesco");
Ao executar esse código, percebemos na view console do Eclipse que foi gerado automaticamente um insert e depois um update para a conta. Novamente o JPA cuidou da entidade e sincronizou todas as suas alterações. É importante frisar que o estado Managed da entidade dura enquanto o EntityManager estiver aberto.


<h2>Conhecendo o estado Detached</h2>
Reforçando que o estado Managed só irá durar enquanto não fecharmos o EntityManager - a partir daí, nenhuma das entidades segue neste estado. Imagine que busquemos uma conta, fechando a entidade em seguida, após o término de uma requisição, por exemplo. Abriremos outro EntityManager (em2) e alteramos o titular da conta. Queremos que esta conta seja sincronizada - mas como transformaremos algo em Managed? Pelo persist()!
EntityManager em2 = new JPAUtil().getEntityManager();
em2.getTransaction().begin();

conta.setTitular("Leonardo");
em2.persist(conta);

em2.getTransaction().commit();
em2.close();
Executando-se o código acima, teremos uma exceção: org.hibernate.PersistentObjectException. O que significa isto? Vamos repassar pelo código. Sabemos que a alteração por si só não é o suficiente neste caso. Teremos que transformar esta conta em Managed para que a sincronização ocorra de forma automática.

Neste momento, qual o estado desta conta? Será que é Transient? Utilizamos o find pelo id``1, portanto sabemos que a conta tem este ID. Ela continua existindo no banco? Sim. Porém, ela não é Transient, pois sua característica é justamente nunca ter sido gerenciada pelo banco.

Significa que quando "matamos" a aplicação, a entidade "morre" junto. Esta entidade, então, é Managed? Não, pois o EntityManager que buscou esta entidade já foi fechado. Qual é o estado em que ela se encontra, então?

Trata-se do estado Detached, em que a conta não é mais gerenciada pelo JPA. Há uma representação sua no banco, no entanto a sincronização automática não está ativada, pois o gerenciamento não ocorre mais. Nosso objetivo, então, é transformar esta conta de Detached para Managed para que a sincronização aconteça.

Fazemos isto com o método merge(), responsável por transformar uma conta que já foi gerenciada pelo JPA em um momento anterior, transformando-a em Managed novamente, permitindo a sincronização com o banco de dados:

EntityManager em2 = new JPAUtil().getEntityManager();
em2.getTransaction().begin();

conta.setTitular("Leonardo");
em2.merge(conta);

em2.getTransaction().commit();
em2.close();
Testando a aplicação, veremos que foi realizado um update, justamente para trocar o titular da conta:

Hibernate: 

    update
        Conta 
    set
        agencia=?,
        banco=?,
        numero=?,
        titular=? 
    where
        id=?


<h2>Conhecendo o estado Removed</h2>
Enfatizando que a tarefa do desenvolvedor ao trabalhar com JPA é transformar o status das entidades em Managed, para conseguir utilizar a sincronização, já conhecemos o método persist(), capaz de transformar uma entidade Transient em Managed, e agora aprendemos sobre o método merge(), para uma entidade que já foi Managed, é Detached, e é transformada em Managed de novo.
Falta entendermos como remover uma entidade. Em nosso banco, há uma conta com Id 1: conta.setId(1);. Vamos removê-la utilizando remove() em vez de persist():

Conta conta = new Conta();
conta.setId(1);
// outros setters

EntityManager em = new JPAUtil().getEntityManager();
em.getTransaction().begin();

em.remove(conta);

em.getTransaction().commit();
em.close();
Ao executá-lo, para nossa surpresa, recebemos uma exceção:

Exception in thread "main" java.lang.IllegalArgumentException: Removing a detached instance br.com.caelum.financas.modelo.Conta#1

A mensagem indica que a conta está desatachada (Detached) e, sendo assim, não pode ser removida. Para remover qualquer entidade, ela precisa estar gerenciada (Managed). Então vamos carregar a conta antes com o método find() deletando-a em seguida.

em.getTransaction().begin();

conta = em.find(Conta.class, 1);

em.remove(conta);
Após a chamada de remove(), o objeto não tem mais representação no banco, já que foi removido. Porém, ele continua existindo na memória, em um estado conhecido como Removed.



Método Persist = transforma uma entidade transiet em managed
Método Merge = Transforma uma entidade detached (que já foi managed) em managed



<h2>Quais são os estados?</h2>
Em quais estados se encontram as entidades nos momentos onde se encontram os comentários?
1)

Conta conta = new Conta();
conta.setId(1);
conta.setTitular("Leonardo");
// aqui
entityManager.persist(conta);
2)

Conta conta = new Conta();
conta.setTitular("Leonardo");

// aqui

entityManager.persist(conta);
3)

Conta conta = em.find(Conta.class, 1);

em.close();

EntityManager em2 = ...

// aqui

conta = em2.find(Conta.class, 1);

conta.setTitular("Daniel");

1) A conta possui id. Ao persistir será lançado um PersistentObjectException: detached passed to persist;
2) A conta acabou de ser criada;
3) O EntityManager que criou a conta já foi fechado.

<h2>JPAUtil?</h2>
No vídeo anterior alteramos nosso código e criamos uma classe chamada JPAUtil. A(s) vantagem(s) de obter um EntityManager a partir dela é:
Manter o código de criação do EntityManager isolado em uma classe especialista. Dessa forma podemos, na nossa aplicação, focar apenas em regras de negócio em vez de nos preocupar com a criação de um EntityManager toda vez que houver a necessidade de trabalhar com o banco.
 
Apesar de ainda precisarmos fechar o EntityManager, dessa forma cada classe será mais coesa tendo seu papel único na aplicação.


<h2>Fazendo analogias com os métodos do EntityManager</h2>
Algo muito comum no processo de aprendizagem é o uso de analogias, onde comparamos algo que estamos aprendendo com alguma outra coisa que já conhecemos.
Por exemplo:

persist() = insert
find() = select

merge() = update

remove() = delete

Por que essa não é recomendável pensarmos dessa maneira ao trabalhar com JPA?
É possível que a execução de algum desses métodos resulte em uma query diferente.
 
Quando trabalhamos com JPA cada método é responsável por trocar o estado da entidade onde as queries são consequências dessas trocas. Por exemplo: ao chamarmos o merge o hibernate também faz um select para verificar se a alteração é realmente necessária.


<h2>Ajude um aluno do Alura!</h2>
Um aluno da Alura, Leonardo, estava estudando JPA e não consegue implementar a remoção; abaixo, o código que ele mandou no fórum:
EntityManager em = Persistence.createEntityManagerFactory("financas").createEntityManager();

Conta conta = new Conta();
conta.setId(1);

em.getTransaction().begin();

em.remove(conta);

em.getTransaction().commit();

em.close();
Ele também recebeu uma exceção de que não se lembra o nome. Você consegue ajudar nosso aluno? Qual das mensagens abaixo você enviaria a ele?
Olá Leonardo, tudo bem?
Seu código está quase lá! No entanto, é necessário que a entidade esteja gerenciada para que a JPA consiga remover.
Em vez de criar a Conta e setar seu id para 1, tente buscar no banco usando o método em.find(), que devolverá uma conta gerenciada.
Abraços!
 
Para que a JPA consiga remover, é necessário que a entidade esteja Managed.


<h2>Finalizando com uma revisão</h2>

Nesta aula vimos os estados que uma entidade pode assumir no JPA, começando sempre pelo Transient, que é quando se faz new objeto (informações acerca da entidade), a ser transformado em Managed. Para isto, chamamos o método persist(), cuja finalidade é justamente realizar esta transformação.
Isto possibilita a sincronização automática com o banco de dados, que por sua vez torna possível o insert. Até quando dura este estado (Managed)? Enquanto não chamarmos em.close(), fechando-se o EntityManager. A partir daí, a entidade se torna Detached, com representação no banco de dados, porém não mais gerenciada pelo JPA.

Para transformar uma entidade Detached em Managed novamente, utilizamos o método merge(), a partir do qual o Hibernate faz o update caso haja alguma mudança na entidade, passando a ser Managed e tendo sincronização automática.

Por último, há o estado Removed, que significa que não há mais representação ou registro no banco, mas a entidade continua em memória.




--------------------------------------------------------------------------------------------------------------
<h1>Seção 04 - Mapeamento de relacionamentos entre entidades</h1>


<h2>Modelando a movimentação</h2>

Quando trabalhamos com contas e bancos, é bem comum realizarmos saques, transferências, pagamentos e afins. Chamamos estas operações de movimentações, que podem ser tanto de entrada quanto de saída. Começaremos a modelar esta movimentação em nosso sistema.

Para isto criaremos uma classe denominada Movimentacao no pacote br.com.caelum.financas.modelo. Ela será uma entidade do nosso sistema, portanto utilizaremos a anotação @Entity. Tratando-se de uma entidade, é muito comum que se possua um id, uma chave privada e primária.

Para que este id seja reconhecido pelo JPA, anotamos com @Id, e precisaremos informar também a estratégia de Auto Incremento, por meio de @GeneratedValue:

@Entity
public class Movimentacao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
}
Que atributos esta movimentação terá? Cada movimentação terá um valor, e seria legal se conseguíssemos ter uma descrição para saber com que exatamente gastamos ou recebemos dinheiro. Além disso, poderemos ter data e tipo (entrada ou saída).

private Integer id;

private valor;
private tipo;
private data;
private descricao;
Para modelar estes tipos, como valor é dinheiro, por exemplo, poderíamos usar Double. Porém ele não possui tanta precisão para lidar com números relacionados a dinheiro. Outra opção seria o BigDecimal.

Para tipo, podemos deixar String, mas esta acabaria não sendo a melhor opção, por precisarmos validá-lo o tempo todo caso decidamos deixar "Alura", "ENTRADA" ou "SAÍDA". Optaremos por int, em que 1 representa uma entrada e 0 uma saída.

O problema é que caso queiramos utilizar 200, por exemplo, esta especificidade não funciona adequadamente. Para definirmos o tipo da movimentação de forma simples e consistente, sem a necessidade de validações e documentação, podemos utilizar um recurso muito interessante introduzido no Java 5, que nos permite criar objetos que representam constantes: as Enums.

Dessa forma, vamos criar uma Enum chamada TipoMovimentacao, que define duas constantes: ENTRADA e SAIDA.

public enum TipoMovimentacao {

   ENTRADA, SAIDA;
}
O código ficará assim, com ENTRADA podendo ser substituído por SAIDA:

private BigDecimal valor;
private TipoMovimentacao tipo = TipoMovimentacao.ENTRADA;
private data;
private descricao;
Como agora o atributo é uma Enum, precisaremos anotá-lo com @Enumerated. Além disso, queremos mapeá-lo no banco de dados como sendo do tipo String. Precisaremos então definir o parâmetro EnumType para STRING. Isso significa que no registro do banco ficará gravado o varchar: SAIDA ou ENTRADA.

@Entity
public class Movimentacao {

   // demais atributos omitidos

   @Enumerated(EnumType.STRING)
   private TipoMovimentacao tipoMovimentacao;

}
No Java, quando trabalhamos com datas, normalmente usamos o tipo Calendar. Mas como cada banco lida de formas diferentes com dados deste tipo, precisaremos indicar ao JPA a forma que queremos armazenar a data no banco.

Devemos então anotar o atributo com @Temporal, depois definir o parâmetro de precisão desejado (TemporalType). Aqui, temos três opções:

DATE: somente a data, sem a hora;
TIME: somente a hora, sem data;
TIMESTAMP: tanto data quanto hora.
No nosso caso deixaremos assim:

@Entity
public class Movimentacao {

   // demais atributos omitidos

   @Temporal(TemporalType.TIMESTAMP)
   private Calendar data;

}
Ainda falta informarmos a conta em que esta movimentação pertence e, para tal, criaremos o atributo private Conta conta;. Sabemos que o JPA sempre mapeará os tipos do mundo Java para os do seu banco. Em relação a Conta, o que será utilizado? String? varchar? int? Vamos ver a resposta para esta pergunta no próximo vídeo.


<h2>E o tipo da movimentação?</h2>

No vídeo foi mostrado que não é boa prática usar String e int para representar constantes. Por quê?

Para evitar um esforço maior em validar o valor entrado pelo usuário.
Ao usar String ou int precisaremos estabelecer valores padrões para entrada ou saida. Além de verificar se o valor que o usuário forneceu é válido, precisamos deixar essa estratégia muito bem documentada para evitar problemas durante a manutenção.

<h2>Qual TemporalType usamos?</h2>
Ao realizar uma busca pelas movimentações do dia 24/02 vimos que as datas estão da seguinte forma:
+------------+
| data       |
+------------+
| 2017-02-24 |
| 2017-02-24 |
| 2017-02-24 |
| 2017-02-24 |
| 2017-02-24 |
| 2017-02-24 |
+------------+
Qual TemporalType foi usado nesse caso?

Podemos reparar que em todos os registros foram retornados apenas a data (2017-02-24). Dessa forma, sabemos que o TemporalType é DATE.

<h2>Modelando e testando o relacionamento</h2>

E aí, pensaram se nossa conta será do tipo String ou int? Faz sentido ser de algum destes dois tipos? O que queremos, na verdade, é mapear um relacionamento entre as duas entidades, Movimentacao e Conta. Basta indicarmos a cardinalidade deste relacionamento para o JPA.
Neste caso, várias movimentações podem pertencer a uma única conta. Ou seja, a cardinalidade é de muitos para um e, para indicarmos este tipo de relacionamento no atributo Conta, precisaremos utilizar @ManyToOne ("muitas movimentações para uma conta"):

@ManyToOne
private Conta conta;
Criaremos getters e setters também, não esquecendo de colocar a entidade Movimentacao no arquivo de configuração do JPA persistence.xml, incluindo ali a linha <class>br.com.caelum.financas.modelo.Movimentacao</class>.

Vamos criar uma classe por motivos de teste, no pacote br.com.caelum.financas.teste, a qual denominaremos TesteJPARelacionamento. Esta classe precisará de um método main, e copiaremos de TesteConta a criação do EntityManager.

Queremos criar uma movimentação para associá-la à conta, portanto setaremos algumas informações:

public static void main(String[] args) {

    Conta conta = new Conta();
    conta.setAgencia("0102");
    conta.setBanco("Itau");
    conta.setNumero("1234");
    conta.setTitular("Leonardo");

    Movimentacao movimentacao = new Movimentacao();
    movimentacao.setData(Calendar.getInstance());
    movimentacao.setDescricao("Churrascaria");
    movimentacao.setTipo(TipoMovimentacao.SAIDA);
    movimentacao.setValor(new BigDecimal("200.0"));

    movimentacao.setConta(conta);

    EntityManager em = new JPAUtil().getEntityManager();
    em.getTransaction().begin();

    em.persist(movimentacao);

    em.getTransaction().commit();
    em.close();
}
Vamos testar para verificar seu funcionamento. Recebemos uma exceção, que nos informa que o objeto referencia uma instância Transient, então, quando persistimos movimentacao, ao setarmos Conta, este é seu estado. Será que faz sentido persistirmos uma conta em Transient?

A chave estrangeira que você usa não é a chave primária de um registro pré existente? Sim, faz sentido. Como é que vamos inserir uma movimentação com uma conta que ainda não existe no banco? Qual o valor que deverá ser colocado como chave estrangeira, se o registro ainda não existe?

Para resolvermos isto, precisaremos primeiramente persistir conta para que ela exista no banco (tenha representação) a partir do id. Assim, conseguimos persistir a movimentacao:

em.persist(conta);
em.persist(movimentacao);
Vamos testar para ver se conseguimos inserir a movimentação? Veremos que é realizado o insert da conta para depois haver o mesmo para Movimentacao. No banco de dados, verificaremos isto pelo comando use financas; e show tables; e, depois, desc Movimentacao;.

Temos conta_id, que é a chave estrangeira da entidade Conta. Vamos solicitar ao terminal para que se mostrem os resultados a partir de select * from Movimentacao;. Nossa Movimentacao é mostrada (Churrascaria), e veremos que seu id é 7. Confirmaremos esta informação digitando select * from Conta;.



<h2>O que há de errado?</h2>

O que há de errado com o código abaixo?
public class TestaRelacionamento {

   public static void main(String[] args) {

      Conta conta = new Conta();
      conta.setTitular("Ana Maria");
      conta.setBanco("Itau");
      conta.setNumero("54321");
      conta.setAgencia("111");

      Movimentacao movimentacao = new Movimentacao();
      movimentacao.setData(Calendar.getInstance());
      movimentacao.setDescricao("Conta de luz");
      movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
      movimentacao.setValor(new BigDecimal("123.9"));

      movimentacao.setConta(conta);

      EntityManager manager = new JPAUtil().getEntityManager();
      manager.getTransaction().begin();

      manager.persist(movimentacao);

      manager.getTransaction().commit();
      manager.close();
   }
}


O objeto movimentacao carrega um relacionamento com uma conta Transient.
Para persistir uma entidade, todos seus relacionamentos devem ser Managed, ou no mínimo Detached.
Como estamos tentando persistir uma movimentação associada à uma conta que não existe, será lançado uma TransientPropertyValueException já que para persistir uma entidade, todos seus relacionamentos devem ser Managed, ou no mínimo Detached, contendo um id existente.
Para solucionar precisamos persistir a conta antes da movimentação:

...

EntityManager manager = new JPAUtil().getEntityManager();
manager.getTransaction().begin();

manager.persist(conta);
manager.persist(movimentacao);

manager.getTransaction().commit();
manager.close();

...
ou definir a conta com um id existente:

EntityManager manager = new JPAUtil().getEntityManager();
manager.getTransaction().begin();

// id de alguma entidade conta existente na base de dados
conta.setId(1);
movimentacao.setConta(conta);

manager.persist(movimentacao);

manager.getTransaction().commit();
manager.close();

<h2>Permissões aos usuários</h2>
Imagine o seguinte cenário:
// Usuario.java

@Entity
public class Usuario { 

       @OneToMany
       private List<Permissao> permissoes;

}
// Permissao.java

@Entity
public class Permissao { 
}
Na nossa aplicação temos três usuários: Nico, Douglas e Rômulo que desejam ter as seguintes permissões respectivamente: admin, user, user.

Levando em consideração que iremos cadastrar os usuários sequencialmente, marque a alternativa que melhor descreve o cadastro:
Nico será cadastrado com permissão de admin e Douglas com permissão de user. Rômulo não irá conseguir usar a permissão de user.

A relação entre Usuario @OneToMany Permissao. O que significa que apenas um usuário poderá ter aquelas permissões e portanto, apenas Douglas, que foi cadastrado primeiro, conseguirá permissão de user.


-----------------------------------------------------------------------------------------------
<h1>Seção 05 - Muitos para muitos e um para um</h1>

<h2>Muitos para muitos e Um para Um</h2>

Seria interessante conseguirmos separar as movimentações através de alguma categoria, para que no futuro consigamos buscar as movimentações relacionadas a Viagem ou Lazer, por exemplo. O que faremos é criar uma nova classe denominada Categoria, também no pacote de modelo:

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    // getters e setters omitidos
}
Além disso podemos agregar mais informações à respeito do titular, em vez de salvar apenas seu nome. Vamos criar mais uma classe para representar este Cliente:

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String profissao;
    private String endereco;
Agora que as entidades foram criadas, não podemos esquecer de associá-las com a movimentação, usando os relacionamentos. Vamos fazer com que a Movimentacao conheça sua Categoria. Em Movimentacao.java, teremos:

@Entity
public class Movimentacao {

    // ...

    private Categoria categoria;

    // ...
}
Mas será que podemos ter apenas uma categoria por movimentação? Na verdade, poderíamos ter uma movimentação relacionada a viagem e a negócios e, neste caso, teremos uma movimentação com duas categorias. Assim, um atributo Categoria na classe Movimentacao não parece fazer muito sentido.

Se queremos ter mais de uma Categoria associada a uma Movimentacao geralmente utilizamos as listas da API de Collections, e pode-se dizer que queremos na verdade ter uma List<Categoria>:

@Entity
public class Movimentacao {

    // ...

    private List<Categoria> categoria;

    // ...
}
Qual será a cardinalidade desse relacionamento? Para uma movimentação, podemos ter diversas categorias e portanto, utilizaremos a anotação @OneToMany.

Será que isso faz sentido? Ao criarmos uma movimentação com a categoria Viagem apenas ela poderá ter essa categoria? O que queremos é que várias movimentações possam ter esta categoria. Corrigindo nossa frase, teremos:

Para várias movimentações, podemos ter diversas categorias.
Conseguiremos esse efeito utilizando a anotação @ManyToMany:

@Entity
public class Movimentacao {

    // ...

    @ManyToMany
    private List<Categoria> categoria;

    // ...
}
Agora falta dizer ao cliente qual é a sua conta, e para isso, precisaremos criar um atributo do tipo Conta na classe Cliente:

@Entity
public class Cliente {

    // ...

    private Conta conta;

    // ...
}
Relembrando que, sendo um relacionamento entre duas entidades, precisaremos informar ao JPA a cardinalidade deste relacionamento. Em nosso caso, uma conta terá apenas um titular, e isso significa que uma conta não pode pertencer a mais de um cliente. Portanto, o relacionamento entre Cliente e Conta é de "um para um". Usaremos a anotação @OneToOne para representá-lo:

@Entity
public class Cliente {

    // ...
    @OneToOne
    private Conta conta;

    // ...
}


<h2>Testando os relacionamentos entre Categoria, Conta e Movimentação (testando-categoria-e-cliente)</h2>

Vamos criar uma classe chamada TesteMovimentacoesComCategoria no pacote br.com.caelum.financas.teste para testarmos os relacionamentos. Criaremos duas categorias também, passando seus nomes através do construtor.
public class TesteMovimentacoesComCategoria {

    public static void main(String[] args) { 

        Categoria categoria1 = new Categoria();
        categoria1.setNome("viagem");

        Categoria categoria2 = new Categoria();
        categoria2.setNome("negócios");


    }
}
O problema é que a classe Categoria não possui nenhum construtor que receba um nome, o que significa que precisaremos criá-lo. Para isso, abriremos a classe Categoria pressionando "Ctrl + 3" e digitando GCUF (Generate Constructor using Fields). Na janela que se abriu, selecionaremos o campo nome e clicaremos em "OK".

@Entity
public Categoria {

    // ...

    public Categoria(String nome) {
        super();
        this.nome = nome;
    }

    // ...

}
Além disso, ainda precisamos ter na nossa classe Categoria um construtor padrão a ser utilizado pelo Hibernate. Mas vamos anotá-lo com @Deprecated para indicar que esse construtor não deveria ser usado no nosso projeto.

@Entity
public class Categoria {

    // ...

    @Deprecated
    public Categoria() { 
    }

    public Categoria(String nome) {
        super();
        this.nome = nome;
    }

    // ...

}
Em TesteMovimentacoesComCategoria, vamos criar as movimentações:

public class TesteMovimentacoesComCategoria {

    public static void main(String[] args) { 

        Categoria categoria1 = new Categoria("Viagem");
        Categoria categoria2 = new Categoria("Negocios");

        Movimentacao movimentacao1 = new Movimentacao();
        movimentacao1.setData(Calendar.getInstance()); //hoje
        movimentacao1.setDescricao("Viagem à SP");
        movimentacao1.setTipo(TipoMovimentacao.SAIDA);
        movimentacao1.setValor(new BigDecimal("100.0"));

    }
}
Passaremos as categorias para a classe Movimentacao, que ainda não possui o método setCategoria - vamos criá-lo:

public class Movimentacao { 

    // ...

    public void setCategorias(List<Categoria> categoria) { 
        this.categoria = categoria;
    }

    // ...
}
Feito isto, passaremos uma lista de Categoria para a movimentação. Para criarmos esta lista usaremos a classe java.util.Arrays:

public class TesteMovimentacoesComCategoria {

    public static void main(String[] args) { 

        Categoria categoria1 = new Categoria("Viagem");
        Categoria categoria2 = new Categoria("Negócios");

        Movimentacao movimentacao1 = new Movimentacao();
        movimentacao1.setData(Calendar.getInstance());
        movimentacao1.setDescricao("Viagem à SP");
        movimentacao1.setTipo(TipoMovimentacao.SAIDA);
        movimentacao1.setValor(new BigDecimal("100.0"));
        movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));

    }
}
Vamos criar uma movimentacao2:

public class TesteMovimentacoesComCategoria {

    public static void main(String[] args) { 

        Categoria categoria1 = new Categoria("Viagem");
        Categoria categoria2 = new Categoria("Negócios");

        Movimentacao movimentacao1 = new Movimentacao();
        movimentacao1.setData(Calendar.getInstance()); // hoje
        movimentacao1.setDescricao("Viagem à SP");
        movimentacao1.setTipo(TipoMovimentacao.SAIDA);
        movimentacao1.setValor(new BigDecimal("100.0"));
        movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));

        Movimentacao movimentacao2 = new Movimentacao();
        movimentacao2.setData(Calendar.getInstance()); // hoje
        movimentacao2.setDescricao("Viagem ao RJ");
        movimentacao2.setTipo(TipoMovimentacao.SAIDA);
        movimentacao2.setValor(new BigDecimal("300.0"));
        movimentacao2.setCategorias(Arrays.asList(categoria1, categoria2));

    }
}
Antes de inserir as categorias e as contas, é preciso uma conta para associá-las com as movimentações. Vamos criar uma conta e setar o id para 2:

public class TesteMovimentacoesComCategoria {

    public static void main(String[] args) { 

        Categoria categoria1 = new Categoria("Viagem");
        Categoria categoria2 = new Categoria("Negócios");

        Conta conta = new Conta();
        conta.setId(2); 

        Movimentacao movimentacao1 = new Movimentacao();
        movimentacao1.setData(Calendar.getInstance()); // hoje
        movimentacao1.setDescricao("Viagem à SP");
        movimentacao1.setTipo(TipoMovimentacao.SAIDA);
        movimentacao1.setValor(new BigDecimal("100.0"));
        movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));

        movimentacao1.setConta(conta);

        Movimentacao movimentacao2 = new Movimentacao();
        movimentacao2.setData(Calendar.getInstance()); // hoje
        movimentacao2.setDescricao("Viagem ao RJ");
        movimentacao2.setTipo(TipoMovimentacao.SAIDA);
        movimentacao2.setValor(new BigDecimal("300.0"));
        movimentacao2.setCategorias(Arrays.asList(categoria1, categoria2));

        movimentacao2.setConta(conta);

    }
}
Pronto! Agora poderemos trabalhar com JPA efetivamente e, para isso, vamos criar o EntityManager. Feito isso, já podemos passar as movimentações para Managed chamando o método persist:

// TesteMovimentacoesComCategoria.java

public class TesteMovimentacoesComCategoria {

    public static void main(String[] args) { 

        Categoria categoria1 = new Categoria("Viagem");
        Categoria categoria2 = new Categoria("Negócios");

        Conta conta = new Conta();
        conta.setId(2); 

        Movimentacao movimentacao1 = new Movimentacao();
        movimentacao1.setData(Calendar.getInstance()); // hoje
        movimentacao1.setDescricao("Viagem à SP");
        movimentacao1.setTipo(TipoMovimentacao.SAIDA);
        movimentacao1.setValor(new BigDecimal("100.0"));
        movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));

        movimentacao1.setConta(conta);

        Movimentacao movimentacao2 = new Movimentacao();
        movimentacao2.setData(Calendar.getInstance()); // hoje
        movimentacao2.setDescricao("Viagem ao RJ");
        movimentacao2.setTipo(TipoMovimentacao.SAIDA);
        movimentacao2.setValor(new BigDecimal("300.0"));
        movimentacao2.setCategorias(Arrays.asList(categoria1, categoria2));

        movimentacao2.setConta(conta);

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        em.persist(movimentacao1);
        em.persist(movimentacao2);

        em.getTransaction().commit();    
        em.close();    

    }
}
Rodando-se o teste, na view console veremos que foi disparada a seguinte exceção:

Caused by: org.hibernate.TransientObjectException: object references an unsaved transient instance
Isto acontece porque as nossas movimentações referenciam algumas categorias que estão no estado Transient. Precisamos trazê-las para Managed, e faremos isso chamando o método persist antes da persistência das movimentações.

public class TesteMovimentacoesComCategoria {

    public static void main(String[] args) { 

        Categoria categoria1 = new Categoria("Viagem");
        Categoria categoria2 = new Categoria("Negócios");

        Conta conta = new Conta();
        conta.setId(2); 

        // códigos referentes a movimentacao1 e movimentacao2 omitidos

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        em.persist(categoria1); // Agora a 'categoria1' é Managed
        em.persist(categoria2); // Agora a 'categoria2' é Managed

        em.persist(movimentacao1);
        em.persist(movimentacao2);

        em.getTransaction().commit();    
        em.close();    

    }
}
Ao rodar esse teste percebemos que se faz o insert de Movimentacao_Categoria. Vamos dar uma olhada no nosso MySQL pra confirmar que está tudo certo, com show tables;. Reparamos que as tabelas foram criadas, e verificaremos se os dados da tabela Movimentacao estão corretos com o comando select * from Movimentacao. Nosso próximo passo será verificar se os dados das categorias estão corretos, com select * from Categoria.

Parece que tudo funciona como esperávamos. Mas como será que é implementado o relacionamento entre as movimentações e a categoria? Quando usamos show tables; no banco, vimos que há uma tabela extra chamada Movimentacao_Categoria. Com select * from Movimentacao_Categoria;, teremos:

+-----------------+--------------+
| Movimentacao_id | categoria_id |
+-----------------+--------------+
|               6 |            1 |
|               6 |            2 |
|               7 |            1 |
|               7 |            2 |
+-----------------+--------------+
4 rows in set (0,00 sec)
Repare que é uma tabela que guarda as chaves estrangeiras das duas tabelas. A movimentação que possui o id 6 tem duas categorias, da mesma forma que a movimentação de id 7.

Esta tabela, com a função de relacionar duas entidades, recebe um nome especial do JPA: Tabela de Relacionamento. Ela serve para relacionamentos Many to many, criada para estas associações, já que uma movimentação pode ter diversas categorias, e estas podem pertencer a diversas movimentações.


<h2>Relacionamento @ManyToMany</h2>

Quando criamos um relacionamento @ManyToMany a JPA/Hibernate irá mapeá-lo para tabelas no mundo relacional. Assinale a alternativa que descreve corretamente a forma que ela fará esse mapeamento.
Criando uma tabela de relacionamento.
 
Será criada uma tabela de relacionamento para relacionar as duas tabelas.

<h2>Testando relacionamento entre Conta e Cliente</h2>
Agora criaremos uma classe chamada TestaContaCliente no pacote br.com.caelum.financas.teste, para testarmos o relacionamento entre as entidades Conta e Cliente. Nessa classe vamos criar um cliente e passar suas informações:
public class TestaContaCliente {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        cliente.setNome("Leonardo");
        cliente.setEndereco("Rua Fulano, 123");
        cliente.setProfissao("Professor");

    }
}
Para informarmos a conta deste Cliente, criaremos também uma nova instância de Conta setando o id para 2 no cliente, lembrando que esta conta se encontra no estado Detached por já possuir um id.

public class TestaContaCliente {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        cliente.setNome("Leonardo");
        cliente.setEndereco("Rua Fulano, 123");
        cliente.setProfissao("Professor");

        Conta conta = new Conta();
        conta.setId(2);

        cliente.setConta(conta);

    }
}
Para persistirmos o cliente, utilizaremos EntityManager e chamaremos o método persist. Em seguida, rodaremos o teste.

public class TestaContaCliente {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        cliente.setNome("Leonardo");
        cliente.setEndereco("Rua Fulano, 123");
        cliente.setProfissao("Professor");

        Conta conta = new Conta();
        conta.setId(2);

        cliente.setConta(conta);

        EntityManager em = new JPAUtil().getTransaction();
        em.getTransaction().begin();

        em.persist(cliente);

        em.getTransaction().commit();        


    }
}
Parece que houve insert normalmente, vamos confirmar se o cliente foi inserido no banco pelo comando select * from Cliente;. Obteremos:

+-----+-----------------+----------+-----------+----------+
|  id | endereco        | nome     | profissao | conta_id |
+-----+-----------------+----------+-----------+----------+
|   1 | Rua Fulano, 123 | Leonardo | Professor |        2 |
+-----+-----------------+----------+-----------+----------+
1 row in set (0,00 sec)
Ótimo, nosso cliente realmente está no banco! Mas será que o teste está correto? Só para lembrarmos, o relacionamento entre Conta e Cliente é @OneToOne. Isso significa que apenas um cliente pode ser dono de uma conta específica.

Vamos verificar se isso é garantido criando uma nova instância de cliente em nosso teste, associando-o à mesma conta do primeiro cliente:

public class TestaContaCliente {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        cliente.setNome("Leonardo");
        cliente.setEndereco("Rua Fulano, 123");
        cliente.setProfissao("Professor");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Douglas");
        cliente2.setEndereco("Rua Fulano, 234");
        cliente2.setProfissao("Professor");

        Conta conta = new Conta();
        conta.setId(2);

        cliente.setConta(conta);
        cliente2.setConta(conta);

        EntityManager em = new JPAUtil().getTransaction();
        em.getTransaction().begin();

        em.persist(cliente);
        em.persist(cliente2);

        em.getTransaction().commit();        


    }
}
Feito o teste, veremos que os dois clientes foram inseridos com sucesso - no entanto, contando com o cliente que tínhamos anteriormente, temos três associados a uma mesma conta:

+-----+-----------------+----------+-----------+----------+
|  id | endereco        | nome     | profissao | conta_id |
+-----+-----------------+----------+-----------+----------+
|   1 | Rua Fulano, 123 | Leonardo | Professor |        2 |
|   2 | Rua Fulano, 123 | Leonardo | Professor |        2 |
|   3 | Rua Fulano, 234 | Douglas  | Professor |        2 |
+-----+-----------------+----------+-----------+----------+
3 rows in set (0,00 sec)
Com certeza não é o que queremos, já que o relacionamento é "one to one". A anotação @OneToOne por padrão não nos garante essa restrição, portanto é necessário adicionarmos a anotação @JoinColumn(unique=true):

@Entity
public class Cliente {

    // ...

    @JoinColumn(unique=true)
    @OneToOne
    private Conta conta;

    // ...
}
Rodando o teste novamente, perceberemos que ainda não resolvemos o problema. Isso acontece porque a anotação @JoinColumn irá adicionar a constraint no momento da criação do schema, quando se criam as tabelas. Quer dizer que precisaremos criar novamente as tabelas para vê-las funcionando.

Removeremos o banco financas, recriando-o em seguida:

drop database financas;
create database financas;
É preciso popular todas as contas novamente, e isto é possível ao executarmos a classe PopulaConta.

Rodando o teste novamente, veremos que, à inserção da segunda conta, receberemos uma exceção dizendo que a chave 2 foi duplicada. Como isto está em uma transação,nada foi inserido, dado o erro.

A partir do comando select * from Cliente; no terminal, comprovamos que não há nada na tabela Cliente.

Vamos então inserir um cliente simplesmente comentando o código em.persist(cliente2); e conferindo isto no terminal depois. O cliente aparece com conta de id 2. Rodamos a classe novamente, e já estamos inserindo o cliente, não precisaremos "descomentar" esta linha. Persistimos a classe mais uma vez, e um erro foi lançado, indicando que já existe um cliente, com conta de id 2.

Neste vídeo vimos os relacionamentos existentes entre as entidades do nosso banco de dados, cujos problemas são resolvidos pelo JPA de forma bastante simples, adicionando-se uma anotação de cardinalidade na entidade para a criação de uma chave estrangeira, ou tabela de relacionamento, por exemplo.

Os relacionamentos vistos aqui foram entre movimentação e conta, já que uma pertence a outra, sendo que uma conta pode possuir várias movimentações também (@ManyToOne). @ManyToMany, por sua vez, indica que uma movimentação pode ter várias categorias, que podem fazer parte de outras movimentações. Estas categorias não se restringem a apenas uma movimentação.

Para modelarmos isto, o JPA criou a tabela de relacionamentos, que em nosso caso é denominada Movimentacao_Categoria, algo que segue o padrão de relacionamento_relacionado.

Também vimos o relacionamento entre conta e cliente; o cliente é dono de uma conta e, por padrão, a anotação @OneToOne não vai limitar o uso de mais de uma conta para um cliente, se utilizarmos a anotação @JoinColumn passando (unique=true). No entanto, isto só funciona quando se cria o schema.

Inclusive, a constraint é adicionada à nossa tabela, o que poderemos conferir por meio de desc Cliente; no terminal. Veremos que a chave (Key) aparece como UNI, de Unique.

<h2>Restrição no relacionamento @OneToOne</h2>

Por padrão quando temos um relacionamento @OneToOne ainda não obtemos a restrição que é esperada por um relacionamento @OneToOne. Para solucionar esse problema:
É necessário colocar a anotação @JoinColumn(unique=true) no relacionamento.
 
A anotação @JoinColumn só funciona na criação do Schema, portanto é necessário deletar o banco e criá-lo novamente.


<h2>Revisando relacionamentos</h2>

Nesta aula vimos que os problemas dos relacionamentos existentes entre as entidades do nosso banco de dados são resolvidos de forma simples pelo JPA. Basta adicionarmos anotações de cardinalidade nas entidades, seja no caso de criação de chave estrangeira ou tabela de relacionamentos.
Em nosso cenário, uma conta pode ter várias movimentações, e por isso representamos este relacionamento com a anotação @ManyToOne na classe Movimentacao:

@Entity
public class Movimentacao {
   // ...

   @ManyToOne
   private Conta conta;

   // ...
}
Também criamos as categorias para as nossas movimentações, para conseguirmos catalogar e pesquisar as movimentações por alguma categoria específica. Para isto, usamos o relacionamento muitos para muitos através da anotação @ManyToMany.

@Entity
public class Movimentacao {
   // ...

   @ManyToOne
   private Conta conta;

   @ManyToMany
   private List<Categoria> categorias;

   // ...
}
Dessa forma o JPA cria uma tabela de relacionamento para relacionar a chave primária das duas entidades, Movimentacao e Categoria.

Para armazenar mais informações sobre o titular da conta, criamos a entidade Cliente, que se relaciona com a conta. Lembrando que uma conta só pode ter apenas um cliente, portanto usamos o relacionamento um para um.

@Entity
public class Cliente {
   // ...

   @OneToOne
   private Conta conta;

   // ...
}
Há um pequeno detalhe aqui: por padrão, a anotação @OneToOne não adiciona a restrição (constraint) de que a chave deverá ser única. Podemos configurar esse comportamento com a anotação @JoinColumn:

@Entity
public class Cliente {
   // ...
   @JoinColumn(unique=true) 
   @OneToOne
   private Conta conta;

   // ...
}



--------------------------------------------------------------------------------------------------------------
<h1>Seção 06 - Pesquisas orientadas a objeto com JPQL</h1>

<h2>Conhecendo a JPQL</h2>

Por padrão, todo banco de dados relacional aceita a Structured Query Language (SQL) para realizar consultas e manipulações em modelos relacionais, montando-se uma query e disparando-a contra o banco de dados para a obtenção de algum resultado - uma inserção, um relatório, ou algo do tipo.

Ao usarmos Java, utilizamos uma API chamada JDBC, o qual permite que, através de um driver, nos comuniquemos com o banco de dados. Nas queries que tínhamos no projeto contas-jdbc, havia aquelas de inserção, update, relatórios, select, entre outras. Todas estas queries têm algo em comum, e você, desenvolvedor, precisa conhecer detalhes de como montar a estrutura de suas tabelas, os nomes das colunas, seus relacionamentos, pois serão necessários à montagem da query.

Quando adotamos o JPA, queremos nos distanciar ao máximo do modelo relacional, focar exclusivamente no modelo orientado a objetos, seus estados e interações. Portanto, é interessante pararmos de utilizar o SQL para estas consultas ou alterações, optando por uma linguagem específica do JPA bem parecida, o JPQL (Java Persistence Query Language), que é o que utilizaremos para manipularmos nossos dados de forma orientada a objetos.

Vamos fazer um pequeno teste para colocar isso em prática? Criaremos uma classe denominada TesteJPQL, e também os métodos main e EntityManager. Precisaremos de uma query para trazer as movimentações, que chamaremos de m, da entidade Movimentacao, em que um id da conta é igual a 2.

public static void main(String[] args) {
    EntityManager em = new JPAUtil().getEntityManager();
    em.getTransaction().begin();

    String query = "select m from Movimentacao m where conta_id = 2";

    em.getTransaction().commit();
    em.close();
}
Como não estamos trabalhando com SQL aqui, e sim com JPQL, conta_id se refere a uma das colunas da tabela. Porém, neste caso, não trabalharemos mais com colunas e tabelas, e sim com atributos e classes. Queremos acessar o id da conta, em Movimentacao. Queremos que quando o id for 2, a Movimentacao seja retornada.

Substituiremos a linha String query = "select m from Movimentacao m where conta_id = 2" por String query = "select m from Movimentacao m where m.conta.id = 2";. Assim, selecionaremos as movimentações (colocamos m como alias para facilitar) cuja conta possui id 2.

Encapsularemos esta String com um objeto chamado Query. Lembrando que não poderemos ter dois objetos com este nome, o código ficará assim:

public static void main(String[] args) {
    EntityManager em = new JPAUtil().getEntityManager();
    em.getTransaction().begin();

    String jpql = "select m from Movimentacao m where m.conta.id = 2";
    Query query = em.createQuery(jpql);

    List<Movimentacao> resultados = query.getResultList(); 

    em.getTransaction().commit();
    em.close();
}
Precisaremos percorrer esta lista que acabamos de criar:

public static void main(String[] args) {
    EntityManager em = new JPAUtil().getEntityManager();
    em.getTransaction().begin();

    String jpql = "select m from Movimentacao m where m.conta.id = 2";
    Query query = em.createQuery(jpql);

    List<Movimentacao> resultados = query.getResultList();

    for (Movimentacao movimentacao : resultados) {
        System.out.println("Descricao: " + movimentacao.getDescricao());
        System.out.println("Conta.id: " + movimentacao.getConta().getId());
    } 

    em.getTransaction().commit();
    em.close();
}
Rodaremos a aplicação e verificamos que ela funciona como esperado; no entanto, ainda há resquícios de modelo relacional. Reparem que ainda precisamos comparar os ids, e seria legal se conseguíssemos comparar os objetos, a conta em si, em vez dos valores das colunas da nossa tabela. Para tal, poderemos deixar uma das linhas assim:

String jpql = "select m from Movimentacao m where m.conta = conta";
Vamos criar uma conta com id 2 para fazermos a comparação na query e trazer os mesmos resultados.

public static void main(String[] args) {
    EntityManager em = new JPAUtil().getEntityManager();
    em.getTransaction().begin();

    Conta conta = new Conta();
    conta.setId(2);

    String jpql = "select m from Movimentacao m where m.conta.id = 2";
    Query query = em.createQuery(jpql);

    List<Movimentacao> resultados = query.getResultList();

    for (Movimentacao movimentacao : resultados) {
        System.out.println("Descricao: " + movimentacao.getDescricao());
        System.out.println("Conta.id: " + movimentacao.getConta().getId());
    } 

    em.getTransaction().commit();
    em.close();
}
Mas como passamos a conta nova ao JPQL? É para isto que utilizamos os parâmetros nomeados (Named Parameter):

String jpql = "select m from Movimentacao m where m.conta = :pConta";
Nossa conta será igual ao valor deste parâmetro. Agora precisaremos setar o valor do parâmetro propriamente dito:

String jpql = "select m from Movimentacao m where m.conta = :pConta";

Query query = em.createQuery(jpql);
query.setParameter("pConta", conta);
Vamos testar? Teoricamente, as movimentações devem ser as mesmas, e é o que ocorre. E se quisermos ordenar as movimentações por valor, em ordem decrescente? Adicionaremos mais código ao query:

String jpql = "select m from Movimentacao m where m.conta = :pConta" + " order by m.valor desc";

Query query = em.createQuery(jpql);
query.setParameter("pConta", conta);
Rodando esta query para verificar seu funcionamento. No caso de querermos adicionar as movimentações de saída, acrescentamos o filtro necessário:

String jpql = "select m from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo" + " order by m.valor desc";

Query query = em.createQuery(jpql);
query.setParameter("pConta", conta);
query.setParameter("pTipo", TipoMovimentacao.SAIDA);
Feito isto, vamos rodá-la mais uma vez. As viagens ao RJ e à SP aparecem conforme esperado. E se trocarmos SAIDA por ENTRADA? Nada deverá ser mostrado, já que não há nenhuma movimentação de entrada no nosso banco, certo? Sim! A consulta foi realizada e nenhum resultado foi mostrado.

Para testarmos o código, criaremos uma movimentação do tipo ENTRADA. E no terminal, digitaremos:

insert into Movimentacao(descricao, tipo, conta_id) values("outra viagem", ENTRADA, 2);
Vamos consultar a conta para ver se esta movimentação foi incluída, por meio do comando select * from Movimentacao:

+----+---------------------+--------------+---------+--------+-----------+
| id | data                | descricao    | tipo    | valor  | conta_id  |
+----+---------------------+--------------+---------+--------+-----------+
|  1 | 2017-01-18 20:34:01 | Viagem à SP  | SAIDA   | 100.00 |        2  |
|  2 | 2017-01-18 20:34:01 | Viagem ao RJ | SAIDA   | 300.00 |        2  |
|  6 | NULL                | Viagem à SP  | ENTRADA | NULL   |        2  |
+----+---------------------+--------------+---------+--------+-----------+
3 rows in set (0,00 sec)
Rodando a aplicação novamente, veremos se isto foi inserido corretamente... Está tudo ok! Sendo assim, neste vídeo vimos que, ao utilizarmos JPA, a ideia é afastarmos o máximo possível o uso do modelo relacional em nosso código, nos preocupando com o mundo orientado a objetos.

Quando falamos em fazer um select from Movimentacao, nos referimos à entidade Movimentacao, e não mais à tabela de mesmo nome. É possível até renomearmos, já que estamos nos referindo à classe Movimentacao, de forma a acessarmos os atributos, as propriedades que existem nesta classe.

Isso é mais vantajoso por trazer uma query mais fácil de ser escrita e, mais uma vez, por estar mais próxima ao modelo orientado a objetos. Além disto, há a questão de portabilidade: não precisaremos trocar a implementação do JPA (de Hibernate para EclipseLink, por exemplo). A forma de escrita de JPQL não muda, pois isto faz parte de sua especificação, que é de padronizar.


<h2>JPQL vs SQL</h2>
Quando trabalhamos com bancos relacionais, usamos a linguagem SQL para manipular os dados. Nesse capítulo conhecemos a JPQL e as suas diferenças do SQL, sabendo disso podemos afirmar:


JPQL é orientado a objetos, enquanto SQL não.
 
De fato, JPQL é feita para abstrair o mundo relacional permitindo com que os desenvolvedores se preocupem apenas com objetos.

No JPQL não usamos chave estrangeira, enquanto SQL sim.
 
Não precisamos referenciar à uma chave estrangeira já que é algo que faz parte do modelo relacional.


<h2>Named Parameter Notation</h2>
Quando trabalhamos com JDBC usamos parâmetros para não precisar concatenar String na query. Dessa forma, o próprio JDBC é capaz de validar os dados entrados pelo usuário evitando assim SQL Injection. Na prática teremos algo assim:
PreparedStatement ps = conn.prepareStatement("select * from Usuario where id = ?");
ps.setInt(1, usuario.getId());
Essa notação de passar o valor do parâmetro baseado na posição onde ele se encontra também existe em JPA e se chama Positional Parameter Notation. No entanto, a presença de muitos parâmetros pode se tornar uma confusão facilmente.

Para evitar isso, usamos a notação Named Parameter Notation que é mais expressiva. Usando ela ganhamos como vantagem:

Facilidade de identificar os parâmetros, diminuindo a probabilidade de erros.
 
Se os parâmetros são identificados por nomes, temos um código mais expressivo.
Quando identificamos os parâmetros através de nomes nosso código código torna-se mais expressivo, diminuindo a probabilidade de confusão.


<h2>Ajude João a economizar</h2>

João quer seus gastos mensais para saber se há alguma forma de economizar. Qual das opções abaixo consegue retornar todas as movimentações que possam ajudar João a fazer essa análise?

select m from Movimentacao m where m.tipoMovimentacao='SAIDA'
 
Trazendo as movimentações de saída, João irá conseguir analisar seus gastos.


<h2>Consultas JPQ em relacionamentos @ManyToMany</h2>

Como poderemos fazer para trazer as movimentações de uma categoria específica? Para descobrir, vamos fazer um teste criando uma classe chamada TesteMovimentacoesPorCategoria no pacote br.com.caelum.financas.teste.
Lembrando que o relacionamento entre Movimentacao e Categoria é do tipo @ManyToMany, o que significa que ele é mapeado em outra tabela - sendo assim, de que forma utilizaremos esse relacionamento no filtro (where)?

Precisaremos juntar as duas entidades (as movimentações com as respectivas categorias) na query e, para isso, temos uma palavra-chave, join, para o qual passaremos um apelido para podermos referenciá-lo mais adiante, no filtro:

public class TestaConsulta {

    public static void main(String[] args) {

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        Categoria categoria = new Categoria();
        categoria.setId(1);

        String jpql = "select m from Movimentacao m join m.categoria c where c = :pCategoria";

        Query query = em.createQuery(jpql);
        query.setParameter("pCategoria", categoria);

        List<Movimentacao> resultados = query.getResultList();

        //...
        }

    }
}
Ao executar este código verificaremos que há duas movimentações com a categoria de id 1 (Viagem).

Portanto, concluímos que é possível realizar buscas em relacionamentos @ManyToMany de forma fácil apenas usando a palavra-chave join. É importante frisar que por estarmos usando JPA, manteremos o modelo orientado à objetos, abstraindo-se a responsabilidade de manipularmos a tabela de relacionamento.

<h2>Ajudando João novamente</h2>
Ao analisar seus gastos, João percebeu que estava gastando muito com viagens e precisava economizar. Como podemos trazer todas as viagens de João que custaram acima de R$ 500,00?


Categoria viagem = ...

Query query = em.createQuery("select m from Movimentacao m join m.categoria c where c = :pCategoria and m.valor > 500 and m.tipoMovimentacao = :pTipoMovimentacao");

query.setParameter("pCategoria", viagem);
query.setParameter("pTipoMovimentacao", TipoMovimentacao.SAIDA);
 
Dessa forma traremos as movimentações de saída que são mais caras de R$ 500 e são relacionadas com viagem.
Dessa forma traremos as movimentações de saída que são mais caras de R$ 500 e são relacionadas com viagem.


----------------------------------------------------------------------------------------------------
<h1>Seção 07 - Trabalhado com Lazy e relacionamentos bidirecionais</h1>

<h2>Relacionamento Bidirecional</h2>
Quando solicitamos ao Hibernate para que os relacionamentos sejam gerenciados, utilizamos a anotação @ManyToOne ou @ManyToMany. Foi o que fizemos na classe Movimentacao, em que @ManyToOne indica que muitas movimentações fazem parte de apenas uma conta.

Vamos comprovar isto criando uma classe TesteMovimentacaoConta no package br.com.caelum.financas.teste. No método main abriremos o EntityManager e buscaremos a movimentação com o id 3.

Feito isto, falta mostrarmos o resultado no console. Com a movimentação carregada, vamos imprimir o titular usando o relacionamento movimentacao.getConta().getTitular():

public class TesteMovimentacaoConta {

    public static void main(String[] args) {
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        Movimentacao movimentacao = em.find(Movimentacao.class, 3);
        Conta conta = movimentacao.getConta();

        System.out.println(conta.getTitular()); 
    }
}
Ao executarmos o código, será impresso o nome do titular. Imagine que agora queremos saber quantas movimentações uma determinada conta possui. Pensando de uma maneira mais orientada a objetos, seria natural pedirmos à conta todas as suas movimentações pelo método getMovimentacoes(), verificando-se depois o tamanho de itens da lista retornada pelo método size().

System.out.println(conta.getMovimentacoes().size());
Esta lista, porém, ainda não existe. Precisaremos criar o getMovimentacoes(), que nos devolverá uma lista de Movimentacao, assim:

public List<Movimentacao> getMovimentacoes() {
    //TODO Auto-generated method stub
    return null;
}
É necessário criarmos também um atributo do tipo List de Movimentacao, o qual chamaremos de movimentacoes. No entanto, o JPA não saberá que simplesmente criamos um atributo, tampouco de que isto deve representar as movimentações daquela conta específica.

É necessário indicar isso através de um relacionamento; se de um lado utilizamos @ManyToOne, do outro usamos a anotação @OneToMany, para indicar que uma conta possui várias movimentações.

@OneToMany
private List<Movimentacao> movimentacoes;
Com isto, temos um relacionamento bidirecional, em que duas partes conhecem o relacionamento. Vamos testar o código abaixo?

public class TesteMovimentacaoConta {

    public static void main(String[] args) {
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        Movimentacao movimentacao = em.find(Movimentacao.class, 3);
        Conta conta = movimentacao.getConta();

        System.out.println(conta.getTitular()); 
        System.out.println(conta.getMovimentacoes().size());
    }
}
A aplicação lança um NullPointerException!

Analisando-se getMovimentacoes(), veremos que não trocamos return null; para return movimentacoes;. O código correto, então, é este:

public List<Movimentacao> getMovimentacoes() {
    //TODO Auto-generated method stub
    return movimentacoes;
}
Rodaremos mais uma vez e... Vejam que estranho! A nossa lista de movimentações retorna 0. Será que isto faz sentido? Vamos conferir no terminal, digitando select * from Movimentacao;.

Como resultado, teremos três movimentações associadas à conta de id 2. Posteriormente, confirmamos que esta conta corresponde ao titular obtido anteriormente (Paulo Roberto Souza), a partir do comando select titular from Conta where id=2;.

Por quê, então, 0 movimentações, quando na realidade temos três?

Trata-se de uma diferença interessante entre o modelo relacional e o orientado a objetos. No primeiro, basta termos a coluna de chave estrangeira (conta_id) para que as duas entidades se conheçam. No modelo orientado a objetos, isto não ocorre de forma implícita, tanto que precisamos colocar, na classe Conta, a lista de movimentações para que a conta também as conheça.

O que tínhamos anteriormente? Apenas Movimentacao conhecendo sua conta, aquela a que ela pertence. A Conta por si só não conhecia suas movimentações, algo que só acontece após incluirmos List<Movimentacao>.

Então, temos duas anotações de relacionamento (@ManyToOne e @OneToMany), o que faz com que o JPA conheça-as como dois relacionamentos independentes, o que na verdade não é o que queremos.

Queremos que @OneToMany represente o mesmo relacionamento que estávamos fazendo anteriormente com @ManyToOne na classe Movimentacao. Esta anotação serve apenas para refletir um relacionamento que existe na outra parte.

Isto se confirma ao digitarmos show tables; no terminal, pois vimos que foi criada uma nova classe, Conta_Movimentacao, para representar o relacionamento @OneToMany que acabamos de criar.

O que queremos é que ambas as partes representem o mesmo relacionamento. Para isto, precisaremos escolher "a parte forte", que cria o relacionamento, a coluna de chave estrangeira (conta_id), que no nosso caso é @ManyToOne na classe Movimentacao.

@OneToMany, a parte que reflete, já foi mapeada por uma coluna chamada conta, do outro lado, na classe Movimentacao.

@OneToMany(mappedBy="conta")
private List<Movimentacao> movimentacoes;
Ao rodarmos a aplicação novamente, verificaremos que o getMovimentacoes() foi reconhecido como estando no mesmo relacionamento do atributo Conta na classe Movimentacao.


<h2>Espelho, espelho meu...</h2>
Um desenvolvedor de uma empresa criou o seguinte relacionamento:
public class Post {

    @ManyToOne
    private Escritor escritor;

}
public class Escritor {

    @OneToMany
    private List<Post> posts;

}
Ao verificar as tabelas no MySQL através de um show tables ele encontrou o seguinte relatório:

+------------------------+
| Tables_in_blog         |
+------------------------+
| Escritor               |
| Escritor_Post          |
| Post                   |
+------------------------+
O que podemos afirmar sobre essa situação?


É necessário colocar o atributo mappedBy na anotação @OneToMany.
 
Com mappedBy="escritores" conseguimos manter o @OneToMany apenas como um espelho (bidirecional) evitando que ele crie duas vezes o relacionamento no banco(chave estrangeira e tabela de relacionamento).

<h2>Lazy Loading</h2>

Vamos supor que precisamos de um relatório com todas as movimentações de todas as contas do nosso banco. Para isto, criaremos a classe TesteTodasAsMovimentacoesDasContas, com um método main, EntityManager e uma lista de Conta que conterá todas as contas existentes no banco de dados.
public class TesteTodasAsMovimentacoesDasContas {

    public static void main(String[] args) {

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        String jpql = "select c from Conta c";

        Query query = em.createQuery(jpql);

        List<Conta> todasAsContas = query.getResultList();

        for (Conta conta : todasAsContas) {
            System.out.println("Titular: " + conta.getTitular());
            System.out.println("Movimentacoes: ");
//          System.out.println(conta.getMovimentacoes());
        }
    }
}
Vamos rodar este código deixando a última linha syso comentada. A conta é buscada por causa da query, e os titulares foram trazidos. Porém, no lugar das movimentações, nada aparece, obviamente por termos comentado a linha relativa a isso. Veremos o que ocorre quando descomentamos esta linha e rodamos a aplicação novamente.

Feito o select (a busca pela conta), várias buscas pelas movimentações de cada uma das contas foram disparadas. Enquanto a conta da Maria dos Santos é tratada, por exemplo, faz-se outro select para buscar suas movimentações. No caso, não existe nenhuma, então nada foi preenchido. Isso se repete com os outros titulares e todas as contas no foreach.

Reparem, no entanto, que se fez isso exatamente quando chamamos, pois quando tínhamos a linha comentada, estas queries não haviam sido feitas. Esta consequência se chama N + 1, por realizar 1 select para buscar as contas e, para cada uma delas, no laço, foi feito mais um select para buscar as movimentações correspondentes.

Isso ocorre justamente porque, por padrão, os relacionamentos [...]ToMany, que são listas ou conjuntos, são considerados Lazy, com "carregamento preguiçoso". Ou seja, o JPA só irá ao banco para buscar estes relacionamentos quando você precisa deles, ao chamarmos o método getMovimentacoes(), por exemplo.

Porém, podemos não querer isto e, pela quantidade de queries feitas, queremos que quando a conta for buscada, sejam trazidas também as movimentações. Então, precisaremos substituir o comportamento Lazy por Eager Loading, a partir do parâmetro join fetch, o que quer dizer que queremos juntar, nesta query, a conta e a movimentação.

Para referenciar a movimentação na conta, utilizaremos c.movimentacoes:

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        String jpql = "select c from Conta c join fetch c.movimentacoes";

        Query query = em.createQuery(jpql);

        List<Conta> todasAsContas = query.getResultList();

        for (Conta conta : todasAsContas) {
            System.out.println("Titular: " + conta.getTitular());
            System.out.println("Movimentacoes: ");
            System.out.println(conta.getMovimentacoes());
        }
Vamos rodar para verificar se tanto a conta quanto a movimentação são trazidas na mesma query? Veremos que o select é feito, bem como um inner join para a movimentação. Desta forma, conseguimos imprimir as movimentações que existem no banco.


<h2>E o N+1?</h2>

No vídeo anterior você conheceu uma situação bem comum que é o problema do N + 1 queries:


Qual das opções abaixo é verdadeira?

N + 1 ocorre quando precisamos disparar queries select para preencher os relacionamentos e pode ser resolvido com um join.
 
Com uso da clausula join, conseguimos trazer todos os resultados em uma única query.


N + 1 é um problema de gerenciamento das queries e não depende de usarmos carregamento LAZY ou EAGER. Com uso da clausula join, conseguimos trazer todos os resultados em uma única query. Abaixo um código de exemplo onde ocorre N+1 com JDBC:
// MovimentacaoDao.java
public class MovimentacaoDao {


    public List<Conta> getMovimentacoesDa(Conta conta) {

        // código jdbc

        String sql = "select * from Movimentacao where conta_id = " + conta.getId();

        // código jdbc

    }
}
// ContaDao.java

public class ContaDao {


    public List<Conta> getContas() {

        // código jdbc

        String sql = "select * from Conta";

        // código jdbc

    }
}
// Teste.java
public static void main(String[] args) {
    List<Conta> contas = contaDao.getContas();

    for(Conta conta : conta) {

        List<Movimentacao> movimentacoes = movimentacaoDao.getMovimentacoesDa(conta);

    }

}


<h2>Um pequeno desafio!</h2>

Vamos à um pequeno desafio? Tente rodar o código abaixo:
public class TesteInserirMovimentacao {

    public static void main(String[] args) {

        EntityManager em = new JPAUtil().getEntityManager();

        Conta conta = em.find(Conta.class, 1);

        List<Movimentacao> movimentacoes = conta.getMovimentacoes();

        em.close();

        for (Movimentacao movimentacao : movimentacoes) {
            System.out.println("Movimentação: " + movimentacao.getDescricao());
        }

    }
}
Você receberá uma LazyInitializationException. Pense um pouco (nada de Stackoverflow) e faça alguns testes. Após isso, marque as possíveis soluções:


Substituir o em.find() por uma query com join.
 
Ele buscará antecipadamente as movimentações junto com a conta.

Colocar FetchType.EAGER no relacionamento: @OneToMany(mappedBy = "conta", fetch = FetchType.EAGER)
 
Ele buscará antecipadamente as movimentações junto com a conta. (risco de N + 1!!)

Mover a linha em.close() para depois do for.
 
Quando ele buscar pelas movimentações, haverá uma conexão aberta com o banco.
LazyInitializationException ocorre quando não há conexão aberta com o banco de dados no momento da execução de um código lazy. As soluções seriam deixá-la aberta um pouco mais ou trazer os relacionamentos de forma Eager.


<h2>Left join e distinct</h2>
Parece que ainda há algo de estranho com nossas queries, pois recebemos as movimentações de um mesmo titular três vezes. Por que será que isso acontece? Vamos descobrir mandando a seguinte query ao banco:
select conta.titular from Conta conta inner join Movimentacao movimentacao on conta.id=movimentacao.conta_id

Reparem que no resultado desta query também recebemos três Paulo Roberto Souza. Isso ocorre porque quando utilizamos inner join estamos, na verdade, fazendo um produto cartesiano. Quer dizer que ele irá trazer uma conta para cada movimentação relacionada a ela. Mas não queremos linhas duplicadas, e sim uma distinção. Para isso, adicionaremos a palavra-chave distinct:

select distinct conta.tienvolvetular from Conta conta inner join Movimentacao movimentacao on conta.id=movimentacao.conta_id

Dessa forma, ao dispararmos a query contra o banco teremos apenas um resultado. Também colocaremos essa palavra chave no JPQL:

String jpql = "select distinct c from Conta c join fetch c.movimentacoes";

Ao executarmos o teste recebemos apenas uma conta - no entanto, precisamos de um relatório com todas as contas e suas movimentações, e apenas a do Paulo Roberto Souza é retornada. Por que isso acontece nesta query?

Por padrão, quando usamos um join, o Hibernate dispara no banco um inner join, o qual irá trazer apenas as contas com movimentação, ignorando as que não possuem. Para que todas as contas sejam trazidas, inclusive as que não possuem movimentações, utilizamos left join:

String jpql = "select distinct c from Conta c left join fetch c.movimentacoes";

Pronto! Ao dispararmos novamente a query, conseguimos trazer todas as contas do banco, tudo aquilo que estiver na entidade, ao lado esquerdo do join, ou seja, a Conta.

Será que se deixarmos de usar distinct o Hibernate trará os resultados desejados da mesma maneira? Rodamos a aplicação e verificamos que não é o caso, ainda ocorre repetição, portanto seguiremos utilizando-o no código.



<h2>Produto Cartesiano?</h2>

Ao usar join podemos trazer alguns elementos repetidos, como foi visto no vídeo anterior. Na prática, estamos fazendo um produto cartesiano (já que não temos uma join condition):


Como podemos solucionar esse problema?


Usando a clausula distinct.
 
Ao usar distinct dizemos ao banco que queremos apenas os resultados diferentes.


<h2>Trazendo todos os dados</h2>

Quando executamos a query abaixo:
String jpql = "select distinct c from Conta c join fetch c.movimentacoes";

Só recebemos como resultado as queries que possuem movimentações. Por quê?

É necessário trazer todos os dados da entidade à esquerda do join.
 
Por padrão o join fetch fará um inner join que trará somente as contas que possuem movimentação. Portanto, precisamos fazer um left join fetch em vez de join fetch.


<h2>Revisando</h2>
Vamos listar tudo o que vimos?
Relacionamentos bidirecionais
No mundo orientado a objetos, precisamos explicitamente criar atributos de relacionamentos para que as duas pontas se reconheçam;
Neste relacionamento, escolhemos uma parte para ser a "dona" e a outra para ser o "espelho" (mappedBy).
Lazy Loading
Por padrão todos os relacionamentos [...]ToMany são Lazy;
Torna mais fácil o problema do N + 1 (Atenção: também é possível ter N + 1 com Eager!);
Usamos o join fetch para carregar o relacionamento de forma Eager (em uma mesma query) ao usarmos JPQL.
Join e produto cartesiano
Ao usarmos join fetch, os resultados aparecem duplicados devido ao produto cartesiano. Para evitá-los, utilizamos a palavra-chave distinct;
O join fetch dispara um inner join no banco, o que acaba trazendo apenas as contas com movimentação. Por isso usamos left join fetch, que trará todos os dados da entidade que se encontram ao lado esquerdo do join (no caso, Conta).


--------------------------------------------------------------------------------------------------------------------
<h1>Seção 08 - Um pouco mais sobre queries e JPQL</h1>

<h2>Funções JPQL</h2>


Já sabemos como fazer consultas ao banco de dados utilizando JPQL. Neste vídeo aprenderemos algumas melhorias e complexidades das queries. Para isto, precisaremos popular novamente a tabela Movimentacoes, então copiaremos a classe TesteMovimentacoesComCategoria, incluindo-a no pacote br.com.caelum.financas.util.

Abrindo-a, vamos alterar o nome da classe para PopulaMovimentacoesComCategoria. Em vez de criarmos duas movimentações com a data de hoje, que é o que está sendo feito aqui, a segunda movimentação terá a data de amanhã:

Calendar amanha = Calendar.getInstance();
amanha.add(CalendarDAY_OF_MONTH, 1);

movimentacao2.setData(amanha); //amanha
Vamos executar para testar, não esquecendo de conferir se está tudo correto no banco também, com select * from Movimentacao;. Confirmando o bom funcionamento, copiaremos a classe TesteJPQL, e criaremos TesteFuncoesJPQL.

Na classe criada anteriormente, usamos o JPQL para buscar todas as movimentações cuja :pConta seja igual a Conta(), nos afastando do mundo relacional. Neste caso, será procurada a conta com id 2. Além disso, será buscada a movimentação de tipo SAIDA, ordenada de forma decrescente pelo valor.

String jpql = "select m from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo" + " order by m.valor desc";

Query query = em.createQuery(jpql);
query.setParameter("pConta", conta);
query.setParameter("pTipo", TipoMovimentacao.SAIDA);
Vamos supor que queiramos a soma dos valores dos preços da movimentação. Conseguiremos isto a partir do método sum():

String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo" + " order by m.valor desc";
Assim, deixamos de trazer as movimentações desta conta e tipo, e sim a soma delas. Nesse caso, não precisaremos mais receber uma lista (getResultList()), pois haverá apenas um resultado. Portanto, substituiremos as linhas de código referentes a List<Movimentacao> por getSingleResult();.

Isto nos retornará um object, já que o JPA não saberá que se trata da representação de uma soma. Na classe Movimentacao, o valor está em BigDecimal, portanto a soma será deste tipo também. Precisaremos fazer um cast para BigDecimal:

Query query = em.createQuery(jpql);
query.setParameter("pConta", conta);
query.setParameter("pTipo", TipoMovimentacao.SAIDA);

BigDecimal soma = (BigDecimal) query.getSingleResult();

System.out.println("A soma é: " + soma);

em.getTransaction().commit();
em.close();
Rodaremos a aplicação e veremos que obteremos a soma, como desejávamos. E se quisermos a média dos valores das movimentações? Algo que some todos os valores e divida este total pela quantidade de movimentações - no nosso caso, 2.

Para isto, existe a função avg(), que nos retornará a média dos valores das nossas movimentações. O nosso código ficará assim:

BigDecimal media = (BigDecimal) query.getSingleResult();

System.out.println("A média é: " + media);

em.getTransaction().commit();
em.close();
Vamos executar? É retornada uma exceção ClassCastException, um Double, que estamos tratando como se fosse um BigDecimal. A função avg() que utilizamos acima trabalha com Double, não com BigDecimal. Corrigindo o código, então, teremos:

Double media = (Double) query.getSingleResult();

System.out.println("A média é: " + media);

em.getTransaction().commit();
em.close();
Desta vez, ao rodarmos novamente, teremos a média correta. No banco de dados há uma movimentação com valor de 100.00 e outra de 300.00. A média recebida na aplicação é de 200.00.

<h2>Opcional: Conhecendo a função max()</h2>

Nesse exercício vamos usar a max() no JPQL, similar ao exercício anterior.
1) Crie uma classe TesteConsultaFuncaoMax no package br.com.caelum.financas.teste. Também já gere o método main.

2) No método main abra o EntityManager pelo JPAUtil e e carregue uma conta pela id (id da conta deve existir).

3) Agora crie uma Query pelo método createQuery(..) e busque maior gasto da conta. Vamos usar o função max no JPQL:

Query query = manager.createQuery("select max(m.valor) from Movimentacao m where m.conta = :pConta");
4) Preencha a query e execute-a pelo método getSingleResult(). É preciso fazer um cast para BigDecimal. Imprima o resultado no console.

5) Execute o método main e confira o resultado no console.

<h2>TypedQuery</h2>

Imaginem que queiramos a média das movimentações, mas não de todas de uma vez, e sim separadamente, por dia, por exemplo. Como faremos isto?
Inicialmente, separaremos as movimentações em grupos: as do primeiro dia ficarão em um canto, e as do segundo, em outro. Poderemos utilizar a função avg() para juntá-las, agregando-as por algum parâmetro, neste caso, valor. Então, usaremos um comando que fará este agrupamento, group by:

String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo" + " group by m.data";
Agrupadas as movimentações, conseguiremos fazer a média de cada um destes grupos. Vamos executar?

Um erro aparecerá indicando que a query não retorna mais um resultado único, e sim dois - o que faz todo sentido, pois são dois dias de movimentações. Portanto, substituiremos getSingleResult(); por getResultList();, o que nos retorna uma lista. Não é mais necessário fazermos cast para Double:

Query query = em.createQuery(jpql);
query.setParameter("pConta", conta);
query.setParameter("pTipo", TipoMovimentacao.SAIDA);

List media = query.getResultList();

System.out.println("A média é: " + media);
O problema é que ao colocarmos media.get(0); para buscar uma média, será retornado um Object (o que significa que teremos que fazer cast), mais código extra, e não queremos isto. Usaremos o JAX do Java 5 e, ao colocarmos este código, será retornado um Double.

Mesmo assim, reparem que continuaremos tendo um aviso, já que não é possível se assegurar de que uma lista de Double seja retornada. Nós sabemos disto por utilizarmos avg(), no entanto o compilador não consegue ter a mesma certeza.

Isto quer dizer que se alguém quiser fazer alguma mudança futuramente, haverá algum problema no momento de execução. O compilador informa que a conversão que estamos tentando fazer não é garantida, mas que irá fazê-la de qualquer forma.

Para assegurar o bom funcionamento, o JPA possui uma versão do objeto Query que permite trabalharmos de forma fortemente tipada: TypedQuery, pelo qual indicamos explicitamente o tipo de query que será retornado. No nosso caso será o Double. Agora, além de jpql, precisaremos passar o tipo com que realmente trabalhamos, Double.class.

Vamos trocar media por medias também, para ficar coerente com o que estamos fazendo, e também imprimir dois System.out.println, um para cada média:

TypedQuery<Double> query = em.createQuery(jpql, Double.class);
query.setParameter("pConta", conta);
query.setParameter("pTipo", TipoMovimentacao.SAIDA);

List<Double> medias = query.getResultList();

System.out.ptinln("A média do dia 26 é: " + medias.get(0));
System.out.println("A média do dia 27 é: " + medias.get(1));
Vamos rodar este código e ver o que acontece. Obteremos o seguinte retorno:

A média do dia 26 é: 100.0
A média do dia 27 é: 100.0
Conferiremos isso no nosso banco de dados, com o comando select * from Movimentacao;, e verificaremos que a média do segundo dia está incorreto, pois deveria ser 300.0, e não 100.0.

Vamos substituir os medias.get() do código acima por um foreach? Desta forma facilitamos o processo, sem precisarmos repetir os syso.

TypedQuery<Double> query = em.createQuery(jpql, Double.class);
query.setParameter("pConta", conta);
query.setParameter("pTipo", TipoMovimentacao.SAIDA);

List<Double> medias = query.getResultList();

for (Double media : medias) {
    System.out.println("A média é: " + media);
}
Feito isto, rodaremos o código novamente, e obteremos o seguinte:

A média é: 100.0
A média é: 100.0
A média é: 300.0
A média é: 300.0
Os resultados são trazidos repetidamente para cada dia. Já vimos isto ocorrer em vídeos anteriores, então sabemos como "enxugar" isso utilizando o distinct:

String jpql = "select distinct avg(m.valor) from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo" + " group by m.data";
Rodaremos o código mais uma vez, e dessa vez teremos os resultados da forma como esperávamos!

<h2>Usando a função corretamente</h2>
Precisamos saber a média dos gastos de uma conta, para isso desenvolvemos uma aplicação que executará o código abaixo:
String jpql = "select avg(m.valor) from Movimentacao m where m.conta=:pConta "
                + "and m.tipoMovimentacao=:pTipo";

TypedQuery<BigDecimal> query = manager.createQuery(jpql, BigDecimal.class);

query.setParameter("pConta", conta);
query.setParameter("pTipo", TipoMovimentacao.SAIDA);

BigDecimal resultado = query.getSingleResult();
O que você acha desse código?

Não é possível usar a função avg() com BigDecimal, portanto receberemos uma exceção.
 
Ao trabalhar com a função avg() precisamos usar Double ao invés de BigDecimal.

-----------------------------------------------------------------------------------------------------------
<h1>Seção 09 - Organizando nossas queries</h1>

<h2>MovimentacaoDao</h2>

Depois de todo conteúdo visto em aula, vamos pensar melhor sobre a organização do nosso código. Em cada vídeo criamos algumas classes de teste para aprendizado diverso do JPA, sendo que todas elas possuem código que manipula o banco de dados.

Estes códigos estão isolados em várias partes do nosso sistema, o que dificulta sua manutenção pela necessidade de os replicarmos em outros locais, algo que demanda trabalho e tempo, uma vez que precisaremos descobrir a localização exata de cada mudança.

Seria interessante, portanto, se conseguíssemos minimizar o impacto das manutenções tendo um local único, cuja responsabilidade seja lidar com as movimentações do banco.

Estas classes, capazes de manipular dados de alguma entidade do sistema no banco de dados, é que o que chamamos de Data Access Object (DAOs), objeto especialista em manipular e acessar os dados da entidade no banco de dados.

A partir do atalho "Ctrl + N" criaremos a classe MovimentacaoDao no pacote br.com.caelum.financas.dao. Em TesteFuncoesJPQL, isolaremos todo o código que lida com o banco de dados da MovimentacaoDao, como String jpql, TypedQuery e List<Double>. O restante ainda não possui nada que se relacione à movimentação do banco. Com "Ctrl + 1" criaremos os métodos e variáveis necessários, deixando o código assim:

Conta conta = new Conta();
conta.setId(2);

MovimentacaoDao dao = new MovimentacaoDao();
List<Double> medias = dao.getMediasPorDiaETipo(TipoMovimentacao.SAIDA, conta);
E em MovimentacaoDao, teremos o código retirado anteriormente de TesteFuncoesJPQL:

public class MovimentacaoDao {

    public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {
        String jpql = "select distinct avg(m.valor) from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo" + " group by m.data";

        TypedQuery<Double> query = em.createQuery(jpql, Double.class);
        query.setParameter("pConta", conta);
        query.setParameter("pTipo", TipoMovimentacao.SAIDA);

        return query.getResultList();
    }
}
Reparem que o Eclipse aponta um erro com em.createQuery(), por conta de em, que tem a ver com o EntityManager. O JPA precisa dele para consultar o banco de dados. No entanto, poderemos ter vários métodos no DAO e, sendo assim, de que forma conseguiremos criar um EntityManager possível de ser acessado a partir de diversos métodos? Colocando-o como um atributo:

public class MovimentacaoDao {

    private EntityManager em;

    public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {
        String jpql = "select distinct avg(m.valor) from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo" + " group by m.data";

        TypedQuery<Double> query = em.createQuery(jpql, Double.class);
        query.setParameter("pConta", conta);
        query.setParameter("pTipo", TipoMovimentacao.SAIDA);

        return query.getResultList();
    }
}
Isto soluciona este problema! Vamos tentar rodar este código e ver o que acontece. Teremos um NullPointerException, por criarmos um atributo de tipo EntityManager sem passar nada. Ao tentarmos criar uma query à variável que aponta para o nulo, obviamente teremos um problema.

Qual seria a melhor forma de passarmos um EntityManager ao MovimentacaoDao? Será que criando um setEm()? Em TesteFuncoesJPQL, poderemos ter:

MovimentacaoDao dao = new MovimentacaoDao();
dao.setEm(em);
List<Double> medias = dao.getMediasPorDiaETipo(TipoMovimentacao.SAIDA, conta);
E em MovimentacaoDao,

private EntityManager em;

public void setEm(EntityManager em) {
    this.em = em;
}
Executando-se o código, após a chegada da lista, será feito o foreach, como acontecia antes. No console, obteremos:

A média é: 100.0
A média é: 300.0
A query é disparada e tudo funcionará normalmente. Supondo que esqueçamos de passar o EntityManager de dao.setEm(em);, o que será que acontecerá? Voltaremos ao mesmo problema do NullPointerException e não saberemos nem por quê.

Ao criarmos MovimentacaoDao, precisaremos obrigatoriamente passar o EntityManager. Teremos este comportamento por meio de um construtor. Então, em TesteFuncoesJPQL, o código ficará assim:

MovimentacaoDao dao = new MovimentacaoDao(em);
E em MovimentacaoDao, utilizaremos o atalho "Ctrl + 3 + GCUF" no Eclipse, selecionando um EntityManager e clicando em "OK" para criar um construtor:

private EntityManager em;

public MovimentacaoDao(EntityManager em) {
    this.em = em;
}
Pronto! Vamos executar o código. Verificaremos que tudo continua funcionando bem. Se tirarmos o EntityManager(em) em MovimentacaoDao dao = new MovimentacaoDao(em), o que ocorre? Um erro, pois quando criamos um construtor, o padrão é que o default seja descartado de nossa classe.

Vimos que a boa prática implica em, em vez de isolar, deixar os códigos que se relacionam com o banco e várias classes distintas em uma única classe.

O DAO é a classe especialista em acessar os dados de uma entidade qualquer em alguma fonte de dados, como um banco, um WebService, entre outros. No nosso contexto, como estamos estudando JPA, trata-se do banco de dados.

Mantendo-se o código ali para o caso de precisarmos fazer alguma manutenção ou alteração, isto se dará de forma fácil e organizada, pois teremos todo o código reunido em um mesmo local.

<h2>Acessando os dados de maneira correta</h2>
Como você viu no vídeo, existe um padrão de projeto destinado a criar classes para acessar os dados de alguma determinada entidade. É sempre bom deixar claro que esses dados não precisam, necessariamente, vir de um banco de dados o que significa que podemos usar quaisquer outras fontes como um WebService ou um EJB.
Dentre as alternativas, qual pode ser assinalada como real consequência do uso do Data Access Object (DAO) em nosso projeto?

Melhorar a manutenibilidade do nosso código já que evitamos espalhar detalhes de persistência pelas regras de negócio.
 
Ao criarmos uma camada de persistência, ao surgir um novo requisito saberemos exatamente onde ir.

Separar as regras de negocio das lógicas de persistência, criando uma camada de persistência.
 
Com o uso do DAO criamos uma camada onde somente por meio dela é possível acessar os dados de uma entidade específica.

<h2>NamedQueries</h2>

Pensando nas formas de organização das queries, com o JPA trabalhamos mais orientados a objetos, pelas buscas baseadas em nosso modelo. Assim, faz sentido colocarmos as consultas junto às entidades (Entity).
O JPA traz um recurso que nos fornece uma maneira diferente de organizar as consultas, denominado Named Query, o qual permite justamente declararmos uma query identificando-a com um nome e, depois, referenciá-la quando formos utilizá-la.

Na classe Movimentacao usaremos a anotação @NamedQuery, passando a mesma query utilizada no método getMediasPorDiaETipo de MovimentacaoDao:

@NamedQuery(query="select distinct avg(m.valor) from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo" + " group by m.data", name="MediasPorDiaETipo")

@Entity
public class Movimentacao {

    // ...

}
Em TesteFuncoesJPQL, não utilizaremos mais a linha MovimentacaoDao dao = new MovimentacaoDao(em);, e precisaremos criar um NamedQuery a partir do EntityManager. Para isto, deixaremos o código da seguinte maneira:

Conta conta = new Conta();
conta.setId(2);

TypedQuery<Double> typedQuery = em.createNamedQuery("MediasPorDiaETipo", Double.class);

typedQuery.setParameter("pConta", conta);
typedQuery.setParameter("pTipo", TipoMovimentacao.SAIDA);

List<Double> medias = typedQuery.getResultList();
Vamos rodar o código e ver se tudo funciona como queremos. As médias de 100.0 e 300.0 continuam sendo retornadas. O interessante nisso tudo é que o select é processado pelo Hibernate assim que ele é iniciado. Caso façamos alguma alteração no modelo sem replicá-la na query, seremos avisados de antemão. Vamos fazer este teste, alterando valor para valor2 no código abaixo, em Movimentacao:

private BigDecimal valor2;
É lançada uma exceção de tipo HibernateException indicando que há um problema em MediasPorDiaETipo, por conta da alteração que acabamos de fazer.

Então, neste vídeo pudemos ver como organizar nossas queries, através de duas opções: o DAO, que isola as queries em um objeto específico com esse propósito, e colocando-se as queries junto ao modelo com o NamedQuery.

Vimos também algumas funções bem legais, como o avg(), que usamos para a obtenção da média entre dois valores, e o group by, com que separamos valores em grupos.



<h2>Usando NamedQuery</h2>
Qual das opções abaixo descreve a maneira correta de se declarar um NamedQuery?

// Movimentacao.java

@NamedQuery(name = "mediaDaContaPeloTipoMovimentacao",
     query = "select avg(m.valor) from Movimentacao m where m.conta=:pConta  and m.tipoMovimentacao = :pTipo")

@Entity
public class Movimentacao {
// TestaConsultaFuncoes.java

TypedQuery<Double> query = manager.createNamedQuery("mediaDaContaPeloTipoMovimentacao", Double.class);
 
Com o JPA, trabalhamos mais orientados à objetos, fazendo as buscas em cima do nosso modelo. Pensando assim, poderia fazer sentido manter as consultas junto às entidades. Fazemos isso com @NamedQuery.


<h2>Considerações finais</h2>

Com isso, chegamos ao final do curso! Aprendemos como é fácil desenvolver aplicações com JPA e como ela ajuda a melhorar nossa produtividade, focando no mundo orientado a objetos.
Vimos diversos recursos, como estados das entidades, relacionamentos, JPQL, Lazy Loading e relacionamentos bidirecionais.

Você pode baixar um projeto completo com todos os testes que fizemos nesse link.

Aqui na Alura temos um segundo curso de JPA, focado em Web, em que aprendemos recursos ainda mais importantes para sua aplicação, tais como Cache de segundo nível, cache de queries e coleções pool de conections, queries dinâmicas EntityManagerInView, entre outros. Espero você lá!
