.PHONY: build
build :
	gradle build

.PHONY: clean
clean :
	gradle clean

.PHONY: run
run :
	gradle run

.PHONY: run-dependencies
run-dependencies :
	docker-compose -f dependencies/mock-server/docker-compose.local.yml up -d --build --force-recreate

app/build/libs/app.jar : build

.PHONY: run-prod
run-prod : app/build/libs/app.jar dependencies/mock-server/docker-compose.yml
	docker-compose -f dependencies/mock-server/docker-compose.local.yml -f dependencies/mock-server/docker-compose.yml up -d --build --force-recreate

.PHONY: component-tests
component-tests :
	gradle -p component-test check
