FROM openjdk:17-jdk-alpine
ARG JAR_FILE=demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} myboot.jar
ENTRYPOINT [ "java", "-jar", "/myboot.jar", "--spring.config.name=testJarPro"]