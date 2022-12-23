#FROM openjdk:11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 8080

FROM openjdk:11
ADD ./target/week-nine-0.0.1-SNAPSHOT.jar week-nine-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "week-nine-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080