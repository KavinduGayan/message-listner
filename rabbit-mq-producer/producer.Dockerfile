FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
#ARG JAR_FILE
COPY ./target/*.jar rabbit-mq-producer.jar
ENTRYPOINT ["java","-jar","/rabbit-mq-producer.jar"]
