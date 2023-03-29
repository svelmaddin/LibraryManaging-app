FROM openjdk:17-jdk-slim AS build

COPY . .
FROM openjdk:17-jdk-slim
WORKDIR TaskForWorkGradle
COPY --from=build build/*.jar TaskForWork-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java" ,"-jar" , "/build/libs/TaskForWork-0.0.1-SNAPSHOT.jar"]