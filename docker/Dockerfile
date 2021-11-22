FROM ibm-semeru-runtimes:open-11-jre-centos7
RUN groupadd spring && adduser spring -g spring
USER spring:spring
ARG JAR_FILE=target/docker-*-SNAPSHOT.jar
COPY ${JAR_FILE} docker.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/docker.jar"]
EXPOSE 8080