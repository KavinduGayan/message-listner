FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./../../../active-mq-producer/target/*.jar active-mq-producer.jar
ENTRYPOINT ["java","-jar","active-mq-producer.jar"]
