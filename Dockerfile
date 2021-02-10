FROM maven

ADD pom.xml pom.xml
## clean install
RUN mvn clean

## copy over the source files
ADD src src/
## build the package
RUN mvn package

### improvement: multi-stage docker build

## expose our port
EXPOSE 8080
## run the compiled jar
ENTRYPOINT ["java","-jar","target/toka.jar"]
