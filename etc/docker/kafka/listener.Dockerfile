FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./../../../rabbit-mq-listener/target/*.jar kafka-listener.jar
ENTRYPOINT ["java","-jar","/kafka-listener.jar"]
