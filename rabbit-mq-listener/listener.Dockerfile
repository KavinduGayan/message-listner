FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./target/*.jar rabbit-mq-listener.jar
ENTRYPOINT ["java","-jar","rabbit-mq-listener.jar"]
