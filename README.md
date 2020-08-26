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
* Para obter a versão `http://localhost:8080/actuator/info`

## Documentação API

* `http://localhost:8080/api-docs/` Retorna json com os endPoints;

* `http://localhost:8080/api-docs.yaml` Retorna arquivo forma yam com os endPoints;

* `http://localhost:8080/swagger-ui.html` Acessa o swagger-ui;


## standard-version
* Test `npm run release -- --dry-run`

