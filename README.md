# Logistic-API

## Actuator

* Os endpoints do actuator é que vão permitir o monitoramento da aplicação, fornecendo informações tais como: health-check, coleta de métricas, http trace etc... Para verificar a saúde da aplicação, acesse `http://localhost:8080/actuator` para links;

```json
{"_links":{"self":{"href":"http://localhost:8080/actuator","templated":false},
"health":{"href":"http://localhost:8080/actuator/health","templated":false},
"health-path":{"href":"http://localhost:8080/actuator/health/{*path}",
"templated":true},"info":{"href":"http://localhost:8080/actuator/info",
"templated":false}}}
```

* Migração do flyway `http://localhost:8080/actuator/flyway`

* Para obter a versão `http://localhost:8080/actuator/info`

## Documentação API

* `http://localhost:8080/api-docs/` Retorna json com os endPoints;

* `http://localhost:8080/api-docs.yaml` Retorna arquivo forma yam com os endPoints;

* `http://localhost:8080/swagger-ui.html` Acessa o swagger-ui;

## Containers

### install

```sh
    sudo apt  install docker-compose
```

### Adicionando docker ao seu grupo de usuário

```sh
    sudo gpasswd -a $USER docker
```

* Faça um `newgrp docker` ou efetue logout / in para ativar as alterações nos grupos
* Faça um teste `docker run hello-world`

### Start e stop do container

* Primeira vez `docker-compose up -d`

```sh
    docker-compose start
    docker-compose stop
```

* docker run -d --name postDb postgres:alpine -e POSTGRES_PASSWORD=admin -p 5432:5432


* Acesse `http://localhost:8091` para admin do banco postegres.

## Testes

* É utilizado o banco H2, acesso: `localhost:8080/h2-console`;
* Colocar JDBC URL: jdbc:h2:mem:testdb

## Conventional Commits

[Conventional Commits](https://www.conventionalcommits.org/)

## ChangeLogs

[standard-version](https://www.npmjs.com/package/standard-version)

* Test `npm run release -- --dry-run`
* `npm run release -- --release-as 0.0.1-SNAPSHOT --dry-run`

## Versionamento

[semver](https://semver.org/)
