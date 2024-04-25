FROM ubuntu:22.04 AS build

RUN apt-get update &&  \
    apt-get install openjdk-21-jdk -y  &&  \
    apt-get clean \

COPY . .

RUN apt-get install maven -y &&  \
    mvn clean install &&  \
    apt-get clean

FROM openjdk:17-jdk-slim
EXPOSE 8080

COPY --from=build /target/restaurant-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]