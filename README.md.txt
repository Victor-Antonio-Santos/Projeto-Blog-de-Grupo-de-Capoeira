# Grupo Capoeira Mundial

Site institucional administrável em Java 17, Spring Boot MVC, Thymeleaf, Spring Security e JPA/Hibernate. Por padrão usa H2 persistente no diretório de dados do WildFly. O PostgreSQL continua disponível pelo perfil `postgres`.

## Execução simples no WildFly

## PostgreSQL opcional

Nunca publique credenciais no Git. Defina as variáveis abaixo no sistema, no IntelliJ ou na configuração do WildFly:

```text
CAPOEIRA_DB_URL=jdbc:postgresql://localhost:5432/capoeira_mundial
CAPOEIRA_DB_USERNAME=capoeira
CAPOEIRA_DB_PASSWORD=sua_senha
CAPOEIRA_ADMIN_USERNAME=admin
CAPOEIRA_ADMIN_PASSWORD=uma_senha_forte
UPLOAD_DIR=C:/capoeira-mundial/uploads
```

Ative com `--spring.profiles.active=postgres`. As variáveis têm o prefixo `CAPOEIRA_` para não colidirem com outros WARs. O administrador inicial é criado no primeiro start e a senha é armazenada com BCrypt.

## Executar no IntelliJ IDEA

1. Abra esta pasta como projeto Maven e selecione um JDK 17.
2. Crie o banco `capoeira_mundial` no PostgreSQL e dê acesso ao usuário `capoeira`.
3. Em **Run > Edit Configurations**, crie uma configuração Spring Boot para `CapoeiraMundialApplication`.
4. Use o perfil `dev` (`--spring.profiles.active=dev`) ou configure as variáveis acima.
5. Execute e acesse `http://localhost:8080/`. O painel fica em `/admin`.

## Build e deploy no WildFly

Requer WildFly 27 ou superior e Java 17.

```bash
mvn clean package
```

O arquivo será gerado em `target/capoeira-mundial.war`. No IntelliJ Ultimate:

1. Adicione uma configuração **JBoss Server > Local** apontando para o WildFly.
2. Em **Deployment**, adicione o artifact `capoeira-mundial:war`.
3. Configure as variáveis de ambiente na aba de inicialização.
4. Inicie o servidor e acesse `/capoeira-mundial/`.

O projeto inclui `WEB-INF/jboss-deployment-structure.xml`. Ele isola os módulos
SLF4J fornecidos pelo WildFly para que não concorram com o Logback do Spring
Boot. Depois de alterar essa configuração, faça **Build > Rebuild Project** e
remova/republique o artifact no WildFly; um WAR antigo continuará apresentando
o erro `LoggerFactory is not a Logback LoggerContext`.

Também é possível copiar o WAR para `WILDFLY_HOME/standalone/deployments/`. O Tomcat está com escopo `provided`; o WildFly fornece o servlet container.

## Estrutura

```text
src/main/java/br/com/capoeiramundial/
├── config/       segurança, seed e recursos web
├── controller/   páginas públicas, admin e erros
├── entity/       entidades JPA
├── repository/   acesso a dados
└── service/      armazenamento e validação de uploads
src/main/resources/
├── static/       CSS e JavaScript
├── templates/    páginas Thymeleaf públicas e administrativas
└── application*.properties
```

Uploads aceitos: JPG, JPEG, PNG, WEBP e MP4. O tamanho máximo é 100 MB por arquivo.
