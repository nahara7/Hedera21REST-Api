FROM maven

ADD pom.xml pom.xml
## clean install
RUN mvn clean

## copy over the source files
ADD src src/
## build the package
RUN mvn package

### improvement: multi-stage docker build

## run the compiled jar on the correct port
ENTRYPOINT ["java","-jar","target/toka.jar"]
