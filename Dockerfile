FROM openjdk:11-jre
ENV APP_HOME=/usr/roi/
WORKDIR $APP_HOME
COPY keys/*.json apiKey.json
COPY dev.urbandigital.com.jks dev.urbandigital.com.jks
COPY build/libs/*.jar application.jar
COPY /logback.xml logback.xml
ENV GOOGLE_APPLICATION_CREDENTIALS /usr/roi/apiKey.json
CMD ["java", "-jar", "application.jar"]
