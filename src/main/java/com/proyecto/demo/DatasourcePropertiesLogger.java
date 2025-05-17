package com.proyecto.demo; // Asegúrate de que este sea el paquete correcto o uno adecuado

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DatasourcePropertiesLogger implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatasourcePropertiesLogger.class);

    // Inyecta el valor de spring.datasource.url. Si no está definido, será null.
    @Value("${spring.datasource.url:#{null}}")
    private String dbUrl;

    // Inyecta el valor de spring.datasource.username.
    @Value("${spring.datasource.username:#{null}}")
    private String dbUsername;

    // Inyecta el valor de spring.datasource.password.
    @Value("${spring.datasource.password:#{null}}")
    private String dbPassword;

    // También intentaremos leer la variable de entorno DATABASE_URL directamente,
    // ya que es una fuente común de problemas en plataformas como Render.
    @Value("${DATABASE_URL:#{null}}")
    private String databaseUrlEnv;

    @Override
    public void run(String... args) throws Exception {
        logger.info("--------------------------------------------------------------------");
        logger.info("INICIO: DEBUGGING VALORES DE CONFIGURACIÓN DEL DATASOURCE");
        logger.info("--------------------------------------------------------------------");
        logger.info("Valor resuelto para spring.datasource.url: [{}]", dbUrl);
        logger.info("Valor resuelto para spring.datasource.username: [{}]", dbUsername);
        
        // Por seguridad, solo registraremos si la contraseña está presente y su longitud.
        if (dbPassword != null && !dbPassword.isEmpty()) {
            logger.info("Valor resuelto para spring.datasource.password: [PRESENTE (longitud: {})]", dbPassword.length());
        } else if (dbPassword != null && dbPassword.isEmpty()){
            logger.info("Valor resuelto para spring.datasource.password: [PRESENTE (vacía)]");
        }
         else {
            logger.info("Valor resuelto para spring.datasource.password: [NO PRESENTE (null)]");
        }

        logger.info("Valor directo de la variable de entorno DATABASE_URL (si existe): [{}]", databaseUrlEnv);
        logger.info("--------------------------------------------------------------------");
        logger.info("FIN: DEBUGGING VALORES DE CONFIGURACIÓN DEL DATASOURCE");
        logger.info("--------------------------------------------------------------------");
    }
}
