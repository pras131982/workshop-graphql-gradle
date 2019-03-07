DOCKERCOMPOSE_PATH=src/main/resources/docker-compose/docker-compose.yml
DOCKER_DOCKERFILE=src/main/resources/docker/Dockerfile
DOCKER_IMG=wesovilabs/workshop-graphql-java:local
DOCKER_RUN=docker-compose -f $(DOCKERCOMPOSE_PATH) run --rm -p9001:9001 api
DOCKER_DATABASE=docker-compose -f $(DOCKERCOMPOSE_PATH) run --rm -p5456:5432 database
DOCKER_STOP=docker-compose -f $(DOCKERCOMPOSE_PATH) down -v
DOCKER_BUILD=docker build -f ${DOCKER_DOCKERFILE} -t=${DOCKER_IMG} .

deps:
	./gradlew dependencies

docker-build:
	./gradlew build;
	$(DOCKER_BUILD)

deploy: docker-build ;
	$(DOCKER_RUN)

database: ;
	$(DOCKER_DATABASE)

docker-stop: ;
	$(DOCKER_STOP)

clean:
	./gradlew clean

run:
	./gradlew bootRun
