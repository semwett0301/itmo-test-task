FROM gradle:7.4.2-jdk17 as build

WORKDIR /home/gradle/project

COPY build.gradle.kts settings.gradle.kts ./
COPY src/main/resources src/main/resources

RUN gradle build --no-daemon || return 0
COPY src ./src

RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
