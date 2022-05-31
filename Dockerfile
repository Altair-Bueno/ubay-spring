# @author Altair Bueno
FROM openjdk:17-alpine
ENV SPRING_PROFILES_ACTIVE=docker
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG WAR_FILE=target/*.war
COPY ${WAR_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]