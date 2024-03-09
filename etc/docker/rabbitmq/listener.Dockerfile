FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./../../../rabbit-mq-listener/target/*.jar rabbit-mq-listener.jar
ENTRYPOINT ["java","-jar","rabbit-mq-listener.jar"]
