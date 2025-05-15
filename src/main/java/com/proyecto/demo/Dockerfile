

FROM eclipse-temurin:8
COPY target/SpringUsoSesionesUsuario-0.0.1-SNAPSHOT.war application.war

ENV DATABASE_URL jdbc:mysql://192.168.1.9:3306/app_barra8
ENV DATABASE_USERNAME root
ENV DATANASE_PASSWORD root
ENV DATABASE_PLATFORM org.hibernate.dialect.MySQL57Dialect
ENV DATABASE_DRIVER com.mysql.connector.j.Driver
ENTRYPOINT ["java","-war","/application.war"]
    