
/**
 * Carga la configuración del sistema desde config.properties
 */
package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties = new Properties();

    static {

        try {

            // 1. Buscar dentro del classpath (src)
            InputStream input = ConfigLoader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            // 2. Si no existe, buscar externo
            if (input == null) {
                input = new FileInputStream("config.properties");
            }

            properties.load(input);

            System.out.println("Configuración cargada correctamente");

        } catch (Exception e) {
            System.out.println("Error cargando config.properties");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}