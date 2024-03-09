FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./../../../rabbit-mq-producer/target/*.jar rabbit-mq-producer.jar
ENTRYPOINT ["java","-jar","rabbit-mq-producer.jar"]
