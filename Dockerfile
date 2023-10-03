FROM openjdk:17
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar

# docker build -t umc:0.0.1 .
# docker-compose up
# sudo docker run -it saghnash/umc:v1.0 bash
# docker run --name umc-db-container -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d --mount source=umc-volume,target=/var/lib/mysql mysql:8.0.27
# docker volume create volume-name