FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./../../../active-mq-listener/target/*.jar active-mq-listener.jar
ENTRYPOINT ["java","-jar","active-mq-listener.jar"]
