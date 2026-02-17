FROM eclipse-temurin:17-jre
COPY target/VirtualShop*.jar /usr/src/VirtualShop.jar
COPY src/main/resources/application.properties /opt/conf/application.properties
CMD ["java", "-jar", "/usr/src/VirtualShop.jar", "--spring.config.location=file:/opt/conf/application.properties"]