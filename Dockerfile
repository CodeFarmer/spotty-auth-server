FROM openjdk:11.0.1-jre-slim-stretch

COPY target/spotty-auth-server-0.1.0-SNAPSHOT-standalone.jar .

# server on port 3000
CMD java -jar spotty-auth-server-0.1.0-SNAPSHOT-standalone.jar
