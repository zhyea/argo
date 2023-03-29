FROM openjdk:8

ENV SPRING_PROFILES_ACTIVE prod

EXPOSE 8087
VOLUME /tmp
ADD target/*.jar liens-admin.jar
ENTRYPOINT ["java","-jar","/liens-admin.jar"]