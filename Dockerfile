FROM maven

## copy over the source files
ADD src src/
ADD pom.xml pom.xml

## clean install and build
RUN mvn clean
RUN mvn package
## add the compiled jar
ADD target/toka.jar toka.jar

### improvement: multi-stage docker build

## expose our port
EXPOSE 8080
## run the compiled jar
ENTRYPOINT ["java","-jar","toka.jar"]
