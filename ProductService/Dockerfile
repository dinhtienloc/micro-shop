# Maven build stage
FROM maven:3.6.3-openjdk-11 as maven
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY . .	
RUN mvn clean package -Dmaven.test.skip=true

# Package stage
FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=maven target/*.jar app.jar
ADD wait-for-it.sh /wait-for-it.sh
RUN apk add --no-cache bash
RUN chmod +x wait-for-it.sh
ENTRYPOINT ["java","-jar","/app.jar"]