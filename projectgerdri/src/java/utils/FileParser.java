package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Clase con métodos estáticos que recupera información de ficheros en varios formatos. Nos ahorramos constructores y atributos
 * innecesarios para un proceso que simplemente necesita la colección de datos contenida en el fichero especificado
 * @author gurug
 */
public class FileParser {
    
    /**
     * Retorna un HashMap con el contenido de un fichero XML parseado
     * @param filePath Ruta donde se encuentra el archivo XML a parsear. Recordar pasar el contexto desde un servlet además del
     * nombre y extensión porque sino no lo encontrará
     * @param language Adicionalmente podemos pasarle un idioma por si es un XML de recursos de texto y hay que apuntar al correcto
     * @return El HashMap con los recursos que contiene el XML. El id del elemento hace de key y el valor de value
     */
    public static HashMap<String, String> getResourcesFromXml(String filePath, String language) {
        //Primero controlamos el idioma del usuario. Si no nos llega o tiene formato erróneo, usamos el archivo español por defecto, sino cogemos el correspondiente
        if (language != null) {
            if (!language.isEmpty() && language.length() == 2) {
                filePath = filePath.replace("_lang", "_" + language);
            }
            else {
                filePath = filePath.replace("_lang", "_es");
            }            
        } else {
            filePath = filePath.replace("_lang", "_es");
        }
        
        HashMap<String,String> parsedXml = new HashMap<>();        
        try{
            SAXBuilder saxBuilder = new SAXBuilder();
            File inputFile = new File(filePath); 
            Document document = saxBuilder.build(inputFile);
            Element root = document.getRootElement();
            List allResources = root.getChildren();

            for (int i = 0; i < allResources.size(); i++) {
                Element el;
                el = (Element) allResources.get(i);
                String key, value;
                key = el.getAttributeValue("id");
                value = el.getValue();
                parsedXml.put(key, value);
            }
        }catch (JDOMException JDOMe){
            System.out.println("Error del DOM XML: " + JDOMe.getMessage());
        }catch (IOException IOe){
            System.out.println("Error lectura/escritura de fichero: " + IOe.getMessage());
        }catch (Exception e){
            System.out.println("Otro error: " + e.getMessage());
        }        
        return parsedXml;
    }
    
    /**
     * Retorna un conjunto de properties parseadas de un fichero indicado con la misma extensión
     * @param filePath Ruta donde se encuentra el archivo XML a parsear. Recordar pasar el contexto desde un servlet además del
     * nombre y extensión porque sino no lo encontrará
     * @return Objeto Properties de Java que contiene la combinación clave/valor de cada línea del fichero
     */
    public static Properties getDataFromProperties(String filePath) {
        Properties properties = new Properties();        
        try {
            File file = new File(filePath);
            FileInputStream fileInput = new FileInputStream(file);            
            properties.load(fileInput);
            fileInput.close();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }        
        return properties;
    }
}
