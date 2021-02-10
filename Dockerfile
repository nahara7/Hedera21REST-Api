FROM openjdk:11
ADD target/toka.jar toka.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","toka.jar"]