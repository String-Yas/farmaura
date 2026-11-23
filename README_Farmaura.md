# Farmaura

Sistema web de farmácia desenvolvido com **Java 21 e Spring Boot**, com interface renderizada por Thymeleaf, persistência em banco H2 e controle de acesso com Spring Security. O projeto reúne um catálogo de produtos farmacêuticos e módulos de cadastro e gerenciamento de clientes, usuários, pedidos e entregas.

> **Documentação baseada na estrutura e no código-fonte do projeto enviado.** Recursos apresentados na interface como entrega expressa, serviços farmacêuticos e promoções representam conteúdo visual/informativo; não foi identificada, no código analisado, integração com pagamento, transportadora, emissão de receita ou agendamento real.

---

## 1. Identificação do projeto

| Item | Informação |
|---|---|
| Nome | Farmaura |
| Tipo | Aplicação web / sistema de gerenciamento de farmácia |
| Linguagem | Java 21 |
| Framework | Spring Boot 4.1.2 |
| Gerenciador de dependências | Maven |
| Persistência | Spring Data JPA / Hibernate |
| Banco de dados | H2 em modo arquivo |
| Interface | HTML, CSS, Thymeleaf e JavaScript |
| Segurança | Spring Security |
| Porta configurada | 8082 |
| Pacote-base | `br.com.farmaura` |

## 2. Objetivo

O Farmaura tem como objetivo oferecer uma aplicação web para apresentar um catálogo de produtos de farmácia e organizar operações básicas de uma farmácia. A aplicação disponibiliza uma página inicial com informações institucionais, categorias e produtos, além de recursos de autenticação e APIs para manutenção dos dados.

O sistema diferencia os níveis de acesso de **Administrador**, **Farmacêutico** e **Cliente**, aplicando permissões distintas para operações administrativas e para o gerenciamento do catálogo.

## 3. Funcionalidades identificadas

### 3.1 Interface e catálogo
- Página inicial com apresentação institucional da Farmaura.
- Exibição de produtos cadastrados no banco de dados.
- Categorias geradas dinamicamente a partir dos produtos persistidos.
- Pesquisa e filtros de produtos na interface.
- Seções visuais de serviços, promoções, dicas de saúde e perguntas frequentes.
- Alternância de tema claro/escuro e interface de carrinho no front-end.

**Observação:** a presença de elementos visuais de carrinho, promoções e serviços na página não comprova, por si só, a existência de finalização de compra, pagamento ou agendamento integrado.

### 3.2 Autenticação e usuários
- Cadastro público de usuário com perfil `CLIENTE`.
- Login e logout gerenciados pelo Spring Security.
- Senhas armazenadas com hash BCrypt.
- Verificação de conta ativa.
- Administração de usuários por API, restrita ao perfil `ADMIN`.
- Possibilidade de alterar perfil, senha e estado ativo do usuário.
- Proteção contra exclusão do próprio usuário autenticado.

### 3.3 Produtos
- Listagem e consulta individual.
- Cadastro, edição e exclusão.
- Dados de identificação, SKU, nome, preço, controle, tarja, categoria terapêutica, concentração e imagem.
- Categorias disponibilizadas à página inicial com base nos produtos existentes.
- Cadastro e manutenção permitidos aos perfis `ADMIN` e `FARMACEUTICO`.

### 3.4 Clientes
- Cadastro, listagem, consulta e exclusão.
- Dados de identificação, tipo de cliente, segmento e status.
- O cadastro público pode criar um registro de cliente quando recebe `idCliente`.

### 3.5 Pedidos
- Criação, listagem, consulta e exclusão de pedidos.
- Associação de pedido a cliente, itens e entrega.
- Cálculo de subtotal por item e total do pedido.
- Atualização do status do pedido.

### 3.6 Entregas
- Cadastro, listagem e consulta de entregas.
- Armazenamento de endereço.
- Atualização do status de entrega.

## 4. Perfis e permissões

| Perfil | Permissões identificadas |
|---|---|
| `ADMIN` | Gerenciar usuários e produtos; acessar recursos protegidos após autenticação. |
| `FARMACEUTICO` | Gerenciar produtos; acessar recursos protegidos após autenticação. |
| `CLIENTE` | Acessar recursos que exigem autenticação, sem permissões administrativas de produtos ou usuários. |

A consulta `GET /api/produtos` e suas consultas individuais são públicas na configuração de segurança. As operações de escrita de produtos exigem `ADMIN` ou `FARMACEUTICO`. A API de usuários exige `ADMIN`.

As demais rotas da aplicação exigem autenticação pela regra geral do Spring Security, salvo as exceções explicitamente configuradas.

## 5. Tecnologias e dependências

| Tecnologia / biblioteca | Finalidade |
|---|---|
| Java 21 | Linguagem e plataforma de execução |
| Spring Boot 4.1.2 | Estrutura principal da aplicação |
| Spring WebMVC | Controladores web e endpoints REST |
| Spring Data JPA | Persistência e repositórios |
| Hibernate | Implementação ORM utilizada pelo JPA |
| Spring Security | Autenticação e autorização |
| Thymeleaf | Renderização de páginas HTML no servidor |
| Thymeleaf Extras Spring Security | Integração de atributos de segurança com Thymeleaf |
| H2 Database | Banco de dados relacional |
| Lombok | Dependência de apoio ao desenvolvimento |
| Spring Boot DevTools | Recursos de desenvolvimento |
| JUnit / Spring Boot Test | Testes automatizados |

O projeto utiliza Maven e declara as dependências de teste para JPA, Security, Thymeleaf e WebMVC.

## 6. Arquitetura e organização do código

O projeto segue uma separação por responsabilidades, organizada em pacotes:

```text
farmaura/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── database/
│   └── farmaura.mv.db
└── src/
    ├── main/
    │   ├── java/br/com/farmaura/
    │   │   ├── FarmauraApplication.java
    │   │   ├── config/
    │   │   ├── controller/
    │   │   ├── dto/
    │   │   ├── model/
    │   │   ├── repository/
    │   │   ├── security/
    │   │   └── service/
    │   └── resources/
    │       ├── application.properties
    │       ├── templates/
    │       │   └── index.html
    │       └── static/
    │           └── css/
    │               └── style.css
    └── test/
        └── java/br/com/farmaura/
            ├── FarmauraApplicationTests.java
            └── model/
```

### Responsabilidade dos pacotes

- **`config`**: configurações de segurança e carga inicial de dados.
- **`controller`**: recebe requisições HTTP e direciona as operações para os serviços.
- **`dto`**: objetos de transferência de dados para operações de usuário.
- **`model`**: entidades persistidas e enumerações do domínio.
- **`repository`**: interfaces de acesso aos dados com Spring Data JPA.
- **`security`**: representação do usuário autenticado e filtro de estado da conta.
- **`service`**: regras e operações de negócio.

### Principais classes do domínio

| Classe / enum | Descrição |
|---|---|
| `Produto` | Produto do catálogo, com preço, SKU, categoria e informações farmacêuticas. |
| `Cliente` | Cadastro de cliente, tipo, segmento e status. |
| `Usuario` | Credenciais, perfil e estado ativo da conta. |
| `Pedido` | Pedido associado a cliente, itens, entrega e status. |
| `ItemPedido` | Produto, quantidade, preço unitário e desconto aplicado no pedido. |
| `Entrega` | Endereço e situação da entrega. |
| `Perfil` | Perfis `ADMIN`, `FARMACEUTICO` e `CLIENTE`. |
| `StatusPedido` | Estados possíveis de um pedido. |
| `StatusEntrega` | Estados possíveis de uma entrega. |

### Relacionamentos principais

- Um pedido referencia um cliente.
- Um pedido contém uma lista de itens (`ItemPedido`).
- Um pedido possui uma associação com uma entrega.
- Cada item de pedido referencia um produto.
- O usuário possui um perfil e um indicador de conta ativa.

## 7. Endpoints da API

As rotas abaixo foram identificadas nos controladores do projeto.

### 7.1 Produtos — `/api/produtos`

| Método | Rota | Operação | Acesso |
|---|---|---|---|
| `GET` | `/api/produtos` | Lista produtos | Público |
| `GET` | `/api/produtos/{id}` | Consulta produto | Público |
| `POST` | `/api/produtos` | Cadastra produto | ADMIN / FARMACEUTICO |
| `PUT` | `/api/produtos/{id}` | Atualiza produto | ADMIN / FARMACEUTICO |
| `DELETE` | `/api/produtos/{id}` | Exclui produto | ADMIN / FARMACEUTICO |

O cadastro exige `idProduto` e verifica se já existe produto com o mesmo identificador.

### 7.2 Clientes — `/api/clientes`

| Método | Rota | Operação |
|---|---|---|
| `GET` | `/api/clientes` | Lista clientes |
| `GET` | `/api/clientes/{id}` | Consulta cliente |
| `POST` | `/api/clientes` | Cadastra cliente |
| `DELETE` | `/api/clientes/{id}` | Exclui cliente |

### 7.3 Pedidos — `/api/pedidos`

| Método | Rota | Operação |
|---|---|---|
| `GET` | `/api/pedidos` | Lista pedidos |
| `GET` | `/api/pedidos/{id}` | Consulta pedido |
| `POST` | `/api/pedidos` | Cria pedido |
| `PATCH` | `/api/pedidos/{id}/status?status=PROCESSANDO` | Atualiza status |
| `DELETE` | `/api/pedidos/{id}` | Exclui pedido |

Valores aceitos para status: `PENDENTE`, `PROCESSANDO`, `ENVIADO`, `ENTREGUE` e `CANCELADO`.

### 7.4 Entregas — `/api/entregas`

| Método | Rota | Operação |
|---|---|---|
| `GET` | `/api/entregas` | Lista entregas |
| `GET` | `/api/entregas/{id}` | Consulta entrega |
| `POST` | `/api/entregas` | Cadastra entrega |
| `PATCH` | `/api/entregas/{id}/status?status=EM_TRANSITO` | Atualiza status |

Valores aceitos para status: `EM_TRANSITO`, `ENTREGUE` e `EXTRAVIADO`.

### 7.5 Usuários — `/api/usuarios`

Todas as operações dessa API são restritas ao perfil `ADMIN`.

| Método | Rota | Operação |
|---|---|---|
| `GET` | `/api/usuarios` | Lista usuários |
| `GET` | `/api/usuarios/{id}` | Consulta usuário |
| `POST` | `/api/usuarios` | Cria usuário |
| `PUT` | `/api/usuarios/{id}` | Atualiza usuário |
| `PATCH` | `/api/usuarios/{id}/status` | Alterna ativo/inativo |
| `DELETE` | `/api/usuarios/{id}` | Exclui usuário, exceto o próprio usuário autenticado |

### 7.6 Autenticação

| Método | Rota | Operação |
|---|---|---|
| `POST` | `/auth/cadastrar` | Cadastro público de usuário cliente |
| `POST` | `/auth/login` | Processamento de login pelo Spring Security |
| `POST` | `/logout` | Encerramento da sessão |

O cadastro público recebe `username` e `senha`; `idCliente` e `tipoCliente` são opcionais. O perfil criado por esse fluxo é sempre `CLIENTE`.

## 8. Banco de dados

O projeto utiliza H2 em modo arquivo, com os parâmetros definidos em `src/main/resources/application.properties`.

| Configuração | Valor |
|---|---|
| URL JDBC | `jdbc:h2:file:./database/farmaura` |
| Driver | `org.h2.Driver` |
| Usuário | `sa` |
| Senha | Vazia na configuração atual |
| Schema | `APP` |
| Estratégia de atualização | `spring.jpa.hibernate.ddl-auto=update` |
| Console | Habilitado em `/h2-console` |
| Porta web | `8082` |

A configuração `ddl-auto=update` permite que o Hibernate atualize a estrutura do banco conforme as entidades. Para ambientes de produção, recomenda-se adotar migrações controladas e credenciais seguras.

### Tabelas mapeadas pelas entidades

| Entidade | Tabela |
|---|---|
| `Produto` | `tb_produto` |
| `Cliente` | `tb_cliente` |
| `Usuario` | `tb_usuario` |
| `Pedido` | `tb_pedido` |
| `ItemPedido` | `tb_item_pedido` |
| `Entrega` | `tb_entrega` |

### Acesso ao console H2

Com a aplicação em execução, acesse:

`http://localhost:8082/h2-console`

Use a URL JDBC `jdbc:h2:file:./database/farmaura`, usuário `sa` e senha vazia, conforme a configuração atual.

A configuração específica `H2ConsoleSecurityConfig` está anotada com `@Profile("dev")`. A configuração principal também contém regras para o caminho `/h2-console/**`. O acesso efetivo deve ser validado com o perfil e a configuração de segurança usados na execução.

## 9. Carga inicial de dados

A classe `CargaAutomatica` executa uma carga inicial quando a aplicação inicia:

- Cria o usuário `admin` se ainda não existir.
- Cria o usuário `farmaceutico` se ainda não existir.
- Insere seis produtos se o catálogo estiver vazio.

Os usuários iniciais definidos no código são:

| Usuário | Perfil | Senha inicial definida no código |
|---|---|---|
| `admin` | `ADMIN` | `admin123` |
| `farmaceutico` | `FARMACEUTICO` | `123456` |

**Atenção:** essas credenciais são apenas valores de desenvolvimento presentes no código. Altere-as imediatamente, não as utilize em produção e não publique credenciais reais em repositórios.

Os produtos iniciais incluem Dipirona, Amoxicilina, Vitamina C + Zinco, Protetor Solar Facial, Paracetamol e Fralda Infantil. A carga só é executada quando a tabela de produtos está vazia.

## 10. Como executar o projeto

### Pré-requisitos

- JDK 21 instalado.
- Maven (ou utilização do Maven Wrapper incluído no projeto).
- IDE de sua preferência, como IntelliJ IDEA.
- Git, caso deseje clonar o repositório.

### Clonar o repositório

```bash
git clone URL_DO_REPOSITORIO
cd farmaura
```

Substitua `URL_DO_REPOSITORIO` pelo endereço real do repositório. Se o projeto já estiver baixado, abra a pasta que contém o `pom.xml` principal.

### Executar pelo Maven Wrapper

No Windows:

```bash
mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

Alternativamente, com Maven instalado:

```bash
mvn spring-boot:run
```

### Acessar a aplicação

Depois que o Spring Boot iniciar, abra:

`http://localhost:8082`

O console H2, quando acessível conforme a configuração de segurança, fica em:

`http://localhost:8082/h2-console`

### Gerar o pacote

```bash
mvn clean package
```

O artefato JAR é gerado na pasta `target/`.

## 11. Testes automatizados

O projeto contém testes JUnit para a inicialização da aplicação e para as classes do pacote `model`.

Classes de teste identificadas:

- `FarmauraApplicationTests`
- `UsuarioTest`
- `ClienteTest`
- `StatusPedidoTest`
- `StatusEntregaTest`
- `ItemPedidoTest`
- `EntregaTest`
- `PedidoTest`
- `PerfilTest`
- `ProdutoTest`

Os testes de modelo verificam construtores, getters e setters, valores iniciais, enumerações, associação de objetos e cálculos de subtotal/total.

Para executar:

```bash
mvn test
```

Para gerar o relatório de testes:

```bash
mvn test
```

Os relatórios XML e TXT do Surefire ficam em `target/surefire-reports/`.

**Escopo atual:** os testes encontrados concentram-se nos modelos e no carregamento do contexto. Não foram identificados, na estrutura de testes analisada, conjuntos específicos de testes de integração para todos os controllers, services, repositórios, autenticação e autorização.

## 12. Regras de negócio observadas

1. O cadastro público cria usuários com perfil `CLIENTE`.
2. Senhas são codificadas com BCrypt antes da persistência.
3. O usuário pode estar ativo ou inativo; o filtro `EstadoContaFilter` participa do controle de contas.
4. Somente `ADMIN` pode administrar usuários.
5. `ADMIN` e `FARMACEUTICO` podem cadastrar, editar e excluir produtos.
6. O catálogo público permite consultar produtos.
7. As categorias da página inicial são obtidas a partir dos produtos cadastrados, evitando manter uma lista fixa de categorias no HTML.
8. O subtotal do item é calculado por `(preço unitário × quantidade) − desconto`.
9. O total do pedido corresponde à soma dos subtotais dos itens.
10. Os status de pedido e entrega são representados por enumerações.

## 13. Pontos de atenção e melhorias futuras

Os itens a seguir são recomendações de evolução, não funcionalidades que este documento afirma já estarem implementadas:

- Implementar validações de entrada com Bean Validation (`@Valid`, `@NotBlank`, `@Positive` etc.).
- Adicionar testes unitários para services e testes de integração para controllers e regras de segurança.
- Revisar o controle de acesso das APIs de clientes, pedidos e entregas, definindo permissões por perfil e por proprietário do recurso.
- Avaliar o uso de `BigDecimal` para valores monetários, evitando imprecisões de ponto flutuante.
- Não manter senhas padrão no código; utilizar variáveis de ambiente ou mecanismo seguro de configuração.
- Desabilitar ou restringir o console H2 fora do ambiente de desenvolvimento.
- Substituir a configuração de CSRF por uma estratégia adequada ao modelo de autenticação e aos clientes da API antes de disponibilizar o sistema publicamente.
- Implementar validação de receita e regras legais para medicamentos controlados, caso o sistema venha a operar com vendas reais.
- Integrar carrinho, checkout, pagamentos, estoque, frete e rastreamento caso façam parte do escopo futuro.
- Adicionar paginação, ordenação e tratamento padronizado de erros nas APIs.
- Documentar a API com OpenAPI/Swagger.
- Utilizar ferramenta de migração de banco, como Flyway ou Liquibase, em vez de depender apenas de `ddl-auto=update`.
- Revisar dados pessoais e requisitos de privacidade/LGPD antes de usar dados reais de clientes.

## 14. Considerações finais

O Farmaura apresenta uma base de aplicação web organizada em camadas, com catálogo de produtos, cadastro de usuários e clientes, estrutura para pedidos e entregas, persistência relacional e controle de acesso por perfil. A utilização de Spring Boot, Spring Data JPA, Thymeleaf, Spring Security e H2 permite demonstrar conceitos importantes de desenvolvimento backend e integração com interface web.

A documentação descreve o estado do código analisado. Funcionalidades adicionais, integrações externas e requisitos de produção devem ser confirmados e implementados antes de considerar o sistema pronto para operação comercial.

---

**Documento:** README / documentação técnica do Farmaura  
**Versão do projeto identificada no `pom.xml`:** `0.0.1-SNAPSHOT`  
**Java:** 21  
**Spring Boot:** 4.1.2
