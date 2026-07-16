# Grupo Capoeira Mundial

Site institucional administrável desenvolvido com **Java 17**, **Spring Boot MVC**, **Thymeleaf**, **Spring Security** e **JPA/Hibernate**.

Por padrão, a aplicação utiliza o banco de dados **H2** persistente. Também é possível utilizar **PostgreSQL** por meio do perfil `postgres`.

---

# Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA (Hibernate)
- Thymeleaf
- Maven
- H2 Database
- PostgreSQL
- WildFly 27+

---

# Executando o projeto no IntelliJ IDEA

## Pré-requisitos

- JDK 17
- Maven
- IntelliJ IDEA
- PostgreSQL (opcional)

### Passos

1. Abra o projeto como um projeto Maven.
2. Configure o JDK 17.
3. Caso utilize PostgreSQL:
   - Crie o banco de dados `capoeira_mundial`.
   - Conceda acesso ao usuário configurado na aplicação.
4. Crie uma configuração **Spring Boot** para a classe:

```
CapoeiraMundialApplication
```

5. Execute utilizando um dos perfis:

```
--spring.profiles.active=dev
```

ou

```
--spring.profiles.active=postgres
```

6. Acesse:

```
http://localhost:8080/
```

Painel administrativo:

```
http://localhost:8080/admin
```

---

# Utilizando PostgreSQL

Para utilizar PostgreSQL, defina as variáveis de ambiente da aplicação antes da execução.

Ative o perfil:

```
--spring.profiles.active=postgres
```

> **Importante:** Nunca publique credenciais no Git. Utilize variáveis de ambiente ou a configuração do servidor.

O administrador padrão é criado automaticamente na primeira execução e sua senha é armazenada utilizando **BCrypt**.

---

# Build do projeto

Execute:

```bash
mvn clean package
```

O arquivo gerado será:

```
target/capoeira-mundial.war
```

---

# Deploy no WildFly

Requisitos:

- WildFly 27 ou superior
- Java 17

No IntelliJ Ultimate:

1. Crie uma configuração **JBoss Server > Local**.
2. Aponte para a instalação do WildFly.
3. Em **Deployment**, adicione o artefato:

```
capoeira-mundial:war
```

4. Configure as variáveis de ambiente.
5. Inicie o servidor.

A aplicação ficará disponível em:

```
http://localhost:8080/capoeira-mundial/
```

Também é possível copiar diretamente o arquivo `.war` para:

```
WILDFLY_HOME/standalone/deployments/
```

O Tomcat está configurado com escopo `provided`, pois o WildFly fornece o container Servlet.

---

# Estrutura do projeto

```text
src/main/java/br/com/capoeiramundial/
├── config/        Configurações de segurança e recursos
├── controller/    Controllers das páginas públicas e administrativas
├── entity/        Entidades JPA
├── repository/    Repositórios
└── service/       Serviços da aplicação

src/main/resources/
├── static/        Arquivos CSS, JavaScript e imagens
├── templates/     Templates Thymeleaf
└── application*.properties
```

---

# Upload de arquivos

Tipos permitidos:

- JPG
- JPEG
- PNG
- WEBP
- MP4

Tamanho máximo por arquivo:

```
100 MB
```

---

# Observações

O projeto possui o arquivo:

```
WEB-INF/jboss-deployment-structure.xml
```

Esse arquivo isola os módulos **SLF4J** fornecidos pelo WildFly, evitando conflitos com o **Logback** utilizado pelo Spring Boot.

Após qualquer alteração nesse arquivo:

1. Execute **Build > Rebuild Project**.
2. Remova o WAR antigo do WildFly.
3. Faça um novo deploy da aplicação.

Isso evita erros como:

```
LoggerFactory is not a Logback LoggerContext
```