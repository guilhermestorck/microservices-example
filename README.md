# POC Micronaut Kotlin

This project has a couple of microservices that interact with each other.

- `catalog-service`: holds product information and provides a search
- `order-service`: agreggates products into an order

For each project, a few commands are available:

- `make build`: builds the projects
- `make run`: deploys and runs the app and all of its dev dependencies in docker containers
- `make dev-dependencies`: deploys and runs all of the app's dev dependencies
- `make run-dev`: runs the app (outside containers)
- `make component-tests`: run the component tests. This command requires that `make run` has been triggered on another terminal

## First run

For instance, after running:

```
cd order-service
make run
```

The following is available:

- App: http://localhost:8080
- Consul: http://localhost:8500
- Zipkin: http://localhost:9411
- Kibana: http://localhost:5601

## Clean architecture

Each microservice has 3 modules: app, contract and core

- `core`: contains all the business logic and entities. Should not depend on any lib/framework/database
- `contract`: describes the endpoints of the microservice, together with the entities expected and returned from them. It also contains converters between the core entities and the contract entities
- `app`: contians all the code that is lib/framework/database specific. This module dependes on both `core` and `contract`

## Technologies used

- Consul: Service discovery / health checking
- Micronaut: Microservice framework
- Kotlin: Linguagem de programação
- ELK stack: Log aggregator + dashboards
- Spek + mockk: Unit testing for Kotlin
- Zipkin: Distributed tracing
- Make: Automation
- Vault: Secret management
- Subby4Node: Mock server
- FF4J: Feature toggles
- JaCoCo: Code coverage
- Gradle: Build automation system
- Cucumber: Component tests

## Feedback

There's a [spreadsheet](https://docs.google.com/spreadsheets/d/1J5mbBrx1C_ZiXRZ01va3nK6MuXMHtm_WIOAOKIMhDog/edit#gid=0) where you can comment pros and cons for each of the tecnologies used here.

## TODO

- ~~Externalize the gradle configuration parts that are common among microservices~~
- Implement component tests on order-service
- Add FF4J
- Integrate a Postgres database with catalog-service
- Deploy all microservices with k8s
- Add border service
- Add shared configuration (through Spring Cloud Config)
- Add Authentication/JWT
- Add monitoring with Prometheus/newRelic/Dynatrace
- Integrate RabbitMQ with order-service
- Create an example that demonstrates the retry and fallback capabilities of Micronaut
- Create an example of a reactive type controller using Micronaut + netty
- Improve code quality with Sonar
