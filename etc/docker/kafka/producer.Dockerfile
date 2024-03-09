FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./../../../rabbit-mq-producer/target/*.jar kafka-producer.jar
ENTRYPOINT ["java","-jar","/kafka-producer.jar"]
