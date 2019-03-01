FROM frolvlad/alpine-oraclejre8:slim
EXPOSE 9001
RUN mkdir -p /app/
ADD build/libs/workshop-graphql-0.0.1-SNAPSHOT.jar /app/wesovilabs-workshop-java.jar
CMD ["--spring.config.location=file:/opt/config/application.yaml"]
ENTRYPOINT ["java", "-jar", "/app/wesovilabs-workshop-java.jar"]
