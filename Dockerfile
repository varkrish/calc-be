FROM registry.access.redhat.com/ubi8/openjdk-11:latest
COPY app.jar /
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]