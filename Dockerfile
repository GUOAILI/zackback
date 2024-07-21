FROM openjdk:17
LABEL maintainer ="zhoujd@hotmail.co.jp"
ADD target/zackback-0.0.1-SNAPSHOT.jar dpj-backed.jar
ENTRYPOINT ["java","-jar","dpj-backed.jar"]