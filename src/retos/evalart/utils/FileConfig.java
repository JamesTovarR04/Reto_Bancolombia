package retos.evalart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase para manejo del archivo "config.properties"
 * @autor James Tovar Rodriguez
 */
public class FileConfig {

    private static Properties properties;

    private FileConfig(){};

    /**
     * Este método retorna una propiedad del archivo segun su clave.
     * @param key Clave de la propiedad
     * @return valor de la propiedad
     */
    public static String getPropertie(String key, String defaultValue) {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(new FileInputStream("config.properties"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(key,defaultValue);
    }

}
