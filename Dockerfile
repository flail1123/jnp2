FROM openjdk:8u252-jdk
WORKDIR /io
CMD ["java","-jar","build/libs/sweaters-0.0.1-SNAPSHOT.jar"]
