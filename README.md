![GitHub package version](https://img.shields.io/github/package-json/v/denissoliveira/logistic-api.svg)
[![GitHub pull requests](https://img.shields.io/github/issues-pr-raw/denissoliveira/logistic-api.svg)](https://github.com/denissoliveira/logistic-api/pulls)
[![GitHub issues](https://img.shields.io/github/issues/danielso2007/logistic-api.svg)](https://github.com/denissoliveira/logistic-api/issues?q=is%3Aopen+is%3Aissue)
![GitHub last commit](https://img.shields.io/github/last-commit/denissoliveira/logistic-api.svg)
[![GitHub issue/pull request author](https://img.shields.io/github/issues/detail/u/denissoliveira/logistic-api/1.svg)](https://github.com/denissoliveira/logistic-api/pulls)
![GitHub contributors](https://img.shields.io/github/contributors/denissoliveira/logistic-api.svg)
![GitHub top language](https://img.shields.io/github/languages/top/denissoliveira/logistic-api.svg)
[![GitHub](https://img.shields.io/github/license/denissoliveira/logistic-api.svg)](https://github.com/denissoliveira/logistic-api)
[![GitHub All Releases](https://img.shields.io/github/downloads/danielso2007/logistic-api/total.svg)](https://github.com/denissoliveira/logistic-api/archive/master.zip)
[![Conventional Commits](https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg)](https://conventionalcommits.org)

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

## Fonte

* https://github.com/acenelio/springboot2-ionic-backend
