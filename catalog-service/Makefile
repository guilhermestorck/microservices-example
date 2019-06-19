.PHONY: build
build :
	gradle build

.PHONY: clean
clean :
	gradle clean

.PHONY: run-dev
run-dev :
	gradle run

.PHONY: run-dependencies
run-dev-dependencies :
	docker-compose -f dependencies/dev-server/docker-compose.base.yml up -d --build --force-recreate

.PHONY: run
run : app/build/libs/app.jar dependencies/dev-server
	docker-compose -f dependencies/dev-server/docker-compose.yml up -d --build --force-recreate

.PHONY: component-tests
component-tests :
	gradle -p component-test check
