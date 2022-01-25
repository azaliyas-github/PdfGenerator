FROM openjdk:11-jre-slim
WORKDIR /opt/PdfGenerator/
COPY target/PdfGenerator-*.jar PdfGenerator.jar

RUN adduser --system --group spring
USER spring:spring
ENTRYPOINT ["java", "-jar", "PdfGenerator.jar"]
