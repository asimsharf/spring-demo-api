0:
    mvn clean spring-boot:run

1:
    docker build -t spring-boot-docker:spring-docker .

2:
    docker run -p 8080:8080 spring-boot-docker:spring-docker .

3:
    docker ps